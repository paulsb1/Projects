import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

class Sorter{
    public static void main(String[] args) throws FileNotFoundException {
    	if(args.length != 2) {
    		System.out.println("Usage: java Main [input file] [output file]");
    		System.exit(0);
    	}
    	String input_file = args[0];
    	String output_file = args[1];

    	/* Trying to open the input file. */
    	Scanner s = null;
    	try{
    		s = new Scanner(new File(input_file));
    	} catch(FileNotFoundException e) {
    		System.out.println("The file could not be opened.");
    		System.exit(0);
    	}

        /* Reading from the input file and inserting into the arraylist while
        sorting after alphabetical order using the value of the strings to 
        determine the position. Starts by adding the first word from the file
        into the arraylist to have something to compare the rest of the words
        with, and does not add the word to the list if the compared value is 0, 
        in order to avoid duplicate words. */
        ArrayList<String> sorted = new ArrayList<>();
        sorted.add(s.nextLine());
        
        while(s.hasNextLine()) {
            String cur = s.nextLine();
            int j = 0;
            int cmp = cur.compareTo(sorted.get(j));

            while(cmp > 0 && j < sorted.size()) {
                cmp = cur.compareTo(sorted.get(j));
                if(cmp < 0) break;
                j++;
            }

            if(cmp != 0)
                sorted.add(j, cur);
        }

    	/* Writing the sorted list to the output file. */
    	PrintWriter pw = new PrintWriter(output_file);
    	for(int i = 0; i < sorted.size(); i++)
    		pw.println(sorted.get(i));

    	pw.close();
    	System.out.println("Done.");
    }
}