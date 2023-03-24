package com.etc.apiMonitor.models;

import com.etc.apiMonitor.views.AccountType;
import com.etc.apiMonitor.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Model {
    private final ViewFactory viewFactory;
    private static Model model;
    private static Client client;
    private final DatabaseDriver databaseDriver;
    private final AccountType loginAccountType = AccountType.CLIENT;
    private boolean adminLoginSuccessFlag = false;
    private boolean clientLoginSuccessFlag = false;
    private final ObservableList<Transaction> latestTransactions;
    private final ObservableList<Transaction> allTransactions;

    public ObservableList<Client> getUsers() {
        return users;
    }

    // Admin Data Section
    private final ObservableList<Client> users;



    private Model() {

        this.databaseDriver = new DatabaseDriver();
        this.viewFactory = new ViewFactory();
        //Client Data Section
        this.clientLoginSuccessFlag = false;
        //Admin Data Section
        this.latestTransactions = FXCollections.observableArrayList();
        this.allTransactions = FXCollections.observableArrayList();
        // Admin Data Section
        this.adminLoginSuccessFlag = false;
        this.users = FXCollections.observableArrayList();
    }

    public static synchronized Model getInstance() {
        if (model == null)  {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }



    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    /* Client Method Section */

    public boolean isClientLoginSuccessFlag() {
        return clientLoginSuccessFlag;
    }

    public void setClientLoginSuccessFlag(boolean clientLoginSuccessFlag) {
        this.clientLoginSuccessFlag = clientLoginSuccessFlag;
    }

    public void evaluateClientCred(String eMailAddress, String password){
        ResultSet resultSet = null;
        try {
            resultSet = databaseDriver.getUserData(eMailAddress, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (resultSet.isBeforeFirst()) {
                client = new Client(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email_address"),
                        LocalDate.parse(resultSet.getString("date_created")
                ));

                this.clientLoginSuccessFlag =true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void evaluateAdminCred(String username, String password){

        if (username.equals("admin_user")  || password.equals("adminpassword") ) {
            this.adminLoginSuccessFlag = true;
        }

    }

    private void prepareTransactions(ObservableList<Transaction> transactions, int limit) {
        /*ResultSet resultSet = databaseDriver.getTransactions(this.client.emailAddressProperty().get(), limit);
        try {
            while (resultSet.next()) {
                //TODO: Add all properties
                String name = resultSet.getNString("Name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    public void setLatestFailed() {
        //TODO: Define only for failed
        //prepareTransactions(this.latestFailed, 4);
    }

    public void setAllTransactions() {
        //TODO: Define only for failed
        prepareTransactions(this.allTransactions, -1);
    }

    public ObservableList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public boolean isAdminLoginSuccessFlag() {
        return adminLoginSuccessFlag;
    }

    public void setAdminLoginSuccessFlag(boolean adminLoginSuccessFlag) {
        this.adminLoginSuccessFlag = adminLoginSuccessFlag;
    }

    public void createNewClient(String firstName, String lastName, String email, String password, LocalDate date) {
        if (databaseDriver.insertNewUser(firstName, lastName, email, password, date)) {
            System.out.println("insert of user into database successful");
        } else {
            System.out.println("Error creating user");
        }
    }

    public void setUsers() {
        ResultSet resultSet = databaseDriver.getAllClientsData();
        try {
            while (resultSet.next()){
                String fName = resultSet.getString("first_name");
                String lName = resultSet.getString("last_name");
                String email = resultSet.getString("email_address");
                String[] dateParts = resultSet.getString("date_created").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                users.add(new Client(fName, lName, email, date));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
