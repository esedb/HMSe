package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 * Created by Ese on 4/1/2017.
 */
public class RegisterHMOPatientController extends VBox {

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
    private TextField lastnamefld;

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
    private ImageView imgview;

    @FXML
    private Button uploadbutton;

    @FXML
    private ChoiceBox<?> sexfld;

    @FXML
    private ChoiceBox<?> hmochb;

    int anteid=0;
    String absolutepath;

    @FXML
    void onClearAction(ActionEvent event) {

        firstnamefld.setText("");
        lastnamefld.setText("");

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

    @FXML
    void initialize(){

        String sql="select hmo_name from hmo_details";
        ObservableList olistd= FXCollections.observableArrayList();
        olistd.add("None");

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql);){
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                olistd.add(rs.getString("hmo_name"));
            }


        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        if(!olistd.isEmpty() && olistd.size()>1){
            hmochb.setItems(olistd);
        }



        dateofregdpicker.setConverter(DateConverter.convert());
        dateofregdpicker.setValue(LocalDate.now());

        reffeefld.setText("0");

        String sql2 = "SELECT id from costof_service where typeof_service='Antenatal'";
        try(DataBase dbase =new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql);){

            PreparedStatement statement = con.prepareStatement(sql2);
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
                String sql1 = "select * from hmo_patients where firstname like '" + nvalue + "%' or lastname like'" + nvalue + "&'";

                try {
                    con = dbase.getConnection();
                    PreparedStatement prepstatement = con.prepareStatement(sql1);
                    ResultSet rs = prepstatement.executeQuery();
                    while (rs.next()) {

                        tvolist.add(new HMOPatientFactory(rs.getString("firstname"), rs.getString("lastname"),
                                rs.getString("patype"), rs.getString("id_sn"), rs.getString("dateofreg"), rs.getInt("phoneno"), rs.getString("hmo_name")));

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });


        table.setItems(tvolist);
        TableColumn tfirstname = new TableColumn("first name");
        tfirstname.setPrefWidth(180);
        tfirstname.setCellValueFactory(new PropertyValueFactory("firstname"));

        TableColumn tlastname = new TableColumn("last name");
        tlastname.setPrefWidth(180);

        tlastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        TableColumn tpatientcategory = new TableColumn("Patient Category");
        tpatientcategory.setCellValueFactory(new PropertyValueFactory("typeofpatient"));
        tpatientcategory.setPrefWidth(180);

        TableColumn thmo=new TableColumn("HMO Name");
        thmo.setCellValueFactory(new PropertyValueFactory("hmo_name"));
        setPrefWidth(180);

        TableColumn patientserial = new TableColumn("Patient Serial No.");
        patientserial.setCellValueFactory(new PropertyValueFactory("patientserial"));
        patientserial.setPrefWidth(180);
        TableColumn tdateofreg = new TableColumn("Date of Registration");
        tdateofreg.setCellValueFactory(new PropertyValueFactory("dateofreg"));
        tdateofreg.setPrefWidth(180);
        TableColumn tphone = new TableColumn("Phone number");
        tphone.setCellValueFactory(new PropertyValueFactory("phonenumber"));
        tphone.setPrefWidth(180);

        table.getColumns().addAll(tfirstname, tlastname, tpatientcategory, thmo, patientserial, tdateofreg, tphone);
        table.prefWidthProperty().bind(tableviewhbox.widthProperty());
        table.prefHeightProperty().bind(tableviewhbox.prefHeightProperty());


        registerbutton.setOnAction(e ->
        {

            saveAction();


        });
        ObservableList olist = FXCollections.observableArrayList("Male", "Female");
        sexfld.setItems(olist);

        ObservableList olist2 = FXCollections.observableArrayList("Christianity", "Islamic");
        religionfld.setItems(olist2);




        ObservableList olist5 = FXCollections.observableArrayList("A", "B", "AB", "O");
        bloodgroup.setItems(olist5);



    }


    void saveAction() {
        String sql = "INSERT INTO patient_registry (firstname, lastname, address, othername, dateofbirth, pacategory, patype,"
                + "nationality, occupation, addressofkin, dateofreg, nextofkin, religion, placeofwork, sex, id_sn, phoneno, bloodgroup, SPO2, image_url, phoneofkin, others, family_name, payment_status,hmo_name, company_name)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
           }

    public RegisterHMOPatientController() {
        try{
            FXMLLoader fxmlloader=new FXMLLoader(NHMOListController.class.getResource("hmo_patient_registration.fxml"));
            fxmlloader.setRoot(this);
            fxmlloader.setController(this);
            fxmlloader.load();

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

}





}
