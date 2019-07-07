import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
/**
 * 
 * @author Robbie CS 121 section 035
 *The Jukebox Hero tool will allow the user to open a music catalog file, 
 *print stats about the catalog (number of songs, number of artists, number of albums,
 * total play time), search for songs in the catalog, and display the entire catalog.

 */
public class JukeboxHero {
	// main Menu Prompts....
	private static String menuBorder = "*****************************";
	private static String menuDivider = "---------------------------------";
	private static String menuPrompt = "*      Program Menu         *";
	private static String menuSelectionPrompt = "(L)oad catalog\n(S)earch catalog\n(A)nalyse catalog\n(P)rint catalog\n(Q)uit\n";

	// General Purpose Prompts...
	private static String lastPrompt = "\nPlease enter a command (press 'm' for Menu): ";
	private static String invalidSelectionPrompt = "\nInvalid selection!\nPlease enter a command (press m for Menu): ";

	// Load Catalog Prompts...
	private static String loadCatalogPrompt = "Load Catalog...";
	private static String loadfilePrompt = "Please enter filename: ";

	// Search Catalog Prompts...
	private static String searchPrompt = "Search Catalog...\nPlease enter the search query: ";

	// Analyse Catalog Prompts...
	private static String loadAnalysis = "Catalog Analysis...";
	private static String numArtists = "Number of Artists: ";
	private static String numAlbums = "Number of Albums: ";
	private static String numSongs = "Number of Songs: ";
	private static String promptPlayTime = "Catalog Playtime: ";
	// Print Catalog Prompts...

	// ArrayLists
	static ArrayList<Song> songList = new ArrayList<Song>();
	static ArrayList<Song> searchResults = new ArrayList<Song>();
	static ArrayList<String> occurances = new ArrayList<String>();
	// Scanner... Used for reading user input
	private static Scanner reader = new Scanner(System.in);

	// Scanner... Used for reading Files.
	static Scanner fileIterator;
	static Scanner commaDelim;
	
	// public int vars
	public static int songCount;
	public static int catPlayTime;
/**
 * prints out all the prompts in an order that creates the main menu  
 * 
 */
	public static void mainMenuPrompt() {
		System.out.println();
		System.out.println(menuBorder);
		System.out.println(menuPrompt);
		System.out.println(menuBorder);
		System.out.println(menuSelectionPrompt);
		System.out.print(lastPrompt);
		return;
	}
/**
 * loads the song Catalog based on user input and saves the songs in an array list
 * for future use. It also prints the number of songs it successfully loaded to the user.
 */
	public static void loadCatalog() {
		System.out.println(loadCatalogPrompt);
		System.out.print(loadfilePrompt);
		String userSelection = reader.nextLine();

		File catalogFile = new File(userSelection);

		try {
			catPlayTime = 0;
			songList.clear();
			Scanner fileIterator = new Scanner(catalogFile);

			while (fileIterator.hasNext()) {

				String line = fileIterator.nextLine();

				Scanner commaDelim = new Scanner(line);

				commaDelim.useDelimiter(",");

				ArrayList<String> fileData = new ArrayList<String>();

				while (commaDelim.hasNextLine()) {
					fileData.add(commaDelim.next());

					if (fileData.size() > 3) {
						String artist = fileData.get(0);
						String title = fileData.get(2);
						String album = fileData.get(1);
						int duration = Integer.parseInt(fileData.get(3));

						Song song = new Song(title, artist, album, duration);
						catPlayTime += duration;
						songList.add(song);

					}

				}

			}
			int songCount = songList.size();
			System.out.println("Successfully loaded " + songCount + " songs!");
			System.out.print(lastPrompt);
			menuInput();
			commaDelim.close();
			fileIterator.close();
		}

		catch (FileNotFoundException e) {
			System.out.println("Unable to open file: " + catalogFile);
			System.out.print(lastPrompt);
			menuInput();
		}
	}

	public static void printCatalog() {
		System.out.println("Song list contains " + songList.size() + " songs...");
		System.out.println(menuDivider);
		for (Song x : songList) {
			System.out.println(x);
		}
		System.out.print(lastPrompt);
		menuInput();
	}
/**
 * prompts the user then takes user input to search the newly created list of songs and finds all
 * Occurrences of the users input, and prints those songs that contain them to the user.
 */
	public static void searchCatalog() {

		System.out.print(searchPrompt);

		String userInput = reader.nextLine();
		searchResults.clear();
		String allLowerUserInput = userInput.toLowerCase();
		// PROMPT USER FOR SEARCH QUERY
		for (Song x : songList) {
			String title = x.getTitle();
			String allLowerTitle = title.toLowerCase();
			if (allLowerTitle.contains(allLowerUserInput)) {
				searchResults.add(x);
			}

		}
		int numberOfOccurances = searchResults.size();
		System.out.println("Found " + numberOfOccurances + " matches");
		System.out.println(menuDivider);
		for (Song match : searchResults) {
			System.out.println(match);

		}
		System.out.print(lastPrompt);
		menuInput();

	}

	/**
	 * Displays: number of songs. number of unique artists. number of unique albums.
	 * total playtime of the album.
	 * @param takes String album or artist and search number of occurrences.
	 * @return (if) number of occurrences the song or artist appear.
	 * @return (else) -1 if no occurrences are found.
	 */

	public static int numberOfUniqueValues(String object) {
		occurances.clear();
		if (object.contains("album")) {

			for (Song song : songList) {
				String album = song.getAlbum();
				if (occurances.contains(album) == false) {
					occurances.add(album);
				}
			}
			return (occurances.size());
		}
		if (object.contains("artist")) {
			for (Song song : songList) {
				String artist = song.getArtist();
				if (occurances.contains(artist) == false) {
					occurances.add(artist);
				}
			}
			return (occurances.size());
		} else {
			return -1;
		}
	}
/**
 * prints formatted input about song objects then prompts the user for input.
 */
	public static void catalogAnalysis() {
		System.out.println(loadAnalysis);
		
		System.out.println("	" + numArtists + numberOfUniqueValues("artist"));
		System.out.println("	" + numAlbums + numberOfUniqueValues("album"));
		System.out.println("	" + numSongs + songList.size());
		System.out.println("	" + promptPlayTime + catPlayTime);
		System.out.print(lastPrompt);
		menuInput();
	}
/**
 * prompts the user for input, then determined on what the user inputs selects and loads 
 * a function. If the input is invalid it will call itself recusivly until the user enters
 * a valid input.
 */
	public static void menuInput() {
		// reads users input to menu prompt
		String selection = reader.nextLine();
		String formattedSelection = selection.toLowerCase();
		do {
			switch (formattedSelection) {
			case "l":

				loadCatalog();
				break;
			case "s":
				searchCatalog();
				break;
			case "a":
				catalogAnalysis();
				break;
			case "p":
				printCatalog();

				break;
			case "q":
				System.out.print("Goodbye!");
				reader.close();
				System.exit(0);
				break;
			case "m":
				mainMenuPrompt();
				menuInput();
				break;
			default:
				System.out.print(invalidSelectionPrompt);
				menuInput();
				break;
			}

		} while (formattedSelection.isEmpty());
	}

	// provides the menu structure and console UI
	public static void main(String[] args) {
		System.out.println("Welcome to Jukebox Hero,\nbringing order to playlists one song at a time...");
		mainMenuPrompt();
		menuInput();

	}
}
