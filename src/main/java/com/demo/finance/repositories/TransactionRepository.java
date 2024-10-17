package com.demo.finance.repositories;

import com.demo.finance.exceptions.FinanceBadRequestException;
import com.demo.finance.exceptions.FinanceResourceNotFoundException;
import com.demo.finance.models.Transaction;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> findAll(Integer user_id, Integer category_id);

    Transaction findById(Integer user_id, Integer category_id, Integer transaction_id) throws FinanceResourceNotFoundException;

    Integer create(Integer user_id, Integer category_id, Double amount, String note, Long Transaction_date) throws FinanceBadRequestException;

    void update(Integer user_id, Integer category_id, Integer transactionId, Transaction transaction) throws FinanceBadRequestException;

    void removeById(Integer user_id, Integer category_id, Integer transaction_id) throws FinanceResourceNotFoundException;
}
