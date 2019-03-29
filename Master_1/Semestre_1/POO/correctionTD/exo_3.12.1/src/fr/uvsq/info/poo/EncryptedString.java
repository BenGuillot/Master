package fr.uvsq.info.poo;

/**
 * Classe en charge de crypter une chaîne.
 * @author mando
 */
public class EncryptedString {

	/**
	 * Nombre de caractère dont il faut décaler la chaîne en clair pour obtenir
	 * la chaine cryptée.
	 */
	private final int shift;
	
	/**
	 * Chaine en clair.
	 */
	private final String stored;
	
	/**
	 * Constructeur.
	 * @param decrypted La chaîne en clair.
	 * @param shift Le décalage à appliquer pour produire la chaine cryptée.
	 * @throws IllegalArgumentException si <code>chaineEnClair == null</code>.
	 */
	private EncryptedString(String decrypted, int shift)
			throws IllegalArgumentException
	{
		if (decrypted == null) {
			throw new IllegalArgumentException("Chaîne en clair invalide");
		}
		this.shift = shift;
		this.stored = decrypted;
	}
	
	/**
	 * Make an EncryptedString from a encrypted String.
	 * @param crypted The encrypted String.
	 * @param aShift The shift.
	 * @return The corresponding EncryptedString instance.
	 */
	public static EncryptedString FromCrypted(String crypted, int aShift) {
		String decrypted = shiftString(crypted, -aShift); 
		return new EncryptedString(decrypted, aShift);
	}
	
	/**
	 * Make an EncryptedString from a String.
	 * @param decrypted The input String.
	 * @param aShift The shift.
	 * @return The corresponding EncryptedString instance.
	 */
	public static EncryptedString FromDecrypted(String decrypted, int aShift) {
		return new EncryptedString(decrypted, aShift);
	}
	
	/**
	 * Accède à la chaîne en clair.
	 * @return La chaine en clair.
	 */
	public String decrypt() {
		return this.stored;
	}
	
	/**
	 * Décale un caractère majuscule.
	 * Les lettres en majuscule ('A' - 'Z') sont décalées de <code>decalage</code>
	 * caractères de façon circulaire. Les autres caractères ne sont pas modifiés.
	 *
	 * @param c Le caractère à décaler.
	 * @param aShift Le décalage à appliquer.
	 * @return Le caractère décalé.
	 */
	private static char shiftChar(char c, int aShift) {
	    return (c < 'A' || c > 'Z')? c : (char)(((c - 'A' + aShift) % 26) + 'A');
	}
	
	/**
	 * Shift an input String of a given number of characters.
	 * @param inputString The input String.
	 * @param aShift The number of characters to shift.
	 * @return The shifted String.
	 */
	private static String shiftString(String inputString, int aShift) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < inputString.length(); ++i) {
			s.append(shiftChar(inputString.charAt(i), aShift));
		}
		return s.toString();
	}
	
	/**
	 * Calcule la chaîne cryptée.
	 * @return La chaîne cryptée.
	 */
	public String encrypt() {
		return shiftString(this.stored, this.shift);
	}
	
	/**
	 * Programme principal.
	 * @param args Arguments passés au programme.
	 */
	public static void main(String[] args) {
		final String hello = "HELLO";
		EncryptedString encryptedString1 = FromDecrypted(hello, 3);
		System.out.println(
			encryptedString1.decrypt()
			+ " --> "
			+ encryptedString1.encrypt()
		);
		
		final String khoor = "KHOOR";
		EncryptedString encryptedString2 = FromCrypted(khoor, 3);
		System.out.println(
			encryptedString2.decrypt()
			+ " --> "
			+ encryptedString2.encrypt()
		);
	}
}
