package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Ese on 12/24/2015.
 */
public class PatientRegistryBean {
    boolean successful;
    String patientcategory;
    String patienttype;
    String firstname;
    String lastname;
    String othernames;
    String dateofbirth;
    String nationality;
    String occupation;
    String address;
    String dateofreg;
    String phonenumber;
    String placeofwork;
    String religion;
    String ethnicgroup;
    String sex;
    String bloodgroup;
    String SPO2;
    String nextofkin;
    String addressofkin;
    String kinphonenumber;
    String other;
    String imageurl;
    String ser_id;
    String familyname;
    String hmo_group;
    String sign_by;
    int age;

    public String getSign_by() {
        return sign_by;
    }

    public void setSign_by(String sign_by) {
        this.sign_by = sign_by;
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public String getHmo_group() {
        return hmo_group;
    }

    public void setHmo_group(String hmo_group) {
        this.hmo_group = hmo_group;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    String company_name;

    public String getHmo_name() {
        return hmo_name;
    }

    public void setHmo_name(String hmo_name) {
        this.hmo_name = hmo_name;
    }

    String hmo_name;


    String regFee;

    public String getRegFee() {
        return regFee;
    }

    public void setRegFee(String regFee) {
        this.regFee = regFee;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getPatientcategory() {
        return patientcategory;
    }

    public void setPatientcategory(String patientcategory) {
        this.patientcategory = patientcategory;
    }

    public String getPatienttype() {
        return patienttype;
    }

    public void setPatienttype(String patienttype) {
        this.patienttype = patienttype;
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

    public String getOthernames() {
        return othernames;
    }

    public void setOthernames(String othernames) {
        this.othernames = othernames;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateofreg() {
        return dateofreg;
    }

    public void setDateofreg(String dateofreg) {
        this.dateofreg = dateofreg;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPlaceofwork() {
        return placeofwork;
    }

    public void setPlaceofwork(String placeofwork) {
        this.placeofwork = placeofwork;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEthnicgroup() {
        return ethnicgroup;
    }

    public void setEthnicgroup(String ethnicgroup) {
        this.ethnicgroup = ethnicgroup;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getSPO2() {
        return SPO2;
    }

    public void setSPO2(String SPO2) {
        this.SPO2 = SPO2;
    }

    public String getNextofkin() {
        return nextofkin;
    }

    public void setNextofkin(String nextofkin) {
        this.nextofkin = nextofkin;
    }

    public String getAddressofkin() {
        return addressofkin;
    }

    public void setAddressofkin(String addressofkin) {
        this.addressofkin = addressofkin;
    }

    public String getKinphonenumber() {
        return kinphonenumber;
    }

    public void setKinphonenumber(String kinphonenumber) {
        this.kinphonenumber = kinphonenumber;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }





    public void execute() {
        DataBase dbase = new DataBase();
        Connection con;
        String sql = "INSERT INTO patient_registry (firstname, othername, address, dateofbirth, pacategory, patype,"
                + "nationality, occupation, addressofkin, dateofreg, nextofkin, religion, placeofwork, sex, id_sn, phoneno, " +
                "bloodgroup, SPO2, image_url, phoneofkin, others, family_name, payment_status,hmo_name, company_name, hmo_group, age, reg_by)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            con = dbase.getConnection();
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, getFirstname());
            stat.setString(2, getOthernames());
             stat.setString (3, getAddress());

            stat.setString(4, getDateofbirth());
            stat.setString(5, null);
            stat.setString(6, getPatienttype());
            stat.setString(7, getNationality());
            stat.setString(8, getOccupation());
            stat.setString(9, getAddressofkin());
            stat.setString(10, getDateofreg());
            stat.setString(11, getNextofkin());
            stat.setString(12, getReligion());
            stat.setString(13, getPlaceofwork());
            stat.setString(14, getSex());
            stat.setString(15, getSer_id());
            stat.setString(16, getPhonenumber());
            stat.setString(17, getBloodgroup());
            stat.setString(18, getSPO2());
            stat.setString(19, getImageurl());
            stat.setString(20,getKinphonenumber());
            stat.setString(21, getOther());
            stat.setString(22, getFamilyname());
            stat.setString(23, "owning");
            stat.setString(24, getHmo_name());
            stat.setString(25, getCompany_name());
            stat.setString(26, getHmo_group());
            stat.setInt(27, getAge());
            stat.setString(28, getSign_by());


            int result = stat.executeUpdate();
            if (result == 1) {
                successful = true;
                setSuccessful(successful);
            } else {
                successful = false;
                setSuccessful(successful);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public void setSer_id()
    {
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        String date = DATE_FORMAT.format(today);
        DATE_FORMAT = new SimpleDateFormat("ddMMyySS");
        date = DATE_FORMAT.format(today);
        int i=(int)(1000*Math.random());
        String hash=date + "" + i;
        this.ser_id=hash;
        debit(this.ser_id);


    }
    public String  getSer_id()
    {
        setSer_id();
        return ser_id;
    }
    public void debit(String hash)
    {


        Long cost=Long.valueOf(getRegFee());
        String typeofservice="Patient Registration";
        try{
            DataBase dbase=new DataBase();
            Connection con=dbase.getConnection();
            String sql="insert into service_rendered(id, service_type, cost) values(?,?,?)";
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setString(1, hash);
            statement.setString(2, typeofservice);
            statement.setLong(3, cost);
            int result=statement.executeUpdate();
            if(result==1)
            {
                ShowDialog.show("Patient Registered Successful");

            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public static void main(String args[])
    {


}




}






