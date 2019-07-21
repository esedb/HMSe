package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
 * Created by Ese on 4/5/2017.
 */
public class SurgeryListController extends VBox {

    @FXML
    private Button deletebutton;

    @FXML
    private Button ckbutton;

    @FXML
    private TableView<?> tableview;


    @FXML
    public void initialize(){

        displayTable();

        deletebutton.setOnAction(e->{

            SurgeryListBean sl=(SurgeryListBean) tableview.getFocusModel().getFocusedItem();
            String id=sl.getId_sn();

            String deletesql="delete from surgery_patient where id_sn=?";
            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(deletesql)){
                stmt.setString(1, id);
                int d=stmt.executeUpdate();
                if(d==1){
                    displayTable();
                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }



        });


        ckbutton.setOnAction(e->{
            SurgeryListBean sb=(SurgeryListBean)tableview.getFocusModel().getFocusedItem();
            String patientid=sb.getId_sn();
            try {
                PreCheckListController pcl = new PreCheckListController(patientid);
                Scene scene=new Scene(pcl);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        });



    }

    public void displayTable(){
        tableview.getColumns().clear();
        tableview.getItems().clear();

        String sql_list="Select * from surgery_patient";
        ObservableList slist= FXCollections.observableArrayList();

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql_list)){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                SurgeryListBean sb=new SurgeryListBean();
                sb.setSurname(rs.getString("surname"));
                sb.setOthername(rs.getString("othername"));
                sb.setSurgery_type(rs.getString("surgery_type"));
                sb.setId_sn(rs.getString("id_sn"));
                slist.add(sb);
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        if(!slist.isEmpty()){
            tableview.setItems(slist);

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

    public SurgeryListController() throws IOException {

        FXMLLoader fxmlloader=new FXMLLoader(SurgeryListController.class.getResource("sugerylist.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }

}
