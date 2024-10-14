package com.demo.finance.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FinanceResourceNotFoundException extends RuntimeException {
    public FinanceResourceNotFoundException(String message) {
        super(message);
    }
}
