package Borrowable_Item_Classes;

import java.util.Date;

public class AudioBook implements Borrowable{
    private String title;
    private String author;
    private String genre;
    private boolean available;
    private Date dueDate;
    private String narrator; // Specific to audiobooks
    private int durationInMinutes; // Duration of the audiobook in minutes
    
    // Constructor for AudioBook object
    public AudioBook(String title, String author, String genre, String narrator, int durationInMinutes) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.narrator = narrator;
        this.durationInMinutes = durationInMinutes;
        this.available = true;
    }

    // Returns the availability of the audiobook in a boolean value
    public boolean isAvailable() {
        return available;
    }

    // This function sets the audiobook to the borrowed state
    // The dueDate is set to 1 week from calling this method, and availability is set to false
    public boolean borrow() {
        if (available) {
            this.available = false;

            // Sets the due date to 1 week after the borrow method is called
            this.dueDate = new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000));
            System.out.println("Audiobook successfully borrowed. The due date is " + dueDate + ".");
            return true;
        }
        return false;
    }

    // This function resets the audiobook to a borrowable state
    // dueDate is reset to null, and availability is set to true
    public boolean returnItem() {
        if (!available) {
            this.available = true;
            this.dueDate = null;
            System.out.println("Audiobook is successfully returned.");
            return true;
        } else {
            System.out.println("You cannot return an unborrowed audiobook.");
        }
        return false;
    }

    // Getter Methods
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getNarrator() {
        return narrator;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    // Setter Methods
    public void setAvailable(boolean val) {
        this.available = val;
    }

    public void setNarrator(String narrator) {
        this.narrator = narrator;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String toString() {
        return "\"" + title + "\" by " + author + " - Genre: " + genre + "\n\tNarrated by: " + narrator + " - Duration (min): " + durationInMinutes;
    }

    @Override
  public boolean equals(Object obj) {
    if (this == obj) {
        return true; // same
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
    AudioBook other = (AudioBook) obj;

    if (this.genre.equals(other.genre)
        && this.author.equals(other.author)
        && this.narrator.equals(other.narrator)
        && this.title.equals(other.title)
        && this.durationInMinutes == other.durationInMinutes) {
        return true;
    }
    return false;
  }
}
