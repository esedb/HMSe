package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Ese on 1/12/2016.
 */
public class AddStoreItemsBeans {
    StringProperty date;

    StringProperty store;
    StringProperty supplier;

    StringProperty receivedby;
    StringProperty itemsupplied;
    IntegerProperty total;
    IntegerProperty rate;
    IntegerProperty receiptno;
    IntegerProperty quantity;

    public AddStoreItemsBeans(String date, String store, String supplier, String receivedby,String itemsupplied, int total, int rate, int receiptno, int quantity)
    {
        setDate(date);
        setStore(store);
        setSupplier(supplier);
        setReceivedby(receivedby);
        setTotal(total);
        setRate(rate);
        setReceiptno(receiptno);
        setQuantity(quantity);
        setItemsupplied(itemsupplied);
    }


    public String getItemsupplied() {
        return itemsuppliedProperty().get();
    }

    public StringProperty itemsuppliedProperty() {

        if(itemsupplied==null) itemsupplied =new SimpleStringProperty(this, "itemsupplied");
        return itemsupplied;
    }

    public void setItemsupplied(String itemsupplied) {
        itemsuppliedProperty().set(itemsupplied);
    }

    public String getDate() {
        return dateProperty().get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        dateProperty().set(date);
    }

    public String getStore() {
        return storeProperty().get();
    }

    public StringProperty storeProperty() {
        return store;
    }

    public void setStore(String store) {
        storeProperty().set(store);
    }

    public String getSupplier() {
        return supplierProperty().get();
    }

    public StringProperty supplierProperty() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        supplierProperty().set(supplier);
    }

    public String getReceivedby() {
        return receivedbyProperty().get();
    }

    public StringProperty receivedbyProperty() {
        return receivedby;
    }

    public void setReceivedby(String receivedby) {
        receivedbyProperty().set(receivedby);
    }

    public int getTotal() {
        return totalProperty().get();
    }

    public IntegerProperty totalProperty() {
        return total;
    }

    public void setTotal(int total) {
        totalProperty().set(total);
    }

    public int getRate() {
        return rateProperty().get();
    }

    public IntegerProperty rateProperty() {
        return rate;
    }

    public void setRate(int rate) {
        rateProperty().set(rate);
    }

    public int getReceiptno() {
        return receiptnoProperty().get();
    }

    public IntegerProperty receiptnoProperty() {
        return receiptno;
    }

    public void setReceiptno(int receiptno) {
        receiptnoProperty().set(receiptno);
    }

    public int getQuantity() {
        return quantityProperty().get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        quantityProperty().set(quantity);
    }



}
