public class Tester {

	public static void main(String[] args) {
		String plaintext = Cipher.getFileAsString("plaintext1.txt");

		String cipher = Cipher.vigenereCipherEncrypt(plaintext, "password", Cipher.ALPHABET);
		
		Cipher.writeStringToFile("ciphertext.txt", cipher);
	}
}