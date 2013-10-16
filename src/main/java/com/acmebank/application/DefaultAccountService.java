package com.acmebank.application;

import java.text.DecimalFormat;

import javax.ejb.Asynchronous;
import javax.inject.Inject;

import com.acmebank.util.Audited;
import com.acmebank.infrastructure.persistence.AccountRepository;
import com.acmebank.domain.Account;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class DefaultAccountService implements AccountService {

    // TODO Inject from producer/properties?
    private static final DecimalFormat MONEY = new DecimalFormat("$0.00");

    private static final Logger logger
            = Logger.getLogger(DefaultAccountService.class.getName());

    @Inject
    private AccountRepository accountRepository;

    @Audited
    @Override
    public void addAccount(Account account) {
        accountRepository.addAccount(account);
    }

    @Override
    public Account getAccount(String customer) {
        return accountRepository.getAccount(customer);
    }

    @Audited
    @Override
    public void updateAccount(Account account) {
        accountRepository.updateAccount(account);
    }

    @Audited
    @Override
    public void deleteAccount(Account account) {
        accountRepository.deleteAccount(account);
    }

    @Asynchronous
    @Override
    public void transfer(String toBank, String toAccount, String fromBank,
            String fromAccount, double amount) {
        try {
            Thread.sleep(3000);
            logger.log(Level.INFO,
                    "Performing transfer to bank: {0}, account: {1} from bank: {2}, account: {3} amount: {4}",
                    new Object[]{toBank, toAccount, fromBank, fromAccount, MONEY.format(amount)});
        } catch (InterruptedException e) {
            throw new RuntimeException("Error performing transfer", e);
        }
    }
}
