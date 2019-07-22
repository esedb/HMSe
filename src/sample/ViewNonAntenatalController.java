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

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by Ese on 2/16/2016.
 */
public class ViewNonAntenatalController extends VBox {

    @FXML
    private Button preattendancebutton;

    @FXML
    private Button scheduleapp;

    @FXML
    private HBox tableviewdisplay;

    @FXML
    private Button vsignbutton;

    @FXML
    private Button viewllappointmentsbutton;

    @FXML
    private Button attendedbutton;

    @FXML
    private DatePicker fromdatepicker;

    @FXML
    private Button datesearchbutton;

    @FXML
    private HBox hboxtableview;

    @FXML
    private TextField searchfld;

    @FXML
    private TableView<?> tableview;

    @FXML
    private DatePicker todatepicker;
    @FXML
    void initialize()
    {
        preattendancebutton.setOnAction(e->{
            ViewAllPatientBean cm = (ViewAllPatientBean) tableview.getFocusModel().getFocusedItem();
            if(cm!=null) {
                try {
                    AttendanceHistoryController ac = new AttendanceHistoryController(cm.getUserId());
                    Scene scene = new Scene(ac);
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }


        });

        attendedbutton.setOnAction(e-> {

            DatePicker dp=new DatePicker();
            dp.setValue(LocalDate.now());
            dp.setConverter(DateConverter.convert());


            ViewAllPatientBean cm = (ViewAllPatientBean) tableview.getFocusModel().getFocusedItem();
            if(cm!=null) {


                String insertsql = "insert into attendance_roster(id_sn, surname, othernames, date, time) values(?,?,?,?,?)";
                try (DataBase dbasee = new DataBase(); Connection conn = dbasee.getConnection(); PreparedStatement stmt = conn.prepareStatement(insertsql)) {
                    stmt.setString(1, cm.getUserId());
                    stmt.setString(2, cm.getFirstname());
                    stmt.setString(3, cm.getOthername());
                    stmt.setString(4, dp.getValue().toString());
                    stmt.setString(5, LocalTime.now().toString());

                    int d= stmt.executeUpdate();
                    if(d==1){
                        ShowDialog.show("Attendance Marked");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });


        displayTable();


        fromdatepicker.setConverter(DateConverter.convert());
        todatepicker.setConverter(DateConverter.convert());
        fromdatepicker.setValue(LocalDate.now());
        todatepicker.setValue(LocalDate.now());
        fromdatepicker.setEditable(false);
        todatepicker.setEditable(false);


        searchfld.textProperty().addListener((observable, oldvalue, newvalue)->{

            ObservableList tvolist2=FXCollections.observableArrayList();
            if (newvalue != null && newvalue.length() > 0) {
                String ssql = "select * from patient_registry where patype='Patient'  AND firstname like '" + newvalue + "%' or othername like'" + newvalue + "&'";

                try (DataBase dbasee = new DataBase(); Connection conn = dbasee.getConnection(); PreparedStatement stmt = conn.prepareStatement(ssql);) {

                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {

                        ViewAllPatientBean cm = new ViewAllPatientBean();
                        cm.firstname.set(rs.getString("firstname"));
                        cm.lastname.set(rs.getString("lastname"));
                        cm.userId.set(rs.getString("id_sn"));
                        cm.patientcategory.set(rs.getString("pacategory"));
                        cm.patienttype.set(rs.getString("patype"));
                        cm.sex.set(rs.getString("sex"));
                        cm.othername.set(rs.getString("othername"));

                        tvolist2.add(cm);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if(!tvolist2.isEmpty()){
                    sdisplayTable(tvolist2);
                }

            }
            else{
                displayTable();
            }



        });

        datesearchbutton.setOnAction(e->{

            String sqldate = "SELECT * FROM patient_registry  WHERE  dateofreg between ? AND ?  AND patype='Patient'";

            ObservableList olist=FXCollections.observableArrayList();

            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement statement=con.prepareStatement(sqldate)) {

                Date fmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromdatepicker.getValue().toString());
                Date tmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(todatepicker.getValue().toString());

                java.sql.Date fmySqlDate = new java.sql.Date(fmyDate.getTime());
                java.sql.Date tmySqlDate = new java.sql.Date(tmyDate.getTime());

                statement.setDate(1, fmySqlDate);
                statement.setDate(2, tmySqlDate);
                int i = 0;
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {

                    ViewAllPatientBean cm = new ViewAllPatientBean();
                    cm.firstname.set(rs.getString("firstname"));
                    cm.lastname.set(rs.getString("lastname"));
                    cm.userId.set(rs.getString("id_sn"));
                    cm.patientcategory.set(rs.getString("pacategory"));
                    cm.patienttype.set(rs.getString("patype"));
                    cm.sex.set(rs.getString("sex"));
                    cm.othername.set(rs.getString("othername"));

                    olist.add(cm);
                }
                rs.close();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

            if(!olist.isEmpty()){
                sdisplayTable(olist);
            }


    });





        vsignbutton.setOnAction(e->{
            try {
                ViewAllPatientBean cv = (ViewAllPatientBean) tableview.getFocusModel().getFocusedItem();
                VitalSignsController vc = new VitalSignsController(cv.getUserId());
                vc.setSurname(cv.getFirstname());
                vc.setOthername(cv.getOthername());
                vc.setPatient_id(cv.getUserId());
                Scene scene = new Scene(vc);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.show();

                System.out.println(cv.getFirstname());
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }


        });

        scheduleapp.setOnAction(e->{
            try {
                ViewAllPatientBean cv = (ViewAllPatientBean) tableview.getFocusModel().getFocusedItem();
                PatientAppointmentController pl = new PatientAppointmentController(cv.getUserId());
                Stage stage = new Stage();
                Scene scene = new Scene(pl);

                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        });

        viewllappointmentsbutton.setOnAction(e->{

            try {
                PatientList pl = new PatientList();
                Stage stage=new Stage();
                Scene scene=new Scene(pl);

                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        });
    }

    void displayTable(){


        tableview.getColumns().clear();
        tableview.getItems().clear();

        ObservableList antelist= FXCollections.observableArrayList();

        try(DataBase dbase=new DataBase();
            Connection con=dbase.getConnection())
        {
            String sql="SELECT * FROM patient_registry where patype='Patient'";
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                ViewAllPatientBean cm = new ViewAllPatientBean();
                cm.firstname.set(rs.getString("firstname"));
                cm.lastname.set(rs.getString("lastname"));
                cm.userId.set(rs.getString("id_sn"));
                cm.patientcategory.set(rs.getString("pacategory"));
                cm.patienttype.set(rs.getString("patype"));
                cm.sex.set(rs.getString("sex"));
                cm.othername.set(rs.getString("othername"));
                antelist.add(cm);
            }

            statement.close();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        if(!antelist.isEmpty()){

            sdisplayTable(antelist);

        }




    }


    void sdisplayTable(ObservableList olist){

        tableview.getColumns().clear();
        tableview.getItems().clear();

        tableview.setItems(olist);

        TableColumn tfirstname = new TableColumn("First Name");

        tfirstname.setCellValueFactory(new PropertyValueFactory<ViewAllPatientBean, String>("firstname"));


        TableColumn tlastname = new TableColumn("Last Name");
        tlastname.setCellValueFactory(new PropertyValueFactory<ViewAllPatientBean, String>("lastname"));


        TableColumn tothername = new TableColumn("Other Name");
        tothername.setCellValueFactory(new PropertyValueFactory<ViewAllPatientBean, String>("othername"));


        TableColumn tpatienttype = new TableColumn("Patient type");
        tpatienttype.setCellValueFactory(new PropertyValueFactory<ViewAllPatientBean, String>("patienttype"));


        TableColumn tpatientcategory = new TableColumn("Patient Category");
        tpatientcategory.setCellValueFactory(new PropertyValueFactory<ViewAllPatientBean, String>("patientcategory"));


        TableColumn tuserid = new TableColumn("User Id");
        tuserid.setCellValueFactory(new PropertyValueFactory<ViewAllPatientBean, String>("userId"));


        TableColumn tsex = new TableColumn("Sex");
        tsex.setCellValueFactory(new PropertyValueFactory<ViewAllPatientBean, String>("sex"));


        tableview.getColumns().addAll(tfirstname, tlastname, tothername, tuserid, tpatientcategory, tpatienttype, tsex);
    }

    public ViewNonAntenatalController () throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(ViewNonAntenatalController.class.getResource("viewnonantenatal.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
}
