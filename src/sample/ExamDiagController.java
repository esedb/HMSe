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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Ese on 1/25/2016.
 */
public class ExamDiagController extends VBox {

    private final String patient_id;

    @FXML
    private TextField othernamefld;

    @FXML
    private TextField signbyfld;

    @FXML
    private DatePicker pdate;

    @FXML
    private Button labbutton;

    @FXML
    private ChoiceBox<?> surgerychb;

    @FXML
    private TextField surnamefld;

    @FXML
    private TextField bp_licfld;

    @FXML
    private TextField heightfld;

    @FXML
    private Button subutton;

    @FXML
    private Button prevbutton;

    @FXML
    private TextField patientidfld;

    @FXML
    private Button presdbutton;

    @FXML
    private TextField timefld;

    @FXML
    private TextField weightfld;

    @FXML
    private TextField spo2fld;

    @FXML
    private Button viewappbutton;

    @FXML
    private TextField respfld;

    @FXML
    private TextField pulsefld;

    @FXML
    private TextField tempfld;

    @FXML
    private TextField sign_byfld;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField bgfld;

    @FXML
    private Button labresbutton;

    @FXML
    private TextField familycompfld;

    @FXML
    private Button nextbutton;

    @FXML
    private Button vpsbutton;

    @FXML
    private Button dischargebutton;

    @FXML
    private Button appointbutton;

    @FXML
    private TextArea complainsarea;

    @FXML
    private Button viewbutton;


    String id2;
    String surname;
    String othername;

    String pid=null;
    List<String> datalist=new ArrayList<>();
    int i=0;
    int n=0;
    List<ExamDiagBeans> list=new ArrayList<>();




    public void setOthername(String othername) {
        othernamefld.setText(othername);
    }


    public void setSurname(String surname) {
       surnamefld.setText(surname);
    }

    public void setId2(String id) {
        patientidfld.setText(id);

    }


    @FXML
    void initialize()
    {




        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection();
            PreparedStatement stmt=con.prepareStatement("select nextofkin, reg_by, dateofreg from patient_registry where id_sn=?")){
            stmt.setString(1, patient_id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                familycompfld.setText(rs.getString("nextofkin"));
                LocalDate lc=LocalDate.parse(rs.getString("dateofreg"));
                pdate.setValue(lc);

                signbyfld.setText(rs.getString("reg_by"));


            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        pdate.setConverter(DateConverter.convert());
        pdate.setDisable(true);
        signbyfld.setEditable(false);
        familycompfld.setEditable(false);



        viewappbutton.setOnAction(e->{
            try {
                PatientList pl = new PatientList();
                Scene scene=new Scene(pl);
                Stage stage=new Stage();

                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        });
        dischargebutton.setOnAction(e->{

            String id_patient=patientidfld.getText();
            String surname=surnamefld.getText();
            String othernames=othernamefld.getText();
            if(id_patient.length()>0 && id_patient!=null){
                double scost=0.00;
                double amount_deposited=0.00;
                double total=0.00;
                double receivables=0.00;
                String status=null;

                String costsql="select sum(cost) as total from service_rendered where id=?";
                try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(costsql)){
                    stmt.setString(1, id_patient);
                    ResultSet rs=stmt.executeQuery();
                    while(rs.next()){
                        scost=rs.getDouble("total");
                    }

                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

                String spayment="select sum(amount_deposited) as total from payment_table where id_sn=?";
                try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(spayment)){
                    stmt.setString(1, id_patient);
                    ResultSet rs=stmt.executeQuery();
                    while(rs.next()){
                        amount_deposited=rs.getDouble("total");
                    }

                }
                catch(Exception ex){
                    ex.printStackTrace();
                    return;
                }

                receivables=amount_deposited-scost;
                System.out.println("amount depostited: " + amount_deposited + " " + " cost of service: " + scost + " " + " receivables: " + receivables);
                if(receivables<0){
                    status="Owing";
                }
                else{
                    status="Paid Fully";
                }

                if(status!=null && status.length()>0){

                    String dissql="INSERT INTO will_parry.discharge_patient (id_sn, surname, othername, cost_of_service, amount_deoposited, receivables, status) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(dissql)){
                        stmt.setString(1, id_patient);
                        stmt.setString(2, surname);
                        stmt.setString(3, othernames);
                        stmt.setDouble(4, scost);
                        stmt.setDouble(5, amount_deposited);
                        stmt.setDouble(6, receivables);
                        stmt.setString(7, status);
                        int d=stmt.executeUpdate();
                        if(d==1){
                            try(DataBase dbasem=new DataBase(); Connection conm=dbasem.getConnection();
                                PreparedStatement stmtm=con.prepareStatement("delete from doc_and_patient where patient_id=?")){
                                stmtm.setString(1, patient_id);
                                int f=stmtm.executeUpdate();
                                if (f==1){
                                    ShowDialog.show("Discharge operation successful");

                                }

                            }
                            catch(Exception ex){
                                ex.printStackTrace();
                            }


                        }


                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }

                }

            }


        });
        labresbutton.setOnAction(e->{

            try {
                LaboratoryPatientList sc=new LaboratoryPatientList();
                Scene scene=new Scene(sc);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        });

        ObservableList surgerylist=FXCollections.observableArrayList();
        surgerylist.add("None");

        String surgerysql="Select * from templates where lab_or_surgery='Surgery'";
        Set<String> sset=new HashSet<>();
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection();
            PreparedStatement stmt=con.prepareStatement(surgerysql)){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                sset.add(rs.getString("service_name"));
            }

        }
        catch(Exception ex){

            ex.printStackTrace();
        }

