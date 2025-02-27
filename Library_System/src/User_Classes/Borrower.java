package User_Classes;

import Borrowable_Item_Classes.*;

import java.util.ArrayList;

public class Borrower extends User {
    private ArrayList<Borrowable> borrowedItems;

    public Borrower(String username, String password) {
        super(username, password);
        this.borrowedItems = new ArrayList<Borrowable>();
    }

    public void borrowItem(Borrowable item){

    }
    public void returnItem(Borrowable item){
    
    }

}