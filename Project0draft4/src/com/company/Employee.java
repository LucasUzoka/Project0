package com.company;

public class Employee {
    private int employee_id; //added private access modifier
    //String employee_name;
    private int access_number;
    //String username;
    private Boolean exist_client;

    public Employee(int employee_id, int access_number, Boolean exist_client) {
        this.employee_id = employee_id;
        this.access_number = access_number;
        this.exist_client = exist_client;
    }

    public Employee() {

    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getAccess_number() {
        return access_number;
    }

    public void setAccess_number(int access_number) {
        this.access_number = access_number;
    }

    public Boolean getExist_client() {
        return exist_client;
    }

    public void setExist_client(Boolean exist_client) {
        this.exist_client = exist_client;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", access_number='" + access_number + '\'' +
                ", exist_client=" + exist_client +
                '}';
    }
}
