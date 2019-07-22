package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ese on 3/1/2017.
 */
public class CreateTemplate2Controller extends VBox {

    @FXML
    private ScrollPane scroll_pane;

    @FXML
    private Button savebutton;

    @FXML
    private ChoiceBox<?> lschb;

    @FXML
    private VBox vbox;

    @FXML
    private Button addbutton;

    @FXML
    private TextField s_name;
    int i=0;
    int n=0;
    GridPane gridpane=new GridPane();

    @FXML
    public void initialize(){

        gridpane.getStyleClass().add("gridbox");

        ObservableList lablist=FXCollections.observableArrayList("Laboratory Test", "Surgery");
        lschb.setItems(lablist);
        lschb.getSelectionModel().selectFirst();

        lschb.selectionModelProperty().addListener((observable, oldvalue, newvalue)->{
            if(newvalue==null) {

                savebutton.setDisable(true);
            }

        });

        savebutton.setDisable(true);


        s_name.textProperty().addListener((observable, oldvalue, newvalue) ->
        {
            if (newvalue.length() > 0) {
                savebutton.setDisable(false);



            } else {
                savebutton.setDisable(true);

            }
        });



        gridpane.setHgap(10);
        gridpane.setVgap(10);


        vbox.getChildren().addAll(gridpane);
        double vsize=vbox.getMaxWidth();

        //scroll_pane.setContent(vbox);



            addbutton.setOnAction(e->{
                n=n+1;
            addFieldToScene2(vbox);

            });

        String service_name=s_name.getText();
        savebutton.setOnAction(e->{

            saveAction(service_name, gridpane);

        });



    }

    public void addFieldToScene2(VBox vbox){


        Label label=new Label("Service Type");
        label.getStyleClass().add("clabel");

        gridpane.add(label, 0,i);

        TextField stfield=new TextField();
        stfield.setPrefColumnCount(35);


        stfield.setId("tx_f" + i);
        gridpane.add(stfield, 1, i);


        Label valabel=new Label("Value Container");
        gridpane.add(valabel, 2, i);
        valabel.getStyleClass().add("clabel");

        ChoiceBox<?> choicebox=new ChoiceBox();
        choicebox.setId("ch_b" + i);

        ObservableList list= FXCollections.observableArrayList("TextField", "TextArea");
        choicebox.setItems(list);
        choicebox.getSelectionModel().selectFirst();
        System.out.println("choicebox value is " + choicebox.getValue().toString());


        gridpane.add(choicebox, 3, i);

        stfield.textProperty().addListener((observable, oldvalue, newvalue) ->
        {
            if (newvalue.length() > 0) {
                savebutton.setDisable(false);



            } else {
                savebutton.setDisable(true);

            }

        });



        i=i+1;
    }

    public void saveAction(String service_name, GridPane gridpane){
        try{
            String sql="select service_name from templates where service_name=?";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            String test=null;
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setString(1, s_name.getText());
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                test=rs.getString("service_name");
            }
            if(s_name.getText().equalsIgnoreCase(test) || test!=null){
                ShowDialog.show("Template name already used");
                return;
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        if(n>0) {


            for (int x = 0; x < n; x++) {
                TextField txfield = (TextField) gridpane.lookup("#tx_f" + x);
                if(txfield.getText().length()<=0)
                {
                    ShowDialog.show("Not Saved");
                    return;
                }

            }
        }

        int svalue=0;

        String servicename=s_name.getText();


        String sql="INSERT INTO will_parry.templates (service_name, service_type, service_container, lab_or_surgery) " +
                "VALUES (?, ?, ?, ?)";


        List<TextField> txlist=new ArrayList<>();
        Map<String, ChoiceBox<?>> chmap=new HashMap<>();
        List<String> cblist=new ArrayList<>();


        if(n>0)
        {

            for(int i=0; i<n; i++){
                TextField field=(TextField) gridpane.lookup("#tx_f" + i);
                txlist.add(field);

                ChoiceBox<?> box=(ChoiceBox<?>) gridpane.lookup("#ch_b" + i);
                if(box==null)
                {
                    ShowDialog.show("Box is null");

                    return;
                }
                String selected=box.getValue().toString();
                String id=box.getId();
                String complete=null;
                if(selected.equals("TextField"))
                {
                    selected="TextField";

                }
                else{
                    selected="TextArea";
                }

                complete=id + "-" + selected;
                cblist.add(complete);



            }

        }
        if(!txlist.isEmpty())
        {
            for(int d=0; d<txlist.size(); d++){
                try {
                    DataBase dbase=new DataBase();
                    Connection con=dbase.getConnection();
                    PreparedStatement statement=con.prepareStatement(sql);
                    statement.setString(1, servicename);
                    statement.setString(2, txlist.get(d).getText()+"-" + txlist.get(d).getId());
                    statement.setString(3, cblist.get(d));
                    statement.setString(4,lschb.getValue().toString());
                    System.out.println("lab or surgery " + lschb.getValue().toString());
                    svalue=statement.executeUpdate();
                    if(svalue>0)
                    {
                        svalue++;
                    }

                    else{
                        return;
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }




            }
            if(svalue>0)
            {

                ShowDialog.show("Operation Successful");
            }
        }

    }



    public CreateTemplate2Controller() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(DrugCategory.class.getResource("create_template2.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }


}
