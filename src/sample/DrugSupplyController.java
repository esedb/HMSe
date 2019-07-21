package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
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
 * Created by Ese on 1/4/2016.
 */
public class DrugSupplyController extends VBox{
    @FXML
    private TextField costfld;

    @FXML
    private DatePicker frompicker;

    @FXML
    private ChoiceBox<?> ssuplierchoice;

    @FXML
    private ChoiceBox<?> itemsupchoice;

    @FXML
    private HBox tableviewerbox;

    @FXML
    private Button searchbutton;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField totalvolumefld;

    @FXML
    private ChoiceBox<?> drugchoice;

    @FXML
    private ChoiceBox<?> oldcostchoice;

    @FXML
    private Button clearbutton;

    @FXML
    private DatePicker topicker;

    @FXML
    private VBox accountformvbox;

    @FXML
    private ChoiceBox<?> supplierchoice;

    @FXML
    private TextField quantityfld;

    @FXML
    void clearAction(ActionEvent event) {
        costfld.setText("");
        quantityfld.setText("");
        totalvolumefld.setText("");

    }
    @FXML
    private void initialize()
    {

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
        frompicker.setConverter(converter);
        frompicker.setValue(LocalDate.now());
        topicker.setValue(LocalDate.now());
        topicker.setConverter(converter);
    }

    @FXML
    void searchAction(ActionEvent event) {

        String sql="SELECT dateofreg FROM patient_registry WHERE dateofreg BETWEEN ? AND ?" ;
        Connection con;

        try{
            DataBase dbase=new DataBase();
            Date fmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(frompicker.getValue().toString());
            Date tmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(frompicker.getValue().toString());

            java.sql.Date fmySqlDate = new java.sql.Date(fmyDate.getTime());
            java.sql.Date tmySqlDate = new java.sql.Date(tmyDate.getTime());
            con=dbase.getConnection();
            PreparedStatement prepstatement=con.prepareStatement(sql);
            prepstatement.setDate(1, fmySqlDate);
            prepstatement.setDate(2, tmySqlDate);
            prepstatement.setString(2, topicker.getValue().toString());

            ResultSet rs=prepstatement.executeQuery();
            while(rs.next())
            {

                System.out.println(rs.getString("dateofreg"));
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public DrugSupplyController() throws IOException
    {
        FXMLLoader fxmlloader=new FXMLLoader(DrugSupplyController.class.getResource("drugsupplier.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }

}
