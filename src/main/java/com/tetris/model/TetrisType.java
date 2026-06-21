package com.tetris.model;

public enum TetrisType {

    O(new int[][]{
            {0, 0}, {0, 1},
            {1, 0}, {1, 1}
    }),

    I(new int[][]{
            {0, 0}, {0, 1},
            {0, 2}, {0, 3}
    }),

    T(new int[][]{
            {0, 1},
            {1, 0}, {1, 1}, {1, 2}
    }),

    J(new int[][]{
            {0, 0},
            {1, 0}, {1, 1}, {1, 2}
    }),

    L(new int[][]{
            {0, 2},
            {1, 0}, {1, 1}, {1, 2}
    }),

    S(new int[][]{
            {0, 1}, {0, 2},
            {1, 0}, {1, 1}
    }),

    Z(new int[][]{
            {0, 0}, {0, 1},
            {1, 1}, {1, 2}
    });

    private final int[][] shape;

    TetrisType(int[][] shape) {
        this.shape = shape;
    }

    public int[][] getShape() {
        return shape;
    }
}