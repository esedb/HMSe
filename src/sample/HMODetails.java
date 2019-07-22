package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Ese on 1/11/2016.
 */
public class HMODetails extends VBox {
    @FXML
    private Button savebutton;

    @FXML
    private TextField phonefld;

    @FXML
    private TextField cityfld;

    @FXML
    private TextArea addressfld;

    @FXML
    private TextField pnamefld;

    @FXML
    private HBox tableviewerbox;

    @FXML
    private TextArea pnotefld;

    @FXML
    private TextField hmo_namefld;

    @FXML
    private TextField countryfld;

    @FXML
    private TextField pmobilefld;

    @FXML
    private TextField zipfld;

    @FXML
    private Button clearbutton;

    @FXML
    private VBox accountformvbox;

    @FXML
    private TextField faxfld;

    @FXML
    private TextField emailfld;

    @FXML
    private TextField pemailfld;

    @FXML
    private TextField statefld;

    @FXML
    void saveAction(ActionEvent event) {

        String sql="INSERT INTO hmo_details (hmo_name, address, phone, fax, city, state, country, zip, email, cpname, " +
                "cpmobile,cpemail, cpnote) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection con;
        DataBase dbase=new DataBase();
        try{
            con=dbase.getConnection();
            PreparedStatement prepstatement=con.prepareStatement(sql);
            prepstatement.setString(1, hmo_namefld.getText());
            prepstatement.setString(2, addressfld.getText());
            prepstatement.setString(3, phonefld.getText());
            prepstatement.setString(4, faxfld.getText());
            prepstatement.setString(5, cityfld.getText());
            prepstatement.setString(6, statefld.getText());
            prepstatement.setString(7, countryfld.getText());
            prepstatement.setString(8, zipfld.getText());
            prepstatement.setString(9, emailfld.getText());
            prepstatement.setString(10, pnamefld.getText());
            prepstatement.setString(11, pmobilefld.getText());
            prepstatement.setString(12, pemailfld.getText());
            prepstatement.setString(13, pnotefld.getText());
            int result=prepstatement.executeUpdate();
            if(result ==1 )
            {
                System.out.println("successful");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    @FXML
    void clearAction(ActionEvent event) {

    }

    public HMODetails() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(CompanyDetails.class.getResource("hmodetails.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
}
