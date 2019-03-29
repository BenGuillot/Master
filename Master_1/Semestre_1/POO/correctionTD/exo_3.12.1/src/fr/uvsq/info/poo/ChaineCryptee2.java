

package fr.uvsq.info.poo;

/**
 * Classe en charge de crypter une chaîne.
 * @author mando
 */
public class ChaineCryptee2 {

	/**
	 * Chaîne cryptée.
	 */
	private final String chaineCryptee;
	
	/**
	 * Chaine en clair.
	 */
	private final String chaineEnClair;
	
	/**
	 * Constructeur.
	 * @param chaineEnClair La chaîne en clair.
	 * @param decalage Le décalage à appliquer pour produire la chaine cryptée.
	 * @throws IllegalArgumentException si <code>chaineEnClair == null</code>.
	 */
	public ChaineCryptee2(String chaineEnClair, int decalage)
			throws IllegalArgumentException
	{
		if (chaineEnClair == null) {
			throw new IllegalArgumentException("Chaîne en clair invalide");
		}
		this.chaineEnClair = chaineEnClair;
		
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < this.chaineEnClair.length(); ++i) {
			s.append(decaleCaractere(this.chaineEnClair.charAt(i), decalage));
		}
		
		this.chaineCryptee = s.toString(); 
	}
	
	/**
	 * Accède à la chaîne en clair.
	 * @return La chaine en clair.
	 */
	public String decrypte() {
		return this.chaineEnClair;
	}
	
	/**
	 * Décale un caractère majuscule.
	 * Les lettres en majuscule ('A' - 'Z') sont décalées de <code>decalage</code>
	 * caractères de façon circulaire. Les autres caractères ne sont pas modifiés.
	 *
	 * @param c Le caractère à décaler.
	 * @param decalage Le décalage à appliquer.
	 * @return Le caractère décalé.
	 */
	private static char decaleCaractere(char c, int decalage) {
	    return (c < 'A' || c > 'Z')? c : (char)(((c - 'A' + decalage) % 26) + 'A');
	}
	
	/**
	 * Calcule la chaîne cryptée.
	 * @return La chaîne cryptée.
	 */
	public String crypte() {
		return this.chaineCryptee;
	}
	
	/**
	 * Programme principal.
	 * @param args Arguments passés au programme.
	 */
	public static void main(String[] args) {
		final String s = "BONJOUR";
		//final String s = null;
		ChaineCryptee2 chaineCryptee = new ChaineCryptee2(s, 3);
		System.out.println(s + " --> " + chaineCryptee.crypte());
	}
}
