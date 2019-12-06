package entity;


import lombok.Getter;
import util.Util;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *  the opertaion entity
 */
@Getter
public class Operation {

    private OperationType type;
    private LocalDateTime date;
    private BigDecimal amount;
    private BigDecimal balance;

    public Operation(OperationType type, BigDecimal amount, BigDecimal balance) {
        this.type = type;
        this.date = LocalDateTime.now();
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "type=" + type +
                ", date=" + Util.formatDate(date) +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }
}
