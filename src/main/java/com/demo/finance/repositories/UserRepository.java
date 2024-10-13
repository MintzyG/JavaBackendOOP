package com.demo.finance.repositories;

import com.demo.finance.exceptions.FinancesAuthException;
import com.demo.finance.models.User;

public interface UserRepository {
    int create(String first_name, String last_name, String email, String password) throws FinancesAuthException;
    User findByEmail(String email, String password) throws FinancesAuthException;
    int getCountByEmail(String email);
    User getById(int id);
}
