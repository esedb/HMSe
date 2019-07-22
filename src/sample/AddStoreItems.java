package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;


/**
 * Created by Ese on 1/12/2016.
 */

public class AddStoreItems extends VBox {
    //A class that defines logic for Add Store Items in purchase
    @FXML
    private TextField ratefld;

    @FXML
    private Button savebutton;

    @FXML
    private TextField receiptnofld;

    @FXML
    private Button addsupbutton;

    @FXML
    private ComboBox<?> itemchoice;

    @FXML
    private TextField receivedbyfld;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField totalfld;

    @FXML
    private Button additemsbutton;

    @FXML
    private Tab listitems;

    @FXML
    private Tab addnewitem;

    @FXML
    private HBox listitembox;

    @FXML
    private ComboBox<?> schoice;

    @FXML
    private TextField quantityfld;

    @FXML
    private ComboBox<?> departmentchoice;

    public AddStoreItems() throws IOException
    {
        FXMLLoader fxmlloader=new FXMLLoader(AddStoreItems.class.getResource("addstoreitems.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }

    @FXML
    public void initialize()
    {
        datepicker.setConverter(DateConverter.convert());
        datepicker.setValue(LocalDate.now());

        ObservableList sulist=FXCollections.observableArrayList();
        try{

            String sqlstatement="select * from suppliers";
            DataBase database=new DataBase();
            Connection connection=database.getConnection();
            PreparedStatement prepstatement=connection.prepareStatement(sqlstatement);
            ResultSet rss=prepstatement.executeQuery();
            while(rss.next())
            {
                sulist.addAll(rss.getString("supplier_name"));
            }


        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        schoice.setItems(sulist);
        schoice.getSelectionModel().selectFirst();

        addsupbutton.setOnAction(e->{
            try {
                AddSupplierController ac = new AddSupplierController();
                Stage stage = new Stage();
                ac.stage = stage;
                Scene scene = new Scene(ac);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }


        });

        additemsbutton.setOnAction(e->{
            try{
                Stage stage=new Stage();
                AddItemSuppliedController ac=new AddItemSuppliedController(stage);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(ac));
                stage.show();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }



        });



        totalfld.setEditable(false);
        receivedbyfld.setEditable(false);

        ObservableList supnamelist= FXCollections.observableArrayList();
        ObservableList itemlist=FXCollections.observableArrayList();


        try {
            String sql="SELECT * FROM supplier_details";
            Connection con;
            DataBase dbase=new DataBase();
            con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                supnamelist.add(rs.getString("supplier_name"));
                itemlist.add(rs.getString("item_supplied"));
            }
        }
        catch(Exception e){
        }

        itemchoice.setItems(supnamelist);
        itemchoice.setItems(itemlist);



        TableView tableview=new TableView();

        //tableview for add items: important

        ObservableList tableitems=FXCollections.observableArrayList();
        try {
            String sql="SELECT * FROM  store_items";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                AddStoreItemsBeans as=new AddStoreItemsBeans(rs.getString("date"), rs.getString("store"), rs.getString("supplier"),
                        rs.getString("received_by"), rs.getString("item_supplied"), rs.getInt("total"), rs.getInt("rate"), rs.getInt("receipt_no"), rs.getInt("quantity_left"));
                tableitems.add(as);

            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        tableview.setItems(tableitems);


        TableColumn tsupplier=new TableColumn("Supplier");
        tsupplier.setCellValueFactory(new PropertyValueFactory<AddStoreItemsBeans, String>("supplier"));

        TableColumn titemsupplied=new TableColumn("Item Supplied");
        titemsupplied.setCellValueFactory(new PropertyValueFactory<AddStoreItemsBeans, String>("itemsupplied"));

        TableColumn tstore=new TableColumn("Store");
        tstore.setCellValueFactory(new PropertyValueFactory<AddStoreItemsBeans, String>("store"));

        TableColumn trate=new TableColumn("Rate");
        trate.setCellValueFactory(new PropertyValueFactory<AddStoreItemsBeans, String>("rate"));

        TableColumn tquantity=new TableColumn("Quantity");
        tquantity.setCellValueFactory(new PropertyValueFactory<AddStoreItemsBeans, String>("quantity"));

        TableColumn ttotal =new TableColumn("Total");
        ttotal.setCellValueFactory(new PropertyValueFactory<AddStoreItemsBeans, String>("Total"));

        tableview.getColumns().addAll(tsupplier, titemsupplied, tstore, trate, tquantity, ttotal);
        tableview.prefWidthProperty().bind((listitembox.widthProperty()));

        listitembox.getChildren().addAll(tableview);

    }

    @FXML
    void saveAction(ActionEvent event) {
        int rate, quantityleft, receiptno, total;
        rate=quantityleft=receiptno=total=0;
        try {
            rate = Integer.parseInt(ratefld.getText());
            quantityleft = Integer.parseInt(quantityfld.getText());
            receiptno = Integer.parseInt(receiptnofld.getText());
            total=Integer.parseInt(totalfld.getText());


        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
        }
        String date=datepicker.getValue().toString();
        String supplier=schoice.getValue().toString();
        String item=itemchoice.getValue().toString();
        String receiveby=receivedbyfld.getText();

        String sql="INSERT INTO store_items( date,supplier, received_by, item_supplied, receipt_no, quantity_left," +
                "rate, total) values(?,?,?,?,?,?,?,?)";

        Connection con;
        try{
            DataBase dbase=new DataBase();
            con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setString(1, date);
            statement.setString(2, supplier);
            statement.setString(3, receiveby);
            statement.setString(4, item);
            statement.setInt(5, receiptno);
            statement.setInt(6, quantityleft);
            statement.setInt(7, total);
            statement.setString(8, receiveby);
            int result=statement.executeUpdate();
            if(result==1)
            {
                System.out.println("Successful");
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }


}
