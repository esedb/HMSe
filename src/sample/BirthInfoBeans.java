package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Ese on 4/3/2017.
 */
public class BirthInfoBeans {

    StringProperty birthid;
    StringProperty our_ref;
    StringProperty place_of_birth;
    StringProperty  name_of_father;
    StringProperty occu_of_father;
    StringProperty tribe_of_father;
    StringProperty lga;
    StringProperty time_of_delivery;
    StringProperty name_of_mother;
    StringProperty tribe_of_mother;
    StringProperty sex_of_baby;
    StringProperty weight;
    StringProperty date_of_delivery;
    StringProperty sign;
    StringProperty perm_address;

    public String getOur_ref() {
        return our_ref.get();
    }

    public StringProperty our_refProperty() {
        return our_ref;
    }

    public void setOur_ref(String our_ref) {
        our_refProperty().set(our_ref);
    }

    public String getBirthid() {
        return birthid.get();
    }

    public StringProperty birthidProperty() {
        if(birthid==null) birthid=new SimpleStringProperty(this, "birthid");
        return birthid;
    }

    public void setBirthid(String birthid) {
       birthidProperty().set(birthid);
    }

    public String getPlace_of_birth() {
        return place_of_birth.get();
    }

    public StringProperty place_of_birthProperty() {
        if(place_of_birth==null) place_of_birth=new SimpleStringProperty(this, "place_of_birth");
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        place_of_birthProperty().set(place_of_birth);
    }

    public String getName_of_father() {
        return name_of_father.get();
    }

    public StringProperty name_of_fatherProperty() {
        if(name_of_father==null) name_of_father=new SimpleStringProperty(this, "name_of_father");
        return name_of_father;
    }

    public void setName_of_father(String name_of_father) {
        name_of_fatherProperty().set(name_of_father);
    }

    public String getOccu_of_father() {
        return occu_of_father.get();
    }

    public StringProperty occu_of_fatherProperty() {
        if(occu_of_father==null) occu_of_father=new SimpleStringProperty(this, "occu_of_father");
        return occu_of_father;

    }

    public void setOccu_of_father(String occu_of_father) {
        occu_of_fatherProperty().set(occu_of_father);
    }

    public String getTribe_of_father() {
        return tribe_of_father.get();
    }

    public StringProperty tribe_of_fatherProperty() {
        if(tribe_of_father==null) tribe_of_father=new SimpleStringProperty(this, "tribe_of_father");
        return tribe_of_father;
    }

    public void setTribe_of_father(String tribe_of_father) {
        tribe_of_fatherProperty().set(tribe_of_father);
    }

    public String getLga() {
        return lga.get();
    }

    public StringProperty lgaProperty() {
        if(lga==null) lga=new SimpleStringProperty(this, "lga");
        return lga;
    }

    public void setLga(String lga) {
        lgaProperty().set(lga);
    }

    public String getTime_of_delivery() {
        return time_of_delivery.get();
    }

    public StringProperty time_of_deliveryProperty() {
        if(time_of_delivery==null) time_of_delivery=new SimpleStringProperty(this, "time_of_delivery");
        return time_of_delivery;
    }

    public void setTime_of_delivery(String time_of_delivery) {
        time_of_deliveryProperty().set(time_of_delivery);
    }

    public String getName_of_mother() {
        return name_of_mother.get();
    }

    public StringProperty name_of_motherProperty() {
        if(name_of_mother==null) name_of_mother=new SimpleStringProperty(this, "name_of_mother");
        return name_of_mother;
    }

    public void setName_of_mother(String name_of_mother) {
        name_of_motherProperty().set(name_of_mother);
    }

    public String getTribe_of_mother() {
        return tribe_of_mother.get();
    }

    public StringProperty tribe_of_motherProperty() {
        if(tribe_of_mother==null) tribe_of_mother=new SimpleStringProperty(this, "tribe_of_mother");
        return tribe_of_mother;
    }

    public void setTribe_of_mother(String tribe_of_mother) {
        tribe_of_motherProperty().set(tribe_of_mother);
    }

    public String getSex_of_baby() {
        return sex_of_baby.get();
    }

    public StringProperty sex_of_babyProperty() {
        if(sex_of_baby==null) sex_of_baby=new SimpleStringProperty(this, "sex_of_baby");
        return sex_of_baby;
    }

    public void setSex_of_baby(String sex_of_baby) {
        sex_of_babyProperty().set(sex_of_baby);
    }

    public String getWeight() {
        return weight.get();
    }

    public StringProperty weightProperty() {
        if(weight==null) weight=new SimpleStringProperty(this, "weight");
        return weight;
    }

    public void setWeight(String weight) {
        weightProperty().set(weight);
    }

    public String getDate_of_delivery() {
        return date_of_delivery.get();
    }

    public StringProperty date_of_deliveryProperty() {
        if(date_of_delivery==null) date_of_delivery=new SimpleStringProperty(this, "date_of_deliery");
        return date_of_delivery;
    }

    public void setDate_of_delivery(String date_of_delivery) {
        date_of_deliveryProperty().set(date_of_delivery);
    }

    public String getSign() {
        return sign.get();
    }

    public StringProperty signProperty() {
        if(sign==null) sign=new SimpleStringProperty(this, "sign");
        return sign;
    }

    public void setSign(String sign) {
        signProperty().set(sign);
    }

    public String getPerm_address() {
        return perm_address.get();
    }

    public StringProperty perm_addressProperty() {
        if(perm_address==null) perm_address=new SimpleStringProperty(this, "perm_address");
        return perm_address;
    }

    public void setPerm_address(String perm_address) {
        perm_addressProperty().set(perm_address);
    }




}
