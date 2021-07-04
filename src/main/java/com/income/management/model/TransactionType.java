package com.income.management.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Date;

@EqualsAndHashCode
@ToString
public class TransactionType {
    private Long id;
    private String name;

    public Date getCreateAt() {
        return createAt;
    }

    private Date createAt;

    public TransactionType() {
    }

    public TransactionType(String name) {
        this.name = name;
    }

    public TransactionType(Long id, String name, Date createAt) {
        this.id = id;
        this.name = name;
        this.createAt = createAt;
    }

    public TransactionType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
