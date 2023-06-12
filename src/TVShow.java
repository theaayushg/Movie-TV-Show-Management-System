public class TVShow extends TVProgram {
    public TVShow(String type, String title, String genre, String country, int release_year, String rating, String director,  int duration) { // creates titles
        super(type,title, genre, country, release_year, rating, director, duration); // uses super to initialize them to public show vars
    }
    public String getTitle() {
        return super.getTitle(); // pulls the title through get title in tvprogram
    }

    public String getDirector() {
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
        System.out.println("Here is the created TV Show"); // title
        System.out.println("TV Show Title: " + getTitle()); // tv show title
        System.out.println("TV Show Director: " + getDirectors()); // tv show director
        System.out.println("TV Show Country: " + getCountry()); // tv show country
        System.out.println("TV Show Release year: " + getReleaseYear()); // tv show release year
        System.out.println("TV Show Rating: " + getRating()); // tv show rating
        System.out.println("TV Show Duration: " + getDuration()); // tv show duration
        System.out.println("TV Show Genre: " + getGenre()); // tv show genre
    }
}