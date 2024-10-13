package com.demo.finance.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class FinancesAuthException extends RuntimeException {
    public FinancesAuthException(String message) {
        super(message);
    }
}
