package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Ese on 12/16/2015.
 */
public class AdminDetailsController extends VBox {
    AdminCreationBeans sac;
    @FXML
    private TextField adminusernamefld;

    @FXML
    private Button cmbutton;


    @FXML
    private TextField dbaseurlfld;

    @FXML
    private TextField dbaserootfld;

    @FXML
    private PasswordField adminpasswordfld;

    @FXML
    private Button creatadmin;

    @FXML
    private PasswordField dbasepasswordfld;
    public AdminDetailsController() throws IOException {

        FXMLLoader fxmlloader = new FXMLLoader(AdminDetailsController.class.getResource("admindetailsfxml.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
    @FXML
    public void initialize()
    {
        creatadmin.setOnAction(e->
        {
            creatAdmin();
        });

        cmbutton.setOnAction(e->{

            try {
                ChangeAdminDetailsController cac=new ChangeAdminDetailsController();
                Stage stage = new Stage();
                Scene scene = new Scene(cac);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.show();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        });

    }


    @FXML
    void adminusernamefld(ActionEvent event) {

    }

     private void creatAdmin() {
        // create Admin details


        String adminpass = adminpasswordfld.getText();
        String adminuser = adminusernamefld.getText();
        String dbroot = dbaserootfld.getText();
        String dbpass = dbasepasswordfld.getText();
        String dburl = dbaseurlfld.getText();

         if(adminpass.length()<=0 || adminuser.length()<=0 || dbroot.length()<=0 || dbpass.length()<=0 || dburl.length()<=0){
             ShowDialog.show("All boxes marked in red must be filled with values");
             return;
         }

        AdminCreationBeans admincb=new AdminCreationBeans();

        admincb.setDburl(dburl);
        admincb.setDbpassword(dbpass);
        admincb.setAdminpassword(adminpass);
        admincb.setAdminusername(adminuser);
        admincb.setDbusername(dbroot);
        admincb.initialize();




    }




}
