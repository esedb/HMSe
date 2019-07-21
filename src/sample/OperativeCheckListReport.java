package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Ese on 4/17/2017.
 */
public class OperativeCheckListReport extends VBox {

    @FXML
    private Button view_databutton;

    @FXML
    private HBox tableviewdisplay;

    @FXML
    private HBox hboxtableview;

    @FXML
    private TableView<?> table;

    ObservableList olist;

    @FXML
    void initialize(){



        displayTable();

        view_databutton.setOnAction(e->{
            if(!olist.isEmpty()) {

                Set<String> set=new HashSet<>();
                List<String> data_list=new ArrayList<>();

                OpclListBean opcl = (OpclListBean) table.getFocusModel().getFocusedItem();
                if (opcl != null) {
                    String patient_id = opcl.getPatient_id();

                    String data_sql="Select * from opcl_data where id_sn=?";
                    ObservableList perlist= FXCollections.observableArrayList();
                    try (DataBase dbasee = new DataBase(); Connection conn = dbasee.getConnection(); PreparedStatement stmt = conn.prepareStatement(data_sql)) {
                        stmt.setString(1, patient_id);
                        ResultSet rs = stmt.executeQuery();

                        while (rs.next()) {
                            OpclListBean cm=new OpclListBean();
                            cm.surname.set(rs.getString("opcl_data.surname"));
                            cm.othername.set(rs.getString("opcl_data.othername"));
                            cm.patient_id.set(rs.getString("opcl_data.id_sn"));
                            cm.operationtype.set(rs.getString("opcl_data.surgery"));
                            cm.data.set(rs.getString("data"));

                            olist.add(cm);
                            set.add(rs.getString("id_sn"));
                            data_list.add(rs.getString("data"));
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    if(!olist.isEmpty()){
                        try {
                            OpclViewController op = new OpclViewController(olist);
                            Scene scene=new Scene(op);
                            Stage stage =new Stage();
                            stage.setScene(scene);
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.show();
                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }
                    }
                    else{
                        ShowDialog.show("No data for this patient");
                    }

                }
            }

        });

    }

    protected void displayTable(){


        table.getColumns().clear();
        table.getItems().clear();

        table.setItems(olist);

        TableColumn tfirstname = new TableColumn("Surname");
        tfirstname.setCellValueFactory(new PropertyValueFactory("surname"));

        TableColumn tlastname = new TableColumn("Other Names");
        tlastname.setCellValueFactory(new PropertyValueFactory("othername"));


        TableColumn patientserial = new TableColumn("Patient Serial No.");
        patientserial.setCellValueFactory(new PropertyValueFactory("patient_id"));

        TableColumn tage = new TableColumn("Age");
        tage.setCellValueFactory(new PropertyValueFactory("age"));

        TableColumn tsex=new TableColumn("Sex");
        tsex.setCellValueFactory(new PropertyValueFactory("sex"));

        TableColumn tsurgery=new TableColumn("Surgery");
        tsurgery.setCellValueFactory(new PropertyValueFactory("operationtype"));


        table.getColumns().addAll(tfirstname, tlastname, patientserial, tage, tsurgery);

    }

    public OperativeCheckListReport(ObservableList olist) throws IOException {

        this.olist=olist;

        FXMLLoader fxmlloader=new FXMLLoader(OperativeCheckListReport.class.getResource("checklist_patient.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
}
