package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ese on 2/9/2016.
 */
public class PaymentController extends VBox {


    @FXML
    private Button savebutton;

    @FXML
    private TextField pay_statusfld;

    @FXML
    private TextField receiptnofld;

    @FXML
    private TextField totalchargefld;

    @FXML
    private TextField othernamefld;

    @FXML
    private ChoiceBox<?> pay_typechoice;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField surnamefld;

    @FXML
    private TextField amountpaidfld;

    @FXML
    private TextField signfld;

    @FXML
    private TextField patientidfld;

    @FXML
    private TableView<?> tableview;

    @FXML
    private TextField balancefld;

    @FXML
    private Button summarybutton;


    private final String id;
    private final String status;
    private final long totalvalue;



    @FXML
    void initialize()
    {
        summarybutton.setOnAction(e->{

            try{
                PatientAccountSummary ps=new PatientAccountSummary(id);
                Stage stage=new Stage();
                Scene scene=new Scene(ps);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        });

        totalchargefld.setText(String.valueOf(totalvalue));

        if(id!=null && id.length()>0){

            String idsql = "select * from patient_registry where id_sn=?";
            try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement stmt = con.prepareStatement(idsql)) {
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    surnamefld.setText(rs.getString("firstname"));

                    othernamefld.setText(rs.getString("othername"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            surnamefld.setEditable(false);
            othernamefld.setEditable(false);
        }
        amountpaidfld.textProperty().addListener((obsrvable, oldvalue, newvalue)->{

            if(newvalue!=null && newvalue.length()>0) {
               if (!MyUtil.isInteger(newvalue)) {
                    amountpaidfld.setText(oldvalue);
                }
                else {

                }
            }

        });



        receiptnofld.textProperty().addListener((obsrvable, oldvalue, newvalue)->{

            if(newvalue!=null && newvalue.length()>0) {

                if (!MyUtil.isInteger(newvalue)) {
                    receiptnofld.setText(oldvalue);
                }
                else {

                }
            }

        });



        balancefld.setEditable(false);

        ObservableList paytypelist=FXCollections.observableArrayList();
        paytypelist.addAll("Cash", "POS", "Online Transfer", "Cheque");
        pay_typechoice.setItems(paytypelist);
        pay_typechoice.getSelectionModel().selectFirst();

        String surname1=null;
        String othername1=null;

        totalchargefld.setEditable(false);
        datepicker.setEditable(false);
        patientidfld.setEditable(false);

        ObservableList<Long> list= FXCollections.observableArrayList();
        String costsql="select cost from service_rendered where id=?";
        try( DataBase dbase= new DataBase(); Connection con=dbase.getConnection(); PreparedStatement statement=con.prepareStatement(costsql);){

            statement.setString(1, id);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {

                list.add(rs.getLong("cost"));

            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        long totalcost=0;

        for(long cost: list)
        {
            totalcost+=cost;

        }


        StringConverter converter=DateConverter.convert();
        datepicker.setConverter(converter);
        datepicker.setValue(LocalDate.now());
        patientidfld.setText(id);
        signfld.setText(Controller.sign_by);


        pay_statusfld.setText(status);

        balancefld.textProperty().addListener((observable, oldvalue, newvalue)->
        {
            if(totalchargefld.getText().length()>0 && newvalue.length()>0)
            {
                long tcost=Long.valueOf(totalchargefld.getText());
                long tbalance=Long.valueOf(amountpaidfld.getText());

                long rbalance=tcost-tbalance;

                balancefld.setText(String.valueOf(rbalance));

            }


        });


        savebutton.setOnAction(e->
        {
            if(amountpaidfld.getText().length()>0 && receiptnofld.getText().length()>0 && balancefld.getText().length()> 0) {
                long tcost = Long.valueOf(totalchargefld.getText());
                String date = datepicker.getValue().toString();
                long amountpaid = Long.valueOf(amountpaidfld.getText());
                String sign_by = signfld.getText();
                String balance = balancefld.getText();

                String surname = surnamefld.getText();
                String othername = othernamefld.getText();
                String receipt_no = receiptnofld.getText();

                String paymentsql = "insert into payment_table(id_sn, amount_deposited, payment_type, receipt_no, sign_by, date_v) values (?,?,?,?,?,?)";

                try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement stmt = con.prepareStatement(paymentsql)) {
                    stmt.setString(1, id);
                    stmt.setLong(2, amountpaid);
                    stmt.setString(3, pay_typechoice.getValue().toString());
                    stmt.setString(4, receiptnofld.getText());
                    stmt.setString(5, Controller.sign_by);
                    stmt.setString(6, date);
                    int d = stmt.executeUpdate();
                    if (d == 1) {
                        long totalvalue2 = 0;
                        long cost_of_service = 0;
                        System.out.println("operation successful");

                        String owingsql = "select sum(cost) as total from service_rendered where id=?";
                        try (DataBase db1 = new DataBase(); Connection conn = db1.getConnection(); PreparedStatement stmt1 = con.prepareStatement(owingsql)) {
                            stmt1.setString(1, id);
                            ResultSet rs = stmt1.executeQuery();
                            while (rs.next()) {
                                cost_of_service = rs.getLong("total");

                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        long deposited = -1;
                        String paidsql = "select sum(amount_deposited) as total from payment_table where id_sn=?";
                        try (DataBase dbase2 = new DataBase(); Connection conn = dbase2.getConnection(); PreparedStatement stmt2 = con.prepareStatement(paidsql)) {
                            stmt2.setString(1, id);
                            ResultSet rs = stmt2.executeQuery();
                            while (rs.next()) {
                                deposited = rs.getLong("total");

                            }
                            System.out.println("deposited " + deposited);

                            System.out.println("total value is " + totalvalue);
                            totalvalue2 = cost_of_service - deposited;

                            if(totalvalue2<=0){
                                String sql="update patient_registry set payment_status='Not owning' where id_sn=?";
                                try(DataBase dbase3=new DataBase(); Connection cn=dbase3.getConnection(); PreparedStatement stmt3=cn.prepareStatement(sql)){
                                    stmt3.setString(1, id);
                                    int ds=stmt3.executeUpdate();
                                    if(ds==1){
                                        System.out.println("patient _registry updated");
                                    }

                                }

                                catch(Exception ex){
                                    ex.printStackTrace();
                                }
                            }
                            else{
                                String sql="update patient_registry set payment_status='Owning' where id_sn=?";
                                try(DataBase dbase3=new DataBase(); Connection cn=dbase3.getConnection(); PreparedStatement stmt3=cn.prepareStatement(sql)){
                                    stmt3.setString(1, id);
                                    int ds=stmt3.executeUpdate();
                                    if(ds==1){
                                        System.out.println("patient _registry 2 updated");
                                    }

                                }

                                catch(Exception ex){
                                    ex.printStackTrace();
                                }

                            }
                        }

                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            /*try {
                String str = "INSERT INTO will_parry.account_payments (id_pay,  " +
                        "cost_of_service, amount_deposited, receivables, signed_by, receipt_no, date_paid) " +
                        "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
                DataBase dbase=new DataBase();
                Connection con=dbase.getConnection();
                PreparedStatement statement=con.prepareStatement(str);
                statement.setLong(1, Long.valueOf(id));
                statement.setLong(2, tcost);
                statement.setLong(3, amountpaid);
                statement.setLong(4, Long.valueOf(balance));
                statement.setString(5, sign_by);
                statement.setLong(6, Long.valueOf(receipt_no));
                statement.setString(7, date);

                int result=statement.executeUpdate();

                if(result==1)
                {
                    System.out.println("Successful");
                }

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            */
            }


        });

        amountpaidfld.textProperty().addListener((observable, oldvalue, newvalue)->{

            if(newvalue.length()>0){
                long cost=-1;
                try{
                    cost=Long.valueOf(totalchargefld.getText())-Long.valueOf(amountpaidfld.getText());
                }
                catch(Exception ex){
                    ex.printStackTrace();

                }
                if(cost>=0){
                    balancefld.setText(String.valueOf(cost));
                }
            }
            else{
                balancefld.setText(String.valueOf(0));
            }

        });
        balancefld.setEditable(false);


    }

    public PaymentController(String id, String status, long totalvalue)throws IOException{
        this.totalvalue=totalvalue;

        this.status=status;

        this.id=id;
        FXMLLoader fxmlloader = new FXMLLoader(PatientRegistry.class.getResource("payment.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
    void setName(String surname, String othername)
    {
        surnamefld.setText(surname);
        othernamefld.setText(surname);

    }
}
