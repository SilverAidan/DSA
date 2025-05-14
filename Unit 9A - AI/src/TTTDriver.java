import java.util.Scanner;

public class TTTDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TTT game = new TTT();
		System.out.println(game);
		Scanner input = new Scanner(System.in);
		while(game.evaluate()=='N') {
			int x = input.nextInt();
			int y = input.nextInt();
			game.board[x][y] = TTT.PLAYER;
			if (game.evaluate()!='N') //in case they win....?
				break;
			System.out.println(game);
			int[] aiMove = game.getMove();
			System.out.println(game.count);
			game.board[aiMove[0]][aiMove[1]] = TTT.AI;
			System.out.println(game);
		}

	}

}
