package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Ese on 1/27/2016.
 */
public class PatientPresController extends VBox {
    @FXML
    private Button savebutton;

    @FXML
    private TextField othernamefld;

    @FXML
    private TextField doctorfld;

    @FXML
    private Button printbutton;

    @FXML
    private TextArea prescriptiontxa;

    @FXML
    private Button clearbutton;

    @FXML
    private Button reloadbutton;

    @FXML
    private TextField patientidfld;

    @FXML
    private TableView<?> tableview;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField surnamefld;


    String id;
    String surname;
    String othername;

    public void setId2(String id)
    {
        patientidfld.setText(id);

    }
    public void setSurname(String surname)
    {
        surnamefld.setText(surname);
    }
    public void setOthername(String surname)
    {
        othernamefld.setText(surname);
    }

    @FXML
    void initialize()
    {

        reloadbutton.setOnAction(e->
        {
            reload();
        });
        patientidfld.setEditable(false);
        surnamefld.setEditable(false);
        othernamefld.setEditable(false);
        ObservableList tablelist=FXCollections.observableArrayList();

        try
        {
            String sql="SELECT * FROM doc_pres";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                PatientPresBeans pb=new PatientPresBeans(rs.getString("patient_id"), rs.getString("doctor"),
                        rs.getString("date"), rs.getString("prescrip"));

                tablelist.addAll(pb);
            }
            tableview.setItems(tablelist);

            TableColumn tpatientid=new TableColumn("Patient id");
            tpatientid.setCellValueFactory(new PropertyValueFactory<PatientPresBeans, String>("patientid"));

            TableColumn tdoctor=new TableColumn("Doctor");
            tdoctor.setCellValueFactory(new PropertyValueFactory<PatientPresBeans, String>("doctor"));

            TableColumn tdate=new TableColumn("Date");
            tdate.setCellValueFactory(new PropertyValueFactory<PatientPresBeans, String>("date"));

            TableColumn tprescription=new TableColumn("Prescription");
            tprescription.setCellValueFactory(new PropertyValueFactory<PatientPresBeans, String>("prescription"));

            tableview.getColumns().addAll(tpatientid, tdoctor, tdate, tprescription);


        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        final String pattern = "yyyy-MM-dd";
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern(pattern);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };


        datepicker.setConverter(converter);
        datepicker.setValue(LocalDate.now());


        doctorfld.setText(Controller.sign_by);



         savebutton.setOnAction(e-> {

                    save();
            });
    }

    public PatientPresController() throws IOException {
        FXMLLoader fxmlloader=new FXMLLoader(PatientPresController.class.getResource("patientprescription.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
    private void save()
    {
        if(prescriptiontxa.getText().length()!=0) {
            String patientid = patientidfld.getText();
            String doctor = doctorfld.getText();
            String prescription = prescriptiontxa.getText();
            String surname = surnamefld.getText();
            String othernames = othernamefld.getText();
            String date = datepicker.getValue().toString();
            try {
                String sql = "INSERT INTO will_parry.doc_pres (patient_id, prescrip, date, doctor, surname, othernames) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                DataBase dbase = new DataBase();
                Connection con = dbase.getConnection();
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, patientid);
                statement.setString(2, prescription);
                statement.setString(3, date);
                statement.setString(4, doctor);
                statement.setString(5, surname);
                statement.setString(6, othernames);
                int result = statement.executeUpdate();
                if (result == 1) {
                    System.out.println("Successful : _");
                }
                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else{
            System.out.println("Prescription field is empty");
        }

    }
    private void reload()
    {
        tableview.getItems().clear();
        tableview.getColumns().clear();
        ObservableList tablelist=FXCollections.observableArrayList();

        try
        {
            String sql="SELECT * FROM doc_pres";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                PatientPresBeans pb=new PatientPresBeans(rs.getString("patient_id"), rs.getString("doctor"),
                        rs.getString("date"), rs.getString("prescrip"));

                tablelist.addAll(pb);
            }
            tableview.setItems(tablelist);

            TableColumn tpatientid=new TableColumn("Patient id");
            tpatientid.setCellValueFactory(new PropertyValueFactory<PatientPresBeans, String>("patientid"));

            TableColumn tdoctor=new TableColumn("Doctor");
            tdoctor.setCellValueFactory(new PropertyValueFactory<PatientPresBeans, String>("doctor"));

            TableColumn tdate=new TableColumn("Date");
            tdate.setCellValueFactory(new PropertyValueFactory<PatientPresBeans, String>("date"));

            TableColumn tprescription=new TableColumn("Prescription");
            tprescription.setCellValueFactory(new PropertyValueFactory<PatientPresBeans, String>("prescription"));

            tableview.getColumns().addAll(tpatientid, tdoctor, tdate, tprescription);


        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
