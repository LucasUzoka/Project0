package com.company;

public class RegisterDaoFactory {
    private static RegisterDao daoRegister;

    private RegisterDaoFactory() {
    }

    public  static RegisterDao getRegisterDao() {
        if (daoRegister == null) {
            daoRegister = new RegisterDaoImplementation();
        }
        return daoRegister;
    }
}
