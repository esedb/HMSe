package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * Created by Ese on 7/5/2016.
 */
public class AlertController extends VBox {

    private  Node node;
    private  Stage stage;
    @FXML
    private TextArea alert_text;

    @FXML
    private Button ok_button;

    @FXML
    private Button cancel_button;

    String message;

    @FXML
    void initialize(){

        alert_text.setText(message);
        alert_text.setEditable(false);
        ok_button.setOnAction(e->{
            if(node==null){
                ShowDialog.flag=1;
                stage.close();
            }
            else{
                if(node instanceof VBox){
                    VBox vbox=(VBox) node;
                    Stage stage =new Stage();
                    Scene scene=new Scene(vbox);
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.show();
                    ShowDialog.node=null;
                    this.stage.close();





                }

            }
        });

        cancel_button.setOnAction(e->{
            ShowDialog sh=new ShowDialog();
            sh.setFlag(0);
            stage.close();
        });

    }





    public AlertController(String message, Stage stage, Node node) {
        this.stage=stage;
        this.node =node;
        if(message==null){
            message="An Error occured";
        }
        this.message=message;
        try {

            FXMLLoader fxmlloader = new FXMLLoader(ViewNonAntenatalController.class.getResource("alert_dialog.fxml"));
            fxmlloader.setRoot(this);
            fxmlloader.setController(this);
            fxmlloader.load();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }


    }


}
