package sample;

import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 12/21/2015.
 */
public class AddDepartmentBeans implements ExecuteService{
    int departmentid;
    String department;
    Button adcbutton;
    Button viewallbutton;


    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    boolean successful;
    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    public void execute()
    {
        String sql="INSERT INTO department_records (department) values(?)";
        DataBase dbase=new DataBase();
        try {
            Connection conn = dbase.getConnection();
            PreparedStatement prepstatement = conn.prepareStatement(sql);
            prepstatement.setString(1, getDepartment());
            int rs=prepstatement.executeUpdate();
            successful=true;
            setSuccessful(successful);
            System.out.println("operation _: " + isSuccessful());

        }
        catch(Exception e)
        {
            successful=false;
            setSuccessful(successful);
            e.printStackTrace();
        }

    }
    public static void main(String args[]) {

        AddDepartmentBeans ad = new AddDepartmentBeans();
        ad.setDepartment("Nurse");
        ad.execute();


    }




}
