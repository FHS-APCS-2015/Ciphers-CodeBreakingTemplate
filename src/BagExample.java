import java.util.ArrayList;

public class BagExample {
	/* This class demonstrates how to use the Bag data structure. */

	public static void main(String[] args) {
		Bag bag = new Bag();

		// Add things to the bag.  It will keep track of how many
		// of each type of thing you added.
		bag.add("a");
		bag.add("a");
		bag.add("a");
		bag.add("a");
		bag.add("b");
		bag.add("b");
		bag.add("b");
		bag.add("c");
		bag.add("c");
		bag.add("d");
		
		// Then you can ask it about frequencies:
		System.out.println("Total items: " + bag.getTotalWords());
		System.out.println("Total unique items: " + bag.getNumUniqueWords());
		System.out.println("'a' occured: " + bag.getNumOccurances("a") + " times ");
		
		// You can also get a list of the top n most common items
		ArrayList<String> commonLetters = bag.getNMostFrequentStrings(2);
		for (String letter : commonLetters) {
			System.out.print("Letter: " + letter);
			double percent = (double)bag.getNumOccurances(letter) / bag.getTotalWords();
			System.out.println(" " + percent);
		}
		
		// You can do the same thing by getting a list of Bag.Item
		// This is a class that stores a String for the word and an int for its frequency
		// all in the same obect.
		ArrayList<Bag.Item> commonItems = bag.getNMostFrequent(2);
		for (Bag.Item item : commonItems) {
			System.out.println(item.word + " : ");
			System.out.println(item.freq);
		}

	}
}
