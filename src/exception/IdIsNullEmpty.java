package exception;

public class IdIsNullEmpty extends Exception {

    public IdIsNullEmpty(){}

    public IdIsNullEmpty(String message){
        super(message);
    }

    public IdIsNullEmpty(Throwable cause){
        super(cause);
    }

    public IdIsNullEmpty(Throwable cause, String message){
        super(message, cause);
    }
}
