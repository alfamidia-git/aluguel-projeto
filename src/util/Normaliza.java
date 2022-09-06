package util;

import java.text.Normalizer;

public class Normaliza {

	
	
	public static String normalizaEmail(String email) {
		
		email = email.toLowerCase();
		email = Normalizer.normalize(email, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		
		return email;
	}
}
