package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Ese on 1/14/2016.
 * LabTestType is created to add Laboratory test type to serve
 * as a helper to AddLabTest.
 */
public class LabTestType extends VBox {
    @FXML
    private Button savebutton;

    @FXML
    private VBox vboxhead;

    @FXML
    private Button cancelbutton;

    @FXML
    private TextField tab_testfld;

    static VBox vbox;
    static Stage stage;

    @FXML
    void initialize()
    {

        savebutton.setOnAction(e->
        {
            try{
                String sql="INSERT INTO lab_test_type (test_type) values(?)";
                DataBase dbase=new DataBase();
                Connection con=dbase.getConnection();
                PreparedStatement statement=con.prepareStatement(sql);
                statement.setString(1, tab_testfld.getText());
                int result=statement.executeUpdate();
                if(result==1)
                {
                    System.out.println("Successful");
                }
                stage.close();
                vbox.setDisable(false);

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                vbox.setDisable(false);
            }
        });
        cancelbutton.setOnAction(e->
        {
            stage.close();
            vbox.setDisable(false);


        });

    }


    public LabTestType() throws IOException
    {
        FXMLLoader fxmlloader=new FXMLLoader(LabTestType.class.getResource("labtesttype.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();


    }

}
