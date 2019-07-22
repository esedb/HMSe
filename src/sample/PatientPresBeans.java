package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ese on 1/27/2016.
 */
public class PatientPresBeans {
    SimpleStringProperty patientid=new SimpleStringProperty();
    SimpleStringProperty doctor=new SimpleStringProperty();
    SimpleStringProperty date=new SimpleStringProperty();
    SimpleStringProperty prescription=new SimpleStringProperty();
    PatientPresBeans(String patientid, String doctor, String date, String prescription)
    {
        setPatientid(patientid);
        setDoctor(doctor);
        setDate(date);
        setPrescription(prescription);
    }

    public String getPrescription() {
        return prescription.get();
    }

    public SimpleStringProperty prescriptionProperty() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription.set(prescription);
    }

    public String getPatientid() {
        return patientid.get();
    }

    public SimpleStringProperty patientidProperty() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid.set(patientid);
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

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }


}
