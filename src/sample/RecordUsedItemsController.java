package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 * Created by Ese on 5/14/2017.
 */
public class RecordUsedItemsController extends VBox {
    @FXML
    private Button savebutton;

    @FXML
    private ChoiceBox<?> itemchb;

    @FXML
    private TextField assignbyfld;

    @FXML
    private DatePicker datepicker;

    @FXML
    private ChoiceBox<?> requestbychb;

    @FXML
    private Button refreshbutton;

    @FXML
    private TextField quantityfld;

    long quantity;

    @FXML
    void initialize(){

        datepicker.setConverter(DateConverter.convert());
        datepicker.setValue(LocalDate.now());
        datepicker.setEditable(false);

        assignbyfld.setText(Controller.sign_by);
        assignbyfld.setEditable(false);

        refreshbutton.setOnAction(e->{
            quantityfld.setText("");
        });


        ObservableList itemlist= FXCollections.observableArrayList();
        itemlist.add("None");
        String itemsql="select item_type from store_item";
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(itemsql)){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                itemlist.addAll(rs.getString("item_type"));
            }

            rs.close();

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        itemchb.setItems(itemlist);
        itemchb.getSelectionModel().selectFirst();

        String workersql="select firstname, lastname from account_records";
        ObservableList workerslist=FXCollections.observableArrayList();
        workerslist.add("None");
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(workersql)){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
               workerslist.add(rs.getString("firstname") + " " + rs.getString("lastname"));
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        requestbychb.setItems(workerslist);
        requestbychb.getSelectionModel().selectFirst();

        itemchb.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue)->
        {

            if(newvalue.toString().equalsIgnoreCase("None")|| newvalue==null)
            {
                quantityfld.setText("");
                return;
            }
            else{
                String quantitysql="select quantity from store_item where item_type=?";
                try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(quantitysql)){
                    stmt.setString(1, newvalue.toString());
                    ResultSet rs=stmt.executeQuery();
                    while(rs.next()){
                        quantityfld.setText(rs.getString("quantity"));
                        quantity=Long.valueOf(rs.getString("quantity"));

                    }

                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }

        });

        savebutton.setOnAction(e->{
            long result=quantity-Long.valueOf(quantityfld.getText());
            if(result<=0){
                System.out.println("number of item is not enough");
                return;
            }
            else{
                if(Long.valueOf(quantityfld.getText())>0 && Long.valueOf(quantityfld.getText())<quantity && !requestbychb.getValue().toString().equalsIgnoreCase("None"))
                {
                    String updatesql="update store_item set quantity=? where item_type=?";
                    try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(updatesql)){
                        stmt.setLong(1, result);
                        stmt.setString(2, itemchb.getValue().toString());
                        int m=stmt.executeUpdate();
                        if(m==1){
                            System.out.println("operation successful");
                            PreparedStatement statement =con.prepareStatement("insert into store_used (date, item_type, quantity, issued_by, request_by) values(?,?,?,?,?)");
                            statement.setString(1, datepicker.getValue().toString());
                            statement.setString(2, itemchb.getValue().toString());
                            statement.setLong(3, Long.valueOf(quantityfld.getText()));
                            statement.setString(4,assignbyfld.getText());
                            statement.setString(5, requestbychb.getValue().toString());
                            int f=statement.executeUpdate();
                            if(f==1){
                                System.out.println("Operation totally successful");
                            }
                        }

                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }

                }
            }

        });


    }

    public RecordUsedItemsController() throws IOException {

        FXMLLoader fxmlloader=new FXMLLoader(RecordUsedItemsController.class.getResource("record_used_item.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
}
