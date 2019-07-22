package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 12/25/2015.
 */
public class OrdinaryTest {
    public static void main(String args[])
    {
        ObservableList tvolist= FXCollections.observableArrayList();

        String url="jdbc:mysql://localhost:3306/will_parry";
        String username="root";
        String password="password@1";
        Connection con;
        String sql="select * from patient_registry where firstname like 'Ro%' or lastname like 'Rose%'" ;
        try{
            con=DriverManager.getConnection(url, username, password);
            PreparedStatement prepstatement=con.prepareStatement(sql);
            ResultSet rs=prepstatement.executeQuery();
            while(rs.next())
            {
                System.out.println("first name is _: "+ rs.getString("firstname"));
                System.out.println("Lastname is : " + rs.getString("lastname"));
                System.out.println("serial is : " + rs.getString("id_sn"));
                System.out.println("phonenumber: " + rs.getString("phoneno"));
                System.out.println("dae of reg " + rs.getString("dateofreg"));
                System.out.println("type of patient :  " + rs.getString("typeofpatient"));


            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
