package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Ese on 2/24/2017.
 */
public class ViewAdmittedPatientController extends VBox {

    @FXML
    private DatePicker tdatepicker;

    @FXML
    private DatePicker fdatepicker;

    @FXML
    private Button vsign;

    @FXML
    private HBox searchbutton;

    @FXML
    private HBox hboxtableview;

    @FXML
    private TextField searchfld;

    @FXML
    private TableView<?> tableview;


    @FXML
    public void initialize(){

        ObservableList olist= FXCollections.observableArrayList();
        try {
            String sql = "SELECT * from vital_signs where admit=?";
            DataBase dbase = new DataBase();
            Connection con = dbase.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "Yes");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ViewAdmittedPatientBean vb = new ViewAdmittedPatientBean();
                vb.surname.set(rs.getString("surname"));
                vb.othername.set(rs.getString("othernames"));
                vb.complain.set(rs.getString("complain"));
                vb.doctor.set(rs.getString("doctor"));
                vb.b_group.set(rs.getString("b_group"));

                olist.add(vb);

            }

            tableview.setItems(olist);


        }
        catch(Exception ex){
            ex.printStackTrace();

            }

        TableColumn tsurname=new TableColumn("Surname");
        tsurname.setCellValueFactory(new PropertyValueFactory<ViewAdmittedPatientBean, String>("surname"));

        TableColumn tothername=new TableColumn("Othername");
        tothername.setCellValueFactory(new PropertyValueFactory<ViewAdmittedPatientBean, String>("othername"));


        TableColumn tcomplain=new TableColumn("complain");
        tcomplain.setCellValueFactory(new PropertyValueFactory<ViewAdmittedPatientBean, String>("complain"));

        TableColumn tdoctor=new TableColumn("Doctor");
        tdoctor.setCellValueFactory(new PropertyValueFactory<ViewAdmittedPatientBean, String>("doctor"));

        TableColumn tbloodgroup=new TableColumn("Blood Group");
        tbloodgroup.setCellValueFactory(new PropertyValueFactory<ViewAdmittedPatientBean, String>("b_group"));

        tableview.getColumns().addAll(tsurname, tothername, tcomplain, tdoctor, tbloodgroup);

        ObservableList tvolist=FXCollections.observableArrayList();

        searchfld.textProperty().addListener((or, olvalue, nvalue) ->
        {


            if (nvalue != null && nvalue.length() > 0) {
                tableview.getItems().clear();

                DataBase dbase = new DataBase();
                Connection con;
                String sql = "select * from vital_signs where surname like '" + nvalue + "%' or othernames like'" + nvalue + "&'";

                try {
                    con = dbase.getConnection();
                    PreparedStatement prepstatement = con.prepareStatement(sql);
                    ResultSet rs = prepstatement.executeQuery();
                    while (rs.next()) {

                        ViewAdmittedPatientBean vb = new ViewAdmittedPatientBean();
                        vb.surname.set(rs.getString("surname"));
                        vb.othername.set(rs.getString("othernames"));
                        vb.complain.set(rs.getString("complain"));
                        vb.doctor.set(rs.getString("doctor"));
                        vb.b_group.set(rs.getString("b_group"));

                        tvolist.add(vb);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                tableview.setItems(tvolist);

            }
           if(nvalue.length()<=0)
           {
               tableview.getItems().clear();
               tableview.setItems(olist);

           }



        });

        final String pattern = "yyyy-MM-dd";
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern(pattern);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };

        fdatepicker.setValue(LocalDate.now());
        fdatepicker.setConverter(converter);

        tdatepicker.setValue(LocalDate.now());
        tdatepicker.setConverter(converter);

        ObservableList dlist=FXCollections.observableArrayList();

        vsign.setOnAction(e->{

            try{
                if(!dlist.isEmpty()) {
                    dlist.clear();
                }
                Date fmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(fdatepicker.getValue().toString());
                Date tmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(tdatepicker.getValue().toString());

                java.sql.Date fmySqlDate = new java.sql.Date(fmyDate.getTime());
                java.sql.Date tmySqlDate = new java.sql.Date(tmyDate.getTime());
                String sql="SELECT * FROM vital_signs WHERE " +
                        "date between ? AND ? AND admit='Yes'";
                DataBase dbase=new DataBase();
                Connection con=dbase.getConnection();
                PreparedStatement statement=con.prepareStatement(sql);

                statement.setDate(1, fmySqlDate);
                statement.setDate(2, tmySqlDate);
                int i=0;
                ResultSet rs=statement.executeQuery();
                while(rs.next())
                {
                    ViewAdmittedPatientBean vb = new ViewAdmittedPatientBean();
                    vb.surname.set(rs.getString("surname"));

                    vb.othername.set(rs.getString("othernames"));
                    vb.complain.set(rs.getString("complain"));
                    vb.doctor.set(rs.getString("doctor"));
                    vb.b_group.set(rs.getString("b_group"));

                    dlist.add(vb);

                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

            tableview.setItems(dlist);

        });


    }

    public ViewAdmittedPatientController() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(ViewAdmittedPatientController.class.getResource("admitted_patients.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }



}
