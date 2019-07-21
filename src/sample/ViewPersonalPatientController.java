package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ese on 4/15/2017.
 */
public class ViewPersonalPatientController extends VBox {
    @FXML
    private HBox tableviewdisplay;

    @FXML
    private Button vlabbutton;

    @FXML
    private Button admitpatient;

    @FXML
    private DatePicker fromdatepicker;

    @FXML
    private Button searchbutton;

    @FXML
    private HBox hboxtableview;

    @FXML
    private TextField searchfld;

    @FXML
    private DatePicker todatepicker;

    @FXML
    private TableView<?> table;

    @FXML
    private Button view_checklistbutton;


    @FXML
    void initialize(){

        fromdatepicker.setValue(LocalDate.now());
        fromdatepicker.setConverter(DateConverter.convert());

        todatepicker.setValue(LocalDate.now());
        todatepicker.setConverter(DateConverter.convert());

        fromdatepicker.setEditable(false);
        todatepicker.setEditable(false);

        String sqldate = "select vital_signs.surname, vital_signs.othernames, vital_signs.patient_id, " +
                "vital_signs.date, patient_registry.payment_status from vital_signs INNER JOIN " +
                "patient_registry on vital_signs.patient_id=patient_registry.id_sn  WHERE date between ? AND ? AND doctor=?";

        searchbutton.setOnAction(e->{

            ObservableList olist= FXCollections.observableArrayList();

            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement statement=con.prepareStatement(sqldate)) {

                Date fmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromdatepicker.getValue().toString());
                Date tmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(todatepicker.getValue().toString());

                java.sql.Date fmySqlDate = new java.sql.Date(fmyDate.getTime());
                java.sql.Date tmySqlDate = new java.sql.Date(tmyDate.getTime());

                statement.setDate(1, fmySqlDate);
                statement.setDate(2, tmySqlDate);
                statement.setString(3, Controller.sign_by);
                int i = 0;
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    ViewAllPatientBean cm = new ViewAllPatientBean();
                    cm.firstname.set(rs.getString("vital_signs.surname"));
                    cm.lastname.set(rs.getString("vital_signs.othernames"));
                    cm.userId.set(rs.getString("vital_signs.patient_id"));
                    cm.dateofreg.set(rs.getString("vital_signs.date"));
                    cm.payment_status.set(rs.getString("patient_registry.payment_status"));
                    olist.add(cm);
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

            if(!olist.isEmpty()){
                sdisplayTable(olist);
            }
            else{
                displayTable();
            }


        });


