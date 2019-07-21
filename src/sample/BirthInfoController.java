package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ese on 2/10/2016.
 */
public class BirthInfoController extends VBox {

    @FXML
    private TextField tribefld;

    @FXML
    private TextField placeofbirthfld;

    @FXML
    private ChoiceBox<?> hourchb;

    @FXML
    private TextField weightfld;

    @FXML
    private TextField signby;

    @FXML
    private Button savebutton;

    @FXML
    private TextField our_reffld;

    @FXML
    private Button updatebutton;

    @FXML
    private DatePicker dateofdelivery;

    @FXML
    private TextField nmotherfld;

    @FXML
    private ChoiceBox<?> secondschb;

    @FXML
    private TextField perm_addressfld;

    @FXML
    private ChoiceBox<?> timeperiodchb;

    @FXML
    private TextField babyidfld;

    @FXML
    private TextField occufatherfld;

    @FXML
    private ChoiceBox<?> minuteschb;

    @FXML
    private TextField fnamefld;

    @FXML
    private TextField trmotherfld;

    @FXML
    private TextField lga_originfld;

    @FXML
    private Button clearbutton;

    @FXML
    private ChoiceBox<?> sexchoice;

    @FXML
    private Button selectpatientbutton;

    @FXML
    private TableView<?> tableview;

    Date fmyDate;

    int weight=0;


    public BirthInfoController() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(PatientRegistry.class.getResource("birthinfo.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }

    @FXML
    public void initialize()
    {
        weightfld.setText("0");

        weightfld.textProperty().addListener((observalbe, oldvalue, newvalue)->{

            if(newvalue!=null && newvalue.length()>0) {
                if (!MyUtil.isInteger(newvalue)) {
                    weightfld.setText(oldvalue);
                }
                else {

                }
            }

        });
        ObservableList timeperiodlist=FXCollections.observableArrayList("AM", "PM");
        timeperiodchb.setItems(timeperiodlist);
        timeperiodchb.getSelectionModel().selectFirst();

        ObservableList hourlist=FXCollections.observableArrayList();
        for(int i=0; i<=12; i++)
        {
            hourlist.add(i);

        }
        hourchb.setItems(hourlist);
        hourchb.getSelectionModel().selectFirst();

        ObservableList minuteslist=FXCollections.observableArrayList();
        for(int i=00; i<60; i++){
            minuteslist.add(i);
        }
        minuteschb.setItems(minuteslist);
        minuteschb.getSelectionModel().selectFirst();

        ObservableList secondslist=FXCollections.observableArrayList();
        for(int i=00; i<60; i++){
            secondslist.add(i);
        }
        secondschb.setItems(secondslist);
        secondschb.getSelectionModel().selectFirst();





        dateofdelivery.setValue(LocalDate.now());

        dateofdelivery.setConverter(DateConverter.convert());
        dateofdelivery.setValue(LocalDate.now());
        dateofdelivery.setEditable(false);


        signby.setText(Controller.sign_by);

        ObservableList olist= FXCollections.observableArrayList("Male", "Female");
        sexchoice.setItems(olist);
        sexchoice.getSelectionModel().selectFirst();

                try {

                    fmyDate=new SimpleDateFormat("yyyy-MM-dd").parse(dateofdelivery.getValue().toString());
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }


        savebutton.setOnAction(e->
        {
            if(placeofbirthfld.getText().length()<=0 || fnamefld.getText().length()<=0 || perm_addressfld.getText().length()<=0
                    || lga_originfld.getText().length()<=0 || tribefld.getText().length()<=0 || occufatherfld.getText().length()<=0
                    || nmotherfld.getText().length()<=0 || weightfld.getText().length()<=0 ){
                ShowDialog.show("Fill values for box marked in red");
                return;
            }
            try{
                weight=Integer.valueOf(weightfld.getText());
                if(weight<=0){
                    ShowDialog.show("Weigth must be greater than 0");
                    return;
                }
            }
            catch(Exception ex)
            {
               ShowDialog.show("An Error Occcured");
                return;
            }
            if (fmyDate != null && weight!=0) {


                try {
                    java.sql.Date fmySqlDate = new java.sql.Date(fmyDate.getTime());

                    DataBase dbase = new DataBase();
                    Connection con = dbase.getConnection();
                    String sql = "INSERT INTO will_parry.birth_info (our_ref, place_of_birth, name_of_father, " +
                            "occupation_of_father, tribe_of_father, local_gov_origin, time_of_delivery, " +
                            "name_of_mother, tribe_of_mother, sex_of_baby, weight, date_of_delivery, sign, perm_address)" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1, our_reffld.getText());
                    statement.setString(2, placeofbirthfld.getText());
                    statement.setString(3, fnamefld.getText());
                    statement.setString(4, occufatherfld.getText());
                    statement.setString(5, tribefld.getText());
                    statement.setString(6, lga_originfld.getText());
                    statement.setString(7, hourchb.getValue().toString()+":"+minuteschb.getValue().toString()+":"+secondschb.getValue().toString()+ " " + timeperiodchb.getValue().toString());
                    statement.setString(8, nmotherfld.getText());
                    statement.setString(9, trmotherfld.getText());
                    statement.setString(10, (String) sexchoice.getSelectionModel().getSelectedItem());
                    statement.setInt(11, weight);
                    statement.setDate(12, fmySqlDate);
                    statement.setString(13, signby.getText());
                    statement.setString(14, perm_addressfld.getText());

                    statement.executeUpdate();



                } catch (Exception ex) {
                    ex.printStackTrace();
                }



            }

        });

        ObservableList birthinfodatalist=FXCollections.observableArrayList();


        String birthsql="select * from birth_info";
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(birthsql); ){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                BirthInfoBeans binfo=new BirthInfoBeans();
                binfo.setName_of_father(rs.getString("name_of_father"));
                binfo.setTribe_of_father(rs.getString("tribe_of_father"));
                binfo.setPlace_of_birth(rs.getString("place_of_birth"));
                binfo.setName_of_mother(rs.getString("name_of_mother"));
                binfo.setTribe_of_mother(rs.getString("tribe_of_mother"));
                binfo.setWeight(rs.getString("weight"));
                binfo.setDate_of_delivery(rs.getString("date_of_delivery"));
                binfo.setLga(rs.getString("local_gov_origin"));
                binfo.setTime_of_delivery(rs.getString("time_of_delivery"));
                binfo.setSex_of_baby(rs.getString("sex_of_baby"));
                binfo.setPerm_address(rs.getString("perm_address"));

                birthinfodatalist.add(binfo);

            }
        }
        catch(Exception ex){
            ShowDialog.show("An Error Occured");
            ex.printStackTrace();
        }

