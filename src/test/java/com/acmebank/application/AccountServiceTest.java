package com.acmebank.application;

import com.acmebank.domain.Account;
import com.acmebank.infrastructure.persistence.AccountRepository;
import com.acmebank.infrastructure.persistence.DefaultAccountRepository;
import com.acmebank.infrastructure.persistence.Repository;
import com.acmebank.util.AuditInterceptor;
import com.acmebank.util.Audited;
import com.acmebank.util.Profiled;
import com.acmebank.util.ProfilingInterceptor;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class AccountServiceTest {

    @Inject
    private AccountService accountService;

    @PersistenceContext
    private EntityManager entityManager;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "acme-bank-test.war")
                // Application layer components directly under test.
                .addClass(AccountService.class)
                .addClass(DefaultAccountService.class)
                .addClass(AccountServiceDecorator.class)
                // Domain layer components.
                .addClass(Account.class)
                // Persistence layer components.
                .addClass(Repository.class)
                .addClass(AccountRepository.class)
                .addClass(DefaultAccountRepository.class)
                // Utilities.
                .addClass(Audited.class)
                .addClass(AuditInterceptor.class)
                .addClass(Profiled.class)
                .addClass(ProfilingInterceptor.class)
                .addAsResource("META-INF/persistence.xml",
                        "META-INF/persistence.xml")
                .addAsWebInfResource("test-beans.xml", "beans.xml");
    }

    @Test
    @InSequence(1)
    public void testAddAccount() {
        Account account = new Account();
        account.setNumber("1111");
        account.setCustomer("nrahman");
        account.setBalance(1000.00);

        accountService.addAccount(account);

        Query query = entityManager
                .createQuery("SELECT a FROM Account a WHERE a.customer = :customer");
        query.setParameter("customer", "nrahman");
        account = (Account) query.getSingleResult();

        assertEquals("1111", account.getNumber());
        assertEquals("nrahman", account.getCustomer());
        assertEquals(new Double(1000.00), new Double(account.getBalance()));
    }

    @Test
    @InSequence(2)
    public void testGetAccount() {
        Account account = accountService.getAccount("nrahman");

        assertNotNull(account);
        assertEquals("1111", account.getNumber());
        assertEquals("nrahman", account.getCustomer());
        assertEquals(new Double(1000.00), new Double(account.getBalance()));
    }

    @Test
    @InSequence(3)
    public void testUpdateAccount() {
        Account account = accountService.getAccount("nrahman");
        account.setBalance(1001.50);
        accountService.updateAccount(account);

        Query query = entityManager
                .createQuery("SELECT a FROM Account a WHERE a.customer = :customer");
        query.setParameter("customer", "nrahman");
        account = (Account) query.getSingleResult();

        assertEquals("1111", account.getNumber());
        assertEquals("nrahman", account.getCustomer());
        assertEquals(new Double(1001.50), new Double(account.getBalance()));
    }

    @Test
    @InSequence(4)
    public void testDeleteAccount() {
        Account account = accountService.getAccount("nrahman");
        accountService.deleteAccount(account);

        Query query = entityManager
                .createQuery("SELECT a FROM Account a WHERE a.customer = :customer");
        query.setParameter("customer", "nrahman");

        assertTrue(query.getResultList().isEmpty());
    }
}
