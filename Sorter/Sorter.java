import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

class Sorter{
    static boolean occ_flag = false;

    public static void main(String[] args) throws FileNotFoundException {
    	if(args.length != 3) {
    		System.out.println("usage: java Sorter [input file] [output file] <flag>");
    		System.exit(0);
    	}
    	String input_file = args[0];
    	String output_file = args[1];
        if(args[2].equals("-y")) occ_flag = true;

    	/* Trying to open the input file. */
    	Scanner scn = null;
    	try{
    		scn = new Scanner(new File(input_file));
    	} catch(FileNotFoundException e) {
    		System.out.println("The file could not be opened.");
    		System.exit(0);
    	}

        ArrayList<Word> sorted = new ArrayList<>();

        while(scn.hasNextLine()) {
            String cur_line = scn.nextLine();
            /* Skips this iteration if the line only contains whitespace. */
            if(cur_line.trim().length() == 0) continue;

            /* Splits the current line by whitespaces to add each individual
            word to the sorted list. */
            String[] line_array = cur_line.split("\\s+");

            for(String cur : line_array) {
                if(sorted.size() > 0) {
                    int j = 0;
                    int cmp = 1; //cur.compareTo(sorted.get(j).get_content());

                    /* Compares to the sorted list and finds the right 
                    position to place the word. */
                    while(cmp > 0 && j < sorted.size()) {
                        cmp = cur.compareTo(sorted.get(j).get_content());
                        if(cmp <= 0) break;
                        j++;
                    }

                    /* Add the word to the list if it's a new word, 
                    increase occurrences of the word if not. */
                    if(cmp != 0) {
                        Word w = new Word(cur);
                        sorted.add(j, w);
                    } else {
                        sorted.get(j).increase_occurrences();
                    }
                } else { 
                    /* If it's the first word, just add it. */
                    Word w = new Word(cur);
                    sorted.add(w);
                }
            }
        }

    	/* Writing the sorted list to the output file. */
    	PrintWriter pw = new PrintWriter(output_file);

        if(occ_flag) {
            for(int i = 0; i < sorted.size(); i++) {
                pw.print(sorted.get(i).get_cont_occ());
                if(i != sorted.size()-1) pw.print("\n");
            }
        } else {
            for(int i = 0; i < sorted.size(); i++) {
                pw.print(sorted.get(i).get_content()); 
                if(i != sorted.size()-1) pw.print("\n");
            }
        }
    	
    	pw.close();
    	System.out.println("Done.");
    }
}