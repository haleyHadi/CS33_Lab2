package Library_Classes;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import exceptions.FullShelfException;

import Borrowable_Item_Classes.*;

public class Shelf<T extends Borrowable> {
    private ArrayList<T> items;
    private final int CAPACITY = 20; // arbitrary

    public Shelf() {
        items = new ArrayList<>();
    }

    public void browse() {
        boolean empty = false;
        for (T item : items) {
            if (item.isAvailable()) {
                empty = true;
                System.out.println(item.toString());
            }
        }
        if (!empty) {
            System.out.println("Shelf is empty.");
        }
    }

    // Checks if the shelf has space
    public boolean hasSpace() {            
        return items.size() < CAPACITY;  // Returns true if there is space
    }

    // Check if the item exists on the shelf
    public boolean contains(T item) {
        return items.contains(item);  // Returns true if item is on the shelf
    }

    public void addItem(T item) throws FullShelfException {
        if (items.size() >= CAPACITY) {
            // If the shelf is full, throw the FullShelfException
            throw new FullShelfException("Cannot add item to shelf: Shelf is full.");
        } else
            items.add(item);
    }

    public void removeItem(T item){
        if (items.size() > 0){
            items.remove(item);
            //System.out.println("Item: " + item + " , successfully removed.");  //testing proper removal
        }
    }

    public void borrowItem(T item) throws IllegalStateException, NoSuchElementException{
        if (!item.isAvailable()) {
            // If the item is already borrowed (unavailable), throw an IllegalStateException
            throw new IllegalStateException("Item is already borrowed or unavailable.");
        }
        else if (!items.contains(item)){
            // If the item doesn't exist in this shelf, throw a NoSuchElementException
            throw new NoSuchElementException("Item not found in this shelf.");
        }
        else {
            // Sets to a borrowed state if the shelf contains the item
            item.setAvailable(false);
            System.out.println("Item borrowed successfully.");
        }
    }



    public void returnItem(T item) {
        if (items.contains(item)) {
            item.setAvailable(true);                // Mark the item as returned
            System.out.println("Item returned successfully.");
        } else {
            System.out.println("Item not found on this shelf.");
        }
    }


}