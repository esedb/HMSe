package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ese on 4/12/2017.
 */
public class ReportDataListBeans {

    SimpleStringProperty id_sn=new SimpleStringProperty();
    SimpleStringProperty dataname=new SimpleStringProperty();
    SimpleStringProperty datavalue=new SimpleStringProperty();
    SimpleStringProperty data=new SimpleStringProperty();
    SimpleStringProperty date=new SimpleStringProperty();

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }



    public String getId_sn() {
        return id_sn.get();
    }

    public SimpleStringProperty id_snProperty() {
        return id_sn;
    }

    public void setId_sn(String id_sn) {
        this.id_sn.set(id_sn);
    }

    public String getDataname() {
        return dataname.get();
    }

    public SimpleStringProperty datanameProperty() {
        return dataname;
    }

    public void setDataname(String dataname) {
        this.dataname.set(dataname);
    }

    public String getDatavalue() {
        return datavalue.get();
    }

    public SimpleStringProperty datavalueProperty() {
        return datavalue;
    }

    public void setDatavalue(String datavalue) {
        this.datavalue.set(datavalue);
    }

    public String getData() {
        return data.get();
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }


}
