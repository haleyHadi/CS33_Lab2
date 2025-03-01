package User_Classes;

import Borrowable_Item_Classes.*;

public class Librarian extends User {
  private Library library;  // A reference to the Library system

  public Librarian(String username, String password, Library library) {
    super(username, password);
    this.library = library;
  }

  //Adds a book (borrowable) to the library system - First open shelf (No Sorting)
  public void addItem(Borrowable item) {
    for (Shelf<Borrowable> shelf : library.getShelves()) {
      if (shelf.hasSpace()) {
        shelf.addItem(item); // Adding item to the shelf
        System.out.println("The item has been added to the library.");
        return;
      }
    }
    System.out.println("No available space in the library to add the item.");
  }

  // Removes an item from the library system
    public void removeItem(Borrowable item) {
      boolean itemFound = false;

      // Iterate through each shelf to find the item
      for (Shelf<Borrowable> shelf : library.getShelves()) {
          if (shelf.containsItem(item)) {
              shelf.removeItem(item); // Remove the item from the shelf
              itemFound = true;
              System.out.println("The item has been removed from the library.");
              return;
          }
      }

      if (!itemFound) {
          System.out.println("The item was not found in the library.");
      }
  }

  //Disallow borrowing and returning for librarians to emphasize categorization of Borrower and Librarian
  public void borrowItem(Borrowable item){
    System.out.println("Librarians cannot borrow items for personal use.");
  }
  
  public void returnItem(Borrowable item){
      System.out.println("Librarians cannot return items for personal use.");
  }

}