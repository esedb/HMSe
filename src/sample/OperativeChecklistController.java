package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 2/16/2016.
 */
public class OperativeChecklistController extends VBox {
    @FXML
    private TextField patientsearchfld;

    @FXML
    private TableView<?> nontableview;

    @FXML
    private Button pabutton;

    @FXML
    private TableView<?> antitableview;

    @FXML
    private Button vpobutton;

    @FXML
    private Button anterefreshbutton;

    @FXML
    private Button parefreshbutton;

    @FXML
    private TextField antenatalsearchfld;

    @FXML
    private TextField antitotalfld;

    @FXML
    private TextField nontotalfld;

    @FXML
    private Button antbutton;

    ObservableList antelist= FXCollections.observableArrayList();
    ObservableList patientlist= FXCollections.observableArrayList();


    @FXML
    void initialize()
    {

        pabutton.setOnAction(e->{

        });

        antbutton.setOnAction(e->{



        });

        vpobutton.setOnAction(e->{



        });

        anteTableview();
        patientTableview();

        anterefreshbutton.setOnAction(e->{
            anteTableview();
        });
        parefreshbutton.setOnAction(e->{
            patientTableview();
        });

        nontableview.setOnMouseClicked(e->{
            ViewAllPatientBean cm = (ViewAllPatientBean)nontableview.getFocusModel().getFocusedItem();

        });


        ObservableList patlist=FXCollections.observableArrayList();
        patientsearchfld.textProperty().addListener((or, olvalue, nvalue) ->
        {
            nontableview.getItems().clear();
            nontableview.getColumns().clear();

            if (nvalue != null && nvalue.length() > 0) {
                nontableview.getItems().clear();


                DataBase dbase = new DataBase();
                Connection con;
                String sql = "select * from patient_registry where firstname like '" + nvalue + "%' and patype='Patient' ";

                try {
                    con = dbase.getConnection();
                    PreparedStatement prepstatement = con.prepareStatement(sql);
                    ResultSet rs = prepstatement.executeQuery();
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

                } catch (Exception e) {
                    e.printStackTrace();
                }

                nontableview.setItems(patlist);



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

                nontableview.getColumns().addAll(tfirstname, tlastname, tothername, tuserid, tpatientcategory, tpatienttype, tsex);


            }
            else{
                patientTableview();
            }

        });

        ObservableList antlist=FXCollections.observableArrayList();
        antenatalsearchfld.textProperty().addListener((observable, oldvalue, nvalue)->{
            antitableview.getItems().clear();
            antitableview.getColumns().clear();

            if (nvalue != null && nvalue.length() > 0) {
                antitableview.getItems().clear();

                DataBase dbase = new DataBase();
                Connection con;
                String sql = "select * from patient_registry where firstname like '" + nvalue + "%' and patype='Antenatal'";

                try {
                    con = dbase.getConnection();
                    PreparedStatement prepstatement = con.prepareStatement(sql);
                    ResultSet rs = prepstatement.executeQuery();
                    while (rs.next()) {
                        ViewAllPatientBean cm = new ViewAllPatientBean();
                        cm.firstname.set(rs.getString("firstname"));
                        cm.lastname.set(rs.getString("lastname"));
                        cm.userId.set(rs.getString("id_sn"));
                        cm.patientcategory.set(rs.getString("pacategory"));
                        cm.patienttype.set(rs.getString("patype"));
                        cm.sex.set(rs.getString("sex"));
                        cm.othername.set(rs.getString("othername"));
                        antlist.add(cm);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                antitableview.setItems(antlist);



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


                antitableview.getColumns().addAll(tfirstname, tlastname, tothername, tuserid, tpatientcategory, tpatienttype, tsex);



            }

            else{
                anteTableview();
            }

        });



    }


    public OperativeChecklistController() throws IOException {
        FXMLLoader fxmlloader=new FXMLLoader(TodaysPrescription.class.getResource("operativechecklist.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();


    }

    void anteTableview()
    {
        antitableview.getItems().clear();
        antitableview.getColumns().clear();

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection())
        {
            String sql="SELECT * FROM patient_registry where patype='Antenatal'";
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

        antitableview.setItems(antelist);



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

        antitableview.getColumns().addAll(tfirstname, tlastname, tothername, tuserid, tpatientcategory, tpatienttype, tsex);

    }

    void patientTableview()
    {
        nontableview.getItems().clear();
        nontableview.getColumns().clear();

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection())
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
                patientlist.add(cm);
            }

            statement.close();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        nontableview.setItems(patientlist);


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

        nontableview.getColumns().addAll(tfirstname, tlastname, tothername, tuserid, tpatientcategory, tpatienttype, tsex);


    }
}
