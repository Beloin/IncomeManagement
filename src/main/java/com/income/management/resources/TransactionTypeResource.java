package com.income.management.resources;

import com.income.management.model.TransactionType;
import com.income.management.service.TransactionTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class TransactionTypeResource {


    public TransactionTypeResource(TransactionTypeService typeService) {
        this.typeService = typeService;
    }

    final TransactionTypeService typeService;


    @GetMapping("/transactions")
    public List<TransactionType> index() {
        return typeService.findAll();
    }
}
