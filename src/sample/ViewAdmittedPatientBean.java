package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ese on 2/24/2017.
 */
public class ViewAdmittedPatientBean {
    public SimpleStringProperty userId = new SimpleStringProperty();
    public SimpleStringProperty surname=new SimpleStringProperty();
    public SimpleStringProperty othername=new SimpleStringProperty();
    public SimpleStringProperty complain=new SimpleStringProperty();
    public SimpleStringProperty doctor=new SimpleStringProperty();
    public SimpleStringProperty b_group=new SimpleStringProperty();

    public String getOthername() {
        return othername.get();
    }

    public SimpleStringProperty othernameProperty() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername.set(othername);
    }

    public String getUserId() {
        return userId.get();
    }

    public SimpleStringProperty userIdProperty() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId.set(userId);
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

    public String getComplain() {
        return complain.get();
    }

    public SimpleStringProperty complainProperty() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain.set(complain);
    }

    public String getDoctor() {
        return doctor.get();
    }

    public SimpleStringProperty doctorProperty() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor.set(doctor);
    }

    public String getB_group() {
        return b_group.get();
    }

    public SimpleStringProperty b_groupProperty() {
        return b_group;
    }

    public void setB_group(String b_group) {
        this.b_group.set(b_group);
    }



}
