package com.tetris.board;

import com.tetris.model.RotationValidator;
import com.tetris.model.TetrisType;
import com.tetris.model.Tetromino;
import com.tetris.model.TetrominoFactory;

public class TetrisBoard {
    private static final int ROWS = 20;
    private static final int COLS = 10;
    private Tetromino currentTetromino;
    private TetrisType[][] board;

    RotationValidator rotationStrategy = new RotationValidator();

    public TetrisBoard(){
        this.board = new TetrisType[20][10];
        this.currentTetromino = TetrominoFactory.createRandomTetromino();
    }
    private void spawnpiece(){
        this.currentTetromino = TetrominoFactory.createRandomTetromino();
    }

    public void moveLeft(){
        Boolean isPossible = true;
        TetrisType t = currentTetromino.getType();
        int[][] coordinates = t.getShape();
        int col = currentTetromino.getCol();
        int row = currentTetromino.getRow();
        for(int[] cols: coordinates){
            int tempcol = cols[1]+col;
            int temprow = cols[0]+row;
            int newcol = tempcol-1;
            if(newcol < 0 || newcol>=COLS){
                isPossible = false;
                break;
            }
            else if(board[temprow][newcol] != null){
                isPossible = false;
                break;
            }
        }
        if(isPossible){
            currentTetromino.setCol(col-1);
        }
    }

    public void moreRight(){
        Boolean isPossible = true;
        TetrisType t = currentTetromino.getType();
        int[][] coordinates = t.getShape();
        int col = currentTetromino.getCol();
        int row = currentTetromino.getRow();
        for(int[] cols: coordinates){
            int tempcol = cols[1]+col;
            int newcol = tempcol+1;
            int temprow = cols[0]+row;

            if(newcol < 0 || newcol>=COLS){
                isPossible = false;
                break;
            }
            else if(board[temprow][newcol] != null){
                isPossible = false;
                break;
            }
        }
        if(isPossible){
            currentTetromino.setCol(col+1);
        }
    }
    public void movedown(){
        Boolean isPossible = true;
        TetrisType t = currentTetromino.getType();
        int[][] coordinates = t.getShape();
        int col = currentTetromino.getCol();
        int row = currentTetromino.getRow();
        for(int[] cols: coordinates){
            int temprow = cols[0]+row;
            int newrow = temprow+1;

            int tempcol = cols[1]+col;
            if(newrow>=ROWS || board[newrow][tempcol] != null){
                isPossible = false;
                break;
            }
        }
        if(isPossible){
            currentTetromino.setRow(row+1);
        }
    }

    public void rotatePiece(){

        rotationStrategy.isValidRoation(currentTetromino, board);
    }
    private void lockPiece(){

    }
    private void clearLines(){

    }
    private boolean isGameOver(){
        return false;
    }
    public TetrisType[][] getGrid(){

        return this.board;
    }

}
