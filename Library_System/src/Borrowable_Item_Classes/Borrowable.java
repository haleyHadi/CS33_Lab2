package Borrowable_Item_Classes;

// Interface for borrowable objects like a book, CD, audiobook, etc.
public interface Borrowable {

  // returns a bool value to check if the borrowable is available to borrow
  boolean isAvailable();

  // prints out borrowable information and marks as borrowed
  boolean borrow();

  // unmarks as borrowed for the given item
  boolean returnItem();

  //sets the availability of borrowable items to the parameter value
  void setAvailable(boolean val);

  // Returns the title of the borrowable item
  String getTitle();

  // implement toString for UI
  String toString();

  // override equals function so comparative methods work
  boolean equals(Object obj);
}
