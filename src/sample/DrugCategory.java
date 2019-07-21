package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * Created by Ese on 1/11/2016.
 */
public class DrugCategory extends VBox {
    private final Stage stage;
    @FXML
    private Button savebutton;

    @FXML
    private TextField drugcategory;

    @FXML
    private Button refreshbutton;


    boolean flag=false;

    @FXML
    private void initialize()
    {
        refreshbutton.setOnAction(e->{
            drugcategory.setText("");
        });

        savebutton.setOnAction(e->{
            if(drugcategory.getText().length()>0){
                saveAction();
            }
            else{
                ShowDialog.show("Boxes marked in red must be filled with values");
            }

        });





    }



    void saveAction() {

        ObservableList olist= FXCollections.observableArrayList();
        String sql = "SELECT * FROM drug_category where category=?";


        try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement prepstatement = con.prepareStatement(sql);){

            prepstatement.setString(1, drugcategory.getText());
            ResultSet rs=prepstatement.executeQuery();


            while(rs.next()) {

                DrugCategoryBean dd=new DrugCategoryBean(rs.getString("category"));
                olist.add(dd);
                flag=true;
            }




        } catch (Exception e) {
            e.printStackTrace();
        }

        if(flag==true){
            ShowDialog.show("Drug is already present in Database");
            return;
        }



        if(!flag) {
            if (drugcategory.getText().length()>0 && drugcategory.getText() != null) {
                String insertsql = "INSERT INTO drug_category (category) values(?)";
                Connection con;
                try {
                    DataBase dbase = new DataBase();
                    con = dbase.getConnection();
                    PreparedStatement prepstatement = con.prepareStatement(insertsql);
                    prepstatement.setString(1, drugcategory.getText());
                    int result = prepstatement.executeUpdate();
                    if (result == 1) {
                       ShowDialog.show("Operation Successful");
                        stage.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public DrugCategory(Stage stage) throws IOException
    {
        this.stage =stage;
        FXMLLoader fxmlloader = new FXMLLoader(DrugCategory.class.getResource("drug_category.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();


    }

}
