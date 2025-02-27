package Library_Classes;

import Borrowable_Item_Classes.*;
import User_Classes.*;

import java.util.ArrayList;

//I don't know if this right or not
//gonna need some help here!!!

public class Library{
    private ArrayList<Book> books;
    
    private ArrayList<User> users; //We can use type casting to add Librarians and Borrowers (I think)

    // Library constructor
    public Library(){
        books = new ArrayList();
        users = new ArrayList();
    }

    //adds a book to the list of books in the library list (??) 
    public void addBook(Book book){
        books.add(book);
    }
    //maybe add the book to the shelf instead?



}