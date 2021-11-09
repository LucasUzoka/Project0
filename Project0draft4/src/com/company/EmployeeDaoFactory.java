package com.company;

public class EmployeeDaoFactory {
    private static EmployeeDao daoEmployee;

    private EmployeeDaoFactory() {
    }

    public  static EmployeeDao getEmployeeDao() {
        if (daoEmployee == null) {
            daoEmployee = new EmployeeDaoImplementation();
        }
        return daoEmployee;
    }
}
