package sample;

import java.util.InputMismatchException;

/**
 * Created by Ese on 7/2/2016.
 */
public class InputException extends InputMismatchException {
    String message;
    String cause;
    public InputException(String message)
    {
        super(message);
        this.message=message;


    }

    public String getMessage(String message)
    {
        return message;
    }

}
