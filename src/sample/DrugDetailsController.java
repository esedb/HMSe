package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 * Created by Ese on 1/4/2016.
 */
public class DrugDetailsController extends VBox {

    @FXML
    private TextField supplierfld;

    @FXML
    private Button savebutton;

    @FXML
    private TextArea dosagearea;

    @FXML
    private TextField manufacturerfld;

    @FXML
    private TextField drugfld;

    @FXML
    private TextField unitpricefld;

    @FXML
    private Button searchbutton;

    @FXML
    private TextField rolimitfld;

    @FXML
    private TextField searchfld;

    @FXML
    private HBox tableviewer;

    @FXML
    private TextField nafdacfld;

    @FXML
    private TextField costpricefld;

    @FXML
    private DatePicker manudate;

    @FXML
    private Button clearbutton;

    @FXML
    private VBox accountformvbox;

    @FXML
    private ChoiceBox<?> drugcategorychoice;

    @FXML
    private TextField packetsizefld;

    @FXML
    private TableView<?> tableview;

    @FXML
    private ComboBox<?> formchoice;

    @FXML
    private HBox searchbox;

    @FXML
    private DatePicker expdate;

    @FXML
    private TextField quantityfld;


    @FXML
    void clearAction(ActionEvent event) {
        manufacturerfld.setText("");
        drugfld.setText("");

        rolimitfld.setText("");
        costpricefld.setText("");
        unitpricefld.setText("");
        packetsizefld.setText("");
        drugfld.setText("");
        supplierfld.setText("");



    }
    @FXML
    private void initialize()
    {
        expdate.setConverter(DateConverter.convert());
        expdate.setValue(LocalDate.now());

        manudate.setConverter(DateConverter.convert());
        manudate.setValue(LocalDate.now());


        ObservableList olist=FXCollections.<String>observableArrayList();
        olist.add("None");
        String sql="SELECT * FROM drug_category";
        Connection con;
        try{
            DataBase dbase = new DataBase();
            con = dbase.getConnection();
            PreparedStatement prepstatement = con.prepareStatement(sql);
            ResultSet rs=prepstatement.executeQuery();
            while(rs.next()) {
                olist.add(rs.getString("category"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        drugcategorychoice.setItems(olist);
        drugcategorychoice.getSelectionModel().selectFirst();

        ObservableList drugformlist=FXCollections.observableArrayList("Bottles", "Blister Pack");
        formchoice.setItems(drugformlist);
        formchoice.getSelectionModel().selectFirst();


        unitpricefld.textProperty().addListener((observable, oldvalue, newvalue)->{

            if(newvalue!=null && newvalue.length()>0) {

                if (!MyUtil.isInteger(newvalue)) {
                    unitpricefld.setText(oldvalue);
                }
                else {

                }
            }

        });

        costpricefld.textProperty().addListener((observable, oldvalue, newvalue)->{

            if(newvalue!=null && newvalue.length()>0) {

                if (!MyUtil.isInteger(newvalue)) {
                    costpricefld.setText(oldvalue);
                }
                else {

                }
            }

        });

        rolimitfld.textProperty().addListener((observable, oldvalue, newvalue)->{

            if(newvalue!=null && newvalue.length()>0) {

                if (!MyUtil.isInteger(newvalue)) {
                    rolimitfld.setText(oldvalue);
                }
                else {

                }
            }

        });



        packetsizefld.textProperty().addListener((observable, oldvalue, newvalue)->{

            if(newvalue!=null && newvalue.length()>0) {

                if (!MyUtil.isInteger(newvalue)) {
                    packetsizefld.setText(oldvalue);
                }
                else {

                }
            }

        });


        }

    @FXML
    void saveActionEvent(ActionEvent event) {

        if(drugfld.getText().length()<=0 || manufacturerfld.getText().length()<=0 || supplierfld.getText().length()<=0 ||
                unitpricefld.getText().length()<=0 || costpricefld.getText().length()<=0 ||
                packetsizefld.getText().length()<=0 || nafdacfld.getText().length()<=0 || dosagearea.getText().length()<=0)
        {
            ShowDialog.show("Boxes marked in red must be filled with values");
            return;
        }

        System.out.println("This part of the programme is not supposed to run");



        String drugname=drugfld.getText();
        if(drugname.length()>0 && drugname!=null && !drugname.equalsIgnoreCase("")) {
            String drugnamesql="select drug from drug_details where drug=?";
            String validate="";
            try(DataBase dbasen=new DataBase(); Connection conn =dbasen.getConnection(); PreparedStatement stmt=conn.prepareStatement(drugnamesql)){
                stmt.setString(1, drugname);
                ResultSet rs=stmt.executeQuery();
                while(rs.next()){

                    validate=rs.getString("drug");
                }
                if(validate.equalsIgnoreCase("")){
                    if (drugcategorychoice.getValue().toString().equalsIgnoreCase("None")) {
                        ShowDialog.show("Select a valid drug category");
                        return;
                    } else {


                        try(DataBase dbase = new DataBase(); Connection con=dbase.getConnection()) {
                            String sql = "INSERT INTO will_parry.drug_details (drug_categroy, dosage, drug, " +
                                    "form, packet_size, re_orderlimit, supplier, " +
                                    "unit_price, cost_price, manufacturer, mfg_date, exp_date, nafdac)" +
                                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                            PreparedStatement prepstatement = con.prepareStatement(sql);
                            prepstatement.setString(1, drugcategorychoice.getValue().toString());
                            prepstatement.setString(2, dosagearea.getText());
                            prepstatement.setString(3, drugfld.getText());
                            prepstatement.setString(4, formchoice.getValue().toString());
                            prepstatement.setLong(5, Long.parseLong(packetsizefld.getText()));
                            prepstatement.setLong(6, Long.parseLong(rolimitfld.getText()));
                            prepstatement.setString(7, supplierfld.getText());
                            prepstatement.setLong(8, Long.parseLong(unitpricefld.getText()));
                            prepstatement.setLong(9, Long.parseLong(costpricefld.getText()));
                            prepstatement.setString(10, manufacturerfld.getText());
                            prepstatement.setString(11, manudate.getValue().toString());
                            prepstatement.setString(12, expdate.getValue().toString());
                            prepstatement.setString(13, nafdacfld.getText());

                            int result = prepstatement.executeUpdate();

                            if (result == 1) {
                                ShowDialog.show("Operation Successfull");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                else {
                    ShowDialog.show("value already present in database");

                    return;

                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }


        }

    }
    @FXML
    void searchAction(ActionEvent event) {
        ObservableList olist= FXCollections.observableArrayList();
        String text=searchfld.getText();
        Connection con;
        DataBase dbase=new DataBase();
        String sql="select * from drug_details where drug like '" + text +"%' or drug_categroy like'" + text + "&'";

        try {
            con = dbase.getConnection();
            PreparedStatement prepstatement=con.prepareStatement(sql);
            ResultSet rs=prepstatement.executeQuery();
            while(rs.next())
            {
                olist.add(new DrugDetailsBeans(rs.getString("drug"), rs.getString("drug_categroy")));


            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        tableview.setItems(olist);

        TableColumn tdrugname=new TableColumn("Drug Name");
        tdrugname.setCellValueFactory(new PropertyValueFactory("drugname"));

        TableColumn tdrugcategory=new TableColumn("Drug Category");
        tdrugcategory.setCellValueFactory(new PropertyValueFactory("drugcategory"));

        tableview.getColumns().addAll(tdrugname, tdrugcategory);




    }

    public DrugDetailsController() throws IOException {
        FXMLLoader fxmlloader=new FXMLLoader(DrugDetailsController.class.getResource("drugdetails.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }


}
