package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 * Created by Ese on 1/18/2016.
 */
public class DrugSales extends VBox {
    private final String id;
    private final  String patienttype;
    @FXML
    private HBox tableboxviewer;

    @FXML
    private TextField patientid;

    @FXML
    private Button tsbutton;

    @FXML
    private TextField unitcostfld;

    @FXML
    private ChoiceBox<?> drugschoice;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField totalcostfld;

    @FXML
    private TextField signfld;

    @FXML
    private Button sellbutton;

    @FXML
    private TextField totalsalesfld;

    @FXML
    private TableView<?> tableview;

    @FXML
    private TextField quantityleftfld;

    @FXML
    private TextField ptypefld;

    @FXML
    private TextField quantityfld;



    @FXML
    void initialize()
    {
        if(id!=null){
            patientid.setText(id);
        }
        else{
            patientid.setText("Unregistered Patient");
        }
        if(patienttype!=null){
            ptypefld.setText(patienttype);
        }
        else{
            ptypefld.setText("Patient");
        }
        ptypefld.setEditable(false);
        patientid.setEditable(false);


        signfld.setText(Controller.sign_by);
        signfld.setEditable(false);



        totalsalesfld.setEditable(false);
        quantityleftfld.setEditable(false);




       // This set the date format to corresond with YY-MM-dd when saving to database
        final String pattern = "yyyy-MM-dd";
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern(pattern);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        datepicker.setConverter(converter);
        datepicker.setValue(LocalDate.now());
        datepicker.setEditable(false);
        ObservableList saleslist=FXCollections.observableArrayList();

        tsbutton.setOnAction(e->{
            tableview.getColumns().clear();
            tableview.getItems().clear();
            try{
                DatePicker datep=new DatePicker();
                datep.setConverter(converter);
                datep.setValue(LocalDate.now());
                System.out.println("date is " + datep.getValue().toString());

                String sql="SELECT * FROM drug_sales where date=? ";
                DataBase dbase=new DataBase();
                Connection con=dbase.getConnection();
                Date fmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(datep.getValue().toString());
                java.sql.Date fmySqlDate = new java.sql.Date(fmyDate.getTime());
                con=dbase.getConnection();
                PreparedStatement prepstatement=con.prepareStatement(sql);
                prepstatement.setDate(1, fmySqlDate);
                ResultSet rs=prepstatement.executeQuery();
                while(rs.next())
                {
                    DrugSalesBeans db=new DrugSalesBeans(rs.getString("patient_id"), rs.getString("date"), rs.getString("sign_by"),
                            rs.getInt("quantity"), rs.getInt("unit_cost"), rs.getInt("selling_cost"),rs.getInt("totalcost"), rs.getString("drug"));

                    saleslist.addAll(db);
                }


                tableview.setItems(saleslist);
                TableColumn tpatientid=new TableColumn("Patient id");
                tpatientid.setCellValueFactory(new PropertyValueFactory<DrugSalesBeans, String>("patientid"));

                TableColumn tdate=new TableColumn("date");
                tdate.setCellValueFactory(new PropertyValueFactory<DrugSalesBeans, String>("date"));

                TableColumn tquansold=new TableColumn("Quantity Sold");
                tquansold.setCellValueFactory(new PropertyValueFactory<DrugSales, String>("quantitysold"));

                TableColumn tsignby=new TableColumn("Sign By");
                tsignby.setCellValueFactory(new PropertyValueFactory<DrugSales, String>("signby"));

                TableColumn tunitcost=new TableColumn("Unit Cost");
                tunitcost.setCellValueFactory(new PropertyValueFactory<DrugSales, String>("unitcost"));

                TableColumn tsellingcost=new TableColumn("Selling Cost");
                tsellingcost.setCellValueFactory(new PropertyValueFactory<DrugSales, String>("sellingcost"));

                TableColumn ttotalcost=new TableColumn("Total Cost");
                ttotalcost.setCellValueFactory(new PropertyValueFactory<DrugSales, String>("totalcost"));

                TableColumn tdrugsold=new TableColumn("drugsold");
                tdrugsold.setCellValueFactory(new PropertyValueFactory<DrugSales, String>("drugsold"));

                tableview.getColumns().addAll(tpatientid, tdate, tquansold, tsignby, tunitcost, tsellingcost, ttotalcost, tdrugsold);


            }

            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        });

        //itemsProperty(). might have been wrong do forget to check later
        ObservableList druglist=FXCollections.observableArrayList();
        druglist.add("None");

        try{
            //filling drugchoice with values;

            String sql="SELECT * FROM drug_details";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                druglist.addAll(rs.getString("drug"));

            }
            System.out.println("successful: _");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        drugschoice.setItems(druglist);
        drugschoice.getSelectionModel().selectFirst();
        quantityfld.setText("0");




        drugschoice.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue)->
                {
                    double quantity=0.0;
                    String drug = drugschoice.getValue().toString();
                    if (drug != null && drug != "") {
                        try {
                            String sql = "SELECT unit_price, packet_size FROM drug_details where drug=?";
                            //make sure drug from drug_details in table is unique- is it vital
                            //so as to avoid logical errors arising from drug visible in unitcostfld
                            DataBase dbase = new DataBase();
                            Connection con = dbase.getConnection();
                            PreparedStatement statement = con.prepareStatement(sql);
                            statement.setString(1, drug);

                            ResultSet rs = statement.executeQuery();
                            while (rs.next()) {
                                double unit_price=rs.getDouble("unit_price");
                                quantity=rs.getDouble("packet_size");
                                System.out.println("first quantity is " + quantity);

                                if(String.valueOf(unit_price)==null || String.valueOf(unit_price)=="")
                                {
                                    unitcostfld.setText(String.valueOf(0));
                                }
                                unitcostfld.setText(String.valueOf(unit_price));

                            }
                            con.close();
                            if(con!=null)
                            {
                                con=null;

                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        String qtsql = "SELECT sum(quantity) as total from drug_sales where drug=?";
                        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(qtsql)){
                            stmt.setString(1, newvalue.toString());
                            ResultSet rs=stmt.executeQuery();
                            while(rs.next()){

                                System.out.println("quantity is " + quantity);
                                System.out.println("total is " + rs.getDouble("total"));

                                quantityleftfld.setText(String.valueOf(quantity-rs.getDouble("total")));


                            }

                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }


                    }
                });
        quantityfld.textProperty().addListener((obsrvable, oldvalue, newvalue)->{

            if(newvalue!=null && newvalue.length()>0) {

                if (!MyUtil.isInteger(newvalue)) {
                    quantityfld.setText(oldvalue);
                }
                else {
                        try {
                        double quantity = Double.parseDouble(newvalue);
                        double sellingprice=quantity * Double.parseDouble(unitcostfld.getText());
                        totalcostfld.setText(String.valueOf(sellingprice));
                            }
                            catch(Exception e)
                            {

                            }

                }
            }

        });



