package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ese on 2/9/2016.
 */
public class PplistBeans {
    SimpleStringProperty name=new SimpleStringProperty();
    SimpleStringProperty id=new SimpleStringProperty();



    public PplistBeans(String name, String id)
    {
        setName(name);
        setId(id);
    }
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }


}
