package com.tetris.model;

public class RotationValidator {
    public void isValidRoation(Tetromino tetromino, TetrisType[][] board){
        TetrisType type = tetromino.getType();
        tetromino.getRotation();
        int nextRotation = (tetromino.getRotation()+1)%4;
        int posx = tetromino.getRow();
        int posy = tetromino.getCol();
        int[][]curr = new int[4][2];
        if(type.equals(TetrisType.O)){
            return;
        } else {

            for(int i=0;i<nextRotation;i++){
                int[][] coordinates = type.getShape();
                int j=0;
                for(int[] x: coordinates){
                    int r = x[0]+posx;
                    int c = x[1]+posy;
                    curr[j][0] = -c;
                    curr[j][1] = r;
                    j++;
                }
                int colmin = Integer.MAX_VALUE;
                int rowmin = Integer.MAX_VALUE;
                for(int [] y: curr){
                    colmin = Math.min(colmin, y[1]);
                    rowmin = Math.min(rowmin, y[0]);
                }
                j=0;
                if(colmin <0 || rowmin<0){
                    for(int[] x: curr){
                        curr[j][0] = curr[j][0]+rowmin;
                        curr[j][1] = curr[j][1]+colmin;
                        j++;
                    }
                }

            }

        }
    }
}
