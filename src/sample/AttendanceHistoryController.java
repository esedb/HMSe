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
 * Created by Ese on 5/3/2017.
 */
public class AttendanceHistoryController extends VBox {
    private final String id;
    @FXML
    private TableView<?> tableview;

    @FXML
    private Button refreshbutton;

    @FXML
    void initialize(){

        displayTable();
        refreshbutton.setOnAction(e->{
            displayTable();
        });

    }

    void displayTable(){
        String ssql = "select * from attendance_roster where id_sn=?";
        ObservableList olist= FXCollections.observableArrayList();

        try (DataBase dbasee = new DataBase(); Connection conn = dbasee.getConnection(); PreparedStatement stmt = conn.prepareStatement(ssql); ) {
            stmt.setString(1, id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){

                AttendanceHistoryBeans ab=new AttendanceHistoryBeans();
                ab.id_sn.set(rs.getString("id_sn"));
                ab.surname.set(rs.getString("surname"));
                ab.othernames.set(rs.getString("othernames"));
                ab.date.set(rs.getString("date"));
                ab.time.set(rs.getString("time"));

                olist.add(ab);

            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        if(!olist.isEmpty()){
            sdisplayTable(olist);
        }
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
        tothername.setCellValueFactory(new PropertyValueFactory("othernames"));

        TableColumn tdate=new TableColumn("Date");
        tdate.setCellValueFactory(new PropertyValueFactory("date"));

        TableColumn ttime=new TableColumn("Time");
        ttime.setCellValueFactory(new PropertyValueFactory("time"));


        tableview.getColumns().addAll(tid, tsurname, tothername, tdate, ttime);

    }

    public AttendanceHistoryController(String id) throws IOException {
        this.id=id;

        FXMLLoader fxmlloader = new FXMLLoader(PatientRegistry.class.getResource("attendancehistory.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
}
