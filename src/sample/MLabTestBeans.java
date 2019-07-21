package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ese on 2/3/2016.
 */
public class MLabTestBeans {
    SimpleStringProperty patientid=new SimpleStringProperty();
    SimpleStringProperty date=new SimpleStringProperty();
    SimpleStringProperty labno=new SimpleStringProperty();
    SimpleStringProperty surname=new SimpleStringProperty();
    SimpleStringProperty othername=new SimpleStringProperty();
    SimpleStringProperty sex=new SimpleStringProperty();
    SimpleStringProperty specimen=new SimpleStringProperty();
    SimpleStringProperty requestby=new SimpleStringProperty();
    SimpleStringProperty clinicalinfo=new SimpleStringProperty();
    SimpleStringProperty testtype=new SimpleStringProperty();
    SimpleStringProperty testname=new SimpleStringProperty();
    SimpleStringProperty normalvalue=new SimpleStringProperty();

    public MLabTestBeans(String patientid, String date, String labno, String surname, String othername, String sex, String specimen,
                         String requestby, String clinicalinfo, String testtype, String testname, String normalvalue)
    {
        setPatientid(patientid);
        setDate(date);
        setLabno(labno);
        setSurname(surname);
        setOthername(othername);
        setSex(sex);
        setSpecimen(specimen);
        setRequestby(requestby);
        setClinicalinfo(clinicalinfo);
        setTesttype(testtype);
        setTestname(testname);
        setNormalvalue(normalvalue);
    }

    public String getSpecimen() {
        return specimen.get();
    }

    public SimpleStringProperty specimenProperty() {
        return specimen;
    }

    public void setSpecimen(String specimen) {
        this.specimen.set(specimen);
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

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getLabno() {
        return labno.get();
    }

    public SimpleStringProperty labnoProperty() {
        return labno;
    }

    public void setLabno(String labno) {
        this.labno.set(labno);
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

    public String getSex() {
        return sex.get();
    }

    public SimpleStringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public String getRequestby() {
        return requestby.get();
    }

    public SimpleStringProperty requestbyProperty() {
        return requestby;
    }

    public void setRequestby(String requestby) {
        this.requestby.set(requestby);
    }

    public String getClinicalinfo() {
        return clinicalinfo.get();
    }

    public SimpleStringProperty clinicalinfoProperty() {
        return clinicalinfo;
    }

    public void setClinicalinfo(String clinicalinfo) {
        this.clinicalinfo.set(clinicalinfo);
    }

    public String getTesttype() {
        return testtype.get();
    }

    public SimpleStringProperty testtypeProperty() {
        return testtype;
    }

    public void setTesttype(String testtype) {
        this.testtype.set(testtype);
    }

    public String getTestname() {
        return testname.get();
    }

    public SimpleStringProperty testnameProperty() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname.set(testname);
    }

    public String getNormalvalue() {
        return normalvalue.get();
    }

    public SimpleStringProperty normalvalueProperty() {
        return normalvalue;
    }

    public void setNormalvalue(String normalvalue) {
        this.normalvalue.set(normalvalue);
    }



}
