package com.income.management.resources.account;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.account.AccountWithValue;
import com.income.management.service.account.AccountService;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController()
public class AccountResource {

    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account")
    public boolean createAccount(@Valid @RequestBody )  {
        return this.accountService.createAccount("name");
    }

    @PutMapping("/account")
    public void changeAccountName(@Valid @RequestBody){
        return this.accountService.changeAccountName();
    }

    @GetMapping("/account")
    public List<AccountWithValue> getAllAccounts() {
        return this.accountService.findAccounts();
    }


    @DeleteMapping("/account/{id}")
    public void deleteAccount(@PathVariable(value = "id") long id){
        this.accountService.deleteAccount(id);
    }

}
