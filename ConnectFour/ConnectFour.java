package ConnectFour;

import java.util.Scanner;

public class ConnectFour {
    public void start(){
        printInstructionsPVP();
    }
    public void startAI(){
        printInstructionsPVE();
    }
    private void printInstructionsPVE(){
        System.out.println("Welcome to Connect Four");
        System.out.println("Just in case if you don't know the rules, there are two checkers 'X' and 'O', you will be assigned one of them ");
        System.out.println("Your goal is to connect four of your checkers, diagonally, vertically or horizontally.");
        System.out.println("The first person to connect four checkers win, so try to not let your opponent connect his checkers");
        System.out.println("This is player vs AI mode, so you will be assigned 'X' checker automatically");
        System.out.println("There are ");
        System.out.println("1) Very easy.");
        System.out.println("2) Easy");
        System.out.println("3) Normal ");
        System.out.println("4) Almost Hard");
        System.out.println("5) Hard");
        Scanner s=new Scanner(System.in);
        System.out.println("Just type in the number of the difficulty, the ones I kept on the left.");
        System.out.println("Difficulty: ");
        int diff=s.nextInt();
        while(diff>5){
            System.out.println("You can't go higher, I am sorry, choose another number, from 1 to 5.");
            System.out.println("Difficulty: ");
            diff=s.nextInt();
        }
        // There are 3 tiebreaks: left right and random, but since random seems to be more 'human-like' I will use it here
        Player p1=new Player("X");
        AIPlayer ai=new AIPlayer("O","RANDOM",diff-1);
        BoardSize(p1, ai);
    }
    private void printInstructionsPVP(){
        System.out.println("Welcome to Connect Four");
        System.out.println("Just in case if you don't know the rules, there are two checkers 'X' and 'O', you will be assigned one of them ");
        System.out.println("Your goal is to connect four of your checkers, diagonally, vertically or horizontally.");
        System.out.println("The first person to connect four checkers win, so try to not let your opponent connect his checkers");
        System.out.println("Now, Player 1, choose your checker, 'X' or 'O'");
        Scanner s=new Scanner(System.in);
        String checker=s.nextLine();
        Player p1=new Player(checker);
        Player p2=new Player(p1.opponentChecker());
        System.out.println("Players have been assigned their checkers");
        System.out.println("P1 : "+p2.opponentChecker());
        System.out.println("P2 : "+p1.opponentChecker());
        BoardSize( p1, p2);
    }

    private void BoardSize(Player p1, Player p2) {
        Scanner s=new Scanner(System.in);
        System.out.println("Now, enter the size of the board");
        System.out.println("You can skip this, and play on default board size, 7x6");
        System.out.println("Do you want to skip this step, just type in yes if you want so, otherwise you will have to set the size.");
        String skip=s.next();
        int[] size={7,6};
        if(!skip.equals("yes")){
            size=getSize();
        }
        Board b=new Board(size[0],size[1]);
        startGame(b,p1,p2);
    }

    private int[] getSize(){
        Scanner s=new Scanner(System.in);
        System.out.println("What height do you want the board to be? It should be larger or equal to 4.");
        System.out.println("Height: ");
        int height=s.nextInt();
        while(height<4){
            System.out.println("The height is less than 4, try again.");
            System.out.println("Height: ");
            height=s.nextInt();
        }
        System.out.println("What width do you want the board to be? It should be larger or equal to 4.");
        System.out.println("Width:");
        int width=s.nextInt();
        while(width<4){
            System.out.println("The width is less than 4, try again.");
            System.out.println("Width: ");
            width=s.nextInt();
        }
        s.nextLine();
        s.close();
        return new int[]{width,height};
    }
    public void startGame(Board b,Player p1,Player p2){
        System.out.println("The game shall start.");
        System.out.println("Board: ");
        System.out.println(b);
        int move=0;
        while(!(b.isFull()) && !(b.isWinFor("X")) && !(b.isWinFor("O"))){
            if(move%2==0){
                p1.nextMove(b);
            }else {
                p2.nextMove(b);
            }
            move++;
            System.out.println(b);
        }
        if(b.isWinFor("X")){
            if(p2.opponentChecker().equals("X")){
                System.out.println("Player 1 won, congratulations!");
            }else {
                System.out.println("Player 2 won, congratulations!");
            }
        }else if(b.isWinFor("O")){
            if(p1.opponentChecker().equals("X")){
                System.out.println("Player 1 won, congratulations!");
            }else {
                System.out.println("Player 2 won, congratulations!");
            }
        }else if(b.isFull()){
            System.out.println("It is a tie. GG I guess?");
        }
        System.out.println("Game ended in "+ move+" moves");
        System.out.println("Want to play again? Just type in yes if you want so.");
        Scanner s=new Scanner(System.in);
        if(s.next().equalsIgnoreCase("yes")){
            System.out.println("Your checkers will be the same as before. Good look :)");
            s.nextLine();
            s.close();
            b.resetBoard();
            startGame(b,p1,p2);
        }else {
            System.out.println("Thanks for playing :)");
        }
        s.nextLine();
        s.close();
    }
}
