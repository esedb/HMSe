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
 * Created by Ese on 1/26/2016.
 */
public class PatientList extends VBox {

    @FXML
    private Button showappointment;

    @FXML
    private TableView<?> patienttable;

    @FXML
    private TableView<?> scheduletable;

    @FXML
    private Button markbutton;

    @FXML
    private Button refreshbutton;


    @FXML
    private void initialize() {
        ObservableList palist= FXCollections.observableArrayList();


        showappointment.setOnAction(e -> {

            patienttable.getItems().clear();

            scheduletable.getItems().clear();
            scheduletable.getColumns().clear();
            String sql="SELECT * FROM patient_schedule where status='not done' AND assign_to=?";
            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement statement=con.prepareStatement(sql)){
                statement.setString(1, Controller.sign_by);
                ResultSet rs=statement.executeQuery();

                while(rs.next())
                {
                    palist.addAll(new PatientListBean(rs.getString("patient_id"), rs.getString("patient_name")));

                }

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

            if(!palist.isEmpty()){
                patienttable.setItems(palist);

            }


            });

        TableColumn tid=new TableColumn("Patient id");
        tid.setCellValueFactory(new PropertyValueFactory<PatientListBean, String>("patient_id"));

        TableColumn tname=new TableColumn("Patient name");
        tname.setCellValueFactory(new PropertyValueFactory<PatientListBean, String>("patient_name"));

        patienttable.getColumns().addAll(tid, tname);

        patienttable.getSelectionModel().selectedItemProperty().addListener((obervable, oldvalue, newvalue)->
        {
            ObservableList schedulelist=FXCollections.observableArrayList();
            PatientListBean list=(PatientListBean) patienttable.getSelectionModel().getSelectedItem();
            if(list!=null) {

                String sql = "SELECT * from patient_schedule where patient_id=? AND assign_to=?";
                String id = list.getPatient_id();
                try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement statement = con.prepareStatement(sql)){

                        statement.setString(1, id);
                        statement.setString(2, Controller.sign_by);

                        ResultSet rs = statement.executeQuery();
                        while (rs.next()) {
                            schedulelist.addAll(new PatientScheduleBeans(rs.getString("patient_id"), rs.getString("date"), rs.getString("time"), rs.getString("purpose"),
                                    rs.getString("details"), rs.getString("scheduled_by"), rs.getString("status")));
                    }
                                    }
                catch (Exception ex) {
                    ex.printStackTrace();
                }

                if(!schedulelist.isEmpty()){

                    displayTable(schedulelist);
                }
            }




        });

        markbutton.setOnAction(e -> {
            PatientScheduleBeans p=(PatientScheduleBeans)scheduletable.getFocusModel().getFocusedItem();
            if(p!=null) {

                String id = p.getId();
                String status = p.getStatus();
                System.out.println(status);
                if (status.equals("not done")) {
                    try {

                        String sql = "UPDATE patient_schedule set status='done' where patient_id=?";

                        Connection con = new DataBase().getConnection();
                        PreparedStatement statement = con.prepareStatement(sql);
                        statement.setString(1, p.getId());

                        int result = statement.executeUpdate();
                        if (result == 1) {
                            displayTable();
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("select a schedule first");
                }
            }
            else{
                return;
            }




        });


    }
    public PatientList() throws IOException {
        FXMLLoader fxmlloader=new FXMLLoader(PatientList.class.getResource("patientlist.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }

    void displayTable(ObservableList schedulelist){

        scheduletable.getColumns().clear();
        scheduletable.getItems().clear();


        scheduletable.setItems(schedulelist);

        TableColumn tpatientid = new TableColumn("Patient id");
        tpatientid.setCellValueFactory(new PropertyValueFactory<PatientScheduleBeans, String>("id"));

        TableColumn tdate = new TableColumn("Date");
        tdate.setCellValueFactory(new PropertyValueFactory<PatientScheduleBeans, String>("date"));

        TableColumn ttime = new TableColumn("Time");
        ttime.setCellValueFactory(new PropertyValueFactory<PatientScheduleBeans, String>("time"));

        TableColumn tpurpose = new TableColumn("Purpose");
        tpurpose.setCellValueFactory(new PropertyValueFactory<PatientScheduleBeans, String>("purpose"));

        TableColumn tdetails = new TableColumn("Details");
        tdetails.setCellValueFactory(new PropertyValueFactory<PatientScheduleBeans, String>("details"));

        TableColumn tscheduleby = new TableColumn("Schedule By");
        tscheduleby.setCellValueFactory(new PropertyValueFactory<PatientScheduleBeans, String>("scheduleby"));

        TableColumn tstatus = new TableColumn("Scedule Status");
        tstatus.setCellValueFactory(new PropertyValueFactory<PatientScheduleBeans, String>("status"));

        scheduletable.getColumns().addAll(tpatientid, tdate, ttime, tpurpose, tdetails, tscheduleby, tstatus);


    }

    void displayTable(){

        ObservableList schedulelist=FXCollections.observableArrayList();
        PatientListBean list=(PatientListBean) patienttable.getSelectionModel().getSelectedItem();
        if(list!=null) {

            String sql = "SELECT * from patient_schedule where patient_id=?";
            String id = list.getPatient_id();
            try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement statement = con.prepareStatement(sql)){

                statement.setString(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    schedulelist.addAll(new PatientScheduleBeans(rs.getString("patient_id"), rs.getString("date"), rs.getString("time"), rs.getString("purpose"),
                            rs.getString("details"), rs.getString("scheduled_by"), rs.getString("status")));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            if(!schedulelist.isEmpty()){

                displayTable(schedulelist);
            }
        }


    }

}
