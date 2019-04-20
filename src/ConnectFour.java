import java.util.Scanner;
public class ConnectFour {

    public static void main(String[] args) {
        Board tavolaDaGioco = new Board();
        System.out.println(tavolaDaGioco);
        Engine eng= new Engine(10);
        int possibleMoveIndex;
        Scanner sc= new Scanner(System.in);
        while(!tavolaDaGioco.checkWinner()){
            possibleMoveIndex=eng.getBestMoveIndex(tavolaDaGioco,Integer.MIN_VALUE, Integer.MAX_VALUE,true);
            tavolaDaGioco.fill(tavolaDaGioco.getPossibleMoves().get(possibleMoveIndex),'x');
            possibleMoveIndex=eng.getBestMoveIndex(tavolaDaGioco,Integer.MIN_VALUE, Integer.MAX_VALUE,false);
            tavolaDaGioco.fill(tavolaDaGioco.getPossibleMoves().get(possibleMoveIndex),'o');
            System.out.println(tavolaDaGioco);
        }
    }
}