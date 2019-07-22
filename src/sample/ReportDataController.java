package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ese on 4/12/2017.
 */
public class ReportDataController extends VBox {


    private final Stage stage;
    @FXML
    private Label namelabel;

    @FXML
    private DatePicker todate;

    @FXML
    private Button printbutton;

    @FXML
    private Button viewbydatebutton;

    @FXML
    private ImageView imageview;

    @FXML
    private TableView<?> tableview;

    @FXML
    private HBox printhbox;

    @FXML
    private DatePicker fromdate;


    ObservableList olist;
    String imgurl=null;
    String surname=null;
    String othername=null;

    @FXML
    void initialize(){

        fromdate.setConverter(DateConverter.convert());
        fromdate.setValue(LocalDate.now());

        todate.setConverter(DateConverter.convert());
        todate.setValue(LocalDate.now());

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection();
            PreparedStatement stmt=con.prepareStatement("select * from patient_registry")){

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        String id=null;

        Set<String> set=new HashSet<>();

        displayTable();
        if(!olist.isEmpty()){

            for(int i=0; i<olist.size(); i++){
               ReportDataListBeans rb=(ReportDataListBeans)olist.get(i);
                set.add(rb.getId_sn());

            }


        }

        if(!set.isEmpty()){
            for(String b: set){
                id=b;
            }

        }
        if(id!=null){
            String sql="select distinct * from patient_registry where id_sn=?";
            try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
                stmt.setString(1, id);
                ResultSet rs=stmt.executeQuery();
                while(rs.next()){
                    imgurl=rs.getString("image_url");
                    surname=rs.getString("firstname");
                    othername=rs.getString("othername");


                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }

        if(othername!=null && surname!=null && imgurl!=null ){
            try {
                if(imgurl!=null) {

                    namelabel.setText(surname + " " + othername);
                    Image img = new Image(imgurl);
                    imageview.setImage(img);
                }
                else{
                    return;
                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        }

        printbutton.setOnAction(e->{


            PrinterJob job=PrinterJob.createPrinterJob();
            if (job != null && job.showPrintDialog(null)) {
                Printer printer = job.getPrinter();
                PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
                double scaleX = pageLayout.getPrintableWidth() / printhbox.getBoundsInParent().getWidth();
                double scaleY = pageLayout.getPrintableHeight() / printhbox.getBoundsInParent().getHeight();
                double minimumScale = Math.min(scaleX, scaleY);
                Scale scale = new Scale(minimumScale, minimumScale);
                try {
                    printhbox.getTransforms().add(scale);
                    boolean success = job.printPage(printhbox);
                    if (success)
                        job.endJob();
                } finally {
                    printhbox.getTransforms().remove(scale);

                }
            }







        });

    }


    void displayTable(){
        if(!olist.isEmpty()) {

            tableview.getColumns().clear();
            tableview.getItems().clear();

            tableview.setItems(olist);

            TableColumn tid = new TableColumn("Patient Id");
            tid.setCellValueFactory(new PropertyValueFactory("id_sn"));


            TableColumn totype = new TableColumn("Operation Type");
            totype.setCellValueFactory(new PropertyValueFactory("dataname"));


            TableColumn todata = new TableColumn("Operation Data");
            todata.setCellValueFactory(new PropertyValueFactory("data"));


            TableColumn tdatavalue = new TableColumn("Value");
            tdatavalue.setCellValueFactory(new PropertyValueFactory("datavalue"));
            tdatavalue.setCellFactory(new Callback<TableColumn<ReportDataListBeans, String>, TableCell<ReportDataListBeans, String>>() {

                @Override
                public TableCell<ReportDataListBeans, String> call(
                        TableColumn<ReportDataListBeans, String> param) {
                    TableCell<ReportDataListBeans, String> cell = new TableCell<>();
                    Text text = new Text();
                    cell.setGraphic(text);
                    cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
                    text.wrappingWidthProperty().bind(cell.widthProperty());
                    text.textProperty().bind(cell.itemProperty());
                    return cell ;
                }

            });


            TableColumn tdate=new TableColumn("Date");
            tdate.setCellValueFactory(new PropertyValueFactory("date"));


            tableview.getColumns().addAll(tid, totype, todata, tdatavalue, tdate);
        }

    }

    public ReportDataController(ObservableList olist, Stage stage)throws IOException {
        this.stage=stage;
        this.olist=olist;

        FXMLLoader fxmlloader=new FXMLLoader(ReportDataController.class.getResource("datadisplay.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }

}
