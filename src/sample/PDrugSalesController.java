package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Ese on 1/16/2016.
 */
public class PDrugSalesController extends VBox {
    @FXML
    private HBox tableboxviewer;

    @FXML
    private Button searchallbutton;

    @FXML
    private ChoiceBox<?> drugchoice;

    @FXML
    private Button printbutton;

    @FXML
    private DatePicker datefpicker;

    @FXML
    private Button searchbutton;

    @FXML
    private DatePicker datetpicker;

    @FXML
    void initialize()
    {
        searchbutton.setOnAction(e->{


        });

        searchallbutton.setOnAction(e->{

        });


    }

    public PDrugSalesController () throws IOException {
        FXMLLoader fxmlloader=new FXMLLoader(PDrugSalesController.class.getResource("addstoreitems.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
}
