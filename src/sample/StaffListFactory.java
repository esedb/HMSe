package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Ese on 10/28/2016.
 */
public class StaffListFactory {
    SimpleStringProperty id;
    SimpleStringProperty firstname;
    SimpleStringProperty lastname;
    SimpleStringProperty department;
    SimpleStringProperty speciality;
    SimpleStringProperty status;
    SimpleStringProperty privelege;
    SimpleStringProperty phone;
    SimpleStringProperty sex;


    public StaffListFactory(String sex, String phone, String privelege,
                            String status, String speciality, String department,
                            String lastname, String firstname, String id) {

        setSex(sex);
        setPhone(phone);

        setPrivelege(privelege);
        setStatus(status);
        setSpeciality(speciality);
        setDepartment(department);
        setLastname(lastname);
        setFirstname(firstname);
        setId(id);


    }


    public String getSpeciality() {
        return speciality.get();
    }

    public SimpleStringProperty specialityProperty() {
        if(speciality==null) speciality=new SimpleStringProperty("speciality");
        return speciality;
    }

    public void setSpeciality(String speciality) {
        specialityProperty().set(speciality);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        if(id==null) id=new SimpleStringProperty("id");
        return id;
    }

    public void setId(String id) {
        idProperty().set(id);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public SimpleStringProperty firstnameProperty() {
        if(firstname==null) firstname=new SimpleStringProperty("firstname");
        return firstname;
    }

    public void setFirstname(String firstname) {
        firstnameProperty().set(firstname);
    }

    public String getLastname() {
        return lastname.get();
    }

    public SimpleStringProperty lastnameProperty() {
        if(lastname==null) lastname=new SimpleStringProperty("lastname");
        return lastname;
    }

    public void setLastname(String lastname) {
        lastnameProperty().set(lastname);
    }

    public String getDepartment() {
        return department.get();
    }

    public SimpleStringProperty departmentProperty() {
        if(department==null) department=new SimpleStringProperty("department");
        return department;
    }

    public void setDepartment(String department) {
        departmentProperty().set(department);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        if(status==null) status=new SimpleStringProperty("status");
        return status;
    }

    public void setStatus(String status) {
        statusProperty().set(status);
    }

    public String getPrivelege() {
        return privelege.get();
    }

    public SimpleStringProperty privelegeProperty() {
        if(privelege==null) privelege=new SimpleStringProperty("privelege");
        return privelege;
    }

    public void setPrivelege(String privelege) {
        privelegeProperty().set(privelege);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        if(phone==null) phone=new SimpleStringProperty("phone");
        return phone;
    }

    public void setPhone(String phone) {
        phoneProperty().set(phone);
    }

    public String getSex() {
        return sex.get();
    }

    public SimpleStringProperty sexProperty() {
        if(sex==null) sex=new SimpleStringProperty("sex");
        return sex;
    }

    public void setSex(String sex) {
        sexProperty().set(sex);
    }


}
