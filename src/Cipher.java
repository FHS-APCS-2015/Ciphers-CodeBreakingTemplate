import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Cipher {
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%_;?-=:" + '\n' + '\r';
	public static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyz ";

	public static Dictionary dictionary = Dictionary
			.buildDictionary("words.txt");

	// use frequency analysis to crack the Vigenere cipher with a 2 character
	// password.
	// return the correct password or null if it can’t be cracked.
	public static String freqAnalysisCrackVigenereLength2(String ciphertext) {

		/* I have added these comments for you to pull! */
		
		return null;
	}

	// same as last one, but this cracks a 3 letter password
	public static String freqAnalysisCrackVigenereLength3(String ciphertext) {
		return null;
	}

	// same as last one, but this works for a password of length passwordLength
	public static String freqAnalysisCrackVigenere(String ciphertext, int passwordLength) {
		return null;
	}

	// same as last one, but this one ALSO figures out the password length*
	public static String freqAnalysisCrackVigenere(String ciphertext) {
		return null;
	}

	/**
	 * returns true if plaintext is valid English.
	 *
	 * @param plaintext
	 *            the text you wish to test for whether it's valid English
	 * @param THRESHOLD
	 *            the % of words that must be English to count as decoded
	 * @return boolean returns true if plaintext is valid English.
	 */
	public static boolean isDecoded(String plain, double THRESHOLD) {
		String[] words = plain.split(" ");
		int count = 0;

		for (int i = 0; i < words.length; i++) {
			if (dictionary.isWord(words[i]))
				count++;
		}

		return ((double) count / words.length) > THRESHOLD;
	}

	/**
	 * returns true if plaintext is at least 80% English words.
	 *
	 * @param plaintext
	 *            the text you wish to test for whether it's valid English
	 * @return boolean returns true if plaintext is valid English.
	 */
	public static boolean isDecoded(String plain) {
		return isDecoded(plain, 0.8); // default threshold of 80%
	}

	public static int bruteForceCrackRotationCipher(String cipher) {
		for (int i = 0; i < ALPHABET.length(); i++) {
			String plain = Cipher.rotationCipherDecrypt(cipher, i, ALPHABET);
			if (isDecoded(plain))
				return i;
		}
		return -1;
	}

	public static String bruteForce2LetterVigenerePassword(String cipher) {
		for (int shift1 = 0; shift1 < ALPHABET.length(); shift1++) {
			for (int shift2 = 0; shift2 < ALPHABET.length(); shift2++) {
				String letter1 = ALPHABET.substring(shift1, shift1 + 1);
				String letter2 = ALPHABET.substring(shift2, shift2 + 1);

				String password = letter1 + letter2;
				String plain = Cipher.vigenereCipherDecrypt(cipher, password,
						ALPHABET);
				if (isDecoded(plain))
					return password;
			}
		}

		return null;
	}

	/**
	 * Returns a single String of a text file's content.
	 *
	 * @param filepath
	 *            A String containing the path to the file you wish to read
	 * @return String returns the contents of the file as a String
	 */
	public static String getFileAsString(String filename) {
		Scanner s;
		StringBuilder sb = new StringBuilder(500000);
		try {
			s = new Scanner(new FileReader(filename));
			s.useDelimiter("");

			while (s.hasNext()) {
				String n = s.next();
				sb.append(n);
			}
		} catch (Exception e) {

		}
		return sb.toString();
	}

	/**
	 * Writes the contents of a String to a new text file.
	 *
	 * @param filepath
	 *            A String containing the path to the file you wish to create
	 * @param text
	 *            The String you wish to write to the file.
	 */
	public static void writeStringToFile(String filename, String text) {
		try {
			PrintWriter out = new PrintWriter(filename);
			for (int i = 0; i < text.length(); i++) {
				char n = text.charAt(i);
				out.print(n);
			}
			out.close();
		} catch (Exception e) {
		}
	}

	/**
	 * Returns the result of decrypting cipherText with the key code.
	 * 
	 * @param cipherText
	 *            the encrypted text.
	 * @param code
	 *            the decryption key
	 * @return returns the decrypted cipherText.
	 */
	public static String vigenereCipherDecrypt(String cipher, String code,
			String ALPHABET) {
		return vigenereCipher(cipher, code, -1, ALPHABET);
	}

	/**
	 * Returns plaintext encrypted by the vigenere cipher encoded with the
	 * String code
	 * 
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param code
	 *            the code to use as the encryption key.
	 * @return returns the encrypted plainText.
	 */
	public static String vigenereCipherEncrypt(String cipher, String code,
			String ALPHABET) {
		return vigenereCipher(cipher, code, 1, ALPHABET);
	}

	// direction must be +1 or -1 depending on direction to shift.
	private static String vigenereCipher(String plain, String code,
			int direction, String ALPHABET) {
		String cipher = "";
		int originalIndex, shiftAmount, newIndex;
		int length = code.length();

		if (!isValidCodeWord(code, ALPHABET))
			return "INVALID CODEWORD!";
		plain = stripInvalid(plain, ALPHABET);

		for (int i = 0; i < plain.length(); i++) {
			char c = plain.charAt(i);
			originalIndex = ALPHABET.indexOf(c);

			if (originalIndex >= 0) {
				shiftAmount = ALPHABET.indexOf(code.charAt(i % length));

				newIndex = originalIndex + direction * shiftAmount;

				while (newIndex < 0)
					newIndex += ALPHABET.length();
				newIndex %= ALPHABET.length();

				cipher += ALPHABET.charAt(newIndex);
			} else {
				// System.out.println("Skipping letter: \"" + c + "\"");
			}
		}
		return cipher;
	}

	/**
	 * returns a copy of the input plaintext String with invalid characters
	 * stripped out.
	 *
	 * @param plaintext
	 *            The plaintext string you wish to remove illegal characters
	 *            from
	 * @param alphabet
	 *            A string of all legal characters.
	 * @return String A copy of plain with all characters not in alphabet
	 *         removed.
	 */

	private static String stripInvalid(String plain, String ALPHABET) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < plain.length(); i++) {
			if (ALPHABET.indexOf(plain.charAt(i)) >= 0)
				b.append(plain.charAt(i));
			else
				System.out.println("Stripping letter: \"" + plain.charAt(i)
						+ "\"");
		}
		return b.toString();
	}

	private static boolean isValidCodeWord(String code, String ALPHABET) {
		for (int i = 0; i < code.length(); i++) {
			if (!ALPHABET.contains(code.substring(i, i + 1)))
				return false;
		}

		return true;
	}

	private static String rotationCipher(String plainText, int shift,
			String ALPHABET) {
		while (shift < 0)
			shift += ALPHABET.length();
		shift = shift % ALPHABET.length();
		String cipherText = "";

		for (int i = 0; i < plainText.length(); i++) {
			String letter = plainText.substring(i, i + 1);

			int shiftedIndex = ALPHABET.indexOf(letter) + shift;

			while (shiftedIndex >= ALPHABET.length())
				shiftedIndex -= ALPHABET.length();
			cipherText += ALPHABET.substring(shiftedIndex, shiftedIndex + 1);
		}

		return cipherText;
	}

	/**
	 * Returns plaintext encrypted by the rotation cipher with a shift of
	 * movement.
	 * 
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param shiftAmount
	 *            the number of characters in ALPHABET to shift by.
	 * @return returns the encrypted plainText.
	 */
	public static String rotationCipherEncrypt(String plain, int shift,
			String ALPHABET) {
		return rotationCipher(plain, shift, ALPHABET);
	}

	/**
	 * Returns a the result of decrypting cipherText by shiftAmount using the
	 * rotation cipher.
	 * 
	 * @param cipherText
	 *            the encrypted text.
	 * @param shiftAmount
	 *            the key to decrypt the cipher.
	 * @return returns the decrypted cipherText.
	 */
	public static String rotationCipherDecrypt(String cipher, int shift,
			String ALPHABET) {
		return rotationCipher(cipher, -shift, ALPHABET);
	}
}