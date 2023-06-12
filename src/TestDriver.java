import java.util.Scanner;


public class TestDriver {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in); // new scanner obj
        System.out.println("Would you like a build a TV Show or a Movie?"); // prompts for file name
        String type = scanner.nextLine(); // stores next line input
        System.out.println("Okay we are going to build a " + type);
        System.out.println("Please enter a Title:");
        String title = scanner.nextLine(); // stores next line input
        System.out.println("Please enter a Director:");
        String director = scanner.nextLine(); // stores next line input
        System.out.println("Please enter a Country:");
        String country = scanner.nextLine(); // stores next line input
        System.out.println("Please enter a Release year:");
        String releaseyear = scanner.nextLine(); // stores next line input
        System.out.println("Please enter a Rating:");
        String rating = scanner.nextLine(); // stores next line input
        System.out.println("Please enter a Duration(seasons for shows and minutes for movies):");
        String duration = scanner.nextLine(); // stores next line input
        System.out.println("Please enter a Genre:");
        String genre = scanner.nextLine(); // stores next line input

        int releaseYearInt = Integer.parseInt(releaseyear); // parses int
        int durationInt = Integer.parseInt(duration); // parses int

        TVProgram tvProgramObj = null; // creates tvProgramObj

        if (type.equals("Movie")) {
            Movie movie = new Movie(type, title, genre, country, releaseYearInt, rating, director, durationInt);
            tvProgramObj = movie;
            movie.writeOutput();
        }
        // if movie is selected, create movie obj, make tvProgramObj the movie, and writes the Output


        else {
            TVShow tvShow = new TVShow(type, title, genre, country, releaseYearInt, rating, director, durationInt);
            tvProgramObj = tvShow;
            tvShow.writeOutput();
        }
        // if tv show is selected, create movie obj, make tvProgramObj the movie, and writes the Output

        TVProgramDatabase database = new TVProgramDatabase();
        database.addTVProgram(tvProgramObj);
        // creates database and adds tvProgramObj

        System.out.println("Would you like to change any of these attributes?"); // prints to get response
        String response = scanner.nextLine(); // stores next line input
        if (response.equals("Yes")) { // if changes are necessary
            System.out.println("Type the attribute you would like to change: "); // prints to get response
            String attribute = scanner.nextLine(); // stores next line input
            String oldValue = null; // creates value as null
            switch (attribute.toLowerCase()) {
                case "title": // case if title and sets old val to the tvProgramObj => getTitle method
                    oldValue = tvProgramObj.getTitle();
                    break; // break from statement
                case "director": // case if director and sets old val to the tvProgramObj => getDirectors method
                    oldValue = tvProgramObj.getDirectors();
                    break; // break from statement
                case "country": // case if country and sets old val to the tvProgramObj => getCountry method
                    oldValue = tvProgramObj.getCountry();
                    break; // break from statement
                case "release year": // case if release year and sets old val to the tvProgramObj => getreleaseyear method
                    oldValue = Integer.toString(tvProgramObj.getReleaseYear());
                    break; // break from statement
                case "rating": // case if rating and sets old val to the tvProgramObj => getRating method
                    oldValue = tvProgramObj.getRating();
                    break; // break from statement
                case "duration": // case if duration and sets old val to the tvProgramObj => getDuration method
                    oldValue = Integer.toString(tvProgramObj.getDuration());
                    break; // break from statement
                case "genre": // case if genre and sets old val to the tvProgramObj => getGenre method
                    oldValue = tvProgramObj.getGenre();
                    break; // break from statement
            }

            if (oldValue != null) {
                System.out.println("Old value was: " + oldValue); // else prints old val
            }

            System.out.println("What would you like to change " + attribute + " to?"); // prints changed attribute
            String newValue = scanner.nextLine(); // new line

            //same as above
            switch (attribute.toLowerCase()) {
                case "title":
                    tvProgramObj.setTitle(newValue);
                    break;
                case "director":
                    tvProgramObj.setDirectors(newValue);
                    break;
                case "country":
                    tvProgramObj.setCountry(newValue);
                    break;
                case "release year":
                    tvProgramObj.setReleaseYear(Integer.parseInt(newValue));
                    break;
                case "rating":
                    tvProgramObj.setRating(newValue);
                    break;
                case "duration":
                    tvProgramObj.setDuration(Integer.parseInt(newValue));
                    break;
                case "genre":
                    tvProgramObj.setGenre(newValue);
                    break;
            }

            // Call the writeOutput() method to display the updated information
            if (type.equals("Movie")) {
                ((Movie) tvProgramObj).writeOutput();
            } else {
                ((TVShow) tvProgramObj).writeOutput();
            }

        }
    }
}
