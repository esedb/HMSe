package sample;/**
 * Created by Ese on 12/8/2015.
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class Login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primarystage) {
        LoginExtender le=new LoginExtender(primarystage);
        le.display();


    }
}
