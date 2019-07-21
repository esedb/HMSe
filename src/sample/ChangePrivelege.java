package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 12/4/2015.
 */
public class ChangePrivelege {
    SimpleStringProperty firstname=new SimpleStringProperty();
    SimpleStringProperty lastname=new SimpleStringProperty();
    SimpleIntegerProperty privelege=new SimpleIntegerProperty();

    public ChangePrivelege(String firstname, String lastname, int status) {
        this.firstname.set(firstname);
        this.lastname.set(lastname);
        this.privelege.set(status);
       new ChangePrivelege();
    }
    public ChangePrivelege()
    {}
    public String getFirstname()
    {
        return firstname.get();

    }
    public String getLastname()
    {
        return lastname.get();
    }
    public int getPrivelege()
    {
        return privelege.get();
    }
    public String getUsername()
    {
        return (getFirstname() + " " + getLastname());

    }

    public void execute()
    {

        try{
            String statement="UPDATE account_form SET privelege=? where username=?";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement prepStatement=con.prepareStatement(statement);
            prepStatement.setInt(1, getPrivelege());
            prepStatement.setString(2, getUsername());
            int  rs=prepStatement.executeUpdate();
            if(rs==1)
            {
                System.out.println("udate to privelege for " + getUsername() + " is successful");
            }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }

}

