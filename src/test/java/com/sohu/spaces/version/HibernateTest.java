package com.sohu.spaces.version;

import com.sohu.spaces.version.ssh.beans.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * 测试hibernate独立运行
 */
public class HibernateTest {

    public static void main(String[] args) {
        Configuration configure = new Configuration().configure();
        SessionFactory sessionFactory = configure.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Account account = new Account();
        account.setName("xiaoming");
        account.setMoney(1001.0);
        try {
            session.save(account);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            //session.close(); //getCurrentSession获取的session不要关闭
            sessionFactory.close();
        }
    }
}
