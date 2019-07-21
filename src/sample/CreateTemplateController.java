package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ese on 6/24/2016.
 */
public class CreateTemplateController extends VBox {
    //This class details the dynamic creation of templates for other
    //test type not created by default.

    @FXML
    private Button addbutton;

    @FXML
    private TextField vtextfield;

    @FXML
    private Button submitbutton;

    @FXML
    private Button remove_allbutton;

    @FXML
    private ChoiceBox<?> choicebox;

    @FXML
    private Label countlabel;

    @FXML
    private TextField service_namelabel;

    @FXML
    private Button removebutton;



    List<String> ttypelist;
    List<String> sitem;
    int count = 0;

    public CreateTemplateController() throws IOException {

        FXMLLoader fxmlloader = new FXMLLoader(DrugCategory.class.getResource("createtemplate.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
    public void initialize()
    {
        countlabel.setText(String.valueOf(count));

        ttypelist=new ArrayList<>();
        sitem=new ArrayList<>();
        ObservableList choiceitems= FXCollections.observableArrayList(
                "Text Field",
                "Text Area"
        );
        choicebox.setItems(choiceitems);
        choicebox.getSelectionModel().selectFirst();

        addbutton.setOnAction(e-> {


            if ((vtextfield.getText().length() > 0 && choicebox.getSelectionModel().getSelectedItem() != null))
            {
                ttypelist.add(vtextfield.getText());
                sitem.add((String)choicebox.getSelectionModel().getSelectedItem());
                addToList(ttypelist, sitem);

            }
            else{System.out.println("You should fill in your values");}

        });

        removebutton.setOnAction(e->{

               removeFromList(ttypelist, sitem);


        });

        remove_allbutton.setOnAction(e->{
            clearList(ttypelist, sitem);
        });

        submitbutton.setOnAction(e->{
            if(vtextfield.getText().length()<=0 || service_namelabel.getText().length()<=0)
            {
                return;
            }
            initializeGUI(ttypelist, sitem);
        });

    }

    public void addToList(List ntextfield, List sitem)
    {
        System.out.println(ntextfield.size() + "  " + sitem.size());
        count=sitem.size();
        countlabel.setText(String.valueOf(count));
    }

    public void removeFromList(List ntextfield, List sitem)
    {

        if(ntextfield.size()>0) {
            count=ntextfield.size();
            ntextfield.remove(count-1);
            sitem.remove(count-1);
            countlabel.setText(String.valueOf(count-1));
        }
        else {
            System.out.println("list is empty");
        }
    }

    public void clearList(List vlist, List sitem)
    {
        if(!(vlist.isEmpty() && sitem.isEmpty())) {
            vlist.clear();
            sitem.clear();
            count=sitem.size();
            countlabel.setText(String.valueOf(count));

        }
    }

    public void initializeGUI(List value, List field){

        Stage stage =new Stage();
        Group group = new Group();

        Scene scene=new Scene(group, 40,40);
        scene.getStylesheets().add(getClass().getResource("loginCss.css").toExternalForm());


        GridPane gridpane=new GridPane();
        gridpane.getStyleClass().add("gridbox");


        int count=0;
        int count2=0;
        for(Object name: value)
        {
                Label label=new Label((String) name);
                label.getStyleClass().add("clabel");
                gridpane.add(label, 0, count);
                count++;

        }
        for(Object name2: sitem)
        {
            if(name2=="Text Field")
            {
                TextField textfield=new TextField();

                gridpane.add(textfield, 1, count2);
                System.out.println("Count 2 textfield" + count2);
                count2++;


            }
            if(name2=="Text Area")
            {
                TextArea textarea=new TextArea();
                gridpane.add(textarea, 1, count2);
                System.out.println("Count 2 TextArea " + count2);
                count2++;

            }

        }


        stage.setTitle("Custom Templates");
        CustomTemplate ct=new CustomTemplate();
        group.getChildren().addAll(ct);
        ct.setGridPane(gridpane);
        ct.setLabel(service_namelabel.getText());



        stage.setScene(scene);
        stage.setHeight(400);
        stage.setWidth(709);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

}
