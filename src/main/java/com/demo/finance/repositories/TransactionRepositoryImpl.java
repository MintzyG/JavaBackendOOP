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

    private static final String SQL_FIND_ALL = "SELECT transaction_id, category_id, user_id, amount, note, transaction_date FROM transactions WHERE user_id = ? AND category_id = ?";
    private static final String SQL_FIND_BY_ID = "SELECT transaction_id, category_id, user_id, amount, note, transaction_date FROM transactions WHERE user_id = ? AND category_id = ? AND transaction_id = ?";
    private static final String SQL_CREATE = "INSERT INTO transactions (transaction_id, category_id, user_id, amount, note, transaction_date) VALUES (NEXTVAL('trans_seq'), ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE transactions SET amount = ?, note = ?, transaction_date = ? WHERE user_id = ? AND category_id = ? AND transaction_id = ?";
    private static final String SQL_DELETE = "DELETE FROM transactions WHERE user_id = ? AND category_id = ? AND transaction_id = ?";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Transaction> findAll(Integer user_id, Integer category_id) {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{user_id, category_id}, transactionRowMapper);
    }

    @Override
    public Transaction findById(Integer user_id, Integer category_id, Integer transaction_id) throws FinanceResourceNotFoundException {
        try{
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{user_id, category_id, transaction_id}, transactionRowMapper);
        }catch(Exception e){
            throw new FinanceResourceNotFoundException("Transaction not found" + e.getMessage());
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
            return (Integer) keyHolder.getKeys().get("transaction_id");
        }catch (Exception e) {
            throw new FinanceBadRequestException("Invalid request" + e.getMessage());
        }
    }

    @Override
    public void update(Integer user_id, Integer category_id, Integer transaction_id, Transaction transaction) throws FinanceBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{transaction.getAmount(), transaction.getNote(), transaction.getTransaction_date(), user_id, category_id, transaction_id});
        }catch (Exception e) {
            throw new FinanceBadRequestException("Invalid request" + e.getMessage());

        }
    }

    @Override
    public void removeById(Integer user_id, Integer category_id, Integer transaction_id) throws FinanceResourceNotFoundException {
    int count = jdbcTemplate.update(SQL_DELETE, new Object[]{user_id, category_id, transaction_id});
    if(count == 0)
        throw new FinanceResourceNotFoundException("Transaction not found");
    }

    private RowMapper<Transaction> transactionRowMapper = ((rs, rowNum) -> {
        return new Transaction(
                rs.getInt("transaction_id"),
                rs.getInt("category_id"),
                rs.getInt("user_id"),
                rs.getDouble("amount"),
                rs.getString("note"),
                rs.getLong("transaction_date")
        );
    });

}
