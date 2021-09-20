package be.contribute.helloworld.exceptions;

import be.contribute.helloworld.service.MessageBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    private final ApiErrorDictionary apiErrorDictionary;

    @Autowired
    public RestResponseEntityExceptionHandler(ApiErrorDictionary apiErrorDictionary) {
        this.apiErrorDictionary = apiErrorDictionary;
    }

    @ExceptionHandler(value = {MessageBusinessException.class})
    protected ResponseEntity<ApiError> handleMessageBusinessException(MessageBusinessException ex) {
        return createErrorResponse(ex);
    }

    private ResponseEntity<ApiError> createErrorResponse(Exception ex) {
        String errorMessage = ex.getMessage() == null ? "Unknown error." : ex.getMessage();
        ApiError apiError = ApiError.builder().errorMessage(errorMessage).build();

        if (ex.getMessage() == null) {
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }

        HttpStatus status = apiErrorDictionary.getApiErrorMap().get(ex.getMessage());

        return new ResponseEntity<>(apiError, status);
    }
}
