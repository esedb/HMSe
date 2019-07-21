package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 * Created by Ese on 7/7/2016.
 */
public class AddWardAndBedController extends VBox {
    @FXML
    private Button savebutton;

    @FXML
    private Button cancelbutton;

    @FXML
    private Button updatebutton;

    @FXML
    private TextField ward_numberfld;

    @FXML
    private TextField bed_numberfld;


    public AddWardAndBedController() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(AddWardAndBedController.class.getResource("addwardandbed.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
    int ward=0;
    int bed=0;

    @FXML
    public void initialize()
    {
        ward_numberfld.textProperty().addListener((observable, oldvalue, newvalue)->{
            if(newvalue!=null && newvalue.length()>0) {

                if (!MyUtil.isInteger(newvalue)) {
                    ward_numberfld.setText(oldvalue);
                }
                else {

                }
            }


        });
        bed_numberfld.textProperty().addListener((observable, oldvalue, newvalue)->{
            if(newvalue!=null && newvalue.length()>0) {

                if (!MyUtil.isInteger(newvalue)) {
                    bed_numberfld.setText(oldvalue);
                }
                else {

                }
            }


        });
        savebutton.setOnAction(e->{

            if(bed_numberfld.getText().length()>0 && ward_numberfld.getText().length()>0) {
                if(checkIfExits()){
                    int d= deleteData();
                    if(d>0){
                        System.out.println("This part of the programme deleteData() ran");
                        int m=populateTable();
                        if(m>0){
                            System.out.println("Table deleted and created successful populated successful");
                        }
                    }
                }
                else{
                    int c=createTable();
                    if(c>0){
                        int p=populateTable();
                        if(p>0){
                            System.out.println("Table created and populated successful");
                        }
                    }
                }
            }


        });



    }

    boolean checkIfExits(){
        try(DataBase dbase=new  DataBase(); Connection con=dbase.getConnection();){
            DatabaseMetaData md = con.getMetaData();
            ResultSet rs = md.getTables(null, null, "ward_and_bed", null);
            if(rs.next()){
                return true;
            }
        }

        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    int createTable(){

        String createtablesql="CREATE TABLE ward_and_bed (ward bigint not null, bed bigint not null, " +
                "status varchar(200) not null default 'vacant', date date null," +
                "patient_id bigint null default '0', name varchar(200) not null default 'None')";
        try(DataBase dbase=new DataBase(); Connection con =dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(createtablesql)){
            int d=stmt.executeUpdate();
            System.out.println("d is: " + d);
            System.out.println("create statement worked successful");

        }
        catch(Exception ex){
            ex.printStackTrace();
            return 0;
        }

        return 1;

    }

    int deleteTable(){

        String deletetablesql="drop table ward_and_bed";
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(deletetablesql)) {
            int m = stmt.executeUpdate();
            System.out.println("Delete statement worked successful");
            System.out.println("m is:  " + m);

        }
        catch(Exception ex){
            ex.printStackTrace();
            return 0;
        }

        return 1;
    }
    int populateTable(){
        DatePicker datepicker=new DatePicker();
        datepicker.setConverter(DateConverter.convert());
        datepicker.setValue(LocalDate.now());
        datepicker.setEditable(false);
        try{
            int numberofbeds=Integer.parseInt(bed_numberfld.getText());
            int numberofwards=Integer.parseInt(ward_numberfld.getText());
            String sql="insert into ward_and_bed(ward, bed, date) values(?,?,?)";
            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
                for(int j=1; j<=numberofwards; j++){
                    for(int i=1; i<=numberofbeds; i++){
                        stmt.setInt(1, j);
                        stmt.setInt(2, i);
                        stmt.setString(3, datepicker.getValue().toString());
                        int d=stmt.executeUpdate();
                        if(d==1){
                            System.out.println("created: " + i + "\t" + j);
                        }
                    }
                }

            }
            catch(Exception ex){
                ex.printStackTrace();
                return 0;
            }
        }
        catch(NumberFormatException ex){
            ex.printStackTrace();
            return 0;
        }

        return 1;

    }
    int deleteData(){
        String deletesql="delete from ward_and_bed";
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(deletesql)){
            int d=stmt.executeUpdate();
            return 1;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return 0;
        }

    }

}
