package com.demo.finance.repositories;

import com.demo.finance.exceptions.FinanceBadRequestException;
import com.demo.finance.exceptions.FinanceResourceNotFoundException;
import com.demo.finance.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private static final String SQL_FIND_BY_ID = "SELECT TRANSACTION_ID, CATEGORY_ID, USER_ID, AMOUNT, NOTE, TRANSACTION_DATE, FROM ET_TRANSACTIONS WHERE USER_ID = ? AND CATEGORY_ID = ? AND TRANSACTION_ID = ?";
    private static final String SQL_CREATE = "INSERT INTO ET_TRANSACTIONS (TRANSACTION_ID, CATEGORY_ID, USER_ID, AMOUNT, NOTE, TRANSACTION_DATE) VALUES('ET_TRANSACTIONS_SEQ'), ?, ?, ?, ?, ?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Transaction> findAll(Integer user_id, Integer category_id) {
        return List.of();
    }

    @Override
    public Transaction findById(Integer user_id, Integer category_id, Integer transaction_id) throws FinanceResourceNotFoundException {
        try{
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{user_id, category_id, transaction_id}, transactionRowMapper);
        }catch(Exception e){
            throw new FinanceResourceNotFoundException("Transaction not found");
        }
    }

    @Override
    public Integer create(Integer user_id, Integer category_id, Double amount, String note, Long transaction_date) throws FinanceBadRequestException {
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, category_id);
                ps.setInt(2, user_id);
                ps.setDouble(3, amount);
                ps.setString(4, note);
                ps.setLong(5, transaction_date);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("TRANSACTION_ID");
        }catch (Exception e) {
            throw new FinanceBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer user_id, Integer category_id, Integer transactionId, Transaction transaction) throws FinanceBadRequestException {

    }

    @Override
    public void removeById(Integer user_id, Integer category_id, Integer transaction_id) throws FinanceResourceNotFoundException {

    }

    private RowMapper<Transaction> transactionRowMapper = ((rs, rowNum) -> {
        return new Transaction(
                rs.getInt("TRANSACTION_ID"),
                rs.getInt("CATEGORY_ID"),
                rs.getDouble("AMOUNT"),
                rs.getString("NOTE"),
                rs.getLong("TRANSACTION_DATE")
        );
    });

}
