package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 * Created by Ese on 5/2/2017.
 */
public class DischargePatientBeans {
    SimpleStringProperty id_sn=new SimpleStringProperty();
    SimpleStringProperty surname=new SimpleStringProperty();
    SimpleStringProperty othername=new SimpleStringProperty();
    SimpleDoubleProperty deposits=new SimpleDoubleProperty();
    SimpleDoubleProperty scost=new SimpleDoubleProperty();
    SimpleDoubleProperty receivables=new SimpleDoubleProperty();
    SimpleStringProperty payment_status=new SimpleStringProperty();
    SimpleStringProperty discharge_status=new SimpleStringProperty();

    public double getDeposits() {
        return deposits.get();
    }

    public SimpleDoubleProperty depositsProperty() {
        return deposits;
    }

    public void setDeposits(double deposits) {
        this.deposits.set(deposits);
    }

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

    public String getOthername() {
        return othername.get();
    }

    public SimpleStringProperty othernameProperty() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername.set(othername);
    }

    public double getScost() {
        return scost.get();
    }

    public SimpleDoubleProperty scostProperty() {
        return scost;
    }

    public void setScost(double scost) {
        this.scost.set(scost);
    }

    public double getReceivables() {
        return receivables.get();
    }

    public SimpleDoubleProperty receivablesProperty() {
        return receivables;
    }

    public void setReceivables(double receivables) {
        this.receivables.set(receivables);
    }

    public String getPayment_status() {
        return payment_status.get();
    }

    public SimpleStringProperty payment_statusProperty() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status.set(payment_status);
    }

    public String getDischarge_status() {
        return discharge_status.get();
    }

    public SimpleStringProperty discharge_statusProperty() {
        return discharge_status;
    }

    public void setDischarge_status(String discharge_status) {
        this.discharge_status.set(discharge_status);
    }


}
