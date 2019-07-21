package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 1/15/2016.
 */
public class AddItemSuppliedController extends VBox {
    private Stage stage;
    @FXML
    private Button savebutton;

    @FXML
    private VBox vboxhead;

    @FXML
    private Button cancelbutton;

    @FXML
    private TextField itemsuppliedfld;

    @FXML
    void initialize()
    {

        String itemsupplied=itemsuppliedfld.getText();

        savebutton.setOnAction(e->{

            if(itemsuppliedfld.getText().length()>0)
            {
                String sql="INSERT INTO resource (items) values(?)";
                try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection()){
                    PreparedStatement stmt=con.prepareStatement("Select items from resource");
                    ResultSet rn=stmt.executeQuery();
                    if(rn.next()){
                        ShowDialog.show("Item already present in database");
                        return;
                    }
                    else {
                        PreparedStatement statement = con.prepareStatement(sql);
                        statement.setString(1, itemsuppliedfld.getText());
                        int result = statement.executeUpdate();
                        if (result == 1) {
                            ShowDialog.show("Operation successful");
                        }
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            else{
                ShowDialog.show("Boxes marked in red must be filled with values");
            }
        });
        cancelbutton.setOnAction(e->{
            stage.close();
                });
    }
    public AddItemSuppliedController(Stage stage) throws IOException
    {
        this.stage=stage;
        FXMLLoader fxmlloader=new FXMLLoader(AddItemSuppliedController.class.getResource("itemsupplied.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }

}
