import java.util.*;
import java.io.*;
class tictactoe{
    static class Move{
        int row,col;
    };
    public static int evaluate(char[][] b,int depth){
        for(int i=0;i<3;i++){
            if(b[i][0]==b[i][1] && b[i][1]==b[i][2]){
                if(b[i][0]=='x'){
                    return 10-depth;
                }else if(b[i][0]=='o'){
                    return -10+depth;
                }
            }

        }
        for(int i=0;i<3;i++){
            if(b[0][i]==b[1][i] && b[1][i]==b[2][i]){
                if(b[0][i]=='x'){
                    return 10-depth;
                }else if(b[0][1]=='o'){
                    return -10+depth;
                }
            }

        }
        if(b[0][0]==b[1][1] && b[1][1]==b[2][2]){
             if(b[0][0]=='x'){
                    return 10-depth;
                }else if(b[0][0]=='o'){
                    return -10+depth;
                }
        }
        if(b[0][2]==b[1][1] && b[1][1]==b[2][0]){
             if(b[0][2]=='x'){
                    return 10-depth;
                }else if(b[0][2]=='o'){
                    return -10+depth;
                }
        }
        return 0;
    }
    public static boolean areMovesLeft(char[][] b){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(b[i][j]=='_'){
                    return true;
                }
            }
        }
        return false;
    }
    static int minimax(char[][] b,int depth,boolean isMaximizer){
        int score=evaluate(b,depth);
        if(score==10){
            return score;
        }
        if(score==-10){
            return score;
        }
        if(areMovesLeft(b)==false){
            return 0;
        }
        if(isMaximizer){
            int bestVal=-1000;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(b[i][j]=='_'){
                        b[i][j]='x';
                        bestVal=Math.max(bestVal,minimax(b,depth+1,false));
                        b[i][j]='_';
                    }
                }
            }
            return bestVal;
        }
        else{
            int bestVal=1000;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(b[i][j]=='_'){
                        b[i][j]='o';
                        bestVal=Math.min(bestVal,minimax(b,depth+1,true));
                        b[i][j]='_';
                    }
                }
            }
        return bestVal;
        }
        
    }
    public static Move bestMove(char[][] b){
       int optval=-100;
       Move best=new Move();
       best.row=-1;
       best.col=-1;

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(b[i][j]=='_'){
                    b[i][j]='x';
                    int moveVal=minimax(b,0,false);
                    b[i][j]='_';
                    if(moveVal>optval){
                        optval=moveVal;
                        best.row=i;
                        best.col=j;
                    }
                }
            }
        }
        System.out.printf("The value of best move is %d",optval);
        return best;
    }
    public static void main(String[] args){
        char b[][] = {{ 'x', 'o', 'x' },
                      { 'o', 'o', 'x' },
                      { '_', '_', '_' }};
 
        Move bestMove = bestMove(b);
 
        System.out.printf("The Optimal Move is :\n");
        System.out.printf("ROW: %d COL: %d\n\n",bestMove.row, bestMove.col );
    }
}