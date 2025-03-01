package Library_Classes;

import java.util.ArrayList;

import Library_Classes.Shelf;
import UI_Classes.Interface;
import User_Classes.*;

public class Library {
    private Shelf<Borrowable>[][] shelves;
    private ArrayList<User> users;
    private Interface ui;
    private UserMode userMode;

    public Library() {
        users = new ArrayList<>();
        ui = new Interface();

        // Initialize a 3x3 grid of shelves -> arbitrary size
        shelves = new Shelf[3][3];
        
        // Populate each cell with a new Shelf
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                shelves[i][j] = new Shelf<>();  // Initialize each shelf in the 2D array
            }
        }
    }

    //A more modular constructor to allow users to change the size of the Library
    public Library(int row, int col){
            users = new ArrayList<>();
            ui = new Interface();
    
            // Initialize a 3x3 grid of shelves -> arbitrary size
            shelves = new Shelf[row][col];
            
            // Populate each cell with a new Shelf
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    shelves[i][j] = new Shelf<>();  // Initialize each shelf in the 2D array
                }
            }
        }
    }


    //Displays all items on the shelves 
    public void browseLibrary() {
        for (int i = 0; i < shelves.length; i++) {
            for (int j = 0; j < shelves[i].length; j++) {
                System.out.println("Shelf [" + i + "][" + j + "]:");
                shelves[i][j].browse();             // Browse items on this shelf
            }
        }
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
