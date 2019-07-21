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
import java.sql.ResultSet;

/**
 * Created by Ese on 1/11/2016.
 */
public class CompanyDetails extends VBox {


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
    private TextField countryfld;

    @FXML
    private TextField pmobilefld;

    @FXML
    private TextField zipfld;


    @FXML
    private TextField company_namefld;

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
    private Button clearbutton;

    @FXML
    private Button savebutton;



    @FXML
    void saveAction(ActionEvent event) {

        if(statefld.getText().length()<=0 ||  company_namefld.getText().length()<=0 || pnamefld.getText().length()<=0
                || pmobilefld.getText().length()<=0 || countryfld.getText().length()<=0
                || addressfld.getText().length()<=0 || cityfld.getText().length()<=0 || phonefld.getText().length()<=0){
            ShowDialog.show("Input value in all box marked in red to save succesfull");
            return;
        }

        try(DataBase dbase=new DataBase(); Connection con =dbase.getConnection(); PreparedStatement stmt =con.prepareStatement("select * from company_details")){
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                String updatesql="update company_details set company_name=?, address=?, phone=?, fax=?, city=?, state=?, country=?, zip=?, email=?, cpname=?," +
                        "cpmobile=?, cpemail=?, cpnote=?";
                try {
                    PreparedStatement prepstatement = con.prepareStatement(updatesql);
                    prepstatement.setString(1, company_namefld.getText());
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
                    int result = prepstatement.executeUpdate();
                    if (result == 1) {
                        ShowDialog.show("Company Details Updated");
                    }
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }

            }
            else{
                String sql="INSERT INTO company_details (company_name, address, phone, fax, city, state, country, zip, email, cpname, " +
                        "cpmobile,cpemail, cpnote) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

                try{
                    PreparedStatement prepstatement=con.prepareStatement(sql);

                    prepstatement.setString(1, company_namefld.getText());
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
                        ShowDialog.show("Company Details Created");
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }


            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }


    }

    @FXML
    void clearAction(ActionEvent event) {

    }
    public CompanyDetails() throws IOException{


        FXMLLoader fxmlloader = new FXMLLoader(CompanyDetails.class.getResource("companydetails.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }

}
