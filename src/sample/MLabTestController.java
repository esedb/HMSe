package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ese on 2/3/2016.
 */
public class MLabTestController extends VBox {
    @FXML
    private Button savebutton;

    @FXML
    private TextField othernamefld;

    @FXML
    private ChoiceBox<?> testtypechoice;

    @FXML
    private TextField requestfld;

    @FXML
    private TextField patientidfld;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField surnamefld;


    Connection con;
    DataBase dbase;
    String id;


    @FXML
    private void initialize()
    {
        ObservableList lablist=FXCollections.observableArrayList();
        lablist.add("None");
        String choicesql="Select * from templates where lab_or_surgery='Laboratory Test'";
        Set<String> ssetn=new HashSet<>();
        try(DataBase dbase=new DataBase(); Connection conn=dbase.getConnection();
            PreparedStatement stmt=conn.prepareStatement(choicesql)){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                ssetn.add(rs.getString("service_name"));
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        if(!ssetn.isEmpty()){
            for(String values: ssetn){
                lablist.add(values);
            }

            testtypechoice.setItems(lablist);
            testtypechoice.getSelectionModel().selectFirst();
        }



        StringConverter converter=DateConverter.convert();
        datepicker.setValue(LocalDate.now());
        datepicker.setConverter(converter);




        requestfld.setText(Controller.sign_by);
        requestfld.setEditable(false);

        patientidfld.setText(id);
        patientidfld.setEditable(false);

        String selectsql="SELECT * from patient_registry where id_sn=?";

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(selectsql)){
            stmt.setString(1, id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                surnamefld.setText(rs.getString("firstname"));
                othernamefld.setText(rs.getString("lastname") + " " + rs.getString("Othername"));

            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        savebutton.setOnAction(e->
        {
            String ttvalue=(String)testtypechoice.getSelectionModel().getSelectedItem();
            if(ttvalue==null || ttvalue.equalsIgnoreCase("None")){
                ShowDialog.show("Select a test type");
            }

            if(patientidfld.getText().length()>0 && !ttvalue.equalsIgnoreCase("None") && ttvalue.length()>0)
            {
                String insertsql="INSERT INTO will_parry.lab_patient (id_sn,  surname, othername, lab_type) " +
                        "VALUES (?, ?, ?, ?)";
               try (DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(insertsql)){

                       stmt.setString(1, patientidfld.getText());
                       stmt.setString(2, surnamefld.getText());
                       stmt.setString(3, othernamefld.getText());
                       stmt.setString(4, testtypechoice.getValue().toString());

                       int result=stmt.executeUpdate();
                       if(result==1)
                       {
                           LaboratoryPatientList lpl=new LaboratoryPatientList();
                           Scene scene=new Scene(lpl);
                           Stage stage=new Stage();
                           stage.setScene(scene);
                           stage.setResizable(false);
                           stage.initModality(Modality.APPLICATION_MODAL);
                           stage.show();
                       }
               }
               catch(Exception ex)
               {
                   ex.printStackTrace();
               }

            }

        });


    }
    public MLabTestController(String id) throws IOException {
        this.id=id;
        FXMLLoader fxmlloader=new FXMLLoader(MLabTestController.class.getResource("mlabtest.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
    public void updatetable()
    {

    }
}
