package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ese on 7/10/2016.
 */
public class ViewAllInpatientBeans {

    SimpleStringProperty patient_id=new SimpleStringProperty();
    SimpleStringProperty surname=new SimpleStringProperty();
    SimpleStringProperty name=new SimpleStringProperty();
    SimpleStringProperty complains=new SimpleStringProperty();
    SimpleStringProperty assigned=new SimpleStringProperty();

    public String getPatient_id() {
        return patient_id.get();
    }

    public SimpleStringProperty patient_idProperty() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id.set(patient_id);
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getComplains() {
        return complains.get();
    }

    public SimpleStringProperty complainsProperty() {
        return complains;
    }

    public void setComplains(String complains) {
        this.complains.set(complains);
    }

    public String getAssigned() {
        return assigned.get();
    }

    public SimpleStringProperty assignedProperty() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned.set(assigned);
    }




}
