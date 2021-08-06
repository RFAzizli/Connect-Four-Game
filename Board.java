package ConnectFour;

public class Board {
    private int height;
    private int width;
    private String[][] slots;

    public Board(int givenWidth,int givenHeight){
        if(givenWidth<4 || givenHeight<4){
            throw new IllegalArgumentException("The board is too small to be played on");
        }
        this.height=givenHeight;
        this.width=givenWidth;
        this.slots=new String[this.height][this.width];
        this.setBoard();
    }
    private void setBoard(){
        for(int i=0;i<this.height;i++){
            for(int j=0;j<this.width;j++){
                slots[i][j]=" ";
            }
        }
    }
    public boolean remove(String checker,int col){
        return this.removeChecker(checker,col);
    }
    private boolean removeChecker(String checker,int col){
        if ((checker != "O" && checker != "X") || col < 0 || col >= this.width) {
            throw new IllegalArgumentException();
        }
        int firstCell=0;
        for (int i = 0;i<this.height;i++) {
            if(this.slots[i][col]!=" "){
                firstCell=i;
                break;
            }
        }
        if(this.slots[firstCell][col].equals(checker)){
            this.slots[firstCell][col]=" ";
            return true;
        }
        return false;
    }
    public boolean add(String checker,int col){
        return this.addChecker(checker,col);
    }
    private boolean addChecker(String checker,int col) {
        if ((!checker.equals("O") && !checker.equals("X")) || col < 0 || col >= this.width) {
            throw new IllegalArgumentException();
        }
        if (this.canAddTo(col)) {
            int emptyCell = 0;
            for (int i = 0;i<this.height;i++) {
                if(i==this.height-1){
                    this.slots[i][col]=checker;
                    return true;
                }
                if(this.slots[i+1][col]!=" "){
                    this.slots[i][col]=checker;
                    return true;
                }
            }
        }
        return false;
    }
    public boolean canAddTo(int col){
        if(col<0 || col>=this.width){
            return false;
        }
        if(this.slots[0][col]!=" "){
            return false;
        }
        return true;
    }
    public boolean isWinFor(String checker){
        if (!checker.equals("X") && !checker.equals("O")) {
            throw new IllegalArgumentException();
        }
        // Check horizontal win
        for(int i=0;i<this.height;i++){
            for(int j=0;j<this.width-3;j++){
                if(
                        this.slots[i][j]==this.slots[i][j+1] &&
                        this.slots[i][j+1]==this.slots[i][j+2] &&
                        this.slots[i][j+2]==this.slots[i][j+3] &&
                        this.slots[i][j+3].equals(checker)
                ){
                    return true;
                }
            }
        }
        // Check vertical win
        for(int i=0;i<this.height-3;i++) {
            for (int j = 0; j < this.width; j++) {
                if (
                        this.slots[i][j] == this.slots[i + 1][j] &&
                        this.slots[i + 1][j] == this.slots[i + 2][j] &&
                        this.slots[i + 2][j] == this.slots[i + 3][j] &&
                        this.slots[i + 3][j] .equals(checker)
                ) {
                    return true;
                }
            }
        }
        // Check Diagonally Upwards win
        for(int i=0;i<this.height-3;i++){
            for(int j=0;j<this.width-3;j++){
                if(
                        this.slots[i][j]==this.slots[i+1][j+1] &&
                        this.slots[i+1][j+1]==this.slots[i+2][j+2] &&
                        this.slots[i+2][j+2]==this.slots[i+3][j+3] &&
                        this.slots[i+3][j+3].equals(checker)
                ){
                    return true;
                }
            }
        }
        // Check Diagonally Downwards win
        for(int i=0;i<this.height-3;i++){
            for(int j=3;j<this.width;j++){
                if(
                        this.slots[i][j]==this.slots[i+1][j-1] &&
                                this.slots[i+1][j-1]==this.slots[i+2][j-2] &&
                                this.slots[i+2][j-2]==this.slots[i+3][j-3] &&
                                this.slots[i+3][j-3].equals(checker)
                ){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isFull(){
        for(String[] arr: this.slots){
            for(String str:arr){
                if(str==" "){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean resetBoard(){
        this.setBoard();
        return true;
    }
    public String toString(){
        String result="";
        for(int i=0;i<this.height;i++){
            result+="| ";
            for(int j=0;j<this.width;j++){
                result+=this.slots[i][j]+" | ";
            }
            result+="\n";
        }
        result+="\u203E";
        for(int i=0;i<this.width;i++){
            result+="\u203E"+"\u203E"+"\u203E"+"\u203E";
        }
        result+="\n";
        result+="  ";
        for(int i=0;i<this.width;i++){
            if(this.canAddTo(i)) {
                result += i + "   ";
            }else{
                result+="X   ";
            }
        }
        return result;
    }
}
