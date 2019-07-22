package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Ese on 3/22/2017.
 */
public class TemplatesListBean {
    StringProperty service_name;
    public String getService_name() {
        return service_name.get();
    }

    public StringProperty service_nameProperty() {
        if(service_name==null) service_name=new SimpleStringProperty(this, "service_name");
        return service_name;
    }

    public void setService_name(String service_name) {
        service_nameProperty().set(service_name);
    }

}
