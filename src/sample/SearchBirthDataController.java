package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Ese on 7/6/2016.
 */
public class SearchBirthDataController extends VBox {

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


    public SearchBirthDataController() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(PatientRegistry.class.getResource("birth_data.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }

    @FXML
    public void initialize()
    {


    }
}
