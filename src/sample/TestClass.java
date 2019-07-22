package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Ese on 12/3/2015.
 */
public class TestClass {
    public static void main(String args[]) {
        Properties properties = new Properties();
        try {
            FileInputStream input = new FileInputStream("properties.dat");
            properties.load(input);
            Set<Object> keys = properties.keySet();
            for (Object key : keys)
                System.out.println((String) key + "  " +properties.getProperty((String) key));

            String password=properties.getProperty("Admin_password");
            System.out.println("Password_:  " + password);
        } catch (Exception e) {

        }
    }
}