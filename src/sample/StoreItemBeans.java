package sample;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ese on 5/14/2017.
 */
public class StoreItemBeans {
    SimpleStringProperty itemtype=new SimpleStringProperty();
    SimpleLongProperty quantity=new SimpleLongProperty();
    SimpleStringProperty date=new SimpleStringProperty();
    SimpleStringProperty receiveby=new SimpleStringProperty();

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getItemtype() {
        return itemtype.get();
    }

    public SimpleStringProperty itemtypeProperty() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype.set(itemtype);
    }

    public long getQuantity() {
        return quantity.get();
    }

    public SimpleLongProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity.set(quantity);
    }

    public String getReceiveby() {
        return receiveby.get();
    }

    public SimpleStringProperty receivebyProperty() {
        return receiveby;
    }

    public void setReceiveby(String receiveby) {
        this.receiveby.set(receiveby);
    }





}
