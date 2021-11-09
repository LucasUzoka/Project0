package com.company;

import java.sql.SQLException;
import java.util.List;

public interface AccountDao {

    void addAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Account account);
    Account getAccountById(int accountId) throws SQLException;
    Account getAccountByAccessNumber(int accessNumber) throws SQLException;
    List<Account> getAccounts() throws SQLException;
    List<Account> getAccountsByClient(int accessNumber);

}
