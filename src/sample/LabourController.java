package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 * Created by Ese on 2/12/2016.
 */
public class LabourController extends VBox {
    @FXML
    private TextField volfld;

    @FXML
    private TableView<?> foetaltableview;

    @FXML
    private TextField cervicaltimefld;

    @FXML
    private Button uterineupdatebutton;

    @FXML
    private TextField protfld;

    @FXML
    private Button oxysavebutton;

    @FXML
    private TextField fluiddrugfld;

    @FXML
    private Button fluidupdatebutton;

    @FXML
    private TextField liquorfld;

    @FXML
    private Button foetalsavebutton;

    @FXML
    private Button uterinesavebutton;

    @FXML
    private Button cervicalupdatebutton;

    @FXML
    private TableView<?> uterinetableview;

    @FXML
    private Button oxyupdatebutton;

    @FXML
    private Button urinesavebutton;

    @FXML
    private Button caputsavebutton;

    @FXML
    private Button maternalupdatebutton;

    @FXML
    private TableView<?> maternaltableview;

    @FXML
    private TableView<?> caputtableview;

    @FXML
    private TextField caputfld;

    @FXML
    private ChoiceBox<?> cevicaldilachoice;

    @FXML
    private TextField maternalpulsefld;

    @FXML
    private TextField mouldingfld;

    @FXML
    private DatePicker cervicaldate;

    @FXML
    private Button caputupdatebutton;

    @FXML
    private TextField fluidfld;

    @FXML
    private DatePicker caputdate;

    @FXML
    private TextField maternaltimefld;

    @FXML
    private TextField oxytocinunitfld;

    @FXML
    private TextField urineduration;

    @FXML
    private TableView<?> urinetableview;

    @FXML
    private DatePicker foetaldate;

    @FXML
    private Button urineupdatebutton;

    @FXML
    private TableView<?> cervicaltable;

    @FXML
    private Button foetalupdatebutton;

    @FXML
    private TextField maternalbpfld;

    @FXML
    private TableView<?> fluidtableview;

    @FXML
    private Button maternalsavebutton;

    @FXML
    private TextField urinemins;

    @FXML
    private Button cervicalsavebutton;

    @FXML
    private Button fluidsavebutton;

    @FXML
    private TextField dropminfld;

    @FXML
    private TableView<?> oxytableview;

    @FXML
    private TextField caputtime;

    @FXML
    private TextField urineacetfld;

    @FXML
    private ChoiceBox<?> foetalheartchoice;

    String id;

