package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Ese on 1/22/2016.
 */
public class TodaysPrescriptionBean {
    StringProperty names;
    StringProperty address;
    StringProperty sex;
    StringProperty id;

    public String getNames() {
        return namesProperty().get();
    }

    public StringProperty namesProperty() {
        if(names==null) names=new SimpleStringProperty(this, "names");
        return names;
    }

    public void setNames(String names) {
        namesProperty().set(names);
    }

    public String getAddress() {
        return addressProperty().get();
    }

    public StringProperty addressProperty() {
        if(address==null) address=new SimpleStringProperty(this, "address");
        return address;
    }

    public void setAddress(String address) {
        addressProperty().set(address);
    }

    public String getSex() {
        return sexProperty().get();
    }

    public StringProperty sexProperty() {
        if(sex==null) sex=new SimpleStringProperty(this, "sex");
        return sex;
    }

    public void setSex(String sex) {
        sexProperty().set(sex);
    }

    public String getId() {
        return idProperty().get();
    }

    public StringProperty idProperty() {
        if(id==null) id=new SimpleStringProperty(this, "id");
        return id;
    }

    public void setId(String id) {
        idProperty().set(id);
    }


}
