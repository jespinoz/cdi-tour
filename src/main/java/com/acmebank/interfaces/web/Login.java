package com.acmebank.interfaces.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.acmebank.domain.User;
import com.acmebank.application.UserService;

@Named
@SessionScoped
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Credentials credentials;

    @Inject
    private UserService userService;

    private User user;

    public String login() {
        user = userService.getUser(credentials.getUsername());
        // Password check here...

        return "account.xhtml";
    }

    @Named
    @Produces
    @LoggedIn
    public User getCurrentUser() {
        return user;
    }
}
