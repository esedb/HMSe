package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ese on 5/3/2017.
 */
public class AttendanceHistoryBeans {

    SimpleStringProperty id_sn=new SimpleStringProperty();
    SimpleStringProperty surname=new SimpleStringProperty();
    SimpleStringProperty othernames=new SimpleStringProperty();
    SimpleStringProperty date=new SimpleStringProperty();
    SimpleStringProperty time=new SimpleStringProperty();

    public String getId_sn() {
        return id_sn.get();
    }

    public SimpleStringProperty id_snProperty() {
        return id_sn;
    }

    public void setId_sn(String id_sn) {
        this.id_sn.set(id_sn);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getOthernames() {
        return othernames.get();
    }

    public SimpleStringProperty othernamesProperty() {
        return othernames;
    }

    public void setOthernames(String othernames) {
        this.othernames.set(othernames);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }


}
