package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Ese on 12/11/2015.
 */
public class OpdService implements ExecuteService{


    private boolean successful;
    public String patientnumber;
    public String firstname;
    public String lastname;
    public String complain;
    public String Doctor;
    public String registration;
    public String bpsystolic;
    public String bpdystolic;
    public String temperature;
    public String height;
    public String SPO2;
    public String pulse;

    public String getPatientnumber() {
        return patientnumber;
    }

    public void setPatientnumber(String patientnumber) {
        this.patientnumber = patientnumber;
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

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String getDoctor() {
        return Doctor;
    }

    public void setDoctor(String doctor) {
        Doctor = doctor;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getBpsystolic() {
        return bpsystolic;
    }

    public void setBpsystolic(String bpsystolic) {
        this.bpsystolic = bpsystolic;
    }

    public String getBpdystolic() {
        return bpdystolic;
    }

    public void setBpdystolic(String bpdystolic) {
        this.bpdystolic = bpdystolic;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSPO2() {
        return SPO2;
    }

    public void setSPO2(String SPO2) {
        this.SPO2 = SPO2;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }
    public String getUsername()
    {
        return (firstname + " " + lastname);
    }


public void execute()
{
    String sql="INSERT into opd_service (patient_name, patientnumber, complain, doctor, regsitration, bpsystolic, bpdystolic, temperature, height, SPO2, pulse) " +
            "values(?,?,?,?,?,?,?,?,?,?,?)";
    DataBase dbase=new DataBase();
    Connection con;
try{
    con=dbase.getConnection();
    PreparedStatement prepstatement=con.prepareStatement(sql);
    prepstatement.setString(1,getUsername());
    prepstatement.setString(2, getPatientnumber());
    prepstatement.setString(3, getComplain());
    prepstatement.setString(4, getDoctor());
    prepstatement.setString(5, getRegistration());
    prepstatement.setString(6, getBpsystolic());
    prepstatement.setString(7, getBpdystolic());
    prepstatement.setString(8, getTemperature());
    prepstatement.setString(9, getHeight());
    prepstatement.setString(10, getSPO2());
    prepstatement.setString(11, getPulse());
    int result=prepstatement.executeUpdate();
    if(result==1)
    {
        successful=true;
        setSuccessful(successful);
    }
}
catch(Exception e)
{
    successful=false;
    setSuccessful(successful);
    e.printStackTrace();
}
}
    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }


}
