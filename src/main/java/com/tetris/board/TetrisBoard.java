package com.tetris.board;

import com.tetris.gridRefactor.GridRefactor;
import com.tetris.model.RotationValidator;
import com.tetris.model.TetrisType;
import com.tetris.model.Tetromino;
import com.tetris.model.TetrominoFactory;

public class TetrisBoard {
    private static final int ROWS = 5;
    private static final int COLS = 10;
    private Tetromino currentTetromino;
    private TetrisType[][] board;
    private boolean gameOver = false;
    RotationValidator rotationStrategy = new RotationValidator();

    public TetrisBoard(){
        this.board = new TetrisType[ROWS][COLS];
        this.currentTetromino = TetrominoFactory.createRandomTetromino();
    }
    private void spawnpiece(){
        currentTetromino = TetrominoFactory.createRandomTetromino();
    }

    public void moveLeft(){
        boolean isPossible = true;

        int[][] coordinates = rotationStrategy.currentStateCoordinate(currentTetromino);

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

    public void moveRight(){
        boolean isPossible = true;
        int[][] coordinates = rotationStrategy.currentStateCoordinate(currentTetromino);
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
        boolean isPossible = true;
        int[][] coordinates = rotationStrategy.currentStateCoordinate(currentTetromino);
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
        else{
            lockPiece();
        }
    }

    public void rotatePiece(){

       boolean ispossible = rotationStrategy.isValidRotation(currentTetromino, board);
       if(ispossible){
           int nextRotation = (currentTetromino.getRotation()+1)%4;
           currentTetromino.setRotation(nextRotation);
           int[][] coords =
                   rotationStrategy.currentStateCoordinate(currentTetromino);

//           for(int[] p : coords){
//               System.out.println(p[0] + "," + p[1]);
//           }
       }

    }
    private void lockPiece(){
        int[][] coordinates = rotationStrategy.currentStateCoordinate(currentTetromino);
        int col = currentTetromino.getCol();
        int row = currentTetromino.getRow();
        for(int[] cols: coordinates){
            int temprow = cols[0]+row;
            int tempcol = cols[1]+col;
            board[temprow][tempcol] = currentTetromino.getType();
        }
        clearLines();
        spawnpiece();

        if(checkSpawnCollision()){
            gameOver = true;
            endGame();
        }
    }
    public void endGame(){
        gameOver = true;
        System.out.println("Game Over");
    }
    private void clearLines(){
        int n= board.length;
        int m= board[0].length;
        for(int i=n-1;i>=0;i--){
            boolean lineFlag=false;
            for(int j=0;j<m;j++){
                if(board[i][j] == null){
                    lineFlag = true;
                }
                if(j==m-1 && !lineFlag){
                    GridRefactor.refactorGrid(board, i);
                    i=i+1;
                }
            }

        }
    }
    public boolean isGameOver() {
        return gameOver;
    }
    private boolean checkSpawnCollision() {
        int[][] coordinates =
                rotationStrategy.currentStateCoordinate(currentTetromino);

        int col = currentTetromino.getCol();
        int row = currentTetromino.getRow();

        for(int[] cols : coordinates){
            int temprow = cols[0] + row;
            int tempcol = cols[1] + col;

            if(board[temprow][tempcol] != null){
                return true;
            }
        }

        return false;
    }
    public TetrisType[][] getRenderableGrid(){
        TetrisType[][] renderedboard = new TetrisType[ROWS][COLS];
        for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLS;j++){
                renderedboard[i][j]= board[i][j];
            }
        }
        int[][] coordinates = rotationStrategy.currentStateCoordinate(currentTetromino);
        int col = currentTetromino.getCol();
        int row = currentTetromino.getRow();
        TetrisType t = currentTetromino.getType();
        for(int[] cols: coordinates){
            int temprow = cols[0]+row;
            int tempcol = cols[1]+col;
            if(temprow >= 0 && temprow < ROWS &&
                    tempcol >= 0 && tempcol < COLS){
                renderedboard[temprow][tempcol] = t;
            }
        }
        return renderedboard;
    }

}
