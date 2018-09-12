import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

class Sorter{
    static boolean occ_flag = false;
    static String input_file;
    static String output_file;
    ArrayList<Word> sorted;

    /* 
     * Main function. Checks the arguments and opens the input file. 
     */
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length != 3) {
            System.out.println("usage: java Sorter [input file] [output file] <flag>");
            System.exit(0);
        }
        input_file = args[0];
        output_file = args[1];
        if(args[2].equals("-y")) occ_flag = true;

        Scanner scn = null;
        try{
            scn = new Scanner(new File(input_file));
        } catch(FileNotFoundException e) {
            System.out.println("The file could not be opened.");
            System.exit(0);
        }

        new Sorter(scn);
    }

    Sorter(Scanner scn) {
        read_and_sort(scn);
        print_to_file();
    }

    /*
     * Reads in the file, and adds all words alphabetically sorted into
     * the arraylist. 
     */
    void read_and_sort(Scanner scn) {
        sorted = new ArrayList<>();

        while(scn.hasNextLine()) {
            String cur_line = scn.nextLine();
            // Skips this iteration if the line only contains whitespace.
            if(cur_line.trim().length() == 0) continue;

            // Splits the current line by whitespaces to add each individual
            // word to the sorted list.
            String[] line_array = cur_line.split("\\s+");

            for(String cur : line_array) {
                // Skips the word if it doesn't contain any alphabetical 
                // characters.
                if((cur = check_word(cur)) == null) continue;

                if(sorted.size() > 0) {
                    int j = 0;
                    int cmp = 1; // Arbitrary value, has to be > 0. 

                    // Compares to the sorted list and finds the right 
                    // position to place the word.
                    while(cmp > 0 && j < sorted.size()) {
                        String cmp_to = sorted.get(j).get_content().toLowerCase();
                        cmp = cur.toLowerCase().compareTo(cmp_to);
                        if(cmp <= 0) break;
                        j++;
                    }

                    // Add the word to the list if it's a new word, 
                    // increase occurrences of the word if not.
                    if(cmp != 0) {
                        Word w = new Word(cur);
                        sorted.add(j, w);
                    } else {
                        sorted.get(j).increase_occurrences();
                    }
                } else { 
                    // If it's the first word, just add it.
                    Word w = new Word(cur);
                    sorted.add(w);
                }
            }
        }
    }

    /* 
     * Checks if a word matches a specified pattern containing only
     * alphabetical characters with an optional hyphen in the middle
     * of the word. If it doesn't, it will remove all non-alphabetical
     * characters from the word, and check that it still has content. 
     * If the length of the word is 0, null will be returned as the 
     * word didn't contain any alphabetical characters. 
     */
    String check_word(String word) {
        if(word.matches("[A-Za-z]+[\\-]?[A-Za-z]*")) return word;

        word = word.replaceAll("[^A-Za-z]", "");
        if(word.length() == 0) return null;
        
        return word;
    }

    /*
     * Writes the sorted list to the output file.
     */ 
    void print_to_file() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(output_file);
        } catch(FileNotFoundException e) {
            System.exit(0);
        }

        for(int i = 0; i < sorted.size(); i++) {
            pw.print(sorted.get(i).get_content(occ_flag));
            if(i != sorted.size()-1) pw.print("\n");
        }
        
        pw.close();
        System.out.println("Done.");
    }
}