package com.acmebank.infrastructure.persistence;

import com.acmebank.domain.User;

public interface UserRepository {

    public void addUser(User user);

    public User getUser(String username);

    public void deleteUser(User user);
}
