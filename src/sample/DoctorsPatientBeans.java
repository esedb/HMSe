package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Ese on 1/24/2016.
 */
public class DoctorsPatientBeans {
    StringProperty surname;
    StringProperty lastname;
    StringProperty dob;
    StringProperty sex;
    StringProperty patient_catregory;
    StringProperty date;
    StringProperty id;

    DoctorsPatientBeans()
    {

    }

    DoctorsPatientBeans(String surname, String lastname, String dob, String sex, String patient_catregory, String date, String id)
    {
        setSurname(surname);
        setLastname(lastname);
        setDob(dob);
        setSex(sex);
        setPatient_catregory(patient_catregory);
        setDate(date);
        setId(id);

    }

    public String getLastname() {
        return lastname.get();
    }

    public StringProperty lastnameProperty() {
        if(lastname==null) lastname=new SimpleStringProperty(this, "lastname");
        return lastname;
    }

    public void setLastname(String lastname) {
        lastnameProperty().set(lastname);
    }

    public String getSurname() {
        return surnameProperty().get();
    }

    public StringProperty surnameProperty() {
        if(surname==null) surname=new SimpleStringProperty(this,"surname");
        return surname;
    }

    public void setSurname(String surname) {
        surnameProperty().set(surname);
    }

    public String getDob() {
        return dobProperty().get();
    }

    public StringProperty dobProperty() {
        if(dob==null) dob=new SimpleStringProperty(this, "dob");
        return dob;
    }

    public void setDob(String dob) {
        dobProperty().set(dob);
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

    public String getPatient_catregory() {
        return patient_catregoryProperty().get();
    }

    public StringProperty patient_catregoryProperty() {
        if(patient_catregory==null) patient_catregory=new SimpleStringProperty(this, "patient_catregory");
        return patient_catregory;
    }

    public void setPatient_catregory(String patient_catregory) {
        patient_catregoryProperty().set(patient_catregory);
    }

    public String getDate() {
        return dateProperty().get();
    }

    public StringProperty dateProperty() {
        if(date==null) date=new SimpleStringProperty(this, "date");
        return date;
    }

    public void setDate(String date) {
        dateProperty().set(date);
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
