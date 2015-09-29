import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class Dictionary {
	private static Dictionary d = null;
	
	HashMap<String, Boolean> words;
	
	public Dictionary() {
		words = new HashMap<String, Boolean>();
	}
	
	public String getRandomWord() {
		int num = (int)(Math.random()*words.size());
		return (String)words.keySet().toArray()[num];
	}
	
	public void addWord(String word) {
		words.put(word, true);
	}
	
	public boolean isWord(String word) {
		return words.containsKey(word);
	}
	
	public static Dictionary buildDictionary(String filename) {
		if (d != null) return d;
		
		Scanner scanner;
		Dictionary dict = new Dictionary();

		try {
			scanner = new Scanner(new FileReader(filename));
			scanner.useDelimiter("\n");

			while (scanner.hasNext()) {
				String line = scanner.next();
				dict.addWord(line.trim());
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found " + filename);
		}
		
		d = dict;
		
		return d;
	}
}