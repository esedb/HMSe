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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ese on 2/10/2016.
 */
public class AntenatalController extends VBox {


    @FXML
    private TextField dedemafld;

    @FXML
    private TextField antecodefld;

    @FXML
    private Button savebutton;

    @FXML
    private TextField tmpfld;

    @FXML
    private TextField sfhfld;

    @FXML
    private TextField attitudefld;

    @FXML
    private TextField bpmmfld;

    @FXML
    private TextField othernamefld;

    @FXML
    private TextField signbyfld;

    @FXML
    private TextArea medhistxa;

    @FXML
    private TextField bpfld;

    @FXML
    private TextField gravidafld;

    @FXML
    private TextField parafld;

    @FXML
    private TextField ogfld;

    @FXML
    private TextField gabookingfld;

    @FXML
    private TextField lieffld;

    @FXML
    private TextField surnamefld;

    @FXML
    private DatePicker lmpfld;

    @FXML
    private TextField eddfld;

    @FXML
    private Button prevbutton;

    @FXML
    private TextField patientidfld;

    @FXML
    private Button rprevbutton;

    @FXML
    private TextField agefld;

    @FXML
    private TextField lcbfld;

    @FXML
    private TextField weightfld;

    @FXML
    private DatePicker dateofbooking;

    @FXML
    private TextField menstraulfld;

    @FXML
    private Button labourbutton;

    @FXML
    private Button lrecordbutton;

    @FXML
    private TextField presentationfld;

    @FXML
    private Button addnewbutton;

    @FXML
    private TextField foetalfld;

    @FXML
    private TextArea specialcirfld;

    @FXML
    private TextArea ultrasoundfld;

    @FXML
    private TextArea clinicalinfotxa;

    @FXML
    private TextArea labexamtxa;

    @FXML
    private TextField prevsurfld;

    @FXML
    private Button nextbutton;

    @FXML
    private TextField signaturefld;

    @FXML
    private ChoiceBox<?> deliverychoice;

    @FXML
    private TextField menarchefld;

    @FXML
    private TextField gaweeksfld;

    @FXML
    private Button savebutton2;

    @FXML
    private Button loadallbutton;

    @FXML
    private DatePicker examdate;

    @FXML
    private TextField labourfld;

    @FXML
    private Button rnextbutton;

    int position=0;
    int length=0;
    List list;

    int position2=0;
    int length2=0;
    List list2;
    String id;



