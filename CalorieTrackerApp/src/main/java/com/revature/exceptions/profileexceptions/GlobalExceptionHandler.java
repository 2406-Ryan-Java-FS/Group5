//package com.revature.exceptions.profileexceptions;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(IncompleteProfileDetailsException.class)
//    public ResponseEntity<String> handleIncompleteProfileDetailsException(IncompleteProfileDetailsException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
//
//    @ExceptionHandler(InvalidProfileDetailsException.class)
//    public ResponseEntity<String> handleInvalidProfileDetailsException(InvalidProfileDetailsException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
//
//    @ExceptionHandler(UserProfileAlreadyExistsException.class)
//    public ResponseEntity<String> handleUserProfileAlreadyExistsException(UserProfileAlreadyExistsException e) {
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//    }
//
//    @ExceptionHandler(UserProfileDoesNotExistException.class)
//    public ResponseEntity<String> handleUserProfileDoesNotExistException(UserProfileDoesNotExistException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//    }
//
//}
