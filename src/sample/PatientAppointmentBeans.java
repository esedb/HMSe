package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ese on 2/1/2016.
 */
public class PatientAppointmentBeans {


    SimpleStringProperty id=new SimpleStringProperty();
    SimpleStringProperty name = new SimpleStringProperty();
    SimpleStringProperty date = new SimpleStringProperty();
    SimpleStringProperty time = new SimpleStringProperty();
    SimpleStringProperty purpose = new SimpleStringProperty();
    SimpleStringProperty schedule_by = new SimpleStringProperty();
    SimpleStringProperty status = new SimpleStringProperty();



    SimpleStringProperty details=new SimpleStringProperty();

    PatientAppointmentBeans(String id,String name, String date, String time, String purpose, String schedule_by, String status, String details)
    {
        setId(id);
        setName(name);
        setDate(date);
        setTime(time);
        setPurpose(purpose);
        setSchedule_by(schedule_by);
        setStatus(status);
        setDetails(details);

    }
    public String getDetails() {
        return details.get();
    }

    public SimpleStringProperty detailsProperty() {
        return details;
    }

    public void setDetails(String details) {
        this.details.set(details);
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

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
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

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
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

    public String getSchedule_by() {
        return schedule_by.get();
    }

    public SimpleStringProperty schedule_byProperty() {
        return schedule_by;
    }

    public void setSchedule_by(String schedule_by) {
        this.schedule_by.set(schedule_by);
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


}
