package com.sohu.spaces.version.ssh.dao.impl;

import com.sohu.spaces.version.ssh.beans.Account;
import com.sohu.spaces.version.ssh.dao.AccountDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("accountDao2")
public class AccountDaoImpl2 extends HibernateDaoSupport implements AccountDao {

    @Autowired
    public void initInject(@Qualifier("sessionFactory") SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    @Override
    public void save(Account account) {
        getHibernateTemplate().save(account);
    }

    @Override
    public void deleteById(Long id) {
        Account account = getHibernateTemplate().get(Account.class, id);
        getHibernateTemplate().delete(account);
    }

    @Override
    public void update(Account account) {
        getHibernateTemplate().merge(account);
    }

    @Override
    public Account findById(Long id) {
        return getHibernateTemplate().get(Account.class,id);
    }

    @Override
    public List<Account> findAll() {
        int maxResults = getHibernateTemplate().getMaxResults();
        return getHibernateTemplate().findByExample(new Account(), 0, maxResults);
    }
}
