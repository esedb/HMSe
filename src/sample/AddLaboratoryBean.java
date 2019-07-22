package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Ese on 12/29/2015.
 */
public class AddLaboratoryBean {
    String laboratorytype;
    String laboratoryname;
    int pricost;

    public int getCompanycost() {
        return companycost;
    }

    public void setCompanycost(int companycost) {
        this.companycost = companycost;
    }

    public int getPricost() {
        return pricost;
    }

    public void setPricost(int pricost) {
        this.pricost = pricost;
    }

    public int getHmocost() {
        return hmocost;
    }

    public void setHmocost(int hmocost) {
        this.hmocost = hmocost;
    }

    int companycost;
    int hmocost;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    boolean successful;

    public String getLaboratoryname() {
        return laboratoryname;
    }

    public void setLaboratoryname(String laboratoryname) {
        this.laboratoryname = laboratoryname;
    }

    public String getLaboratorytype() {
        return laboratorytype;
    }

    public void setLaboratorytype(String laboratorytype) {
        this.laboratorytype = laboratorytype;
    }
    public void execute()
    {
        String sql="INSERT INTO laboratory (labname, labtype, pricost, hmocost, companycost) values(?,?,?,?,?);";
        Connection con;
        DataBase dbase=new DataBase();
        try {
            con=dbase.getConnection();
            PreparedStatement prepstatement=con.prepareStatement(sql);
            prepstatement.setString(1, getLaboratoryname());
            prepstatement.setString(2, getLaboratorytype());
            prepstatement.setInt(3, getPricost());
            prepstatement.setInt(4, getHmocost());
            prepstatement.setInt(5, getCompanycost());
            prepstatement.executeUpdate();
            successful=true;
            setSuccessful(successful);

        }
        catch(Exception e)
        {
            successful=false;
            setSuccessful(successful);
            e.printStackTrace();
        }
    }
    


    }




