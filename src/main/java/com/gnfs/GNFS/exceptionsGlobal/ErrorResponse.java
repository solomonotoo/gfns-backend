
package com.gnfs.GNFS.exceptionsGlobal;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private final Instant timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    // Validation errors (multiple per field)
    private final Map<String, List<String>> fieldErrors;

    // Global errors (rare, optional)
    private final List<String> errors;

    private ErrorResponse(
            int status,
            String error,
            String message,
            String path,
            Map<String, List<String>> fieldErrors,
            List<String> errors) {

        this.timestamp = Instant.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.fieldErrors = fieldErrors;
        this.errors = errors;
    }

    /* ---------- Factory methods ---------- */

    // Generic / business errors
    public static ErrorResponse of(
            HttpStatus status,
            String message,
            String path) {

        return new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                path,
                null,
                null
        );
    }


    // Validation errors
    public static ErrorResponse validation(
            String path,
            Map<String, List<String>> fieldErrors) {

        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation failed",
                path,
                fieldErrors,
                null
        );
    }

    // Internal / unexpected errors
    public static ErrorResponse internal(
            String path,
            List<String> errors) {

        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Unexpected error occurred",
                path,
                null,
                errors
        );
    }
}
