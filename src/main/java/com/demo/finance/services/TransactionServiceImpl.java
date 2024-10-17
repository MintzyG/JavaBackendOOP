package com.demo.finance.services;

import com.demo.finance.exceptions.FinanceBadRequestException;
import com.demo.finance.exceptions.FinanceResourceNotFoundException;
import com.demo.finance.models.Transaction;
import com.demo.finance.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public List<Transaction> fetchAllTransactions(Integer user_id, Integer category_id) {
        return transactionRepository.findAll(user_id, category_id);
    }

    @Override
    public Transaction fetchTransactionById(Integer user_id, Integer category_id, Integer transaction_id) throws FinanceResourceNotFoundException {
        return transactionRepository.findById(user_id, category_id, transaction_id);
    }

    @Override
    public Transaction addTransaction(Integer user_id, Integer category_id, Double amount, String note, Long transaction_date) throws FinanceResourceNotFoundException {
        int transaction_id = transactionRepository.create(user_id, category_id, amount, note, transaction_date);
        return transactionRepository.findById(user_id, category_id, transaction_id);
    }

    @Override
    public void updateTransaction(Integer user_id, Integer category_id, Integer transaction_id, Transaction transaction) throws FinanceBadRequestException {
    transactionRepository.update(user_id, category_id, transaction_id, transaction);
    }

    @Override
    public void removeTransaction(Integer user_id, Integer category_id, Integer transaction_id) throws FinanceResourceNotFoundException {

    }
}
