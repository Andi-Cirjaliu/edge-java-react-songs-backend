package application.models;

public class Book {
    int id;
    String title;
    String author;
    int totalRating;
    int ratingCount;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Book(int id, String title, String author, int totalRating, int ratingCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.totalRating = totalRating;
        this.ratingCount = ratingCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    public void rate(int rating){
        this.ratingCount++;
        this.totalRating+=rating;
    }
    
    public double getRating() {
        return (double)this.totalRating/this.ratingCount;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }
}