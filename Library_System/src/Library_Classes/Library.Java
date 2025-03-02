package Library_Classes;

import java.util.ArrayList;

import Borrowable_Item_Classes.Borrowable;
import Library_Classes.Shelf;
import UI_Classes.Interface;
import User_Classes.*;

public class Library {
    private Shelf<Borrowable>[][] shelves;
    private ArrayList<User> users;
    private Interface ui;
    private UserMode userMode;
    private final int shelvesColumns;
    private final int shelvesRows;

    public Library() {
        userMode = UserMode.MENU;
        users = new ArrayList<>();
        ui = new Interface();

        shelvesColumns = 3;
        shelvesRows = 10;
        // Initialize a 3x3 grid of shelves -> arbitrary size
        shelves = new Shelf[shelvesColumns][shelvesRows];
        
        // Populate each cell with a new Shelf
        for (int i = 0; i < shelvesColumns; i++) {
            for (int j = 0; j < shelvesRows; j++) {
                shelves[i][j] = new Shelf<>();  // Initialize each shelf in the 2D array
            }
        }
    }

    //A more modular constructor to allow users to change the size of the Library
    public Library(int row, int col) {
        users = new ArrayList<>();
        ui = new Interface();

        shelvesColumns = col;
        shelvesRows = row;
        // Initialize a 3x3 grid of shelves -> arbitrary size
        shelves = new Shelf[row][col];
        
        // Populate each cell with a new Shelf
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                shelves[i][j] = new Shelf<>();  // Initialize each shelf in the 2D array
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
        
    }

    public void libraryLoop() { // very wip
        while (true) {
            switch (userMode) {
                case UserMode.MENU:
                    libraryMenu();
                case UserMode.BROWSING:
                    break;
                case UserMode.LOGGED_BORROWER:
                    handleBorrower();
                    break;
                case UserMode.LOGGED_LIBRARIAN:
                    handleLibrarian();
                    break;
                case UserMode.LOG_IN:
                    logIn();
                    break;
                case UserMode.CREATE_ACCOUNT:
                    createAccount();
                    break;
            }
        }
    }

    private void libraryMenu() {
        int choice = ui.interfaceMenu();

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
            case 4:
                System.exit(1);
            default:
                break;
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
                userMode = UserMode.LOGGED_BORROWER;
                break;
            case 2:
                users.add(new Librarian(accountName, password, this));
                userMode = UserMode.LOGGED_LIBRARIAN;
                break;
            default:
                users.add(new Borrower(accountName, password)); // default user type
                userMode = UserMode.LOGGED_BORROWER;
                break;
        }
        System.out.println("Congratulations! You have successfully created your account!");
    }

    private void logIn() {
        while (true) {
            String username = ui.getAccountName();
            String password = ui.getPassword();

            for (User user : users) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    if (user instanceof Borrower) {
                        userMode = UserMode.LOGGED_BORROWER;
                    }
                    if (user instanceof Librarian) {
                        userMode = UserMode.LOGGED_LIBRARIAN;
                    }

                    return;
                }
            }
            int option = ui.invalidUsernameOrPassword();
            if (option == 2) {
                userMode = UserMode.MENU;
                return;
            }
        }
    }

    private void handleBorrower() {
        int option = ui.borrowerInterface();

        switch (option) {
            case 4:
                userMode = UserMode.MENU;
                break;
        }
    }

    private void handleLibrarian() {
        int option = ui.librarianInterface();

        switch (option) {
            case 6:
                userMode = UserMode.MENU;
                break;
        }
    }
}