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
 * Created by Ese on 2/7/2016.
 */
public class Pplist extends VBox {
    @FXML
    private TableView<?> tableview;

    @FXML
    private Button makepaybutton;

    @FXML
    private Button refreshbutton;

    Connection con=null;
    DataBase dbase=null;
    @FXML
    void initialize()
    {
        List<String> set = null;
        List<PplistBeans> list=new ArrayList<>();
        try {

            dbase = new DataBase();
            con = dbase.getConnection();
        }


        catch(Exception ex) {
            ex.printStackTrace();
        }
        ObservableList patientlist= FXCollections.observableArrayList();
        try{
                     String sql9="SELECT * FROM patient_registry where payment_status='owning'";
                     DataBase database=new DataBase();
                     Connection dcon=database.getConnection();
                     PreparedStatement prepstatement=dcon.prepareStatement(sql9);

                     ResultSet rs2=prepstatement.executeQuery();
                     while (rs2.next()) {

                       PplistBeans plist = new PplistBeans(rs2.getString("firstname") + " " + rs2.getString("Othername"), rs2.getString("id_sn"));
                        patientlist.addAll(plist);
                    }

                }
        catch (Exception ex) {
                    ex.printStackTrace();
                }

        if(patientlist!=null){
            displayTable(patientlist);
        }


        makepaybutton.setOnAction(e->{
            long cost_of_service=-1;
            PplistBeans pl= (PplistBeans) tableview.getFocusModel().getFocusedItem();
            long totalvalue=-1;
            if(pl!=null) {
                try {

                    String owingsql = "select sum(cost) as total from service_rendered where id=?";
                    try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement stmt = con.prepareStatement(owingsql)) {
                        stmt.setString(1, pl.getId());
                        ResultSet rs=stmt.executeQuery();
                        while(rs.next()){
                            cost_of_service=rs.getLong("total");

                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    long deposited=-1;

                    if(cost_of_service>=0){
                        String paidsql = "select sum(amount_deposited) as total from payment_table where id_sn=?";
                        try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement stmt = con.prepareStatement(paidsql)) {
                            stmt.setString(1, pl.getId());
                            ResultSet rs=stmt.executeQuery();
                            while(rs.next()){
                                deposited=rs.getLong("total");

                            }
                            System.out.println("deposited " + deposited);

                            System.out.println("total value is " +totalvalue);
                            totalvalue=cost_of_service-deposited;
                            if(totalvalue>0){

                                PaymentController pm = new PaymentController(pl.getId(), "Owining", totalvalue);
                                Stage stage = new Stage();
                                Scene scene = new Scene(pm);
                                stage.setScene(scene);
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.setResizable(false);
                                stage.show();

                            }
                            else if(totalvalue<=0){
                                PaymentController pm = new PaymentController(pl.getId(), "Not Owining", totalvalue);
                                Stage stage = new Stage();
                                Scene scene = new Scene(pm);
                                stage.setScene(scene);
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.setResizable(false);
                                stage.show();

                            }


                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        System.out.println("cost of service is " + cost_of_service);
                    }


                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        });

        refreshbutton.setOnAction(e-> {

            ObservableList olist=FXCollections.observableArrayList();

            String sql9="SELECT * FROM patient_registry where payment_status='owning'";
            try (DataBase db1 = new DataBase(); Connection conn = db1.getConnection(); PreparedStatement stmt1 = con.prepareStatement(sql9)) {
                ResultSet rs = stmt1.executeQuery();
                while (rs.next()) {

                    PplistBeans plist = new PplistBeans(rs.getString("firstname") + " " + rs.getString("Othername"), rs.getString("id_sn"));
                    olist.addAll(plist);

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if(olist!=null && !olist.isEmpty()){
                displayTable(olist);
            }

        });
    }
    void displayTable(ObservableList patientlist){


        tableview.getColumns().clear();
        tableview.getItems().clear();


        tableview.setItems(patientlist);

        TableColumn tname=new TableColumn("Patient Name");
        tname.setCellValueFactory(new PropertyValueFactory<PplistBeans, String>("name"));

        TableColumn tid=new TableColumn("Id");
        tid.setCellValueFactory(new PropertyValueFactory<PplistBeans, String>("id"));

        tableview.getColumns().addAll(tname, tid);


    }

    public Pplist() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(PatientRegistry.class.getResource("pplist.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
}
