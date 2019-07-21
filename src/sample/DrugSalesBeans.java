package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Ese on 1/20/2016.
 */
public class DrugSalesBeans {
    StringProperty patientid;
    StringProperty date;
    StringProperty signby;
    IntegerProperty quantitysold;
    IntegerProperty unitcost;
    IntegerProperty sellingcost;
    IntegerProperty totalcost;
    StringProperty drugsold;


    public DrugSalesBeans(String patientid, String date, String signby, int quantitysold, int unitcost, int sellingcost, int totalcost, String drugsold)
    {
        setPatientid(patientid);
        setDate(date);
        setSignby(signby);
        setQuantitysold(quantitysold);
        setUnitcost(unitcost);
        setSellingcost(sellingcost);
        setTotalcost(totalcost);
        setDrugsold(drugsold);
    }
    public int getUnitcost() {
        return unitcostProperty().get();
    }

    public IntegerProperty unitcostProperty() {
        if(unitcost==null) unitcost=new SimpleIntegerProperty(this, "unitcost");
        return unitcost;
    }

    public void setUnitcost(int unitcost) {
        unitcostProperty().set(unitcost);
    }
    public String getDrugsold() {
        return drugsoldProperty().get();
    }

    public StringProperty drugsoldProperty() {
        if(drugsold==null) drugsold=new SimpleStringProperty(this, "drugsold");
        return drugsold;
    }

    public void setDrugsold(String drugsold) {
        drugsoldProperty().set(drugsold);
    }

    public int getQuantitysold() {
        return quantitysoldProperty().get();
    }

    public IntegerProperty quantitysoldProperty() {
        if(quantitysold==null) quantitysold=new SimpleIntegerProperty(this, "quantityleft");
        return quantitysold;
    }

    public void setQuantitysold(int quantitysold) {
        quantitysoldProperty().set(quantitysold);
    }

    public String getPatientid() {
        return patientidProperty().get();
    }

    public StringProperty patientidProperty() {
        if(patientid==null) patientid=new SimpleStringProperty(this, "patientid");
        return patientid;
    }

    public void setPatientid(String patientid) {
        patientidProperty().set(patientid);
    }

    public String getDate() {
        return dateProperty().get();
    }

    public StringProperty dateProperty() {
        if(date==null) date=new SimpleStringProperty(this, "date");
        return date;
    }

    public void setDate(String date) {
        dateProperty().set(date);
    }

    public String getSignby() {
        return signbyProperty().get();
    }

    public StringProperty signbyProperty() {
        if(signby==null) signby=new SimpleStringProperty(this, "signby");
        return signby;
    }

    public void setSignby(String signby) {
        signbyProperty().set(signby);
    }

    public int getSellingcost() {
        return sellingcostProperty().get();
    }

    public IntegerProperty sellingcostProperty() {
        if(sellingcost==null) sellingcost=new SimpleIntegerProperty(this, "sellingcost");
        return sellingcost;
    }

    public void setSellingcost(int sellingcost) {
        sellingcostProperty().set(sellingcost);
    }

    public int getTotalcost() {

        return totalcostProperty().get();
    }

    public IntegerProperty totalcostProperty() {
        if(totalcost==null) totalcost=new SimpleIntegerProperty(this, "totalcost");
        return totalcost;
    }

    public void setTotalcost(int totalcost) {
        totalcostProperty().set(totalcost);
    }
}
