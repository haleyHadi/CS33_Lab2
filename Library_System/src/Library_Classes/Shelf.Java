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

    public void borrowItem() {
        
    }

    public void returnItem() {

    }
}
