package Library_Classes;

import java.util.ArrayList;

import UI_Classes.Interface;
import User_Classes.*;

public class Library {
    private ArrayList<Shelf> shelves;
    private ArrayList<User> users;
    private Interface ui;
    private UserMode userMode;

    public Library() {
        shelves = new ArrayList<>();
        users = new ArrayList<>();
        ui = new Interface();
    }

    public void librarySetup() {
        int choice = ui.interfaceStart();

        switch (choice) {
            case 1:
                userMode = UserMode.BROWSING;
                break;
            case 2:
                userMode = UserMode.LOG_IN;
                break;
            case 3:
                userMode = UserMode.CREATE_ACCOUNT;
                break;
            default:
                break;
        }
    }

    public void libraryLoop() { // very wip
        while (true) {
            switch (userMode) {
                case UserMode.BROWSING:
                    break;
                case UserMode.LOG_IN:
                    break;
                case UserMode.CREATE_ACCOUNT:
                    createAccount();
                    break;
            }
        }
    }

    private void createAccount() {
        String accountName;
        String password;
        boolean validName = true;

        while (true) {
            accountName = ui.getAccountName();

            for (User user : users) {
                if (accountName.equals(user.getUsername())) {
                    System.out.println("Error: Username already in use.");
                    validName = false;
                    break;
                }
            }

            if (validName) {
                break;
            } else {
                validName = true;
            }
        }
        password = ui.getPassword();

        int userType = ui.checkUser();
        switch (userType) {
            case 1:
                users.add(new Borrower(accountName, password));
                break;
                case 2:
                users.add(new Librarian(accountName, password));
                break;
                default:
                users.add(new Borrower(accountName, password)); // default user type
                break;
        }
        System.out.println("Congratulations! You have successfully created your account!");
        // userMode = UserMode.BROWSING;
    }
}
