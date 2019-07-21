package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;


import java.io.IOException;

/**
 * Created by Ese on 9/2/2017.
 */
public class ContactUsController extends VBox {

    @FXML
    private TextArea contact_area;

    @FXML
    void initialize(){
        contact_area.setEditable(false);
    }

    public ContactUsController() throws IOException {

        FXMLLoader fxmlloader=new FXMLLoader(ContactUsController.class.getResource("contact_us.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
}
