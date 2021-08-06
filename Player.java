package ConnectFour;

import java.util.Scanner;

public class Player {
    String checker;
    int numberOfMoves;
    public Player(String givenChecker){
        if(!givenChecker.equalsIgnoreCase("X") && !givenChecker.equalsIgnoreCase("O")){
            throw new  IllegalArgumentException();
        }
        this.numberOfMoves=0;
        this.checker=givenChecker.toUpperCase();
    }
    public String toString(){
        return "Player " + this.checker;
    }
    public String opponentChecker(){
        if(this.checker.equals("X")){
            return "O";
        }else{
            return "X";
        }
    }
    public void nextMove(Board b){
        Scanner s=new Scanner(System.in);
        int col;
        System.out.println("Enter the column you want to put your checker in");
        System.out.println("Column: ");
        col = s.nextInt();
        boolean canAdd=b.canAddTo(col);
        while(!canAdd){
            System.out.println("Can't put the checker in the given column, try again");
            System.out.println("Column: ");
            col=s.nextInt();
        }
        b.add(this.checker,col);
    }
}
