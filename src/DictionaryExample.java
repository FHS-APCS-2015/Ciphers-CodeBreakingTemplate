public class DictionaryExample {
	public static void main(String[] args) {
		Dictionary d = Dictionary.buildDictionary("words.txt");

		String sentence = "Twas brillig and the slithy toves did gyre and gimble in the wabe";
		sentence = sentence.toLowerCase();
		
		// This splits the String at every space
		String[] words = sentence.split(" ");

		// Test if a String is an English word
		String word = words[0];
		System.out.println(word + " is a word: " + d.isWord(word));

		// This gets a random word from the dictionary
		System.out.println("Random word is: " + d.getRandomWord());
	}
}