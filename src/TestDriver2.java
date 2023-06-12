import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.io.*;
import java.nio.file.*;
import java.util.*;


public class TestDriver2 {
    private static Set<String> uniqueRatings = new HashSet<>(); // creates hash set for uni ratings
    private static Set<String> uniqueDirectors = new HashSet<>(); // creates hash set for uni directors
    private static Set<String> uniqueGenres = new HashSet<>(); // creates hash set for uni genres
    private static Set<String> uniqueCountries = new HashSet<>(); // creates hash set for uni countries
    private static Set<Integer> uniqueYears = new HashSet<>(); // creates hash set for uni years

    public static void removeLineFromFile(String fileName, int lineNumberToRemove) {
        Path filePath = Paths.get(fileName);
        List<String> fileContents;
        try {
            fileContents = Files.readAllLines(filePath);

            // Check if the lineNumberToRemove is valid
            if (lineNumberToRemove >= 0 && lineNumberToRemove < fileContents.size()) {
                fileContents.remove(lineNumberToRemove);
                Files.write(filePath, fileContents);
            } else {
                System.out.println("Invalid line number. No lines were removed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isProgramMatching(TVProgram program, int attributeChoice1, String value1, String type_of_ent) {
        switch (attributeChoice1) { // attribute choice
            case 1:
                if (!program.getRating().equals(value1)) { // gets rating for value1
                    return false; // returns false
                }
                break; // breaks
            case 2:
                if (!program.getDirectors().contains(value1)) { // gets directors for value1
                    return false; // returns false
                }
                break; // breaks
            case 3:
                if (!program.getGenre().contains(value1)) {  // gets genre for value1
                    return false; // returns false
                }
                break; // breaks
            case 4:
                int duration = program.getDuration(); // gets duration for value 1
                int[] movieRanges = {30, 60, 90, 120, 150, 180}; // range of vals for movies
                int[] showRanges = {1, 2, 3, 4, 5, 6}; // range of vals for shows

                int[] ranges = type_of_ent.equalsIgnoreCase("Movie") ? movieRanges : showRanges; // decides what type
                int selectedRangeIndex = Integer.parseInt(value1) - 1; // helps with parsing and integer parsing

                if (selectedRangeIndex == 0 && duration > ranges[selectedRangeIndex]) { // if range > 0 and duration > ranges
                    return false; // returns false
                } else if (selectedRangeIndex == ranges.length - 1 && duration <= ranges[selectedRangeIndex - 1]) { // if range == range - 1 and duration < ranges
                    return false; // returns false
                } else if (duration <= ranges[selectedRangeIndex - 1] || duration > ranges[selectedRangeIndex]) { // if duration < ranges and duration > ranges
                    return false; // returns false
                }
                break; // breaks
            case 5:
                if (!program.getCountry().contains(value1)) { // if country contains val
                    return false; // false
                }
                break; // breaks
            case 6:
                if (program.getReleaseYear() != Integer.parseInt(value1)) { // if not the same
                    return false; // returns false
                }
                break; // breaks
            default:
                return false; // return false
        }
        return true; // else return true
    }

    private static void handleAttributeChoice(int attributeChoice, Scanner input, Set<String> uniqueRatings, Set<String> uniqueDirectors, Set<String> uniqueGenres, Set<String> uniqueCountries, Set<Integer> uniqueYears, TVProgramDatabase database, String type_of_ent) {
        switch (attributeChoice) { // attribute switch case
            case 1: // case 1
                System.out.println("Please select a rating:"); // prints ratings
                for (String rating : uniqueRatings) { // for loop for ratings
                    System.out.println(rating); // prints ratings
                }
                break; // breaks
            case 2:
                System.out.println("Please select a director:"); // prints director
                for (String director : uniqueDirectors) { // for loop for directors
                    System.out.println(director); // prints director
                }
                break; // breaks
            case 3:
                System.out.println("Please select a genre:"); // prints genre
                for (String genre : uniqueGenres) { // for loop for genres
                    System.out.println(genre); // prints genre
                }
                break; // breaks
            case 4:
                if (type_of_ent.equalsIgnoreCase("Movie")) { // if movie
                    System.out.println("Please select a duration range for movies:"); // prints the prompt
                    System.out.println("1. 0-30 minutes"); // 0-30 mins
                    System.out.println("2. 31-60 minutes"); // 31-60 mins
                    System.out.println("3. 61-90 minutes"); // 61-90 mins
                    System.out.println("4. 91-120 minutes"); // 91-120 mins
                    System.out.println("5. 121-150 minutes"); // 121-150 mins
                    System.out.println("6. 151-180 minutes"); // 151-180 mins
                    System.out.println("7. 180+ minutes"); // 180> mins
                } else if (type_of_ent.equalsIgnoreCase("TV Show")) { // if tv show
                    System.out.println("Please select a duration range for TV shows:"); // prints the prompt
                    System.out.println("1. 0-1 seasons"); // 0-1 seasons
                    System.out.println("2. 1-2 seasons"); // 1-2 seasons
                    System.out.println("3. 2-3 seasons"); // 2-3 seasons
                    System.out.println("4. 3-4 seasons"); // 3-4 seasons
                    System.out.println("5. 4-5 seasons"); // 4-5 seasons
                    System.out.println("6. 5-6 seasons"); // 5-6 seasons
                    System.out.println("7. 6+ seasons"); // 6+ seasons
                }
                break; // breaks
            case 5:
                System.out.println("Please select a country:"); // prints country
                for (String country : uniqueCountries) { // for loop
                    System.out.println(country); // prints country
                }
                break; // breaks
            case 6:
                System.out.println("Please select a year:"); // prints year
                for (Integer year : uniqueYears){ // for loop
                    System.out.println(year); // prints year
                }
                break; // breaks
            default:
                System.out.println("Invalid input"); // ELSE invalid input
        }
    }

    public static void main(String[] args) {
        // ... (The code remains the same as before until the following line inside the "case "3":")
        System.out.print("Please enter the name of the input file: "); // name of file input
        Scanner input = new Scanner(System.in); // scanner obj
        String filename = input.nextLine(); // input line prompt
        Path path = Paths.get(filename); // gets file name locally
        Path absPath = path.toAbsolutePath(); // gets abspath to abs path
        File file = new File(absPath.toUri()); // creates path using uri path

        TVProgramDatabase database = new TVProgramDatabase(); // adds database program for this file

        try {
            Scanner fileInput = new Scanner(file); // scanner input
            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine(); // next line input
                String[] attributes = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); // edge case detection and fixation
                String type = attributes[0]; // sets type to zero attribute
                String title = attributes[1]; // sets title to first attribute

                String director = " "; // default value
                if (!attributes[2].isEmpty()) {
                    director = attributes[2]; // sets director to second attribute
                }

                String country = " "; // default value
                if (!attributes[3].isEmpty()) {
                    country = attributes[3]; // sets country to third attribute
                }
                String rating = " "; // default value
                if (!attributes[5].isEmpty()) {
                    rating = attributes[5]; // sets rating to fifth attribute
                }

                int releaseYear = 0; // release year default value
                if (!attributes[4].isEmpty()) { // release year error handling
                    try {
                        releaseYear = Integer.parseInt(attributes[4]);
                    } catch (NumberFormatException e) {
                        // System.out.println("Error: Invalid release year in file");
                    }
                }

                String duration_string_orig = attributes[6]; // duration is set as the 6 attribute
                int duration = 0; // default val
                if (!duration_string_orig.isEmpty()) {
                    try {
                        String stripped = duration_string_orig.replaceAll("[^0-9]", "");
                        duration = Integer.parseInt(stripped); // stripped down

                    } catch (NumberFormatException e) {
                        // System.out.println("Error: Invalid duration in file");
                    }
                }

                String genre = attributes[7]; // sets attribute 7 as genre

                TVProgram entertainment = new TVProgram(type, title, genre, country, releaseYear, rating, director, duration);
                // adds entertainment for given program
                database.addTVProgram(entertainment); // adds program

                uniqueRatings.add(rating); // adds uni rating for rating
                uniqueDirectors.add(director); // adds uni director for director
                uniqueGenres.add(genre); // adds uni genre for genre
                uniqueCountries.add(country); // adds uni country for country
                uniqueYears.add(releaseYear); // adds uni values for release year
            }
            fileInput.close();

            System.out.println("1. Add a Title"); // later implementation
            System.out.println("2. Delete a Title"); // later implementation
            System.out.println("3. Search for Titles"); // current sprint
            System.out.println("4. Modify a Title"); // later implementation
            System.out.println("Type Exit to end program"); // exit case
            String use_case = input.nextLine();
            while (!use_case.equals("Exit")) { // exit case
                switch (use_case) {
                    case "1":
                        break; // To implement later for Adding Titles
                    case "2":
                        System.out.println("The list of all the Movies will show first, followed by the list of shows");
                        int counterTrack = 1;
                        int tracker = 10;
                        int index = 0;
                        boolean shouldExit = false;
                        List<TVProgram> tvPrograms = database.getTVPrograms();
                        Scanner scanner = new Scanner(System.in);
                        int movieCounter = 0; // Keep track of the number of displayed movies

                        // Loop for Movies
                        while (index < tvPrograms.size() && !shouldExit) {
                            TVProgram title = tvPrograms.get(index);
                            if (title.getType().equalsIgnoreCase("Movie")) {
                                System.out.println(counterTrack + ". " + title.getTitle());
                                counterTrack++;
                                movieCounter++; // Increment movieCounter each time a movie is displayed
                                tracker--;

                                if (tracker == 0) {
                                    System.out.println("Hit spacebar to see more, or type the number of title you would like to remove");
                                    String userInput = scanner.nextLine();

                                    if (userInput.trim().isEmpty()) {
                                        tracker = 10;
                                    } else {
                                        try {
                                            int selectedTitleToRemove = Integer.parseInt(userInput);

                                            if (selectedTitleToRemove > 0 && selectedTitleToRemove < 50) {
                                                if (selectedTitleToRemove > 0 && selectedTitleToRemove <= counterTrack) {
                                                    TVProgram tvProgramToRemove = tvPrograms.get(selectedTitleToRemove - 1);
                                                    int lineNumberToRemove = selectedTitleToRemove - 1; // Adjust this based on how the text file is structured
                                                    removeLineFromFile(filename, lineNumberToRemove);
                                                    database.removeTVProgram(tvProgramToRemove); // Remove from the in-memory list
                                                    counterTrack--; // Update the counterTrack after removal
                                                    shouldExit = true;
                                                } else {
                                                    System.out.println("Invalid input");
                                                }
                                            } else {
                                                shouldExit = true;
                                            }
                                        } catch (NumberFormatException e) {
                                            System.out.println("Invalid input. Please enter a valid number.");
                                        }
                                    }
                                }
                            }
                            index++;
                        }

                        // Reset index for TV Shows
                        index = 0;
                        counterTrack = counterTrack - movieCounter; // Reset counterTrack to 1

                        // Loop for TV Shows
                        while (index < tvPrograms.size() && !shouldExit) {
                            TVProgram title = tvPrograms.get(index);
                            if (title.getType().equalsIgnoreCase("TV Show")) {
                                System.out.println(counterTrack + ". " + title.getTitle());
                                counterTrack++;
                                tracker--;

                                if (tracker == 0) {
                                    System.out.println("Hit spacebar to see more, or type the number of title you would like to remove");
                                    String userInput = scanner.nextLine();

                                    if (userInput.trim().isEmpty()) {
                                        tracker = 10;
                                    } else {
                                        try {
                                            int selectedTitleToRemove = Integer.parseInt(userInput);

                                            if (selectedTitleToRemove > 0 && selectedTitleToRemove < 50) {
                                                if (selectedTitleToRemove > 0 && selectedTitleToRemove <= counterTrack) {
                                                    TVProgram tvProgramToRemove = tvPrograms.get(selectedTitleToRemove - 1 + movieCounter); // Add the movieCounter value
                                                    int lineNumberToRemove = selectedTitleToRemove - 1 + movieCounter; // Add the movieCounter value
                                                    removeLineFromFile(filename, lineNumberToRemove);
                                                    database.removeTVProgram(tvProgramToRemove); // Remove from the in-memory list
                                                    counterTrack--; // Update the counterTrack after removal
                                                    shouldExit = true;
                                                } else {
                                                    System.out.println("Invalid input");
                                                }
                                            } else {
                                                shouldExit = true;
                                            }
                                        } catch (NumberFormatException e) {
                                            System.out.println("Invalid input. Please enter a valid number.");
                                        }
                                    }
                                }
                            }
                            index++;
                        }

                        System.out.println("");
                        break; // To implement later for Deleting Titles

                    case "3":
                        System.out.println("Are you looking for a Movie or TV Show?"); // prompt for movie or show
                        String type_of_ent = input.nextLine(); // next line input
                        if (type_of_ent.equalsIgnoreCase("Movie") || type_of_ent.equalsIgnoreCase("TV Show")) { // asks for show or movie
                            System.out.println("Which attribute are you searching based on?"); // asks for search attribute
                            System.out.println("1. Rating"); // rating prompt
                            System.out.println("2. Director"); // director prompt
                            System.out.println("3. Genre"); // genre prompt
                            System.out.println("4. Duration"); // duration prompt
                            System.out.println("5. Country"); // country prompt
                            System.out.println("6. Year"); // year prompt

                            int attributeChoice1 = input.nextInt(); // next line int
                            input.nextLine(); // next line input

                            handleAttributeChoice(attributeChoice1, input, uniqueRatings, uniqueDirectors, uniqueGenres, uniqueCountries, uniqueYears, database, type_of_ent);
                            // handles attribute choice for given attribute

                            System.out.println("Please enter the first attribute value:"); // prompt
                            String value1 = input.nextLine(); // input value for attribute

                            List<TVProgram> results = new ArrayList<>(); // creates new array list
                            for (TVProgram program : database.getTVPrograms()) { // for loop
                                if (program.getType().equalsIgnoreCase(type_of_ent) && isProgramMatching(program, attributeChoice1, value1, type_of_ent)) {
                                    results.add(program); // adds program
                                }
                            }

                            // ... (The rest of the code remains the same as before)
                            if (results.isEmpty()) {
                                System.out.println("No matching programs found."); // none found
                            } else {
                                System.out.println("Matching programs:"); // prints matching programs
                                int count = 1; // Initialize the counter
                                for (TVProgram result : results) { // for loop
                                    System.out.println(count + ". " + result.getTitle()); // gets title from database with the counter
                                    count++; // Increment the counter
                                }
                                System.out.println("");
                            }
                        } else {
                            System.out.println("Invalid input."); // invalid input
                        }
                        break;
                    case "4":
                        break; // To implement later for Modifying Titles
                    default:
                        System.out.println("Invalid input."); // invalid input
                }
                System.out.println("1. Add a Title");
                System.out.println("2. Delete a Title");
                System.out.println("3. Search for Titles"); // sprint 2
                System.out.println("4. Modify a Title");
                System.out.println("Type Exit to end program");
                use_case = input.nextLine(); // next line input
            }
        } catch (FileNotFoundException e) { // exception e
            System.out.println("Error: File not found"); // if error is found
        }

        input.close(); // close inputs
    }
}