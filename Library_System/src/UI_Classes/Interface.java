package UI_Classes;

import java.util.Date;
import java.util.Scanner;

import Borrowable_Item_Classes.AudioBook;
import Borrowable_Item_Classes.Book;
import Borrowable_Item_Classes.Magazine;
import Borrowable_Item_Classes.Movie;
import Borrowable_Item_Classes.Music;
import User_Classes.Borrower;

public class Interface {
    private Scanner scanner;
    private final int MAX_NAME_LENGTH = 16; // completely arbitrary
    private final int MAX_PASSWORD_LENGTH = 20;

    public Interface() {
        scanner = new Scanner(System.in);
    }

    public int interfaceMenu() {
        System.out.println("Hello and welcome to the library!" + 
                         "\nHow may I help you today?" + 
                         "\n" + 
                         "\n[1] Browse selection." + 
                         "\n[2] Log in." + 
                         "\n[3] Create an account." +
                         "\n[4] Exit program.");
        return parseInteger(1, 5);
    }
                        
    public int borrowerInterface() {
        System.out.println("Please select an option." + 
                         "\n" + 
                         "\n[1] Browse selection." + 
                         "\n[2] Borrow an item" + 
                         "\n[3] Return an item." +
                         "\n[4] Log out.");
        return parseInteger(1, 5);
    }

    public int librarianInterface() {
        System.out.println("Please select an option." + 
                         "\n" + 
                         "\n[1] Browse selection." + 
                         "\n[2] Borrow an item" + 
                         "\n[3] Return an item." +
                         "\n[4] Add an item." +
                         "\n[5] Remove an item." +
                         "\n[6] Log out.");
        return parseInteger(1, 7);
    }

    public int browserInterface() {
        System.out.println("Shelves are numbered from 1-30." +
                         "\nPlease enter the number of the shelf you wish to browse." +
                         "\nOr press [0] to exit.");
        return parseInteger(0, 31);
    }

    public int selectShelf() {
        System.out.println("Shelves are numbered from 1-30." +
                         "\nPlease enter the number of the shelf you wish to select.");
        return parseInteger(1, 31);
    }

    public int invalidUsernameOrPassword() {
        System.out.println("Error: Invalid username or password" +
                         "\n" + 
                         "\n[1] Continue?" + 
                         "\n[2] Return to menu.");
        return parseInteger(1, 3);
    }

    public String getAccountName() {
        System.out.printf("Enter account name, must be %d characters or less.\n", MAX_NAME_LENGTH);
        return getSpecifiedLength(MAX_NAME_LENGTH);
    }

    public String getPassword() {
        System.out.printf("Enter password, must be %d characters or less.\n", MAX_PASSWORD_LENGTH);
        return getSpecifiedLength(MAX_PASSWORD_LENGTH);
    }

    public int checkUser() {
        System.out.println("What kind of user are you?" + 
                         "\n" + 
                         "\n[1] Borrower." + 
                         "\n[2] Librarian.");
        return parseInteger(1, 3);
    }

    public Book addBook() {
        System.out.println("Enter title.");
        String title = scanner.nextLine();
        System.out.println("Enter author.");
        String author = scanner.nextLine();
        System.out.println("Enter genre.");
        String genre = scanner.nextLine();
        return new Book(title, author, genre);
    }

    public Movie addMovie() {
        System.out.println("Enter title.");
        String title = scanner.nextLine();
        System.out.println("Enter director.");
        String director = scanner.nextLine();
        System.out.println("Enter genre.");
        String genre = scanner.nextLine();
        System.out.println("Enter length in minutes.");
        int length = parseInteger(0, 10000);
        System.out.println("Enter year of release.");
        int year = parseInteger(0, 2100);
        System.out.println("Enter month of release.");
        int month = parseInteger(1, 13);
        System.out.println("Enter day of release.");
        int day = parseInteger(1, 32);
        return new Movie(title, genre, director, length, new Date(year - 1900, month - 1, day));
    }
    
    public Magazine addMagazine() {
        System.out.println("Enter title.");
        String title = scanner.nextLine();
        System.out.println("Enter genre.");
        String genre = scanner.nextLine();
        System.out.println("Enter issue number.");
        int issueNumber = parseInteger(0, 1000000000);
        System.out.println("Enter year of release.");
        int year = parseInteger(0, 2100);
        System.out.println("Enter month of release.");
        int month = parseInteger(1, 13);
        System.out.println("Enter day of release.");
        int day = parseInteger(1, 32);
        return new Magazine(title, genre, issueNumber, new Date(year - 1900, month - 1, day));
    }

    public AudioBook addAudioBook() {
        System.out.println("Enter title.");
        String title = scanner.nextLine();
        System.out.println("Enter author.");
        String author = scanner.nextLine();
        System.out.println("Enter genre.");
        String genre = scanner.nextLine();
        System.out.println("Enter narrator.");
        String narrator = scanner.nextLine();
        System.out.println("Enter length in minutes.");
        int length = parseInteger(0, 10000);
        return new AudioBook(title, author, genre, narrator, length);
    }

    public Music addMusic() {
        System.out.println("Enter title.");
        String title = scanner.nextLine();
        System.out.println("Enter genre.");
        String genre = scanner.nextLine();
        System.out.println("Enter artist.");
        String artist = scanner.nextLine();
        System.out.println("Enter length in minutes.");
        int length = parseInteger(0, 10000);
        System.out.println("Enter year of release.");
        int year = parseInteger(0, 2100);
        System.out.println("Enter month of release.");
        int month = parseInteger(1, 13);
        System.out.println("Enter day of release.");
        int day = parseInteger(1, 32);
        return new Music(title, genre, artist, length, new Date(year - 1900, month - 1, day));
    }

    public int selectItemType() {
        System.out.println("Please select a type of item." + 
                         "\n" + 
                         "\n[1] Book" + 
                         "\n[2] Movie." + 
                         "\n[3] Audio book." +
                         "\n[4] Music." +
                         "\n[5] Magazine.");
        return parseInteger(1, 6);
    }

    public int selectBorrowed(Borrower borrower) {
        System.out.println("Please select an item to return.\n");
        int size = borrower.displayBorrowedItems();
        return parseInteger(1, size + 1);
    }

    private String getSpecifiedLength(int maxLength) {
        String result;
        while (true) {
            result = scanner.nextLine();
            if (result.length() <= maxLength) {
                break;
            }
        }
        System.out.println("RESULT: " + result);
        return result;
    }

    private int parseInteger(int low, int high) { // gets input and checks if it is an integer and in a specified range
        int result;
        while (true) {
            if (scanner.hasNextInt()) {
                result = scanner.nextInt();
                if (result >= low && result < high) {
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Invalid input, integer not in range");
                }
            } else {
                System.out.println("Invalid input, an integer is expected.");
            }

            scanner.nextLine();
        }

        return result;
    }
}
