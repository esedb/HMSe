package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 * Created by Ese on 5/16/2017.
 */
public class EditPatientRegistryController extends VBox {

    @FXML
    private ChoiceBox<?> hmogroupchb;

    @FXML
    private TextField addressfld;

    @FXML
    private TextField othernamefld;

    @FXML
    private TextField addofkinfld;

    @FXML
    private DatePicker dobdpicker;

    @FXML
    private TextField othersfld;

    @FXML
    private Button edit_button;

    @FXML
    private TextField ethnicfld;

    @FXML
    private TextField phonenofld;

    @FXML
    private TextField placeofworkfld;

    @FXML
    private TextField nationalityfld;

    @FXML
    private ChoiceBox<?> patienttype;

    @FXML
    private TextField phofkin;

    @FXML
    private TextField agefld;

    @FXML
    private ChoiceBox<?> religionfld;

    @FXML
    private TextField occupationfld;

    @FXML
    private DatePicker dateofregdpicker;

    @FXML
    private TextField firstnamefld;

    @FXML
    private ChoiceBox<?> bloodgroup;

    @FXML
    private TextField spo2;

    @FXML
    private TextField nextofkinfld;

    @FXML
    private ChoiceBox<?> hmochb;

    @FXML
    private ImageView imgview;

    @FXML
    private Button uploadbutton;

    @FXML
    private ChoiceBox<?> sexfld;

    private  String id;
    String absolutepath=null;

    @FXML
    void initialize(){

        agefld.textProperty().addListener((observable, oldvalue, newvalue)->{
            if(newvalue!=null && newvalue.length()>0) {
                System.out.println("newvalue is " + newvalue);

                if (!MyUtil.isInteger(newvalue)) {
                    agefld.setText(oldvalue);
                }
                else {

                }
            }

        });



        ObservableList hmogrouplist = FXCollections.observableArrayList();
        hmogrouplist.add("None");

        String hmogsql="select group_name from hmo_group";
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(hmogsql);){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                hmogrouplist.add(rs.getString("group_name"));
            }


        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        hmogroupchb.setItems(hmogrouplist);
        hmogroupchb.getSelectionModel().selectFirst();

        ObservableList hmolist=FXCollections.observableArrayList();
        hmolist.add("None");

        String hmonamesql="select hmo_name from hmo_details";

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(hmonamesql);){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                hmolist.add(rs.getString("hmo_name"));
            }


        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        hmochb.setItems(hmolist);
        hmochb.getSelectionModel().selectFirst();





        dobdpicker.setValue(LocalDate.now());
        dobdpicker.setConverter(DateConverter.convert());
        dobdpicker.setEditable(false);

        dateofregdpicker.setValue(LocalDate.now());
        dateofregdpicker.setConverter(DateConverter.convert());
        dateofregdpicker.setEditable(false);

        ObservableList olist = FXCollections.observableArrayList("Male", "Female");
        sexfld.setItems(olist);
        sexfld.getSelectionModel().selectFirst();

        ObservableList olist2 = FXCollections.observableArrayList("Christianity", "Islamic", "Others");
        religionfld.setItems(olist2);
        religionfld.getSelectionModel().selectFirst();


        ObservableList olist4 = FXCollections.observableArrayList("Patient", "Antenatal");
        patienttype.setItems(olist4);
        patienttype.getSelectionModel().selectFirst();

        ObservableList olist5 = FXCollections.observableArrayList("A", "B", "AB", "O");
        bloodgroup.setItems(olist5);
        bloodgroup.getSelectionModel().selectFirst();






        patienttype.getSelectionModel().selectedItemProperty().addListener((observableValue, oldvalue, newvalue) -> {

            String newest=newvalue.toString();


            if(newvalue!=null && newvalue!=oldvalue) {
            }

        });
        hmogroupchb.setDisable(true);

        hmochb.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue)->
        {
            if(newvalue.toString().equalsIgnoreCase("None")|| newvalue==null)
            {
                hmogroupchb.setDisable(true);
            }
            else{
                hmogroupchb.setDisable(false);
            }

        });



        uploadbutton.setOnAction(e ->
        {
            HBox mainStage = new HBox();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(

                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),

                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            File selectedFile = fileChooser.showOpenDialog(new Stage());
            if (selectedFile != null) {
                try {
                    Image img = new Image(selectedFile.toURI().toURL().toExternalForm());
                    imgview.setImage(img);

                    absolutepath= selectedFile.toURI().toURL().toExternalForm();


                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }


        });
        edit_button.setOnAction(e->{
            if(firstnamefld.getText().length()<=0 || othernamefld.getText().length()<=0 || dobdpicker.getValue().toString().length()<=0
                    || dobdpicker.getValue()==null || dateofregdpicker.getValue().toString().length()<=0 || dateofregdpicker.getValue()==null
                    || sexfld.getValue().toString().length()<=0 || sexfld.getValue()==null || agefld.getText().length()<=0
                    || phonenofld.getText().length()<=0 || addressfld.getText().length()<=0
                    || patienttype.getValue().toString().length()<=0 || patienttype.getValue()==null )
            {
                ShowDialog.show("Box marked in red must be filled ");
                return;
            }
            String sql="UPDATE will_parry.patient_registry " +
                    "SET " +

                    "firstname = ?, othername=?, address = ?, dateofbirth = ?, patype = ?,nationality = ?, occupation = ?," +
                    "addressofkin = ?, dateofreg = ?, nextofkin = ?, religion = ?,placeofwork = ?, sex = ?, phoneno = ?, bloodgroup = ?," +
                    "SPO2 = ?, image_url = ?, phoneofkin = ?, others = ?, hmo_name = ?, hmo_group = ?, age = ?, reg_by = ?" +
                    "WHERE id_sn = ?;";
            try(DataBase dbase=new DataBase(); Connection con =dbase.getConnection(); PreparedStatement stat=con.prepareStatement(sql)){
                stat.setString(1, firstnamefld.getText());
                stat.setString(2, othernamefld.getText());
                stat.setString (3, addressfld.getText());

                stat.setString(4, dobdpicker.getValue().toString());

                stat.setString(5, patienttype.getValue().toString());
                stat.setString(6, nationalityfld.getText());
                stat.setString(7, occupationfld.getText());
                stat.setString(8, addofkinfld.getText());
                stat.setString(9, dateofregdpicker.getValue().toString());
                stat.setString(10, nextofkinfld.getText());
                stat.setString(11, religionfld.getValue().toString());
                stat.setString(12, placeofworkfld.getText());
                stat.setString(13, sexfld.getValue().toString());

                stat.setString(14, phonenofld.getText());
                stat.setString(15, bloodgroup.getValue().toString());
                stat.setString(16, spo2.getText());
                stat.setString(17, absolutepath);
                stat.setString(18, phofkin.getText());
                stat.setString(19, othersfld.getText());

                stat.setString(20, hmochb.getValue().toString());
                stat.setString(21, hmogroupchb.getValue().toString());
                stat.setInt(22, Integer.valueOf(agefld.getText()));
                stat.setString(23, Controller.sign_by);
                stat.setString(24, id);

                int m=stat.executeUpdate();



                if(m==1){
                    ShowDialog.show("Operation Successful");
                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        });

    }

    public EditPatientRegistryController(String id) throws IOException {
        this.id=id;
        FXMLLoader fxmlloader = new FXMLLoader(PatientRegistry.class.getResource("patientregistryedit.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
}
