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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Ese on 3/22/2017.
 */
public class OpenTemplatesController extends VBox {

    @FXML
    private Button deletebutton;

    @FXML
    private Button open_button;

    @FXML
    private TableView<?> tableview;

    @FXML
    void initialize(){

        displayTable();


        open_button.setOnAction(e->{


            TemplatesListBean tlb=(TemplatesListBean) tableview.getFocusModel().getFocusedItem();
            if(tlb!=null){
                try {
                    DataTemplateController dc = new DataTemplateController(tlb.getService_name(), null);
                    Stage stage = new Stage();
                    Scene scene = new Scene(dc);
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

            TemplatesListBean tlb=(TemplatesListBean) tableview.getFocusModel().getFocusedItem();
            String service_name=tlb.getService_name();

            String deletesql="delete from templates where service_name=?";

            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(deletesql)){
                stmt.setString(1, service_name);
                int d=stmt.executeUpdate();
                System.out.println("Delete is successful" + d);

                displayTable();



            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        });



    }

    void displayTable(){

        tableview.getItems().clear();
        tableview.getColumns().clear();

        ObservableList olist= FXCollections.observableArrayList();
        Set<String> set=new HashSet<>();


        try{
            String sql="Select * from templates";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement stmt=con.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){

                set.add(rs.getString("service_name"));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        if(set.isEmpty()){
            return;
        }

        for(String sv_name: set){

            TemplatesListBean tlb=new TemplatesListBean();
            tlb.setService_name(sv_name);
            olist.addAll(tlb);

        }

        if(olist!=null && !olist.isEmpty()){
            tableview.setItems(olist);

            TableColumn tsname=new TableColumn("Service Name");
            tsname.setCellValueFactory(new PropertyValueFactory("service_name"));
            tableview.getColumns().addAll(tsname);
        }

    }

    public OpenTemplatesController() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(DrugCategory.class.getResource("open_templates.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }

}
