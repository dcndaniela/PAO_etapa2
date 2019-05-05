package main.java.campionat.domain;

public class CampionatException extends RuntimeException{
    private ErrorCode errorCode;

    public CampionatException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getCode() {
        return errorCode;
    }

}
