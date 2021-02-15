package com.games;

import java.awt.*;

public class BlockGenerator {
    int brickWidth;
    int brickHeight;


    int[][] block;

    public BlockGenerator(int row, int col) {

        block = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                block[i][j] = 1;
            }
        }
        brickWidth = 420 / col;
        brickHeight = 150 / row;
    }


    public void draw(Graphics2D g) {
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                if (block[i][j] > 0) {
                    g.setColor(Color.white);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

                }

            }
        }
    }

    public void setBrickValue(int value, int row, int col) {
        block[row][col] = value;
    }
}
