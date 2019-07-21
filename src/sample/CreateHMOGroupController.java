package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 3/31/2017.
 */
public class CreateHMOGroupController extends VBox {

    @FXML
    private Label privatelabtype;

    @FXML
    private VBox vboxhead;

    @FXML
    private ChoiceBox<?> hmochb;

    @FXML
    private TextField hmogroup;

    @FXML
    private Button createbutton;

    @FXML
    void initialize(){

        String sql="select hmo_name from hmo_details";
        ObservableList olist= FXCollections.observableArrayList();
        olist.add("None");

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql);){
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                olist.add(rs.getString("hmo_name"));
            }


        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        if(!olist.isEmpty() && olist.size()>1){
            hmochb.setItems(olist);
        }

        hmochb.getSelectionModel().selectFirst();





        createbutton.setOnAction(e->{
            String hmo_name=hmochb.getValue().toString();

            if(hmo_name.equalsIgnoreCase("None")){
                return;
            }

            if(hmogroup.getText().length()>0 && hmo_name!=null && hmo_name.length()>0 && !hmo_name.equalsIgnoreCase("None")) {
                String groupsql = "INSERT INTO hmo_group(group_name, hmo_name) values(?,?)";
                try(DataBase dbase =new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(groupsql))
                {
                    stmt.setString(1, hmogroup.getText());
                    stmt.setString(2, hmo_name);
                    int f=stmt.executeUpdate();
                    if(f==1){
                        System.out.println("Operation successful");
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }


        });


    }

    public CreateHMOGroupController() throws IOException {

        FXMLLoader fxmlloader=new FXMLLoader(CreateHMOGroupController.class.getResource("hmo_group.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }



}
