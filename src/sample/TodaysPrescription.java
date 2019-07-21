package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 1/18/2016.
 */
public class TodaysPrescription extends VBox {

    @FXML
    private TableView<?> nontableview;

    @FXML
    private TableView<?> antitableview;

    @FXML
    private Button viewnonbutton;

    @FXML
    private Button viewallpatientbutton;

    @FXML
    private Button nonsalesbutton;

    @FXML
    private Button viewantebutton;

    @FXML
    private TextArea antitextarea;

    @FXML
    private Button antsalesbutton;

    @FXML
    private TextArea nontextarea;

    @FXML
    void initialize()
    {
        //this clas belongs to docotrs patient;
        ObservableList nonantinatal= FXCollections.observableArrayList();
        ObservableList antinatalist=FXCollections.observableArrayList();
        viewallpatientbutton.setOnAction(e->{

            nontableview.getColumns().clear();
            nontableview.getItems().clear();
            antitableview.getColumns().clear();
            antitableview.getColumns().clear();
            try {
                String sql="SELECT * FROM patient_registry where patype='Patient'";
                DataBase dbase=new DataBase();
                Connection con=dbase.getConnection();
                PreparedStatement statement=con.prepareStatement(sql);
                ResultSet rs=statement.executeQuery();
                while(rs.next())
                {
                    TodaysPrescriptionBean db=new TodaysPrescriptionBean();
                    db.setNames(rs.getString("firstname") + " " + rs.getString("lastname"));
                    db.setId(rs.getString("id_sn"));
                    db.setAddress(rs.getString("address"));
                    db.setSex(rs.getString("sex"));
                    nonantinatal.addAll(db);

                }
                nontableview.setItems(nonantinatal);

                TableColumn tnames=new TableColumn("Name");
                tnames.setCellValueFactory(new PropertyValueFactory<TodaysPrescription, String>("names"));

                TableColumn tid=new TableColumn("Id");
                tid.setCellValueFactory(new PropertyValueFactory<TodaysPrescription, String>("id"));

                TableColumn taddress=new TableColumn("Address");
                taddress.setCellValueFactory(new PropertyValueFactory<TodaysPrescription, String>("address"));

                TableColumn tsex=new TableColumn("Sex");
                tsex.setCellValueFactory(new PropertyValueFactory<TodaysPrescription, String>("sex"));

                nontableview.getColumns().addAll(tnames, tid, taddress, tsex);
                ObservableList preslist=FXCollections.observableArrayList();

                nontableview.getSelectionModel().selectedItemProperty().addListener((obervable, oldvalue, newvalue)->
                {
                    preslist.clear();
                    TodaysPrescriptionBean tb=(TodaysPrescriptionBean) newvalue;
                    if(tb!=null) {
                        String id = tb.getId();
                        try {
                            String pre = "SELECT * from doc_pres WHERE patient_id=?";

                            PreparedStatement state = con.prepareStatement(pre);
                            state.setString(1, id);
                            ResultSet rss = state.executeQuery();
                            while (rss.next()) {
                                preslist.addAll(rss.getString("date") + "  :\t" + rss.getString("prescrip"));
                            }
                            StringBuilder builder = new StringBuilder();
                            for (Object value : preslist) {
                                builder.append((String) value + "\n");

                            }
                            nontextarea.setText(builder.toString());


                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                });






            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            try {
                String sql="SELECT * FROM patient_registry where patype='Antenatal'";
                DataBase dbase=new DataBase();
                Connection con=dbase.getConnection();
                PreparedStatement statement=con.prepareStatement(sql);
                ResultSet rs=statement.executeQuery();
                while(rs.next())
                {
                    TodaysPrescriptionBean db=new TodaysPrescriptionBean();
                    db.setNames(rs.getString("firstname") + " " + rs.getString("lastname"));
                    db.setId(rs.getString("id_sn"));
                    db.setAddress(rs.getString("address"));
                    db.setSex(rs.getString("sex"));
                    antinatalist.addAll(db);

                }
                antitableview.setItems(antinatalist);

                TableColumn tnames=new TableColumn("Name");
                tnames.setCellValueFactory(new PropertyValueFactory<TodaysPrescription, String>("names"));

                TableColumn tid=new TableColumn("Id");
                tid.setCellValueFactory(new PropertyValueFactory<TodaysPrescription, String>("id"));

                TableColumn taddress=new TableColumn("Address");
                taddress.setCellValueFactory(new PropertyValueFactory<TodaysPrescription, String>("address"));

                TableColumn tsex=new TableColumn("Sex");
                tsex.setCellValueFactory(new PropertyValueFactory<TodaysPrescription, String>("sex"));

                antitableview.getColumns().addAll(tnames, tid, taddress, tsex);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        });

        nonsalesbutton.setOnAction(e->{
            TodaysPrescriptionBean tb=(TodaysPrescriptionBean)nontableview.getFocusModel().getFocusedItem();
            String patienttype="Non Ante-natal Patient";
            if(tb!=null){
                String id=tb.getId();
                try{
                    DrugSales ds=new DrugSales(id, patienttype);
                    Scene scene=new Scene(ds);
                    Stage stage=new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();


                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }

        });

        antsalesbutton.setOnAction(e->{

            TodaysPrescriptionBean tb=(TodaysPrescriptionBean)antitableview.getFocusModel().getFocusedItem();
            if(tb!=null){
                String id=tb.getId();
                String patienttype="Ante-natal Patient";
                try{
                    DrugSales ds=new DrugSales(id, patienttype);
                    Scene scene=new Scene(ds);
                    Stage stage=new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();


                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }

        });

        viewantebutton.setOnAction(e->{

            TodaysPrescriptionBean tb=(TodaysPrescriptionBean)antitableview.getFocusModel().getFocusedItem();
            if(tb!=null){
                String id=tb.getId();
                try {
                    ViewPharmacyBillsController vbc = new ViewPharmacyBillsController(id);
                    Scene scene=new Scene(vbc);
                    Stage stage=new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            }

        });

        viewnonbutton.setOnAction(e->{

            TodaysPrescriptionBean tb=(TodaysPrescriptionBean)nontableview.getFocusModel().getFocusedItem();
            if(tb!=null){
                String id=tb.getId();
                try {
                    ViewPharmacyBillsController vbc = new ViewPharmacyBillsController(id);
                    Scene scene=new Scene(vbc);
                    Stage stage=new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            }

        });

    }
    public TodaysPrescription() throws IOException {
        FXMLLoader fxmlloader=new FXMLLoader(TodaysPrescription.class.getResource("todayspres.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
}
