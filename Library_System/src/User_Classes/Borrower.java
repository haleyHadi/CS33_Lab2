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

    public void returnItem(Borrowable item) throws NoSuchElementException{
        // Check if the item exists in borrowedItems
        if (!borrowedItems.contains(item)) {
            // If the item is not found, throw a NoSuchElementException
            throw new NoSuchElementException("The item is not found in your borrowed items.");
        }

        if (item.returnItem()) {
            // Remove the item from borrowedItems if returned successfully
            borrowedItems.remove(item);
            System.out.println("Item successfully returned.");
        }
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


    //Getter Methods
    // public ArrayList<Borrowabble> getBorrowedItems(){
    //     return borrowedItems;
    // }

}