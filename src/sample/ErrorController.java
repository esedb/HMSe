package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.IOException;



/**
 * Created by Ese on 7/2/2016.
 */
public class ErrorController extends VBox {
    @FXML
    private TextArea error_area;
    String message;
    public ErrorController(String message)  {
        this.message=message;

        try {
            FXMLLoader fxmlloader = new FXMLLoader(ErrorController.class.getResource("error_dialog.fxml"));
            fxmlloader.setController(this);
            fxmlloader.setRoot(this);
            fxmlloader.load();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

      }

    public void initialize()
    {
        error_area.setText(message);
        error_area.setEditable(false);
    }

}
