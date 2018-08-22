public class Media {
	/* Values to store about each optical media. */
	private String media;
	private String artist;
	private String title;
	private int price;

	/* Constructor. */
	public Media(String media, String artist, String title, int price) {
		this.media = media;
		this.artist = artist;
		this.title = title;
		this.price = price;
	}

	/* 
	 * Funtions to return values used by the main program. 
	 */

	public int get_price() {
		return price;
	}

	public String get_entry() {
		return media + "|" + artist + "|" + title + "|" + price;
	}

	public String get_listing() {
		return artist + ": " + title + " (" + media + ")";
	}
}