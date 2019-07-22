package sample;

import java.io.*;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Bruno on 7/14/2017.
 */
public class DateTracker implements Serializable {
    int former_date;
    int former_month;
    int former_year;

    int current_date;
    int current_month;
    int current_year;

    int day_spent;

    public int getFormer_date() {
        return former_date;
    }

    public void setFormer_date(int former_date) {
        this.former_date = former_date;
    }

    public int getFormer_month() {
        return former_month;
    }

    public void setFormer_month(int former_month) {
        this.former_month = former_month;
    }

    public int getFormer_year() {
        return former_year;
    }

    public void setFormer_year(int former_year) {
        this.former_year = former_year;
    }

    public int getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(int current_date) {
        this.current_date = current_date;
    }

    public int getCurrent_month() {
        return current_month;
    }

    public void setCurrent_month(int current_month) {
        this.current_month = current_month;
    }

    public int getCurrent_year() {
        return current_year;
    }

    public void setCurrent_year(int current_year) {
        this.current_year = current_year;
    }

    void increment(){
        if(current_date!=former_date || former_year!=current_year || current_month!=current_month){
            day_spent++;
            System.out.println("day spent is " + day_spent);

        }
    }
    void incrementFirst(){
        day_spent++;
    }
    int getDay_spent(){
        return day_spent;
    }





}