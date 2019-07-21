package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Ese on 12/4/2015.
 */
public class AdminProperty {
    Properties property;
    static AdminCreationBeans adminc;
    public Properties getProperties()
    {
        return property;

    }
    public void setProperties()
    {

    }
    public void initialize() {


        try {
            property = new Properties();
            property.put("dbase_username", adminc.getDbusername());
            property.put("dbase_password", adminc.getDbpassword());
            property.put("admin_username", adminc.getAdminusername());
            property.put("Admin_password", adminc.getAdminpassword());
            property.put("Admin_registered", adminc.getAdminregistered());
            property.put("dbase_url", adminc.getDburl());
            property.put("admin_privelege", adminc.getAdminprivelege());
            property.put("admin_firstname", adminc.getAdminfirstname());
            property.put("admin_lastname", adminc.getAdminlastname());

            File file = new File("properties.dat");
            if (!file.exists())
                file.createNewFile();
            System.out.println(file.getAbsolutePath());

            FileOutputStream output = new FileOutputStream("properties.dat");
            property.store(output, "Admin Properties");
            output.flush();
            output.close();
        } catch (IOException e) {
            System.out.println("an error occured trying to write to file");
        }
    }

    public static void main(String[] args)
    {
        AdminProperty adprop=new AdminProperty();
        adprop.initialize();
    }

}
