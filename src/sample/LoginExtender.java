package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Ese on 12/8/2015.
 */
public class LoginExtender {
    Stage primarystage;
    public LoginExtender(Stage primarstage)
    {
        this.primarystage=primarstage;
    }
    void display()
    {
        Group group=new Group();
        group.getChildren().addAll();
        Scene scene=new Scene(group);
        primarystage.setResizable(false);
        primarystage.show();
    }
}
