import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class JukeboxHeroEC {
	
	
	
	
	private static void displayMenu(ArrayList<Song> sl) {
		
		Scanner kbd = new Scanner(System.in);
		String selection = kbd.nextLine();
		String formattedSelection = selection.toLowerCase();
		do {
			switch (formattedSelection) {
			case "l":
				loadCatalog(sl, kbd);
				displayMenu(sl);
				break;
			case "s":
				searchCatalog(sl, kbd);
				break;
			case "a":
				analyseCatalog(sl);
				break;
			case "p":
				printCatalog(sl);

				break;
			case "q":
				System.out.print("Goodbye!");
				kbd.close();
				System.exit(0);
				break;
			case "m":
				System.out.println();
				System.out.println("*****************************");
				System.out.println("*      Program Menu         *");
				System.out.println("*****************************");
				System.out.println("(L)oad catalog\n(S)earch catalog\n(A)nalyse catalog\n(P)rint catalog\n(Q)uit\n");
				System.out.print("\nPlease enter a command (press 'm' for Menu): ");
				displayMenu(sl);
				break;
			default:
				System.out.print("\nInvalid selection!\nPlease enter a command (press m for Menu): ");
				displayMenu(sl);
				break;
			}

		} while (formattedSelection.isEmpty());

	}
	private static ArrayList loadCatalog(ArrayList<Song> sl, Scanner kbd) {
		
		System.out.println("Load Catalog...");
		System.out.print("Please enter filename: ");
		String userSelection = kbd.nextLine();
		File catalogFile = new File(userSelection);
		
		try {
	
			sl.clear();
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
						sl.add(song);

					}

				}
				
			}
			int songCount = sl.size();
			System.out.println("Successfully loaded " + songCount + " songs!");
			System.out.print("\nPlease enter a command (press 'm' for Menu): ");
			return sl;
			
			
		}

		catch (FileNotFoundException e) {
			System.out.println("Unable to open file: " + catalogFile);
			System.out.print("\nPlease enter a command (press 'm' for Menu): ");
			displayMenu(sl);
		}
		
		return sl;
	}
	private static void printCatalog(ArrayList<Song> sl) {
		System.out.println("Song list contains " + sl.size() + " songs...");
		System.out.println("---------------------------------");
		for (Song x : sl) {
			System.out.println(x);
		}
		System.out.print("\nPlease enter a command (press 'm' for Menu): ");
		displayMenu(sl);
		
	}
	private static void searchCatalog(ArrayList<Song> sl, Scanner kbd) {
		ArrayList<Song> searchResults = new ArrayList<Song>();
		System.out.print("Search Catalog...\nPlease enter the search query: ");
		String userInput = kbd.nextLine();
		searchResults.clear();
		String allLowerUserInput = userInput.toLowerCase();
		// PROMPT USER FOR SEARCH QUERY
		for (Song x : sl) {
			String title = x.getTitle();
			String allLowerTitle = title.toLowerCase();
			if (allLowerTitle.contains(allLowerUserInput)) {
				searchResults.add(x);
			}

		}
		int numberOfOccurances = searchResults.size();
		System.out.println("Found " + numberOfOccurances + " matches");
		System.out.println("---------------------------------");
		for (Song match : searchResults) {
			System.out.println(match);

		}
		System.out.print("\nPlease enter a command (press 'm' for Menu): ");
		displayMenu(sl);
	}
	public static int numberOfUniqueValues(ArrayList<Song> sl, String object) {
		ArrayList<String> occurances = new ArrayList<String>();
		occurances.clear();
		if (object.contains("album")) {

			for (Song song : sl) {
				String album = song.getAlbum();
				if (occurances.contains(album) == false) {
					occurances.add(album);
				}
			}
			return (occurances.size());
		}
		if (object.contains("artist")) {
			for (Song song : sl) {
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

	private static void analyseCatalog(ArrayList<Song> sl) {
		int catPlayTime = 0;
		for(Song x : sl) {
			 int songTime = x.getPlayTime();
		}
	
		System.out.println("Catalog Analysis...");
		System.out.println("	" + "Number of Artists: " + numberOfUniqueValues(sl,"artist"));
		System.out.println("	" + "Number of Albums: " + numberOfUniqueValues(sl,"album"));
		System.out.println("	" + "Number of Songs: " + sl.size());
		System.out.println("	" + "Catalog Playtime: " + catPlayTime);
		System.out.print("\nPlease enter a command (press 'm' for Menu): ");
		displayMenu(sl);
	}

	public static void main(String[] args) {
	ArrayList<Song> sl = new ArrayList<Song>();
	System.out.println("Welcome to Jukebox Hero,\nbringing order to playlists one song at a time...");
	System.out.println();
	System.out.println("*****************************");
	System.out.println("*      Program Menu         *");
	System.out.println("*****************************");
	System.out.println("(L)oad catalog\n(S)earch catalog\n(A)nalyse catalog\n(P)rint catalog\n(Q)uit\n");
	System.out.print("\nPlease enter a command (press 'm' for Menu): ");
	displayMenu(sl);
	
}
}
