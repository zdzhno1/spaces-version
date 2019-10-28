package com.sohu.spaces.version.ssh.dao;

import com.sohu.spaces.version.ssh.beans.Account;

import java.util.List;

public interface AccountDao {
    void save(Account account);

    void deleteById(Long id);

    void update(Account account);

    Account findById(Long id);

    List<Account> findAll();
}
