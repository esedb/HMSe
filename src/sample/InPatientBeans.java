package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ese on 2/29/2016.
 */
public class InPatientBeans {
    public InPatientBeans(String idsn, String surname, String othernmae, String complains, String date) {
        idsnProperty().set(idsn);
        surnameProperty().set(surname);
        othernmaeProperty().set(othernmae);
        complainsProperty().set(complains);
        dateProperty().set(date);

    }

    public String getIdsn() {
        return idsn.get();
    }

    public SimpleStringProperty idsnProperty() {
        return idsn;
    }

    public void setIdsn(String idsn) {
        this.idsn.set(idsn);
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

    public String getOthernmae() {
        return othernmae.get();
    }

    public SimpleStringProperty othernmaeProperty() {
        return othernmae;
    }

    public void setOthernmae(String othernmae) {
        this.othernmae.set(othernmae);
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

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    SimpleStringProperty idsn=new SimpleStringProperty();
    SimpleStringProperty surname=new SimpleStringProperty();
    SimpleStringProperty othernmae=new SimpleStringProperty();
    SimpleStringProperty complains=new SimpleStringProperty();
    SimpleStringProperty date=new SimpleStringProperty();
}
