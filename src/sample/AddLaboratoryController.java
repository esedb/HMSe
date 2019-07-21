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
public class AddLaboratoryController extends VBox {
    @FXML
    private Button savebutton;

    @FXML
    private Label privatelabtype;

    @FXML
    private TextField labtype;

    @FXML
    private VBox vboxhead;

    @FXML
    private TextField labname;

    @FXML
    private void saveAction(ActionEvent event) {
        String laboratoryname=labname.getText();
        String laboratorytype=labtype.getText();
        AddRadiologicalBean rb=new AddRadiologicalBean();
        rb.setRadiologicalname(laboratoryname);
        rb.setRadiologicaltype(laboratorytype);
        rb.execute();

    }
    @FXML
    private void initialize()
    {


    }
    public AddLaboratoryController() throws IOException
    {
        FXMLLoader fxmlloader=new FXMLLoader(AddLaboratoryController.class.getResource("addlaboratory.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }


}