    @FXML
    void initialize() {
        cervicaldate.setConverter(DateConverter.convert());
        cervicaldate.setValue(LocalDate.now());
        foetaldate.setConverter(DateConverter.convert());
        foetaldate.setValue(LocalDate.now());
        caputdate.setConverter(DateConverter.convert());
        caputdate.setValue(LocalDate.now());

        foetalupdatebutton.setOnAction(e->{foetalTable();});
        maternalupdatebutton.setOnAction(e->{maternalTable();});
        cervicalupdatebutton.setOnAction(e->{cervicalTable();});
        caputupdatebutton.setOnAction(e->{caputTable();});
        uterineupdatebutton.setOnAction(e->{uterineTable();});
        oxyupdatebutton.setOnAction(e->{oxytocinTable();});
        fluidupdatebutton.setOnAction(e->{fluidTable();});
        urineupdatebutton.setOnAction(e->{urineTable();});

        ObservableList hrlist=FXCollections.observableArrayList();





                foetalsavebutton.setOnAction(e -> {
            Object foetal = foetalheartchoice.getSelectionModel().getSelectedItem();
            if (foetal != null) {

                String foetalchoice = (String) foetal;
                String foetalliquor = liquorfld.getText();
                String date = foetaldate.getValue().toString();
                try {
                    String sql = "INSERT INTO will_parry.foetal_heart (patient_id, foetal_heart_rate, liquor_time, `date`) " +
                            "VALUES (?, ?, ?, ?) ";
                    DataBase dbase = new DataBase();
                    Connection con = dbase.getConnection();
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1, id);
                    statement.setString(2, foetalliquor);
                    statement.setString(3, date);
                    int result = statement.executeUpdate();
                    if (result == 1) {
                        System.out.println("Successful: _ ");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();

                }

            }

        });

        maternalsavebutton.setOnAction(e -> {
            if (maternalpulsefld.getText().length() > 0 && maternalbpfld.getText().length() > 0 && maternaltimefld.getText().length() > 0) {
                String maternalpulse = maternalpulsefld.getText();
                String maternalbp = maternalbpfld.getText();
                String maternaltime = maternaltimefld.getText();
                try {
                    String sql = "INSERT INTO will_parry.maternal_records (patient_id, maternal_id, matenal_pulse, time) " +
                            "VALUES (?, ?, ?, ?) ";
                    DataBase dbase = new DataBase();
                    Connection con = dbase.getConnection();
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1, id);
                    statement.setString(2, maternalbp);
                    statement.setString(3, maternalpulse);
                    statement.setString(4, maternaltime);
                    int result = statement.executeUpdate();
                    if (result == 1) {
                        System.out.println("Successful: _ ");
                        con.close();
                        con = null;
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();

                }

            }

        });


        cervicalsavebutton.setOnAction(e -> {

            LocalDate date = cervicaldate.getValue();
            Object cervicaldilation = cevicaldilachoice.getSelectionModel().getSelectedItem();
            if (date != null && cervicaldilation != null && cervicaltimefld.getText().length() > 0) {
                String cevicdate = date.toString();
                String cevidila = (String) cervicaldilation;
                String cervicaltime = cervicaltimefld.getText();
                try {
                    String sql = "INSERT INTO will_parry.cervical_records (patient_id, cervical_dilation, time, date) " +
                            "VALUES (?, ?, ?, ?) ";
                    DataBase dbase = new DataBase();
                    Connection con = dbase.getConnection();
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1, id);
                    statement.setString(2, cevidila);
                    statement.setString(3, cervicaltime);
                    statement.setString(4, cevicdate);
                    int result = statement.executeUpdate();
                    if (result == 1) {
                        System.out.println("Successful: _ ");
                        con.close();
                        con = null;
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();

                }


            }

        });


        caputsavebutton.setOnAction(e -> {

            Object date = caputdate.getValue();

            if (date != null && caputfld.getText().length() > 0 && caputtime.getText().length() > 0 && mouldingfld.getText().length() > 0) {
                try {
                    String sql = "INSERT INTO will_parry.caput_records (patient_id, date, time, moulding) " +
                            "VALUES (?, ?, ?, ?) ";
                    DataBase dbase = new DataBase();
                    Connection con = dbase.getConnection();
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1, id);
                    statement.setString(2, date.toString());
                    statement.setString(3, caputtime.getText());
                    statement.setString(4, mouldingfld.getText());
                    int result = statement.executeUpdate();
                    if (result == 1) {
                        System.out.println("Successful: _ ");
                        con.close();
                        con = null;
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();

                }

            }


        });


        uterinesavebutton.setOnAction(e -> {
            //uterine is urine mistake
            if (urinemins.getText().length() > 0 && urineduration.getText().length() > 0) {
                try {
                    String sql = "INSERT INTO will_parry.uterine_record (patient_id, mins, duration) " +
                            "VALUES (?, ?, ?)";
                    DataBase dbase = new DataBase();
                    Connection con = dbase.getConnection();
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1, id);
                    statement.setString(2, urinemins.getText());
                    statement.setString(3, urineduration.getText());

                    int result = statement.executeUpdate();
                    if (result == 1) {
                        System.out.println("Successful: _ ");
                        con.close();
                        con = null;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });


        oxysavebutton.setOnAction(e->{
            if(oxytocinunitfld.getText().length()>0 && dropminfld.getText().length()>0)
            {
                try {
                    String sql = "INSERT INTO will_parry.oxytocin_record (patient_id, unit, drops) " +
                            "VALUES (?, ?, ?)";
                    DataBase dbase = new DataBase();
                    Connection con = dbase.getConnection();
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1, id);
                    statement.setString(2, oxytocinunitfld.getText());
                    statement.setString(3, dropminfld.getText());

                    int result = statement.executeUpdate();
                    if (result == 1) {
                        System.out.println("Successful: _ ");
                        con.close();
                        con = null;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });

        fluidsavebutton.setOnAction(e->{
            if(fluiddrugfld.getText().length()>0 && fluidfld.getText().length()>0)
            {
                try {
                    String sql = "INSERT INTO will_parry.fluid_records (patient_id, fluid, drug) " +
                            "VALUES (?, ?, ?)";
                    DataBase dbase = new DataBase();
                    Connection con = dbase.getConnection();
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1, id);
                    statement.setString(2, fluidfld.getText());
                    statement.setString(3, fluiddrugfld.getText());

                    int result = statement.executeUpdate();
                    if (result == 1) {
                        System.out.println("Successful: _ ");
                        con.close();
                        con = null;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        });


        urinesavebutton.setOnAction(e->{

            if(urineacetfld.getText().length()>0 && protfld.getText().length()>0 && volfld.getText().length()>0)
            {
                try {
                    String sql = "INSERT INTO will_parry.urine_records (patient_Id, acet, port, vol) " +
                            "VALUES (?, ?, ?, ?)";
                    DataBase dbase = new DataBase();
                    Connection con = dbase.getConnection();
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1, id);
                    statement.setString(2, urineacetfld.getText());
                    statement.setString(3, protfld.getText());
                    statement.setString(4, volfld.getText());

                    int result = statement.executeUpdate();
                    if (result == 1) {
                        System.out.println("Successful: _ ");
                        con.close();
                        con = null;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });




    }

    public LabourController(String id) throws IOException {
        this.id=id;
        FXMLLoader fxmlloader = new FXMLLoader(PatientRegistry.class.getResource("labour_records.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }

    void foetalTable()
    {
        ObservableList foetallist= FXCollections.observableArrayList();
        foetaltableview.getItems().clear();
        foetaltableview.getColumns().clear();

        try{
            String sql="SELECT * FROM foetal_heart where patient_id=?";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement =con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                LabourFoetalHeartBeans fb=new LabourFoetalHeartBeans(rs.getString("foetal_heart_rate"), rs.getString("liquor_time"),rs.getString("date"));
                foetallist.addAll(fb);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        foetaltableview.setItems(foetallist);

        TableColumn tfoetalrate=new TableColumn("Foetal heart rate");
        tfoetalrate.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("foetalheartrate"));


        TableColumn tliquortime=new TableColumn("Liquor Time");
        tliquortime.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("Liquorttime"));

        TableColumn tdate=new TableColumn("Date");
        tdate.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("date"));

        foetaltableview.getColumns().addAll(tfoetalrate, tliquortime, tdate);


    }

    void maternalTable()
    {
        ObservableList foetallist= FXCollections.observableArrayList();
        maternaltableview.getItems().clear();
        maternaltableview.getColumns().clear();

        try{
            String sql="SELECT * FROM maternal_records where patient_id=?";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement =con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                LabourFoetalHeartBeans fb=new LabourFoetalHeartBeans(rs.getString("maternal_id"), rs.getString("matenal_pulse"),rs.getString("time"));
                foetallist.addAll(fb);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        maternaltableview.setItems(foetallist);

        TableColumn tfoetalrate=new TableColumn("Maternal B.P");
        tfoetalrate.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("foetalheartrate"));


        TableColumn tliquortime=new TableColumn("Pulse");
        tliquortime.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("Liquorttime"));

        TableColumn tdate=new TableColumn("Time");
        tdate.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("date"));

        maternaltableview.getColumns().addAll(tfoetalrate, tliquortime, tdate);

    }

    void cervicalTable()
    {
        ObservableList foetallist= FXCollections.observableArrayList();
        cervicaltable.getItems().clear();
        cervicaltable.getColumns().clear();

        try{
            String sql="SELECT * FROM cervical_records where patient_id=?";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement =con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                LabourFoetalHeartBeans fb=new LabourFoetalHeartBeans(rs.getString("cervical_dilation"), rs.getString("time"),rs.getString("date"));
                foetallist.addAll(fb);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        maternaltableview.setItems(foetallist);

        TableColumn tfoetalrate=new TableColumn("Cervical Dilation");
        tfoetalrate.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("foetalheartrate"));


        TableColumn tliquortime=new TableColumn("Time");
        tliquortime.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("Liquorttime"));

        TableColumn tdate=new TableColumn("Date");
        tdate.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("date"));

        cervicaltable.getColumns().addAll(tfoetalrate, tliquortime, tdate);
    }

    void caputTable()
    {
        ObservableList foetallist= FXCollections.observableArrayList();
        caputtableview.getItems().clear();
        caputtableview.getColumns().clear();

        try{
            String sql="SELECT * FROM cervical_records where patient_id=?";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement =con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                LabourFoetalHeartBeans fb=new LabourFoetalHeartBeans(rs.getString("caput"), rs.getString("date"),rs.getString("time"), rs.getString("moulding"));
                foetallist.addAll(fb);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        caputtableview.setItems(foetallist);

        TableColumn tfoetalrate=new TableColumn("Caput");
        tfoetalrate.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("foetalheartrate"));


        TableColumn tliquortime=new TableColumn("Date");
        tliquortime.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("Liquorttime"));

        TableColumn tdate=new TableColumn("Date");
        tdate.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("date"));

        TableColumn tcaput=new TableColumn("Moulding");
        tcaput.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("argument4"));



        caputtableview.getColumns().addAll(tcaput, tfoetalrate, tliquortime, tdate);

    }

    void uterineTable()
    {
        ObservableList foetallist= FXCollections.observableArrayList();
        uterinetableview.getItems().clear();
        uterinetableview.getColumns().clear();

        try{
            String sql="SELECT * FROM uterine_record where patient_id=?";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement =con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                LabourFoetalHeartBeans fb=new LabourFoetalHeartBeans(rs.getString("mins"), rs.getString("duration"));
                foetallist.addAll(fb);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        uterinetableview.setItems(foetallist);

        TableColumn tfoetalrate=new TableColumn("Minute's");
        tfoetalrate.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("foetalheartrate"));


        TableColumn tliquortime=new TableColumn("Duration");
        tliquortime.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("Liquorttime"));

       uterinetableview.getColumns().addAll(tfoetalrate, tliquortime);


    }

    void oxytocinTable()
    {

        ObservableList foetallist= FXCollections.observableArrayList();
        oxytableview.getItems().clear();
        oxytableview.getColumns().clear();

        try{
            String sql="SELECT * FROM oxytocin_record where patient_id=?";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement =con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                LabourFoetalHeartBeans fb=new LabourFoetalHeartBeans(rs.getString("unit"), rs.getString("drops"));
                foetallist.addAll(fb);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        oxytableview.setItems(foetallist);

        TableColumn tfoetalrate=new TableColumn("Unit");
        tfoetalrate.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("foetalheartrate"));


        TableColumn tliquortime=new TableColumn("Drops");
        tliquortime.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("Liquorttime"));

        oxytableview.getColumns().addAll(tfoetalrate, tliquortime);
    }

