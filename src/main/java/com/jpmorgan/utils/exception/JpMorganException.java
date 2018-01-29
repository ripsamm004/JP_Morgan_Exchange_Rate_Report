package com.jpmorgan.utils.exception;

public class JpMorganException extends Exception {

    protected String beautyMessage;

    public JpMorganException(String logMessage) {
        super(logMessage);
        this.beautyMessage = logMessage;
    }

    public String getBeautyMessage() {
        return beautyMessage;
    }
}
