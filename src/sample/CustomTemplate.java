package sample;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.DrugCategory;

import java.io.IOException;

/**
 * Created by Ese on 6/26/2016.
 */
public class CustomTemplate extends VBox {
    @FXML
    private ScrollPane scroll_pane;

    @FXML
    private Label service_label;

    @FXML
    private HBox hbox_container;

    @FXML
    void initialize(){
        ObservableList<Node> list=hbox_container.getChildren();
        for(Node node: list){

            TextField fld=(TextField) node.lookup("#textfield");
            if(fld!=null){

            }

            }
    }

    public CustomTemplate()  {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(DrugCategory.class.getResource("temp_container.fxml"));
            fxmlloader.setRoot(this);
            fxmlloader.setController(this);
            fxmlloader.load();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

    }
    public GridPane setGridPane(GridPane gridpane)
    {
        scroll_pane.setContent(gridpane);


        return gridpane;
    }

    public void setLabel(String text)
    {
        service_label.setText(text);

    }

}
