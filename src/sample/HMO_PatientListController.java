package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 2/20/2016.
 */
public class HMO_PatientListController extends VBox {
    @FXML
    private TableView<?> tableview;

    @FXML
    void initialize()
    {
        ObservableList patientlist= FXCollections.observableArrayList();

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection()){
            String sql="SELECT * from patient_registry where pacategory='HMO'";
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
                cm.hmo_name.set(rs.getString("hmo_name"));
                cm.address.set(rs.getString("address"));
                patientlist.addAll(cm);
            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        tableview.setItems(patientlist);


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

        TableColumn thmo_name=new TableColumn("HMO Name");
        thmo_name.setCellValueFactory(new PropertyValueFactory<ViewAllPatientBean, String>("hmo_name"));

        TableColumn taddress=new TableColumn("Address");
        taddress.setCellValueFactory(new PropertyValueFactory<ViewAllPatientBean, String>("address"));

        tableview.getColumns().addAll(tfirstname, tlastname, tothername, tpatienttype, tpatientcategory, tuserid, tsex, thmo_name, taddress);

        tableview.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue)->{

            ViewAllPatientBean vb=(ViewAllPatientBean) newvalue;
            if(vb!=null && vb.getHmo_name()!=null) {
                if (vb.getHmo_name().equalsIgnoreCase("STERLING HEALTH MANAGED CARE SERVICES LIMITED")) {

                    try {
                        Stage stage = new Stage();
                        Sterling_hmoController sh = new Sterling_hmoController(vb.getUserId());
                        Scene scene = new Scene(sh);
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                else if(vb.getHmo_name().equalsIgnoreCase("NOVO HEALTH AFRICA"))
                {
//                    Novo_Health nh=new Novo_Health(vb);
//                    nh.populate();
                }
                else if(vb.getHmo_name().equalsIgnoreCase("PREMIUM HEALTH"))
                {

                }
            }

        });


       }



    public HMO_PatientListController() throws IOException {
        FXMLLoader fxmlloader=new FXMLLoader(HMO_PatientListController.class.getResource("hmo_patientlist.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();


    }
}
