package com.example.giviproject.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

//search why lombok is using bad and can we override lombok?
@Builder
@Getter
@AllArgsConstructor
public class UserException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
}