        vlabbutton.setOnAction(e->{
            try {
                LaboratoryPatientList sc=new LaboratoryPatientList();
                Scene scene=new Scene(sc);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        });

        String sql1 = "select * from vital_signs where doctor=?";


        ObservableList tvolist = FXCollections.observableArrayList();
        String sql = "select vital_signs.surname, vital_signs.othernames, vital_signs.patient_id, " +
                "vital_signs.date, patient_registry.payment_status from vital_signs INNER JOIN " +
                "patient_registry on vital_signs.patient_id=patient_registry.id_sn where vital_signs.doctor=?";
        try (DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            stmt.setString(1, Controller.sign_by);
            ResultSet rs =stmt.executeQuery();
            while (rs.next()) {
                ViewAllPatientBean cm = new ViewAllPatientBean();
                cm.firstname.set(rs.getString("vital_signs.surname"));
                cm.lastname.set(rs.getString("vital_signs.othernames"));
                cm.userId.set(rs.getString("vital_signs.patient_id"));
                cm.dateofreg.set(rs.getString("vital_signs.date"));
                cm.payment_status.set(rs.getString("patient_registry.payment_status"));

                tvolist.add(cm);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

        if(!tvolist.isEmpty()){
            sdisplayTable(tvolist);
        }


        ObservableList lablist=FXCollections.observableArrayList();
        lablist.add("None");
        String choicesql="Select * from templates where lab_or_surgery='Laboratory Test'";
        Set<String> sset=new HashSet<>();
        try(DataBase dbase=new DataBase(); Connection conn=dbase.getConnection();
            PreparedStatement stmt=conn.prepareStatement(choicesql)){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                sset.add(rs.getString("service_name"));
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        if(!sset.isEmpty()){
            for(String values: sset){
                lablist.add(values);
            }

        }

       searchfld.textProperty().addListener((observable, oldvalue, newvalue)->{

            ObservableList tvolist2=FXCollections.observableArrayList();

            if (newvalue != null && newvalue.length() > 0) {
                String ssql = "select vital_signs.surname, vital_signs.othernames, vital_signs.patient_id, vital_signs.date, patient_registry.payment_status FROM vital_signs " +
                        "INNER JOIN patient_registry ON vital_signs.patient_id=patient_registry.id_sn where vital_signs.doctor=? AND vital_signs.surname like '" + newvalue + "%' or vital_signs.othernames like '" + newvalue + "%'";

                try (DataBase dbasee = new DataBase(); Connection conn = dbasee.getConnection(); PreparedStatement stmt = conn.prepareStatement(ssql);) {

                    stmt.setString(1, Controller.sign_by);
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {

                        ViewAllPatientBean cm = new ViewAllPatientBean();
                        cm.firstname.set(rs.getString("vital_signs.surname"));
                        cm.lastname.set(rs.getString("vital_signs.othernames"));
                        cm.userId.set(rs.getString("vital_signs.patient_id"));
                        cm.dateofreg.set(rs.getString("vital_signs.date"));
                        cm.payment_status.set(rs.getString("patient_registry.payment_status"));

                        tvolist2.add(cm);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
           if(!tvolist2.isEmpty()){
               
               sdisplayTable(tvolist2);
           }
           else{
               displayTable();
           }

        });

        view_checklistbutton.setOnAction(e->{

            ObservableList olist=FXCollections.observableArrayList();

            String opclsql="select * from opcl_data inner join patient_registry  on opcl_data.id_sn=patient_registry.id_sn";


            try (DataBase dbasee = new DataBase(); Connection conn = dbasee.getConnection(); PreparedStatement stmt = conn.prepareStatement(opclsql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    OpclListBean cm=new OpclListBean();
                    cm.surname.set(rs.getString("opcl_data.surname"));
                    cm.othername.set(rs.getString("opcl_data.othername"));
                    cm.patient_id.set(rs.getString("opcl_data.id_sn"));
                    cm.operationtype.set(rs.getString("opcl_data.surgery"));
                    cm.sex.set(rs.getString("patient_registry.sex"));
                    cm.age.set(String.valueOf(rs.getInt("opcl_data.age")));


                    olist.add(cm);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if(!olist.isEmpty()){
                try {
                    OperativeCheckListReport or = new OperativeCheckListReport(olist);
                    Scene scene=new Scene(or);
                    Stage stage=new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            }
            else {
                ShowDialog.show("No Preoperative checklist Data");
            }


        });

        admitpatient.setOnAction(e->{
            ViewAllPatientBean vb=(ViewAllPatientBean) table.getFocusModel().getFocusedItem();
            if(vb!=null) {

                try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection();
                     PreparedStatement stmt = con.prepareStatement("update vital_signs set Admit=? where patient_id=? and doctor=?")) {
                    stmt.setString(1, "Yes");
                    stmt.setString(2, vb.getUserId());
                    stmt.setString(3, Controller.sign_by);
                    int d=stmt.executeUpdate();
                    if(d==1){
                        ShowDialog.show("Patient Admitted");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


        });

    }

    void displayTable(){


        table.getColumns().clear();
        table.getItems().clear();

        ObservableList tvolist2=FXCollections.observableArrayList();


        String ssql = "select vital_signs.surname, vital_signs.othernames, vital_signs.patient_id, " +
                "vital_signs.date, patient_registry.payment_status from vital_signs INNER JOIN " +
                "patient_registry on vital_signs.patient_id=patient_registry.id_sn where vital_signs.doctor=?";

        try (DataBase dbasee = new DataBase(); Connection conn = dbasee.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(ssql);
            stmt.setString(1, Controller.sign_by);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                ViewAllPatientBean cm = new ViewAllPatientBean();
                cm.firstname.set(rs.getString("vital_signs.surname"));
                cm.lastname.set(rs.getString("vital_signs.othernames"));
                cm.userId.set(rs.getString("vital_signs.patient_id"));
                cm.dateofreg.set(rs.getString("vital_signs.date"));
                cm.payment_status.set(rs.getString("patient_registry.payment_status"));

                tvolist2.add(cm);


            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        sdisplayTable(tvolist2);



    }

    protected void sdisplayTable(ObservableList tvolist){

        table.getColumns().clear();
        table.getItems().clear();

        table.setItems(tvolist);

        TableColumn tfirstname = new TableColumn("Surname");
        tfirstname.setCellValueFactory(new PropertyValueFactory("firstname"));

        TableColumn tlastname = new TableColumn("Other Names");
        tlastname.setCellValueFactory(new PropertyValueFactory("lastname"));


        TableColumn patientserial = new TableColumn("Patient Serial No.");
        patientserial.setCellValueFactory(new PropertyValueFactory("userId"));

        TableColumn tdateofreg = new TableColumn("Date of Registration");
        tdateofreg.setCellValueFactory(new PropertyValueFactory("dateofreg"));

        TableColumn tpaystatus=new TableColumn("Payment Status");
        tpaystatus.setCellValueFactory(new PropertyValueFactory("payment_status"));


        table.getColumns().addAll(tfirstname, tlastname, patientserial, tdateofreg, tpaystatus);

    }

    public ViewPersonalPatientController() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(ViewPersonalPatientController.class.getResource("doc_personal_patient.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
}
