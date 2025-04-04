package experiment;

import java.util.Date;

public class GenerateNewEmailDemo {
	public static void main(String[] args) {
		
		System.out.println(generateBrandNewEmail());
		
	}
	
	public static String generateBrandNewEmail() {
		
		Date date = new Date();
		String dateString = date.toString();
		System.out.println( dateString);
		String dateStringWithoutSpaces = dateString.replaceAll("\\s", "");
		String dateStringWithoutSpaceAndColumn = dateStringWithoutSpaces.replaceAll("\\:", "");
		String brandNewEmail = dateStringWithoutSpaceAndColumn+"@gmail.com";
		return brandNewEmail;
	}
}
