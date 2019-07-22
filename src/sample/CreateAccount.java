package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 12/2/2015.
 */
public class CreateAccount {
    SimpleStringProperty firstname=new SimpleStringProperty();
    SimpleStringProperty lastname=new SimpleStringProperty();
    SimpleStringProperty department=new SimpleStringProperty();
    SimpleStringProperty status=new SimpleStringProperty();
    SimpleIntegerProperty privelege=new SimpleIntegerProperty();
    SimpleStringProperty password=new SimpleStringProperty();

    public CreateAccount()
    {

    }

    public CreateAccount(String firstname, String lastname, String department, String status, int privelege, String password){
        this.firstname.set(firstname);
        this.lastname.set(lastname);
        this.department.set(department);
        this.status.set(status);
        this.privelege.set(privelege);
        this.password.set(password);
    }
    public String getFirstname()
    {
        return firstname.get();
    }
    public String getLastname()
    {
        return lastname.get();
    }
    public String getDepartment()
    {
        return department.get();
    }
    public String getStatus()
    {
        return status.get();
    }
    public int getPrivelege()
    {
        return privelege.get();
    }
    public CreateAccount getAccount()
    {
        return new CreateAccount();
    }
    public String getPassword()
    {
        return this.password.get();
    }
    public String getUsername()
    {
        return getFirstname() + " " + getLastname();
    }
    public void execute()
    {

        try{
            String statement="INSERT INTO account_form (username, password, department, status, privelege) values(?,?,?,?,?)";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement prepStatement=con.prepareStatement(statement);
            prepStatement.setString(1,getUsername());
            prepStatement.setString(2,getPassword());
            prepStatement.setString(3, getDepartment());
            prepStatement.setString(4, getStatus());
            prepStatement.setInt(5, getPrivelege());
            int result=prepStatement.executeUpdate();
            System.out.println(result);


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }


}
