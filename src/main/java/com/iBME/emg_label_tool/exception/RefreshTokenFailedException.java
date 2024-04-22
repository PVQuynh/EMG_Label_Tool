package com.iBME.emg_label_tool.exception;

public class RefreshTokenFailedException extends  RuntimeException {
    public  RefreshTokenFailedException(String message) {
        super(message);
    }
}
