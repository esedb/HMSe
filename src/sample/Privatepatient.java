package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Ese on 12/13/2015.
 */
public class Privatepatient extends VBox {
    /*Class private patient handlers private patient
    behaviour in Human resource slection sub-categories
     */

    int sum;
    @FXML
    private TextField totalfld;
    @FXML
    private TextField addofkinfld;

    @FXML
    private TextField laboratoryfld;

    @FXML
    private TextField treatmentfld;

    @FXML
    private TextField consulcostfld;

    @FXML
    private Button addcostbut;

    @FXML
    private TextField treatcostfld;

    @FXML
    private TextField phonenofld;

    @FXML
    private Button clearbutton;

    @FXML
    private TextField placeofworkfld;

    @FXML
    private TextField pharmacyfld;

    @FXML
    private TextField nationalityfld;

    @FXML
    private TextField labcostfld;

    @FXML
    private TextField religionfld;

    @FXML
    private TextField lastnamefld;

    @FXML
    private TextField occupationfld;

    @FXML
    private TextField dobfld;

    @FXML
    private TextField radcostfld;

    @FXML
    private TextField firstnamefld;

    @FXML
    private Button addaccbutton;

    @FXML
    private TextField dateofregfld;

    @FXML
    private TextField nextofkinfld;

    @FXML
    private TextField consultationfld;

    @FXML
    private TextField radiologyfld;

    @FXML
    private TextField mortcostfld;

    @FXML
    private ChoiceBox<?> sexfld;

    @FXML
    private TextField pharcostfld;

    @FXML
    private TextField mortuaryfld;

    @FXML
    void addCostAction(ActionEvent event) {
        try {
            int consulcost=Integer.parseInt(consulcostfld.getText());
            int treatcost=Integer.parseInt(treatcostfld.getText());
            int labcost=Integer.parseInt(labcostfld.getText());
            int radcost=Integer.parseInt(radcostfld.getText());
            int mortuarycost=Integer.parseInt(mortcostfld.getText());
            int pharmacycost=Integer.parseInt(pharcostfld.getText());
            sum=consulcost + treatcost+labcost + radcost + mortuarycost+ pharmacycost;



            }
        catch(NumberFormatException e)
        {
            System.out.println("you should only input a valid numeral");
        }

    }
    @FXML
    void initialize()
    {
    }

    @FXML
    void onClearAction(ActionEvent event) {
        addofkinfld.setText("");
        phonenofld.setText("");
        placeofworkfld.setText("");
        nationalityfld.setText("");
        religionfld.setText("");
        occupationfld.setText("");
        dobfld.setText("");
        nextofkinfld.setText("");

        firstnamefld.setText("");
        lastnamefld.setText("");

        radiologyfld.setText("");
        laboratoryfld.setText("");
        pharmacyfld.setText("");
        treatmentfld.setText("");
        consultationfld.setText("");
        mortuaryfld.setText("");
        dateofregfld.setText("");

    }

    @FXML
    void registerAction(ActionEvent event) {
        String addkin=addofkinfld.getText();
        String phone_no=phonenofld.getText();
        String placeof_work=placeofworkfld.getText();
        String nationality=nationalityfld.getText();
        String religion=religionfld.getText();
        String occupation=occupationfld.getText();
        String dateof_birth=dobfld.getText();
        String nextof_kin=nextofkinfld.getText();

        String firstname=firstnamefld.getText();
        String lastname=lastnamefld.getText();
        String sextype=sexfld.getAccessibleText();
        String radiology_ty=radiologyfld.getText();
        String laboratory_ty=laboratoryfld.getText();
        String pharmacy_ty=pharmacyfld.getText();
        String treatment_ty=treatmentfld.getText();
        String consultation_ty=consultationfld.getText();
        String mortuary_ty=mortuaryfld.getText();
        String dateofreg=dateofregfld.getText();

        PrivatePatientBean pb=new PrivatePatientBean();
        pb.setFirstname(firstname);
        pb.setLastname(lastname);
        pb.setOccupation(occupation);
        pb.setDateofbirth(dateof_birth);
        pb.setDateofreg(dateofreg);
        pb.setNationality(nationality);
        pb.setNextofkin(nextof_kin);
        pb.setAddressofkin(addkin);
        pb.setPlaceofwork(placeof_work);
        pb.setPhoneno(phone_no);
        pb.setReligion(religion);
        pb.setSex(sextype);
        pb.setTreatment(treatment_ty);
        pb.setRadiology(radiology_ty);
        pb.setPharmacy(pharmacy_ty);
        pb.setConsultation(consultation_ty);
        pb.setLaboratory(laboratory_ty);
        pb.setMortuary(mortuary_ty);
        pb.setSum(sum);
        pb.execute();

        if(pb.isSuccessful())
        {
            System.out.println("Success for PrivatePatient_: " + pb.isSuccessful());
        }


   }

    public Privatepatient()throws IOException
    {
        FXMLLoader fxmlloader=new FXMLLoader(AccountFormController.class.getResource("privatepatient.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
}
