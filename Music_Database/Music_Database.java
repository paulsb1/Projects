import java.util.*;
import java.io.*;

public class Music_Database {
	Scanner in = new Scanner(System.in);
	ArrayList<Media> media_list = new ArrayList<>();
	static String filename = "";
	int new_entries = 0;

	/* Main function. Opens the input file. */
	public static void main(String[] args) throws FileNotFoundException {
		if(args.length != 1) {
			System.out.println("Usage: java Music_Database [filename]");
			System.exit(0);
		}
		filename = args [0];

		Scanner s = null;
		try {
			s = new Scanner(new File(filename));
		} catch(FileNotFoundException e) {
			System.out.println("The file could not be opened.");
			System.exit(0);
		}

		new Music_Database(s);
	}

	Music_Database(Scanner s) {
		read_file(s);
		menu();
		if(new_entries > 0) print_file();
	}

	/* Function that reads the input file, creates media entries, and
	stores them in the media arraylist. */
	public void read_file(Scanner s) {
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String[] data = line.split("\\|");

			String type = data[0];
			String artist = data[1];
			String title = data[2];
			int price = 0;
			try {
				price = Integer.parseInt(data[3]);
			} catch(NumberFormatException e) {
				System.out.println("Error in file.");
				System.exit(0);
			}

			Media m = new Media(type, artist, title, price);
			media_list.add(m);
		}
	}

	/* Function that returns an alphabetically sorted version of media_list. */
	public ArrayList<Media> sort_list() {
		ArrayList<Media> sorted = new ArrayList<Media>();

		for(int i = 0; i < media_list.size(); i++) {
			if(sorted.size() == 0) {
				sorted.add(media_list.get(i));
			} else {
				Media cur = media_list.get(i); 
				String cur_string = cur.get_entry();
				int j = 0;
				int cmp = cur_string.compareTo(sorted.get(j).get_entry());

				while(cmp > 0 && j < sorted.size()) {
					cmp = cur_string.compareTo(sorted.get(j).get_entry());
					if(cmp < 0) break;
					j++;
				}

				sorted.add(j, cur);
			}

		}
		return sorted;
	}

	/* Rewrites the input file to update it with new entries. Only
	used before the program shuts down if new entries has been added. */
	public void print_file() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(filename);
		} catch(FileNotFoundException e) {
			System.exit(0);
		}

		media_list = sort_list();

		for(Media m : media_list) { 
			pw.println(m.get_entry());
		}

		pw.close();
		System.out.println("The file has been updated.");
	}

	/* Function that adds a new entry based on user input. */
	public void new_entry() {
		System.out.println("Media type:");
		String type = in.nextLine();

		System.out.println("Artist/band name:");
		String artist = in.nextLine();

		System.out.println("Title:");
		String title = in.nextLine();

		System.out.println("Price (in NOK):");
		int price;
		try {
			price = Integer.parseInt(in.nextLine());
		} catch(NumberFormatException e) {
			System.out.println("Please enter only numbers.");
			return;
		}

		Media m = new Media(type, artist, title, price);
		media_list.add(m);

		new_entries++;
	}

	public void print_list() {
		for(Media m : media_list) {
			System.out.println(m.get_listing());
		}
	}

	public void get_total_money() {
		int total = 0;
		for(Media m : media_list) {
			total += m.get_price();
		}

		System.out.println("Total money spent: " + total + " NOK.");
	}

	public void display_menu() {
		System.out.println("\nSelect an action:");
		System.out.println("1 - Register new.");
		System.out.println("2 - List all.");
		System.out.println("3 - Print total money spent.");
		System.out.println("0 - Terminate program.");
	}

	public void menu() {
		String selection = "";
		
		while(!(selection.equals("0"))) {
			display_menu();
			selection = in.nextLine();
			
			if 	   (selection.equals("1")) new_entry();
			else if(selection.equals("2")) print_list();
			else if(selection.equals("3")) get_total_money();
			else if(selection.equals("0")) break;
			else System.out.println("Please enter a valid command.");
		}
	}
}