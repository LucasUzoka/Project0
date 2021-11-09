package com.company;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountDaoImplementation implements AccountDao {
    Connection connection;

    public AccountDaoImplementation(){
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addAccount(Account account) {
        String sql = "insert into account (access_number, account_balance) values (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account.getAccess_number());
            preparedStatement.setDouble(2, account.getAccount_balance());

            int count = preparedStatement.executeUpdate();
            if(count > 0 ){
                System.out.println("Success");
            }else{
                System.out.println("failure");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        String sql = "update account set access_number = ?, account_balance = ? where account_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account.getAccess_number());
            preparedStatement.setDouble(2, account.getAccount_balance());

            int count = preparedStatement.executeUpdate();
            if(count > 0 ){
                System.out.println("Success");
            }else{
                System.out.println("failure");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAccount(Account account) {
        String sql = "delete from account where account_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account.getAccount_id());

            int count = preparedStatement.executeUpdate();
            if(count > 0 ){
                System.out.println("Success");
            }else{
                System.out.println("failure");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account getAccountById(int accountId) throws SQLException {
        Account account = new Account();
        String sql = "select * from account where account_id = " + accountId;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        if (resultSet.next()) {
            int account_id = resultSet.getInt(1);
            int access_number = resultSet.getInt(2);
            double account_balance = resultSet.getDouble(3);

            account = new Account(account_id, access_number, account_balance );
        } else {
            System.out.println("no record found");
        }
        return account;

    }

    @Override
    public Account getAccountByAccessNumber(int access_Number) throws SQLException {
        Account account = new Account();
        String sql = "select * from Account where access_number = " + access_Number;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        if (!resultSet.next()) {
            int account_id = resultSet.getInt(1);
            int access_number = resultSet.getInt(2);
            double account_balance = resultSet.getDouble(3);

            account = new Account(account_id, access_number, account_balance );
        } else {
            System.out.println("no record found");
        }
        return account;
    }

    @Override
    public List<Account> getAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "select * from account";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            int account_id = resultSet.getInt(1);
            int access_number = resultSet.getInt(2);
            double account_balance = resultSet.getDouble(3);

            Account account = new Account(account_id, access_number, account_balance);
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public List<Account> getAccountsByClient(int access_Number) {
        List<Account> accounts = new ArrayList<>();
        String sql = "select * from Account where access_number = " + access_Number;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                int account_id = resultSet.getInt(1);
                int access_number = resultSet.getInt(2);
                double account_balance = resultSet.getDouble(3);

                Account account = new Account(account_id, access_Number, account_balance);
                accounts.add(account);
            }

        } catch (SQLException e) {
            System.out.println("does not exist");
            e.printStackTrace();
        }
        return accounts;
    }
}
