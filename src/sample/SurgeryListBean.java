package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Ese on 4/6/2017.
 */
public class SurgeryListBean {

    StringProperty surname;
    StringProperty othername;
    StringProperty surgery_type;
    StringProperty id_sn;

    public String getOthername() {
        return othername.get();
    }

    public StringProperty othernameProperty() {
        if(othername==null) othername=new SimpleStringProperty(this, "othername");
        return othername;
    }

    public void setOthername(String othername) {
        othernameProperty().set(othername);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        if(surname==null) surname=new SimpleStringProperty(this, "surname");
        return surname;
    }

    public void setSurname(String surname) {
        surnameProperty().set(surname);
    }

    public String getSurgery_type() {
        return surgery_type.get();
    }

    public StringProperty surgery_typeProperty() {
        if(surgery_type==null) surgery_type=new SimpleStringProperty(this, "surgery_type");
        return surgery_type;
    }

    public void setSurgery_type(String surgery_type) {
        surgery_typeProperty().set(surgery_type);
    }

    public String getId_sn() {
        return id_sn.get();
    }

    public StringProperty id_snProperty() {
        if(id_sn==null) id_sn=new SimpleStringProperty(this, "id_sn");
        return id_sn;
    }

    public void setId_sn(String id_sn) {
        id_snProperty().set(id_sn);
    }


}
