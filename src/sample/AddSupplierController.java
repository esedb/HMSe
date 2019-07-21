package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Ese on 1/15/2016.
 */
public class AddSupplierController extends VBox {
    @FXML
    private TextField supplierfld;

    @FXML
    private Button savebutton;

    @FXML
    private VBox vboxhead;

    @FXML
    private Button cancelbutton;

    static VBox vbox;
    static Stage stage;


    @FXML
    public void initialize()
    {
        savebutton.setOnAction(e->
        {
            if(supplierfld.getText().length()>0 && supplierfld.getText()=="insert supplier name")
            {
                String supname=supplierfld.getText();
                String sql="INSERT INTO suppliers(supplier_name) values(?)";
                Connection con=null;
                try{
                    DataBase dbase=new DataBase();
                     con=dbase.getConnection();
                    PreparedStatement statement=con.prepareStatement(sql);
                    statement.setString(1, supname);
                    int result=(statement.executeUpdate());
                    if(result==1)
                    {
                        System.out.println("successful");
                    }
                    con.close();
                    stage.close();
                }
                catch(Exception ex)
                {
                    if(con !=null)
                    {
                        try {
                            con.close();
                        }
                        catch(Exception ed)
                        {
                            System.out.println(ed.getMessage());
                            Platform.exit();
                        }
                    }
                    ex.printStackTrace();


                }


            }

        });
    }
    public AddSupplierController() throws IOException {
        FXMLLoader fxmlloader=new FXMLLoader(AddSupplierController.class.getResource("addsupplier.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
}
