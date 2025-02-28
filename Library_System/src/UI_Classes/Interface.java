package UI_Classes;

import java.util.Scanner;

public class Interface {
    private Scanner scanner;
    private final int MAX_NAME_LENGTH = 16; // completely arbitrary
    private final int MAX_PASSWORD_LENGTH = 20;

    public Interface() {
        scanner = new Scanner(System.in);
    }

    public int interfaceStart() {
        System.out.println("Hello and welcome to the library!" + 
                         "\nHow may I help you today?" + 
                         "\n" + 
                         "\n[1] Browse selection." + 
                         "\n[2] Log in." + 
                         "\n[3] Create an account.");
        return parseInteger(1, 4);
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
