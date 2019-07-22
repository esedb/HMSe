package sample;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.CreateAccount;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Ese on 12/9/2015.
 */
public class AccountFormController extends VBox {


    @FXML
    private Label passwordlbl1;

    @FXML
    private Label lastnamelbl11;

    @FXML
    private TextField phonefld;

    @FXML
    private TextArea addressfld;

    @FXML
    private Label lastnamelbl1;

    @FXML
    private Label privelegelbl1;

    @FXML
    private TextField searchfld;

    @FXML
    private ChoiceBox<?> titlefld;

    @FXML
    private Button createaccountbutton;

    @FXML
    private Label lastnamelbl;

    @FXML
    private DatePicker dobdatepick;

    @FXML
    private Label firstnamelbl11;

    @FXML
    private Label privelegelbl;

    @FXML
    private Label passwordlbl;

    @FXML
    private CheckBox single_check;

    @FXML
    private Button clearbutton;

    @FXML
    private Label firstnamelbl1;

    @FXML
    private Label statuslbl;

    @FXML
    private ChoiceBox<?> departmentchoice;

    @FXML
    private TextField nextofkintelfld;

    @FXML
    private TextField qualificationfld;

    @FXML
    private TextField relofkinfld;

    @FXML
    private TextField lastnamefld;

    @FXML
    private CheckBox married_checked;

    @FXML
    private Label departmentlbl1;

    @FXML
    private Label firstnamelbl1111;

    @FXML
    private Label firstnamelbl;

    @FXML
    private Label departmentlbl;

    @FXML
    private Label firstnamelbl111;

    @FXML
    private TextField special_fld;

    @FXML
    private TextField stateoforiginfld;

    @FXML
    private ChoiceBox<?> sexchoicebox;

    @FXML
    private ChoiceBox<?> religionchoice;

    @FXML
    private HBox tableviewer;

    @FXML
    private TextField firstnamefld;

    @FXML
    private TextField nextofkinfld;

    @FXML
    private DatePicker doedatepicker;

    @FXML
    private Label privelegelbl12;

    @FXML
    private VBox accountformvbox;

    @FXML
    private Label privelegelbl11;

    @FXML
    private TableView<?> tableview;

    @FXML
    void clearAction(ActionEvent event) {
        firstnamefld.setText("");
        lastnamefld.setText("");

        addressfld.setText("");
        nextofkinfld.setText("");
        nextofkintelfld.setText("");

        relofkinfld.setText("");


        stateoforiginfld.setText("");
        phonefld.setText("");
  }
    @FXML
    private HBox tableviewd;


