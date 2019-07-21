package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 * Created by Ese on 2/29/2016.
 */
public class InPatients extends VBox {
    @FXML
    private HBox tableviewdisplay;

    @FXML
    private DatePicker fromdatepicker;

    @FXML
    private HBox hboxtableview;

    @FXML
    private TextField searchfld;

    @FXML
    private TableView<?> tableview;

    @FXML
    private DatePicker todatepicker;

    @FXML
    void initialize(){

        todatepicker.setConverter(DateConverter.convert());
        fromdatepicker.setConverter(DateConverter.convert());

        ObservableList inlist= FXCollections.observableArrayList();

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection())
        {
            String sql="SELECT * FROM vital_signs where admit='Yes'";
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                InPatientBeans inpatient=new InPatientBeans(rs.getString("patient_id"), rs.getString("surname"), rs.getString("othernames"),
                        rs.getString("complain"), rs.getString("date"));

                inlist.addAll(inpatient);



            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        tableview.setItems(inlist);

        TableColumn tid=new TableColumn("Patient Id");
        tid.setCellValueFactory(new PropertyValueFactory<InPatientBeans, String>("idsn"));

        TableColumn tsurname=new TableColumn("Surname");
        tsurname.setCellValueFactory(new PropertyValueFactory<InPatientBeans, String>("surname"));

        TableColumn tothername=new TableColumn("Othernames");
        tothername.setCellValueFactory(new PropertyValueFactory<InPatientBeans, String>("othernmae"));

        TableColumn tcomplain=new TableColumn("Complains");
        tcomplain.setCellValueFactory(new PropertyValueFactory<InPatientBeans, String>("complains"));

        TableColumn tdate=new TableColumn("Date");
        tdate.setCellValueFactory(new PropertyValueFactory<InPatientBeans, String>("date"));

        tableview.getColumns().addAll(tsurname, tothername, tid, tcomplain, tdate);




    }
    public InPatients() throws IOException {

        FXMLLoader fxmlloader=new FXMLLoader(FrontTilesController.class.getResource("inpatients.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();


    }
}
