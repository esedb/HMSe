package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Ese on 12/30/2015.
 */
public class AddRadiologicalBean {
    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    boolean successful;
    String radiologicalname;
    String radiologicaltype;
    int pricost;
    int hmocost;

    public int getCompanycost() {
        return companycost;
    }

    public void setCompanycost(int companycost) {
        this.companycost = companycost;
    }

    int companycost;


    public int getPricost() {
        return pricost;
    }

    public void setPricost(int pricost) {
        this.pricost = pricost;
    }

    public String getRadiologicalname() {
        return radiologicalname;
    }

    public void setRadiologicalname(String radiologicalname) {
        this.radiologicalname = radiologicalname;
    }

    public String getRadiologicaltype() {
        return radiologicaltype;
    }

    public void setRadiologicaltype(String radiologicaltype) {
        this.radiologicaltype = radiologicaltype;
    }

    public int getHmocost() {
        return hmocost;
    }

    public void setHmocost(int hmocost) {
        this.hmocost = hmocost;
    }



    public void execute()
    {
        String sql="INSERT INTO radiological (radname, radtype, pricost, hmocost, companycost) values(?,?,?,?,?);";
        Connection con;
        DataBase dbase=new DataBase();
        try {
            con=dbase.getConnection();
            PreparedStatement prepstatement=con.prepareStatement(sql);
            prepstatement.setString(1, getRadiologicalname());
            prepstatement.setString(2, getRadiologicaltype());
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
