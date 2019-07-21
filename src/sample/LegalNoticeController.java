package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Ese on 9/3/2017.
 */
public class LegalNoticeController extends VBox {
    @FXML
    private TextArea legal_area;

    @FXML
    void initialize(){
        legal_area.setEditable(false);
    }
    public LegalNoticeController() throws IOException {

        FXMLLoader fxmlloader=new FXMLLoader(LegalNoticeController.class.getResource("legal_notice.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
}
