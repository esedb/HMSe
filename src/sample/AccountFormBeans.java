package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Ese on 12/22/2015.
 */
public class AccountFormBeans implements ExecuteService{
    String title;
    String firstname;
    String lastname;
    String sexchice;
    String dateofbirth;
    String stateoforigin;
    String phoneno;
    String religion;
    String department;
    String qualification;
    String specialty;
    String dateofemployment;
    String relaofkin;
    String marital_status;



    String phoneofrel;

    boolean successful;



    String addres;

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }


    public AccountFormBeans()
    {

    }
    public AccountFormBeans(String title, String firstname, String lastname, String sexchice, String dateofbirth,
                            String stateoforigin, String phoneno, String religion, String department,
                            String qualification, String specialty, String dateofemployment, String relaofkin, String addres, String phoneofrel, String marital_status) {
        this.title = title;
        this.firstname = firstname;
        this.lastname = lastname;
        this.sexchice = sexchice;
        this.dateofbirth = dateofbirth;
        this.stateoforigin = stateoforigin;
        this.phoneno = phoneno;
        this.religion = religion;
        this.department = department;
        this.qualification = qualification;
        this.specialty = specialty;
        this.dateofemployment = dateofemployment;
        this.relaofkin = relaofkin;
        this.addres=addres;
        this.marital_status=marital_status;
    }




    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getSexchice() {
        return sexchice;
    }

    public void setSexchice(String sexchice) {
        this.sexchice = sexchice;
    }

    public String getStateoforigin() {
        return stateoforigin;
    }

    public void setStateoforigin(String stateoforigin) {
        this.stateoforigin = stateoforigin;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getDateofemployment() {
        return dateofemployment;
    }

    public void setDateofemployment(String dateofemployment) {
        this.dateofemployment = dateofemployment;
    }

    public String getRelaofkin() {
        return relaofkin;
    }

    public void setRelaofkin(String relaofkin) {
        this.relaofkin = relaofkin;
    }
    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }
    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
    public String getPhoneofrel() {
        return phoneofrel;
    }

    public void setPhoneofrel(String phoneofrel) {
        this.phoneofrel = phoneofrel;
    }
public void execute() {
    String sql = "INSERT INTO  account_records(firstname, lastname, sexchoice, dateofbirth, address, stateoforigin," +
            "department, specialty, dateofemployment, phone, marital_status) values (?,?,?,?,?,?,?,?,?,?,?)";
    try(DataBase dbase = new DataBase(); Connection con = dbase.getConnection(); PreparedStatement prepstatement = con.prepareStatement(sql)){
        prepstatement.setString(1, getFirstname());
        prepstatement.setString(2, getLastname());
        prepstatement.setString(3, getSexchice());
        prepstatement.setString(4, getDateofbirth());
        prepstatement.setString(5, getAddres());
        prepstatement.setString(6, getStateoforigin());
        prepstatement.setString(7, getDepartment());
        prepstatement.setString(8, getSpecialty());
        prepstatement.setString(9, getDateofemployment());
        prepstatement.setString(10, getPhoneno());
        prepstatement.setString(11, getMarital_status());
        int result = prepstatement.executeUpdate();
        if (result == 1)
            successful = true;
        ShowDialog.show("Operation successful");
        System.out.println("successful _: " + isSuccessful());

    } catch (Exception e) {
        ShowDialog.show("Operation unsuccessful");
        setSuccessful(successful);
        e.printStackTrace();

    }
}

}



