package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Ese on 1/7/2016.
 */
public class SupplierDetails extends VBox {

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
    private TextField itemsupplied;

    @FXML
    private HBox tableviewerbox;

    @FXML
    private TextArea pnotefld;

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
    private TextField suppliernamefld;

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
        if(suppliernamefld.getText()== null && suppliernamefld.getText()=="" && phonefld.getText()==null && phonefld.getText()=="" ||
                faxfld.getText()==null && faxfld.getText()=="" || addressfld.getText() == null &&addressfld.getText()=="" ||
                countryfld.getText()==null && countryfld.getText()=="" || statefld.getText() == null && statefld.getText()=="" ||
                cityfld.getText() ==null || cityfld.getText() == "" && itemsupplied.getText()==null || itemsupplied.getText()=="")
        {

        }
        Connection con;
        DataBase dbase=new DataBase();
        try {
            String sql="INSERT INTO supplier_datails (supplier_name, address, phone, fax, country, state, city, zip, email, pname," +
                    "pmobile, pemail, pnote, item_supplied) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            con=dbase.getConnection();
            PreparedStatement prepstatement=con.prepareStatement(sql);
            prepstatement.setString(1, suppliernamefld.getText());
            prepstatement.setString(2, addressfld.getText());
            prepstatement.setString(3, phonefld.getText());
            prepstatement.setString(4, faxfld.getText());
            prepstatement.setString(5, countryfld.getText());
            prepstatement.setString(6, statefld.getText());
            prepstatement.setString(7, cityfld.getText());
            prepstatement.setString(8, zipfld.getText());
            prepstatement.setString(9, emailfld.getText());
            prepstatement.setString(10, pnamefld.getText());
            prepstatement.setString(11, pmobilefld.getText());
            prepstatement.setString(12, pemailfld.getText());
            prepstatement.setString(13, pnotefld.getText());
            prepstatement.setString(14, itemsupplied.getText());

            int result=prepstatement.executeUpdate();
            if(result==1)
            {
                System.out.println("successful");
            }
            con.close();
                   }
        catch(Exception e)
        {
            System.out.println("not successful");
            e.printStackTrace();

        }

    }

    @FXML
    void clearaction(ActionEvent event) {
        suppliernamefld.setText("");
        phonefld.setText("");
        faxfld.setText("");
        addressfld.setText("");
        countryfld.setText("");
        statefld.setText("");
        cityfld.setText("");
        emailfld.setText("");
        zipfld.setText("");
        pnamefld.setText("");
        pemailfld.setText("");
        pnotefld.setText("");
        itemsupplied.setText("");


    }

    public SupplierDetails() throws IOException{
        FXMLLoader fxmlloader=new FXMLLoader(DrugSupplyController.class.getResource("supplierdetails.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
}
