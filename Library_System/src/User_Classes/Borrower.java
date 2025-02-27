package User_Classes;

import java.util.ArrayList;
import Borrowable_Item_Classes.*;

public class Borrower extends User {
    private ArrayList<Borrowable> borrowedItems;

    public Borrower(String username, String password) {
        super(username, password);
        this.borrowedItems = new ArrayList<Borrowable>();
    }

    public void borrowItem(String request){

    }
    public void returnItem(Borrowable item){
    
    }

}