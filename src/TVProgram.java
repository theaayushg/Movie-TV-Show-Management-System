public class TVProgram {
    private String title; // creates title string
    private String genre; // creates genre string
    private String country; // creates country string
    private int releaseYear; // creates releaseyear int
    private String rating; // creates rating string
    private String directors; // creates directors string
    private int duration; // creates duration int
    private String type; // type (MOVIE OR TVSHOW)

    public TVProgram(){
        // since excel values are empty for some of these objects, they are initialized to either 0 or blank
        directors = "";
        country = "";
        duration = 0;
    }

    public TVProgram(String type, String title, String genre, String country, int releaseYear, String rating, String directors, int duration) {
        this.type = type; // initializes type
        this.title = title; // initializes title
        this.genre = genre; // initializes genre
        this.country = country; // initializes country
        this.releaseYear = releaseYear; // initializes releaseYear
        this.rating = rating; // initializes rating
        this.directors = directors; // initializes director
        this.duration = duration; // initializes duration
    }

    public String getType() {
        return type; // function to get the type when called
    }

    public void setType(String type) {
        this.type = type; // function to set the type when called
    }
    public String getTitle() {
        return title; // function to get the title when called
    }

    public void setTitle(String title) {
        this.title = title; // function to set the title when called
    }

    public int getDuration() {
        return duration; // function to get the duration when called
    }

    public void setDuration(int duration) {
        this.duration = duration; // function to set the duration when called
    }

    public String getGenre() {
        return genre; // function to get the genre when called
    }

    public void setGenre(String genre) {
        this.genre = genre; // function to set the genre when called
    }

    public String getCountry() {
        return country; // function to get the country when called
    }

    public void setCountry(String country) {
        this.country = country; // function to set the country when called
    }

    public int getReleaseYear() {
        return releaseYear; // function to get the releaseyear when called
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear; // function to set the releaseyear when called
    }

    public String getRating() {
        return rating; // function to get the rating when called
    }

    public void setRating(String rating) {
        this.rating = rating; // function to set the rating when called
    }

    public String getDirectors() {
        return directors; // function to get the director when called
    }

    public void setDirectors(String directors) {
        this.directors = directors; // function to set the director when called
    }
}