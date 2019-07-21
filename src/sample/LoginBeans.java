package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 12/8/2015.
 */
public class LoginBeans {
    String firstnamedbase;
    String lastnamedbase;
    String passwordbase;
    String departmentdbase;
    public String firstname;
    public String lastname;
    public String password;
    public String department;
    public String retusername;
    private boolean successful;



    String loginusername;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }



    public void setFirstname(String firstname) {
        this.firstname = firstname;

    }

    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public String getUsername() {
        return (getFirstname() + " " + getLastname());
    }

    public void execute() {

        System.out.println("execute run");


        try {
            String sql = "SELECT firstname, lastname, password, department FROM account_records " +
                    "where firstname=? and lastname=? and department=? and password=?";
            DataBase dbase = new DataBase();
            Connection conn = dbase.getConnection();
            PreparedStatement prepstatement = conn.prepareStatement(sql);
            prepstatement.setString(1, getFirstname());
            prepstatement.setString(2, getLastname());
            prepstatement.setString(3, getDepartment());
            prepstatement.setString(4, getPassword());


            ResultSet rs = prepstatement.executeQuery();

            while (rs.next()) {

                firstnamedbase= rs.getString("firstname");
                lastnamedbase=rs.getString("lastname");
                passwordbase = rs.getString("password");
                departmentdbase= rs.getString("department");


            }

            if (getFirstname().equalsIgnoreCase(firstnamedbase) && getLastname().equals(lastnamedbase) && getPassword().equalsIgnoreCase(passwordbase) && getDepartment().equalsIgnoreCase(departmentdbase)) {
                successful = true;
                setSuccessful(successful);


            } else {
                successful = false;
                setSuccessful(successful);
            }

        } catch (Exception e) {
            e.printStackTrace();
            successful = false;
            setSuccessful(successful);
        }

    }

    public void retrieve() {
        String username;
        DataBase dbase = new DataBase();

        Connection con;
        String sql = "SELECT firstname, lastname  from account_records";
        try {
            con = dbase.getConnection();
            PreparedStatement prepstatement = con.prepareStatement(sql);
            ResultSet rs = prepstatement.executeQuery();
            while (rs.next()) {
                retusername = rs.getString("firstname") + " " + rs.getString("lastname");
                setLoginusername(retusername);
            }



        } catch (Exception e) {
            e.printStackTrace();

        }


    }
    public String getLoginusername() {
        return loginusername;
    }

    public void setLoginusername(String loginusername) {
        this.loginusername = loginusername;
    }
}
