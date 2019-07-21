package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 2/5/2016.
 */
public class MLabResultController extends VBox {
    @FXML
    private TableView<?> nontableview;

    @FXML
    private TableView<?> antitableview;

    @FXML
    private Button showbutton;

    Connection con;
    DataBase dbase;


    @FXML
    private void initialize()
    {
        try{
            dbase=new DataBase();
            con=dbase.getConnection();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        showbutton.setOnAction(e->{
            ObservableList resultlist= FXCollections.observableArrayList();
            ObservableList resultlist1=FXCollections.observableArrayList();

            reload(resultlist, resultlist1);


                });
    }
    public MLabResultController() throws IOException {
        FXMLLoader fxmlloader=new FXMLLoader(MLabTestController.class.getResource("mlabresult.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
    void reload(ObservableList resultlist, ObservableList resultlist1)
    {
        nontableview.getItems().clear();
        nontableview.getColumns().clear();
        nontableview.getColumns().removeAll();

        antitableview.getItems().clear();
        antitableview.getColumns().clear();
        antitableview.getColumns().removeAll();



        try{


        String sql="SELECT patient_id, palabtest.date, lab_no, palabtest.surname, palabtest.othername, palabtest.sex, specimen, palabtest.request_by, clinical_info, test_type, test_name, normal_value FROM palabtest INNER JOIN patient_registry ON \n" +
                "palabtest.patient_id=patient_registry.id_sn AND  patype='Non Antinatal';";
        PreparedStatement statement=con.prepareStatement(sql);
        ResultSet rs=statement.executeQuery();
        while(rs.next())
        {
            MLabTestBeans mb=new MLabTestBeans(rs.getString("patient_id"), rs.getString("date"), rs.getString("lab_no"),
                    rs.getString("surname"), rs.getString("othername"), rs.getString("sex"), rs.getString("specimen"),
                    rs.getString("request_by"), rs.getString("clinical_info"), rs.getString("test_type"), rs.getString("test_name"),
                    rs.getString("normal_value"));
            resultlist.addAll(mb);


        }
            nontableview.setItems(resultlist);

            TableColumn tpatientid=new TableColumn("Patient id");
            tpatientid.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("patientid"));

            TableColumn tdate=new TableColumn("Date");
            tdate.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("date"));

            TableColumn tlabno=new TableColumn("Lab no");
            tdate.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("labno"));

            TableColumn tsurname=new TableColumn("Surname");
            tsurname.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("surname"));

            TableColumn tothername=new TableColumn("Othernames");
            tothername.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("othername"));

            TableColumn tsex=new TableColumn("Sex");
            tsex.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("sex"));

            TableColumn tspecimen=new TableColumn("Specimen");
            tspecimen.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("specimen"));

            TableColumn trequestby=new TableColumn("Requet By");
            trequestby.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("requestby"));

            TableColumn tclinicalinfo=new TableColumn("Clinical Information");
            tclinicalinfo.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("clinicalinfo"));

            TableColumn ttesttype=new TableColumn("Test type");
            ttesttype.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("testtype"));

            TableColumn ttname=new TableColumn("Test name");
            ttname.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("testname"));

            TableColumn tnormalvalue=new TableColumn("normal value");
            tnormalvalue.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("normalvalue"));

            nontableview.getColumns().addAll(tpatientid, tlabno, tsurname, tothername, tsex, ttesttype, ttname, tnormalvalue, tclinicalinfo, trequestby, tspecimen, tdate);


        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        try{
            String sql="SELECT patient_id, palabtest.date, lab_no, palabtest.surname, palabtest.othername, palabtest.sex, specimen, palabtest.request_by, clinical_info, test_type, test_name, normal_value FROM palabtest INNER JOIN patient_registry ON \n" +
                    "palabtest.patient_id=patient_registry.id_sn AND  patype='Antinatal';";
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
            {
                MLabTestBeans mb=new MLabTestBeans(rs.getString("patient_id"), rs.getString("date"), rs.getString("lab_no"),
                        rs.getString("surname"), rs.getString("othername"), rs.getString("sex"), rs.getString("specimen"),
                        rs.getString("request_by"), rs.getString("clinical_info"), rs.getString("test_type"), rs.getString("test_name"),
                        rs.getString("normal_value"));
                resultlist1.addAll(mb);


            }
            antitableview.setItems(resultlist1);

            TableColumn tpatientid=new TableColumn("Patient id");
            tpatientid.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("patientid"));

            TableColumn tdate=new TableColumn("Date");
            tdate.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("date"));

            TableColumn tlabno=new TableColumn("Lab no");
            tdate.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("labno"));

            TableColumn tsurname=new TableColumn("Surname");
            tsurname.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("surname"));

            TableColumn tothername=new TableColumn("Othernames");
            tothername.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("othername"));

            TableColumn tsex=new TableColumn("Sex");
            tsex.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("sex"));

            TableColumn tspecimen=new TableColumn("Specimen");
            tspecimen.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("specimen"));

            TableColumn trequestby=new TableColumn("Requet By");
            trequestby.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("requestby"));

            TableColumn tclinicalinfo=new TableColumn("Clinical Information");
            tclinicalinfo.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("clinicalinfo"));

            TableColumn ttesttype=new TableColumn("Test type");
            ttesttype.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("testtype"));

            TableColumn ttname=new TableColumn("Test name");
            ttname.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("testname"));

            TableColumn tnormalvalue=new TableColumn("normal value");
            tnormalvalue.setCellValueFactory(new PropertyValueFactory<MLabTestBeans, String>("normalvalue"));

            antitableview.getColumns().addAll(tpatientid, tlabno, tsurname, tothername, tsex, ttesttype, ttname, tnormalvalue, tclinicalinfo, trequestby, tspecimen, tdate);



        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }


    }
}
