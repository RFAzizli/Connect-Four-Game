package ConnectFour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class AIPlayer extends Player{
    private String tiebreak;
    private int lookahead;
    public AIPlayer(String givenChecker,String givenTiebreak,int givenLookahead){
        super(givenChecker);
        if(!givenChecker.equals("X") && !givenChecker.equals("O")){
            throw new IllegalArgumentException();
        }
        if(!("LEFTRIGHTRANDOM").contains(givenTiebreak)){
            throw new IllegalArgumentException();
        }
        if(givenLookahead<0){
            throw new IllegalArgumentException();
        }
        this.tiebreak=givenTiebreak;
        this.lookahead=givenLookahead;
    }
    public String toString(){
        return super.toString()+" ("+this.tiebreak+", "+this.lookahead+")";
    }
    public int maxScoreColumn(int[] scores){
        if(scores==null){
            throw new IllegalArgumentException();
        }
        int max=findMax(scores);
        ArrayList<Integer> maxIndex=new ArrayList<Integer>();
        for(int i=0;i<scores.length;i++){
            if(scores[i]==max){
                maxIndex.add(i);
            }
        }
        if(this.tiebreak.equals("LEFT")){
            return maxIndex.get(0);
        }else if(this.tiebreak.equals("RIGHT")){
            return maxIndex.get(maxIndex.size()-1);
        }else {
            return randomIndex(maxIndex);
        }
    }

    public int[] scoresFor(Board b){
        int[] scores=new int[b.getWidth()];
        Arrays.fill(scores, 50);
        for(int i=0;i<b.getWidth();i++){
            if(!b.canAddTo(i)){
                scores[i]=-1;
            }else {
                if(b.isWinFor(this.checker)){
                    scores[i]=100;
                }else if(b.isWinFor(this.opponentChecker())){
                    scores[i]=0;
                }else if(this.lookahead>=1){
                    AIPlayer temp= new AIPlayer(this.opponentChecker(),this.tiebreak,this.lookahead-1);
                    b.add(this.checker,i);
                    int[] tempScores=temp.scoresFor(b);
                    int best_col=temp.maxScoreColumn(tempScores);
                    scores[i]=100-tempScores[best_col];
                    b.remove(i);
                }
            }
        }
        return scores;
    }
    public void nextMove(Board b){
        int col=this.move(b);
        b.add(this.checker,col);
    }
    public int move(Board b){
        int[] scores=this.scoresFor(b);
        int move=this.maxScoreColumn(scores);
        this.numberOfMoves++;
        return move;
    }
    private static int findMax(int[] scores){
        int index=0;
        for(int i=0;i<scores.length;i++){
            if(scores[i]>scores[index]){
                index=i;
            }
        }
        return scores[index];
    }
    private static int randomIndex(ArrayList<Integer> ind){
        Random r=new Random();
        int rand=r.nextInt(ind.size());
        return ind.get(rand);
    }
}
