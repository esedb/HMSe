package sample;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;


import javax.sql.ConnectionEventListener;
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
 * Created by Ese on 12/28/2015.
 */
public class  ViewAllPatientController extends VBox {

    @FXML
    private ChoiceBox<?> labchb;

    @FXML
    private HBox tableviewdisplay;

    @FXML
    private Button vlabbutton;

    @FXML
    private Button vsign;

    @FXML
    private DatePicker fromdatepicker;

    @FXML
    private Button searchbutton;

    @FXML
    private Button labbutton;

    @FXML
    private HBox hboxtableview;

    @FXML
    private TextField searchfld;

    @FXML
    private DatePicker todatepicker;

    @FXML
    private TableView<?> table;


    public ViewAllPatientController() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(ViewAllPatientController.class.getResource("viewallpatient.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }

    @FXML
    public void initialize() {



        fromdatepicker.setValue(LocalDate.now());
        fromdatepicker.setConverter(DateConverter.convert());

        todatepicker.setValue(LocalDate.now());
        todatepicker.setConverter(DateConverter.convert());

        fromdatepicker.setEditable(false);
        todatepicker.setEditable(false);

        displayTable();

        searchbutton.setOnAction(e->{

            ObservableList olist=FXCollections.observableArrayList();

            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement statement=con.prepareStatement("SELECT * FROM patient_registry  WHERE " +
                    " dateofreg between ? AND ? ")) {

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
                    cm.lastname.set(rs.getString("othername"));
                    cm.userId.set(rs.getString("id_sn"));
                    cm.patientcategory.set(rs.getString("pacategory"));
                    cm.dateofreg.set(rs.getString("dateofreg"));
                    cm.patienttype.set(rs.getString("patype"));
                    cm.sex.set(rs.getString("sex"));
                    cm.phone.set(rs.getString("phoneno"));
                    olist.add(cm);
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

            if(!olist.isEmpty()){
                sdisplayTable(olist);
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

        labchb.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue)->
        {
            if(newvalue.toString().equalsIgnoreCase("None")|| newvalue==null)
            {
                labbutton.setDisable(true);
            }
            else{
                labbutton.setDisable(false);
            }

        });

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

            labchb.setItems(lablist);
            labchb.getSelectionModel().selectFirst();
        }



        labbutton.setOnAction(e->{

            ViewAllPatientBean vb=(ViewAllPatientBean)table.getFocusModel().getFocusedItem();

            String labtype=labchb.getValue().toString();
            if(labtype==null || labtype.equalsIgnoreCase("None")){
                System.out.println("Rong selection");
                return;

            }

            String labsql="INSERT INTO lab_patient (id_sn, surname, othername, lab_type) values(?,?,?,?)";
            try(DataBase dbasee=new DataBase(); Connection cone = dbasee.getConnection(); PreparedStatement stmt=cone.prepareStatement(labsql)){
                stmt.setString(1, vb.getUserId());
                stmt.setString(2, vb.getFirstname());
                stmt.setString(3, vb.getLastname());
                stmt.setString(4, labchb.getValue().toString());
                int d=stmt.executeUpdate();
                if(d==1){
                    LaboratoryPatientList lpl=new LaboratoryPatientList();
                    Scene scene=new Scene(lpl);
                    Stage stage=new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }




            }
            catch(Exception ex){
                ex.printStackTrace();
            }



        });


        vsign.setOnAction(e -> {
            try {
                ViewAllPatientBean cv = (ViewAllPatientBean) table.getFocusModel().getFocusedItem();
                System.out.println("other names are " + cv.getOthername());
                VitalSignsController vc = new VitalSignsController(cv.getUserId());
                vc.setSurname(cv.getFirstname());
                vc.setOthername(cv.getOthername());
                vc.setPatient_id(cv.getUserId());
                Scene scene=new Scene(vc);
                Stage stage=new Stage();
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


        searchfld.textProperty().addListener((observable, oldvalue, newvalue)->{

            ObservableList tvolist2=FXCollections.observableArrayList();

            if (newvalue != null && newvalue.length() > 0) {
                String ssql = "select * from patient_registry where firstname like '" + newvalue + "%' or othername like'" + newvalue + "&'";

                try (DataBase dbasee = new DataBase(); Connection conn = dbasee.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(" select * from patient_registry where firstname like '" + newvalue + "%' or othername like'" + newvalue + "&'");
                     ResultSet rs = stmt.executeQuery()) {


                    while (rs.next()) {

                        ViewAllPatientBean cm = new ViewAllPatientBean();
                        cm.firstname.set(rs.getString("firstname"));
                        cm.lastname.set(rs.getString("othername"));
                        cm.userId.set(rs.getString("id_sn"));
                        cm.patientcategory.set(rs.getString("pacategory"));
                        cm.dateofreg.set(rs.getString("dateofreg"));
                        cm.patienttype.set(rs.getString("patype"));
                        cm.sex.set(rs.getString("sex"));
                        cm.phone.set(rs.getString("phoneno"));

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



    }

    void displayTable(){

        ObservableList tvolist2=FXCollections.observableArrayList();
        table.getColumns().clear();
        table.getItems().clear();

            String ssql = "select * from patient_registry";

            try (DataBase dbasee = new DataBase(); Connection conn = dbasee.getConnection(); PreparedStatement stmt = conn.prepareStatement(ssql);) {

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {

                    ViewAllPatientBean cm = new ViewAllPatientBean();
                    cm.firstname.set(rs.getString("firstname"));
                    cm.lastname.set(rs.getString("othername"));
                    cm.userId.set(rs.getString("id_sn"));
                    cm.patientcategory.set(rs.getString("pacategory"));
                    cm.dateofreg.set(rs.getString("dateofreg"));
                    cm.patienttype.set(rs.getString("patype"));
                    cm.sex.set(rs.getString("sex"));
                    cm.phone.set(rs.getString("phoneno"));

                    tvolist2.add(cm);


                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        table.setItems(tvolist2);

        TableColumn tfirstname = new TableColumn("first name");
        tfirstname.setCellValueFactory(new PropertyValueFactory("firstname"));

        TableColumn tlastname = new TableColumn("last name");
        tlastname.setCellValueFactory(new PropertyValueFactory("lastname"));

        TableColumn tpatientcategory = new TableColumn("Patient Category");
        tpatientcategory.setCellValueFactory(new PropertyValueFactory("patienttype"));

        TableColumn patientserial = new TableColumn("Patient Serial No.");
        patientserial.setCellValueFactory(new PropertyValueFactory("userId"));

        TableColumn tdateofreg = new TableColumn("Date of Registration");
        tdateofreg.setCellValueFactory(new PropertyValueFactory("dateofreg"));

        TableColumn tphone = new TableColumn("Phone number");
        tphone.setCellValueFactory(new PropertyValueFactory("phone"));

        TableColumn tsex=new TableColumn("Sex");
        tsex.setCellValueFactory(new PropertyValueFactory("sex"));


        table.getColumns().addAll(tfirstname, tlastname, tpatientcategory, patientserial, tdateofreg, tphone, tsex);


    }

    protected void sdisplayTable(ObservableList tvolist){

        table.getColumns().clear();
        table.getItems().clear();

        table.setItems(tvolist);

        TableColumn tfirstname = new TableColumn("first name");
        tfirstname.setCellValueFactory(new PropertyValueFactory("firstname"));

        TableColumn tlastname = new TableColumn("last name");
        tlastname.setCellValueFactory(new PropertyValueFactory("lastname"));

        TableColumn tpatientcategory = new TableColumn("Patient Category");
        tpatientcategory.setCellValueFactory(new PropertyValueFactory("patienttype"));

        TableColumn patientserial = new TableColumn("Patient Serial No.");
        patientserial.setCellValueFactory(new PropertyValueFactory("userId"));

        TableColumn tdateofreg = new TableColumn("Date of Registration");
        tdateofreg.setCellValueFactory(new PropertyValueFactory("dateofreg"));

        TableColumn tphone = new TableColumn("Phone number");
        tphone.setCellValueFactory(new PropertyValueFactory("phone"));

        TableColumn tsex=new TableColumn("Sex");
        tsex.setCellValueFactory(new PropertyValueFactory("sex"));


        table.getColumns().addAll(tfirstname, tlastname, tpatientcategory, patientserial, tdateofreg, tphone, tsex);

    }
}
/*tb.setRowFactory(new Callback<TableView<ViewAllPatientBean>, TableRow<ViewAllPatientBean>>() {
            @Override
            public TableRow<ViewAllPatientBean> call(TableView<ViewAllPatientBean> param) {
                return new TableRow<ViewAllPatientBean>() {
                    @Override
                    protected void updateItem(ViewAllPatientBean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            if (item.getReceivables() > 0) {
                                this.setTextFill(Color.WHITE);
                                this.setStyle("-fx-background-color: red; ");

                            }
                            if (item.getReceivables() == 0 || item.getReceivables() < 0) {
                                this.setStyle("-fx-background-color: green");
                            }
                        }


                    }
                };
            }
        });*/


