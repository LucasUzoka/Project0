package com.company;

public class Register {
    private int registration_id;
    private int access_number;
    private double open_deposit;
   // private boolean confirmed;




    public Register(int registration_id, int access_number, double open_deposit) {
        this.registration_id = registration_id;
        this.access_number = access_number;
        this.open_deposit = open_deposit;
      //  this.confirmed = confirmed;
    }

    public Register() {
    }

    public int getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(int registration_id) {
        this.registration_id = registration_id;
    }

    public int getAccess_number() {
        return access_number;
    }

    public void setAccess_number(int access_number) {
        this.access_number = access_number;
    }

    public double getOpen_deposit() {
        return open_deposit;
    }

    public void setOpen_deposit(double open_deposit) {
        this.open_deposit = open_deposit;
    }

//    public boolean isConfirmed() {
//        return confirmed;
//    }
//
//    public void setConfirmed(boolean confirmed) {
//        this.confirmed = confirmed;
//    }

    @Override
    public String toString() {
        return "Register{" +
                "registration_id=" + registration_id +
                ", access_number='" + access_number + '\'' +
                ", open_deposit=" + open_deposit +
                ", confirmed=" + //confirmed +
                '}';
    }
}
