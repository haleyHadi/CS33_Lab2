package User_Classes;

import Borrowable_Item_Classes.*;
import Library_Classes.*;
import exceptions.OverdueBookException;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class Borrower extends User {
    private ArrayList<Borrowable> borrowedItems;

    public Borrower(String username, String password) {
        super(username, password);
        this.borrowedItems = new ArrayList<Borrowable>();
    }

    public void borrowItem(Borrowable item) throws OverdueBookException{
        // Check if there are any overdue books
        if (hasOverdueBooks()) {
            // If there are, throw an OverdueBookException
            throw new OverdueBookException("You cannot borrow a new book because you have overdue books. Please return them first.");
        }
        if (item.borrow())
            borrowedItems.add(item);
    }

    public Borrowable returnItem(int index) {
        Borrowable item = borrowedItems.get(index);
        borrowedItems.remove(index);
        return item;
    }

    //checks if the user's borrowedItems list has any overdue books
    public boolean hasOverdueBooks() {
        for (Borrowable item : borrowedItems) {
            if (item instanceof Book) {
                Book book = (Book) item;
                if (!book.isAvailable() && book.dueDate().before(new Date())) {
                    return true; // If any borrowed book is overdue
                }
            }
        }
        return false;
    }

    public int getNumberBorrowedBooks() {
        return borrowedItems.size();
    }

    public int displayBorrowedItems() {
        int size = borrowedItems.size();
        for (int i = 0; i < size; i++) {
            System.out.printf("[%d] %s.", i + 1, borrowedItems.get(i).toString());
        }
        return size;
    }
    //Getter Methods
    // public ArrayList<Borrowabble> getBorrowedItems(){
    //     return borrowedItems;
    // }

}