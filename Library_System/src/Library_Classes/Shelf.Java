package Library_Classes;

import Borrowable_Item_Classes.*;
import User_Classes.*;

import java.util.ArrayList;

public class Shelf{
    private ArrayList<T> items;
    private int capacity;

    //Shelf Constructor
    public Shelf(){
        items = new ArrayList();
        capacity = 20;          //Arbitrary capacity size
    }

}