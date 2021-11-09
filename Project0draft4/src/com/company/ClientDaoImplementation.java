package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class ClientDaoImplimentation implements ClientDao {
    Connection connection; // removed private access modifier

    public ClientDaoImplimentation() {
        this.connection = ConnectionFactory.getConnection();}//link implements to database

    @Override
    public void addClient(Client client) throws SQLException {
        String sql = "insert into client (name, email, password) values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql); //sql represents insert statement
        preparedStatement.setString(1, client.getName());
        preparedStatement.setString(2, client.getEmail());
        preparedStatement.setString(3, client.getPassword());
      //  preparedStatement.setBoolean(4, client.getExist_client());
        int count = preparedStatement.executeUpdate();
        if (count > 0)
            System.out.println("Client added...");
        else
            System.out.println("Sorry! entry attempt failed");
    }

    @Override
    public void updateClient(Client client) throws SQLException {
        String sql = "update client set name = ?, email = ?, password =? where access_number = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, client.getName());
        preparedStatement.setString(2, client.getEmail());
        preparedStatement.setString(3, client.getPassword());
     //   preparedStatement.setBoolean(4, client.getExist_client());
        preparedStatement.setInt(4, client.getAccess_number());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("client details updated");
        } else {
            System.out.println("Sorry! update could not be completed");
        }
    }

    @Override
    public void deleteClient(int access_number) throws SQLException {
        String sql = "delete from client where access_number = ?"; //parameters involved (?) use (PreparedStatement)
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, access_number);
        int count = preparedStatement.executeUpdate();
        if(count > 0){
            System.out.println("client deleted");
        }else{
            System.out.println("Oops! something went wrong");
        }
    }



    @Override
    public List<Client> getClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "select * from client";
        Statement statement = connection.createStatement(); // no parameters in sql statement (Statement)
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int access_number = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String password = resultSet.getString(4);
            Boolean exist_client = resultSet.getBoolean(5);
            Client client = new Client(access_number, name, email, password, exist_client); //new client object with the ()fields
            clients.add(client); //put new client object into client list
        }
        return clients; //line 61 created list with the added (new) client object values
    }


    @Override
    public Client getClientByID(int accessNumber) throws SQLException {//parameter can be anything NOT column name
        Client client = new Client();
        String sql = "select * from client where access_number = " + accessNumber; // ? not needed in this statement
        Statement statement = connection.createStatement(); // no parameters in sql statement (Statement)
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        if (resultSet != null) {
            int access_number = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String password = resultSet.getString(4);
            Boolean exist_client = resultSet.getBoolean(5);
            client = new Client(access_number, name, email, password, exist_client);
        } else {
            System.out.println("Could not find client");
        }
        return client;
    }

    @Override
    public Client getClientByEmail(String eMail) throws SQLException {
        Client client = new Client();
        String sql = "select * from client where email = ?"; //.formatted(eMail); //string interpolation (read up more)
       PreparedStatement preparedStatement = connection.prepareStatement(sql);
       preparedStatement.setString(1, eMail);
       ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int access_number = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String password = resultSet.getString(4);
            Boolean exist_client = resultSet.getBoolean(5);

            client = new Client(access_number, name, email, password, exist_client);
        }else {
            System.out.println("email does not exist...please check the spelling and try again");
            return null;
        }
        return client;
    }

}