package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 7/14/2016.
 */
public class AssignBedController extends VBox {

    int ward=0;
    int bed=0;


    public AssignBedController() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(ViewAllInpatientController.class.getResource("outpatient_table.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }


    @FXML
    public void initialize()
    {

        String sql="select * from ward_bed where id=1";
        try{
            DataBase dbase = new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statements =con.prepareStatement(sql);
            ResultSet rs = statements.executeQuery();
            while(rs.next())
            {
                ward=rs.getInt("ward");
                bed=rs.getInt("bed");
            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();

        }

        try{

            String sqll="INSERT INTO bed_records (bed,ward,occupied) values (?,?,?)";
            DataBase dbase = new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement =con.prepareStatement(sql);
            for (int j=0; j<ward; j++)
            {
                for(int i=0; i<bed; i++)
                {
                    statement.setInt(1,i);
                    statement.setInt(2, j);
                    statement.setString(3, "No");
                }
            }
            int result=statement.executeUpdate();
            System.out.println("result: " + result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();

        }

    }

}
