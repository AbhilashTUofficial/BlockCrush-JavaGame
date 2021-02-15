package com.games;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Graphics;

import javax.swing.*;

//to implement the KeyListener and ActionListener we need to add its Override methods
public class GameLogic extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private boolean onGame = false;
    private int screenWidth = 600;
    private int screenHeight = 600;
    private int boarderSize = 5;
    private BlockGenerator map;
    private Timer time;
    private int delay = 8;
    private int playerX = 250;
    private int playerY = 540;
    private int paddleHeight = 10;
    private int paddleWidth = 100;
    private int ballSize = 20;
    private boolean lost = false;
    private boolean win = false;
    private boolean onMenu = true;
    private int option = 1;
    private int moveRate = 20;
    private int ballX = 200;
    private int ballY = 200;
    private int ballXDir = -1;
    private int ballYDir = -2;
    private boolean noMoreBlock = false;
    private int row = 3;
    private int col = 7;
    private int numberOfBricks = 21;
    private int score = 0;


    public GameLogic() {
        map = new BlockGenerator(row, col);
        addKeyListener(this);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);
        time = new Timer(delay, this);
        time.start();

    }

    public void paint(Graphics g) {


        //background painting
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, screenWidth, screenHeight);
        //border painting
        g.setColor(Color.green);
        g.fillRect(0, 0, boarderSize, screenHeight);
        g.fillRect(600 - 20, 0, boarderSize, screenHeight);
        g.fillRect(0, 0, screenWidth, boarderSize);


        if (onGame) {
            map.draw((Graphics2D) g);
            //paddle painting
            g.setColor(Color.green);
            g.fillRect(playerX, playerY, paddleWidth, paddleHeight);
            //ball painting
            g.setColor(Color.yellow);
            g.fillOval(ballX, ballY, ballSize, ballSize);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString(""+score, 30, 540);
            g.dispose();
        }
        if (lost) {
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 50));
            g.drawString("GAME OVER!", 160, 300);
            if (option == 1) {
                g.setColor(Color.white);
                g.fillRect(205, 380, 80, 5);


                g.setColor(Color.GREEN);


                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("Replay", 220, 360);
                g.setColor(Color.GREEN);
                g.setColor(Color.yellow);
                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("Menu", 320, 360);
            }
            if (option == 2) {

                g.setColor(Color.white);
                g.fillRect(310, 380, 80, 5);


                g.setColor(Color.yellow);

                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("Replay", 220, 360);


                g.setColor(Color.GREEN);

                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("Menu", 320, 360);


            }


        }
        if (win) {
            g.setColor(Color.green);
            g.setFont(new Font("serif", Font.BOLD, 50));
            g.drawString("YOU WIN!", 160, 300);
            if (option == 1) {
                g.setColor(Color.white);
                g.fillRect(205, 380, 80, 5);


                g.setColor(Color.GREEN);


                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("Replay", 220, 360);
                g.setColor(Color.GREEN);
                g.setColor(Color.yellow);
                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("Menu", 320, 360);
            }
            if (option == 2) {

                g.setColor(Color.white);
                g.fillRect(310, 380, 80, 5);


                g.setColor(Color.yellow);

                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("Replay", 220, 360);


                g.setColor(Color.GREEN);

                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("Menu", 320, 360);


            }
        }
        if (onMenu) {
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 50));
            g.drawString("Block Crush!", 160, 300);
            if (option == 1 || option == 0) {
                g.setColor(Color.white);
                g.fillRect(205, 380, 80, 5);


                g.setColor(Color.GREEN);


                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("Play", 220, 360);
                g.setColor(Color.GREEN);
                g.setColor(Color.yellow);
                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("Exit", 320, 360);
            }
            if (option == 2) {

                g.setColor(Color.white);
                g.fillRect(310, 380, 80, 5);


                g.setColor(Color.yellow);

                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("Play", 220, 360);


                g.setColor(Color.GREEN);

                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("Exit", 320, 360);


            }
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //paddle-border collision
        if (playerX > 470) {
            playerX = 470;
        } else if (playerX < 10) {
            playerX = 10;
        }
        //ball-border collision
        if (play) {
            ballX += ballXDir;
            ballY += ballYDir;
            if (ballX < 0) {
                ballXDir = ballXDir * -1;
            }
            if (ballY < 0) {
                ballYDir = ballYDir * -1;
            }
            if (ballX > 570) {
                ballXDir = ballXDir * -1;
            }
            if (ballY > playerY + 40) {
                numberOfBricks=21;
                score=0;
                play = false;
                onGame = false;
                onMenu = false;
                lost = true;
                option = 1;

            }
            //ball-paddle collision
            if (new Rectangle(ballX, ballY, ballSize, ballSize).intersects(new Rectangle(playerX, playerY, paddleWidth, paddleHeight))) {
                ballYDir = ballYDir * -1;
            }
        }

        //collision with blocks
        A:
        for (int i = 0; i < map.block.length; i++) {
            for (int j = 0; j < map.block[0].length; j++) {
                if (map.block[i][j] > 0) {
                    int brickX = j * map.brickWidth + 80;
                    int brickY = i * map.brickHeight + 50;
                    int brickWidth = map.brickWidth;
                    int brickHeight = map.brickHeight;
                    Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                    Rectangle ballRect = new Rectangle(ballX, ballY, ballSize, ballSize);
                    Rectangle brickRect = rect;
                    if (ballRect.intersects(brickRect)) {
                        map.setBrickValue(0, i, j);
                        numberOfBricks--;
                        score++;
                        if (ballX + 19 <= brickRect.x || ballX + 1 >= brickRect.x + brickRect.width) {
                            ballXDir = -ballXDir;
                        } else {
                            ballYDir = -ballYDir;
                        }
                        break A;

                    }
                }
            }
        }
        if (numberOfBricks == 4) {
            numberOfBricks=21;
            score=0;
            play = false;
            onGame = false;
            System.out.println("win");
            win = true;
        }
        time.start();


        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (onGame) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                play = true;
                playerX -= moveRate;
                System.out.println(playerX);

            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                play = true;
                playerX += moveRate;
                System.out.println(playerX);
            }


        } else if (onMenu || lost || win) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                option = 1;

            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                option = 2;
            }
            if (onMenu) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && option == 1) {
                    onGame = true;
                    ballX = 200;
                    ballY = 200;
                    ballXDir = -1;
                    ballYDir = -2;
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER && option == 2) {
                    onMenu = true;
                    ballX = 200;
                    ballY = 200;
                    ballXDir = -1;
                    ballYDir = -2;



                }
            }
            if (lost || win) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && option == 1) {
                    lost = false;
                    win = false;
                    ballX = 200;
                    ballY = 200;
                    ballXDir = -1;
                    ballYDir = -2;
                    onGame = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER && option == 2) {

                    lost = false;
                    win = false;
                    onMenu = true;
                    ballX = 200;
                    ballY = 200;
                    ballXDir = -1;
                    ballYDir = -2;

                }
            }


        }
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
