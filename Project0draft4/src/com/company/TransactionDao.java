package com.company;

import java.sql.SQLException;
import java.util.List;

public interface TransactionDao {
    void addTransaction(Transaction transaction) throws SQLException;

    void updateTransaction(Transaction transaction);

    void deleteTransaction(int transaction_id);

    List<Transaction> getTransactions();

    Transaction getTransactionById(int transId);

    List<Transaction> getTransactionBySenderId(int senderId);

    List<Transaction> getTransactionByReceiverId(int receiverId);

}
