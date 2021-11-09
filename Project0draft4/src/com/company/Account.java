package com.company;

public class Account {
    private int account_id;
    private int access_number;
    private double account_balance;

    public Account(int account_id, int access_number, double account_balance) {
        this.account_id = account_id;
        this.access_number = access_number;
        this.account_balance = account_balance;
    }

    public Account() {

    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getAccess_number() {
        return access_number;
    }

    public void setAccess_number(int access_number) {
        this.access_number = access_number;
    }

    public double getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(double account_balance) {
        this.account_balance = account_balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", access_number=" + access_number +
                ", account_balance=" + account_balance +
                '}';
    }
}
