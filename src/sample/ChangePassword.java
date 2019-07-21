package sample;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Connection;

/**
 * Created by Ese on 12/2/2015.
 */
public class ChangePassword {
    SimpleStringProperty username;
    SimpleStringProperty oldpassword;
    SimpleStringProperty newpassword;
    public ChangePassword(String username, String oldpassword, String newpassword)
    {
        this.username.set(username);
        this.oldpassword.set(oldpassword);
        this.newpassword.set(newpassword);
    }
    public String getUsername()
    {
        return username.get();
    }
    public String getOldpassword()
    {
        return oldpassword.get();
    }
    public String getNewpassword()
    {
        return newpassword.get();
    }


}
