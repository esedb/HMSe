package sample;

/**
 * Created by Ese on 7/5/2017.
 */
public class ActivationState {
    private boolean activated=false;
    public void setActivate(boolean activated){
        this.activated=activated;

    }
    public boolean isActivated(){
        if(activated){
            return true;

        }
        return false;
    }

}
