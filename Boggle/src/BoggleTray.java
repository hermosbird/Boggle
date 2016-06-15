import java.util.Random;

public class BoggleTray {

	private class Boggled {
		private char element;
		private boolean selected;

		public Boggled(char c) {
			element = c;
			selected = false;
		}
	}

	private Boggled[][] board;
	int R;
	int C;
	private Dice[] diceList;

	public BoggleTray(char[][] array) {
		R = array.length;
		C = array[0].length;
		board = new Boggled[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (array[i][j] >= 97 && array[i][j] <= 122)
					board[i][j] = new Boggled((char) (array[i][j] - 32));
				else
					board[i][j] = new Boggled(array[i][j]);
			}
		}
	}

	public String turnQUtoQ(String str) {
		if (str.length() < 2) {
			return str;
		}
		if (str.substring(0, 2).equals("QU")) {
			return "Q" + str.substring(2);
		} else {
			return str.charAt(0) + turnQUtoQ(str.substring(1));
		}
	}

	public BoggleTray() {
		diceList = new Dice[16];
		diceList[0] = new Dice(new char[] { 'L', 'R', 'Y', 'T', 'T', 'E' });
		diceList[1] = new Dice(new char[] { 'A', 'N', 'A', 'E', 'E', 'G' });
		diceList[2] = new Dice(new char[] { 'A', 'F', 'P', 'K', 'F', 'S' });
		diceList[3] = new Dice(new char[] { 'Y', 'L', 'D', 'E', 'V', 'R' });
		diceList[4] = new Dice(new char[] { 'V', 'T', 'H', 'R', 'W', 'E' });
		diceList[5] = new Dice(new char[] { 'I', 'D', 'S', 'Y', 'T', 'T' });
		diceList[6] = new Dice(new char[] { 'X', 'L', 'D', 'E', 'R', 'I' });
		diceList[7] = new Dice(new char[] { 'Z', 'N', 'R', 'N', 'H', 'L' });
		diceList[8] = new Dice(new char[] { 'E', 'G', 'H', 'W', 'N', 'E' });
		diceList[9] = new Dice(new char[] { 'O', 'A', 'T', 'T', 'O', 'W' });
		diceList[10] = new Dice(new char[] { 'H', 'C', 'P', 'O', 'A', 'S' });
		diceList[11] = new Dice(new char[] { 'N', 'M', 'I', 'H', 'U', 'Q' });
		diceList[12] = new Dice(new char[] { 'S', 'E', 'O', 'T', 'I', 'S' });
		diceList[13] = new Dice(new char[] { 'M', 'T', 'O', 'I', 'C', 'U' });
		diceList[14] = new Dice(new char[] { 'E', 'N', 'S', 'I', 'E', 'U' });
		diceList[15] = new Dice(new char[] { 'O', 'B', 'B', 'A', 'O', 'J' });

		board = new Boggled[4][4];
		R = 4;
		C = 4;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Random generator = new Random();
				int n = generator.nextInt(16);
				if (!diceList[n].isSelected()) {
					board[i][j] = new Boggled(diceList[n].get());
					diceList[n].select(true);
				} else {
					j = j - 1;
				}
			}
		}
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < 4; i++) {
			String temp = "";
			for (int j = 0; j < 4; j++) {
				if (board[i][j].element != 'Q')
					temp = temp + " " + board[i][j].element + "  ";
				else
					temp = temp + " " + board[i][j].element + "u" + " ";
			}
			temp = temp.trim();
			result = result + temp + '\n';
		}
		return result;
	}

	public boolean foundInBoggleTray(String str) {
		if (str.length() == 0)
			return false;
		String str2 = str.toUpperCase();
		str2 = turnQUtoQ(str2);
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j].element == str2.charAt(0)
						&& board[i][j].selected == false) {
					board[i][j].selected = true;
					if (foundInBoggleTray(str2.substring(1), 0, i, j) == true) {
						board[i][j].selected = false;
						return true;
					}
					board[i][j].selected = false;
				}
			}
		}
		return false;
	}

	private boolean foundInBoggleTray(String str, int index, int x, int y) {
		if (index >= str.length())
			return true;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if ((i >= 0 && i < R) && (j >= 0 && j < C)
						&& (!(i == x && j == y))) {
					if (board[i][j].selected == false
							&& board[i][j].element == str.charAt(index)) {
						board[i][j].selected = true;
						if (foundInBoggleTray(str, index + 1, i, j) == true) {
							board[i][j].selected = false;
							return true;
						} else
							board[i][j].selected = false;
					}
				}
			}
		}
		return false;
	}

}