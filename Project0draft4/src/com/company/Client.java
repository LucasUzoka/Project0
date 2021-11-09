package com.company;

public class Client {
    private int access_number;
    private String name;
    private String email;
    private String password;
    private Boolean exist_client;



    public Client(){   //just in case
    }

    //** remember alt insert to easily build constructor and get set --Revise Week 1 class samples
    public Client(int access_number, String name, String email, String password, Boolean exist_client) {
        this.access_number = access_number;
        this.name = name;
        this.email = email;
        this.password = password;
        this.exist_client = exist_client;
    }

    public int getAccess_number() {
        return access_number;
    }

    public void setAccess_number(int access_number) {
        this.access_number = access_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getExist_client() {
        return exist_client;
    }

    public void setExist_client(Boolean exist_client) {
        this.exist_client = exist_client;
    }

    @Override
    public String toString() {
        return "Client{" +
                "access_number=" + access_number +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", exist_client=" + exist_client +
                '}';
    }



}
