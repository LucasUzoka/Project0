package com.company;

public class AccountDaoFactory {
    private static AccountDao daoAccount;

    private AccountDaoFactory() {
    }

    public  static AccountDao getAccountDao() {
        if (daoAccount == null) {
            daoAccount = (AccountDao) new AccountDaoImplementation();
        }
        return daoAccount;
    }
}
