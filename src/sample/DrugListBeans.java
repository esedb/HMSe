package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Ese on 1/10/2016.
 */
public class DrugListBeans {
    StringProperty drug;
    IntegerProperty price;
    IntegerProperty quantityleft;
    public DrugListBeans(String drug, int price, int quantityleft)
    {
        setDrug(drug);
        setPrice(price);
        setQuantityleft(quantityleft);

    }

    public int getPrice() {
       return priceProperty().get();
    }

    public IntegerProperty priceProperty() {
        if(price==null) price=new SimpleIntegerProperty(this, "price");
        return price;
    }

    public void setPrice(int price) {
        priceProperty().set(price);
    }

    public String getDrug() {
        return drugProperty().get();
    }

    public StringProperty drugProperty() {
        if(drug==null) drug=new SimpleStringProperty(this, "drug");
        return drug;
    }

    public void setDrug(String drug) {
        drugProperty().set(drug);
    }

    public int getQuantityleft() {
        return quantityleftProperty().get();
    }

    public IntegerProperty quantityleftProperty() {
        if(quantityleft==null) quantityleft=new SimpleIntegerProperty(this, "quantityleft");
        return quantityleft;
    }

    public void setQuantityleft(int quantityleft) {
        quantityleftProperty().set(quantityleft);
    }


}
