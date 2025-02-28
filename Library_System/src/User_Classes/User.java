package User_Classes;

import Borrowable_Item_Classes.*;

public abstract class User {
  private String username;
  private String password;
  private double fine;

  // Constructor for the User class
  public User(String username, String password) { // password needed?
    this.username = username;
    this.password = password;
    this.fine = 0;
  }

  // Abstract methods -> Subclass will implement these methods
  public abstract void borrowItem(Borrowable item);
  public abstract void returnItem(Borrowable item);

  
  // Allows user to pay their fine
  public void payFine(double amount) {
    // Reduces fine by amount if there is a fine remaining
    // Prevents negative fines
    if (fine >= amount) {
      fine -= amount;
    } 
    else {
      System.out.println("Amount provided is too large for the current fine. We do not have the change for this transaction.");
      
      System.out.println("Please the pay the approriate amount: " + fine + ".\n");
    }
  }

  // Getters and setters for the fines
  public void setFine(double fine) {
    this.fine = fine;
  }

  public double getFine() {
    return this.fine;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }
}