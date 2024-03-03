package com.example.giviproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//we can call this also UserExceptionController instead of UserExceptionHandler
//check also @RestControllerAdvice what is it?
//not it works lets write down topics which we need to study for next

//1. first of all instead of just single line comments learn multi line comment with /* and */
//2. learn best string format when writing exceptions instead of just concat
//3. learn more about best coding practices with @ControllerAdvice @ExceptionHandler,
//also i dont like the fact we have UserException and UserNotFoundException kinda looks strange should we have something
//different? just search other cases of exceptions
//4. search about ResonseEntity exactly what is it. why are we writing ResponseEntity<Object> lets see different excamples
//with working with response entity and writing different logic of response entity when content is found or not found
//writing different status codes
//5. search about @RestControllerAdvice what is it exactly?
//6. search for @ConditionOnProperty and do examples ast JAD JIPIDI about it. it is used to run logic based on different
//conceepts and also search alternative case of not using @ConditionalOnPRoperty and doing it by bean xml or something like
//in my project
//7.make a guidline of everything of steps how to handle exceptions, Rest endpoints, test, Repository and etc.
//8. write tests also Unit tests and Integration Tests, Search of UI testing is it really the case that
//UI testing means just testing everything with UI by hand like A said? anyway search it!

//p.s write more optimized code as possible! for example below can we use builder instead of constuctor? to reduce
//readability and lines? and do this for all cases everywhere write most efficent code!
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

        return new ResponseEntity<>(userException, HttpStatus.NOT_FOUND);
    }
}
