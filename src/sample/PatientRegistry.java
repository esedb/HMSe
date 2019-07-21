package sample;


import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 * Created by Ese on 12/24/2015.
 */
public class PatientRegistry extends VBox {

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
    private Button searchbutton;

    @FXML
    private TextField searchfld;

    @FXML
    private HBox tableviewhbox;

    @FXML
    private TextField ethnicfld;

    @FXML
    private TextField phonenofld;

    @FXML
    private Button clearbutton;

    @FXML
    private TextField placeofworkfld;

    @FXML
    private TextField nationalityfld;

    @FXML
    private ChoiceBox<?> patienttype;

    @FXML
    private HBox searchbox;

    @FXML
    private TableView<?> table;

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
    private Button registerbutton;

    @FXML
    private TextField firstnamefld;

    @FXML
    private ChoiceBox<?> bloodgroup;

    @FXML
    private TextField reffeefld;

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

    @FXML
    private Button edit_button;



    String absolutepath=null;
    Connection con=null;
    DataBase dbase=null;
    int anteid=0;


    public PatientRegistry()throws IOException
    {

        FXMLLoader fxmlloader = new FXMLLoader(PatientRegistry.class.getResource("patientregistry.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
    @FXML
    private void initialize() {

        dateofregdpicker.setEditable(false);

        reffeefld.textProperty().addListener((observable, oldvalue, newvalue)->{

            if(newvalue!=null && newvalue.length()>0) {

                if (!MyUtil.isInteger(newvalue)) {
                    reffeefld.setText(oldvalue);
                }
                else {

                }
            }

        });


        dobdpicker.setValue(LocalDate.now());
        dobdpicker.setConverter(DateConverter.convert());
        dobdpicker.setEditable(false);


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



        dateofregdpicker.setConverter(DateConverter.convert());
        dateofregdpicker.setValue(LocalDate.now());

        reffeefld.setText("0");


        try {
            dbase = new DataBase();
            con = dbase.getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            String sql = "SELECT id from costof_service where typeof_service='Antenatal'";
            PreparedStatement statement = this.con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                anteid = rs.getInt("id");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        uploadbutton.setDisable(true);

        firstnamefld.textProperty().addListener((observable, oldvalue, newvalue) ->
        {
            if (newvalue.length() > 0) {
                uploadbutton.setDisable(false);


            } else {
                uploadbutton.setDisable(true);

            }
        });

        Stage nodeim = new Stage();

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
        TableView tb;
        ObservableList tvolist = FXCollections.observableArrayList();


        searchfld.textProperty().addListener((or, olvalue, nvalue) ->
        {


            if (nvalue != null && nvalue.length() > 0) {
                table.getItems().clear();

                DataBase dbase = new DataBase();
                Connection con;
                String sql = "select * from patient_registry where firstname like '" + nvalue + "%' or othername like'" + nvalue + "&'";

                try {
                    con = dbase.getConnection();
                    PreparedStatement prepstatement = con.prepareStatement(sql);
                    ResultSet rs = prepstatement.executeQuery();
                    while (rs.next()) {

                        tvolist.add(new PatientRegistryFactory(rs.getString("firstname"), rs.getString("othername"),
                                rs.getString("patype"), rs.getString("id_sn"), rs.getString("dateofreg"), rs.getInt("phoneno")));

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });


        table.setItems(tvolist);
        TableColumn tfirstname = new TableColumn("Surname");
        tfirstname.setPrefWidth(180);
        tfirstname.setCellValueFactory(new PropertyValueFactory("firstname"));

        TableColumn tlastname = new TableColumn("Othernames");
        tlastname.setPrefWidth(180);
        tlastname.setCellValueFactory(new PropertyValueFactory("lastname"));

        TableColumn tpatientcategory = new TableColumn("Patient Category");
        tpatientcategory.setCellValueFactory(new PropertyValueFactory("typeofpatient"));
        tpatientcategory.setPrefWidth(180);

        TableColumn patientserial = new TableColumn("Patient Serial No.");
        patientserial.setCellValueFactory(new PropertyValueFactory("patientserial"));
        patientserial.setPrefWidth(180);
        TableColumn tdateofreg = new TableColumn("Date of Registration");
        tdateofreg.setCellValueFactory(new PropertyValueFactory("dateofreg"));
        tdateofreg.setPrefWidth(180);
        TableColumn tphone = new TableColumn("Phone number");
        tphone.setCellValueFactory(new PropertyValueFactory("phonenumber"));
        tphone.setPrefWidth(180);

        table.getColumns().addAll(tfirstname, tlastname, tpatientcategory, patientserial, tdateofreg, tphone);
        table.prefWidthProperty().bind(tableviewhbox.widthProperty());
        table.prefHeightProperty().bind(tableviewhbox.prefHeightProperty());


        registerbutton.setOnAction(e ->
        {

            saveAction();


        });
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

        edit_button.setOnAction(e->{
            PatientRegistryFactory pr=(PatientRegistryFactory) table.getFocusModel().getFocusedItem();
            if(pr!=null){
                String id=pr.getPatientserial();
                try {
                    EditPatientRegistryController ec=new EditPatientRegistryController(id);
                    ShowDialog.node=ec;
                    ShowDialog.show("How you sure use want to edit Patient records with serial number" + " " + pr.getPatientserial());
                    ShowDialog sh=new ShowDialog();

                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            else{
                ShowDialog.show("Select Patient From Table First");
            }


        });






    }


    public void saveAction()
    {
        if(reffeefld.getText().length()<=0){
            ShowDialog.show("Registration fee must be greater or equal to 0");
            return;
        }

        if(firstnamefld.getText().length()<=0 || othernamefld.getText().length()<=0 || dobdpicker.getValue().toString().length()<=0
                || dobdpicker.getValue()==null || dateofregdpicker.getValue().toString().length()<=0 || dateofregdpicker.getValue()==null
                || sexfld.getValue().toString().length()<=0 || sexfld.getValue()==null || agefld.getText().length()<=0
                || phonenofld.getText().length()<=0 || addressfld.getText().length()<=0
                || patienttype.getValue().toString().length()<=0 || patienttype.getValue()==null )
        {
            ShowDialog.show("Box marked in red must be filled ");
            return;
        }
        if (absolutepath==null){
            ShowDialog.show("Image must be selected");
            return;
        }
        if(absolutepath!=null && absolutepath.length()>0) {
            try {

                PatientRegistryBean ps = new PatientRegistryBean();

                ps.setFirstname(firstnamefld.getText());
                ps.setAddress(addressfld.getText());
                ps.setOthernames(othernamefld.getText());
                ps.setDateofbirth(dobdpicker.getValue().toString());

                ps.setPatienttype(patienttype.getValue().toString());
                ps.setNationality(nationalityfld.getText());
                ps.setOccupation(occupationfld.getText());
                ps.setAddressofkin(addofkinfld.getText());
                ps.setDateofreg(dateofregdpicker.getValue().toString());
                ps.setNextofkin(nextofkinfld.getText());
                ps.setReligion(religionfld.getValue().toString());
                ps.setPlaceofwork(placeofworkfld.getText());
                ps.setSex(sexfld.getValue().toString());
                ps.setPhonenumber(phonenofld.getText());
                ps.setBloodgroup(bloodgroup.getValue().toString());
                ps.setSPO2(spo2.getText());
                ps.setImageurl(absolutepath);
                ps.setKinphonenumber(phofkin.getText());
                ps.setOther(othersfld.getText());
                ps.setRegFee(reffeefld.getText());
                ps.setHmo_group(hmogroupchb.getValue().toString());
                ps.setHmo_name(hmochb.getValue().toString());
                ps.setAge(Integer.valueOf(agefld.getText()));
                ps.setSign_by(Controller.sign_by);

                ps.execute();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else{
            ShowDialog.show("Image must be selected");
        }

    }

    @FXML
    void onClearAction(ActionEvent event) {


        firstnamefld.setText("");
        addofkinfld.setText("");
        nationalityfld.setText("");
        occupationfld.setText("");
        phonenofld.setText("");
        placeofworkfld.setText("");
        othersfld.setText("");
        nextofkinfld.setText("");
        spo2.setText("");
        othernamefld.setText("");
        ethnicfld.setText("");

    }

    @FXML
    void registerAction(ActionEvent event) {


    }



}


