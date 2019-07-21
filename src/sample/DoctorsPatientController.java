package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Ese on 1/24/2016.
 */
public class DoctorsPatientController extends VBox {
    @FXML
    private TableView<?> nontableview;

    @FXML
    private DatePicker tdatepicker;

    @FXML
    private DatePicker fdatepicker;

    @FXML
    private TableView<?> antitableview;

    @FXML
    private TextField antitotalfld;

    @FXML
    private Button searchbutton;

    @FXML
    private Button nondiagbutton;

    @FXML
    private TextField nontotalfld;

    @FXML
    private Button antediagbutton;

    @FXML
    void initialize()
    {

        fdatepicker.setValue(LocalDate.now());
        fdatepicker.setConverter(DateConverter.convert());

        tdatepicker.setValue(LocalDate.now());
        tdatepicker.setConverter(DateConverter.convert());

        tdatepicker.setEditable(false);
        fdatepicker.setEditable(false);


        ObservableList antinallist= FXCollections.observableArrayList();
        ObservableList nonantinallist=FXCollections.observableArrayList();
        searchbutton.setOnAction(e->{
            antitableview.getItems().clear();
            antitableview.getColumns().clear();

            nontableview.getItems().clear();
            nontableview.getColumns().clear();

            try{
                Date fmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(fdatepicker.getValue().toString());
                Date tmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(tdatepicker.getValue().toString());

                java.sql.Date fmySqlDate = new java.sql.Date(fmyDate.getTime());
                java.sql.Date tmySqlDate = new java.sql.Date(tmyDate.getTime());
                String sql="SELECT * FROM patient_registry INNER JOIN doc_and_patient ON patient_registry.id_sn=doc_and_patient.patient_id WHERE " +
                        "doc_and_patient.doc_name=? AND date between ? AND ? AND patype='Patient'";
                DataBase dbase=new DataBase();
                Connection con=dbase.getConnection();
                PreparedStatement statement=con.prepareStatement(sql);
                statement.setString(1, Controller.sign_by);
                statement.setDate(2, fmySqlDate);
                statement.setDate(3, tmySqlDate);
                int i=0;
                ResultSet rs=statement.executeQuery();
                while(rs.next())
                {
                    DoctorsPatientBeans db=new DoctorsPatientBeans(rs.getString("firstname"),  rs.getString("othername"),
                            rs.getString("dateofbirth"), rs.getString("sex"), rs.getString("patype"), rs.getString("date"),rs.getString("id_sn"));

                    nonantinallist.addAll(db);
                    i++;
                }
                nontableview.setItems(nonantinallist);
                nontotalfld.setText(String.valueOf(i));

                TableColumn tfirstname=new TableColumn("Surnname");
                tfirstname.setCellValueFactory(new PropertyValueFactory<DoctorsPatientBeans, String>("surname"));

                TableColumn tothername=new TableColumn("Other Name");
                tothername.setCellValueFactory(new PropertyValueFactory<DoctorsPatientBeans, String>("lastname"));

                TableColumn tdateofbirth=new TableColumn("Date of Birth");
                tdateofbirth.setCellValueFactory(new PropertyValueFactory<DoctorsPatientBeans, String>("dob"));

                TableColumn tsex=new TableColumn("Sex");
                tsex.setCellValueFactory(new PropertyValueFactory<DoctorsPatientBeans, String>("sex"));

                TableColumn tpatype=new TableColumn("Patient Type");
                tpatype.setCellValueFactory(new PropertyValueFactory<DoctorsPatientBeans, String>("patient_catregory"));

                TableColumn  tdate=new TableColumn("Date");
                tdate.setCellValueFactory(new PropertyValueFactory<DoctorsPatientBeans, String>("date"));

                TableColumn tid=new TableColumn("id");
                tid.setCellValueFactory(new PropertyValueFactory<DoctorsPatientBeans, String>("id"));

                nontableview.getColumns().addAll(tfirstname, tothername, tdateofbirth, tsex, tpatype, tdate, tid);

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

            try{
                Date fmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(fdatepicker.getValue().toString());
                Date tmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(tdatepicker.getValue().toString());

                java.sql.Date fmySqlDate = new java.sql.Date(fmyDate.getTime());
                java.sql.Date tmySqlDate = new java.sql.Date(tmyDate.getTime());
                String sql="select * FROM patient_registry INNER JOIN doc_and_patient ON patient_registry.id_sn=doc_and_patient.patient_id WHERE " +
                "doc_and_patient.doc_name=? AND date between ? AND ? AND patype='Antenatal'";
                DataBase dbase=new DataBase();
                Connection con=dbase.getConnection();
                PreparedStatement statement=con.prepareStatement(sql);
                statement.setString(1, Controller.sign_by);
                statement.setDate(2, fmySqlDate);
                statement.setDate(3, tmySqlDate);
                int n=0;
                ResultSet rs=statement.executeQuery();
                while(rs.next())
                {
                    DoctorsPatientBeans db=new DoctorsPatientBeans(rs.getString("firstname"),  rs.getString("othername"),
                            rs.getString("dateofbirth"), rs.getString("sex"), rs.getString("patype"), rs.getString("date"),rs.getString("id_sn"));
                    antinallist.addAll(db);
                    n++;
                }

                antitotalfld.setText(String.valueOf(n));

                antitableview.setItems(antinallist);
                TableColumn tfirstname=new TableColumn("Surnname");
                tfirstname.setCellValueFactory(new PropertyValueFactory<DoctorsPatientBeans, String>("surname"));

                TableColumn tothername=new TableColumn("Other Name");
                tothername.setCellValueFactory(new PropertyValueFactory<DoctorsPatientBeans, String>("lastname"));

                TableColumn tdateofbirth=new TableColumn("Date of Birth");
                tdateofbirth.setCellValueFactory(new PropertyValueFactory<DoctorsPatientBeans, String>("dob"));

                TableColumn tsex=new TableColumn("Sex");
                tsex.setCellValueFactory(new PropertyValueFactory<DoctorsPatientBeans, String>("sex"));

                TableColumn tpatype=new TableColumn("Patient Type");
                tpatype.setCellValueFactory(new PropertyValueFactory<DoctorsPatientBeans, String>("patient_catregory"));

                TableColumn  tdate=new TableColumn("Date");
                tdate.setCellValueFactory(new PropertyValueFactory<DoctorsPatientBeans, String>("date"));

                TableColumn tid=new TableColumn("id");
                tid.setCellValueFactory(new PropertyValueFactory<DoctorsPatientBeans, String>("id"));

                antitableview.getColumns().addAll(tfirstname, tothername, tdateofbirth, tsex, tpatype, tdate, tid);

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        });


        nondiagbutton.setOnAction(e->{

            try {


                DoctorsPatientBeans dbn = (DoctorsPatientBeans) nontableview.getFocusModel().getFocusedItem();
                if (dbn != null) {
                    String id = dbn.getId();
                    System.out.println("id is " + id);
                    String surname = dbn.getSurname();
                    String othername = dbn.getLastname();


                    ExamDiagController exm = new ExamDiagController(dbn.getId());
                    exm.setId2(id);
                    exm.setSurname(surname);
                    exm.setOthername(othername);

                    Stage stage = new Stage();
                    Scene scene = new Scene(exm);
                    stage.setScene(scene);

                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }
                }catch(Exception ex){
                    ex.printStackTrace();
                }


        });

        antediagbutton.setOnAction(e->{

            try {

                DoctorsPatientBeans dbn=(DoctorsPatientBeans)antitableview.getFocusModel().getFocusedItem();
                if(dbn!=null) {
                    String id = dbn.getId();
                    String surname = dbn.getSurname();
                    String othername = dbn.getLastname();
                    ExamDiagController exm = new ExamDiagController(dbn.getId());
                    exm.setId2(id);
                    exm.setSurname(surname);
                    exm.setOthername(othername);

                    Stage stage = new Stage();
                    Scene scene = new Scene(exm);
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });




        /*nontableview.getSelectionModel().selectedItemProperty().addListener((obervable, oldvalue, newvalue)->{
            if(newvalue!=null) {

            }

        });
        */


       /* antitableview.getSelectionModel().selectedItemProperty().addListener((obervable, oldvalue, newvalue)->{
            if(newvalue!=null) {

            }

        });
        */
    }

    void sdisplayTable(ObservableList olist){

    }

    public DoctorsPatientController() throws IOException
    {
        FXMLLoader fxmlloader=new FXMLLoader(VitalSignsController.class.getResource("doctorpatients.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }

}
