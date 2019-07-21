package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ese on 12/21/2015.
 */
public class AddSpecialty extends VBox {
    @FXML
    private Button savebutton;

    @FXML
    private Button cancelbutton;

    @FXML
    private Button viewallbutton;

    @FXML
    private TextField specializationfld;

    @FXML
    private ChoiceBox<?> departmetnchoicebox;



    @FXML
    public void initialize()
    {
        ArrayList<String> list=new ArrayList<String>();
        list.add("Bruno");
        list.add("Blaize");
        list.add("Elohor");
        savebutton.setOnAction(e->{
            String save=savebutton.getText();
            String specialtyname=specializationfld.getText();
            save(save, specialtyname);

        });

    }


    public AddSpecialty() throws IOException
    {
        FXMLLoader fxmlloader = new FXMLLoader(AdminDetailsController.class.getResource("add_specialty.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
    public void save(String save, String specialtyname)
    {


    }


}
