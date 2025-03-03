package Library_Classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.Arrays;

import Borrowable_Item_Classes.*;
import Library_Classes.Shelf;
import UI_Classes.Interface;
import User_Classes.*;
import exceptions.FullShelfException;

public class Library {
    private Shelf<Borrowable>[][] shelves;
    private ArrayList<User> users;
    private Interface ui;
    private UserMode userMode;
    private User currentUser;
    private final int shelvesColumns;
    private final int shelvesRows;

    public Library() {
        userMode = UserMode.MENU;
        currentUser = null;
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
        int option = ui.browserInterface();
        if (option == 0) {
            userMode = UserMode.MENU;
            return;
        }
        shelves[(option - 1) % shelvesColumns][(option - 1) % shelvesRows].browse();
    }

    //Adds and item to the next available slot in a shelf
    //returns true if succesfully added, false if not
    public boolean addItem(Borrowable item) throws FullShelfException{
        for (int i = 0; i < shelvesRows; i++){

            for (int j = 0; j < shelvesColumns; j++){
                Shelf<Borrowable> shelf = shelves[i][j];
                if (shelf.hasSpace()){
                    shelf.addItem(item);
                    return true;
                }
            }
        }

        return false;
    }

    //Removes an item from any shelf in the library
    public boolean removeItem(Borrowable item) {
        boolean itemFound = false;
        
        for (int i = 0; i < shelvesRows; i++) {
            for (int j = 0; j < shelvesColumns; j++) {
                Shelf<Borrowable> shelf = shelves[i][j];
                if (shelf.contains(item)) {  // Check if the shelf contains the item
                    shelf.removeItem(item);  // Remove the item from the shelf
                    itemFound = true;
                    // System.out.println("Item removed from shelf (" + i + ", " + j + ").");      //used for testing 
                    break;  // Exit the loop once item is removed
                }
            }
            if (itemFound) 
                return true;  // return true if found and removed
        }
        throw new NoSuchElementException("The item was not found in the library."); // item not in library if it reaches this point
    }

    // Method to create and add Borrowable items to the library
    public void addDefaultItems() {
        // List of Borrowable objects to be added to the library
        List<Borrowable> items = Arrays.asList(
            new Book("The Great Gatsby", "F. Scott Fitzgerald", "Classic"),
            new Book("1984", "George Orwell", "Dystopian"),
            new Book("To Kill a Mockingbird", "Harper Lee", "Fiction"),
            new Book("Moby Dick", "Herman Melville", "Adventure"),
            new Book("Pride and Prejudice", "Jane Austen", "Romance"),

            new AudioBook("Fahrenheit 451", "Ray Bradbury", "Political Fiction", "Christopher Hurt", 301),
            new Magazine("Times Person of the Year - 2012", "Politics", 1234567, new Date(2012 - 1900, 12 - 1, 18)),
            new Movie("The Batman", "Action, Suspense", "Matt Reeves", 176, new Date(2022 - 1900, 3 - 1, 4)),
            new Music("Die With A Smile", "Pop", "Lady Gaga, Bruno Mars", 252, new Date(2024 - 1900, 8 - 1, 15))
        );

        // Add each item to the library
        for (Borrowable item : items) {
            // System.out.println(item.toString());  //testing output
            try {
                if (!addItem(item)) {
                    System.out.println("Could not add " + item.getTitle() + " to the library (library is full).");
                }
            } catch (FullShelfException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    //returns the entrie 2D array of shelves
    public Shelf<Borrowable>[][] getShelves(){
        return shelves;
    }


    public void libraryLoop() { // very wip
        while (true) {
            switch (userMode) {
                case UserMode.MENU:
                    libraryMenu();
                    break;
                case UserMode.BROWSING:
                    browseLibrary();
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
        currentUser = users.getLast();
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
                    currentUser = user;
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
            case 1:
                browseLibrary();
            case 4:
                userMode = UserMode.MENU;
                break;
        }
    }

    private void handleLibrarian() {
        int option = ui.librarianInterface();
        Borrowable item = null;

        switch (option) {
            case 1:
                browseLibrary();
                break;
            case 4:
                item = createItem();
                try {
                    addItem(item);
                } catch (FullShelfException e) {
                    System.out.println("Error: Shelf is full.");
                }
            case 5:
                item = createItem();
                try {
                    removeItem(item);
                } catch (NoSuchElementException e) {
                    System.out.println("Error: Item is already not found in the library.");
                }
            case 6:
                userMode = UserMode.MENU;
                break;
        }
    }

    private Borrowable createItem() {
        int option = ui.selectItemType();
        switch (option) {
            case 1:
                return ui.addBook();
            case 2:
                return ui.addMovie();
            case 3:
                return ui.addAudioBook();
            case 4: 
                return ui.addMusic();
            case 5:
                return ui.addMagazine();
        }
        return null; // null item, should not be reachable
    }

    private void returnItem() {
        
    }
    
    private void borrowItem() {
        int shelf = ui.selectShelf();
        Borrowable item = createItem();

        
    }
}