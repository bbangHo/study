package apiPayload.exception.handler;


import apiPayload.code.BaseErrorCode;
import apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
