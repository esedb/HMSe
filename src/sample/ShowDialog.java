package sample;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * Created by Ese on 5/15/2017.
 */
public class ShowDialog {

    static Node node=null;
    static int flag=0;

    public static void show(String message){
        Stage stage=new Stage();
        AlertController ac=new AlertController(message, stage, node);
        Scene scene=new Scene(ac);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        node=null;


    }
    void setFlag(int flag){
        this.flag=flag;
    }

}
