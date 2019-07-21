package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Ese on 2/28/2016.
 */
public class FrontTilesController  extends TilePane {
    @FXML
    private Button staff_entry_button;

    @FXML
    private Button out_patientbutton;

    @FXML
    private Button anti_natal_button;

    @FXML
    private Button patient_reg_button;

    @FXML
    private Button nonantenatalbutton;


    @FXML
    private Button in_patient_button;

    @FXML
    private Button paymentbutton;

    @FXML
    private Button viewll_all_patbutton;

    @FXML
    private Button hmobutton;

    BorderPane geneborderpane;

    @FXML
    void initialize()
    {
        Tooltip tp = new Tooltip("Patient Registration");
        patient_reg_button.setOnMouseEntered(event->{
            Node node = (Node) event.getSource();
            Tooltip.install(patient_reg_button,tp);
        });


        patient_reg_button.setOnMouseExited(event->{
            Tooltip.uninstall(patient_reg_button, tp);
        });

        tp.setStyle("-fx-background-color: #5CB002");

        Tooltip etp=new Tooltip("Staff Entry Form");
        staff_entry_button.setOnMouseEntered(event->{
            Node node = (Node) event.getSource();
            Tooltip.install(staff_entry_button,etp);
        });
        staff_entry_button.setOnMouseExited(event->{
            Tooltip.uninstall(staff_entry_button, etp);
        });

        etp.setStyle("-fx-background-color: #5CB002");

        Tooltip cstp=new Tooltip("Cost of Service");


        cstp.setStyle("-fx-background-color: #5CB002");

        Tooltip vatp=new Tooltip("View All Patient");
        viewll_all_patbutton.setOnMouseEntered(event->{
            Tooltip.install(viewll_all_patbutton,vatp);
        });
        viewll_all_patbutton.setOnMouseExited(event->{
            Tooltip.uninstall(viewll_all_patbutton, vatp);
        });
        vatp.setStyle("-fx-background-color: #5CB002");

        Tooltip vintp=new Tooltip("View all In Patient");
        in_patient_button.setOnMouseEntered(event->{
            Tooltip.install(in_patient_button,vintp);
        });
        in_patient_button.setOnMouseExited(event->{
            Tooltip.uninstall(in_patient_button,vintp);
        });
        vintp.setStyle("-fx-background-color: #5CB002");

        Tooltip optp=new Tooltip("View all Out Patient");
        out_patientbutton.setOnMouseEntered(e->{
            Tooltip.install(out_patientbutton,optp);
        });
        out_patientbutton.setOnMouseExited(e->{
            Tooltip.uninstall(out_patientbutton,optp);
        });
        optp.setStyle("-fx-background-color: #5CB002");


        Tooltip antp =new Tooltip("View Antenatal Patient");
        anti_natal_button.setOnMouseEntered(event->{
            Tooltip.install(anti_natal_button,antp);

        });
        anti_natal_button.setOnMouseExited(event->{
            Tooltip.install(anti_natal_button,antp);
        });
        antp.setStyle("-fx-background-color: #5CB002");


        Tooltip nontp=new Tooltip("View Ordinary Patient");
        nonantenatalbutton.setOnMouseEntered(e->{

            Tooltip.install(nonantenatalbutton,nontp);

        });

        nonantenatalbutton.setOnMouseExited(event->{
            Tooltip.uninstall(anti_natal_button,antp);

        });
        nontp.setStyle("-fx-background-color: #5CB002");

        Tooltip hmotp=new Tooltip("HMO");
        hmobutton.setOnMouseEntered(event->{
            Tooltip.install(hmobutton, hmotp);

        });

        hmobutton.setOnMouseExited(event->{
            Tooltip.uninstall(hmobutton, hmotp);

                });

        hmotp.setStyle("-fx-background-color: #5CB002");

        Tooltip ptp=new Tooltip("Accept Payment");
        paymentbutton.setOnMouseEntered(event->{
            Tooltip.install(paymentbutton, ptp);

        });
        paymentbutton.setOnMouseExited(event->{
            Tooltip.uninstall(paymentbutton, ptp);
        });
        ptp.setStyle("-fx-background-color: #5CB002");

        patient_reg_button.setOnAction(e->{
            try{

                PatientRegistry pr=new PatientRegistry();
                Stage stage=new Stage();
                Scene scene=new Scene(pr);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.show();


            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        });

        staff_entry_button.setOnAction(event->{
            try{
                AccountFormController form=new AccountFormController();
                geneborderpane.setCenter(form);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        });



        viewll_all_patbutton.setOnAction(event->{
            try{
                ViewAllPatientController cp=new ViewAllPatientController();
                geneborderpane.setCenter(cp);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        });

        anti_natal_button.setOnAction(e->{
            try{
                ViewAntenatalPatientController va=new ViewAntenatalPatientController();
                geneborderpane.setCenter(va);

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        });

        hmobutton.setOnAction(e->{
            try {
                NHMOListController hlist = new NHMOListController();
                geneborderpane.setCenter(hlist);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        });

        paymentbutton.setOnAction(e->{
            try{
                PaymentController pc=new PaymentController(null, null, 0);
                geneborderpane.setCenter(pc);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        });

        nonantenatalbutton.setOnAction(event->{
            try {
                ViewNonAntenatalController vc = new ViewNonAntenatalController();
                geneborderpane.setCenter(vc);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        });

        in_patient_button.setOnAction(event->{
            try{
                InPatients inp=new InPatients();
                geneborderpane.setCenter(inp);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        });


    }


    FrontTilesController(BorderPane geneborderpane) throws IOException {
        this.geneborderpane=geneborderpane;
        FXMLLoader fxmlloader=new FXMLLoader(FrontTilesController.class.getResource("fronttiles.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
}
