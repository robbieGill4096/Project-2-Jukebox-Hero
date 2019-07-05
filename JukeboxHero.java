import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
public class JukeboxHero {
	private static  String menuBorder = "*****************************";
	private static  String menuPrompt = "*      Program Menu         *";	
	private static  String menuSelectionPrompt = "(L)oad catalog\n(S)earch catalog\n(A)nalyse catalog\n(P)rint catalog\n(Q)uit\n";
	private static  String lastPrompt = "Please enter a command (press 'm' for Menu):";

	private static Scanner reader = new Scanner(System.in);
	static ArrayList<Song> songList = new ArrayList<Song>();
	
	public static void mainMenuPrompt() {
		System.out.println(menuBorder);
		System.out.println(menuPrompt);
		System.out.println(menuBorder);
		System.out.println(menuSelectionPrompt);
		System.out.println(lastPrompt);
		return;	
	}
	
public static void  loadCatalog() {
		
		System.out.println("select a catalog file to load: ");
		
		String userSelection = reader.nextLine();
		
		File catalogFile = new File(userSelection);
		
		try {
			Scanner fileIterator = new Scanner(catalogFile);
			
			while(fileIterator.hasNext()) {
				
				String line = fileIterator.nextLine();
				
				Scanner commaDelim = new Scanner(line);
				
				
				
				commaDelim.useDelimiter(",");
				
				
				ArrayList<String> fileData = new ArrayList<String>();
				
				while (commaDelim.hasNextLine()) {
					fileData.add(commaDelim.next());
					
					
					if (fileData.size() > 3) {
						String artist = fileData.get(0);
						String title = fileData.get(1);
						String album = fileData.get(2);
						int duration = Integer.parseInt(fileData.get(3));
						
						Song song = new Song(artist,title,album,duration);
						songList.add(song);
						
					}
					
				}
			}
			if (fileIterator.hasNext() == false) {
				for(Song x : songList) {
					System.out.println(x);
				}
			}
			
		} 
		
		catch (FileNotFoundException e) {
			System.out.println("Unable to open file: " + "userSelection");
			System.out.println(lastPrompt);
			menuInput();
			}
				}
				
	
	public static void menuInput() {
		//reads users input to menu prompt
		String selection = reader.nextLine();
		String formattedSelection = selection.toLowerCase();
		do {
			switch(formattedSelection) {
			case "l":
				
				loadCatalog();
				break;
			case "s":
				
				break;
			case "a":
							
				break;
			case "p":
				
				break;
			case "q":
				System.out.print("Goodbye!");
				reader.close();
				System.exit(0);
				break;
			case "m":
				mainMenuPrompt();
				menuInput();
				
			default:
				System.out.println("Invalid selection!\nPlease enter a command (press m for Menu):"); 
				menuInput();
				break;
			}
			
		}while(formattedSelection.isEmpty());
	}
//provides the menu structure and console UI
	public static void main(String[] args) {
		mainMenuPrompt();
		menuInput();
		
		
	} 
}
