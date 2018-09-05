public class Word {
	String content;
	int occurrences;

	public Word(String content) {
		this.content = content;
		occurrences = 1;
	}

	public void increase_occurrences() {
		occurrences++;
	}

	public String get_content() {
		return content;
	}

	public String get_cont_occ() {
		return content + ", occurrences: " + occurrences;
	}
}