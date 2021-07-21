package com.aninfo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TaskDoesNotBelong extends RuntimeException {

    public TaskDoesNotBelong(String message) {
        super(message);
    }
}
