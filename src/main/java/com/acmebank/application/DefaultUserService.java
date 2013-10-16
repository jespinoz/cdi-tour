package com.acmebank.application;

import javax.inject.Inject;

import com.acmebank.util.Audited;
import com.acmebank.infrastructure.persistence.UserRepository;
import com.acmebank.domain.User;
import javax.ejb.Stateless;

@Stateless
public class DefaultUserService implements UserService {

    @Inject
    private UserRepository userRepository;

    @Audited
    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    public User getUser(String username) {
        return userRepository.getUser(username);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }
}
