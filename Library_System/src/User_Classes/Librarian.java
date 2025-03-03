package User_Classes;

import Borrowable_Item_Classes.*;
import Library_Classes.Library;
import exceptions.FullShelfException;

public class Librarian extends User {
  private Library library;  // A reference to the Library system

  public Librarian(String username, String password, Library library) {
    super(username, password);
    this.library = library;
  }

  //Adds a book (borrowable) to the library system - First open shelf (No Sorting)
  public void addItem(Borrowable item) throws FullShelfException{
    if (library.addItem(item))
      System.out.println("The item has been added to the library.");
    else
      System.out.println("No available space in the library to add the item.");
  }

  // Removes an item from the library system
    public void removeItem(Borrowable item) {
    if (library.removeItem(item))
      System.out.println("The item has been removed from the library.");

    else
      System.out.println("The item was not found in the library.");

  }

  //Disallow borrowing and returning for librarians to emphasize categorization of Borrower and Librarian
  public void borrowItem(Borrowable item){
    System.out.println("Librarians cannot borrow items for personal use.");
  }
  
  public Borrowable returnItem(int index) {
      System.out.println("Librarians cannot return items for personal use.");
      return null;
  }

}