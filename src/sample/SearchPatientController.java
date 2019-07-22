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
import java.util.Date;

/**
 * Created by Ese on 7/4/2016.
 */
public class SearchPatientController extends VBox {
    @FXML
    private HBox tableviewdisplay;

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


    ObservableList patientlist= FXCollections.observableArrayList();


    public SearchPatientController() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(SearchPatientController.class.getResource("search_patient.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }

    @FXML
    public void initialize() {

        fromdatepicker.setValue(LocalDate.now());
        fromdatepicker.setConverter(DateConverter.convert());
        fromdatepicker.setEditable(false);

        todatepicker.setValue(LocalDate.now());
        todatepicker.setConverter(DateConverter.convert());
        todatepicker.setEditable(false);

        patientTableview();

        datesearchbutton.setOnAction(e -> {

                    String sqldate = "SELECT * FROM patient_registry  WHERE  dateofreg between ? AND ?";
                    ObservableList olist = FXCollections.observableArrayList();
                    try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement statement = con.prepareStatement(sqldate)) {

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
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                    if (!olist.isEmpty()) {
                        sdisplayTable(olist);
                    }
                });


            searchfld.textProperty().addListener((or, olvalue, nvalue) ->
            {
                if (nvalue.length() <= 0) {
                    patientTableview();
                }
                if (nvalue != null && nvalue.length() > 0) {
                    ObservableList patlist = FXCollections.observableArrayList();
                    String sql = "select * from patient_registry where firstname like '" + nvalue + "%' or othername like '" + nvalue + "%' ";
                    try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

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
                            patlist.add(cm);

                        }


                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (!patlist.isEmpty()) {
                        sdisplayTable(patlist);
                    } else {
                        patientTableview();
                    }
                }


            });



    }



    void patientTableview()
    {
        tableview.getItems().clear();
        tableview.getColumns().clear();

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection())
        {
            String sql="select * from patient_registry";
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
                patientlist.add(cm);
            }

            statement.close();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }


        sdisplayTable(patientlist);


    }

    void sdisplayTable(ObservableList patlist)
    {
        tableview.getColumns().clear();
        tableview.getItems().clear();


        tableview.setItems(patlist);
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

/*
        vsign.setOnAction(e -> {
            try {
                ViewAllPatientBean cv = (ViewAllPatientBean) tb.getFocusModel().getFocusedItem();
                VitalSignsController vc = new VitalSignsController();
                vc.setSurname(cv.getFirstname());
                vc.setOthername(cv.getOthername());
                vc.setPatient_id(cv.getUserId());
                Scene scene = new Scene(vc);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.show();

                System.out.println(cv.getFirstname());
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        });
        */

