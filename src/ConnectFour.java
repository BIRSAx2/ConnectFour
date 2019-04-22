import java.util.Scanner;
public class ConnectFour {

    public static void main(String[] args) {
        Board tavolaDaGioco = new Board();
        System.out.println(tavolaDaGioco);
        Engine eng= new Engine();
        int possibleMoveIndex;
        Scanner sc= new Scanner(System.in);
        while(!tavolaDaGioco.checkWinner()){
            possibleMoveIndex=eng.getBestMoveIndex(tavolaDaGioco,1,Integer.MIN_VALUE, Integer.MAX_VALUE,true);
            tavolaDaGioco.fill(possibleMoveIndex,'x');
            System.out.println(tavolaDaGioco);
            //possibleMoveIndex=eng.getBestMoveIndex(tavolaDaGioco,4,Integer.MIN_VALUE, Integer.MAX_VALUE,false);
            possibleMoveIndex=sc.nextInt();
            tavolaDaGioco.fill(possibleMoveIndex,'o');
            System.out.println(tavolaDaGioco);
        }
    }
}