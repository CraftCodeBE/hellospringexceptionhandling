package be.contribute.helloworld.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static be.contribute.helloworld.service.MessageBusinessException.ID_ALREADY_EXISTS;
import static be.contribute.helloworld.service.MessageBusinessException.MESSAGE_NOT_FOUND;

@Component
public class ApiErrorDictionary {
    private final Map<String, HttpStatus> apiErrorMap = new HashMap<>();

    private ApiErrorDictionary() {
        fillMap();
    }

    private void fillMap() {
        apiErrorMap.put(ID_ALREADY_EXISTS, HttpStatus.CONFLICT);
        apiErrorMap.put(MESSAGE_NOT_FOUND, HttpStatus.CONFLICT);
    }

    public Map<String, HttpStatus> getApiErrorMap() {
        return Collections.unmodifiableMap(apiErrorMap);
    }
}
