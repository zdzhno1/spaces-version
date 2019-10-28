package com.sohu.spaces.version.ssh.controller;

import com.sohu.spaces.version.ssh.beans.Account;
import com.sohu.spaces.version.ssh.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value="/{id}",method= {RequestMethod.GET,RequestMethod.POST})
    public String findAccountById(@PathVariable Long id, Model model){
        Account account = accountService.findAccountById(id);
        model.addAttribute("account", account);
        return "accountDetail";
    }

    @RequestMapping(value = "/",method={RequestMethod.GET,RequestMethod.POST})
    public String findAllAccount(Model model){
        List<Account> accounts = accountService.findAllAccount();
        model.addAttribute("accounts", accounts);
        return "accountList";
    }

    @RequestMapping("/delete_account")
    public String deleteAccountById(Long id){
        accountService.deleteAccountById(id);
        return "redirect:accountList.jsp";
    }

    @RequestMapping("/update_account")
    public String updateAccount(Account account,Model model){
        if (true) {
            throw new DataAccessResourceFailureException("");
        }
        accountService.updateAccount(account);
        return "redirect:"+account.getId();
    }

}
