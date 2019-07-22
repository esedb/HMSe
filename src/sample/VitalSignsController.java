package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Ese on 1/23/2016.
 */
public class VitalSignsController extends VBox {
    @FXML
    private TextField weightfld;

    @FXML
    private TextField spo2fld;

    @FXML
    private Button savebutton;

    @FXML
    private TextField othernamefld;

    @FXML
    private TextArea complainsta;

    @FXML
    private TextField respirationfld;

    @FXML
    private TextField bpfld;

    @FXML
    private TextField pulsefld;

    @FXML
    private TextField tempfld;

    @FXML
    private TextField sign_byfld;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField surnamefld;

    @FXML
    private TextField bp_licfld;

    @FXML
    private ChoiceBox<?> admitchoice;

    @FXML
    private TextField heightfld;

    @FXML
    private Button printbutton;

    @FXML
    private Button clearbutton;

    @FXML
    private ChoiceBox<?> b_groupchoice;

    @FXML
    private TextField patientidfld;

    @FXML
    private ChoiceBox<?> doctorchoice;

    @FXML
    private TextField timefld;

    String surname;
    String othername;
    String patient_id;
    void setPatient_id(String patient_id)
    {
         patientidfld.setText(patient_id);
    }

    void setSurname(String surname)
    {
        this.surname=surname;

        surnamefld.setText(surname);
    }

    void setOthername(String othername)
    {

    }




    @FXML
    private void initialize()
    {
        String othername=null;

        String dsql ="select othername from patient_registry where id_sn = ?";
        try(DataBase dbase=new DataBase(); Connection con =dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(dsql)){

            stmt.setString(1, patient_id);

            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                othername=rs.getString("othername");
                System.out.println("Other name " + rs.getString("othername"));
            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        if(othername!=null){

            othernamefld.setText(othername);
        }
        othernamefld.setEditable(false);


        savebutton.setDisable(true);
        timefld.setText(String.valueOf(LocalTime.now().getHour())+":"+String.valueOf(LocalTime.now().getMinute()) + ":" + String.valueOf(LocalTime.now().getSecond()));

        clearbutton.setOnAction(e->
        {
            spo2fld.setText("");
            tempfld.setText("");
            bpfld.setText("");
            respirationfld.setText("");
            heightfld.setText("");
            weightfld.setText("");
            pulsefld.setText("");

        });
        ObservableList admitlist=FXCollections.observableArrayList();
        admitlist.addAll("Yes", "No");
        admitchoice.setItems(admitlist);
        admitchoice.getSelectionModel().selectFirst();
        doctorchoice.getSelectionModel().selectedItemProperty().addListener((obervable, oldvalue, newvalue)->
        {
            if(newvalue!=null)
            {
                savebutton.setDisable(false);
            }
            else{
                savebutton.setDisable(true);
            }

        });

        ObservableList bglist=FXCollections.observableArrayList("O", "AA", "AS", "SS");
        b_groupchoice.setItems(bglist);
        b_groupchoice.getSelectionModel().selectFirst();



        datepicker.setConverter(DateConverter.convert());
        datepicker.setValue(LocalDate.now());
        datepicker.setEditable(false);
        ObservableList doclist= FXCollections.observableArrayList();
          try{
            String sql="SELECT * from account_records where department='Doctor'";

            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                doclist.addAll(rs.getString("firstname") + " " + rs.getString("lastname"));

            }
            doctorchoice.setItems(doclist);


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        patientidfld.setText(patient_id);
        System.out.println("Dow down patient id is " + patient_id);
        sign_byfld.setText(Controller.sign_by);
        surnamefld.setEditable(false);

        patientidfld.setEditable(false);
        sign_byfld.setEditable(false);


        savebutton.setOnAction(e->
        {
            if(complainsta.getText().length()<=0 || doctorchoice.getValue()==null || doctorchoice.getValue().toString().length()<=0
                    || doctorchoice.getValue().toString().equalsIgnoreCase("None") ||
                 datepicker.getValue()==null || datepicker.getValue().toString().length()<=0 || patientidfld.getText().length()<=0)
            {
                return;
            }


                save();
        });


    }
    public VitalSignsController(String patient_id) throws IOException {
        this.patient_id=patient_id;
        FXMLLoader fxmlloader=new FXMLLoader(VitalSignsController.class.getResource("vitalsigns.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
    private void save()  {


        try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection();
             PreparedStatement stmt = con.prepareStatement("select patient_id from doc_and_patient where patient_id=?")) {
            stmt.setString(1, patientidfld.getText());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                PreparedStatement statement = con.prepareStatement("update doc_and_patient set doc_name=?, date=? where patient_id=?");
                statement.setString(1, doctorchoice.getValue().toString());
                statement.setString(2, datepicker.getValue().toString());
                statement.setString(3, patientidfld.getText());
                int m = statement.executeUpdate();
                if (m == 1) {
                    insertIntoVitalSigns();
                    statement.close();

                }


            }
            else{
                PreparedStatement statement=con.prepareStatement("insert into doc_and_patient(doc_name, date, patient_id) values(?,?,?)");
                statement.setString(1, doctorchoice.getValue().toString());
                statement.setString(2, datepicker.getValue().toString());
                statement.setString(3, patientidfld.getText());
                int m=statement.executeUpdate();
                if(m==1) {
                    System.out.println("false ran");
                    insertIntoVitalSigns();
                    statement.close();
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void insertIntoVitalSigns() {

        String surname = surnamefld.getText();
        String othernames = othernamefld.getText();
        String patientid = patientidfld.getText();
        String complains = complainsta.getText();
        String doctor = doctorchoice.getValue().toString();
        String respiration = respirationfld.getText();
        String bpstlic = bp_licfld.getText();
        String date = datepicker.getValue().toString();
        String time = timefld.getText();
        String pulse = pulsefld.getText();
        String bpressure = bpfld.getText();
        String bgroup = b_groupchoice.getValue().toString();
        String temperature = tempfld.getText();
        String height = heightfld.getText();
        String weight = weightfld.getText();
        String spo2 = spo2fld.getText();
        String signby = sign_byfld.getText();
        String admitted = admitchoice.getValue().toString();

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection()) {
            String sql = "INSERT INTO will_parry.vital_signs (patient_id, surname, othernames, complain, doctor, respiration, bp_lic, time, date, pulse, sign_by, bp, b_group, temp, height, weight, spo2, admit) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, patientid);
            statement.setString(2, surname);
            statement.setString(3, othernames);
            statement.setString(4, complains);
            statement.setString(5, doctor);
            statement.setString(6, respiration);
            statement.setString(7, bpstlic);
            statement.setString(8, time);
            statement.setString(9, date);
            statement.setString(10, pulse);
            statement.setString(11, signby);
            statement.setString(12, bpressure);
            statement.setString(13, bgroup);
            statement.setString(14, temperature);
            statement.setString(15, height);
            statement.setString(16, weight);
            statement.setString(17, spo2);
            statement.setString(18, admitted);
            int result = statement.executeUpdate();
            if (result == 1) {
                ShowDialog.show("Opeartion successful");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

