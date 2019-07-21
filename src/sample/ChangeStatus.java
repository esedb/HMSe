package sample;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Ese on 12/5/2015.
 */
public class ChangeStatus {
    SimpleStringProperty status = new SimpleStringProperty();
    SimpleStringProperty firstname=new SimpleStringProperty();
    SimpleStringProperty lastname=new SimpleStringProperty();

    public ChangeStatus(String firstname, String lastname, String status) {
        this.firstname.set(firstname);
        this.lastname.set(lastname);
        this.status.set(status);
    }

    public ChangeStatus() {

    }
    public String getFirstname()
    {
        return firstname.get();
    }
    public String getLastname()
    {
        return lastname.get();

    }
    public String getUsername()
    {
        return (getFirstname() + " " + getLastname());
    }

    public String getStatus() {
        return this.status.get();
    }
    public void execute()
    {
        try{
            String statement="UPDATE account_form SET status=? where username=?";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement prepStatement=con.prepareStatement(statement);
            prepStatement.setString(1, getStatus());

            prepStatement.setString(2, getUsername());
            int  rs=prepStatement.executeUpdate();
            if(rs==1)
            {
                System.out.println("udate to status for " + getUsername() + " is successful");
            }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
    public static void main(String args[])
    {
        ChangeStatus cs=new ChangeStatus("Bruno", "Pedro", "enabled");
        cs.execute();
    }
}
