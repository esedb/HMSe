package sample;

/**
 * Created by Ese on 1/31/2016.
 */
public class ExamDiagBeans {
    String respiration;
    String bp_lic;
    String time;
    String date;
    String pulse;
    String temperature;
    String height;
    String weight;
    String spo2;
    String bgroup;

    public String getComplains() {
        return complains;
    }

    public void setComplains(String complains) {
        this.complains = complains;
    }

    String complains;

    public ExamDiagBeans(String respiration, String bp_lic, String time, String date, String pulse, String temperature, String height,
                         String weight, String spo2, String bgroup, String complains)
    {
        setRespiration(respiration);
        setBp_lic(bp_lic);
        setTime(time);
        setDate(date);
        setPulse(pulse);
        setTemperature(temperature);
        setHeight(height);
        setWeight(weight);
        setSpo2(spo2);
        setBgroup(bgroup);
        setComplains(complains);

    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getRespiration() {
        return respiration;
    }

    public void setRespiration(String respiration) {
        this.respiration = respiration;
    }

    public String getBp_lic() {
        return bp_lic;
    }

    public void setBp_lic(String bp_lic) {
        this.bp_lic = bp_lic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSpo2() {
        return spo2;
    }

    public void setSpo2(String spo2) {
        this.spo2 = spo2;
    }

    public String getBgroup() {
        return bgroup;
    }

    public void setBgroup(String bgroup) {
        this.bgroup = bgroup;
    }


}
