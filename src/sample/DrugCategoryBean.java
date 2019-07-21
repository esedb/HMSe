package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Ese on 1/11/2016.
 */
public class DrugCategoryBean {
    StringProperty drugcategory;
    public DrugCategoryBean(String drugcategory)
    {
        setDrugcategory(drugcategory);
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
