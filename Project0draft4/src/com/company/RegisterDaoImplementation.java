package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegisterDaoImplementation implements RegisterDao{
    Connection connection;

    public  RegisterDaoImplementation(){
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addRegister(Register register) {
        String sql = "insert into register (access_number, open_deposit) values (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, register.getAccess_number());
            preparedStatement.setDouble(2,register.getOpen_deposit());

            int count = preparedStatement.executeUpdate();

            if (0 < count){
                System.out.println("Registration Complete ");
            }else{
                System.out.println("please check your input and try again");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRegister(Register register) {
        String sql = "update register set access_number = ?, open_deposit = ? where registration_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, register.getAccess_number());
            preparedStatement.setDouble(2,register.getOpen_deposit());
            preparedStatement.setInt(3, register.getRegistration_id());

            int count = preparedStatement.executeUpdate();

            if (0 < count){
                System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteRegister(int registration_id) {
        String sql = "delete from register where registration_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, registration_id);

            int count = preparedStatement.executeUpdate();
            if (count > 0){
                System.out.println("Delete Complete");
            }else{
                System.out.println("Sorry! please try again");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            System.out.println("deleting...");
        }
    }

    @Override
    public List<Register> getRegister() {
        List<Register> registration = new ArrayList<>();
        String sql = "select * from register";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int registration_id = resultSet.getInt(1);
                int access_number = resultSet.getInt(2);
                double open_deposit = resultSet.getDouble(3);

                Register register = new Register(registration_id, access_number, open_deposit);
                registration.add(register);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registration;
    }

    @Override
    public Register getRegisterByID(int registration_id) {
        Register register = new Register();
        String sql = "select from register where registration_id = " + registration_id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet != null){
                int registrationID = resultSet.getInt(1);
                int access_number = resultSet.getInt(2);
                double open_deposit = resultSet.getDouble(3);

                register = new Register(registrationID, access_number, open_deposit);
            }else {
                System.out.println("0 results");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return register;
    }
}
