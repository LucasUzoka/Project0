package com.company;
import java.sql.SQLException;
import java.util.List;

public interface ClientDao {
    void addClient(Client client) throws SQLException;

    void updateClient(Client client) throws SQLException;

    void deleteClient(int access_number) throws SQLException;

    List<Client> getClients() throws SQLException; //

    Client getClientByID(int access_number) throws SQLException;  //access_number

    Client getClientByEmail(String email) throws SQLException;



   // Boolean login(String email, String password) throws SQLException; //put this somewhere else if not working

   // void login();
}
