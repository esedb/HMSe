package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 12/23/2015.
 */
public class StaffEntryForm {
    //factory for table view for Company and Staff Entry form
    //use Extensively for AccountFormController.java
    public StaffEntryForm(String firstname, String lastname, String department,String phone, String dateofbirth)
    {
        setFirstname(firstname);
        setLastname(lastname);
        setDepartment(department);
        setPhoneno(phone);
        setDateofbirth(dateofbirth);
    }

    StringProperty firstname;

    public void setFirstname(String value) {
        firstnameProperty().set(value);
    }

    public String getFirstname() {
        return firstnameProperty().get();
    }

    public StringProperty firstnameProperty() {
        if (firstname == null) firstname = new SimpleStringProperty(this, "firstName");
        return firstname;
    }

    StringProperty lastname;

    public void setLastname(String value) {
        lastnameProperty().set(value);
    }

    public String getLastname() {
        return lastnameProperty().get();
    }

    public StringProperty lastnameProperty() {
        if (lastname == null) lastname = new SimpleStringProperty(this, "lastname");
        return lastname;
    }

    StringProperty department;

    public void setDepartment(String value) {
        departmentProperty().set(value);

    }

    public String getStreet() {
        return departmentProperty().get();
    }

    public StringProperty departmentProperty() {
        if (department == null) department = new SimpleStringProperty(this, "lastname");
        return department;
    }

    StringProperty phone;

    public void setPhoneno(String value) {
        phoneProperty().set(value);
    }

    public String getPhoneno() {
        return phoneProperty().get();
    }

    public StringProperty phoneProperty() {
        if (phone == null) phone = new SimpleStringProperty(this, "phone");
        return phone;
    }

    StringProperty dateofbirth;

    public void setDateofbirth(String value) {

        dateofbirthproperty().get();
    }

    public String getDateofbirth() {
        return dateofbirthproperty().get();

    }

    public StringProperty dateofbirthproperty() {
        if (dateofbirth == null) dateofbirth = new SimpleStringProperty(this, "date of birth");
        return dateofbirth;
    }
    public static void search(String value)
    {
        DataBase dbase=new DataBase();
        Connection con;
        String sql="select * from account records where firstname like%" + value + "% or lastname like %" + value + "%";
        try{
            con=dbase.getConnection();
            PreparedStatement prepstatement=con.prepareStatement(sql);
            ResultSet rs=prepstatement.executeQuery();
            while(rs.next())
            {

            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

