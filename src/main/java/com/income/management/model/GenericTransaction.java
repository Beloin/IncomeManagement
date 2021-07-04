package com.income.management.model;

public class GenericTransaction {
    private long id;
    private long account;
    private float value;

    public GenericTransaction(long id, long account, float value) {
        this.id = id;
        this.account = account;
        this.value = value;
    }

    public long getId() {
        return id;
    }

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
}
