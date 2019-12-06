package entity;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * the bank account entity
 */
@Getter
public class Account {

    // user name
    private String name;
    @Setter
    private BigDecimal balance;
    //  user's operations list
    private List<Operation> operations;

    public Account(String name) {
        this.name = name;
        this.operations = new ArrayList<>();
        this.balance = BigDecimal.ZERO;
    }
}
