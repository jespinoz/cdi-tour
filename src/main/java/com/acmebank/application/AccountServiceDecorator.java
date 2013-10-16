package com.acmebank.application;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

import com.acmebank.domain.Account;
import java.util.logging.Level;
import java.util.logging.Logger;

@Decorator
public class AccountServiceDecorator implements AccountService {

    // TODO Inject this from a property file?
    private static final double LARGE_AMOUNT = 1000.00;

    private static final Logger logger = Logger.getLogger(
            AccountServiceDecorator.class.getName());

    @Inject
    @Delegate
    private AccountService accountService;

    @Override
    public void addAccount(Account account) {
        if (account.getBalance() > LARGE_AMOUNT) {
            logger.log(Level.WARNING,
                    "Large amount alert on account: {0}, amount: {1}",
                    new Object[]{account.getNumber(), account.getBalance()});
        }

        accountService.addAccount(account);
    }

    @Override
    public Account getAccount(String customer) {
        return accountService.getAccount(customer);
    }

    @Override
    public void updateAccount(Account account) {
        if (account.getBalance() > LARGE_AMOUNT) {
            logger.log(Level.WARNING,
                    "Large amount alert on account: {0}, amount: {1}",
                    new Object[]{account.getNumber(), account.getBalance()});
        }

        accountService.updateAccount(account);
    }

    @Override
    public void deleteAccount(Account account) {
        if (account.getBalance() > LARGE_AMOUNT) {
            logger.log(Level.WARNING,
                    "Large amount alert on account: {0}, amount: {1}",
                    new Object[]{account.getNumber(), account.getBalance()});
        }

        accountService.deleteAccount(account);
    }

    @Override
    public void transfer(String toBank, String toAccount, String fromBank,
            String fromAccount, double amount) {
        if (amount > LARGE_AMOUNT) {
            logger.log(Level.WARNING,
                    "Large amount alert on account: {0}, amount: {1}",
                    new Object[]{fromAccount, amount});
        }

        accountService.transfer(toBank, toAccount, fromBank, fromAccount, amount);
    }
}
