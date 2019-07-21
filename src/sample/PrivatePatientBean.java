package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 12/18/2015.
 */
public class PrivatePatientBean implements ExecuteService{


    int sum;


    boolean successful;

    String addressofkin;
    String laboratory;
    String treatment;
    String phoneno;
    String placeofwork;
    String pharmacy;
    String nationality;
    String religion;
    String lastname;
    String occupation;
    String dateofbirth;
    String firstname;
    String nextofkin;
    String consultation;
    String radiology;
    String mortuary;
    String dateofreg;
    String sex;

    public String getAddressofkin() {
        return addressofkin;
    }

    public void setAddressofkin(String addressofkin) {
        this.addressofkin = addressofkin;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPlaceofwork() {
        return placeofwork;
    }

    public void setPlaceofwork(String placeofwork) {
        this.placeofwork = placeofwork;
    }

    public String getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(String pharmacy) {
        this.pharmacy = pharmacy;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getNextofkin() {
        return nextofkin;
    }

    public void setNextofkin(String nextofkin) {
        this.nextofkin = nextofkin;
    }

    public String getConsultation() {
        return consultation;
    }

    public void setConsultation(String consultation) {
        this.consultation = consultation;
    }

    public String getRadiology() {
        return radiology;
    }

    public void setRadiology(String radiology) {
        this.radiology = radiology;
    }

    public String getMortuary() {
        return mortuary;
    }

    public void setMortuary(String mortuary) {
        this.mortuary = mortuary;
    }

    public String getDateofreg() {
        return dateofreg;
    }

    public void setDateofreg(String dateofreg) {
        this.dateofreg = dateofreg;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getUsername(){
        return (getFirstname() + " " + getLastname());
    }
    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }


    public void execute()
{
    String sql="INSERT INTO patient_records(patient_name, dateofreg, nationality, occupation, addressofkin, phonenumber, dob, sex, " +
            "religion, placeofwork, radiology_service, pharmacy_service, treatment_service," +
            "consultation_service, mortuary, nextofkin, total_cost) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    DataBase dbase=new DataBase();
    Connection con;
    try
    {
       con=dbase.getConnection();
        PreparedStatement prepstatement=con.prepareStatement(sql);
        prepstatement.setString(1, getUsername());
        prepstatement.setString(2,getDateofreg());
        prepstatement.setString(3, getNationality());
        prepstatement.setString(4, getOccupation());
        prepstatement.setString(5, getAddressofkin());
        prepstatement.setString(6, getPhoneno());
        prepstatement.setString(7, getDateofbirth());
        prepstatement.setString(8, getSex());
        prepstatement.setString(9, getReligion());
        prepstatement.setString(10, getPlaceofwork());
        prepstatement.setString(11, getRadiology());
        prepstatement.setString(12, getPharmacy());
        prepstatement.setString(13, getTreatment());
        prepstatement.setString(14, getConsultation());
        prepstatement.setString(15, getMortuary());
        prepstatement.setString(16, getNextofkin());
        prepstatement.setInt(17, getSum());
        int rs=prepstatement.executeUpdate();
        if(rs==1)
        successful=true;
        setSuccessful(successful);


    }
    catch(Exception e)
    {
        successful=false;
        setSuccessful(successful);
        e.printStackTrace();

    }
}



}