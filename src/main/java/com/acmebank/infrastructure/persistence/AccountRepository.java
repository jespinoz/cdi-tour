package com.acmebank.infrastructure.persistence;

import com.acmebank.domain.Account;

public interface AccountRepository {

    public void addAccount(Account account);

    public Account getAccount(String customer);

    public void updateAccount(Account account);

    public void deleteAccount(Account account);
}
