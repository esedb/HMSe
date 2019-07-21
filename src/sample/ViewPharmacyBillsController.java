package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 * Created by Ese on 4/23/2017.
 */
public class ViewPharmacyBillsController extends VBox {
    private final String id;

    @FXML
    private Label namelabel;

    @FXML
    private TextArea prestextarea;

    @FXML
    private DatePicker todate;

    @FXML
    private Button viewbydatebutton;

    @FXML
    private TableView<?> tableview;

    @FXML
    private HBox printhbox;

    @FXML
    private DatePicker fromdate;

    String surname=null;
    String othername=null;
    String imgurl=null;
    @FXML
    void initialize(){
        fromdate.setValue(LocalDate.now());
        todate.setValue(LocalDate.now());

        fromdate.setConverter(DateConverter.convert());
        todate.setConverter(DateConverter.convert());

        viewbydatebutton.setOnAction(e->{

            String datesql="Select * from drug_sales where date between ? and ? and patient_id=?";
            ObservableList datelist=FXCollections.observableArrayList();
            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(datesql)){
                stmt.setString(1, fromdate.getValue().toString());
                stmt.setString(2, todate.getValue().toString());
                stmt.setString(3, id);
                ResultSet rs=stmt.executeQuery();
                while(rs.next()){
                    ViewPharmacyBillBean vb=new ViewPharmacyBillBean();
                    vb.cost.set(rs.getDouble("unit_cost"));
                    vb.drugname.set(rs.getString("drug"));
                    vb.date.set(rs.getString("date"));
                    vb.quantity.set(rs.getDouble("quantity"));
                    vb.totalcost.set(rs.getDouble("totalcost"));
                    datelist.add(vb);

                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }

            if(!datelist.isEmpty()){
                displayTable(datelist);
            }

        });



        String billsql="select * from drug_sales where patient_id=?";
        ObservableList ddlist= FXCollections.observableArrayList();
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(billsql)){
            stmt.setString(1, id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                ViewPharmacyBillBean vb=new ViewPharmacyBillBean();
                vb.cost.set(rs.getDouble("unit_cost"));
                vb.drugname.set(rs.getString("drug"));
                vb.date.set(rs.getString("date"));
                vb.quantity.set(rs.getDouble("quantity"));
                vb.totalcost.set(rs.getDouble("totalcost"));
                ddlist.add(vb);

            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        if (!ddlist.isEmpty()) {
            System.out.println("this part of the programme ran");
            displayTable(ddlist);
        }

        //String namesql="Select * from drug_sales inner join patient_registry on drug_sales.patient_id=patient_registry.id_sn";

        if(id!=null) {
            String namesql = "Select firstname, othername, image_url from patient_registry where id_sn=?";
            try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement stmt = con.prepareStatement(namesql)) {
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    surname = rs.getString("firstname");
                    othername = rs.getString("othername");
                    imgurl = rs.getString("image_url");
                    System.out.println("firstname is " + rs.getString("firstname"));


                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (surname != null && othername != null && imgurl != null) {
                namelabel.setText(surname + " " + othername);
                System.out.println("suname is  " + surname);

            } else {
                namelabel.setText(surname + " " + othername);

            }
        }

        String pre = "SELECT * from doc_pres WHERE patient_id=?";
        ObservableList preslist=FXCollections.observableArrayList();
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(pre)){

            stmt.setString(1, id);
            ResultSet rss = stmt.executeQuery();
            while (rss.next()) {
                preslist.addAll(rss.getString("date") + "  :\t" + rss.getString("prescrip"));
            }
            StringBuilder builder = new StringBuilder();
            for (Object value : preslist) {
                builder.append((String) value + "\n");

            }
            if(builder.toString()!=null && builder.toString().length()>0) {
                prestextarea.setText(builder.toString());
            }


        }
        catch(Exception ex){
            ex.printStackTrace();
        }


    }

    void displayTable(ObservableList ddlist){
        tableview.getItems().clear();
        tableview.getColumns().clear();

        tableview.setItems(ddlist);

        TableColumn tdrugname=new TableColumn("Drug name");
        tdrugname.setCellValueFactory(new PropertyValueFactory("drugname"));

        TableColumn tquantity=new TableColumn("Quantity");
        tquantity.setCellValueFactory(new PropertyValueFactory("quantity"));

        TableColumn tunitprice=new TableColumn("Unit Price");
        tunitprice.setCellValueFactory(new PropertyValueFactory("cost"));

        TableColumn ttotalcost=new TableColumn("Total Cost");
        ttotalcost.setCellValueFactory(new PropertyValueFactory("totalcost"));

        TableColumn tdate=new TableColumn("Date");
        tdate.setCellValueFactory(new PropertyValueFactory("date"));

        tableview.getColumns().addAll(tdrugname, tquantity, tunitprice, ttotalcost, tdate);
    }


    public ViewPharmacyBillsController(String id) throws IOException {
        this.id=id;

        FXMLLoader fxmlloader=new FXMLLoader(ViewPharmacyBillsController.class.getResource("viewpharmacybill.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
}
