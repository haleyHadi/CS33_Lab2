package Borrowable_Item_Classes;

import java.util.Date;

public class Movie implements Borrowable {
    private String title;
    private String genre;
    private String director; // Specific to movies
    private boolean available;
    private Date dueDate;
    private int durationInMinutes; // Duration of the movie in minutes
    private Date releaseDate;

    // Constructor for Movie object
    public Movie(String title, String genre, String director, int durationInMinutes, Date releaseDate) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.durationInMinutes = durationInMinutes;
        this.releaseDate = releaseDate;
        this.available = true;
    }

    // Returns the availability of the movie in a boolean value
    public boolean isAvailable() {
        return available;
    }

    // This function sets the movie to the borrowed state
    // The dueDate is set to 1 week from calling this method, and availability is set to false
    public boolean borrow() {
        if (available) {
            this.available = false;

            // Sets the due date to 1 week after the borrow method is called
            this.dueDate = new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000));
            System.out.println("Movie successfully borrowed. The due date is " + dueDate + ".");
            return true;
        } else {
            System.out.println("Movie is unavailable, it is already borrowed.");
        }
        return false;
    }

    // This function resets the movie to a borrowable state
    // dueDate is reset to null, and availability is set to true
    public boolean returnItem() {
        if (!available) {
            this.available = true;
            this.dueDate = null;
            System.out.println("Movie successfully returned.");
            return true;
        } else {
            System.out.println("You cannot return an unborrowed movie.");
        }
        return false;
    }

    // Getter Methods
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    // Setter Methods
    public void setAvailable(boolean val) {
        this.available = val;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
