package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Ese on 1/14/2016.
 */
public class AddLabTestBeans {
    SimpleStringProperty testtype=new SimpleStringProperty();
    SimpleStringProperty testname=new SimpleStringProperty();
    SimpleStringProperty normalvalue=new SimpleStringProperty();
    SimpleLongProperty amount=new SimpleLongProperty();

    public int getAmount2() {
        return amount2.get();
    }

    public SimpleIntegerProperty amount2Property() {
        return amount2;
    }

    public void setAmount2(int amount2) {
        this.amount2.set(amount2);
    }

    SimpleIntegerProperty amount2=new SimpleIntegerProperty();

    public String getTestname() {
        return testname.get();
    }

    public SimpleStringProperty testnameProperty() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname.set(testname);
    }

    public String getTesttype() {

        return testtype.get();
    }

    public SimpleStringProperty testtypeProperty() {
        return testtype;
    }

    public void setTesttype(String testtype) {
        this.testtype.set(testtype);
    }

    public String getNormalvalue() {
        return normalvalue.get();
    }

    public SimpleStringProperty normalvalueProperty() {
        return normalvalue;
    }

    public void setNormalvalue(String normalvalue) {

        this.normalvalue.set(normalvalue);
    }

    public long getAmount() {
        return amount.get();
    }

    public SimpleLongProperty amountProperty() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount.set(amount);
    }


    public AddLabTestBeans(String testtype, String testname, String normalvalue, long amount, int amount2)
    {
        setTesttype(testtype);
        setTestname(testname);
        setNormalvalue(normalvalue);
        setAmount(amount);
        setAmount2(amount2);
    }


}
