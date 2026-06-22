package com.tetris.model;

public class RotationValidator {
    public Boolean isValidRotation(Tetromino tetromino, TetrisType[][] board){
        int boardx = board.length;
        int boardy = board[0].length;
        TetrisType type = tetromino.getType();
        tetromino.getRotation();
        int nextRotation = (tetromino.getRotation()+1)%4; // Value of next rotation
        int posx = tetromino.getRow();//getting the current row position
        int posy = tetromino.getCol();// getting the current col position
        int[][] coordinates = type.getShape();
        int[][]curr = new int[coordinates.length][2];

        for(int i=0;i<coordinates.length;i++){
            curr[i][0] = coordinates[i][0];
            curr[i][1] = coordinates[i][1];
        }

        // coordinates of the rotated tetromino
        if(type.equals(TetrisType.O)){
            return true; // For the O shape tetromino nothing can be changed.
        } else {

            for(int i=0;i<nextRotation;i++){ // Getting the next rotation
                 //current corrdiates of the tetromino to be calculated.
                int j=0;
                int[][] next = new int[curr.length][2];
                for(int[] x: curr){
                    int r = x[0];
                    int c = x[1];
                    next[j][0] = c; //rotated cordinates before normalization
                    next[j][1] = -r;//rotated cordinates before normalization
                    j++;
                }
                curr = next;
                int colmin = Integer.MAX_VALUE;
                int rowmin = Integer.MAX_VALUE;
                for(int [] y: curr){
                    colmin = Math.min(colmin, y[1]); // calculation of min rowval
                    rowmin = Math.min(rowmin, y[0]); // calculation of min colval
                }
                j=0;
                if(colmin <0 || rowmin<0){ // It will run always.
                    for(int[] x: curr){
                        curr[j][0] = curr[j][0] - rowmin; // normalized coordinates
                        curr[j][1] = curr[j][1] - colmin; // normalized coordinates

                        j++;
                    }
                }

            }
            //Check for the collision in the board with actaul normalized corrdinates.
            for(int[] r:  curr){
                int corx = r[0]+posx;
                int cory = r[1]+posy;
                if(cory<0 || cory >=boardy || corx<0 || corx>=boardx) return false;
                if(board[corx][cory]!=null){
                    return false;
                }

            }
            return true;
        }
    }
    public int[][] currentStateCoordinate(Tetromino tetromino){
        int[][] coordinates = tetromino.getType().getShape();
        int currentRotation = tetromino.getRotation();
        int[][]curr = new int[coordinates.length][2];
        for(int i=0;i<coordinates.length;i++){
            curr[i][0] = coordinates[i][0];
            curr[i][1] = coordinates[i][1];
        }
        if(tetromino.getType().equals(TetrisType.O)){
            return coordinates;
        }
        else{
            for(int i=0;i<currentRotation;i++){ // Getting the next rotation
                //current corrdiates of the tetromino to be calculated.
                int j=0;
                int[][] next = new int[curr.length][2];
                for(int[] x: curr){
                    int r = x[0];
                    int c = x[1];
                    next[j][0] = c; //rotated cordinates before normalization
                    next[j][1] = -r;//rotated cordinates before normalization
                    j++;
                }
                curr = next;
                int colmin = Integer.MAX_VALUE;
                int rowmin = Integer.MAX_VALUE;
                for(int [] y: curr){
                    colmin = Math.min(colmin, y[1]); // calculation of min rowval
                    rowmin = Math.min(rowmin, y[0]); // calculation of min colval
                }
                j=0;
                if(colmin <0 || rowmin<0){ // It will run always.
                    for(int[] x: curr){
                        curr[j][0] = curr[j][0] - rowmin; // normalized coordinates
                        curr[j][1] = curr[j][1] - colmin; // normalized coordinates

                        j++;
                    }
                }

            }
            return curr;
        }
    }
}
