package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Ese on 3/31/2017.
 */
public class NHMOBeans {
    StringProperty hmoname;
    StringProperty address;
    StringProperty phone;
    StringProperty state;
    StringProperty city;
    StringProperty country;
    StringProperty email;

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        if(email==null) email = new SimpleStringProperty(this, "email");
        return email;
    }

    public void setEmail(String email) {
        emailProperty().set(email);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        if(country==null) country = new SimpleStringProperty(this, "country");
        return country;
    }

    public void setCountry(String country) {
        countryProperty().set(country);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        if(city==null) city = new SimpleStringProperty(this, "city");
        return city;
    }

    public void setCity(String city) {
        cityProperty().set(city);
    }

    public String getState() {
        return state.get();
    }

    public StringProperty stateProperty() {
        if(state==null) state = new SimpleStringProperty(this, "state");
        return state;
    }

    public void setState(String state) {
        stateProperty().set(state);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        if(phone==null) phone=new SimpleStringProperty(this, "phone");

        return phone;
    }

    public void setPhone(String phone) {
        phoneProperty().set(phone);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        if(address==null) address = new SimpleStringProperty(this, "address");
        return address;
    }

    public void setAddress(String address) {
        addressProperty().set(address);
    }

    public String getHmoname() {
        return hmoname.get();
    }

    public StringProperty hmonameProperty() {
        if(hmoname==null) hmoname = new SimpleStringProperty(this, "hmoname");
        return hmoname;
    }

    public void setHmoname(String hmoname) {
        hmonameProperty().set(hmoname);
    }





}
