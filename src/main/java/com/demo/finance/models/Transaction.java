package com.demo.finance.models;

public class Transaction {

    private Integer transaction_id;
    private Integer category_id;
    private Integer user_id;
    private Double amount;
    private String note;
    private Long transaction_date;

    public Transaction(Integer transaction_id, Integer category_id, Integer user_id, Double amount, String note, Long transaction_date) {
        this.transaction_id = transaction_id;
        this.category_id = category_id;
        this.user_id = user_id;
        this.amount = amount;
        this.note = note;
        this.transaction_date = transaction_date;
    }

    public Integer getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Long transaction_date) {
        this.transaction_date = transaction_date;
    }
}
