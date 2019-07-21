package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 7/25/2016.
 */
public class ViewStoreItemsController extends VBox {

    @FXML
    private Button deletebutton;

    @FXML
    private TableView<?> tableview;

    @FXML
    void initialize(){

        displayTable();

        deletebutton.setOnAction(e->{

            StoreItemBeans sb=(StoreItemBeans)tableview.getFocusModel().getFocusedItem();
            if(sb!=null) {
                try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection();
                     PreparedStatement stmt = con.prepareStatement("delete from store_item where item_type = ?")) {
                    stmt.setString(1, sb.getItemtype());
                    int d=stmt.executeUpdate();
                    if(d==1){
                        System.out.println("Operation successful");
                        displayTable();
                    }


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


    }
    void displayTable()
    {

        ObservableList olist= FXCollections.observableArrayList();

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement("select * from store_item")){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){

                StoreItemBeans sb=new StoreItemBeans();
                sb.itemtype.set(rs.getString("item_type"));
                sb.receiveby.set(rs.getString("received_by"));
                sb.date.set(rs.getString("date"));
                sb.quantity.set(rs.getLong("quantity"));
                olist.add(sb);

           }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        sdisplayTable(olist);



    }

    void sdisplayTable(ObservableList olist){

        tableview.getColumns().clear();
        tableview.getItems().clear();

        tableview.setItems(olist);

        TableColumn titemtype=new TableColumn("Item Type");
        titemtype.setCellValueFactory(new PropertyValueFactory("itemtype"));

        TableColumn tireceivedby=new TableColumn("Received By");
        tireceivedby.setCellValueFactory(new PropertyValueFactory("receiveby"));

        TableColumn tdate=new TableColumn("Date");
        tdate.setCellValueFactory(new PropertyValueFactory("date"));

        TableColumn tquantity=new TableColumn("Quantity");
        tquantity.setCellValueFactory(new PropertyValueFactory("quantity"));

        tableview.getColumns().addAll(titemtype, tireceivedby, tdate, tquantity);

    }
    public ViewStoreItemsController() throws IOException {

        FXMLLoader fxmlloader=new FXMLLoader(ViewStoreItemsController.class.getResource("viewstoreitem.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }

}
