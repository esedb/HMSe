package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ese on 4/23/2017.
 */
public class ViewPharmacyBillBean {
    SimpleStringProperty drugname=new SimpleStringProperty();
    SimpleDoubleProperty quantity=new SimpleDoubleProperty();
    SimpleDoubleProperty cost=new SimpleDoubleProperty();
    SimpleStringProperty date=new SimpleStringProperty();
    SimpleDoubleProperty totalcost=new SimpleDoubleProperty();

    public double getTotalcost() {
        return totalcost.get();
    }

    public SimpleDoubleProperty totalcostProperty() {
        return totalcost;
    }

    public void setTotalcost(double totalcost) {
        this.totalcost.set(totalcost);
    }



    public double getQuantity() {
        return quantity.get();
    }

    public SimpleDoubleProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity.set(quantity);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public double getCost() {
        return cost.get();
    }

    public SimpleDoubleProperty costProperty() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost.set(cost);
    }

    public String getDrugname() {
        return drugname.get();
    }

    public SimpleStringProperty drugnameProperty() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname.set(drugname);
    }


}
