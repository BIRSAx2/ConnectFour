import java.util.Scanner;

/**
 * Connect Four Game
 *
 * @author mouhieddine
 * @version 1.0
 */
public class ConnectFour {

    public static void main(String[] args) {
        final int SEARCH_DEPTH = 5; // The highest this number is the strongest the engine become.
        Scanner sc= new Scanner(System.in);
        Engine connectFourEngine = new Engine('x');
        int choice = 0, move, aiMove;
        Board gameBoard;
        while (choice != 3) {
            System.out.println("Choose the game mode: ");
            System.out.println("----------------------\n 1) Player vs Player\n 2) Player vs AI\n 3) exit\n----------------------");
            while (choice != 1 && choice != 2 && choice != 3) if (sc.hasNextInt()) choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    choice = 0;
                    gameBoard = new Board();
                    System.out.println("Player x\n vs \nPlayer o");
                    System.out.println(gameBoard);
                    while (!gameBoard.checkWinner() && !gameBoard.isBoardFull()) {

                        do {
                            System.out.println("Possible moves: " + gameBoard.getPossibleMoves());
                            System.out.print("Player 1's move: ");
                            move = sc.nextInt();
                        } while (!gameBoard.fill(move, 'x'));
                        System.out.println(gameBoard);
                        if (!gameBoard.checkWinner() && !gameBoard.isBoardFull()) {
                            do {
                                System.out.println("Possible moves: " + gameBoard.getPossibleMoves());
                                System.out.print("Player 2's move: ");
                                move = sc.nextInt();
                            } while (!gameBoard.fill(move, 'o'));
                        }
                        System.out.println(gameBoard);

                    }
                    System.out.println("Game End");
                    if (gameBoard.checkWinner()) {
                        System.out.println("The winner is: " + ((gameBoard.checkWinner('x') ? "Player 1" : "Player 2")));
                    } else {
                        System.out.println("Draw");
                    }
                }
                case 2 -> {
                    choice = 0;
                    gameBoard = new Board();
                    System.out.println("AI x\n vs \nPlayer o");
                    System.out.println(gameBoard);
                    while (!gameBoard.checkWinner() && !gameBoard.isBoardFull()) {
                        do {
                            System.out.println("Possible moves: " + gameBoard.getPossibleMoves());
                            System.out.print("Player's move: ");
                            move = sc.nextInt();
                        } while (!gameBoard.fill(move, 'o'));
                        System.out.println(gameBoard);

                        System.out.println("Possible moves: " + gameBoard.getPossibleMoves());
                        System.out.print("AI move: ");
                        aiMove = connectFourEngine.getBestMoveIndex(gameBoard,
                                SEARCH_DEPTH,
                                Integer.MIN_VALUE,
                                Integer.MAX_VALUE,
                                false);
                        System.out.println(aiMove);
                        gameBoard.fill(aiMove, 'x');
                        System.out.println(gameBoard);
                    }
                    System.out.println("Game End");
                    if (gameBoard.checkWinner()) {
                        System.out.println("The winner is: " + ((gameBoard.checkWinner('x') ? "AI" : "Player")));
                    } else {
                        System.out.println("Draw");
                    }
                }
                case 3 -> System.out.println("Exiting");
                default -> System.out.println("Unexpected value: " + choice);
            } // switch

        } // while
    }
}