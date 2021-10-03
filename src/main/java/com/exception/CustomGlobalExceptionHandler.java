package com.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Advice of AOP for exception handlers
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 */

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomGlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ApiError> handleThereIsNoSuchDataException(ResourceNotFoundException exception) {
        LOGGER.error("Resource not found", exception);

        return new ResponseEntity<>(ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND)
                .message("Resource Not Found")
                .errors(Stream.of(exception.getMessage()).collect(Collectors.toList()))
                .build(), HttpStatus.NOT_FOUND);
    }

    private static class ApiError {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        private LocalDateTime timestamp;
        private HttpStatus status;
        private String message;
        private List errors;

        public ApiError() {
        }

        public ApiError(LocalDateTime timestamp, HttpStatus status, String message, List errors) {
            this.timestamp = timestamp;
            this.status = status;
            this.message = message;
            this.errors = errors;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public void setStatus(HttpStatus status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List getErrors() {
            return errors;
        }

        public void setErrors(List errors) {
            this.errors = errors;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ApiError apiError = (ApiError) o;
            return Objects.equals(timestamp, apiError.timestamp) && status == apiError.status && Objects.equals(message, apiError.message) && Objects.equals(errors, apiError.errors);
        }

        @Override
        public int hashCode() {
            return Objects.hash(timestamp, status, message, errors);
        }

        public static ApiErrorBuilder builder() {
            return new ApiError().new ApiErrorBuilder();
        }

        private class ApiErrorBuilder {

            private ApiErrorBuilder() {
            }

            public ApiErrorBuilder timestamp(LocalDateTime timestamp) {
                ApiError.this.timestamp = timestamp;

                return this;
            }

            public  ApiErrorBuilder status (HttpStatus status) {
                ApiError.this.status = status;

                return this;
            }

            public ApiErrorBuilder message(String message) {
                ApiError.this.message = message;

                return this;
            }

            public ApiErrorBuilder errors (List errors) {
                ApiError.this.errors = errors;

                return this;
            }

            public ApiError build() {
                return ApiError.this;
            }

        }
    }
}
