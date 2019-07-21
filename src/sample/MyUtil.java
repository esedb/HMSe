package sample;

/**
 * Created by Ese on 4/3/2017.
 */
public class MyUtil {

    public static boolean isInteger(String i){
        if(i.startsWith("0") || i.equals(" ") ||i.endsWith(" ") || i.endsWith("d") || i.endsWith("f")){
            return false;
        }

        try{

            double no=Double.valueOf(i);
            if(no==0 || no> 0){
                return true;
            }
        }
        catch(Exception ex){
            return false;
        }
        return false;
    }
}
