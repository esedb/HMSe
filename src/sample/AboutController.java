package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Ese on 9/3/2017.
 */
public class AboutController extends VBox {

    @FXML
    void initialize(){

    }

    public AboutController() throws IOException {

        FXMLLoader fxmlloader=new FXMLLoader(AboutController.class.getResource("about_s.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }


}
