package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.*;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

       DataBaseWrapper dw=new DataBaseWrapper();
       dw.setConnection();


        int dayspent=0;
        URL url=Main.class.getResource("ms_con.dll");
        File file=new File(url.getPath());
        if (!file.exists()) {
            try {
                file.createNewFile();
                DateTracker dt=new DateTracker();
                LocalDate ldate=LocalDate.now();
                dt.setFormer_date(ldate.getDayOfMonth());
                dt.setFormer_month(ldate.getMonthValue());
                dt.setFormer_year(ldate.getYear());
                dt.setCurrent_date(ldate.getDayOfMonth());
                dt.setCurrent_month(ldate.getMonthValue());
                dt.setCurrent_year(ldate.getYear());
                dt.incrementFirst();

                dayspent=dt.getDay_spent();

                ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(file));
                out.writeObject(dt);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        else{
            try {

                ObjectInputStream in =new ObjectInputStream(new FileInputStream(file));
                DateTracker dt = (DateTracker) in.readObject();
                LocalDate ldate = LocalDate.now();
                dt.setCurrent_date(ldate.getDayOfMonth());
                dt.setCurrent_month(ldate.getMonthValue());
                dt.setCurrent_year(ldate.getYear());

                dt.increment();
                dayspent=dt.getDay_spent();



                dt.setFormer_date(ldate.getDayOfMonth());
                dt.setFormer_month(ldate.getMonthValue());
                dt.setFormer_year(ldate.getYear());

                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                out.writeObject(dt);


            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        }
        if(dayspent>120){
            ShowDialog.show("Free trail period is already exhausted get an Unlimited version from Esel Technologies");
            return;
        }

        int successful=0;
        try{
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            if(con==null){
                successful=0;
            }
            else{
                successful=1;
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
            successful=0;

        }
        if(successful==1)
        {
            try {


                FXMLLoader fxmlloader = new FXMLLoader(Main.class.getResource("new_login.fxml"));
                VBox vbox = fxmlloader.load();
                final LoginController cl = fxmlloader.getController();

                Scene scene = new Scene(vbox);

                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();
            }
            catch(IOException e)
            {
                e.getMessage();
            }
        }
        else{
            try {
                AdminDetailsController ac = new AdminDetailsController();
                Stage stage=new Stage();
                Scene scene=new Scene(ac);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.show();

            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
    }
