package com.acmebank.interfaces.web;

import javax.inject.Inject;

import com.acmebank.domain.Account;
import com.acmebank.domain.User;
import com.acmebank.application.AccountService;
import com.acmebank.application.UserService;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DataPopulator implements ServletContextListener {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(
            DataPopulator.class.getName());

    @Inject
    private UserService userService;

    @Inject
    private AccountService accountService;

    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Populating application data...");

        User user = userService.getUser("nrahman");

        if (user != null) {
            logger.info("Resetting user 1...");

            Account account = accountService.getAccount(user.getUsername());

            accountService.deleteAccount(account);
            userService.deleteUser(user);
        }

        user = userService.getUser("rrahman");

        if (user != null) {
            logger.info("Resetting user 2...");

            Account account = accountService.getAccount(user.getUsername());

            accountService.deleteAccount(account);
            userService.deleteUser(user);
        }

        user = new User();
        user.setUsername("nrahman");
        user.setFirstName("Nicole");
        user.setLastName("Rahman");
        user.setPassword("secret1");

        userService.addUser(user);

        Account account = new Account();
        account.setNumber("1111AAA");
        account.setCustomer(user.getUsername());
        account.setBalance(40000);

        accountService.addAccount(account);

        user = new User();
        user.setUsername("rrahman");
        user.setFirstName("Reza");
        user.setLastName("Rahman");
        user.setPassword("secret2");

        userService.addUser(user);

        account = new Account();
        account.setNumber("2222BBB");
        account.setCustomer(user.getUsername());
        account.setBalance(25000);

        accountService.addAccount(account);

        logger.info("Done!");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // Nothing to do.
    }
}
