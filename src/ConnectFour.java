import java.util.Scanner;
public class ConnectFour {

    public static void main(String[] args) {
        Board tavolaDaGioco = new Board();
        System.out.println(tavolaDaGioco);
        Engine eng= new Engine();
        int possibleMoveIndex;
        Scanner sc= new Scanner(System.in);
        while(!tavolaDaGioco.checkWinner()){
            possibleMoveIndex=eng.getBestMoveIndex(tavolaDaGioco, 5,Integer.MIN_VALUE, Integer.MAX_VALUE,true);
            tavolaDaGioco.fill(tavolaDaGioco.getPossibleMoves().get(possibleMoveIndex),'x');
            System.out.println(tavolaDaGioco);
            tavolaDaGioco.fill(sc.nextInt(),'o');
            System.out.println(tavolaDaGioco);
        }
    }
}