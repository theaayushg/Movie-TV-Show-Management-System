public class Movie extends TVProgram {
    public Movie(String type, String title, String genre, String country, int releaseYear, String rating, String directors, int duration) {
        super(type, title, genre, country, releaseYear, rating, directors, duration);
    }

    public String getTitle() {
        return super.getTitle(); // pulls the title through get title in tvprogram
    }

    public String getDirectors() {
        return super.getDirectors(); // pulls the title through get director in tvprogram
    }

    public String getCountry() {
        return super.getCountry(); // pulls the title through get country in tvprogram
    }

    public String getRating() {
        return super.getRating(); // pulls the title through get rating in tvprogram
    }

    public int getReleaseYear() {
        return super.getReleaseYear(); // pulls the title through get release year in tvprogram
    }

    public int getDuration() {
        return super.getDuration(); // pulls the title through get duration in tvprogram
    }

    public String getGenre() {
        return super.getGenre(); // pulls the title through get genre in tvprogram
    }
    public void writeOutput(){ // uses writeoutput to print for testcases
        System.out.println("Here is the created Movie"); // title
        System.out.println("Movie Title: " + getTitle()); // movie title
        System.out.println("Movie Director: " + getDirectors()); // movie  director
        System.out.println("Movie Country: " + getCountry()); // movie country
        System.out.println("Movie Release year: " + getReleaseYear()); // movie release year
        System.out.println("Movie Rating: " + getRating()); // movie rating
        System.out.println("Movie Duration: " + getDuration()); // movie duration
        System.out.println("Movie Genre: " + getGenre()); // movie genre

    }
}