        if(birthinfodatalist!=null && !birthinfodatalist.isEmpty()){
            tableview.setItems(birthinfodatalist);

           System.out.println("size of list is " +  birthinfodatalist.size());

            TableColumn tfathername=new TableColumn("Name Of father");
            tfathername.setCellValueFactory(new PropertyValueFactory("name_of_father"));

            TableColumn tmothername=new TableColumn("Name Of Mother");
            tmothername.setCellValueFactory(new PropertyValueFactory("name_of_mother"));

            TableColumn tplaceofbirth=new TableColumn("Place Of Birth");
            tplaceofbirth.setCellValueFactory(new PropertyValueFactory("place_of_birth"));

            TableColumn tdateofbirth=new TableColumn("Date Of Birth");
            tdateofbirth.setCellValueFactory(new PropertyValueFactory("date_of_delivery"));

            TableColumn ttimeofbirth=new TableColumn("Time Of Birth");
            ttimeofbirth.setCellValueFactory(new PropertyValueFactory("time_of_delivery"));

            TableColumn tweight=new TableColumn("Baby Weight");
            tweight.setCellValueFactory(new PropertyValueFactory("weight"));

            TableColumn tlga=new TableColumn("LGA");
            tlga.setCellValueFactory(new PropertyValueFactory("lga"));

            tableview.getColumns().addAll(tfathername, tmothername, tplaceofbirth, tdateofbirth, ttimeofbirth, tweight, tlga);

        }

        updatebutton.setOnAction(e->{

            String babyid=babyidfld.getText();
            int id=0;
            if(babyid.length()>0){

                try {
                    id = Integer.valueOf(babyid);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

                if(id>0){
                    String sql="UPDATE birth_info SET  place_of_birth=?, name_of_father=?, occupation_of_father=?, tribe_of_father=?, local_gov_origin=?, " +
                            "name_of_mother=?, tribe_of_mother=?, sex_of_baby=?, weight=?, perm_address=?" +
                            " where baby_id=?";
                    try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
                        stmt.setString(1, placeofbirthfld.getText());
                        stmt.setString(2, fnamefld.getText());
                        stmt.setString(3, occufatherfld.getText());
                        stmt.setString(4, tribefld.getText());
                        stmt.setString(5, lga_originfld.getText());
                        stmt.setString(6, nmotherfld.getText());
                        stmt.setString(7, trmotherfld.getText());
                        stmt.setString(8, sexchoice.getValue().toString());
                        stmt.setString(9, weightfld.getText());
                        stmt.setString(10, perm_addressfld.getText());
                        stmt.setInt(11, id);
                        stmt.executeUpdate();

                    }

                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }




            }



        });

        signby.setEditable(false);

        clearbutton.setOnAction(e->{

            fnamefld.setText("");
            tribefld.setText("");
            nmotherfld.setText("");
            trmotherfld.setText("");
            our_reffld.setText("");
            weightfld.setText("");
            lga_originfld.setText("");
            occufatherfld.setText("");
            placeofbirthfld.setText("");
            our_reffld.setText("");

            System.out.println("value of . is " + Integer.valueOf('.'));

        });



        /*


        selectpatientbutton.setOnAction(e->{

            String baby_id=null;

            List<String> bilist=new ArrayList<>();
            List<BirthInfoBeans> orlist=new ArrayList<>();

            BirthInfoBeans binfobeans=(BirthInfoBeans)tableview.getFocusModel().getFocusedItem();
            if(binfobeans!=null){

                String babysql="select * from birth_info where name_of_father=?";

                try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(babysql); ) {
                    stmt.setString(1, binfobeans.getName_of_father());
                    ResultSet rs = stmt.executeQuery();
                    while(rs.next()){

                        BirthInfoBeans binfo=new BirthInfoBeans();
                        binfo.setName_of_father(rs.getString("name_of_father"));
                        bilist.add(rs.getString("baby_id"));
                    }

                }
                catch(Exception ex){

                    ex.printStackTrace();

                }


            }

            if(!bilist.isEmpty()){
                for(String bid : bilist){
                    baby_id=bid;
                }
            }

            if(baby_id!=null){
                try {

                    System.out.println("baby id " + baby_id);

                    ExamDiagController ec = new ExamDiagController(baby_id);
                    Scene scene=new Scene(ec);
                    Stage stage=new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            }

        });

*/





    }


}
