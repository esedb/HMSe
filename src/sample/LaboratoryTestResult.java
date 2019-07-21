package sample;

import java.sql.Connection;

/**
 * Created by Ese on 12/11/2015.
 */
public class LaboratoryTestResult implements ExecuteService{
    public boolean successful;
    public String patientnumber;
    public String date;
    public String labno;
    public String firstname;
    public String lastname;
    public String sex;
    public int Age;
    public int specimen;
    public String requestedby;
    public String labmedsec;
    public String clinicalinfomation;

    public String getPatientnumber() {
        return patientnumber;
    }

    public void setPatientnumber(String patientnumber) {
        this.patientnumber = patientnumber;

        }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLabno() {
        return labno;
    }

    public void setLabno(String labno) {
        this.labno = labno;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getSpecimen() {
        return specimen;
    }

    public void setSpecimen(int specimen) {
        this.specimen = specimen;
    }

    public String getRequestedby() {
        return requestedby;
    }

    public void setRequestedby(String requestedby) {
        this.requestedby = requestedby;
    }

    public String getLabmedsec() {
        return labmedsec;
    }

    public void setLabmedsec(String labmedsec) {
        this.labmedsec = labmedsec;
    }

    public String getClinicalinfomation() {
        return clinicalinfomation;
    }

    public void setClinicalinfomation(String clinicalinfomation) {
        this.clinicalinfomation = clinicalinfomation;
    }
public void execute()
{
    DataBase dbase=new DataBase();

    try{
        Connection con=dbase.getConnection();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }





}


}
