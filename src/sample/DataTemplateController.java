package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ese on 3/8/2017.
 */
public class DataTemplateController extends VBox {

    @FXML
    private Button save_button;

    @FXML
    private ScrollPane scroll_pane;

    @FXML
    private VBox vbox;

    @FXML
    private TextField id_field;

    @FXML
    private Label t_name;

    @FXML
    private TextField amountfld;

    String service_name;
    String patientid;

    GridPane gridpane=new GridPane();

    int index=0;

    @FXML
    void initialize(){

        id_field.setText(patientid);
        save_button.setDisable(true);

        amountfld.textProperty().addListener((observable, oldvalue, newvalue)->{

            if(newvalue!=null && newvalue.length()>0) {


                if (!MyUtil.isInteger(newvalue)) {
                    amountfld.setText(oldvalue);
                }
                else {

                }
            }

            if(newvalue.length()<=0 || newvalue==null || newvalue.equals(" ")){
                save_button.setDisable(true);
            }
            else{
                save_button.setDisable(false);
            }



        });



        t_name.setText(service_name);
        t_name.getStyleClass().add("chlabel");

        List<String> servicetype=new ArrayList<>();
        List<String> servicecon=new ArrayList<>();

        try{

            String sql="select * from templates where service_name=?";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setString(1, service_name);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){

                servicetype.add(rs.getString("service_type"));
                servicecon.add(rs.getString("service_container"));

            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();

        }

        GridPane gridpane=new GridPane();
        ColumnConstraints ccons=new ColumnConstraints();
        ccons.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().addAll(ccons);
        gridpane.getStyleClass().add("gridbox");

        int index=0;

        if(!servicetype.isEmpty() && !servicecon.isEmpty()){

            this.index=servicetype.size();

            System.out.println("index is " + this.index);


            for(int i=0; i<servicetype.size(); i++)
            {
                String s_composite[]=servicetype.get(i).split("-");
                String s_labelname=s_composite[0];
                String s_id=s_composite[1];

                Label label=new Label(s_labelname);
                label.setId(s_id);
                System.out.println("label id is " + label.getId());
                label.getStyleClass().add("clabel");
                gridpane.add(label, 0, i);

                String c_composite[]=servicecon.get(i).split("-");
                String c_id=c_composite[0];
                String c_con=c_composite[1];
                if(c_con.equalsIgnoreCase("TextField")){
                    TextField tfield=new TextField();
                    tfield.setPrefColumnCount(35);
                    
                    tfield.setId(c_id);
                    gridpane.add(tfield, 1, i);
                    System.out.println("id is " + tfield.getId());
                }
                if(c_con.equalsIgnoreCase("TextArea")){
                    TextArea tarea=new TextArea();
                    tarea.setPrefColumnCount(35);
                    tarea.setId(c_id);
                    tarea.setWrapText(true);

                    gridpane.add(tarea, 1, i);
                }

            }

            vbox.getChildren().addAll(gridpane);

        }

        List<String> labelist=new ArrayList<>();
        List<String> tlist=new ArrayList<>();
        List<String> talist=new ArrayList<>();




        LocalDate ldate=LocalDate.now();
        int year=ldate.getYear();
        int month=ldate.getMonthValue();
        int day=ldate.getDayOfMonth();
        String cdate=year+"-"+month+"-"+day;
        System.out.println("customize date is  " + cdate);

        save_button.setOnAction(e->{
            String cid=id_field.getText();
            String amount=amountfld.getText();


            String value=null;
            try{
                String sql="Select id_sn from patient_registry where id_sn=?";
                DataBase dbase=new DataBase();
                Connection con=dbase.getConnection();
                PreparedStatement stmt=con.prepareStatement(sql);
                stmt.setString(1, cid);
                ResultSet rs=stmt.executeQuery();
                while(rs.next()){
                    value=rs.getString("id_sn");
                    System.out.println("rs.getString(idsn) " + rs.getString("id_sn"));
                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }

            if(value!=null){

                if(this.index>0)
                {
                    System.out.println("index > 0");
                    for(int i=0; i<this.index; i++){

                        Label label=(Label)vbox.lookup("#tx_f" + i);
                        Node node =(Node) vbox.lookup("#ch_b" + i);
                        if(label!=null){

                            labelist.add(label.getText());
                            System.out.println("label is " + label.getText());

                        }

                        if(node!=null && node instanceof TextField){
                            TextField tf=(TextField)node;

                            if(tf.getText().length()>0) {
                                tlist.add(tf.getText());
                            }

                            else{
                                ShowDialog.show("No field should be left empty");
                                return;
                            }
                        }

                        if(node!=null && node instanceof TextArea){
                            TextArea ta=(TextArea)node;
                            if(ta.getText().length()>0) {
                                tlist.add(ta.getText());
                            }
                            else{
                                ShowDialog.show("No field should be left empty");
                                return;
                            }
                        }


                    }
                    System.out.println("labelist size is " + labelist.size());

                    if(cid.length()>0 && amount.length()>0){


                        int d=0;
                        if(tlist.isEmpty() || labelist.isEmpty()){
                            ShowDialog.show("Fields show not be empty");
                            return;
                        }

                        if(!tlist.isEmpty() && !labelist.isEmpty()) {
                            for (int i = 0; i < labelist.size(); i++) {
                                try {
                                    String sql = "insert into service_data(c_id, service_name, service_type, service_value, date_v) value(?,?,?,?,?)";
                                    DataBase dbase = new DataBase();
                                    Connection con = dbase.getConnection();
                                    PreparedStatement statement = con.prepareStatement(sql);
                                    statement.setString(1, cid);
                                    statement.setString(2, service_name);
                                    statement.setString(3, labelist.get(i));
                                    System.out.println("labels list is " + labelist.get(i));
                                    statement.setString(4, tlist.get(i));
                                    statement.setString(5, cdate);
                                    d = statement.executeUpdate();

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                            }
                        }


                        if(d==1){


                            double cost=0;
                            try{
                                cost=Double.valueOf(amount);
                            }
                            catch(Exception ex){
                                ex.printStackTrace();
                            }
                            if(cost>0) {

                                try {
                                    String sql = "insert into service_rendered(id, service_type, cost, date_v) values(?,?,?,?)";
                                    DataBase dbase = new DataBase();
                                    Connection con = dbase.getConnection();
                                    PreparedStatement stmt = con.prepareStatement(sql);
                                    stmt.setString(1, cid);
                                    stmt.setString(2, service_name);
                                    stmt.setDouble(3, cost);
                                    stmt.setString(4, cdate);
                                    int r=stmt.executeUpdate();
                                    if(r>0){
                                        ShowDialog.show("Operation successful");
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }




                    }

                    }
                }

            }
            else{
                ShowDialog.show("Id is not a valid patient id");
            }

        });

    }


    public DataTemplateController(String service_name, String patientid) throws IOException {
        this.patientid=patientid;

        this.service_name=service_name;
        FXMLLoader fxmlloader = new FXMLLoader(DrugCategory.class.getResource("data_template.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
}
