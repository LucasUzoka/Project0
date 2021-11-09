package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImplementation implements TransactionDao {
    private Connection connection;

    public TransactionDaoImplementation(){
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addTransaction(Transaction transaction) throws SQLException {
        String sql = "insert into transaction(amount, sender_id, receiver_id) values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, transaction.getAmount());
        preparedStatement.setInt(2, transaction.getSender_id());
        preparedStatement.setInt(3, transaction.getReceiver_id());

        int count = preparedStatement.executeUpdate();
        if (count> 0){
            System.out.println("Recorded");
        }else{
            System.out.println("Record not added");
        }
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        String sql = "update transaction set amount = ?, sender_id = ?, sender_receiver where transaction_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, transaction.getAmount());
            preparedStatement.setInt(2, transaction.getSender_id());
            preparedStatement.setInt(3, transaction.getReceiver_id());
            preparedStatement.setInt(4, transaction.getTransaction_id());

            int count = preparedStatement.executeUpdate();

            if (count> 0){
                System.out.println("Update Complete");
            }else{
                System.out.println("Update failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTransaction(int transaction_id) {
        String sql ="delete from transaction where transaction_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, transaction_id);
            int  count = preparedStatement.executeUpdate();
            if (count > 0){
                System.out.println("Deleted");
            }else {
                System.out.println("Transaction still available");
            }

        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }finally {
            System.out.println("finally");
        }
    }

    @Override
    public List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "select * from transaction";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){

                int transaction_id = resultSet.getInt(1);
                double amount = resultSet.getInt(2);
                int sender_id = resultSet.getInt(3);
                int receiver_id = resultSet.getInt(4);

                Transaction transaction = new Transaction(transaction_id, amount, sender_id, receiver_id);
                transactions.add(transaction);
            }

        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }finally {
            System.out.println("finally");
        }
        return transactions;
    }

    @Override
    public Transaction getTransactionById(int transId) {
        Transaction transaction = new Transaction();
        String sql = "select * from transaction where transaction_id = " + transId;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet != null){
                int transaction_id = resultSet.getInt(1);
                double amount = resultSet.getInt(2);
                int sender_id = resultSet.getInt(3);
                int receiver_id = resultSet.getInt(4);

                transaction = new Transaction(transaction_id, amount, sender_id, receiver_id);

            }else{
                System.out.println("please try again");
            }
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }finally {
            System.out.println("finally");
        }
        return transaction;
    }

    @Override
    public List<Transaction> getTransactionBySenderId(int senderId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "select * transactions where sender_id = " + senderId;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int transaction_id = resultSet.getInt(1);
                double amount = resultSet.getInt(2);
                int sender_id = resultSet.getInt(3);
                int receiver_id = resultSet.getInt(4);

                Transaction transaction = new Transaction(transaction_id, amount, sender_id, receiver_id);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }finally {
            System.out.println("finally");
        }

        return transactions;
    }

    @Override
    public List<Transaction> getTransactionByReceiverId(int receiverId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "select * transactions where receiver_id = " + receiverId;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int transaction_id = resultSet.getInt(1);
                double amount = resultSet.getInt(2);
                int sender_id = resultSet.getInt(3);
                int receiver_id = resultSet.getInt(4);

                Transaction transaction = new Transaction(transaction_id, amount, sender_id, receiver_id);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }finally {
            System.out.println("finally");
        }

        return transactions;
    }
}
