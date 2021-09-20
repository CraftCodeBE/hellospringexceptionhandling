package be.contribute.helloworld.exceptions;

import lombok.*;

/**
 * A helper class to return a consistent error message object to the user of our API.
 */
@Getter
@Builder
@ToString
@AllArgsConstructor
public class ApiError {
    private final String errorMessage;
}
