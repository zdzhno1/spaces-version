package com.sohu.spaces.version.ssh.service;

import com.sohu.spaces.version.ssh.beans.Account;

import java.util.List;

public interface AccountService {

    void saveAccount(Account account);

    void deleteAccountById(Long id);

    void updateAccount(Account account);

    Account findAccountById(Long id);

    List<Account> findAllAccount();
}
