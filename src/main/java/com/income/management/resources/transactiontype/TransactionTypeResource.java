package com.income.management.resources.transactiontype;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.TransactionType;
import com.income.management.resources.transactiontype.TransTypeDTO;
import com.income.management.service.TransactionTypeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
public class TransactionTypeResource {


    public TransactionTypeResource(TransactionTypeService typeService) {
        this.typeService = typeService;
    }

    final TransactionTypeService typeService;


    @GetMapping("/transaction-types")
    public List<TransactionType> index() {
        return typeService.findAll();
    }

    @GetMapping("/transaction-types/{id}")
    public TransactionType findOne(@PathVariable long id) {
        try {
            return this.typeService.find(id);
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping("/transaction-types")
    public TransactionType createTrans(@Valid @RequestBody TransTypeDTO dto) {
        return this.typeService.createTransType(dto.getName());
    }

    @DeleteMapping("/transaction-types/{id}")
    public void deleteTrans(@PathVariable long id) {
        try {
            this.typeService.deleteTransactionType(id);
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
    }
}
