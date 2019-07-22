package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Ese on 12/16/2015.
 */
public class AdminCreationBeans {
    public String adminusername;
    public String adminpassword;
    public String dburl;
    public String dbpassword;
    public String dbusername;
    public String adminprivelege;
    public String adminregistered;
    public String adminfirstname;

    public String getAdminlastname() {
        return adminlastname;
    }

    public void setAdminlastname(String adminlastname) {
        this.adminlastname = adminlastname;
    }

    public String getAdminfirstname() {
        return adminfirstname;
    }

    public void setAdminfirstname(String adminfirstname) {
        this.adminfirstname = adminfirstname;
    }

    public String adminlastname;

    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }

    public String getAdminusername() {
        return adminusername;
    }

    public void setAdminusername(String adminusername) {
        this.adminusername = adminusername;
    }

    public String getDburl() {
        return dburl;
    }

    public void setDburl(String dburl) {
        this.dburl = dburl;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }

    public String getDbusername() {
        return dbusername;
    }

    public void setDbusername(String dbusername) {
        this.dbusername = dbusername;
    }

    public String getAdminprivelege() {
        return adminprivelege;
    }

    public void setAdminprivelege(String adminprivelege) {
        this.adminprivelege = adminprivelege;
    }

    public String getAdminregistered() {
        return adminregistered;
    }

    public void setAdminregistered(String adminregistered) {
        this.adminregistered = adminregistered;
    }

    Properties property;
    static AdminCreationBeans adminc;

    public Properties getProperties() {
        return property;

    }

    public void setProperties() {

    }

    public void initialize() {
        String admin_username = "";
        String admin_password = "";


        try {

            File file = new File("properties.dat");
            file.setWritable(true);
            file.setReadable(true);

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


            Properties properties = new Properties();

            FileInputStream input = new FileInputStream(file);
            properties.load(input);
            Set<Object> keys = properties.keySet();
            admin_username = properties.getProperty("admin_username");
            admin_password = properties.getProperty("Admin_password");
            ShowDialog.show("Admin user name is " + admin_username);

            input.close();

        } catch (Exception ex) {
            ShowDialog.show(ex.getMessage());
            ex.printStackTrace();
        }

        if (getAdminusername().equalsIgnoreCase(admin_username) && getAdminpassword().equalsIgnoreCase(admin_password)) {

            try {
                Connection con = DriverManager.getConnection(getDburl(), getDbusername(), getDbpassword());
                if (con != null) {

                    property = new Properties();
                    property.put("dbase_url", getDburl());
                    property.put("dbase_username", getDbusername());
                    property.put("dbase_password", getDbpassword());
                    property.put("admin_username", getAdminusername());
                    property.put("Admin_password", getAdminpassword());



                    File file = new File("properties.dat");
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    boolean read=file.setReadable(true);
                    boolean write = file.setWritable(true);

                    if(read && write) {

                        FileOutputStream output = new FileOutputStream(file);
                        property.store(output, "Admin Properties");
                        output.flush();
                        output.close();
                        ShowDialog.show("Operation Successful");
                    }
                    else{
                        ShowDialog.show("Error writing to file");
                    }

                } else {
                    ShowDialog.show("Either database url, password, or username is incorrect");
                }

            } catch (Exception e) {
                ShowDialog.show("Serious error occured");
            }
        } else {
            ShowDialog.show("Admin username or password is incorrect");
        }
    }
}







