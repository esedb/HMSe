package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Ese on 12/11/2015.
 */
public class LabourRecords {


    private boolean successful;
    public int foetalheartrate;
    public String liquortime;
    public String foetaldate;
    public String cervicaldilation;

    public String foetaltime;
    public String caput;
    public String caputdate;
    public int moulding;
    public String caputtime;
    public String ucmins;

    public int getFoetalheartrate() {
        return foetalheartrate;
    }

    public String getLiquortime() {
        return liquortime;
    }

    public String getFoetaldate() {
        return foetaldate;
    }

    public String getCervicaldilation() {
        return cervicaldilation;
    }

    public String getFoetaltime() {
        return foetaltime;
    }

    public String getCaput() {
        return caput;
    }

    public String getCaputdate() {
        return caputdate;
    }

    public int getMoulding() {
        return moulding;
    }

    public String getCaputtime() {
        return caputtime;
    }

    public String getUcmins() {
        return ucmins;
    }

    public String getUcduration() {
        return ucduration;
    }

    public int getOxyunits() {
        return oxyunits;
    }

    public int getDropmins() {
        return dropmins;
    }

    public String getFdfluids() {
        return fdfluids;
    }

    public String getFudrugs() {
        return fudrugs;
    }

    public String getUracet() {
        return uracet;
    }

    public String getUrprot() {
        return urprot;
    }

    public int getUrvol() {
        return urvol;
    }

    public String ucduration;
    public int oxyunits;
    public int dropmins;

    public String fdfluids;
    public String fudrugs;
    public String uracet;
    public String urprot;
    public int urvol;

    public void setFoetaltime(String foetaltime) {
        this.foetaltime = foetaltime;
    }

    public void setFoetalheartrate(int foetalheartrate) {
        this.foetalheartrate = foetalheartrate;
    }

    public void setLiquortime(String liquortime) {
        this.liquortime = liquortime;
    }

    public void setFoetaldate(String foetaldate) {
        this.foetaldate = foetaldate;
    }

    public void setCervicaldilation(String cervicaldilation) {
        this.cervicaldilation = cervicaldilation;
    }

    public void setCaput(String caput) {
        this.caput = caput;
    }

    public void setCaputdate(String caputdate) {
        this.caputdate = caputdate;
    }

    public void setMoulding(int moulding) {
        this.moulding = moulding;
    }

    public void setCaputtime(String caputtime) {
        this.caputtime = caputtime;
    }

    public void setUcmins(String ucmins) {
        this.ucmins = ucmins;
    }

    public void setUcduration(String ucduration) {
        this.ucduration = ucduration;
    }

    public void setOxyunits(int oxyunits) {
        this.oxyunits = oxyunits;
    }

    public void setDropmins(int dropmins) {
        this.dropmins = dropmins;
    }

    public void setFdfluids(String fdfluids) {
        this.fdfluids = fdfluids;
    }

    public void setFudrugs(String fudrugs) {
        this.fudrugs = fudrugs;
    }

    public void setUracet(String uracet) {
        this.uracet = uracet;
    }

    public void setUrprot(String urprot) {
        this.urprot = urprot;
    }

    public void setUrvol(int urvol) {
        this.urvol = urvol;
    }
    public void execute()
    {
        Connection con;
        String sql="insert into labour_records (patient_name, foetalheartrate, liquortime, foetaldate, cervicaldilation, foetaltime, caput, caputdate, moulding, ucmin)" +
                " values(?,?,?,?,?,?,?,?,?,?)";
        DataBase dbase=new DataBase();
        try{
            con=dbase.getConnection();
            PreparedStatement prepstatement=con.prepareStatement(sql);
            int result=prepstatement.executeUpdate();
            if(result==1)
            {
                successful=true;
                setSuccessful(successful);

            }
            else{
                successful=false;
                setSuccessful(successful);
            }



        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }



}