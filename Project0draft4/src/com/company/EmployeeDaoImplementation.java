package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;

public class EmployeeDaoImplementation implements EmployeeDao{

    Connection connection;

    public EmployeeDaoImplementation(){
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addEmployee(Employee employee) {
        String sql = "insert into employee (access_number, exist_client) values (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employee.getAccess_number());
            preparedStatement.setBoolean(2,employee.getExist_client());

            int count = preparedStatement.executeUpdate();

            if (count > 0){
                System.out.println("An Employee has been added");
            }else{
                System.out.println("Sorry, nothing added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("adding...");
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = "update employee set access_number = ?, exist_client = ? where employee_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,employee.getAccess_number());
            preparedStatement.setBoolean(2,employee.getExist_client());
            preparedStatement.setInt(3, employee.getEmployee_id());

            int count = preparedStatement.executeUpdate();
            if (count > 0){
                System.out.println("employee details updated");
            }else{
                System.out.println("...failed to update employee");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("updating...");
        }
    }

    @Override
    public void deleteEmployee(int employee_id) {
        String sql = "delete from employee where employee_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,employee_id);

            int count = preparedStatement.executeUpdate();
            if (count > 0){
                System.out.println("You have deleted an employee");
            }else {
                System.out.println("employee could not be deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("deleting...");
        }
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "select * from employee";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                int employee_id = resultSet.getInt(1);
                int access_number = resultSet.getInt(2);
                Boolean exist_client = resultSet.getBoolean(3);

                Employee employee = new Employee(employee_id, access_number, exist_client);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("..compile list");
        }
        return employees;
    }

    @Override
    public Employee getEmployeeByID(int employeeID) {
        Employee employee = new Employee();   //IntelliJ suggested a new constructor (Employee.Java -empty)
        String sql = "select from employee where employee_id = " + employeeID;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet != null){
                int employee_id = resultSet.getInt(1);
                int access_number = resultSet.getInt(2);
                Boolean exist_client = resultSet.getBoolean(3);
                employee = new Employee(employee_id, access_number, exist_client);
            }else{
                System.out.println("We have no employee by that ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee getEmployeeByAccess_number(int Access_number) {
        Employee employee = new Employee();
        String sql = "select * from employee where access_number = " + Access_number;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                int employee_id = resultSet.getInt(1);
                int access_number = resultSet.getInt(2);
                Boolean exist_client = resultSet.getBoolean(3);
                employee = new Employee(employee_id, access_number, exist_client);
            }else {
                System.out.println("Email entered does not exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
