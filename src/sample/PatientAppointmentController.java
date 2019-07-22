package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Ese on 2/1/2016.
 */
public class PatientAppointmentController extends VBox {
    @FXML
    private TextField phonefld;

    @FXML
    private Button viewappbutton;

    @FXML
    private TextField addressfld;

    @FXML
    private TextField patientid;

    @FXML
    private TextField signedby;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextArea appointmentfld;

    @FXML
    private TextArea appointmentdetails;

    @FXML
    private TextField othername;

    @FXML
    private ChoiceBox<?> doctorchb;

    @FXML
    private TextField surname;

    @FXML
    private TableView<?> tableview;

    @FXML
    private TextField timefld;

    @FXML
    private Button addappoint;


    String id;


    @FXML
    private void initialize()
    {
        ObservableList doclist = FXCollections.observableArrayList();
        doclist.add("None");
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection();
            PreparedStatement stmt=con.prepareStatement("SELECT * from account_records where department='Doctor'"); ResultSet rs=stmt.executeQuery()){
            while(rs.next()) {
                doclist.addAll(rs.getString("firstname") + " " + rs.getString("lastname"));
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        doctorchb.setItems(doclist);
        doctorchb.getSelectionModel().selectFirst();

        viewappbutton.setOnAction(e->{
            try {
                PatientList pl = new PatientList();
                Scene scene=new Scene(pl);
                Stage stage=new Stage();

                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

            }
            catch(Exception ex){
                ex.printStackTrace();
            }


        });
        String ssql="SELECT * FROM patient_registry where id_sn=?;";
        try(DataBase dbase=new DataBase();Connection con=dbase.getConnection(); PreparedStatement statement=con.prepareStatement(ssql);){
            final String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            statement.setString(1, id);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {

                datepicker.setValue(LocalDate.parse(rs.getString("dateofreg"), dateFormatter));
                addressfld.setText(rs.getString("address"));
                signedby.setText(Controller.sign_by);
                surname.setText(rs.getString("firstname"));
                othername.setText(rs.getString("lastname") + " " + rs.getString("othername"));
                phonefld.setText(rs.getString("phoneno"));
            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        patientid.setText(id);
        patientid.setEditable(false);

        datepicker.setConverter(DateConverter.convert());
        datepicker.setValue(LocalDate.now());
        datepicker.setEditable(false);

        String pattern2="HH:mm";
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(pattern2);
        timefld.setText(LocalTime.now().format(formatter));


        displayTable();


        addappoint.setOnAction(e-> {
            String sqln="select * from patient_schedule where patient_id=?";
            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sqln)){

                stmt.setString(1, id);
                ResultSet rs=stmt.executeQuery();
                if(rs.next()){
                    System.out.println("Already present in database_ ");
                    updateRecords();
                    return;
                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            if (appointmentfld.getText().length() > 0 && patientid.getText().length()>0 && doctorchb.getValue()!=null && !doctorchb.getValue().toString().equalsIgnoreCase("None"))
            {
                String appointment=appointmentfld.getText();
                String appointmentd=appointmentdetails.getText();
                String date=datepicker.getValue().toString();
                String time=timefld.getText();
                String sqll = "INSERT INTO will_parry.patient_schedule (patient_id, patient_name, date, time, purpose, details, scheduled_by, status, assign_to)  " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (DataBase dbase =new DataBase(); Connection con = dbase.getConnection(); PreparedStatement statement = con.prepareStatement(sqll)){
                        statement.setString(1, patientid.getText());
                        statement.setString(2, surname.getText() + " " + othername.getText());
                        statement.setString(3, datepicker.getValue().toString());
                        statement.setString(4, timefld.getText());
                        statement.setString(5, appointment);
                        statement.setString(6, appointmentd);
                        statement.setString(7, signedby.getText());
                        statement.setString(8, "not done");
                        statement.setString(9, doctorchb.getValue().toString());

                        int result = statement.executeUpdate();
                        if (result == 1)
                        {
                            System.out.println("Successful : _");
                        }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }

        });
    }

    void updateRecords(){

        String appointment=appointmentfld.getText();
        String appointmentd=appointmentdetails.getText();

        if(appointment.length()>0 && appointmentd.length()>0 && patientid.getText().length()>0) {

            String sqll = "UPDATE will_parry.patient_schedule set  date=?, time=?, purpose=?, details=?, scheduled_by=?, status=? where patient_id=? ";
            try (DataBase dbase=new DataBase(); Connection con = dbase.getConnection(); PreparedStatement statement = con.prepareStatement(sqll)) {

                statement.setString(1, datepicker.getValue().toString());
                statement.setString(2, timefld.getText());
                statement.setString(3, appointment);
                statement.setString(4, appointmentd);
                statement.setString(5, signedby.getText());
                statement.setString(6, "not done");
                statement.setString(7, patientid.getText());

                int result = statement.executeUpdate();
                if (result == 1) {
                    System.out.println("Update succesful Successful : _");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }

    void setPatientid(String id)
    {
     this.id=id;
    }

    void displayTable(){
        ObservableList pslist= FXCollections.observableArrayList();
        String sql="SELECT * FROM patient_schedule";

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                PatientAppointmentBeans ab=new PatientAppointmentBeans(rs.getString("patient_id"),rs.getString("patient_name"), rs.getString("date"),
                        rs.getString("time"), rs.getString("purpose"), rs.getString("scheduled_by"), rs.getString("status"), rs.getString("details"));
                pslist.addAll(ab);

            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        tableview.setItems(pslist);

        TableColumn tpatientid=new TableColumn("Patient id");
        tpatientid.setCellValueFactory(new PropertyValueFactory<PatientScheduleBeans, String>("id"));

        TableColumn tname=new TableColumn("Patient Name");
        tname.setCellValueFactory(new PropertyValueFactory<PatientScheduleBeans, String>("name"));
        TableColumn tdate=new TableColumn("Date");
        tdate.setCellValueFactory(new PropertyValueFactory<PatientScheduleBeans, String>("date"));

        TableColumn ttime=new TableColumn("Time");
        ttime.setCellValueFactory(new PropertyValueFactory<PatientScheduleBeans, String>("time"));

        TableColumn tpurpose=new TableColumn("Purpose");
        tpurpose.setCellValueFactory(new PropertyValueFactory<PatientScheduleBeans, String>("purpose"));

        TableColumn tscheduleby=new TableColumn("Schedule By");
        tscheduleby.setCellValueFactory(new PropertyValueFactory<PatientScheduleBeans, String>("schedule_by"));

        TableColumn tstatus=new TableColumn("Scedule Status");
        tstatus.setCellValueFactory(new PropertyValueFactory<PatientScheduleBeans, String>("status"));

        TableColumn tdetails=new TableColumn("Details");
        tdetails.setCellValueFactory(new PropertyValueFactory<PatientScheduleBeans, String>("details"));

        tableview.getColumns().addAll(tpatientid, tname, tdate, ttime, tpurpose, tdetails, tscheduleby, tstatus);
    }

    public PatientAppointmentController(String id) throws IOException{
        setPatientid(id);
        FXMLLoader fxmlloader=new FXMLLoader(PatientAppointmentController.class.getResource("patientappointments.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
}
