package org.catscode.elastic.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class ErrorResponse {

    String httpStatus;
    String messages;

    public ErrorResponse(
            @JsonProperty("httpStatus") String httpStatus,
            @JsonProperty("message") String messages) {
        this.httpStatus = httpStatus;
        this.messages = messages;
    }
}
