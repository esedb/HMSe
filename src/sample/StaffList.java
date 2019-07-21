package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 1/19/2016.
 */
public class StaffList extends VBox {

    @FXML
    private TableView<?> tableview;

    @FXML
    private ChoiceBox<?> choicebox;


    StaffListFactory slf=null;
    ObservableList olist= FXCollections.observableArrayList();

    @FXML
    void initialize()
    {

        ObservableList department= FXCollections.observableArrayList();
        department.add("None");

        try{
            DataBase dbase =new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement("SELECT department from department_records ");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                department.addAll(rs.getString("department"));

            }


        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        choicebox.setItems(department);
        choicebox.getSelectionModel().selectFirst();

        choicebox.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue)->
        {
            if(newvalue.toString()!=null)
            {
                try {

                    String choiceboxvalue= newvalue.toString();
                    if(choiceboxvalue==null || choiceboxvalue.length()<=0 || choiceboxvalue.equalsIgnoreCase("None"))
                    {
                        return;
                    }

                    String sql = "SELECT * FROM account_records WHERE department=?";
                    DataBase dbase = new DataBase();
                    Connection con = dbase.getConnection();
                    PreparedStatement statement = con.prepareStatement(sql);

                    olist.clear();
                    tableview.getColumns().clear();

                    statement.setString(1, choiceboxvalue);
                    ResultSet rs=statement.executeQuery();
                    while(rs.next()){

                        slf=new StaffListFactory(rs.getString("sexchoice"), rs.getString("phone"), rs.getString("privelege"),
                                rs.getString("status"), rs.getString("specialty"), rs.getString("department"), rs.getString("lastname"),
                                rs.getString("firstname"), rs.getString("accountid"));


                        olist.addAll(slf);


                    }

                    displayStaff(olist);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }



            }
            else{

            }
        });



    }


    public StaffList() throws IOException{
        FXMLLoader fxmlloader=new FXMLLoader(StaffList.class.getResource("stafflist.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
    private void displayStaff(ObservableList olist)
    {

        tableview.setItems(olist);
        TableColumn tfirstname=new TableColumn("First Name");
        tfirstname.setCellValueFactory(new PropertyValueFactory("firstname"));

        TableColumn tlastname=new TableColumn("Last Name");
        tlastname.setCellValueFactory(new PropertyValueFactory("lastname"));

        TableColumn tdepartment=new TableColumn("Department");
        tdepartment.setCellValueFactory(new PropertyValueFactory("department"));

        TableColumn tspeciality=new TableColumn("Speciality");
        tspeciality.setCellValueFactory(new PropertyValueFactory("speciality"));

        TableColumn tphone=new TableColumn("Phone");
        tphone.setCellValueFactory(new PropertyValueFactory("phone"));

        TableColumn tsex=new TableColumn("Sex");
        tsex.setCellValueFactory(new PropertyValueFactory("sex"));

        TableColumn tprivelege=new TableColumn("Privelege");
        tprivelege.setCellValueFactory(new PropertyValueFactory("privelege"));

        tableview.getColumns().addAll(tfirstname, tlastname, tdepartment, tspeciality, tphone, tsex, tprivelege);

    }
}
