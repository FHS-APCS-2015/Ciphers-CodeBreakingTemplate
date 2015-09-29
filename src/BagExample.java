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
		System.out.println("Top 2 most frequent items: " + bag.getNMostFrequent(2));
		double freq = (double)bag.getNumOccurances("a") / bag.getTotalWords();
		System.out.println("Frequency of 'a' in data set is: " + freq);

	}
}
