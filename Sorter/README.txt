A simple program that takes a specified txt file that can contain e.g. a book 
as input, sorts all the words in the file (separated by whitespace or newline)
alphabetically while counting occurrences of each word, and writes them to the 
specified output file. Ignores words consisting of only non-alphabetical 
characters, and removes any non-alphabetical character, except for the hyphen, 
from any word. A flag for whether or not the amount of occurrences will be 
printed to the output file is specified as the third argument, where "-y" means 
yes and "-n" means no (but really, anything other than "-y" will be recognised 
as no). 