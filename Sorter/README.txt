A simple program that takes a specified txt file containing a list of words 
separated by whitespace or newline as input, sorts all the words 
alphabetically while counting occurrences of each word, and writes them to
the specified output file. A flag for whether or not the amount of occurrences
will be printed to the output file is specified as the third argument, where
"-y" means yes and "-n" means no (but really, anything other than "-y" will
be recognised as no). 

For testing, I used a file containg the names of all Pokemon in National Dex 
order. The file is called "pokemon_names.txt". I also made an alternative file 
called "pokemon_names2.txt" which contains multiple names on the same line, and
some repetition. 
Note: several Pokemon has names that consits of 2 words (Mr. Mime, Mime Jr., 
Type: Null, Tapu Koko, Tapu Lele, Tapu Bulu and Tapu Fini). In order to not 
count each of these as 2 separate words, I removed the whitespace from the 
names.