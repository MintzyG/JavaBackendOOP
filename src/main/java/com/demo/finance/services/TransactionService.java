package com.demo.finance.services;

import com.demo.finance.exceptions.FinanceBadRequestException;
import com.demo.finance.exceptions.FinanceResourceNotFoundException;
import com.demo.finance.models.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> fetchAllTransactions(Integer user_id, Integer category_id);

    Transaction fetchTransactionById(Integer user_id, Integer category_id, Integer transaction_id) throws FinanceResourceNotFoundException;

    Transaction addTransaction(Integer user_id, Integer category_id, Double amount, String note, Long transaction_date) throws FinanceResourceNotFoundException;

    void updateTransaction(Integer user_id, Integer category_id, Integer transaction_id, Transaction transaction) throws FinanceBadRequestException;

    void removeTransaction(Integer user_id, Integer category_id, Integer transaction_id) throws FinanceResourceNotFoundException;
}
