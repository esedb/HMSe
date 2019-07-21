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
 * Created by Ese on 3/31/2017.
 */
public class NHMOListController extends VBox {
    @FXML
    private Button hmogroupbutton;

    @FXML
    private TableView<?> tableview;

    @FXML
    private Button createhmobutton;

    @FXML
    private Button reghmobutton;

    @FXML
    void initialize(){
        ObservableList nhlist= FXCollections.observableArrayList();
        String sql="select * from hmo_details";

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql);){

            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                NHMOBeans nhbeans=new NHMOBeans();
                nhbeans.setHmoname(rs.getString("hmo_name"));
                nhbeans.setAddress(rs.getString("address"));
                nhbeans.setPhone(rs.getString("phone"));
                nhbeans.setEmail(rs.getString("cpemail"));
                nhbeans.setCity(rs.getString("city"));
                nhbeans.setState(rs.getString("state"));
                nhbeans.setCountry(rs.getString("country"));
                nhlist.add(nhbeans);
            }


        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        if(nhlist!=null && !nhlist.isEmpty()) {

            tableview.setItems(nhlist);
            TableColumn tsname=new TableColumn("HMO Name");
            tsname.setCellValueFactory(new PropertyValueFactory("hmoname"));

            TableColumn taddress=new TableColumn("Address");
            taddress.setCellValueFactory(new PropertyValueFactory("address"));

            TableColumn tcity=new TableColumn("City/ Address");
            tcity.setCellValueFactory(new PropertyValueFactory("city"));

            TableColumn tcountry=new TableColumn("Country");
            tcountry.setCellValueFactory(new PropertyValueFactory("country"));

            TableColumn temail=new TableColumn("Email");
            temail.setCellValueFactory(new PropertyValueFactory("email"));


            tableview.getColumns().addAll(tsname, taddress, tcity, tcountry, temail);
        }

        createhmobutton.setOnAction(e->{
            try {
                HMODetails hdetails = new HMODetails();
                Scene scene=new Scene(hdetails);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        });


        hmogroupbutton.setOnAction(e->{
            try {
                CreateHMOGroupController hmoc = new CreateHMOGroupController();
                Scene scene=new Scene(hmoc);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        });

        reghmobutton.setOnAction(e->{
            try {

                PatientRegistry hreg = new PatientRegistry();
                Scene scene = new Scene(hreg);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        });


    }

    public NHMOListController() throws IOException {

        FXMLLoader fxmlloader=new FXMLLoader(NHMOListController.class.getResource("new_hmo_list.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
}
