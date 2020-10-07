package application.models;

public class Song {
    int id;
    String title;
    String artist;
    String url;
    int totalRating;
    int ratingCount;

    public Song(int id, String title, String artist, String url) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.url = url;
    }

    public Song(int id, String title, String artist, String url, int totalRating, int ratingCount) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.url = url;
        this.totalRating = totalRating;
        this.ratingCount = ratingCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void rate(int rating) {
        this.ratingCount++;
        this.totalRating += rating;
    }

    public double getRating() {
        if ( ratingCount == 0 ) {
            return 0;
        }
        return (double) this.totalRating / this.ratingCount;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
  
}