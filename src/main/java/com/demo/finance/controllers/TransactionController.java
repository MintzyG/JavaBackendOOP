package com.demo.finance.controllers;

import com.demo.finance.models.Transaction;
import com.demo.finance.services.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/categories/{category_id}/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

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
}
