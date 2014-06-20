package com.interzonedev.pretendpoint.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ErrorResponse extends PretendPointResponse {

    private Map<String, String> validationErrorMessages;

    private String processingErrorMessage;

    public ErrorResponse(Map<String, String> validationErrorMessages) {
        this.error = true;
        this.validationErrorMessages = validationErrorMessages;
        this.processingErrorMessage = null;
    }

    public ErrorResponse(String processingErrorMessage) {
        this.error = true;
        this.processingErrorMessage = processingErrorMessage;
        this.validationErrorMessages = new HashMap<String, String>();
    }

    public Map<String, String> getValidationErrorMessages() {
        return Collections.unmodifiableMap(validationErrorMessages);
    }

    public String getProcessingErrorMessage() {
        return processingErrorMessage;
    }

}
