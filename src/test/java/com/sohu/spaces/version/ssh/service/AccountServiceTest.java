package com.sohu.spaces.version.ssh.service;

import com.sohu.spaces.version.ssh.beans.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring/applicationContext.xml")
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void saveAccount() {
        Account account = new Account();
        account.setId(3L);
        account.setName("xiaoli");
        account.setMoney(700.0);
        accountService.saveAccount(account);
    }
}