package com.tetris.model;

public class Tetromino {
    TetrisType type;
    public int row;
    public int col;
    public int rotation;

    public TetrisType getType() {
        return type;
    }

    public void setType(TetrisType type) {
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

}
