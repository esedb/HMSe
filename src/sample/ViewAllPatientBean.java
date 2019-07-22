package sample;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class ViewAllPatientBean implements Serializable {

    public SimpleStringProperty userId = new SimpleStringProperty();
    public SimpleStringProperty firstname = new SimpleStringProperty();
    public SimpleStringProperty lastname=new SimpleStringProperty();
    public SimpleStringProperty othername = new SimpleStringProperty();
    public SimpleStringProperty patienttype = new SimpleStringProperty();
    public SimpleStringProperty patientcategory = new SimpleStringProperty();
    public SimpleStringProperty sex = new SimpleStringProperty();
    public SimpleIntegerProperty cost=new SimpleIntegerProperty();
    public SimpleIntegerProperty receivables=new SimpleIntegerProperty();
    public SimpleIntegerProperty deposit=new SimpleIntegerProperty();
    public SimpleStringProperty hmo_name=new SimpleStringProperty();
    public SimpleStringProperty address=new SimpleStringProperty();
    public SimpleStringProperty dateofreg=new SimpleStringProperty();
    public SimpleStringProperty payment_status=new SimpleStringProperty();
    public SimpleStringProperty phone=new SimpleStringProperty();

    public String getPayment_status() {
        return payment_status.get();
    }

    public SimpleStringProperty payment_statusProperty() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status.set(payment_status);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }




    public String getDateofreg() {
        return dateofreg.get();
    }

    public SimpleStringProperty dateofregProperty() {
        return dateofreg;
    }

    public void setDateofreg(String dateofreg) {
        this.dateofreg.set(dateofreg);
    }



    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }



    public String getHmo_name() {
        return hmo_name.get();
    }

    public SimpleStringProperty hmo_nameProperty() {
        return hmo_name;
    }

    public void setHmo_name(String hmo_name) {
        this.hmo_name.set(hmo_name);
    }



    public Integer getDeposit()
    {
        return deposit.get();
    }
    public Integer getReceivables()
    {
        return receivables.get();
    }
    public Integer getCost()
    {
        return cost.get();
    }


public String getUserId() {
        return userId.get();
        }

public String getFirstname() {
        return firstname.get();
        }
public String getLastname()
{
    return lastname.get();
}
public String getOthername() {
        return othername.get();
        }

public String getPatienttype() {
        return patienttype.get();
        }

public String getPatientcategory() {
        return patientcategory.get();
        }

public String getSex() {
        return sex.get();
        }
        }