    void fluidTable()
    {
        ObservableList foetallist= FXCollections.observableArrayList();
        fluidtableview.getItems().clear();
        fluidtableview.getColumns().clear();

        try{
            String sql="SELECT * FROM fluid_records where patient_id=?";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement =con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                LabourFoetalHeartBeans fb=new LabourFoetalHeartBeans(rs.getString("fluid"), rs.getString("drug"));
                foetallist.addAll(fb);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        fluidtableview.setItems(foetallist);

        TableColumn tfoetalrate=new TableColumn("Fluid");
        tfoetalrate.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("foetalheartrate"));


        TableColumn tliquortime=new TableColumn("Drugs");
        tliquortime.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("Liquorttime"));

        fluidtableview.getColumns().addAll(tfoetalrate, tliquortime);


    }

    void urineTable()
    {
        ObservableList foetallist= FXCollections.observableArrayList();
        urinetableview.getItems().clear();
        urinetableview.getColumns().clear();

        try{
            String sql="SELECT * FROM urine_records where patient_id=?";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement =con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                LabourFoetalHeartBeans fb=new LabourFoetalHeartBeans(rs.getString("acet"), rs.getString("port"),rs.getString("vol"));
                foetallist.addAll(fb);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        urinetableview.setItems(foetallist);

        TableColumn tfoetalrate=new TableColumn("ACET");
        tfoetalrate.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("foetalheartrate"));


        TableColumn tliquortime=new TableColumn("PROT");
        tliquortime.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("Liquorttime"));

        TableColumn tdate=new TableColumn("VOL");
        tdate.setCellValueFactory(new PropertyValueFactory<LabourFoetalHeartBeans, String>("date"));

        urinetableview.getColumns().addAll(tfoetalrate, tliquortime, tdate);
    }

}
