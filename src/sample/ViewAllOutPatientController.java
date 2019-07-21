package sample;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javafx.collections.ObservableList;

/**
 * Created by Ese on 7/13/2016.
 */
public class ViewAllOutPatientController extends VBox {

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

    public ViewAllOutPatientController() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(ViewAllInpatientController.class.getResource("outpatient_table.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }

    @FXML
    public void initialize(){

        fromdatepicker.setValue(LocalDate.now());
        fromdatepicker.setConverter(DateConverter.convert());
        fromdatepicker.setEditable(false);

        todatepicker.setValue(LocalDate.now());
        todatepicker.setConverter(DateConverter.convert());
        todatepicker.setEditable(false);

        ObservableList olist = FXCollections.observableArrayList();
        String sql = "SELECT * FROM vital_signs where admit='No'";
        try (DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()){

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


        searchfld.textProperty().addListener((or, olvalue, nvalue) ->
        {

            if (nvalue != null && nvalue.length() > 0) {
                ObservableList olist2 = FXCollections.observableArrayList();
                String sqlstatement = "select * from vital_signs where othernames  like '" + nvalue + "%' " +
                        "AND admit='No'";

                try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sqlstatement); ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {

                        ViewAllInpatientBeans vb = new ViewAllInpatientBeans();
                        vb.patient_id.set(rs.getString("vital_signs.patient_id"));
                        vb.surname.set(rs.getString("vital_signs.surname"));
                        vb.name.set(rs.getString("vital_signs.othernames"));
                        vb.complains.set(rs.getString("vital_signs.complain"));

                        olist2.add(vb);

                    }


                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if(!olist2.isEmpty()){
                    displayTable(olist2);
                }


            }
            else{

                sdisplayTable();

            }


        });



        dsearchbutton.setOnAction(e->{

            ObservableList olistdate = FXCollections.observableArrayList();


            tableview.getItems().clear();
            tableview.getColumns().clear();

            String sqll = "SELECT * FROM vital_signs where date between ? and ? and admit=?";

            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement statement=con.prepareStatement(sqll)) {
                Date fmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromdatepicker.getValue().toString());
                Date tmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(todatepicker.getValue().toString());

                java.sql.Date fmySqlDate = new java.sql.Date(fmyDate.getTime());
                java.sql.Date tmySqlDate = new java.sql.Date(tmyDate.getTime());

                statement.setDate(1, fmySqlDate);
                statement.setDate(2, tmySqlDate);
                statement.setString(3, "No");
                ResultSet rs = statement.executeQuery();


                while(rs.next())
                {
                    ViewAllInpatientBeans vb = new ViewAllInpatientBeans();
                    vb.patient_id.set(rs.getString("vital_signs.patient_id"));
                    vb.surname.set(rs.getString("vital_signs.surname"));
                    vb.name.set(rs.getString("vital_signs.othernames"));
                    vb.complains.set(rs.getString("vital_signs.complain"));
                    olistdate.add(vb);
                }



            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if(!olistdate.isEmpty()){
                displayTable(olistdate);
            }

        });

    }

    public void displayTable( ObservableList olist)
    {

        tableview.setItems(olist);
        tableview.getColumns().clear();

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

        ObservableList olist = FXCollections.observableArrayList();

        String sql = "SELECT * FROM vital_signs where admit='No'";
        try (DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()){

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
