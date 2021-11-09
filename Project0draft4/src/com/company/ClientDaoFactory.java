package com.company;

public class ClientDaoFactory {
    private static ClientDao daoClient;

    private ClientDaoFactory() {
    }

    public  static ClientDao getClientDao() {
        if (daoClient == null) {
            daoClient = new ClientDaoImplimentation();
        }
        return daoClient;
    }
}