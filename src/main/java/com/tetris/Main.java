package com.tetris;

import com.tetris.board.TetrisBoard;
import com.tetris.model.TetrisType;

public class Main {
    public static void render(TetrisType[][] board){
        for(TetrisType[] arr: board){
            for(TetrisType x: arr){
                if(x == null){
                    System.out.print(".");
                }
                else System.out.print("*");
            }
            System.out.println("\n");
        }
        System.out.println("\n");
        System.out.println("\n");
    }
    public static void main(String[] args) {

        TetrisBoard Board = new TetrisBoard();
        while(true){
            TetrisType[][] board = Board.getRenderableGrid();
            render(board);
            Board.movedown();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(Board.isGameOver()){
                break;
            }
        }
    }
}