package com.sohu.spaces.version.ssh.beans;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {

    private static final long serialVersionUID = 7811213564043103548L;
    private Long id;
    private String name;
    private Double money;

    public Account() {
    }

    public Account(Long id, String name, Double money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id) &&
                name.equals(account.name) &&
                money.equals(account.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, money);
    }
}
