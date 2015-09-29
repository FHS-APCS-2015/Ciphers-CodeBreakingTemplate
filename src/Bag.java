import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Bag {
	private HashMap<String, Item> words;
	private List<Item> sortedWords;
	private int totalWords = 0;
	private boolean sorted = false;
	
	public Bag() {
		words = new HashMap<String, Item>();
	}

	public void add(String w) {
		if (words.containsKey(w)) {
			Item i = words.get(w);
			i.increment();
			words.put(w, i);
		} else {
			words.put(w, new Item(w));
		}
		totalWords++;
		sorted = false;
	}
	
	public List<Item> getNMostFrequent(int n) {
		if (!sorted) {
			this.sortedWords = new ArrayList(words.values());
			Collections.sort(sortedWords);
			sorted = true;
		}
		return sortedWords.subList(0, n);
	}
	
	public List<String> getNMostFrequentStrings(int n) {
		if (!sorted) {
			this.sortedWords = new ArrayList(words.values());
			Collections.sort(sortedWords);
			sorted = true;
		}
		
		ArrayList<String> freq = new ArrayList<String>();
		for (Item i : sortedWords.subList(0, n)) {
			freq.add(i.word);
		}
		return freq;
	}
	

	public List<String> getSortedList() {
		return getNMostFrequentStrings(getNumUniqueWords());
	}
	
	public int getNumOccurances(String w) {
		Item i = words.get(w);
		if (i == null) return 0;
		return i.freq;
	}
	
	public int getNumUniqueWords() {
		return words.size();
	}
	
	public int getTotalWords() {
		return totalWords;
	}
	
	public class Item implements Comparable<Item> {
		public String word;
		public int freq;
		
		public Item(String w) {
			word = w;
			freq = 1;
		}
		
		public void increment() {
			freq++;
		}

		@Override
		public int compareTo(Item o) {
			if (this.freq > o.freq) return -1;
			if (this.freq < o.freq) return 1;
			return 0;
		}
		
		public String toString() {
			return word + "\t\t: " + freq + "\n"; 
		}
	}
}