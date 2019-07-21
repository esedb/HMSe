package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ese on 4/27/2017.
 */
public class WardAndBedBeans {

    SimpleIntegerProperty ward=new SimpleIntegerProperty();
    SimpleIntegerProperty bed=new SimpleIntegerProperty();
    SimpleStringProperty status=new SimpleStringProperty();
    SimpleStringProperty patient_id=new SimpleStringProperty();
    SimpleStringProperty date=new SimpleStringProperty();
    SimpleStringProperty name=new SimpleStringProperty();

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public String getPatient_id() {
        return patient_id.get();
    }

    public SimpleStringProperty patient_idProperty() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id.set(patient_id);
    }



    public int getWard() {
        return ward.get();
    }

    public SimpleIntegerProperty wardProperty() {
        return ward;
    }

    public void setWard(int ward) {
        this.ward.set(ward);
    }

    public int getBed() {
        return bed.get();
    }

    public SimpleIntegerProperty bedProperty() {
        return bed;
    }

    public void setBed(int bed) {
        this.bed.set(bed);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }


}
