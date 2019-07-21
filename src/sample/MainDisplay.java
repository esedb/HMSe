package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Ese on 12/9/2015.
 */
public class MainDisplay{
    Stage stage;
    public MainDisplay(Stage stage)
    {
        this.stage=stage;
    }
    public MainDisplay() throws IOException
    {
        FXMLLoader fxmlloader=new FXMLLoader(MainDisplay.class.getResource("Sample.fxml"));
        fxmlloader.setRoot(this);
         fxmlloader.setController(this);
        fxmlloader.load();
    }
    public TabPane getTabpane()
    {
        Tab tab=new Tab("Patient List");
        HBox hbox1=new HBox();
        tab.setContent(hbox1);

        TabPane tabpane=new TabPane();
        tabpane.getTabs().add(tab);

        return tabpane;
    }


}
