package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 5/2/2017.
 */
public class DischargePatientController extends VBox {

    @FXML
    private Button dischargebutton;

    @FXML
    private TableView<?> tableview;

    @FXML
    private Button makepaybutton;

    @FXML
    private Button deletebutton;

    @FXML
    void initialize(){

        makepaybutton.setOnAction(e->{


        });

        deletebutton.setOnAction(e->{

            DischargePatientBeans dbeans=(DischargePatientBeans)tableview.getFocusModel().getFocusedItem();
            if(dbeans!=null) {
                String id=dbeans.getId_sn();
                String deletesql = "delete from discharge_patient where id_sn=?";
                try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement stmt = con.prepareStatement(deletesql)) {
                    stmt.setString(1, id);
                    int d=stmt.executeUpdate();
                    if(d==1){
                        displayTable();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        });

        displayTable();

        dischargebutton.setOnAction(e->{
            DischargePatientBeans dbeans=(DischargePatientBeans)tableview.getFocusModel().getFocusedItem();
            if(dbeans!=null) {
                String id = dbeans.getId_sn();

                String dpsql = "update discharge_patient set discharge_status='Discharged' where id_sn=?";
                try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement stmt = con.prepareStatement(dpsql)) {
                    stmt.setString(1, id);
                    int d=stmt.executeUpdate();
                    if(d==1){
                        displayTable();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }



        });


    }

    public DischargePatientController() throws IOException {

        FXMLLoader fxmlloader=new FXMLLoader(ExamDiagController.class.getResource("dischargedlist.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }

    void sdisplayTable(ObservableList olist){
        tableview.getColumns().clear();
        tableview.getItems().clear();

        tableview.setItems(olist);

        TableColumn tid=new TableColumn("Patient Id");
        tid.setCellValueFactory(new PropertyValueFactory("id_sn"));

        TableColumn tsurname=new TableColumn("Surname");
        tsurname.setCellValueFactory(new PropertyValueFactory("surname"));

        TableColumn tothername=new TableColumn("Othernames");
        tothername.setCellValueFactory(new PropertyValueFactory("othername"));

        TableColumn tcost=new TableColumn("Cost Of Service");
        tcost.setCellValueFactory(new PropertyValueFactory("scost"));

        TableColumn tdeposits=new TableColumn("Deposit");
        tdeposits.setCellValueFactory(new PropertyValueFactory("deposits"));

        TableColumn treceivables=new TableColumn("Receivables");
        treceivables.setCellValueFactory(new PropertyValueFactory("receivable"));

        TableColumn tpaymentstatus=new TableColumn("Payment Status");
        tpaymentstatus.setCellValueFactory(new PropertyValueFactory("payment_status"));

        TableColumn tdischargestatus=new TableColumn("Discharge Status");
        tdischargestatus.setCellValueFactory(new PropertyValueFactory("discharge_status"));

        tableview.getColumns().addAll(tid, tsurname, tothername, tcost, tdeposits, treceivables, tpaymentstatus, tdischargestatus);


    }

    void displayTable(){
        ObservableList dlist=FXCollections.observableArrayList();
        String dpsql="select * from discharge_patient";
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(dpsql)){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()) {
                DischargePatientBeans db = new DischargePatientBeans();
                db.id_sn.set(rs.getString("id_sn"));
                db.surname.set(rs.getString("surname"));
                db.othername.set(rs.getString("othername"));
                db.scost.set(rs.getDouble("cost_of_service"));
                db.deposits.set(rs.getDouble("amount_deoposited"));
                db.receivables.set(rs.getDouble("receivables"));
                db.payment_status.set(rs.getString("status"));
                db.discharge_status.set(rs.getString("discharge_status"));
                dlist.addAll(db);

            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        if(!dlist.isEmpty()){
            sdisplayTable(dlist);
        }

    }
}
