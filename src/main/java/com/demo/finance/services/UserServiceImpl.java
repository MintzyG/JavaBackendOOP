package com.demo.finance.services;

import com.demo.finance.exceptions.FinancesAuthException;
import com.demo.finance.models.User;
import com.demo.finance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User ValidateUser(String email, String password) throws FinancesAuthException {
        if (email == null) throw new FinancesAuthException("Invalid email");
        email = email.toLowerCase();
        return userRepository.findByEmail(email, password);
    }

    @Override
    public User RegisterUser(String first_name, String last_name, String email, String password) throws FinancesAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (email == null) throw new FinancesAuthException("Invalid email");
        email = email.toLowerCase();
        if (!pattern.matcher(email).matches()) throw new FinancesAuthException("Invalid email");
        int count = userRepository.getCountByEmail(email);
        if (count > 0) throw new FinancesAuthException("Account already exists");
        int user_id = userRepository.create(first_name, last_name, email, password);
        return userRepository.getById(user_id);
    }
}
