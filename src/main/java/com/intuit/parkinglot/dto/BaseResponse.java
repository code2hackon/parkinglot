package com.intuit.parkinglot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.intuit.parkinglot.constant.Constants;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse implements Serializable {

    private Integer statusCode;

    private String messageDescription;

    private String errorCode;

    public BaseResponse(){
        this.messageDescription = Constants.SUCCESS_MESSAGE;
        this.statusCode = Constants.SUCCESS;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessageDescription() {
        return messageDescription;
    }

    public void setMessageDescription(String messageDescription) {
        this.messageDescription = messageDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
