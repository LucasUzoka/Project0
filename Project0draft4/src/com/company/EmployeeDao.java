package com.company;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {

    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(int employee_id);

    List<Employee> getEmployees();

    Employee getEmployeeByID(int employee_id);

    Employee getEmployeeByAccess_number(int access_number);

   // Boolean login(String email, String password) throws SQLException;
}
