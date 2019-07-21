package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Ese on 1/10/2016.
 */
public class DrugDetailsBeans {
    StringProperty drugname;
    StringProperty drugcategory;

    public DrugDetailsBeans(String drugname, String drugcategory)
    {
        setDrugname(drugname);
        setDrugcategory(drugcategory);
    }


    public String getDrugname() {
        return drugnameProperty().get();
    }

    public StringProperty drugnameProperty() {
        if(drugname==null) drugname=new SimpleStringProperty(this, "drugname");
        return drugname;
    }

    public void setDrugname(String drugname) {
        drugnameProperty().set(drugname);
    }

    public String getDrugcategory() {
       return drugcategoryProperty().get();
    }

    public StringProperty drugcategoryProperty() {
        if(drugcategory==null) drugcategory=new SimpleStringProperty(this, "drugcategory");
        return drugcategory;
    }

    public void setDrugcategory(String drugcategory) {
        drugcategoryProperty().set(drugcategory);
    }


}
