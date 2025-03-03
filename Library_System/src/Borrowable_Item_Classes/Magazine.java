package Borrowable_Item_Classes;

import java.util.Date;

public class Magazine implements Borrowable {
    private String title;
    private String genre;
    private boolean available;
    private Date dueDate;
    private int issueNumber; // Specific to magazines
    private Date publicationDate; // Specific to magazines

    // Constructor for Magazine object
    public Magazine(String title, String genre, int issueNumber, Date publicationDate) {
        this.title = title;
        this.genre = genre;
        this.issueNumber = issueNumber;
        this.publicationDate = publicationDate;
        this.available = true;
    }

    // Returns the availability of the magazine in a boolean value
    public boolean isAvailable() {
        return available;
    }

    // This function sets the magazine to the borrowed state
    // The dueDate is set to 1 week from calling this method, and availability is set to false
    public boolean borrow() {
        if (available) {
            this.available = false;

            // Sets the due date to 1 week after the borrow method is called
            this.dueDate = new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000));
            System.out.println("Magazine successfully borrowed. The due date is " + dueDate + ".");
            return true;
        }
        return false;
    }

    // This function resets the magazine to a borrowable state
    // dueDate is reset to null, and availability is set to true
    public boolean returnItem() {
        if (!available) {
            this.available = true;
            this.dueDate = null;
            System.out.println("Magazine successfully returned.");
            return true;
        } else {
            System.out.println("You cannot return an unborrowed magazine.");
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

    public int getIssueNumber() {
        return issueNumber;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    // Setter Methods
    public void setAvailable(boolean val) {
        this.available = val;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String toString() {
        return "\"" + this.title + " - Genre: " + this.genre + "\n\tIssue Number: " + this.issueNumber + " - Publication Date: " + this.publicationDate;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // same
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Magazine other = (Magazine) obj;
    
        if (this.genre.equals(other.genre)
            && this.issueNumber == other.issueNumber
            && this.title.equals(other.title)) {
            return true;
        }
        return false;
    }
}
