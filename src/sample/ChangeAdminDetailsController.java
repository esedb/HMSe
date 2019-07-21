package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Ese on 6/26/2017.
 */
public class ChangeAdminDetailsController extends VBox {

    @FXML
    private Button savebutton;

    @FXML
    private TextField oldusernamefld;

    @FXML
    private TextField newusernamefld;

    @FXML
    private PasswordField oldpasswordfld;

    @FXML
    private PasswordField newpasswordfld;

    @FXML
    void initialize(){
        savebutton.setOnAction(e->{

            if(oldusernamefld.getText().length()<=0 || oldpasswordfld.getText().length()<=0
                    || newusernamefld.getText().length()<=0 || newpasswordfld.getText().length()<=0){
                ShowDialog.show("All boxed marked in red must be filled with values");
                return;
            }

            String oldusername=oldusernamefld.getText();
            String oldpassword=oldpasswordfld.getText();
            String newusername=newusernamefld.getText();
            String newpassword=newpasswordfld.getText();

            String admin_username=null;
            String admin_password=null;
            String dbase_url=null;
            String dbase_username=null;
            String dbase_password=null;
            try {
                Properties properties = new Properties();

                File file=new File("properties.dat");
                if(!file.exists()){
                    file.createNewFile();
                    file.setReadable(true);
                    file.setWritable(true);

                    Properties property = new Properties();
                    property.put("dbase_url", "jdbc:mysql://localhost:3306/hmse?");
                    property.put("dbase_username", "root");
                    property.put("dbase_password", "password@1");
                    property.put("admin_username", "Administrator");
                    property.put("Admin_password", "password@1");

                    FileOutputStream output = new FileOutputStream(file);
                    property.store(output, "Admin Properties");
                    output.flush();
                    ShowDialog.show("Personal test");
                    output.close();
                }

                if(file.exists()) {

                    FileInputStream input = new FileInputStream(file);
                    properties.load(input);
                    Set<Object> keys = properties.keySet();
                    admin_username = properties.getProperty("admin_username");
                    admin_password = properties.getProperty("Admin_password");
                    dbase_url = properties.getProperty("dbase_url");
                    dbase_username = properties.getProperty("dbase_username");
                    dbase_password = properties.getProperty("dbase_password");
                }
                else{
                    ShowDialog.show("Error reading form file");
                }

            }
            catch(Exception ex){
                ShowDialog.show(ex.getMessage());

            }
            if(oldusername.equalsIgnoreCase(admin_username) && oldpassword.equalsIgnoreCase(admin_password)) {
                try {
                    Connection con = DriverManager.getConnection(dbase_url, dbase_username, dbase_password);
                    Properties property;
                    if (con != null) {

                        Path file = Paths.get("properties.dat");
                        AclFileAttributeView aclAttr = Files.getFileAttributeView(file, AclFileAttributeView.class);
                        System.out.println(aclAttr.getOwner());
                        for(AclEntry aclEntry : aclAttr.getAcl()){
                            System.out.println(aclEntry);
                        }
                        System.out.println();

                        UserPrincipalLookupService upls = file.getFileSystem().getUserPrincipalLookupService();
                        UserPrincipal user = upls.lookupPrincipalByName(System.getProperty("user.name"));
                        AclEntry.Builder builder = AclEntry.newBuilder();
                        builder.setPermissions( EnumSet.of(AclEntryPermission.READ_DATA, AclEntryPermission.EXECUTE,
                                AclEntryPermission.READ_ACL, AclEntryPermission.READ_ATTRIBUTES, AclEntryPermission.READ_NAMED_ATTRS,
                                AclEntryPermission.WRITE_ACL, AclEntryPermission.DELETE
                        ));
                        builder.setPrincipal(user);
                        builder.setType(AclEntryType.ALLOW);
                        aclAttr.setAcl(Collections.singletonList(builder.build()));

                        property = new Properties();
                        property.put("dbase_url", dbase_url);
                        property.put("dbase_username", dbase_username);
                        property.put("dbase_password", dbase_password);
                        property.put("admin_username", newusername);
                        property.put("Admin_password", newpassword);


                        File filed=file.toFile();
                        if(!filed.exists()){
                            filed.createNewFile();
                        }

                        FileOutputStream output = new FileOutputStream(filed);
                        property.store(output, "Admin Properties");
                        output.flush();
                        output.close();
                        ShowDialog.show("Admin Details changed successfully");
                    } else {
                        ShowDialog.show("Database credentials is incorrect");
                    }

                } catch (Exception n) {
                    n.printStackTrace();
                    ShowDialog.show("Serious error occured");
                }
            }
            else{
                ShowDialog.show("Old Admin username or password is incorrect or empty");
            }


        });

    }

    public ChangeAdminDetailsController() throws IOException {

        FXMLLoader fxmlloader = new FXMLLoader(AdminDetailsController.class.getResource("change_admin_details.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }


}
