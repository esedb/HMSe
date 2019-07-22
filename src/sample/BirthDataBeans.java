package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ese on 7/7/2016.
 */
public class BirthDataBeans {

    SimpleStringProperty fathers_tribe;
    SimpleStringProperty placeofbirth;
    SimpleIntegerProperty weight;
    SimpleStringProperty  signby;
    SimpleStringProperty our_ref;
    SimpleStringProperty dateofdelivery;
    SimpleStringProperty nameofmother;
    SimpleStringProperty perm_address;
    SimpleStringProperty occu_father;
    SimpleStringProperty fathers_name;
    SimpleStringProperty mothers_tribe;
    SimpleStringProperty lga;
    SimpleStringProperty time_of_delivery;


    SimpleStringProperty baby_id;

    public BirthDataBeans(String baby_id, String fathers_tribe, String placeofbirth,
                          int weight, String signby, String our_ref,
                          String dateofdelivery, String nameofmother,
                          String perm_address, String occu_father,
                          String fathers_name, String mothers_tribe,
                          String lga, String time_of_delivery) {
        setFathers_tribe(fathers_tribe);
        setPlaceofbirth(placeofbirth);
        setWeight(weight);
        setSignby(signby);
        setOur_ref(our_ref);
        setDateofdelivery(dateofdelivery);
        setNameofmother(nameofmother);
        setPerm_address(perm_address);
        setOccu_father(occu_father);
        setFathers_name(fathers_name);
        setMothers_tribe(mothers_tribe);
        setLga(lga);
        setTime_of_delivery(time_of_delivery);
        setBaby_id(baby_id);

    }

    public String getPerm_address() {
        return perm_address.get();
    }

    public SimpleStringProperty perm_addressProperty() {
        if(perm_address==null) perm_address=new SimpleStringProperty(this, "perm_address");
        return perm_address;
    }

    public void setPerm_address(String perm_address) {
        perm_addressProperty().set(perm_address);
    }

    public String getFathers_tribe() {
        return fathers_tribe.get();
    }

    public SimpleStringProperty fathers_tribeProperty() {
        if(fathers_tribe==null) fathers_tribe=new SimpleStringProperty(this, "fathers_tribe");
        return fathers_tribe;
    }

    public void setFathers_tribe(String fathers_tribe) {
        fathers_tribeProperty().set(fathers_tribe);
    }

    public String getPlaceofbirth() {
        return placeofbirth.get();
    }

    public SimpleStringProperty placeofbirthProperty() {
        if(placeofbirth==null) placeofbirth=new SimpleStringProperty(this, "placeofbirth");
        return placeofbirth;
    }

    public void setPlaceofbirth(String placeofbirth) {
        placeofbirthProperty().set(placeofbirth);
        this.placeofbirth.set(placeofbirth);
    }

    public int getWeight() {
        return weight.get();
    }

    public SimpleIntegerProperty weightProperty() {
        if(weight==null) weight=new SimpleIntegerProperty(this, "weight");
        return weight;
    }

    public void setWeight(int weight) {
        weightProperty().set(weight);
    }

    public String getSignby() {
        return signby.get();
    }

    public SimpleStringProperty signbyProperty() {
        if(signby==null) signby=new SimpleStringProperty(this, "signby");
        return signby;
    }

    public void setSignby(String signby) {
        signbyProperty().set(signby);
    }

    public String getOur_ref() {
        return our_ref.get();
    }

    public SimpleStringProperty our_refProperty() {
        if(our_ref==null) our_ref=new SimpleStringProperty(this, "our_ref");
        return our_ref;
    }

    public void setOur_ref(String our_ref) {
        our_refProperty().set(our_ref);
    }

    public String getDateofdelivery() {
        return dateofdelivery.get();
    }

    public SimpleStringProperty dateofdeliveryProperty() {
        if(dateofdelivery==null) dateofdelivery=new SimpleStringProperty(this, "dateofdelivery");
        return dateofdelivery;
    }

    public void setDateofdelivery(String dateofdelivery) {
        dateofdeliveryProperty().set(dateofdelivery);
    }

    public String getNameofmother() {
        return nameofmother.get();
    }

    public SimpleStringProperty nameofmotherProperty() {
        if(nameofmother==null) nameofmother=new SimpleStringProperty(this, "nameofmother");
        return nameofmother;
    }

    public void setNameofmother(String nameofmother) {
        nameofmotherProperty().set(nameofmother);
    }

    public String getOccu_father() {
        return occu_father.get();
    }

    public SimpleStringProperty occu_fatherProperty() {
        if(occu_father==null) occu_father=new SimpleStringProperty(this, "occu_fathers");
        return occu_father;
    }

    public void setOccu_father(String occu_father) {
        occu_fatherProperty().set(occu_father);
    }

    public String getFathers_name() {
        return fathers_name.get();
    }

    public SimpleStringProperty fathers_nameProperty() {
        if(fathers_name==null) fathers_name=new SimpleStringProperty(this, "fathers_name");
        return fathers_name;
    }

    public void setFathers_name(String fathers_name) {
        fathers_nameProperty().set(fathers_name);
    }

    public String getMothers_tribe() {
        return mothers_tribe.get();
    }

    public SimpleStringProperty mothers_tribeProperty() {
        if(mothers_tribe==null) mothers_tribe=new SimpleStringProperty(this, "mothers_tribe");
        return mothers_tribe;
    }

    public void setMothers_tribe(String mothers_tribe) {
        mothers_tribeProperty().set(mothers_tribe);

    }

    public String getLga() {
        return lga.get();
    }

    public SimpleStringProperty lgaProperty() {
        if(lga==null) lga=new SimpleStringProperty(this, "lga");
        return lga;
    }

    public void setLga(String lga) {
        lgaProperty().set(lga);
    }

    public String getTime_of_delivery() {
        return time_of_delivery.get();
    }

    public SimpleStringProperty time_of_deliveryProperty() {
        if(time_of_delivery==null) time_of_delivery=new SimpleStringProperty(this, "time_of_delivery");
        return time_of_delivery;
    }

    public void setTime_of_delivery(String time_of_delivery) {
        time_of_deliveryProperty().set(time_of_delivery);
    }

    public String getBaby_id() {
        return baby_id.get();
    }

    public SimpleStringProperty baby_idProperty() {
        if(baby_id==null) baby_id=new SimpleStringProperty(this, "baby_id");
        return baby_id;
    }

    public void setBaby_id(String baby_id) {
        baby_idProperty().set(baby_id);
    }



}
