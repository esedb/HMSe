package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;

import java.io.IOException;

/**
 * Created by Ese on 2/19/2016.
 */
public class Sterling_hmoController extends VBox {
    @FXML
    private TextField nameproviderfld;

    @FXML
    private TextField nameofpaymentfld;

    @FXML
    private Text drugtxt;

    @FXML
    private TextField dateofnotifld;

    @FXML
    private TextField registrationfld;

    @FXML
    private TextField dateofadminfld;

    @FXML
    private TextField feedingfld;

    @FXML
    private Text investigationtxt;

    @FXML
    private Label moneyfld;

    @FXML
    private Label costlabel;

    @FXML
    private TextField accodays;

    @FXML
    private Text durationtext;

    @FXML
    private TextField accomodationfld;

    @FXML
    private Text dosagetxt;

    @FXML
    private TextField feedingdaysfld;

    @FXML
    private Button printbutton;

    @FXML
    private TextField idnofld;

    @FXML
    private TextField dischargefld;

    @FXML
    private TextField professionfeesfld;

    @FXML
    private VBox printPage;

    @FXML
    private TextField agefld;

    String id;

    @FXML
    void initialize(){


        printbutton.setOnAction(e->{
        Printer defaultprinter = Printer.getDefaultPrinter();
        System.out.println(printPage.getBoundsInParent().getWidth());

        PageLayout pageLayout = defaultprinter.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.EQUAL);
        double scaleX = 595.28;
        double scaleY = 841.89;
        System.out.println(scaleX);
        System.out.println(scaleY);


        printPage.getTransforms().add(new Scale(scaleX, scaleY));

        if (defaultprinter != null) {
            String name = defaultprinter.getName();
            System.out.println("Default printer name: " + name); }
        else {
            System.out.println("No printers installed.");
        }


        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob!= null) {

            boolean printed = printerJob.printPage(printPage);
            if (!printed) {

                printerJob.endJob();
            } else {
                System.out.println("Printing failed.");
            } }
             else {
            System.out.println("Could not create a printer job."); }
        });



    }

    public Sterling_hmoController(String id) throws IOException {
        this.id=id;
        FXMLLoader fxmlloader=new FXMLLoader(Sterling_hmoController.class.getResource("sterling_hmo.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();


    }
}
