package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ese on 4/19/2017.
 */
public class OpclViewController extends VBox {

    @FXML
    private TableView<?> tableview;

    @FXML
    private HBox printhbox;

    List<String> data_list =new ArrayList<>();

    ObservableList olist;

    @FXML
    void initialize(){

        ObservableList data_list=FXCollections.observableArrayList();

        StringBuilder sb = new StringBuilder();
        if(olist!=null && !olist.isEmpty()){
            for(int i=0; i<olist.size(); i++){
                OpclListBean ob=(OpclListBean) olist.get(i);

                sb.append(ob.getData());
            }
        }

        String data[]=sb.toString().split("_");

        for(int i =1; i<data.length; i++){
            OpclDataBean oc=new OpclDataBean();
            oc.question.set(data[i]);
            oc.answer.set("Yes");
            data_list.add(oc);

        }

        if(!data_list.isEmpty()){
            displayTable(data_list);
        }

    }
    protected void displayTable(ObservableList olist){

        tableview.getColumns().clear();
        tableview.getItems().clear();

        tableview.setItems(olist);

        TableColumn tquestion = new TableColumn("Checklist Questions");
        tquestion.setCellValueFactory(new PropertyValueFactory("question"));
        tquestion.setCellFactory(new Callback<TableColumn<ReportDataListBeans, String>, TableCell<ReportDataListBeans, String>>() {

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


        TableColumn tanswer = new TableColumn("Answers");
        tanswer.setCellValueFactory(new PropertyValueFactory("answer"));

        tableview.getColumns().addAll(tquestion, tanswer);

    }


    public OpclViewController(ObservableList olist) throws IOException {

        this.olist=olist;

        FXMLLoader fxmlloader=new FXMLLoader(OperativeCheckListReport.class.getResource("opclview.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
}
