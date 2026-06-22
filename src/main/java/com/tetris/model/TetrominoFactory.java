package com.tetris.model;

import java.util.Random;

public class TetrominoFactory {

    private static final Random RANDOM = new Random();

    public static Tetromino createRandomTetromino() {

        TetrisType[] types = TetrisType.values();
        TetrisType randomType = types[RANDOM.nextInt(types.length)];

        Tetromino tetromino = new Tetromino();
        tetromino.type = randomType;
//        tetromino.type = TetrisType.Z;
        tetromino.row = 0;
        tetromino.col = 4;
        tetromino.rotation = 0;

        return tetromino;
    }
}