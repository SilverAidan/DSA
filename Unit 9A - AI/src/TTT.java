public class TTT {
	char[][] board;
	static final char PLAYER = 'X';
	static final char AI = 'O';
	static final char EMPTY = ' ';
	boolean turn;
	public int count = 0;// count states later

	public TTT() {
		board = new char[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board[i][j] = EMPTY;
		turn = Math.random() < -.5;// Random Startin Player
	}

	public String toString() {
		String output = "";
		for (int i = 0; i < 3; i++) {
			output += "|";
			for (int j = 0; j < 3; j++) {
				output += board[i][j] + "|";
			}
			output += "\n-------\n";
		}
		return output;
	}

	public char evaluate() {
		// sorry it's kinda inefficient
		for (int i = 0; i < 3; i++) {// rows and columns
			if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] != EMPTY)
				return board[i][0];
			if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] != EMPTY)
				return board[0][i];
		}
		if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != EMPTY)// diagonals
			return board[0][0];

		if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != EMPTY)
			return board[0][2];

		for (int i = 0; i < 3; i++)// checking if there's any empty spots
			for (int j = 0; j < 3; j++)
				if (board[i][j] == EMPTY)
					return 'N';
		return 'D';

	}

	public int[] getMove() {
		int[] output = { 9, 9 };// spot to move to
		int best = -1000; // let's get minimaxing
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == EMPTY) {
					board[i][j] = AI;
					int current = minimax(false);
					if (current > best) {
						best = current;
						output[0] = i;
						output[1] = j;
					}
					board[i][j] = EMPTY;
				}
			}
		}
		return output;
	}

	public int minimax(boolean isMax, int alpha, int beta) {
		count++;
		char eval = evaluate();
		switch (eval) {
		case PLAYER:
			return -100;
		case AI:
			return 100;
		case 'D':
			return 0;
		}

		int best = isMax?-1000:1000;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(board[i][j]==EMPTY) {
					board[i][j] = isMax?AI:PLAYER;
					if(isMax)
						best = Math.max(best, minimax(!isMax, alpha, beta));
					else
						best = Math.min(best, minimax(!isMax, alpha, beta));
					board[i][j] = EMPTY;
					alpha = Math.max(alpha, best);
					beta = Math.min(beta, best);
				}
			}

		}
		return best;
	}

}