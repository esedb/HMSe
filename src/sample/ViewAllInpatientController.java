package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Ese on 7/10/2016.
 */
public class ViewAllInpatientController extends VBox {

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
    private Button dsearchbutton;

    @FXML
    private DatePicker todatepicker;

    @FXML
    private Button assign_bed_button;


    public ViewAllInpatientController() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(ViewAllInpatientController.class.getResource("inpatient_table.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
    @FXML
    public void initialize() {

        assign_bed_button.setOnAction(e->{
            ViewAllInpatientBeans vb=(ViewAllInpatientBeans) tableview.getFocusModel().getFocusedItem();
            if(vb!=null) {
                String id = vb.getPatient_id();
                String name = vb.getSurname() + " " + vb.getName();

                try {
                    Stage stage = new Stage();
                    AssignWardAndBedController ab = new AssignWardAndBedController(id, name, stage);
                    Scene scene = new Scene(ab);
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        ObservableList olistdate=FXCollections.observableArrayList();

        fromdatepicker.setValue(LocalDate.now());
        fromdatepicker.setConverter(DateConverter.convert());

        todatepicker.setValue(LocalDate.now());
        todatepicker.setConverter(DateConverter.convert());
        ObservableList olist = FXCollections.observableArrayList();



        String sql = "SELECT * FROM vital_signs where admit='Yes'";
        try( DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ViewAllInpatientBeans vb = new ViewAllInpatientBeans();
                vb.patient_id.set(rs.getString("vital_signs.patient_id"));
                vb.surname.set(rs.getString("vital_signs.surname"));
                vb.name.set(rs.getString("vital_signs.othernames"));
                vb.complains.set(rs.getString("vital_signs.complain"));

                olist.addAll(vb);


            }
            displayTable(olist);


        } catch (Exception ex) {
            ex.printStackTrace();
        }




        searchfld.textProperty().addListener((or, olvalue, nvalue) ->
        {


            if (nvalue != null && nvalue.length() > 0) {
                ObservableList olist2 = FXCollections.observableArrayList();
                String sqlstatement = "select * from vital_signs  where othernames  like '%" + nvalue + "%' or surname like '%" + nvalue + "%'";
                try (DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement statements=con.prepareStatement(sqlstatement)){
                    ResultSet rs = statements.executeQuery();
                    while (rs.next()) {

                        ViewAllInpatientBeans vb = new ViewAllInpatientBeans();
                        vb.patient_id.set(rs.getString("vital_signs.patient_id"));
                        vb.surname.set(rs.getString("vital_signs.surname"));
                        vb.name.set(rs.getString("vital_signs.othernames"));
                        vb.complains.set(rs.getString("vital_signs.complain"));

                        olist2.addAll(vb);

                    }



                } catch (Exception ex) {
                    ex.printStackTrace();

                }

                if(!olist2.isEmpty()){
                    displayTable( olist2);
                }

            }
            else{
                sdisplayTable();
            }




        });

        dsearchbutton.setOnAction(e->{

            tableview.getItems().clear();
            tableview.getColumns().clear();
            try {
                Date fmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromdatepicker.getValue().toString());
                Date tmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(todatepicker.getValue().toString());

                java.sql.Date fmySqlDate = new java.sql.Date(fmyDate.getTime());
                java.sql.Date tmySqlDate = new java.sql.Date(tmyDate.getTime());
                String sqll = "SELECT * FROM vital_signs where date between ? AND ? AND admit='Yes'";
                DataBase database = new DataBase();
                Connection connection = database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sqll);

                statement.setDate(1, fmySqlDate);
                statement.setDate(2, tmySqlDate);
                int n = 0;
                ResultSet rs = statement.executeQuery();
                while(rs.next())
                {
                    ViewAllInpatientBeans vb = new ViewAllInpatientBeans();
                    vb.patient_id.set(rs.getString("vital_signs.patient_id"));
                    vb.surname.set(rs.getString("vital_signs.surname"));
                    vb.name.set(rs.getString("vital_signs.othernames"));
                    vb.complains.set(rs.getString("vital_signs.complain"));

                    olistdate.addAll(vb);
                }



            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if(!olistdate.isEmpty()){
                displayTable(olistdate);
            }

        });


    }

    public void displayTable(ObservableList olist)
    {
        tableview.getColumns().clear();
        tableview.getItems().clear();


        tableview.setItems(olist);

        TableColumn tpatientid=new TableColumn("Patient Id");
        tpatientid.setCellValueFactory(new PropertyValueFactory("patient_id"));

        TableColumn tsurname= new TableColumn("Surname");
        tsurname.setCellValueFactory(new PropertyValueFactory("surname"));

        TableColumn tname=new TableColumn("name");
        tname.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn tcomplains=new TableColumn("Complains");
        tcomplains.setCellValueFactory(new PropertyValueFactory("complains"));


        tableview.getColumns().addAll(tpatientid, tsurname, tname, tcomplains);




    }

    void sdisplayTable(){

        ObservableList olist=FXCollections.observableArrayList();
        String sql = "SELECT * FROM vital_signs where admit='Yes'";
        try( DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ViewAllInpatientBeans vb = new ViewAllInpatientBeans();
                vb.patient_id.set(rs.getString("vital_signs.patient_id"));
                vb.surname.set(rs.getString("vital_signs.surname"));
                vb.name.set(rs.getString("vital_signs.othernames"));
                vb.complains.set(rs.getString("vital_signs.complain"));

                olist.addAll(vb);


            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if(!olist.isEmpty()){
            displayTable(olist);
        }

    }

}
