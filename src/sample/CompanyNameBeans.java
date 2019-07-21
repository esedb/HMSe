package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Ese on 1/11/2016.
 */
public class CompanyNameBeans {
    StringProperty companyname;
    public CompanyNameBeans(String companyname)
    {
        setCompanyname(companyname);
    }
    public String getCompanyname() {
        return companynameProperty().get();
    }

    public StringProperty companynameProperty() {
        if(companyname==null) companyname=new SimpleStringProperty(this, "companyname");
        return companyname;
    }

    public void setCompanyname(String companyname) {
        companynameProperty().set(companyname);
    }


}
