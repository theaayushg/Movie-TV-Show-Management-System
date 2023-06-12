import java.util.ArrayList;
import java.util.List;

public class TVProgramDatabase {
    private List<TVProgram> tvPrograms; // creates list program for tvPrograms

    public TVProgramDatabase() {
        tvPrograms = new ArrayList<>(); // creates new Array List
    }

    public void addTVProgram(TVProgram tvProgram) {
        tvPrograms.add(tvProgram); // adds program to array list
    }

    public void removeTVProgram(TVProgram tvProgram) { // removes program from array list
        tvPrograms.remove(tvProgram); // removes program from array list
    }

    public TVProgram getTVProgramByTitle(String title) {
        for (TVProgram tvProgram : tvPrograms) { // for loop to go through array list
            if (tvProgram.getTitle().equals(title)) { // if program title in database, it returns it
                return tvProgram; // returns program
            }
        }
        return null; // else null
    }

    public List<TVProgram> getTVProgramsByGenre(String genre) {
        List<TVProgram> matchingPrograms = new ArrayList<>(); // matching for arraylist
        for (TVProgram tvProgram : tvPrograms) { // if program genre in database, it returns it
            if (tvProgram.getGenre().equals(genre)) { // if the same
                matchingPrograms.add(tvProgram); // adds matching program
            }
        }
        return matchingPrograms; // returns matching programs
    }

    public List<TVProgram> getTVProgramsByDirector(String director) {
        List<TVProgram> matchingPrograms = new ArrayList<>(); // matching for arraylist
        for (TVProgram tvProgram : tvPrograms) { // if program director in database, it returns it
            if (tvProgram.getDirectors().equals(director)) { // if the same
                matchingPrograms.add(tvProgram); // adds matching program
            }
        }
        return matchingPrograms; // returns matching programs
    }
    public List<TVProgram> getTVPrograms() {
        return new ArrayList<>(tvPrograms); // if program tvprograms in database, it returns it
    }
}