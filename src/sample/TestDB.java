package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 3/9/2016.
 */
public class TestDB {

    public static void main(String args[])
    {
        int total=0;
        String name=null;
        try {
            DataBase dbase = new DataBase();
            Connection con = dbase.getConnection();
            PreparedStatement statement=con.prepareStatement("select count(*) from patient_registry");
            ResultSet rs=statement.executeQuery();
            while (rs.next())
            {
                 total =rs.getInt(1);

            }
        }
        catch(Exception ex){
        ex.printStackTrace();


    }
        System.out.println("total is " + total + " " + name);

        try {
            DataBase dbase = new DataBase();
            Connection con = dbase.getConnection();
            PreparedStatement statement=con.prepareStatement("select  firstname from patient_registry");
            ResultSet rs=statement.executeQuery();
            while (rs.next())
            {


                name=rs.getString(1);
            }
        }

        catch(Exception ex){
            ex.printStackTrace();


        }
        System.out.println(name);
    }
}
