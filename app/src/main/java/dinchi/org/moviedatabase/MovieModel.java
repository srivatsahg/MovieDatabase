package dinchi.org.moviedatabase;

/**
 * Created by Srivatsa on 3/13/2017.
 * Represents a Movie object
 */
public class MovieModel {
    private String title;
    private String rating;
    private String synopsis;
    private String genre;
    private String year;
    private String image;

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
        return year;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
