package com.company;

import java.sql.*;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static ClientDao daoClient = ClientDaoFactory.getClientDao();
    private static EmployeeDao daoEmployee = EmployeeDaoFactory.getEmployeeDao();
    private static TransactionDao daoTrans = TransactionDaoFactory.getTransactionDao();
    private static AccountDao daoAccount = AccountDaoFactory.getAccountDao();
    private static RegisterDao daoRegister = RegisterDaoFactory.getRegisterDao();

    private static Connection connection = ConnectionFactory.getConnection();
    private static Scanner scanner;



    public static void main(String[] args) {
        // write your code here
        scanner = new Scanner(System.in);
        try {
            homePage();
        } catch (SQLException e) {
            System.out.println("oops");
            e.printStackTrace();
        }

    }

    public static void homePage() throws SQLException {
        boolean flag2 = true;
        while (flag2) {
            System.out.println("******************************");
            System.out.println("***********Welcome************");
            System.out.println("*************to***************");
            System.out.println("**********RevaBank************");
            System.out.println("==============================");

            System.out.println("Press 1: Login || Press 2: Register || Press 3: Close");

            int input = scanner.nextInt();
            switch (input) {
                case 1: {
                    login();
                    break;
                }

                case 2: {
                    registerClient();
                    break;
                }

                case 3: {
                    System.out.println("Thank you");
                    System.out.println("Exiting...");
                    flag2 = false;
                    break;
                }
                default:
                    homePage();
                    break;
            }
        }
    }

    private static void registerClient() throws SQLException {
        System.out.print("Name: ");
        String name1 = scanner.nextLine();

        System.out.print("email: ");
        String eMail = scanner.nextLine();

        String sqlCount = "select count(*) from client where email = \"" + eMail + "\";";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCount);
        resultSet.next();
//        try {
//            statement = connection.createStatement();
//        } catch (SQLException e) {
//            System.out.println("Something is wrong");
//            e.printStackTrace();
//        }
//        ResultSet resultSet;
//        try {
//            resultSet = statement.executeQuery(sqlCount);
//        } catch (SQLException e) {
//            System.out.println("Something is wrong");
//            e.printStackTrace();
//        }
//        try {
//            resultSet.next();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //email already available check
        int countAvailable = resultSet.getInt("count(*)");
