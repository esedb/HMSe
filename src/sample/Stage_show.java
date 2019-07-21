package sample;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Ese on 7/5/2016.
 */
public class Stage_show {
    static void show(VBox vbox)
    {
        Stage stage=new Stage();
        Scene scene=new Scene(vbox);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
