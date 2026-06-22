package com.tetris.gridRefactor;

import com.tetris.model.TetrisType;

public class GridRefactor {
    public static void refactorGrid(TetrisType[][] board, int rowInd){

        int m= board[0].length;
        for(int j=0;j<m;j++) board[rowInd][j] = null;

        for(int i=rowInd;i>0;i--){
            for(int j=0;j<m;j++){
                 board[i][j] = board[i-1][j];
            }
        }
        for(int j=0;j<m;j++) board[0][j] = null;
    }
}
