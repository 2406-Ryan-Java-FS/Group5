package com.revature.exceptions;


import com.revature.exceptions.ApiErrorResponse;
import com.revature.exceptions.profileexceptions.IncompleteProfileDetailsException;
import com.revature.exceptions.profileexceptions.InvalidProfileDetailsException;
import com.revature.exceptions.profileexceptions.UserProfileAlreadyExistsException;
import com.revature.exceptions.profileexceptions.UserProfileDoesNotExistException;
import com.revature.exceptions.userexceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IncompleteProfileDetailsException.class)
    public ResponseEntity<String> handleIncompleteProfileDetailsException(IncompleteProfileDetailsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidProfileDetailsException.class)
    public ResponseEntity<String> handleInvalidProfileDetailsException(InvalidProfileDetailsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(UserProfileAlreadyExistsException.class)
    public ResponseEntity<String> handleUserProfileAlreadyExistsException(UserProfileAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(UserProfileDoesNotExistException.class)
    public ResponseEntity<String> handleUserProfileDoesNotExistException(UserProfileDoesNotExistException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex) {
        ApiErrorResponse error = new ApiErrorResponse("INTERNAL_SERVER_ERROR", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidUserCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidUserCredentialsException(InvalidUserCredentialsException ex) {
        ApiErrorResponse error = new ApiErrorResponse("INVALID_USER_CREDENTIALS", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UserNotAuthorizedException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotAuthorizedException(UserNotAuthorizedException ex) {
        ApiErrorResponse error = new ApiErrorResponse("USER_NOT_AUTHORIZED", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotFoundException(Exception ex) {
        ApiErrorResponse error = new ApiErrorResponse("USER_NOT_FOUND", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleUsernameAlreadyExists(Exception ex) {
        ApiErrorResponse error = new ApiErrorResponse("CONFLICT", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserCreationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handleUserCreationFailedException(Exception ex) {
        ApiErrorResponse error = new ApiErrorResponse("INTERNAL_SERVER_ERROR", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserUpdateFailedException.class)
    public ResponseEntity<ApiErrorResponse> handleUserUpdateFailedException(Exception ex) {
        ApiErrorResponse error = new ApiErrorResponse("INTERNAL_SERVER_ERROR", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserDeletionFailedException.class)
    public ResponseEntity<ApiErrorResponse> handleUserDeletionFailedException(Exception ex) {
        ApiErrorResponse error = new ApiErrorResponse("INTERNAL_SERVER_ERROR", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserLoginAttemptFailedException.class)
    public ResponseEntity<ApiErrorResponse> handleUserLoginAttemptFailedException(Exception ex) {
        ApiErrorResponse error = new ApiErrorResponse("INTERNAL_SERVER_ERROR", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
