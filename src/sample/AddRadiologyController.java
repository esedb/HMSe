package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;


/**
 * Created by Ese on 12/30/2015.
 */
public class AddRadiologyController extends VBox {
    @FXML
    private TextField radname;

    @FXML
    private Button savebutton;

    @FXML
    private Label privatelabtype;

    @FXML
    private VBox vboxhead;

    @FXML
    private TextField radtype;


    public AddRadiologyController()throws IOException {
        FXMLLoader fxmlloader=new FXMLLoader(AddRadiologyController.class.getResource("addradiology.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
    @FXML
    public void initialize()
    {

    }

    @FXML
    void saveAction(ActionEvent event) {
        String radiologyname=radname.getText();
        String radiologytype=radtype.getText();
        AddRadiologicalBean rb=new AddRadiologicalBean();
        rb.setRadiologicalname(radiologyname);
        rb.setRadiologicaltype(radiologytype);
        rb.execute();

    }

}
