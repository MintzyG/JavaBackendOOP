package com.demo.finance.controllers;

import com.demo.finance.models.Transaction;
import com.demo.finance.services.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories/{category_id}/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("")
    public ResponseEntity<List<Transaction>> getAllTransactions(HttpServletRequest request,
                                                                @PathVariable("category_id") Integer category_id) {
        int user_id = (Integer) request.getAttribute("user_id");
        List<Transaction> transactions = transactionService.fetchAllTransactions(user_id, category_id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("{transaction_id}")
    public ResponseEntity<Transaction> getTransactionById(HttpServletRequest request,
                                                         @PathVariable("category_id") Integer category_id,
                                                         @PathVariable("transaction_id") Integer transaction_id) {
        int user_id = (Integer) request.getAttribute("user_id");
        Transaction transaction = transactionService.fetchTransactionById(user_id, category_id, transaction_id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<Transaction> addTransaction(HttpServletRequest request,
                                                      @PathVariable("category_id") Integer category_id,
                                                      @RequestBody Map<String, Object> transactionMap) {
        int user_id = (Integer) request.getAttribute("user_id");
        Double amount = Double.valueOf(transactionMap.get("amount").toString());
        String note = (String) transactionMap.get("note");
        Long transaction_date = (Long) transactionMap.get("transaction_date");
        Transaction transaction = transactionService.addTransaction(user_id, category_id, amount, note, transaction_date);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PutMapping("/{transaction_id}")
    public ResponseEntity<Map<String, Boolean>> updateTransaction(HttpServletRequest request,
                                                                  @PathVariable("category_id") Integer category_id,
                                                                  @PathVariable("transaction_id") Integer transaction_id,
                                                                  @RequestBody Transaction transaction) {
        int user_id = (Integer) request.getAttribute("user_id");
        transactionService.updateTransaction(user_id, category_id, transaction_id, transaction);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{transaction_id}")
    public ResponseEntity<Map<String, Boolean>> deleteTransaction(HttpServletRequest request,
                                                                  @PathVariable("category_id") Integer category_id,
                                                                  @PathVariable("transaction_id") Integer transaction_id) {
        int user_id = (Integer) request.getAttribute("user_id");
        transactionService.removeTransaction(user_id, category_id, transaction_id);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
