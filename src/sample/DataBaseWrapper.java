package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Ese on 9/5/2017.
 */
public class DataBaseWrapper {
    static Connection con;
    Properties properties=new Properties();
    static String password;
    static String url;
    static String username;
    static boolean connected=false;
    void setConnection(){


        try {


            File file=new File("properties.dat");
            boolean read=file.setReadable(true);
            if(!read){
                ShowDialog.show("Error reading from file");
                return;
            }
            if(!file.exists()){
                file.createNewFile();
                file.setReadable(true);
                file.setWritable(true);

                Properties property = new Properties();
                property.put("dbase_url", "jdbc:mysql://localhost:3306/hmse?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
                property.put("dbase_username", "root");
                property.put("dbase_password", "password@1");
                property.put("admin_username", "Administrator");
                property.put("Admin_password", "password@1");

                FileOutputStream output = new FileOutputStream(file);
                property.store(output, "Admin Properties");
                output.flush();

                output.close();
            }
            FileInputStream input = new FileInputStream(file);
            properties.load(input);
            Set<Object> keys = properties.keySet();
            password=properties.getProperty("dbase_password");
            username=properties.getProperty("dbase_username");
            url=properties.getProperty("dbase_url");
            connected=true;
            input.close();


        } catch (Exception e) {

            e.printStackTrace();

        }
    }
    static Connection getConnection(){
        try{

        con= DriverManager.getConnection( url,username, password);
        return con;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return con;
    }
}
