package sample;
import org.w3c.dom.*;

import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by Ese on 12/21/2015.]'
 * /**
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 */
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

/**
 * A sample that shows a choice box with several options. The ChoiceBox control
 * displays a default or current selection, with an icon to click that expands
 * the list for a selection.
 *
 * @see javafx.scene.control.ChoiceBox
 * @related controls/buttons/CheckBoxes
 * @related controls/buttons/ToggleButton
 * @related controls/toolbar/ToolBar
 */
/*public class ChoiceBoxSample extends Application {

    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 150,100));
        ChoiceBox cb = new ChoiceBox();
        cb.getItems().addAll("Dog", "Cat", "Horse");
        cb.getSelectionModel().selectFirst();
        root.getChildren().add(cb);
    }

    public double getSampleWidth() { return 150; }

    public double getSampleHeight() { return 100; }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}


*
 *
 *
 */
public class XmlTest {
    public static void main(String args[])
    {
        try {
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            File file = new File(XmlTest.class.getResource("testxm.xml").getFile());

            System.out.println(file.toString());
            DocumentBuilder builder = dbfactory.newDocumentBuilder();
            Document doc = builder.parse(file);
            Element rootElement = doc.getDocumentElement();
            Element elem=doc.createElement("Personx");
            Text txt=doc.createTextNode("Wat you say");
            elem.appendChild(txt);
            NodeList nolist=doc.getElementsByTagName("journal");
            nolist.item(0).getParentNode().insertBefore(elem, nolist.item(0));

            System.out.println("Root Element is: " + rootElement.getTagName());
            NodeList nodelist = rootElement.getChildNodes();
            for (int i = 0; i < nodelist.getLength(); i++){
                Node node=nodelist.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE)
                {
                    Element element=(Element)node;
                    System.out.println("element is _: " + element.getTagName());
                }
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

}
