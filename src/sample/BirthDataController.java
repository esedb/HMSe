package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class BirthDataController extends VBox {

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

    public BirthDataController() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(PatientRegistry.class.getResource("birth_data.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }

    @FXML
    public void initialize() {

        fromdatepicker.setValue(LocalDate.now());
        fromdatepicker.setConverter(DateConverter.convert());

        todatepicker.setValue(LocalDate.now());
        todatepicker.setConverter(DateConverter.convert());

        displayTable();


        dsearchbutton.setOnAction(e -> {
            ObservableList olist=FXCollections.observableArrayList();

            String sql = "SELECT * FROM birth_info WHERE date_of_delivery between ? AND ?";

            try (DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement statement=con.prepareStatement(sql)){
                Date fmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromdatepicker.getValue().toString());
                Date tmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(todatepicker.getValue().toString());

                java.sql.Date fmySqlDate = new java.sql.Date(fmyDate.getTime());
                java.sql.Date tmySqlDate = new java.sql.Date(tmyDate.getTime());

                statement.setDate(1, fmySqlDate);
                statement.setDate(2, tmySqlDate);

                ResultSet rs = statement.executeQuery();
                while (rs.next()) {

                    BirthDataBeans bd=new BirthDataBeans(rs.getString("baby_id"),rs.getString("tribe_of_father"), rs.getString("place_of_birth"),
                            rs.getInt("weight"), rs.getString("sign"), rs.getString("our_ref"), rs.getString("date_of_delivery"),
                            rs.getString("name_of_mother"), rs.getString("perm_address"), rs.getString("occupation_of_father"),
                            rs.getString("name_of_father"), rs.getString("tribe_of_mother"), rs.getString("local_gov_origin"),
                            rs.getString("time_of_delivery"));


                    olist.addAll(bd);

                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

            sdisplayTable(olist);


        });

        searchfld.textProperty().addListener((or, olvalue, nvalue) ->
        {

            ObservableList olist=FXCollections.observableArrayList();

            if (nvalue != null && nvalue.length() > 0) {

                /*  String sql = "select * from birth_info where baby_id like '" + nvalue + "%' or name_of_mother like'" +
                        "%' or name_of_father like'" + nvalue + "&'";
                        */

                try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection();
                    PreparedStatement prepstatement=con.prepareStatement("select * from birth_info where baby_id like '" + nvalue + "%' or name_of_mother like'" + nvalue +
                            "%' or name_of_father like'" + nvalue + "&'");
                ResultSet rs = prepstatement.executeQuery()) {

                    while (rs.next()) {
                        BirthDataBeans bd=new BirthDataBeans(rs.getString("baby_id"),rs.getString("tribe_of_father"), rs.getString("place_of_birth"),
                                rs.getInt("weight"), rs.getString("sign"), rs.getString("our_ref"), rs.getString("date_of_delivery"),
                                rs.getString("name_of_mother"), rs.getString("perm_address"), rs.getString("occupation_of_father"),
                                rs.getString("name_of_father"), rs.getString("tribe_of_mother"), rs.getString("local_gov_origin"),
                                rs.getString("time_of_delivery"));


                        olist.addAll(bd);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(!olist.isEmpty()) {

                    sdisplayTable(olist);

                }


            }
            else{
                displayTable();
            }

        });


    }

    public void searchByDate(ObservableList olist)
    {
        try (DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement statement =con.prepareStatement("select * from birth_info")){

            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                BirthDataBeans bd=new BirthDataBeans(rs.getString("baby_id"),rs.getString("tribe_of_father"), rs.getString("place_of_birth"),
                        rs.getInt("weight"), rs.getString("sign"), rs.getString("our_ref"), rs.getString("date_of_delivery"),
                        rs.getString("name_of_mother"), rs.getString("perm_address"), rs.getString("occupation_of_father"),
                        rs.getString("name_of_father"), rs.getString("tribe_of_mother"), rs.getString("local_gov_origin"),
                        rs.getString("time_of_delivery"));


                olist.addAll(bd);
            }

            rs.close();

        } catch (Exception ex)
        {
            ex.printStackTrace();

        }
            sdisplayTable(olist);





    }

    void sdisplayTable(ObservableList olist){

        tableview.getColumns().clear();
        tableview.getItems().clear();

        tableview.setItems(olist);

        TableColumn tbabyid = new TableColumn("Baby Id");
        tbabyid.setCellValueFactory(new PropertyValueFactory("baby_id"));

        TableColumn ttribeoffather = new TableColumn("Fathers Tribe");
        ttribeoffather.setCellValueFactory(new PropertyValueFactory("fathers_tribe"));

        TableColumn tplaceofbirth = new TableColumn("Place of Birth");
        tplaceofbirth.setCellValueFactory(new PropertyValueFactory("placeofbirth"));

        TableColumn tweight = new TableColumn("Weight");
        tweight.setCellValueFactory(new PropertyValueFactory("weight"));

        TableColumn tsignby = new TableColumn("Sign By");
        tsignby.setCellValueFactory(new PropertyValueFactory("signby"));

        TableColumn tour_ref = new TableColumn("Our Ref");
        tour_ref.setCellValueFactory(new PropertyValueFactory("our_ref"));

        TableColumn tdateofdelivery = new TableColumn("Date of Delivery");
        tdateofdelivery.setCellValueFactory(new PropertyValueFactory("dateofdelivery"));

        TableColumn tnameofmother = new TableColumn("Name of Mother");
        tnameofmother.setCellValueFactory(new PropertyValueFactory("nameofmother"));

        TableColumn tpermanet_address = new TableColumn("Permanent Address");
        tpermanet_address.setCellValueFactory(new PropertyValueFactory("perm_address"));

        TableColumn toccufather = new TableColumn("Occupation of Father");
        toccufather.setCellValueFactory(new PropertyValueFactory("occu_father"));

        TableColumn tfathername = new TableColumn("Fathers Name");
        tfathername.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("fathers_name"));

        TableColumn ttimeofdelivery = new TableColumn("Time of Delivery");
        ttimeofdelivery.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("time_of_delivery"));

        TableColumn tmothername = new TableColumn("Mother's Name");
        tmothername.setCellValueFactory(new PropertyValueFactory("nameofmother"));

        tableview.getColumns().addAll(tbabyid, ttimeofdelivery, tdateofdelivery, tmothername, tfathername, tplaceofbirth, tsignby,
                tpermanet_address, tweight);


    }

    void displayTable(){

        ObservableList olist=FXCollections.observableArrayList();
        try (DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement statement =con.prepareStatement("select * from birth_info")){

            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                BirthDataBeans bd=new BirthDataBeans(rs.getString("baby_id"),rs.getString("tribe_of_father"), rs.getString("place_of_birth"),
                        rs.getInt("weight"), rs.getString("sign"), rs.getString("our_ref"), rs.getString("date_of_delivery"),
                        rs.getString("name_of_mother"), rs.getString("perm_address"), rs.getString("occupation_of_father"),
                        rs.getString("name_of_father"), rs.getString("tribe_of_mother"), rs.getString("local_gov_origin"),
                        rs.getString("time_of_delivery"));


                olist.addAll(bd);
            }

            rs.close();

        } catch (Exception ex)
        {
            ex.printStackTrace();

        }
        sdisplayTable(olist);


    }


}