        sellbutton.setOnAction(e->
        {
            if(patientid.getText()!=null || patientid.getText()!="") {
                sell();
            }
            else {
                System.out.println("Select a type of drug");
            }
        });

        String totalsql="select sum(totalcost) as total from drug_sales";

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(totalsql)){

            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                totalsalesfld.setText(rs.getString("total"));
            }


        }
        catch(Exception ex){
            ex.printStackTrace();
        }



    }


    public DrugSales(String id, String patienttype) throws IOException {
        this.patienttype=patienttype;

        this.id=id;

        FXMLLoader fxmlloader=new FXMLLoader(DrugSales.class.getResource("drugsales.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
    private void sell()
    {


        double quan1=0.0;
        double quan2=0.0;
        try{
            if(quantityleftfld.getText().length()<=0)
            {
                quantityleftfld.setText("0");
                quantityfld.setText("0");
            }
            quan1=Double.valueOf(quantityfld.getText());
            quan2=Double.valueOf(quantityleftfld.getText());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        double answer=quan2-quan1;
        if(answer<0)
        {
            ShowDialog.show("Drug selected has been exhuasted");
        }
        if(answer>=0){

            if(unitcostfld.getText().length()<=0 || quantityfld.getText().length()<=0 || totalcostfld.getText().length()<=0)
            {
                unitcostfld.setText("0");
                quantityfld.setText("0");
                totalcostfld.setText("0");
            }

            String patient_id=patientid.getText();
            String patienttype=ptypefld.getText();
            String drug=drugschoice.getValue().toString();
            double quantity=Double.parseDouble(quantityfld.getText());
            double unitcost=Double.parseDouble(unitcostfld.getText());
            double totalcost=Double.parseDouble(totalcostfld.getText());
            String sign=signfld.getText();

            try{
                Date salesdate= new SimpleDateFormat("yyyy-MM-dd").parse(datepicker.getValue().toString());
                java.sql.Date sqlsalesdate = new java.sql.Date(salesdate.getTime());
                String sql="INSERT INTO will_parry.drug_sales (patient_id, patient_type, drug, quantity, unit_cost, totalcost, date, sign_by) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                DataBase dbase=new DataBase();
                Connection con=dbase.getConnection();
                PreparedStatement statement=con.prepareStatement(sql);
                statement.setString(1, patient_id);
                statement.setString(2, patienttype);
                statement.setString(3, drug);
                statement.setDouble(4, quantity);
                statement.setDouble(5, unitcost);

                statement.setDouble(6, totalcost);
                statement.setDate(7, sqlsalesdate);
                statement.setString(8, signfld.getText());
                int result=statement.executeUpdate();
                if(result==1)
                {
                    quantityleftfld.setText(String.valueOf(answer));
                    ShowDialog.show("Operation successful");
                }
                con.close();

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


        }

        else{
            return;
        }

    }

}
