package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ese on 1/26/2016.
 */
public class PatientScheduleBeans {

    SimpleStringProperty date=new SimpleStringProperty();
    SimpleStringProperty time=new SimpleStringProperty();
    SimpleStringProperty purpose=new SimpleStringProperty();
    SimpleStringProperty detials=new SimpleStringProperty();
    SimpleStringProperty scheduleby=new SimpleStringProperty();
    SimpleStringProperty status=new SimpleStringProperty();
    SimpleStringProperty id=new SimpleStringProperty();

    PatientScheduleBeans(String id,String date, String time, String purpose, String details, String scheduleby, String status)
    {
        setDate(date);
        setTime(time);
        setPurpose(purpose);
        setDetials(details);
        setScheduleby(scheduleby);
        setStatus(status);
        setId(id);
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

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }




    public String getPurpose() {
        return purpose.get();
    }

    public SimpleStringProperty purposeProperty() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose.set(purpose);
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

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getDetials() {
        return detials.get();
    }

    public SimpleStringProperty detialsProperty() {
        return detials;
    }

    public void setDetials(String detials) {
        this.detials.set(detials);
    }

    public String getScheduleby() {
        return scheduleby.get();
    }

    public SimpleStringProperty schedulebyProperty() {
        return scheduleby;
    }

    public void setScheduleby(String scheduleby) {
        this.scheduleby.set(scheduleby);
    }


}
