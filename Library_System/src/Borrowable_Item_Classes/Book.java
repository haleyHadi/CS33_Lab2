package Borrowable_Item_Classes;

import java.util.Date;

public class Book implements Borrowable{
    private String title;
    private String author;
    private String genre;
    private boolean available;
    private Date dueDate;


    //Constructor for Book object -> Sets all innate properties to parameter values
    public Book(String title, String author, String genre){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = true;
    }

    //returns the availability of a book in a boolean value
    public boolean isAvailable(){
        return available;
    }

    //this function sets the book to the borrowed state
    //dueDate is set to 1 week from calling this method and availability is set to false
    public boolean borrow(){
        if(available){
            this.available = false;

            //sets the due date to 1 week after the borrow method is called
            this.dueDate = new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 *1000));
            System.out.println("Book succesfully borrowed. The due date is " + dueDate + ".");
            
            return true;
        }

        return false;
    }

    // this function resets the book to a borrowable state
    //dueDate is reset to nothing and availability is set to true

    //need to update this functionality -> Check for due date before setting null. If late need to pay fees. 
    public boolean returnItem(){
        if (!available){
            this.available = true;
            this.dueDate = null;
            System.out.println("Book is succesfully returned.");
            return true;
        }
        else{
            System.out.println("You cannot return an unborrowed book.");
        }

        return false;
    }


    //Getter Methods
    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getGenre(){
        return genre;
    }

    public Date dueDate(){
        return dueDate;
    }


    //Setter Methods
    public void setAvailable(boolean val){
        this.available = val;
    }
    
    public String toString() {
        return "\"" + title + "\" by " + author + " - Genre: " + genre;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // same
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Book other = (Book) obj;
    
        if (this.genre.equals(other.genre)
            && this.author.equals(other.author)
            && this.title.equals(other.title)) {
            return true;
        }
        return false;
    }
}
