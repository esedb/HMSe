package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.DrugDetailsController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 1/10/2016.
 */
public class DrugListController extends VBox {
    @FXML
    private ToolBar toolbar;

    @FXML
    private HBox tableviewer;

    @FXML
    private void initialize()
    {
        ObservableList olist= FXCollections.observableArrayList();
        try{
            String sql="SELECT * FROM drug_details";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement prepstatement=con.prepareStatement(sql);
            ResultSet rs=prepstatement.executeQuery();
            while (rs.next())
            {
                DrugListBeans db=new DrugListBeans(rs.getString("drug"), rs.getInt("unit_price"), rs.getInt("qty_left"));
                olist.add(db);
            }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        TableView tableview=new TableView(olist);
        TableColumn tdrug=new TableColumn("Drug Name");
        tdrug.setCellValueFactory(new PropertyValueFactory<DrugListBeans, String>("drug"));

        TableColumn tunitprice=new TableColumn("Unit Price");
        tunitprice.setCellValueFactory(new PropertyValueFactory<DrugListBeans, String>("price"));

        TableColumn tquantityleft=new TableColumn("Quantity Left");
        tquantityleft.setCellValueFactory(new PropertyValueFactory<DrugListBeans, String>("quantityleft"));

        tableview.getColumns().addAll(tdrug, tunitprice, tquantityleft);


        tableview.prefWidthProperty().bind(tableviewer.widthProperty());


        tableviewer.getChildren().add(tableview);


    }

    public DrugListController() throws IOException {
        FXMLLoader fxmlloader=new FXMLLoader(DrugDetailsController.class.getResource("druglist.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
}

