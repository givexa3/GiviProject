package com.example.giviproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException userNotFoundException)
    {
        UserException userException = new UserException(
                userNotFoundException.getMessage(),
                userNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        //alternative also search what is the advantage and disadvantage of using Builder Pattern
        //also write builder pattern from scratch by your own
//        UserException userException1 = UserException
//                .builder()
//                .message(userNotFoundException.getMessage())
//                .throwable(userNotFoundException.getCause())
//                .httpStatus(HttpStatus.NOT_FOUND)
//                .build();

        return new ResponseEntity<>(userException, HttpStatus.NOT_FOUND);
    }
}
