package com.income.management.resources.transaction.dto;

public class TransactionDTO {
    private long account;
    private float value;
    private long category;


    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }
}
