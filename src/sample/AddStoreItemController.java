package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 * Created by Ese on 5/11/2017.
 */
public class AddStoreItemController extends VBox {

    @FXML
    private TextField ratefld;

    @FXML
    private Button savebutton;

    @FXML
    private TextField receiptnofld;

    @FXML
    private TextField item_typefld;

    @FXML
    private TextField receivedbyfld;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField totalfld;

    @FXML
    private Button refreshbutton;

    @FXML
    private TextField quantityfld;

    @FXML
    void initialize(){
        datepicker.setConverter(DateConverter.convert());
        datepicker.setValue(LocalDate.now());
        datepicker.setEditable(false);


        receivedbyfld.setText(Controller.sign_by);
        receivedbyfld.setEditable(false);

        totalfld.setText("0");
        quantityfld.setText("0");
        ratefld.setText("0");

        refreshbutton.setOnAction(e->{
            item_typefld.setText("");
            receiptnofld.setText("");


        });




        totalfld.textProperty().addListener((obsrvable, oldvalue, newvalue)-> {

            if (newvalue != null && newvalue.length() > 0) {
                if (!MyUtil.isInteger(newvalue)) {
                    totalfld.setText(oldvalue);
                } else {

                }
             }

        });

        quantityfld.textProperty().addListener((obsrvable, oldvalue, newvalue)-> {

            if (newvalue != null && newvalue.length() > 0) {
                if (!MyUtil.isInteger(newvalue)) {
                    quantityfld.setText(oldvalue);
                } else {

                }
            }

        });

        ratefld.textProperty().addListener((obsrvable, oldvalue, newvalue)-> {

            if (newvalue != null && newvalue.length() > 0) {
                if (!MyUtil.isInteger(newvalue)) {
                    ratefld.setText(oldvalue);
                } else {

                }
            }



        });

        ratefld.textProperty().addListener((observable, oldvalue, newvalue)-> {
            if(quantityfld.getText().length()>0) {

                if (newvalue.length() > 0 && newvalue != null) {
                    try {
                        long quantity = Long.valueOf(quantityfld.getText());
                        long rate = Long.valueOf(newvalue);
                        long result = rate * quantity;
                        totalfld.setText(String.valueOf(result));
                    }
                    catch(NumberFormatException ex){
                        ex.printStackTrace();
                    }
                }
            }
            else{
                return;
            }

        });

        receiptnofld.textProperty().addListener((observable, oldvalue, newvalue)->{

            if (newvalue != null && newvalue.length() > 0) {
                if (!MyUtil.isInteger(newvalue)) {
                    receiptnofld.setText(oldvalue);
                } else {

                }
            }


        });


        totalfld.setEditable(false);
        savebutton.setOnAction(e->{
            if(item_typefld.getText().length()<=0 || quantityfld.getText().length()<=0 || receiptnofld.getText().length()<=0){
                ShowDialog.show("Boxes marked in red must be filled");
                return;
            }
            String item_sql="select item_type from store_item where item_type=?";
            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(item_sql)){
                stmt.setString(1, item_typefld.getText());
                ResultSet rs=stmt.executeQuery();
                if(rs.next()){
                    ShowDialog.show("Item type already in database");

                    return;
                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            String sql="INSERT INTO store_item(date, item_type, receipt_no, quantity, rate, total, received_by) values(?,?,?,?,?,?,?)";
            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
                stmt.setString(1, datepicker.getValue().toString());
                stmt.setString(2, item_typefld.getText());
                stmt.setString(3, receiptnofld.getText());
                stmt.setLong(4, Long.valueOf(quantityfld.getText()));
                stmt.setLong(5, Long.valueOf(ratefld.getText()));
                stmt.setLong(6, Long.valueOf(totalfld.getText()));
                stmt.setString(7, receivedbyfld.getText());
                int d=stmt.executeUpdate();
                if(d==1){
                    ShowDialog.show("Operation Successful");
                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        });


    }

    public AddStoreItemController()throws IOException {
        FXMLLoader fxmlloader=new FXMLLoader(AddStoreItemController.class.getResource("addstore_items.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
}
