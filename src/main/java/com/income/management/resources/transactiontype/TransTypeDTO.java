package com.income.management.resources.transactiontype;

import javax.validation.constraints.NotNull;

public class TransTypeDTO {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }
}