    @FXML
    void initialize()
    {
        patientidfld.setText(id);
        patientidfld.setEditable(false);

        StringConverter converter=DateConverter.convert();
        dateofbooking.setConverter(converter);
        dateofbooking.setValue(LocalDate.now());



        lmpfld.setConverter(converter);
        lmpfld.valueProperty().addListener((observable, oldvalue, newvalue)->{
            if(newvalue!=null && newvalue!=oldvalue)
            {

                eddfld.setText(newvalue.plusDays(280).toString());

                gabookingfld.setText(newvalue.minusDays(LocalDate.now().getDayOfMonth()).
                        format(DateTimeFormatter.ofPattern("E, dd MMM yyyy")).toString());
            }

        });

        signbyfld.setText(Controller.sign_by);
        signaturefld.setText(Controller.sign_by);
        labourfld.setText("True");



        List<RAntenatalBeans> lexamdiag=new ArrayList<>();
        try{
            String sql="SELECT * FROM antenatal_records where patientid=? ";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(patientidfld.getText()));
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                RAntenatalBeans ran=new RAntenatalBeans(rs.getString("dateofbooking"), rs.getLong("patientid"), rs.getString("surname"), rs.getString("othername"), rs.getString("age"),
                        rs.getString("para"), rs.getString("gravida"), rs.getString("lcb"), rs.getString("spcecial_cirum"), rs.getString("antenatal_code"), rs.getString("type_of_delivery"),
                        rs.getString("lmp"), rs.getString("edd"),rs.getString("ga_booking"), rs.getString("og_surgical"), rs.getString("menarche"),
                        rs.getString("mens_duration"),  rs.getString("prev_surgery"),
                        rs.getString("sign_by"), rs.getString("prev_midhistroy"), rs.getString("labour"));
                lexamdiag.add(ran);

            }
            rs.close();
            con=null;

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }



        this.list=lexamdiag;
        length=list.size();
        position=length;
        rnextbutton.setOnAction(e->{
            if(!list.isEmpty())
            {
                nextAction();
            }

        });

        rprevbutton.setOnAction(e->{
            if(!list.isEmpty()) {
                previousAction();
            }

        });

        List<UAntenatalBeans> ulist=new ArrayList<>();
        try{
            String sql="SELECT * FROM ante_exam_update where patientid=?";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(patientidfld.getText()));
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                UAntenatalBeans uab=new UAntenatalBeans(rs.getLong("patientid"), rs.getString("examdate"), rs.getString("weight"), rs.getString("dedema"),
                        rs.getString("presentation"), rs.getString("gaweeks"), rs.getString("bpm"), rs.getString("sfh"), rs.getString("attitude"), rs.getString("ultrsound"),
                        rs.getString("tmp"), rs.getString("bp"), rs.getString("lie"), rs.getString("foeatal_heart"), rs.getString("clinical_info"), rs.getString("sign_by"));
                ulist.add(uab);



            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        this.list2=ulist;
        length2=list2.size();
        position2=length2;



        prevbutton.setOnAction(e->{
          if(!list2.isEmpty()) {
                uPrevAction();
            }
                });

        nextbutton.setOnAction(e->{
            if(!list2.isEmpty()) {
                uNextAction();
            }

        });

        labourbutton.setOnAction(e->{

            labourAction();

        });

        loadallbutton.setOnAction(e->{
            loadAllAction();
        });
        savebutton.setOnAction(e->{

            saveAction();

        });

        savebutton2.setOnAction(e->{

            saveUpdate();

        });

        labourbutton.setOnAction(e->{

            try{
                LabourController lc=new LabourController(id);
                Stage stage=new Stage();
                Scene scene=new Scene(lc);

                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        });
    }
    public AntenatalController(String id) throws IOException
    {
        this.id=id;
        FXMLLoader fxmlloader = new FXMLLoader(PatientRegistry.class.getResource("antenatal.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
    void saveAction(){

        String dateob=dateofbooking.getValue().toString();
        String patientid=patientidfld.getText();
        String surname=surnamefld.getText();
        String othername=othernamefld.getText();
        String age=agefld.getText();
        String para=parafld.getText();
        String gravida=gravidafld.getText();
        String lcb=lcbfld.getText();
        String specialcircum=specialcirfld.getText();
        String antenatalcode=antecodefld.getText();
        String typeofdelivery=(String)deliverychoice.getSelectionModel().getSelectedItem();
        String lmpdate=lmpfld.getValue().toString();
        String edddate=eddfld.getText();
        String gabooking=gabookingfld.getText();
        String ogsurgical=ogfld.getText();
        String menarche=menarchefld.getText();
        String mensduration=menstraulfld.getText();
        String prevsurgery=prevsurfld.getText();
        String sign_by=signbyfld.getText();
        String medicalhistory=medhistxa.getText();
        String labour=labourfld.getText();

        try{
            String sql="INSERT INTO will_parry.antenatal_records (dateofbooking, patientid, surname, othername, age, " +
                    "para, gravida, special_circum, antenatal_code, type_of_delivery, lmp, edd, ga_booking, og_surgical, " +
                    "menarche, mens_duration, prev_midhistroy, prev_surgery, labour, sign_by, lcb) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            DataBase dbase =new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement =con.prepareStatement(sql);
            statement.setString(1, dateob);
            statement.setString(2, patientid);
            statement.setString(3, surname);
            statement.setString(4, othername);
            statement.setString(5, age);
            statement.setString(6, para);
            statement.setString(7, gravida);
            statement.setString(8, specialcircum);
            statement.setString(9, antenatalcode);
            statement.setString(10,typeofdelivery);
            statement.setString(11, lmpdate);
            statement.setString(12, edddate);
            statement.setString(13, gabooking);
            statement.setString(14, ogsurgical);
            statement.setString(15, menarche);
            statement.setString(16, mensduration);
            statement.setString(17, medicalhistory);
            statement.setString(18, prevsurgery);
            statement.setString(19, labour);
            statement.setString(20, sign_by);
            statement.setString(21, lcb);
            int result=statement.executeUpdate();
            if(result==1)
            {
                System.out.println("Successful : _");

            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }


    }
    void saveUpdate()
    {
        Long patientid=Long.valueOf(patientidfld.getText());
        String examd=examdate.getValue().toString();
        String weight=weightfld.getText();
        String dedema=dedemafld.getText();
        String presentation=presentationfld.getText();
        String gaweeks=gaweeksfld.getText();
        String bpm=bpmmfld.getText();
        String sfh=sfhfld.getText();
        String attitude=attitudefld.getText();
        String ultrasound=ultrasoundfld.getText();
        String tmp=tmpfld.getText();
        String bp=bpfld.getText();
        String lie=lieffld.getText();
        String foetalheart=foetalfld.getText();
        String clinicalinfo=clinicalinfotxa.getText();
        String signature=signaturefld.getText();



        try{
            String sql="INSERT INTO will_parry.ante_exam_update (patientid, examdate, weight, dedema, " +
                    "presentation, ga_weeks, bpmm, sfh, attitude, ultrasound, temp, bp, lie, foetal_heart, clinical_info, sign_by) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setLong(1, patientid);
            statement.setString(2, examd);
            statement.setString(3, weight);
            statement.setString(4, dedema);
            statement.setString(5, presentation);
            statement.setString(6, gaweeks);
            statement.setString(7, bpm);
            statement.setString(8, sfh);
            statement.setString(9, attitude);
            statement.setString(10, ultrasound);
            statement.setString(11, tmp);
            statement.setString(12, bp);
            statement.setString(13, lie);
            statement.setString(14, foetalheart);
            statement.setString(15, clinicalinfo);
            statement.setString(16, signature);

            int result=statement.executeUpdate();
            if(result==1)
            {
                System.out.println("Sucessful : _ ");
                con.close();
                con=null;
            }


        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }
    void nextAction()
    {
        if(position>=length)
        {
            position=length-1;
        }
        else{
            RAntenatalBeans ran=(RAntenatalBeans) list.get(position);
            ObservableList olist= FXCollections.<String>observableArrayList();

            olist.add(ran.getTypeofdelivery());

            dateofbooking.setValue(LocalDate.parse(ran.getGabooking()));
            patientidfld.setText(String.valueOf(ran.getPatientid()));
            surnamefld.setText(ran.getSurname());
            othernamefld.setText(ran.getOthername());
            agefld.setText(ran.getAge());
            parafld.setText(ran.getPara());
            gravidafld.setText(ran.getGravida());
            lcbfld.setText(ran.getLcb());
            specialcirfld.setText(ran.getSpecialcircum());
            antecodefld.setText(ran.getAntenatalcode());
            deliverychoice.setItems(olist);
            lmpfld.setValue(LocalDate.parse(ran.getLmpdate()));
            eddfld.setText(ran.getEdddate());
            gabookingfld.setText(ran.getGabooking());
            ogfld.setText(ran.getOgsurgical());
            menarchefld.setText(ran.getMenarche());
            menstraulfld.setText(ran.getMensduration());
            prevsurfld.setText(ran.getPrevsurgery());
            signbyfld.setText(ran.getSign_by());
            medhistxa.setText(ran.getMedicalhistory());
            labourfld.setText(ran.getLabour());

            position=position+1;


        }
    }
    void previousAction()
    {
        if(position<=0)
        {
            position=0;
        }
        else{
            position=position-1;
            RAntenatalBeans ran=(RAntenatalBeans) list.get(position);
            ObservableList olist= FXCollections.<String>observableArrayList();

            olist.add(ran.getTypeofdelivery());

            dateofbooking.setValue(LocalDate.parse(ran.getGabooking()));
            patientidfld.setText(String.valueOf(ran.getPatientid()));
            surnamefld.setText(ran.getSurname());
            othernamefld.setText(ran.getOthername());
            agefld.setText(ran.getAge());
            parafld.setText(ran.getPara());
            gravidafld.setText(ran.getGravida());
            lcbfld.setText(ran.getLcb());
            specialcirfld.setText(ran.getSpecialcircum());
            antecodefld.setText(ran.getAntenatalcode());
            deliverychoice.setItems(olist);
            lmpfld.setValue(LocalDate.parse(ran.getLmpdate()));
            eddfld.setText(ran.getEdddate());
            gabookingfld.setText(ran.getGabooking());
            ogfld.setText(ran.getOgsurgical());
            menarchefld.setText(ran.getMenarche());
            menstraulfld.setText(ran.getMensduration());
            prevsurfld.setText(ran.getPrevsurgery());
            signbyfld.setText(ran.getSign_by());
            medhistxa.setText(ran.getMedicalhistory());
            labourfld.setText(ran.getLabour());

        }


    }
    void uNextAction() {
        if (position2 >= length2) {
            position2 = length2 - 1;
        } else {
            UAntenatalBeans ran = (UAntenatalBeans) list2.get(position2);
            patientidfld.setText(String.valueOf(ran.getPatientid()));
            examdate.setValue(LocalDate.parse(ran.getExamd()));
            weightfld.setText(ran.getWeight());
            dedemafld.setText(ran.getDedema());
            presentationfld.setText(ran.getPresentation());
            gaweeksfld.setText(ran.getGaweeks());
            bpmmfld.setText(ran.getBpm());
            sfhfld.setText(ran.getSfh());
            attitudefld.setText(ran.getAttitude());
            ultrasoundfld.setText(ran.getUltrasound());
            tmpfld.setText(ran.getTmp());
            bpfld.setText(ran.getBp());
            lieffld.setText(ran.getLie());
            foetalfld.setText(ran.getFoetalheart());
            clinicalinfotxa.setText(ran.getClinicalinfo());
            signaturefld.setText(ran.getSignature());

            position2 = position2 + 1;


        }
    }
    void uPrevAction()
    {
        if(position2<=0)
        {
            position2=0;
        }
        else {
            position2 = position2 - 1;
            UAntenatalBeans ran = (UAntenatalBeans) list2.get(position2);
            patientidfld.setText(String.valueOf(ran.getPatientid()));
            examdate.setValue(LocalDate.parse(ran.getExamd()));
            weightfld.setText(ran.getWeight());
            dedemafld.setText(ran.getDedema());
            presentationfld.setText(ran.getPresentation());
            gaweeksfld.setText(ran.getGaweeks());
            bpmmfld.setText(ran.getBpm());
            sfhfld.setText(ran.getSfh());
            attitudefld.setText(ran.getAttitude());
            ultrasoundfld.setText(ran.getUltrasound());
            tmpfld.setText(ran.getTmp());
            bpfld.setText(ran.getBp());
            lieffld.setText(ran.getLie());
            foetalfld.setText(ran.getFoetalheart());
            clinicalinfotxa.setText(ran.getClinicalinfo());
            signaturefld.setText(ran.getSignature());

        }


        }
    void labourAction()
    {


    }
    void loadAllAction()
    {

    }

}
