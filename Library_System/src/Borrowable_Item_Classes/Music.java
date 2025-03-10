package Borrowable_Item_Classes;

import java.util.Date;

public class Music implements Borrowable {
    private String title;
    private String genre;
    private String artist; // Specific to music
    private boolean available;
    private Date dueDate;
    private int durationInMinutes; // Duration of the music in minutes
    private Date releaseDate;

    // Constructor for Music object
    public Music(String title, String genre, String artist, int durationInMinutes, Date releaseDate) {
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.durationInMinutes = durationInMinutes;
        this.releaseDate = releaseDate;
        this.available = true;
    }

    // Returns the availability of the music in a boolean value
    public boolean isAvailable() {
        return available;
    }

    // This function sets the music to the borrowed state
    // The dueDate is set to 1 week from calling this method, and availability is set to false
    public boolean borrow() {
        if (available) {
            this.available = false;

            // Sets the due date to 1 week after the borrow method is called
            this.dueDate = new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000));
            System.out.println("Music successfully borrowed. The due date is " + dueDate + ".");
            return true;
        } else {
            System.out.println("Music is unavailable, it is already borrowed.");
        }
        return false;
    }

    // This function resets the music to a borrowable state
    // dueDate is reset to null, and availability is set to true
    public boolean returnItem() {
        if (!available) {
            this.available = true;
            this.dueDate = null;
            System.out.println("Music successfully returned.");
            return true;
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

    public String getArtist() {
        return artist;
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

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public String toString() {
        return "\"" + this.title + "\" by " + this.artist + " - Genre: " + this.genre + "\n\tReleased on: " + this.releaseDate + " - Duration (min): " + this.durationInMinutes;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // same
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Music other = (Music) obj;
    
        if (this.genre.equals(other.genre)
            && this.artist.equals(other.artist)
            && this.title.equals(other.title)
            && this.durationInMinutes == other.durationInMinutes) {
            return true;
        }
        return false;
    }
}
