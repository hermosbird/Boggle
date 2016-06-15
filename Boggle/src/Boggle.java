import java.io.*;
import java.util.*;

public class Boggle {

	// Boggle will use your well-tested BoggleTray.
	// If you need one (did not get 100% problem coverage,
	// ask Rick for his BoggleTray: mercer@cs.arizona.edu
	private BoggleTray boggleTray;
	List<String> words;
	int score;
	List<String> listofpossiblewords;
	int numberofwords;

	// Other instance variables will be needed to store collections such
	// as the 80,000+ BoggleWords ...

	// Initialize a game with 16 dice to simulate the real game with the real
	// Boggle dice. You will need java.util.Random to get a random side. Also
	// make
	// sure you shuffle the dice so the dice appear in seemingly random
	// locations!

	public Boggle() {
		// TODO: Complete this method
		words = new LinkedList<String>();
		listofpossiblewords = new LinkedList<String>();
		score = 0;
		boggleTray = new BoggleTray();
		set();
	}

	// Allow testers to set the BoggleTray to a known one (not random).

	public void setBoggleTray(BoggleTray dt) {
		this.boggleTray = dt;
		set();
	}

	private void set() {
		// TODO Auto-generated method stub
		int count = 0;
		Scanner s = null;
		try {
			s = new Scanner(new FileInputStream("BoggleWords.txt"));
		} catch (FileNotFoundException e) {
		}
		while (s.hasNext()) {
			String str = s.next();
			if (boggleTray.foundInBoggleTray(str)) {
				listofpossiblewords.add(count, str);
				count++;
			}
		}
		numberofwords = count;
	}

	// Return the BoggleTray object as a textual representation as a String
	public String getBoggleTrayAsString() {
		return boggleTray.toString();
	}

	// Record one word (a string with no whitespace) as one of the users'
	// guesses.
	// Do what you want with it, but oneGuess should be processed so all methods
	// can return the correct values such as getScore() and getWordsFound().
	public void addGuess(String oneGuess) {
		String s = oneGuess.toLowerCase();
		if (oneGuess.length() == 0)
			return;
		else if (!words.contains(s))
			words.add(s);
		else
			return;
	}

	// Return a list of all words the user entered that count for the score. The
	// found words
	// must be in their natural ordering. This method should return an empty
	// list until
	// addGuess(String) is called at least once. The returned List<E> could also
	// be empty if
	// no attempts actually counted for anything. There must be no duplicates!
	public List<String> getWordsFound() {
		// TODO: Complete this method
		LinkedList<String> result = new LinkedList<String>();
		String[] temp = new String[words.size()];
		int a = 0;
		for (int i = 0; i < words.size(); i++) {
			if (listofpossiblewords.contains(words.get(i))) {
				temp[a] = words.get(i);
				a++;
			}
		}
		sort(temp, a);
		for (int i = 0; i < a; i++) {
			result.add(i, temp[i]);
		}
		return result;
	}

	public void sort(String[] t, int count) {
		// TODO Auto-generated method stub
		int min = 0;
		for (int i = 0; i < count - 1; i++) {
			min = i;
			for (int index = i + 1; index < count; index++) {
				if (t[index].compareTo(t[min]) < 0)
					min = index;
			}
			String temp = t[i];
			t[i] = t[min];
			t[min] = temp;
		}
	}

	// Return a list of all words the user entered that do not count for the
	// score
	// in their natural order. This list may be empty with no words guessed or
	// all
	// guessed words actually count for points. There must be no duplicates!
	public List<String> getWordsIncorrect() {
		// TODO: Complete this method
		LinkedList<String> result = new LinkedList<String>();
		String[] temp = new String[words.size()];
		int a = 0;
		for (int i = 0; i < words.size(); i++) {
			if (!listofpossiblewords.contains(words.get(i))
					|| words.get(i).length() < 3) {
				temp[a] = words.get(i);
				a++;
			}
		}
		sort(temp, a);
		for (int i = 0; i < a; i++) {
			result.add(i, temp[i]);
		}
		return result;

	}

	// All words the user could have guessed but didn't in their natural order.
	// This list could also be empty at first or if the user guessed ALL words
	// in the board and in the list of 80K words (unlikely).
	// There must be no duplicates!
	public List<String> getWordsNotGuessed() {
		// TODO: Complete this method
		LinkedList<String> result = new LinkedList<String>();
		String[] array = new String[numberofwords];
		int a = 0;
		for (int i = 0; i < numberofwords; i++) {
			String temp = listofpossiblewords.get(i);
			if (!words.contains(temp)) {
				array[a] = temp;
				a++;
			}
		}
		sort(array, a);
		for (int i = 0; i < a; i++) {
			result.add(i, array[i]);
		}
		return result;
	}

	// Return the correct score.
	public int getScore() {
		int score = 0;
		for (String str : getWordsFound()) {
			if (str.length() == 3 || str.length() == 4) {
				score += 1;
			} else if (str.length() == 5) {
				score += 2;
			} else if (str.length() == 6) {
				score += 3;
			} else if (str.length() == 7) {
				score += 5;
			} else {
				score += 11;
			}
		}

		return score;
	}

	// For the three methods to get return their elements and get "\n" if there
	// are 10 words on one line.
	public String stringit(List<String> s) {
		String result = "";
		if (s.size() <= 10) {
			for (int i = 0; i < s.size(); i++)
				result += s.get(i) + " ";
		} else {
			int i = 0;
			for (int a = 0; a < s.size(); a++) {
				i++;
				result += s.get(a) + " ";
				if (i % 10 == 0) {

					result += "\n";
				}
			}
		}
		return result;
	}
}