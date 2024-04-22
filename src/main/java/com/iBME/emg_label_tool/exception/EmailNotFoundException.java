package com.iBME.emg_label_tool.exception;

public class EmailNotFoundException extends  RuntimeException {
    public  EmailNotFoundException(String message) {
        super(message);
    }
}
