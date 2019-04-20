
/**
 *
 * @author mouhieddine
 */
import java.util.ArrayList;

public class Engine {
    private int depth;

    public Engine(int depth) {
        this.depth = depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getBestMoveIndex(Board state,int alpha, int beta, boolean player) {
        int value;
        ArrayList<Integer> movesScore= new ArrayList();
        
        for (int i : state.getPossibleMoves()) {
                Board newState = new Board(state.getBoardDeepCopy());
                depth--;
                movesScore.add(alpha_beta(newState, alpha, beta, true));
        }
        return state.getPossibleMoves().get(movesScore.indexOf(maxValueOf(movesScore)));
    }

    public int alpha_beta(Board state,int alpha, int beta, boolean player) {
        int value;
        System.out.println(depth);
        if (depth <= 0 || isTerminalNode(state)) {
            System.out.println("\n-----------------------ALPHA-BETA--------------------------------");
            System.out.println(state);
            System.out.println("-----------------------ALPHA-BETA--------------------------------\n");
            return score(state);
        }
        else if (player) {
            value = Integer.MIN_VALUE;
            for (int i : state.getPossibleMoves()) {
                Board newState = new Board(state.getBoardDeepCopy());
                newState.fill(i, 'x');
                System.out.println("\n-----------------------ALPHA-BETA X --------------------------------");
                System.out.println(depth);
                System.out.println(newState);
                System.out.println("-----------------------ALPHA-BETA--------------------------------\n");
                depth--;
                value = Math.max(value, alpha_beta(newState,alpha, beta, false));
                alpha = Math.max(alpha, value);
                if (beta <= alpha) {
                    return value;

                }
            }
            return value;
        } else {
            value = Integer.MAX_VALUE;
            for (int i : state.getPossibleMoves()) {
                Board newState = new Board(state.getBoardDeepCopy());
                newState.fill(i, 'o');
                System.out.println("\n-----------------------ALPHA-BETA O --------------------------------");
                System.out.println(depth);
                System.out.println(newState);
                System.out.println("-----------------------ALPHA-BETA--------------------------------\n");
                depth--;
                value = Math.min(value, alpha_beta(newState, alpha, beta, true));
                alpha = Math.min(alpha, value);
                if (beta <= alpha) {
                    return value;

                }
                return value;
            }
        }
        return 0;

    }

    private boolean isTerminalNode(Board state) {
        return (state.isBoardFull() || state.checkWinner());
    }

    private int score(Board state) {
        if (state.checkWinner('x')) {
            return 10;
        } else if (state.checkWinner('o')) {
            return -10;
        } else {
            return scoreOf(state);
        }
    }
    private int maxValueOf(ArrayList<Integer> list){
        int max=list.get(0);
        for(int i: list){
            if(i>max) max=i;
        }
        return max;
    }
    public int scoreOf(Board state){
        int value =0, count=0;
        ArrayList<Integer> counts= new ArrayList();
        // rows
        for( int c=0; c< state.getBoard()[0].length;c++){
            for( char[] row: state.getBoard()){
                if(row[c]=='x') count++;
                else{
                counts.add(count);
                count=0;
                }
            }
        }
        counts.add(count);
        count=0;
        //cols
        for( int c=0; c<state.getBoard()[0].length;c++){
            for(int r=0; r<state.getBoard().length;r++){
                if(state.getBoard()[r][c]=='x') count++;
                else{
                    counts.add(count);
                    count=0;
                }
            }
        }
        //Downward Diag

        //UpWard Diag

        return maxValueOf(counts);
    }
}