//            countAvailable = resultSet.getInt("count(*)");
//        } catch (SQLException e) {
//            System.out.println("Umm....");
//            e.printStackTrace();
  //      }
        while (countAvailable > 0) {
            System.out.print("email already in use, try again ");
            eMail = scanner.nextLine();
            sqlCount = "select count(*) from client where email = \"" + eMail + "\";";
        //    try {
            resultSet = statement.executeQuery(sqlCount);
            resultSet.next();
            countAvailable = resultSet.getInt("count(*)");
         //   } catch (SQLException e) {
        //        System.out.println(".......");
          //      e.printStackTrace();
//            }
//            try {
//                resultSet.next();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                countAvailable = resultSet.getInt("count(*)"); //Remember to review try-catch-finally
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }

        System.out.print("Please Provide a Password: ");
        String passWord = scanner.nextLine();

        Client clientNew = new Client();

        clientNew.setName(name1);
        clientNew.setEmail(eMail);
        clientNew.setPassword(passWord);



        daoClient.addClient(clientNew);

        Client newClient = daoClient.getClientByEmail(eMail);
        System.out.println("Registration Success! Thank you for choosing RevaBank");
        System.out.println("Hit any Key");

        clientPage(newClient);
    }

    private static void clientPage(Client client) throws SQLException {
        boolean flag = true;
        while (flag) {

            System.out.println("Customer:- 1: Register Account || 2: Withdraw Funds || 3: Send Money || 4: Make Deposit || 5: Get Balance || 6: Close Page");

            int input = scanner.nextInt();

            switch (input) {
                case 1: {
                    System.out.println("Enter Opening Deposit Amount");
                    double open_deposit = scanner.nextDouble();
                    scanner.nextLine();
                    Register register = new Register();
                    register.setAccess_number(client.getAccess_number());
                    register.setOpen_deposit(open_deposit);
                    daoRegister.addRegister(register);
                    System.out.println("Registration Complete");

                    clientPage(client);
                    break;
                }

                case 2: {
                    accountsDisplay(client);
                    System.out.println("Enter Account ID: ");
                    int account_id = scanner.nextInt();
                    scanner.nextLine();

                    Account withdraw_account = daoAccount.getAccountById(account_id);
//                    try {
//                        withdraw_account = daoAccount.getAccountById(account_id);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
                    Transaction withdraw = new Transaction();
                    withdraw.setReceiver_id(1);
                    withdraw.setSender_id(account_id);

                    System.out.print("Withdrawal Amount: $");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    if (withdraw_account.getAccount_balance() - amount < 0) {
                        System.out.println("Insufficient Funds");
                    } else if (amount < 0) {
                        System.out.println("Invalid Amount");
                    } else {
                        withdraw.setAmount(amount);
                        daoTrans.addTransaction(withdraw);
//                        try {
//                            daoTrans.addTransaction(withdraw);
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
                        withdraw_account.setAccount_balance(withdraw_account.getAccount_balance() - amount);
                        daoAccount.updateAccount(withdraw_account);
                        System.out.println("You have withdrawn $" + amount);
                    }

                    clientPage(client);
                    break;
                }

                case 3: {
                    accountsDisplay(client);

                    System.out.print("Enter Account ID: ");
                    int senderId = scanner.nextInt();
                    scanner.nextLine();

                    Account senderAccount = daoAccount.getAccountById(senderId);
//                    try {
//                        senderAccount = daoAccount.getAccountById(senderId);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }

                    Transaction send = new Transaction();
                    send.setSender_id(senderId);

                    System.out.print("Pay: $");
                    double sendAmount = scanner.nextDouble();
                    scanner.nextLine();

                    if (sendAmount > senderAccount.getAccount_balance()) {
                        System.out.println("Insufficient Funds");
                        clientPage(client);
                    } else {
                        System.out.print("Please enter the account ID of where you would like to transfer your money. Account ID: ");
                        int receiver_id = scanner.nextInt();
                        scanner.nextLine();
                     //   try {
                            Account receiverAccount = daoAccount.getAccountById(receiver_id);
                   //     } catch (SQLException e) {
                    //        e.printStackTrace();
                      //  }
                        send.setReceiver_id(receiver_id);
                        send.setAmount(sendAmount);

               //         try {
                            daoTrans.addTransaction(send);
         //               } catch (SQLException e) {
        //                    e.printStackTrace();
                   //     }
                        System.out.println("Press Enter to return to the user menu");
                    }
                    clientPage(client);
                    break;
                }

                case 4: {
                    accountsDisplay(client);

                    System.out.println("Enter account ID: ");
                    int deposit = scanner.nextInt();
                    scanner.nextLine();
                    Account deposit_account = daoAccount.getAccountById(deposit);
//                    try {
//                        deposit_account = daoAccount.getAccountById(deposit);
//                    } catch (SQLException e) {
//                        System.out.println(".......");
//                        e.printStackTrace();
//                    }
                    Transaction depositT = new Transaction();
                    System.out.print("Deposit");
                    System.out.print("Enter Deposit Amount: $");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    if (amount < 0) {
                        System.out.println("Invalid Transaction");
                    } else {
                        depositT.setAmount(amount);
                        depositT.setSender_id(1);
                        depositT.setReceiver_id(deposit);

             //           try {
                        daoTrans.addTransaction(depositT);
//                        } catch (SQLException e) {
//                            System.out.println(".....");
//                            e.printStackTrace();
//                        }
                        deposit_account.setAccount_balance(deposit_account.getAccount_balance() + amount);
                        daoAccount.updateAccount(deposit_account);
                        System.out.println("Deposit success!");
                    }
                    System.out.println("Press Enter to return to the user menu");

                    clientPage(client);
                    break;
                }

                case 5: {
                    accountsDisplay(client);
                    clientPage(client);
                    break;
                }

                case 6: {
                    System.out.println("Thank you");
                    System.out.println("Exiting...");
                    clientPage(client);
                    flag = false;

                    break;
                }

                default:
                    System.out.println("Choose between 1 - 6");

            }

        }

    }

    private static void accountsDisplay(Client client) {

        System.out.println("View account details below");
        List<Account> accounts = daoAccount.getAccountsByClient(client.getAccess_number());
        for (Account account : accounts) {
            System.out.println(account);
        }

    }

    private static void login() throws SQLException {
        System.out.println("email address: ");
        var scanner2 = new Scanner(System.in);
        String email = scanner2.nextLine();
        Client client = daoClient.getClientByEmail(email);
        while(client == null) {
            System.out.println("Sorry, that username does not exist.");
            System.out.println("Please try again.");
            System.out.print("Username: ");
            email = scanner.nextLine();
            client = daoClient.getClientByEmail(email);
        }

        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();

        while(!password.equals(client.getPassword())) {

            System.out.println("Please try again.");
            System.out.print("Password: ");
            password = scanner.nextLine();
        }

        if (daoEmployee.getEmployeeByAccess_number(client.getAccess_number()) != null){
            employeePage();
        }else{
            clientPage(client);
        }

    }

    private static void employeePage() throws SQLException {

        boolean flag1 = true;
        while (flag1) {
            System.out.println("Employee Menu: \n1: View accounts\n2: View transactions\n3: Logout\n4: Close");

            int input = scanner.nextInt();
            switch (input) {
                case 1: { //see existing accounts
                    System.out.println("ACCOUNTS");
                    List<Client> clients = daoClient.getClients();
                 //   try {
                       // clients = daoClient.getClients();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
                    for (Client c : clients) {
                        System.out.println(c);
                    }
                    System.out.println("Search Account ID ");
                    int access_number = scanner.nextInt();
                    scanner.nextLine();
                    Client c = daoClient.getClientByID(access_number);
                   // try {
                     //   c = daoClient.getClientByID(access_number);
                   // } catch (SQLException e) {
                //        e.printStackTrace();
                  //  }
                    List<Account> accounts = daoAccount.getAccountsByClient(c.getAccess_number());
                    for (Account account : accounts) {
                        System.out.println(account);
                    }
                    System.out.println("Hit any key");
                    break;
                }
                case 2: { //read current transactions
                    List<Transaction> transactions = daoTrans.getTransactions();

                    for (Transaction transaction : transactions) {
                        System.out.println(transaction);
                        System.out.println("Hit any key");
                    }
                    employeePage();
                    break;
                }
                case 3: {//Sign out
                 //   try {
                        homePage();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
                    break;
                }
                case 4: { //leave
                    System.out.println("Thank you");
                    System.out.println("Exiting...");
                    flag1 = false;
                    break;
                }
                default:
                    System.out.println("Choose between 1 - 4");
                    employeePage();
                    break;
            }
        }
    }
}





    





