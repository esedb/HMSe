package sample;

/**
 * Created by Ese on 2/11/2016.
 */
public class UAntenatalBeans {
    Long patientid;
    String examd;
    String weight;
    String dedema;
    String presentation;
    String gaweeks;
    String bpm;
    String sfh;
    String attitude;
    String ultrasound;
    String tmp;
    String bp;
    String lie;
    String foetalheart;
    String clinicalinfo;
    String signature;

    public UAntenatalBeans(Long patientid, String examd, String weight, String dedema, String presentation,
                           String gaweeks, String bpm, String sfh, String attitude, String ultrasound, String tmp,
                           String bp, String lie, String foetalheart, String clinicalinfo, String signature)
    {
        this.patientid = patientid;
        this.examd = examd;
        this.weight = weight;
        this.dedema = dedema;
        this.presentation = presentation;
        this.gaweeks = gaweeks;
        this.bpm = bpm;
        this.sfh = sfh;
        this.attitude = attitude;
        this.ultrasound = ultrasound;
        this.tmp = tmp;
        this.bp = bp;
        this.lie = lie;
        this.foetalheart = foetalheart;
        this.clinicalinfo = clinicalinfo;
        this.signature = signature;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public Long getPatientid() {
        return patientid;
    }

    public void setPatientid(Long patientid) {
        this.patientid = patientid;
    }

    public String getExamd() {
        return examd;
    }

    public void setExamd(String examd) {
        this.examd = examd;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDedema() {
        return dedema;
    }

    public void setDedema(String dedema) {
        this.dedema = dedema;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getGaweeks() {
        return gaweeks;
    }

    public void setGaweeks(String gaweeks) {
        this.gaweeks = gaweeks;
    }

    public String getSfh() {
        return sfh;
    }

    public void setSfh(String sfh) {
        this.sfh = sfh;
    }

    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }

    public String getUltrasound() {
        return ultrasound;
    }

    public void setUltrasound(String ultrasound) {
        this.ultrasound = ultrasound;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getLie() {
        return lie;
    }

    public void setLie(String lie) {
        this.lie = lie;
    }

    public String getFoetalheart() {
        return foetalheart;
    }

    public void setFoetalheart(String foetalheart) {
        this.foetalheart = foetalheart;
    }

    public String getClinicalinfo() {
        return clinicalinfo;
    }

    public void setClinicalinfo(String clinicalinfo) {
        this.clinicalinfo = clinicalinfo;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }


}
