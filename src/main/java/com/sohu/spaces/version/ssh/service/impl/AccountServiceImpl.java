package com.sohu.spaces.version.ssh.service.impl;

import com.sohu.spaces.version.ssh.beans.Account;
import com.sohu.spaces.version.ssh.dao.AccountDao;
import com.sohu.spaces.version.ssh.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("accountService")
//@Transactional(transactionManager = "hibernateTransactionManager",readOnly = true,propagation = Propagation.SUPPORTS)
public class AccountServiceImpl implements AccountService {

    @Autowired
    @Qualifier("accountDao")
    private AccountDao accountDao;

    @Resource(name="accountDao2")
    private AccountDao accountDao2;

    @Override
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void saveAccount(Account account) {
        //accountDao.save(account);
        accountDao2.save(account);
        try {
            Thread.sleep(123L); //测试接口执行时间的aop配置
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void deleteAccountById(Long id) {
        accountDao.deleteById(id);
    }

    @Override
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void updateAccount(Account account) {
        accountDao2.update(account);
    }

    @Override
    public Account findAccountById(Long id) {
        return accountDao2.findById(id);
    }

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAll();
    }
}