        if(!sset.isEmpty()){
            for(String values: sset){
                surgerylist.add(values);
            }

            surgerychb.setItems(surgerylist);
            surgerychb.getSelectionModel().selectFirst();
        }

        vpsbutton.setOnAction(e->{

            try {

                SurgeryListController sl = new SurgeryListController();
                Scene scene = new Scene(sl);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }


        });

        subutton.setDisable(true);

        surgerychb.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue)->
        {
            if(newvalue.toString().equalsIgnoreCase("None")|| newvalue==null)
            {
                subutton.setDisable(true);
            }
            else{
                subutton.setDisable(false);
            }

        });






        /*

        subutton.setOnAction(e->{

            String id=patientidfld.getText();
            if(id.length()>0) {

                try {
                    PreCheckListController pcl = new PreCheckListController(id);
                    Scene scene=new Scene(pcl);
                    Stage stage=new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


        });


        patientidfld.setText(pid);

        */




        patientidfld.setEditable(true);
        surnamefld.setEditable(true);
        othernamefld.setEditable(true);


        datepicker.setValue(LocalDate.now());
        datepicker.setConverter(DateConverter.convert());


        timefld.setText(LocalTime.now().toString());

        sign_byfld.setText(Controller.sign_by);
        sign_byfld.setEditable(false);
        ObservableList agelist= FXCollections.observableArrayList();
        agelist.addAll("Adult", "Children");

        presdbutton.setOnAction(e-> {
            if(patientidfld.getText()!="" && patientidfld.getText().length()>0) {
                try {
                    PatientPresController pc = new PatientPresController();
                    pc.setId2(patientidfld.getText());
                    System.out.println("mm " + patientidfld.getText());
                    pc.setSurname(surnamefld.getText());
                    pc.setOthername(othernamefld.getText());
                    Stage stage = new Stage();
                    Scene scene = new Scene(pc);
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


        });

        try(DataBase dbase=new DataBase(); PreparedStatement statement=dbase.getConnection().prepareStatement("SELECT * FROM vital_signs where patient_id=?");){

            System.out.println("pid is: " + pid);
            statement.setString(1, pid);

            ResultSet rs=statement.executeQuery();

            while(rs.next())
            {
                ExamDiagBeans eb=new ExamDiagBeans(rs.getString("respiration"), rs.getString("bp_lic"), rs.getString("time"),
                        rs.getString("date"), rs.getString("pulse"), rs.getString("temp"), rs.getString("height"), rs.getString("weight"),
                        rs.getString("spo2"), rs.getString("b_group"), rs.getString("complain"));
                list.add(eb);
            }
            if(list.isEmpty())
            {
                return;

            }
            else{
                ExamDiagBeans eb=(ExamDiagBeans)list.get(list.size()-1);
                respfld.setText(eb.getRespiration());
                bp_licfld.setText(eb.getBp_lic());
                timefld.setText(eb.getTime());
                datepicker.setValue(LocalDate.parse(eb.getDate()));
                pulsefld.setText(eb.getPulse());
                tempfld.setText(eb.getTemperature());
                heightfld.setText(eb.getHeight());
                weightfld.setText(eb.getWeight());
                spo2fld.setText(eb.getSpo2());
                bgfld.setText(eb.getBgroup());
                complainsarea.setText(eb.getComplains());

            }

        }
        catch(Exception ex)
        {
            System.err.println(ex);
        }



        nextbutton.setOnAction(e->
        {
            if(list.isEmpty()) {
                return;
            }
            if(n<list.size() && n>=0)
            {
                System.out.println("next n " + n);
                ExamDiagBeans eb=(ExamDiagBeans)list.get(n);
                respfld.setText(eb.getRespiration());
                bp_licfld.setText(eb.getBp_lic());
                timefld.setText(eb.getTime());
                datepicker.setValue(LocalDate.parse(eb.getDate()));
                pulsefld.setText(eb.getPulse());
                tempfld.setText(eb.getTemperature());
                heightfld.setText(eb.getHeight());
                weightfld.setText(eb.getWeight());
                spo2fld.setText(eb.getSpo2());
                bgfld.setText(eb.getBgroup());
                complainsarea.setText(eb.getComplains());

                n=n+1;

            }
            if(n<0)
            {
                n=0;
            }
            if(n>=list.size())
            {
                n=list.size()-1;
            }


        });



        prevbutton.setOnAction(e->{
            if(list.isEmpty()) {
              return;
            }
            if(n>=0 && n<list.size())
            {
                System.out.println("prev n " + n);
                ExamDiagBeans eb=list.get(n);
                respfld.setText(eb.getRespiration());
                bp_licfld.setText(eb.getBp_lic());
                timefld.setText(eb.getTime());
                datepicker.setValue(LocalDate.parse(eb.getDate()));
                pulsefld.setText(eb.getPulse());
                tempfld.setText(eb.getTemperature());
                heightfld.setText(eb.getHeight());
                weightfld.setText(eb.getWeight());
                spo2fld.setText(eb.getSpo2());
                bgfld.setText(eb.getBgroup());
                complainsarea.setText(eb.getComplains());
                list.get(n);
                n=n-1;
            }

            if(n<0)
            {
                n=0;

            }

        });



        viewbutton.setOnAction(e->
        {
            try {
                ViewPersonalPatientController vp=new ViewPersonalPatientController();
                Stage stage=new Stage();
                Scene scene=new Scene(vp);

                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
            catch(IOException ex)
            {
                System.err.println(ex);
            }

        });
        appointbutton.setOnAction(e->{
            System.out.println("this part of the programme ran");
            try {
                PatientAppointmentController pl = new PatientAppointmentController(patientidfld.getText());
                Stage stage=new Stage();
                Scene scene=new Scene(pl);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

        });

        labbutton.setOnAction(e->{
            try{
                MLabTestController ml=new MLabTestController(patientidfld.getText());
                Stage stage=new Stage();
                Scene scene=new Scene(ml);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        });

        subutton.setOnAction(e-> {
            String texttype = surgerychb.getValue().toString();
            int d = 0;
            if (texttype.length() > 0) {
                try (DataBase dbase = new DataBase(); Connection con = dbase.getConnection();
                     PreparedStatement stmt = con.prepareStatement("select id_sn from surgery_patient where id_sn=?")) {
                    stmt.setString(1, patientidfld.getText());
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        ShowDialog.show("Patient already in Surgery");

                    } else {
                        String sql = "insert into surgery_patient(id_sn, surname, othername, surgery_type) values(?, ?, ?, ?)";
                        try (DataBase dbases = new DataBase(); Connection cons = dbases.getConnection();
                             PreparedStatement stmts = cons.prepareStatement(sql)) {
                            stmts.setString(1, patientidfld.getText());
                            stmts.setString(2, surnamefld.getText());
                            stmts.setString(3, othernamefld.getText());
                            stmts.setString(4, texttype);

                            d = stmts.executeUpdate();
                            System.out.println("i value is " + i);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        if (d == 1) {
                            try {


                                SurgeryListController sl = new SurgeryListController();
                                Scene scene = new Scene(sl);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.show();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

    }

    public ExamDiagController(String id) throws IOException {

        setPatientid(id);
        this.patient_id=id;


        FXMLLoader fxmlloader=new FXMLLoader(ExamDiagController.class.getResource("examinationdiag.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();


    }


        void setPatientid(String id )
    {
        this.pid=id;

    }
    void previous()
    {}
    void next()
    {}
    private void save()
    {

        String sign=sign_byfld.getText();
        String patientid=patientidfld.getText();

        String date=datepicker.getValue().toString();
        String signed=sign_byfld.getText();

        java.util.Date myDate=new java.util.Date();
        java.sql.Time theTime=new java.sql.Time(myDate.getTime());




        try{
            Date fmyDate = new SimpleDateFormat("yyyy-MM-dd").parse(datepicker.getValue().toString());
            java.sql.Date fmySqlDate = new java.sql.Date(fmyDate.getTime());
            String sql="INSERT INTO will_parry.exam_diag (patient_id, resp_system, abdomenn, nerveous, musk, sign_by, history, " +
                    "date, time, features, examination, diagnosis, signed_by)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setString(1, patientid);

            statement.setString(6, sign);

            statement.setDate(8, fmySqlDate);
            statement.setTime(9, theTime);

            statement.setString(13, signed);
            int result=statement.executeUpdate();

            if(result==1)
            {
                System.out.println("Succesful : ");
            }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
