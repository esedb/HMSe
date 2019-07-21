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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 1/14/2016.
 */
public class AddLabTest extends VBox {
    @FXML
    private Button savebutton;

    @FXML
    private TextField testnamefld;

    @FXML
    private HBox tableviewbox;

    @FXML
    private Button addtestbutton;

    @FXML
    private ChoiceBox<?> testtypechoice;

    @FXML
    private TextField normalvaluefld;

    @FXML
    private TextField amountfld;
    @FXML
    void initialize()
    {
        ObservableList olist= FXCollections.<String>observableArrayList();
        String validate="";
        try{
            String sql="SELECT * FROM lab_test_type";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                validate=rs.getString("test_type");
                if(validate!=null && validate!="")
                {
                    olist.addAll(validate);
                }

            }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        testtypechoice.setItems(olist);
        if(validate=="" || validate==null)
        {

                this.setDisable(true);
                Stage stage=new Stage();
                try{
                    VBox vbox=this;
                    LabTestType tt=new LabTestType();
                    tt.stage=stage;
                    tt.vbox=this;
                    Scene scene=new Scene(tt);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.setOnCloseRequest(e->
                    {
                       vbox.setDisable(false);
                    });
                    stage.show();
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
        }
        addtestbutton.setOnAction(e->
        {
            this.setDisable(true);
            Stage stage=new Stage();
            try{
                LabTestType tt=new LabTestType();
                tt.stage=stage;
                tt.vbox=this;
                Scene scene=new Scene(tt);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setOnCloseRequest(ev->
                {
                    this.setDisable(false);
                });
                stage.show();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        });
        ObservableList typetlist=FXCollections.observableArrayList();
        try{
            String sql="SELECT * FROM laboratory_test";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                typetlist.addAll(new AddLabTestBeans(rs.getString("test_type"), rs.getString("test_name"), rs.getString("normal_value"), rs.getLong("amount"), rs.getInt("amount")));
                System.out.println("Amount is " + rs.getString("amount"));

            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        TableView tableview=new TableView();

        TableColumn ttesttype=new TableColumn("Test Type");
        ttesttype.setCellValueFactory(new PropertyValueFactory<AddLabTestBeans, String>("testtype"));

        TableColumn ttestname=new TableColumn("Test Name");
        ttestname.setCellValueFactory(new PropertyValueFactory<AddLabTestBeans, String>("testname"));

        TableColumn tnormalvalue=new TableColumn("Normal Value");
        tnormalvalue.setCellValueFactory(new PropertyValueFactory<AddLabTestBeans, String>("normalvalue"));

        TableColumn tamount=new TableColumn("Amount");
        tamount.setCellValueFactory(new PropertyValueFactory<AddLabTestBeans, String>("amount)"));

        TableColumn tamount2=new TableColumn("Amount");
        tamount.setCellValueFactory(new PropertyValueFactory<AddLabTestBeans, String>("amount2)"));

        tableview.setItems(typetlist);
        tableview.getColumns().addAll(ttesttype, ttestname, tnormalvalue, tamount,tamount2);


        tableview.prefWidthProperty().bind(tableviewbox.widthProperty());

        tableviewbox.getChildren().add(tableview);



    }

    @FXML
    void saveAction(ActionEvent event) {
        long amount=0;
        Object ttype=testtypechoice.getSelectionModel().getSelectedItem();
        if(amountfld.getText().length()>0 && ttype!=null) {
            try{
                amount=Long.valueOf(amountfld.getText());

            }
            catch(NumberFormatException ex)
            {
                ex.printStackTrace();
            }



            if (normalvaluefld.getText().length()>0 && normalvaluefld.getText().length()>0
                    && testnamefld.getText().length()>0 && testnamefld.getText().length()>0)
            {
                try {
                    String sql = "INSERT INTO laboratory_test (test_type, test_name, normal_value, amount) values(?,?,?,?)";
                    DataBase dbase = new DataBase();
                    Connection con = dbase.getConnection();
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1, testtypechoice.getValue().toString());
                    statement.setString(2, testnamefld.getText());
                    statement.setString(3, normalvaluefld.getText());
                    statement.setLong(4, amount);
                    int result = statement.executeUpdate();
                    if (result == 1) {
                        System.out.println("Successful");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            System.out.println("You should specify amount");
        }

    }

    public AddLabTest() throws IOException
    {
        FXMLLoader fxmlloader=new FXMLLoader(AddLabTest.class.getResource("addlaboratorytest.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }

}
