import java.util.Scanner; // imports scanner
import java.io.File; // imports file
import java.io.FileNotFoundException; // imports file not found exception
import java.nio.file.Path; // imports path
import java.nio.file.Paths; // imports paths
import java.util.ArrayList; // imports array list
import java.util.List; // imports list
import java.util.Set; // imports set
import java.util.HashSet; // imports hash set
import java.io.*; // imports io
import java.nio.file.*; // imports path
import java.io.FileWriter;
import java.util.*; // imports util

//main file for sprint 3
public class TestDriver5 {
    private static Set<String> uniqueRatings = new HashSet<>(); // creates hash set for uni ratings
    private static Set<String> uniqueDirectors = new HashSet<>(); // creates hash set for uni directors
    private static Set<String> uniqueGenres = new HashSet<>(); // creates hash set for uni genres
    private static Set<String> uniqueCountries = new HashSet<>(); // creates hash set for uni countries
    private static Set<Integer> uniqueYears = new HashSet<>(); // creates hash set for uni years

    public static void removeLineFromFile(String fileName, int lineNumberToRemove) { // removes line from file
        Path filePath = Paths.get(fileName); // gets file path
        List<String> fileContents; // creates list for file contents
        try { // try
            fileContents = Files.readAllLines(filePath); // reads all lines
            // Check if the lineNumberToRemove is valid
            if (lineNumberToRemove >= 0 && lineNumberToRemove < fileContents.size()) { // if line number to remove is valid
                fileContents.remove(lineNumberToRemove); // removes line number to remove
                Files.write(filePath, fileContents); // writes file contents
            } else { // else
                System.out.println("Invalid line number. No lines were removed."); // prints invalid line number
            }
        } catch (IOException e) { // catch
            e.printStackTrace(); // prints stack trace
        }
    }
    public static boolean processInput(Scanner scanner, List<TVProgram> tvPrograms, int counterTrack, List<Integer> originalIndices, String filename, TVProgramDatabase database) {
        boolean shouldExit = false; // boolean for exit
        System.out.println("Hit spacebar to see more, or type the number of title you would like to remove"); // prints hit spacebar
        String userInput = scanner.nextLine(); // gets user input
        if (userInput.trim().isEmpty()) { // if user input is empty
            return false; // returns false
        } else { // else
            try { // try
                int selectedTitleToRemove = Integer.parseInt(userInput); // gets title to remove
                if (selectedTitleToRemove > 0 && selectedTitleToRemove <= originalIndices.size()) { // if title to remove is valid
                    int originalIndex = originalIndices.get(selectedTitleToRemove - 1); // gets original index
                    TVProgram tvProgramToRemove = tvPrograms.get(originalIndex); // gets program to remove
                    System.out.println("Title: " + tvProgramToRemove.getTitle() + " will be deleted now"); // prints title
                    removeLineFromFile(filename, originalIndex); // removes line from file
                    database.removeTVProgram(tvProgramToRemove); // removes program from database
                    shouldExit = true; // sets exit to true
                } else { // else
                    System.out.println("Invalid input"); // prints invalid input
                }
            } catch (NumberFormatException e) { // catch
                System.out.println("Invalid input. Please enter a valid number."); // prints invalid input
            }
        }
        return shouldExit; // returns exit
    }
    public static void updateRatingInFile(String fileName, int lineNumberToUpdate, String newRating) {
        Path filePath = Paths.get(fileName);
        List<String> fileContents;
        try {
            fileContents = Files.readAllLines(filePath);
            if (lineNumberToUpdate >= 0 && lineNumberToUpdate < fileContents.size()) {
                String originalLine = fileContents.get(lineNumberToUpdate);
                String[] parts = originalLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                parts[5] = newRating;
                String updatedLine = String.join(",", parts);
                fileContents.set(lineNumberToUpdate, updatedLine);
                Files.write(filePath, fileContents);
            } else {
                System.out.println("Invalid line number. Rating not updated.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean newprocessInput(Scanner scanner, List<TVProgram> tvPrograms, int counterTrack, List<Integer> originalIndices, String filename, TVProgramDatabase database) {
        boolean shouldExit = false;
        System.out.println("Hit spacebar to see more, or type the number of title you would like to change the rating for");
        String userInput = scanner.nextLine();
        if (userInput.trim().isEmpty()) {
            return false;
        } else {
            try {
                int selectedTitleToUpdate = Integer.parseInt(userInput);
                if (selectedTitleToUpdate > 0 && selectedTitleToUpdate <= originalIndices.size()) {
                    int originalIndex = originalIndices.get(selectedTitleToUpdate - 1);
                    TVProgram tvProgramToUpdate = tvPrograms.get(originalIndex);
                    System.out.println("Title: " + tvProgramToUpdate.getTitle() + " - Current Rating: " + tvProgramToUpdate.getRating());
                    System.out.print("Enter the new rating: ");
                    String newRating = scanner.nextLine();

                    updateRatingInFile(filename, originalIndex, newRating); // Update the rating in the file
                    tvProgramToUpdate.setRating(newRating); // Update the rating in the database
                    shouldExit = true;
                } else {
                    System.out.println("Invalid input");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return shouldExit;
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
                        try {
                            FileWriter writer = new FileWriter(filename, true); // create FileWriter object with "append" mode
                            Scanner adder = new Scanner(System.in); // Scanner obj
                            System.out.print("Enter the Type (Movie or TV Show): "); // prints prompt
                            String first = adder.nextLine(); // next line string
                            System.out.print("Enter the title: "); // prints prompt
                            String second = adder.nextLine(); // next line string
                            System.out.print("Enter the director: "); // prints prompt
                            String third = adder.nextLine(); // next line string
                            System.out.print("Enter the country: "); // prints prompt
                            String fourth = adder.nextLine(); // next line string
                            System.out.print("Enter the release year: "); // prints prompt
                            String fifth = adder.nextLine(); // next line string
                            System.out.print("Enter the rating: "); // prints prompt
                            String sixth = adder.nextLine(); // next line string
                            System.out.print("Enter the duration(min if Movie or Seasons if Show): "); // prints prompt
                            String seventh = adder.nextLine(); // next line string
                            System.out.print("Enter the genres(Separated by \"\"): "); // prints prompt
                            String eighth = adder.nextLine(); // next line string
                            String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s\n", first, second, third, fourth, fifth, sixth, seventh, eighth);
                            writer.write(line);
                            writer.close();
                            TVProgram newTVProgram = new TVProgram(first, second, eighth, fourth, Integer.parseInt(fifth), sixth, third, Integer.parseInt(seventh));
                            database.addTVProgram(newTVProgram);
                            //Movie,Jaws 5,Joe Alves,United States,2010,TV-MA,98,Thriller
                        }catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                        break; // To implement later for Adding Titles
                    case "2":
                        System.out.println("The list of all the Movies will show first, followed by the list of shows"); // prints prompt
                        int counterTrack = 1; // counter
                        int tracker = 10; // tracker
                        int index = 0; // index
                        boolean shouldExit = false; // boolean for exit
                        List<TVProgram> tvPrograms = database.getTVPrograms(); // gets tv programs
                        Scanner scanner = new Scanner(System.in); // Scanner obj
                        List<Integer> originalIndices = new ArrayList<>(); // List of original indices

                        // Loop for Movies
                        while (index < tvPrograms.size() && !shouldExit) { // while index is less than size of tvPrograms and shouldExit is false
                            TVProgram title = tvPrograms.get(index); // gets title
                            if (title.getType().equalsIgnoreCase("Movie")) { // if type is movie
                                System.out.println(counterTrack + ". " + title.getTitle()); // prints title
                                originalIndices.add(index); // add index to list of original indices
                                counterTrack++; // increment counter
                                tracker--; // decrement tracker

                                if (tracker == 0 || index == tvPrograms.size() - 1) { // if tracker is 0 or index is the last index
                                    shouldExit = processInput(scanner, tvPrograms, counterTrack, originalIndices, filename, database); // process input
                                    tracker = 10; // Reset tracker
                                }
                            }
                            index++; // increment index
                        }

                        // Reset index for TV Shows
                        index = 0; // Reset index
                        counterTrack = 1; // Reset counter
                        tracker = 10; // Reset tracker
                        originalIndices.clear(); // Clear the list of original indices

                        // Loop for TV Shows
                        while (index < tvPrograms.size() && !shouldExit) { // while index is less than the size of the list and shouldExit is false
                            TVProgram title = tvPrograms.get(index); // get title
                            if (title.getType().equalsIgnoreCase("TV Show")) { // if type is TV Show
                                System.out.println(counterTrack + ". " + title.getTitle()); // print title
                                originalIndices.add(index); // add index to original indices
                                counterTrack++; // increment counter
                                tracker--; // decrement tracker

                                if (tracker == 0 || index == tvPrograms.size() - 1) { // if tracker is 0 or index is at the end of the list
                                    shouldExit = processInput(scanner, tvPrograms, counterTrack, originalIndices, filename, database); // process input
                                    tracker = 10; // reset tracker
                                }
                            }
                            index++; // increment index
                        }

                        System.out.println(""); // empty line
                        break; // break for delete case

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
                                }}
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
                            }} else {
                            System.out.println("Invalid input."); // invalid input
                        }
                        break;
                    case "4":
                        System.out.println("The list of all the Movies will show first, followed by the list of shows"); // prints prompt
                        int newcounterTrack = 1; // counter
                        int newtracker = 10; // tracker
                        int newindex = 0; // index
                        boolean newshouldExit = false; // boolean for exit
                        List<TVProgram> newtvPrograms = database.getTVPrograms(); // gets tv programs
                        Scanner newscanner = new Scanner(System.in); // Scanner obj
                        List<Integer> neworiginalIndices = new ArrayList<>(); // List of original indices

                        // Loop for Movies
                        while (newindex < newtvPrograms.size() && !newshouldExit) { // while index is less than size of tvPrograms and shouldExit is false
                            TVProgram title = newtvPrograms.get(newindex); // gets title
                            if (title.getType().equalsIgnoreCase("Movie")) { // if type is movie
                                System.out.println(newcounterTrack + ". " + title.getTitle()); // prints title
                                neworiginalIndices.add(newindex); // add index to list of original indices
                                newcounterTrack++; // increment counter
                                newtracker--; // decrement tracker

                                if (newtracker == 0 || newindex == newtvPrograms.size() - 1) { // if tracker is 0 or index is the last index
                                    newshouldExit = newprocessInput(newscanner, newtvPrograms, newcounterTrack, neworiginalIndices, filename, database); // process input
                                    newtracker = 10; // Reset tracker
                                }
                            }
                            newindex++; // increment index
                        }

                        // Reset index for TV Shows
                        index = 0; // Reset index
                        counterTrack = 1; // Reset counter
                        tracker = 10; // Reset tracker
                        neworiginalIndices.clear(); // Clear the list of original indices

                        // Loop for TV Shows
                        while (index < newtvPrograms.size() && !newshouldExit) { // while index is less than the size of the list and shouldExit is false
                            TVProgram title = newtvPrograms.get(index); // get title
                            if (title.getType().equalsIgnoreCase("TV Show")) { // if type is TV Show
                                System.out.println(counterTrack + ". " + title.getTitle()); // print title
                                neworiginalIndices.add(index); // add index to original indices
                                counterTrack++; // increment counter
                                tracker--; // decrement tracker

                                if (tracker == 0 || index == newtvPrograms.size() - 1) { // if tracker is 0 or index is at the end of the list
                                    newshouldExit = newprocessInput(newscanner, newtvPrograms, counterTrack, neworiginalIndices, filename, database); // process input
                                    tracker = 10; // reset tracker
                                }
                            }
                            index++; // increment index
                        }

                        System.out.println(""); // empty line
                        break;
                    default:
                        System.out.println("Invalid input."); // invalid input
                }
                System.out.println("1. Add a Title"); // sprint 4
                System.out.println("2. Delete a Title"); // sprint 3
                System.out.println("3. Search for Titles"); // sprint 2
                System.out.println("4. Modify a Title"); // sprint 5
                System.out.println("Type Exit to end program"); // sprint 1
                use_case = input.nextLine(); // next line input
            }
        } catch (FileNotFoundException e) { // exception e
            System.out.println("Error: File not found"); // if error is found
        }
        input.close(); // close inputs
    }}