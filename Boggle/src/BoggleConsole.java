//This is for a user to play a game without the GUI.
import java.io.*;
import java.util.*;

public class BoggleConsole {

	public static void main(String[] args) {
		List<String> list = new LinkedList<String>();
		Boggle game = new Boggle();
		System.out.println(game.getBoggleTrayAsString());

		System.out.println("\nEnter words or to quit type ZZ:");
		Scanner a = new Scanner(System.in);

		// To enter your words and the key then see the result report of a game.
		while (!zz(list)) {
			String add = a.next();
			list.add(add);
		}
		for (int j = 0; j < list.size(); j++)
			game.addGuess(getridofzz(list).get(j));

		System.out.println("\n***** Your score is: " + game.getScore());
		System.out.println("\n***** Words you found are : "
				+ game.stringit(game.getWordsFound()));
		System.out.println("\n***** Words you found incorrectly are : "
				+ game.stringit(game.getWordsIncorrect()));
		System.out.println("\n***** Words you could hava found are : "
				+ game.stringit(game.getWordsNotGuessed()));

	}

	//Input a list and get a Stirng out of "zZ or zz or ZZ or Zz".
	private static List<String> getridofzz(List<String> s) {
		s.remove("zz");
		s.remove("ZZ");
		s.remove("Zz");
		s.remove("zZ");
		return s;
	}

	//Return true if an element is "zZ or zz or ZZ or Zz".
	private static boolean zz(List<String> s) {
		if (s.contains("zz"))
			return true;
		if (s.contains("ZZ"))
			return true;
		if (s.contains("zZ"))
			return true;
		if (s.contains("Zz"))
			return true;
		return false;
	}
}
