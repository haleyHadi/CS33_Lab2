package Library_Classes;

import java.util.ArrayList;

import Borrowable_Item_Classes.*;

public class Shelf<T extends Borrowable> {
    private ArrayList<T> items;
    private final int CAPACITY = 20; // arbitrary

    public Shelf() {
        items = new ArrayList<>();
    }

    public void browse() {
        for (T item : items) {
            if (item.isAvailable()) {
                System.out.println(item);
            }
        }
    }

    public void addItem(T item) {
        if (items.size() < CAPACITY) {
            items.add(item);
        } else {
            System.out.println("Error: Cannot add item, shelf is full.");
        }
    }

    public void removeItem(T item){
        if (items.size() > 0){
            items.remove(item);
            //System.out.println("Item: " + item + " , successfully removed.");  //testing proper removal
        }
    }

    public void borrowItem(T item) {
        if (items.contains(item) && item.isAvailable()) {
            item.setAvailable(false);               // Mark the item as borrowed
            System.out.println("Item borrowed successfully.");
        } else {
            System.out.println("Item is either not available or not found.");
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
