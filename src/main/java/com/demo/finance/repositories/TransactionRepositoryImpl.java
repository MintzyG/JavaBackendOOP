package com.demo.finance.repositories;

import com.demo.finance.exceptions.FinanceBadRequestException;
import com.demo.finance.exceptions.FinanceResourceNotFoundException;
import com.demo.finance.models.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private static final String SQL_CREATE = "";

    @Override
    public List<Transaction> findAll(Integer user_id, Integer category_id) {
        return List.of();
    }

    @Override
    public Transaction findById(Integer user_id, Integer category_id, Integer transaction_id) throws FinanceResourceNotFoundException {
        return null;
    }

    @Override
    public Integer create(Integer user_id, Integer category_id, Double amount, String note, Long Transaction_date) throws FinanceBadRequestException {
        return 0;
    }

    @Override
    public void update(Integer user_id, Integer category_id, Integer transactionId, Transaction transaction) throws FinanceBadRequestException {

    }

    @Override
    public void removeById(Integer user_id, Integer category_id, Integer transaction_id) throws FinanceResourceNotFoundException {

    }
}
