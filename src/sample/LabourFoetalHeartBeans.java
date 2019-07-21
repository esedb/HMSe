package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ese on 2/12/2016.
 */
public class LabourFoetalHeartBeans {
    SimpleStringProperty foetalheartrate=new SimpleStringProperty();
    SimpleStringProperty liquorttime=new SimpleStringProperty();
    SimpleStringProperty date=new SimpleStringProperty();
    SimpleStringProperty argument4=new SimpleStringProperty();

    public LabourFoetalHeartBeans(String foetalheartrate, String liquorttime)
    {
        setFoetalheartrate(foetalheartrate);
        setLiquorttime(liquorttime);
    }
    public LabourFoetalHeartBeans(String foetalheartrate, String liquorttime, String date) {
        setFoetalheartrate(foetalheartrate);
        setLiquorttime(liquorttime);
        setDate(date);
    }
    public LabourFoetalHeartBeans(String foetalheartrate, String liquorttime, String date, String argument4) {
        setFoetalheartrate(foetalheartrate);
        setLiquorttime(liquorttime);
        setDate(date);
        setArgument4(argument4);
    }
    public String getArgument4() {
        return argument4.get();
    }

    public SimpleStringProperty argument4Property() {
        return argument4;
    }

    public void setArgument4(String argument4) {
        this.argument4.set(argument4);
    }

    public String getFoetalheartrate() {
        return foetalheartrate.get();
    }

    public SimpleStringProperty foetalheartrateProperty() {
        return foetalheartrate;
    }

    public void setFoetalheartrate(String foetalheartrate) {
        this.foetalheartrate.set(foetalheartrate);
    }

    public String getLiquorttime() {
        return liquorttime.get();
    }

    public SimpleStringProperty liquorttimeProperty() {
        return liquorttime;
    }

    public void setLiquorttime(String liquorttime) {
        this.liquorttime.set(liquorttime);
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


}
