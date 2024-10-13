package com.demo.finance.services;

import com.demo.finance.models.User;
import com.demo.finance.exceptions.FinancesAuthException;

public interface UserService {
    User ValidateUser(String email, String password) throws FinancesAuthException;
    User RegisterUser(String first_name, String last_name, String email, String password) throws FinancesAuthException;
}
