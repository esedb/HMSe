package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Ese on 4/1/2017.
 */
public class HMOPatientFactory {

    StringProperty hmo_name;
    public String getHmo_name() {
        return hmo_name.get();
    }

    public StringProperty hmo_nameProperty() {
        if(hmo_name==null) hmo_name=new SimpleStringProperty(this, "hmo_name");
        return hmo_name;
    }

    public void setHmo_name(String hmo_name) {
        hmo_nameProperty().set(hmo_name);
    }


    public HMOPatientFactory(String firstname, String lastname,String typeofpatient, String patientserial, String dateofreg, int phonenumber, String hmo_name)
    {
        setFirstname(firstname);
        setLastname(lastname);
        setTypeofpatient(typeofpatient);
        setPatientserial(patientserial);
        setPhonenumber(phonenumber);
        setDateofreg(dateofreg);
        setHmo_name(hmo_name);


    }

    StringProperty patientserial;
    public String getPatientserial() {
        return patientserialProperty().get();
    }
    public void setPatientserial(String patientserial) {
        patientserialProperty().set(patientserial);
    }

    public StringProperty patientserialProperty() {
        if (patientserial==null)
            patientserial=new SimpleStringProperty(this, "Patient Serial");
        return patientserial;
    }


    StringProperty firstname;
    public String getFirstname() {
        return firstnameProperty().get();
    }
    public void setFirstname(String firstname) {
        firstnameProperty().set(firstname);
    }

    public StringProperty firstnameProperty() {
        if(firstname ==null)
            firstname=new SimpleStringProperty(this, "firstname");
        return firstname;
    }


    StringProperty lastname;
    public String getLastname() {
        return lastnameProperty().get();
    }
    public void setLastname(String lastname) {
        lastnameProperty().set(lastname);
    }

    public StringProperty lastnameProperty() {
        if(lastname==null)
            lastname=new SimpleStringProperty(this,"lastname");
        return lastname;
    }


    StringProperty typeofpatient;
    public String getTypeofpatient() {
        return typeofpatientProperty().get();
    }

    public StringProperty typeofpatientProperty() {
        if(typeofpatient==null)
            typeofpatient=new SimpleStringProperty(this, "typeofpatient");
        return typeofpatient;
    }

    public void setTypeofpatient(String typeofpatient) {
        typeofpatientProperty().set(typeofpatient);
    }
    StringProperty dateofreg;
    public String getDateofreg() {
        return dateofregProperty().get();
    }

    public StringProperty dateofregProperty() {
        if(dateofreg==null)
            dateofreg=new SimpleStringProperty(this, "dateofreg");
        return dateofreg;
    }

    public void setDateofreg(String dateofreg) {
        dateofregProperty().set(dateofreg);
    }
    IntegerProperty phonenumber;
    public int getPhonenumber() {
        return phonenumberProperty().get();
    }
    public void setPhonenumber(int phonenumber) {
        phonenumberProperty().set(phonenumber);
    }

    public IntegerProperty phonenumberProperty() {
        if(phonenumber==null)
            phonenumber=new SimpleIntegerProperty(this, "phonenumber");
        return phonenumber;
    }



}
