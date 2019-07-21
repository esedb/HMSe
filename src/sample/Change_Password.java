package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 10/25/2016.
 */
public class Change_Password extends VBox {


    @FXML
    private TextField firstnamefld;

    @FXML
    private Button c_password;

    @FXML
    private TextField lastnamefld;

    @FXML
    private ChoiceBox<?> departmentchb;

    @FXML
    private PasswordField n_passwordfld;

    @FXML
    private Button clearbutton;

    @FXML
    private PasswordField o_passwordfld;


    @FXML
    public void initialize(){

        clearbutton.setOnAction(e->{
            firstnamefld.setText("");
            lastnamefld.setText("");
            o_passwordfld.setText("");
            n_passwordfld.setText("");

        });

        ObservableList olist= FXCollections.observableArrayList();
        olist.add("None");
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement("select department from department_records")){

            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                olist.add(rs.getString("department"));
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        departmentchb.setItems(olist);
        departmentchb.getSelectionModel().selectFirst();

        c_password.setOnAction(e->{
            if(departmentchb.getValue().toString().equalsIgnoreCase("None") || firstnamefld.getText().length()<=0 || lastnamefld.getText().length()<=0
                    || n_passwordfld.getText().length()<=0){

                ShowDialog.show("Fill in values for Box mark in red");
                return;
            }
            String sql="select * from account_records where firstname=? and lastname =? and password=?";
            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection();
                PreparedStatement stmt=con.prepareStatement("select * from account_records where firstname=? and lastname =? and password=? and department=?")){
                stmt.setString(1, firstnamefld.getText());
                stmt.setString(2, lastnamefld.getText());
                stmt.setString(3, o_passwordfld.getText());
                stmt.setString(4, departmentchb.getValue().toString());
                ResultSet rs=stmt.executeQuery();
                if(rs.next()){
                    changePassword(firstnamefld.getText(), lastnamefld.getText(), o_passwordfld.getText(), n_passwordfld.getText(), con);
                    rs.close();
                }
                else{

                    ShowDialog.show("Operation unsuccessful check values for correctness");
                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        });

    }

    private void changePassword(String firstname, String lastname, String oldpassword, String newpassword, Connection con) {
        if(con!=null){

            try(PreparedStatement stmt=con.prepareStatement("update account_records set password=? where firstname=? and lastname =? and password=?")){
                stmt.setString(1, newpassword);
                stmt.setString(2, firstname);
                stmt.setString(3, lastname);
                stmt.setString(4, oldpassword);
                int m=stmt.executeUpdate();
                if(m==1){
                    ShowDialog.show("Operation Successful");
                }

            }
            catch(Exception ex){
                ex.printStackTrace();
                ShowDialog.show("Operation Not successful");
            }

        }
    }


    public Change_Password() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(PatientRegistry.class.getResource("change_password.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }

}
