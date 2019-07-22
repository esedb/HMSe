package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Ese on 12/25/2015.
 */
public class RadCompBean {
    boolean successful;
    long cost;
    String radiologicaltype;

    public void setCost(Long cost)
    {
        this.cost=cost;
    }
    public long getCost()
    {
        if( cost < 0)
            return Long.valueOf(0);

        return cost;
    }
    public String getRadiologicaltype() {
        return radiologicaltype;
    }

    public void setRadiologicaltype(String radiologicaltype) {
        this.radiologicaltype = radiologicaltype;
    }
    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public void execute()
    {
        String sql="INSERT INTO comp_rad_type (radtype, cost) values(?,?)";
        DataBase db=new DataBase();
        Connection con;
        try{
            con=db.getConnection();
            PreparedStatement prepstatement=con.prepareStatement(sql);
            prepstatement.setString(1,getRadiologicaltype());
            prepstatement.setLong(2, getCost());

            int result=prepstatement.executeUpdate();

            if(result==1)
            {
                successful=true;
                setSuccessful(successful);

            }
        }
        catch(Exception e){
            successful=false;
            setSuccessful(successful);
            e.printStackTrace();
        }


    }
}