    @FXML
    void addAccountAction(ActionEvent event) {
        String marital_status="";
        if(married_checked.isSelected()){
            marital_status="Married";
        }
        else if(single_check.isSelected()){
            marital_status="Single";
        }
        else{
            marital_status="Single";
        }


        String title=titlefld.getValue().toString();
        String firstname=firstnamefld.getText();
        String lastname=lastnamefld.getText();
        String sexchice=sexchoicebox.getValue().toString();
        String dateofbirth=dobdatepick.getValue().toString();
        String stateoforigin=stateoforiginfld.getText();
        String phoneno=phonefld.getText();
        String religion=religionchoice.getValue().toString();
        String department=departmentchoice.getValue().toString();
        String qualification=qualificationfld.getText();
        String specialty=special_fld.getText();
        String dateofemployment=doedatepicker.getValue().toString();
        String relaofkin=relofkinfld.getText();
        String address=addressfld.getText();
        String phonenofrel=nextofkintelfld.getText();

        if(firstname.length()<=0 || lastname.length()<=0 || phoneno.length()<=0 || department.equals("None")
                ||qualification.length()<=0 || stateoforigin.length()<=0 || qualification.length()<=0 || specialty.length()<=0
                || address.length()<=0 || relaofkin.length()<=0 || phonenofrel.length()<=0)
        {
            ShowDialog.show("Box marked in red must be filled");
            return;
        }



        AccountFormBeans afb=new AccountFormBeans(title, firstname, lastname, sexchice, dateofbirth,stateoforigin,
                phoneno, religion, department, qualification, specialty, dateofemployment, relaofkin, address, phonenofrel, marital_status);
        afb.execute();

    }
    public AccountFormController() throws IOException{
        FXMLLoader fxmlloader = new FXMLLoader(AccountFormController.class.getResource("AccountForm.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
    @FXML
    private void initialize() {

        displayTable();

        doedatepicker.setConverter(DateConverter.convert());
        doedatepicker.setValue(LocalDate.now());

        dobdatepick.setConverter(DateConverter.convert());
        dobdatepick.setValue(LocalDate.now());


        married_checked.setSelected(true);
        single_check.setSelected(false);

        married_checked.selectedProperty().addListener((observable, oldvalue, newvalue)->{
            if(newvalue){
                single_check.setSelected(false);
            }
            else{
                single_check.setSelected(true);
            }

        });
        single_check.selectedProperty().addListener((observable, oldvalue, newvalue)->{
            if(newvalue){
                married_checked.setSelected(false);
            }
            else{
                married_checked.setSelected(true);
            }

        });


        ObservableList titlelist=FXCollections.observableArrayList();
        titlelist.add("None");
        try(DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement("select * from department_records");
            ResultSet rs=statement.executeQuery()){
            while(rs.next())
            {
                titlelist.addAll(rs.getString("department"));
            }


        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        titlefld.setItems(titlelist);
        titlefld.getSelectionModel().selectFirst();





        searchfld.textProperty().addListener((or, olvalue, newvalue)->
        {
            ObservableList tvolist=FXCollections.observableArrayList();

            try(DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
                PreparedStatement prepstatement=con.prepareStatement("select * from account_records where firstname like '" + newvalue + "%' or lastname like '" + newvalue + "%'");
                 ResultSet rs=prepstatement.executeQuery()){
                while(rs.next())
                {
                    tvolist.add(new StaffEntryForm(rs.getString("firstname") , rs.getString("lastname") ,
                            rs.getString("department"), rs.getString("phone"), rs.getString("dateofbirth")));

                }

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            if(!tvolist.isEmpty()){
                sdisplayTable(tvolist);
            }
            else{
                displayTable();
            }

             });


        ObservableList olist = FXCollections.<String>observableArrayList();
        olist.add("None");

        String sql = "select * from department_records";

        try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection();
             PreparedStatement prepstatement = con.prepareStatement(sql);
            ResultSet rs = prepstatement.executeQuery()){
            while (rs.next()) {
                String department = rs.getString("department");
                olist.add(department);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        departmentchoice.setItems(olist);
        departmentchoice.getSelectionModel().selectFirst();



        ObservableList sexlist = FXCollections.observableArrayList("Male", "Female");
        sexchoicebox.setItems(sexlist);
        sexchoicebox.getSelectionModel().selectFirst();


        ObservableList religion = FXCollections.observableArrayList("Christian", "Muslim", "Traditional");
        religionchoice.setItems(religion);
        religionchoice.getSelectionModel().selectFirst();

    }

    void sdisplayTable(ObservableList olist){
        tableview.getItems().clear();
        tableview.getColumns().clear();

        tableview.setItems(olist);
        TableColumn tfirstname=new TableColumn("first name");
        tfirstname.setPrefWidth(180);
        tfirstname.setCellValueFactory(new PropertyValueFactory("firstname"));

        TableColumn tlastname=new TableColumn("last name");
        tlastname.setPrefWidth(180);
        tlastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        TableColumn tstreet=new TableColumn("Department");
        tstreet.setCellValueFactory(new PropertyValueFactory("department"));
        tstreet.setPrefWidth(180);
        TableColumn timages=new TableColumn("Phone");
        timages.setCellValueFactory(new PropertyValueFactory("phone"));
        timages.setPrefWidth(180);
        tableview.getColumns().addAll(tfirstname, tlastname, tstreet, timages);

    }

    void displayTable(){

        ObservableList tvolist=FXCollections.observableArrayList();

        try(DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement prepstatement=con.prepareStatement("select * from account_records");
            ResultSet rs=prepstatement.executeQuery()){
            while(rs.next())
            {
                tvolist.add(new StaffEntryForm(rs.getString("firstname") , rs.getString("lastname") ,
                        rs.getString("department"), rs.getString("phone"), rs.getString("dateofbirth")));

            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        sdisplayTable(tvolist);



    }

}
