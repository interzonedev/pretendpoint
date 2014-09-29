package com.interzonedev.pretendpoint.web.echo;

import org.springframework.http.HttpStatus;

public class EchoForm {

    private int httpStatusValue = HttpStatus.OK.value();

    private String httpLocation;

    public int getHttpStatusValue() {
        return httpStatusValue;
    }

    public void setHttpStatusValue(int httpStatusValue) {
        this.httpStatusValue = httpStatusValue;
    }

    public String getHttpLocation() {
        return httpLocation;
    }

    public void setHttpLocation(String httpLocation) {
        this.httpLocation = httpLocation;
    }

}
