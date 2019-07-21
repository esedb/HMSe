package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Ese on 1/17/2016.
 */
public class PDrugConsumption extends VBox {
    @FXML
    private Button savebutton;

    @FXML
    private ChoiceBox<?> cdrugconchoice;

    @FXML
    private TextField drugfld;

    @FXML
    private TextField amountfld;

    @FXML
    private TextField titlefld;

    @FXML
    private TextField totalfld;

    @FXML
    private TextField namefld;

    @FXML
    private ChoiceBox<?> conumedchoice;

    @FXML
    private Button clearbutton;

    @FXML
    private TextField signfld;

    @FXML
    private TextField grandtotalfld;

    @FXML
    private ChoiceBox<?> selectchoice;

    @FXML
    private Button refreshbutton;

    @FXML
    private TextField quantityfld;

    @FXML
    private ChoiceBox<?> mconchoice;

    @FXML
    void initialze()
    {

    }
    public PDrugConsumption() throws IOException
    {
        FXMLLoader fxmlloader=new FXMLLoader(PDrugConsumption.class.getResource("pdrugconsumption.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }

}
