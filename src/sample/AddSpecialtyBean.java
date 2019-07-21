package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Ese on 12/21/2015.
 */
public class AddSpecialtyBean implements ExecuteService {
    public String department;
    public String specialty_name;

    public String getSpecialty_name() {
        return specialty_name;
    }

    public void setSpecialty_name(String specialty_name) {
        this.specialty_name = specialty_name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }





    public void execute(){
        String sql="INSERT INTO department_records ( specialname) values(?)";
        DataBase dbase=new DataBase();
        Connection con;
        try{
            con=dbase.getConnection();
            PreparedStatement prepstatement=con.prepareStatement(sql);
            prepstatement.setString(1, getSpecialty_name());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
}
