package com.company;

public class Transaction {
    private int transaction_id;
    private double amount;
    //private Boolean confirmed; also removed from constructor
    private int sender_id;
    private int receiver_id;

    public Transaction(){
    }

    public Transaction(int transaction_id, double amount, int sender_id, int receiver_id) {
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_id=" + transaction_id +
                ", amount=" + amount +
                ", sender_id=" + sender_id +
                ", receiver_id=" + receiver_id +
                '}';
    }
}
