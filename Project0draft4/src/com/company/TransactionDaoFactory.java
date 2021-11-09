package com.company;

public class TransactionDaoFactory {
    private static TransactionDaoImplementation daoTrans;

    private TransactionDaoFactory() {
    }

    public  static TransactionDao getTransactionDao() {
        if (daoTrans == null) {
            daoTrans = new TransactionDaoImplementation();
        }
        return daoTrans;
    }
}
