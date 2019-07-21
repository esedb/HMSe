package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
 * Created by Ese on 4/8/2017.
 */
public class LaboratoryPatientList extends VBox {



    @FXML
    private Button view_web_id;

    @FXML
    private Button ptestbutton;

    @FXML
    private Button deletebutton;

    @FXML
    private TableView<?> tableview;

    @FXML
    public void initialize() {

        displayTable();

        view_web_id.setOnAction(e->{
            try {

                SurgeryListBean sb=(SurgeryListBean) tableview.getFocusModel().getFocusedItem();
                if(sb!=null) {
                    WebViewTest wtest = new WebViewTest(sb.getId_sn());
                    Stage stage = new Stage();
                    Scene scene = new Scene(wtest);
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }
                else{
                    return;
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        });

        /*
        viewreportbutton.setOnAction(e->{
            System.out.println("this part of the programme ran");
            SurgeryListBean sb=(SurgeryListBean) tableview.getFocusModel().getFocusedItem();
            ObservableList olist=FXCollections.observableArrayList();


            String sql="SELECT distinct * FROM lab_patient  inner join service_data on lab_patient.id_sn=service_data.c_id where lab_patient.id_sn=?";
            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
                stmt.setString(1, sb.getId_sn());
                ResultSet rs=stmt.executeQuery();
                while(rs.next()){
                    ReportDataListBeans rb=new ReportDataListBeans();
                    rb.id_sn.set(rs.getString("lab_patient.id_sn"));
                    rb.dataname.set(rs.getString("service_data.service_name"));
                    rb.data.set(rs.getString("service_data.service_type"));
                    rb.datavalue.set(rs.getString("service_data.service_value"));
                    rb.date.set(rs.getString("service_data.date_v"));
                    olist.add(rb);
                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            if(!olist.isEmpty()){
                try {
                    Stage stage=new Stage();
                    ReportDataController rc = new ReportDataController(olist, stage);
                    Scene scene=new Scene(rc);

                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.show();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            else{
                ShowDialog.show("No laboratory Test data");
            }

        });
        */

        ptestbutton.setOnAction(e->{

            SurgeryListBean sl=(SurgeryListBean) tableview.getFocusModel().getFocusedItem();
            if(sl!=null){
                String labtype=sl.getSurgery_type();
                String id=sl.getId_sn();
                try {
                    DataTemplateController dc = new DataTemplateController(labtype, id);
                    Scene scene = new Scene(dc);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            }


        });

        deletebutton.setOnAction(e->{

            SurgeryListBean sl=(SurgeryListBean) tableview.getFocusModel().getFocusedItem();
            if(sl!=null) {
                String id = sl.getId_sn();

                String deletesql = "delete from lab_patient where id_sn=?";
                try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement stmt = con.prepareStatement(deletesql)) {
                    stmt.setString(1, id);
                    int d = stmt.executeUpdate();
                    if (d == 1) {
                        displayTable();
                    }
                    else{
                        System.out.println("Delete Operation unsucessfull");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });


    }

    void displayTable(){

        tableview.getColumns().clear();
        tableview.getItems().clear();

        ObservableList lablist = FXCollections.observableArrayList();

        String lablistsql="Select * from lab_patient";
        try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement stmt = con.prepareStatement(lablistsql)) {
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                SurgeryListBean sb=new SurgeryListBean();
                sb.setSurname(rs.getString("surname"));
                sb.setOthername(rs.getString("othername"));
                sb.setSurgery_type(rs.getString("lab_type"));
                sb.setId_sn(rs.getString("id_sn"));
                lablist.add(sb);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if(!lablist.isEmpty()){
            tableview.setItems(lablist);

            TableColumn tsurname= new TableColumn("Surname");
            tsurname.setCellValueFactory(new PropertyValueFactory("surname"));

            TableColumn tothername=new TableColumn("Other Names");
            tothername.setCellValueFactory(new PropertyValueFactory("othername"));

            TableColumn tsurgery=new TableColumn("Surgery Type");
            tsurgery.setCellValueFactory(new PropertyValueFactory("surgery_type"));

            TableColumn tidsn=new TableColumn("Patient Id");
            tidsn.setCellValueFactory(new PropertyValueFactory("id_sn"));

            tableview.getColumns().addAll(tidsn, tsurname, tothername, tsurgery);
            
        }

    }
    public LaboratoryPatientList() throws IOException {

        FXMLLoader fxmlloader=new FXMLLoader(LaboratoryPatientList.class.getResource("labpatientslist.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();


    }
}
