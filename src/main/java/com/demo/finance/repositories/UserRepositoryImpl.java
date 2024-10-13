package com.demo.finance.repositories;

import com.demo.finance.exceptions.FinancesAuthException;
import com.demo.finance.models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_CREATE_USER =
            "INSERT INTO users (user_id, first_name, last_name, email, password) " +
            "VALUES (NEXTVAL('user_seq'), ?, ?, ?, ?)";

    private static final String SQL_COUNT_BY_EMAIL =
            "SELECT COUNT(*) FROM users WHERE email = ?";

    private static final String SQL_FIND_BY_ID =
            "SELECT user_id, first_name, last_name, email, password FROM users WHERE user_id = ?";

    private static final String SQL_FIND_BY_EMAIL =
            "SELECT user_id, first_name, last_name, email, password FROM users WHERE email = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int create(String first_name, String last_name, String email, String password) throws FinancesAuthException {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, first_name);
                ps.setString(2, last_name);
                ps.setString(3, email);
                ps.setString(4, hashedPassword);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("user_id");
        } catch (Exception e) {
            throw new FinancesAuthException(e.getMessage());
        }
    }

    @Override
    public User findByEmail(String email, String password) throws FinancesAuthException {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email}, userRowMapper);
            if (!BCrypt.checkpw(password, user.getPassword()))
                throw new FinancesAuthException("Wrong password");
            return user;
        } catch (EmptyResultDataAccessException e) {
            throw new FinancesAuthException(e.getMessage());
        }
    }

    @Override
    public int getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email}, Integer.class);
    }

    @Override
    public User getById(int id) {
        return  jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id}, userRowMapper);
    }

    private final RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(rs.getInt("user_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("password"));
    });
}
