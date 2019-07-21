package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ese on 1/26/2016.
 */
public class PatientListBean  {
    SimpleStringProperty patient_id=new SimpleStringProperty();
    SimpleStringProperty patient_name=new SimpleStringProperty();

    PatientListBean(String patient_id, String patient_name)
    {
        setPatient_id(patient_id);
        setPatient_name(patient_name);
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

    public String getPatient_name() {
        return patient_name.get();
    }

    public SimpleStringProperty patient_nameProperty() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name.set(patient_name);
    }



}
