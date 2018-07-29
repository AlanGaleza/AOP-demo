package com.aop.dao;

import com.aop.model.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class AccountDAO {

    private String name;
    private String serviceName;

    public void addAccount(Account theAccount, boolean vipFlag) {

        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    public boolean doWork() {
        System.out.println(getClass() + ": doWork()");

        return true;
    }

    public List<Account> findAccounts() {

        List<Account> myAccounts = new ArrayList<>();

        Account temp1 = new Account("John", "Silver");
        Account temp2 = new Account("All", "Platinum");
        Account temp3 = new Account("Luca", "Gold");

        myAccounts.add(temp1);
        myAccounts.add(temp2);
        myAccounts.add(temp3);

        return myAccounts;
    }
}
