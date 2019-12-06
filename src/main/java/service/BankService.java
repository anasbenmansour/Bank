package service;

import entity.Account;
import entity.Operation;
import entity.OperationType;
import exception.BalanceException;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Bank service in which we implement the bank methods as deposit, withdraw and
 * prints history
 */

public class BankService {

    // protected modifier for test purpose
    @Getter
    protected final Account account;

    public BankService(String name) {
        this.account = new Account(name);
    }

    /**
     * Withdraw the given amount from the account thread safe method
     */
    public synchronized void withdraw(BigDecimal amount) {
        //Amount must be positive
        if (amount.compareTo(BigDecimal.ZERO) >= 0) {
            //check if the client have enough balance for the amount
            if (account.getBalance().compareTo(amount) >=0) {
                final BigDecimal balance = account.getBalance().subtract(amount);
                account.setBalance(balance);
                final Operation operation = new Operation(OperationType.WITHDRAWAL, amount, balance);
                account.getOperations().add(operation);
            } else {
                throw new BalanceException("You do not have balance for this withdrawal " + amount);
            }
        } else {
            throw new IllegalArgumentException("The amount of withdrawal must be positive " + amount);
        }
    }

    /**
     * Deposit the given amount into the account thread safe method
     */
    public synchronized void deposit(BigDecimal amount) {
        //Amount must be positive
        if (amount.compareTo(BigDecimal.ZERO) >= 0) {
            final BigDecimal balance = account.getBalance().add(amount);
            account.setBalance(balance);
            final Operation operation = new Operation(OperationType.DEPOSIT, amount, balance);
            account.getOperations().add(operation);
        } else {
            throw new IllegalArgumentException("The amount of deposit must be positive " + amount);
        }
    }

    /**
     * Prints the account history by status
     */
    public void printAccountHistory() {
        //display account name and number
        System.out.println("Name :" + account.getName() + "\n Operations : ");
        if (account.getOperations().size() == 0) {
            System.out.println("No Saved  operations");
        } else {
            // print history
            for (Operation operation : account.getOperations()) {
                System.out.println(operation);
            }
        }
    }
}
