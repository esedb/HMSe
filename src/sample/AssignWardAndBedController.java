package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 * Created by Ese on 4/27/2017.
 */
public class AssignWardAndBedController extends VBox {

    private final Stage stage;
    private final String patient_id;
    private final String name;

    @FXML
    private TextField namefld;

    @FXML
    private Button assignbutton;

    @FXML
    private TextField idfld;

    @FXML
    private TableView<?> tableview;

    @FXML
    private Button removebutton;




    @FXML
    void initialize(){

        idfld.setText(patient_id);
        namefld.setText(name);
        System.out.println("name is " + name);
        namefld.setEditable(false);
        idfld.setEditable(false);

        removebutton.setOnAction(e->{
            WardAndBedBeans wb=(WardAndBedBeans) tableview.getFocusModel().getFocusedItem();
            if(wb!=null) {
                String id = wb.getPatient_id();
                System.out.println("id is " + id);
                if (id.equalsIgnoreCase("0") || id.length() <= 2) {
                    System.out.println("this rong part ran");
                    return;
                } else {
                    String sql = "update ward_and_bed set patient_id=?, status=?, name='None' where patient_id=?";
                    try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
                        stmt.setString(1, "0");
                        stmt.setString(2, "vacant");
                        stmt.setString(3, id);
                        int d=stmt.executeUpdate();
                        if(d==1){
                            sdisplayTable();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

        });


        ObservableList tlist= FXCollections.observableArrayList();
        String tsql="select * from ward_and_bed";
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(tsql)){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){

                WardAndBedBeans wb=new WardAndBedBeans();
                wb.ward.set(rs.getInt("ward"));
                wb.bed.set(rs.getInt("bed"));
                wb.status.set(rs.getString("status"));
                wb.patient_id.set(rs.getString("patient_id"));
                wb.date.set(rs.getString("date"));
                wb.name.set(rs.getString("name"));

                tlist.add(wb);

            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        if(!tlist.isEmpty()){
            displayTable(tlist);
        }

        DatePicker datepicker=new DatePicker();
        datepicker.setConverter(DateConverter.convert());
        datepicker.setValue(LocalDate.now());
        datepicker.setEditable(false);

        assignbutton.setOnAction(e->{
            String validatesql="select patient_id from ward_and_bed where patient_id=?";
            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(validatesql)){
                stmt.setString(1, patient_id);
                ResultSet rs=stmt.executeQuery();
                if(rs.next()){
                    System.out.println("Already have been assigned bed");

                    return;
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            WardAndBedBeans wb=(WardAndBedBeans)tableview.getFocusModel().getFocusedItem();
            if(wb!=null && !wb.getStatus().equalsIgnoreCase("Occupied")) {
                String updatesql = "update ward_and_bed set status=?, patient_id=?, name=?, date=? where ward=? and bed=?";
                try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement stmt = con.prepareStatement(updatesql)) {
                    stmt.setString(1, "Occupied");
                    stmt.setString(2, idfld.getText());
                    stmt.setString(3, namefld.getText());
                    stmt.setString(4, datepicker.getValue().toString());
                    stmt.setInt(5, wb.getWard());
                    stmt.setInt(6, wb.getBed());
                    int d=stmt.executeUpdate();
                    if(d==1){
                        System.out.println("Operation successful");
                        sdisplayTable();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else{
                System.out.println("Select a valid room or the room selected is occupied");
            }

        });


    }
    public AssignWardAndBedController(String patient_id, String name, Stage stage)throws IOException {
        this.name=name;
        this.patient_id=patient_id;
        this.stage=stage;
        FXMLLoader fxmlloader = new FXMLLoader(AdminDetailsController.class.getResource("ward_and_bed.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }

    void displayTable(ObservableList tvolist){
        tableview.getColumns().clear();
        tableview.getItems().clear();

        tableview.setItems(tvolist);

        TableColumn tward = new TableColumn("Ward No.");
        tward.setCellValueFactory(new PropertyValueFactory("ward"));

        TableColumn tbed = new TableColumn("Bed No.");
        tbed.setCellValueFactory(new PropertyValueFactory("bed"));

        TableColumn tstatus = new TableColumn("Status");
        tstatus.setCellValueFactory(new PropertyValueFactory("status"));

        TableColumn tid = new TableColumn("Patient id");
        tid.setCellValueFactory(new PropertyValueFactory("patient_id"));

        TableColumn tdate = new TableColumn("Date");
        tdate.setCellValueFactory(new PropertyValueFactory("date"));

        TableColumn tname = new TableColumn("Name");
        tname.setCellValueFactory(new PropertyValueFactory("name"));




        tableview.getColumns().addAll(tward, tbed, tstatus, tid, tname, tdate);

    }

    void sdisplayTable(){

        ObservableList tlist= FXCollections.observableArrayList();
        String tsql="select * from ward_and_bed";
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(tsql)){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){

                WardAndBedBeans wb=new WardAndBedBeans();
                wb.ward.set(rs.getInt("ward"));
                wb.bed.set(rs.getInt("bed"));
                wb.status.set(rs.getString("status"));
                wb.patient_id.set(rs.getString("patient_id"));
                wb.date.set(rs.getString("date"));
                wb.name.set(rs.getString("name"));

                tlist.add(wb);

            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        if(!tlist.isEmpty()) {

            displayTable(tlist);
        }


        }



}
