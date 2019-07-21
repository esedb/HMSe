package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Set;


/**
 * Created by Ese on 12/8/2015.
 */
public class LoginController {

    //try to check vital signs it seems there is a name issure


    @FXML
    private GridPane logingrid;

    @FXML
    private TextField firstnamefld;


    @FXML
    private TextField lastnamefld;

    @FXML
    private Button submitbutton;

    @FXML
    private PasswordField pwdtextfield;

    @FXML
    private ComboBox<?> choicebox;
    @FXML
    public void initialize() {


        ObservableList department=FXCollections.observableArrayList();
        department.addAll("Administrator", "Doctor", "Nurse", "Accountant");

        try{
            DataBase dbase =new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement("SELECT department from department_records ");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                department.addAll(rs.getString("department"));
            }


        }
        catch(Exception ex){
            ex.printStackTrace();
        }



        choicebox.setItems(department);
        //coming back here
        choicebox.getSelectionModel().selectFirst();

        submitbutton.setOnAction(e->
        {

            String choiceboxvalue= (String)choicebox.getValue();
            if(choiceboxvalue.equalsIgnoreCase("Administrator")){
                String admin_username="";
                String admin_password="";

                try {

                    Properties properties=new Properties();

                    FileInputStream input = new FileInputStream("properties.dat");
                    properties.load(input);
                    Set<Object> keys = properties.keySet();
                    admin_username=properties.getProperty("admin_username");
                    admin_password=properties.getProperty("Admin_password");

                    input.close();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                String adminname=firstnamefld.getText()+lastnamefld.getText();
                String password=pwdtextfield.getText();
                if(admin_username.equalsIgnoreCase(adminname) && admin_password.equalsIgnoreCase(password)){

                    try {
                        Stage stage=new Stage();

                        Controller cl=new Controller(choicebox.getValue().toString());
                        Controller.sign_by=firstnamefld.getText() + " " + lastnamefld.getText();
                        System.out.println("choice box value is " + choicebox.getValue().toString());
                        cl.setLogonname(firstnamefld.getText() +" " + lastnamefld.getText());


                        Scene scene = new Scene(cl, 1000, 600);
                        stage.setScene(scene);
                        stage.setResizable(true);
                        cl.stage=stage;


                        stage.show();



                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                }
                else{
                    ShowDialog.show("Adminstrator username or passoword is incorrect");
                }

            }
            if(choiceboxvalue.equalsIgnoreCase("None")){
                ShowDialog.show("Department must not be None");
                return;
            }
            if(!choicebox.getValue().toString().equalsIgnoreCase("None") &&
                    !choicebox.getValue().toString().equalsIgnoreCase("Administrator") && choicebox.getValue().toString().length()>0) {

                final String firstname = firstnamefld.getText();
                String lastname = lastnamefld.getText();
                final String password = pwdtextfield.getText();

                LoginBeans loginbeans = new LoginBeans();


                loginbeans.setFirstname(firstname);
                loginbeans.setLastname(lastname);
                loginbeans.setPassword(password);
                loginbeans.setDepartment(choiceboxvalue);
                loginbeans.execute();

                if (loginbeans.isSuccessful()) {
                    try {
                        Stage stage = new Stage();

                        Controller cl = new Controller(choicebox.getValue().toString());
                        Controller.sign_by = firstnamefld.getText() + " " + lastnamefld.getText();
                        System.out.println("choice box value is " + choicebox.getValue().toString());
                        cl.setLogonname(firstnamefld.getText() + " " + lastnamefld.getText());


                        Scene scene = new Scene(cl, 1000, 600);
                        stage.setScene(scene);
                        stage.setResizable(true);
                        cl.stage = stage;


                        stage.show();


                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {

                    ShowDialog.show("Either your name or password is incorrect");


                }
            }




        });




        /*

        ObservableList departmentlist=FXCollections.observableArrayList();

        try {
            String sql="select * from department_records";
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){

                departmentlist.addAll(rs.getString("department"));

            }

        } catch (Exception ex){
            ex.printStackTrace();
        }


        ObservableList choiceItems= FXCollections.observableArrayList(
                "Admin",
                "Account",
                "MD",
                "CEO",
                "Nurse",
                "Consultants",
                "Pharmacist",
                "Lab Scientist",
                "Medical Officials",
                "Receptionist",
                "Doctor"
        );

        choicebox.setItems(departmentlist);
        choicebox.getSelectionModel().selectFirst();


        LoginBeans loginbeans=new LoginBeans();



        submitbutton.setOnAction(e->
        {
            String choiceboxvalue= (String)choicebox.getValue();

            final String firstname=firstnamefld.getText();
            String lastname=lastnamefld.getText();
            final String password=pwdtextfield.getText();
            System.out.println("real password : " + password);
            System.out.println("choice box value is :" + choiceboxvalue);

            loginbeans.setFirstname(firstname);
            loginbeans.setLastname(lastname);
            loginbeans.setPassword(password);
            loginbeans.setDepartment(choiceboxvalue);
            loginbeans.execute();

           if(loginbeans.isSuccessful()) {
               try {
                   Stage stage=new Stage();
                   FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("genesisfxml.fxml"));
                   //genesis.fxml is the main container class i.e the orginin and base of most GUI

                   VBox vbox = loader.load();
                   final Controller cl =loader.getController();




                   Controller.sign_by=firstnamefld.getText() + " " + lastnamefld.getText();
                   cl.setLogonname(firstnamefld.getText() +" " + lastnamefld.getText());


                   Scene scene = new Scene(vbox, 1000, 600);
                   stage.setScene(scene);
                   stage.setResizable(true);
                   cl.stage=stage;


                   stage.show();



               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }
            else
           {


           }




        });
        */


    }



}
