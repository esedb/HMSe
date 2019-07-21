package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 12/21/2015.
 */
public class AddDepartmentController extends VBox {


    private final Stage stage;
    @FXML
    private TextField departmentfld;

    @FXML
    private Button savebutton;

    @FXML
    private Button cancelbutton;

    public AddDepartmentController(Stage stage) throws IOException
    {
        this.stage=stage;
        FXMLLoader fxmlloader = new FXMLLoader(AdminDetailsController.class.getResource("add_department.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
    @FXML
    public void initialize()
    {
        savebutton.setOnAction(e->
        {

            String dept=departmentfld.getText();
            if(dept.length()<=0){
                ShowDialog.show("Boxes marked in red must be filled with vales");

            }
            else if(dept.length()>0 && dept.equalsIgnoreCase("Doctor")
                    && dept.equalsIgnoreCase("Accountant") && dept.equalsIgnoreCase("Administrator") && dept.equalsIgnoreCase("Nurse"))
            {
                ShowDialog.show("Doctor, Accountant, Adminstrator and Nurse is already added by default");
                return;
            }
            else if(dept.length()>0 && !dept.equalsIgnoreCase("Doctor") && !dept.equalsIgnoreCase("Accountant")
                    && !dept.equalsIgnoreCase("Administrator") && !dept.equalsIgnoreCase("Nurse")){
                String deptsql="select department from department_records where department =?";
                try(DataBase dbasen=new DataBase(); Connection conn=dbasen.getConnection(); PreparedStatement stmtn=conn.prepareStatement(deptsql)){
                    String validate="";
                    stmtn.setString(1, dept);
                    ResultSet rs=stmtn.executeQuery();
                    if(rs.next())
                    {
                        ShowDialog.show("Department already present in Database");
                        return;
                    }
                    else{

                        String sql="INSERT INTO department_records (department) values(?)";
                        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)) {
                            String department = departmentfld.getText();
                            stmt.setString(1, department);
                            int d = stmt.executeUpdate();
                            if (d == 1) {
                                ShowDialog.show("Operation successful");
                            }

                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }

                    }


                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            else{
                ShowDialog.show("Boxes marked in red must be filled with values");
            }



        });
        cancelbutton.setOnAction(e->
        {
            stage.close();

        });

    }
    public void save(int departmentid, String department)
    {
        AddDepartmentBeans adb=new AddDepartmentBeans();
        adb.setDepartmentid(departmentid);
        adb.setDepartment(department);
        adb.execute();
    }

}
