package sample;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Ese on 12/2/2015.
 */
public class DataBase implements AutoCloseable{

    String url, password, username;
    Properties properties=new Properties();
    public Connection getConnection() throws java.sql.SQLException
    {
        if(DataBaseWrapper .connected==false){
            return null;
        }
        return DataBaseWrapper.getConnection();
    }
    public void close() throws IOException {
        try {
            DataBaseWrapper.getConnection().close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


    }



}