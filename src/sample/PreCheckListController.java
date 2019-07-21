package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ese on 2/16/2016.
 */
public class PreCheckListController extends VBox {
    @FXML
    private DatePicker tdatepicker;

    @FXML
    private Button savebutton;

    @FXML
    private TextField othernamefld;

    @FXML
    private HBox hboxc;

    @FXML
    private TextField surnamefld;

    @FXML
    private CheckBox q11;

    @FXML
    private CheckBox q10;

    @FXML
    private CheckBox q13;

    @FXML
    private CheckBox q12;

    @FXML
    private CheckBox q15;

    @FXML
    private CheckBox q14;

    @FXML
    private CheckBox q17;

    @FXML
    private CheckBox q16;

    @FXML
    private TextField checkbyfld;

    @FXML
    private Button clearbutton;

    @FXML
    private Button selectfld;

    @FXML
    private TextField patientidfld;

    @FXML
    private TextField agefld;

    @FXML
    private CheckBox q1;

    @FXML
    private CheckBox q2;

    @FXML
    private CheckBox q3;

    @FXML
    private CheckBox q4;

    @FXML
    private CheckBox q5;

    @FXML
    private TextField employeenofld;

    @FXML
    private CheckBox q6;

    @FXML
    private CheckBox q7;

    @FXML
    private Button updatebutton;

    @FXML
    private CheckBox q8;

    @FXML
    private CheckBox q9;

    @FXML
    private TextField psurgeryfld;

    @FXML
    private TextField bloodgroupfld;


    String id;

    @FXML
    void initialize()
    {
        String datasql="select * from surgery_patient where id_sn=?";
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(datasql)){
            stmt.setString(1, id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                surnamefld.setText(rs.getString("surname"));
                othernamefld.setText(rs.getString("othername"));
                psurgeryfld.setText(rs.getString("surgery_type"));
                patientidfld.setText(id);

            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }


        tdatepicker.setConverter(DateConverter.convert());
        tdatepicker.setValue(LocalDate.now());

        checkbyfld.setText(Controller.sign_by);
        checkbyfld.setEditable(false);

        patientidfld.setText(id);
        patientidfld.setEditable(false);
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); ){
            String sql="SELECT * FROM patient_registry where id_sn=?";
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                surnamefld.setText(rs.getString("firstname"));
                othernamefld.setText(rs.getString("othername"));
                agefld.setText(rs.getString("age"));
                checkbyfld.setText(Controller.sign_by);
                bloodgroupfld.setText(rs.getString("bloodgroup"));


            }
            rs.close();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        agefld.setDisable(true);
        checkbyfld.setDisable(true);
        bloodgroupfld.setDisable(true);

        List<String> selectedlist=new ArrayList<>();
        StringBuffer buffer=new StringBuffer();

        savebutton.setOnAction(e->{

            for(int i = 1; i<=17; i++){
                Node node =hboxc.lookup("#q" + i);
                CheckBox cb=(CheckBox) node;
                if(cb.isSelected()){
                    selectedlist.add(cb.getText());
                }
            }

            if(!selectedlist.isEmpty()) {

                for(int i=0; i<selectedlist.size(); i++){
                    if(i==0){
                        buffer.append("_" + selectedlist.get(i)+ "_");

                    }
                    else{
                        buffer.append(selectedlist.get(i)+ "_");
                    }
                }

                /*for (String value : selectedlist) {
                    buffer.append("_" + value+"_");
                }
                */
            }
            else{
                System.out.println("The list is empty");
                return;
            }

            String clistsql="INSERT INTO opcl_data(id_sn, surname, othername, surgery, data, age, bloodgroup, sign_by) values(?,?,?,?,?,?,?,?)";

            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection();
                PreparedStatement stmt=con.prepareStatement(clistsql)){
                stmt.setString(1, patientidfld.getText());
                stmt.setString(2, surnamefld.getText());
                stmt.setString(3, othernamefld.getText());
                stmt.setString(4, psurgeryfld.getText());
                stmt.setString(5, buffer.toString());
                stmt.setInt(6, Integer.valueOf(agefld.getText()));
                stmt.setString(7, bloodgroupfld.getText());
                stmt.setString(8, checkbyfld.getText());

                int m =stmt.executeUpdate();
                if (m==1){
                    String patientid=patientidfld.getText();
                    String surgerytype=psurgeryfld.getText();

                    DataTemplateController dc=new DataTemplateController(surgerytype, patientid);
                    Scene scene =new Scene(dc);
                    Stage stage=new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();

                }



            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        });


    }
    public PreCheckListController(String id) throws IOException {

        this.id=id;
        FXMLLoader fxmlloader=new FXMLLoader(TodaysPrescription.class.getResource("prechecklist.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }
}
