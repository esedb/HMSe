package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.css.SimpleStyleableStringProperty;

/**
 * Created by Ese on 4/17/2017.
 */
public class OpclListBean {
    SimpleStringProperty surname=new SimpleStringProperty();
    SimpleStringProperty othername=new SimpleStringProperty();
    SimpleStringProperty  operationtype=new SimpleStringProperty();
    SimpleStringProperty patient_id=new SimpleStringProperty();
    SimpleStringProperty age=new SimpleStringProperty();
    SimpleStringProperty sex=new SimpleStringProperty();
    SimpleStringProperty sign_by=  new SimpleStringProperty();
    SimpleStringProperty data=new SimpleStringProperty();

    public String getData() {
        return data.get();
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }



    public String getAge() {
        return age.get();
    }

    public SimpleStringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
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

    public String getOperationtype() {
        return operationtype.get();
    }

    public SimpleStringProperty operationtypeProperty() {
        return operationtype;
    }

    public void setOperationtype(String operationtype) {
        this.operationtype.set(operationtype);
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

    public String getSign_by() {
        return sign_by.get();
    }

    public SimpleStringProperty sign_byProperty() {
        return sign_by;
    }

    public void setSign_by(String sign_by) {
        this.sign_by.set(sign_by);
    }

    public String getSex() {
        return sex.get();
    }

    public SimpleStringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }



}
