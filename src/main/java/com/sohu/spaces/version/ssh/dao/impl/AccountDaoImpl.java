package com.sohu.spaces.version.ssh.dao.impl;

import com.sohu.spaces.version.ssh.beans.Account;
import com.sohu.spaces.version.ssh.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("accountDao")
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

    @Autowired
    public void initInject(@Qualifier("dataSource") DataSource dataSource){
        setDataSource(dataSource);
    }

    @Override
    public void save(Account account) {
        System.out.println("保存");
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("删除");
    }

    @Override
    public void update(Account account) {
        String sql = "update account set name = ?,money=? where id=?";
        getJdbcTemplate().update(sql,account.getName(),500,account.getId());
        int i=1/0;
        getJdbcTemplate().update(sql,account.getName(),600,account.getId());
    }

    @Override
    public Account findById(Long id) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        return null;
    }
}
