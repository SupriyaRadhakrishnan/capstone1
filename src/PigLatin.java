import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PigLatin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scnr = new Scanner(System.in);
		boolean isYes = false;
		String yourLine = "";
		String translatedFormat = "";
		System.out.println("Welcome to the Pig Latin Translator!");
		do {
			System.out.println("Enter the Line to be translated :");

			yourLine = scnr.nextLine();
			if (yourLine.length() <= 0) {
				System.out.println("You must enter a Text.Try Again");
				isYes = true;
			} else {
				String yourWord[] = yourLine.split(" ");

				for (int i = 0; i < yourWord.length; i++) {
					if (i != 0 && i != yourWord.length)
						translatedFormat += " ";
					if (isComma(yourWord[i]))
						translatedFormat += translateToPigLatin(yourWord[i].substring(0, yourWord[i].length() - 1))
								+ ",";
					else if (isNumber(yourWord[i]) || containsSpecialCharacters(yourWord[i])
							|| WithinSingleQuote(yourWord[i]))
						translatedFormat += yourWord[i];
					else
						translatedFormat += translateToPigLatin(yourWord[i]);
				}
				System.out.println("Translated Word:" + translatedFormat);
				// System.out.println("Translated Word length:" + translatedFormat.length());
				if (Validator.getString(scnr, "Translate another line? (y/n):").equalsIgnoreCase("y")) {

					isYes = true;
				} else
					isYes = false;
				translatedFormat = "";
			}

		} while (isYes);
		System.out.println("Thank You for using PigLatin Translator");
	}

	private static String translateToPigLatin(String originalFormat) {
		String translatedFormat = "";
		char firstLetter = originalFormat.charAt(0);
		if (isVowel(firstLetter)) {
			translatedFormat = originalFormat + "way";
			translatedFormat = getWordCase(originalFormat, translatedFormat);
			return translatedFormat;
		} else {
			translatedFormat = translateConsonant(originalFormat);
			translatedFormat = getWordCase(originalFormat, translatedFormat);
			return translatedFormat;
		}

	}

	private static String translateConsonant(String originalFormat) {
		String translatedFormat = "";
		int indexOfVowel = 0;
		for (int i = 0; i < originalFormat.length(); i++) {
			if (isVowel(originalFormat.charAt(i))) {
				indexOfVowel = originalFormat.indexOf(originalFormat.charAt(i));
				i = originalFormat.length();
			}
		}

		translatedFormat = originalFormat.substring(indexOfVowel) + originalFormat.substring(0, indexOfVowel) + "ay";
		return translatedFormat;
	}

	private static boolean isVowel(char currentChar) {
		return "AEIOUaeiou".indexOf(currentChar) != -1;
	}

	private static boolean isNumber(String currentString) {
		return currentString.chars().anyMatch(Character::isDigit);

	}

	private static boolean containsSpecialCharacters(String currentString) {
		Pattern special = Pattern.compile("[!@#$%&*()_+.=:;|<>?{}\\[\\]~-]");
		// Pattern special = Pattern.compile("[^a-zA-Z0-9]");
		Matcher hasSpecial = special.matcher(currentString);
		return hasSpecial.find();
	}

	private static String getWordCase(String originalFormat, String currentString) {
		if (isStringUpperCase(originalFormat))
			return currentString.toUpperCase();
		else if (isStringLowerCase(originalFormat))
			return currentString.toLowerCase();
		else if (Character.isUpperCase(originalFormat.charAt(0)) && isStringLowerCase(originalFormat.substring(1)))
			return currentString.substring(0, 1).toUpperCase() + currentString.substring(1).toLowerCase();
		else
			return currentString;
	}

	private static boolean isStringUpperCase(String str) {

		// convert String to char array
		char[] charArray = str.toCharArray();

		for (int i = 0; i < charArray.length; i++) {

			// if any character is in lower case, return false
			if (Character.isLowerCase(charArray[i]))
				return false;
		}

		return true;
	}

	private static boolean isStringLowerCase(String str) {

		// convert String to char array
		char[] charArray = str.toCharArray();

		for (int i = 0; i < charArray.length; i++) {

			// if any character is in lower case, return false
			if (Character.isUpperCase(charArray[i]))
				return false;
		}

		return true;
	}

	private static boolean WithinSingleQuote(String currentString) {
		if (currentString.charAt(0) == '\'' || currentString.charAt((currentString.length() - 1)) == '\'') {
			return true;
		} else
			return false;
	}

	private static boolean isComma(String currentString) {
		return (",".indexOf(currentString.charAt(currentString.length() - 1)) != -1);

	}
}