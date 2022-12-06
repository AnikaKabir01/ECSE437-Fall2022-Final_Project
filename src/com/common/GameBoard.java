package com.common;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.awt.Dimension;
import java.awt.Component.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

// import com.common.MinesAdapter.InnerClass;

// import org.w3c.dom.events.MouseEvent;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoard extends JPanel{
    private final int NUM_IMAGES = 13;
    final static int CELL_SIZE = 15;

    final int COVER_FOR_CELL = 10;
    final int MARK_FOR_CELL = 10;
    final int EMPTY_CELL = 0;
    final int MINE_CELL = 9;
    final int COVERED_MINE_CELL = MINE_CELL + COVER_FOR_CELL;
    final int MARKED_MINE_CELL = COVERED_MINE_CELL + MARK_FOR_CELL;

    // static boolean wantRestart = false;

    private final int DRAW_MINE = 9;
    private final int DRAW_COVER = 10;
    private final int DRAW_MARK = 11;
    private final int DRAW_WRONG_MARK = 12;

    // private final int N_MINES = 40;
    // final int N_ROWS = 16;
    // final int N_COLS = 16; //226 cells in total in the minefield
    int N_MINES;
    int N_COLS;
    int N_ROWS;

    // private final int BOARD_WIDTH = N_COLS * CELL_SIZE + 1;
    // private final int BOARD_HEIGHT = N_ROWS * CELL_SIZE + 1;

    int[] field;
    boolean inGame; //in game or game over
    int minesLeft;
    private Image[] img; //images will be images 0 to 15.png...
    JLabel statusbar;
    private int allCells;

    public GameBoard(JLabel statusbar, int nCols, int nRows, int nMines) {

        N_COLS = nCols;
        N_ROWS = nRows;
        N_MINES = nMines;

        this.statusbar = statusbar;
        initBoard(); //!!!
    }

    public GameBoard getGameBoard() {
        return this;
    }

    //Constructor for the board
    private void initBoard() {

        // setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setPreferredSize(new Dimension(N_COLS * CELL_SIZE + 1, N_ROWS * CELL_SIZE + 1));

        img = new Image[NUM_IMAGES];

        for (int i = 0; i < NUM_IMAGES; i++) {

            var path = "src/resources/" + i + ".png";
            img[i] = (new ImageIcon(path)).getImage();
        }

        // MinesAdapter minesAdapter = new MinesAdapter(statusbar); //!!!!!
        // InnerClass innerClass = minesAdapter.new InnerClass();

        //Add Mouse listener
        // addMouseListener(innerClass);
        addMouseListener(new MinesAdapter());

        //Begins a new game
        newGame();
    }

    // private void setPreferredSize(Dimension dimension) {
    // }

    //The following method is setting up the mine field which will be used to play the game
    void newGame() {

        int cell;

        var random = new Random();
        inGame = true;
        minesLeft = N_MINES;

        allCells = N_ROWS * N_COLS;
        field = new int[allCells];

        //Every cell is covered by default in the mine field
        for (int i = 0; i < allCells; i++) {

            field[i] = COVER_FOR_CELL;
        }

        statusbar.setText(Integer.toString(minesLeft));

        int i = 0;

        //Each cell may be surrouded by 8 cells, in exception to the border cells
        //We increase each adjacent cell by one unit for every randomly placed mine

        while (i < N_MINES) {

            int position = (int) (allCells * random.nextDouble());

            if ((position < allCells)
                    && (field[position] != COVERED_MINE_CELL)) {

                int current_col = position % N_COLS;
                field[position] = COVERED_MINE_CELL;
                i++;

                if (current_col > 0) {
                    cell = position - 1 - N_COLS;
                    if (cell >= 0) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }
                    cell = position - 1;
                    if (cell >= 0) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }

                    cell = position + N_COLS - 1;
                    if (cell < allCells) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }
                }

                cell = position - N_COLS;
                if (cell >= 0) {
                    if (field[cell] != COVERED_MINE_CELL) {
                        field[cell] += 1;
                    }
                }

                cell = position + N_COLS;
                if (cell < allCells) {
                    if (field[cell] != COVERED_MINE_CELL) {
                        field[cell] += 1;
                    }
                }

                if (current_col < (N_COLS - 1)) {
                    cell = position - N_COLS + 1;
                    if (cell >= 0) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }
                    cell = position + N_COLS + 1;
                    if (cell < allCells) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }
                    cell = position + 1;
                    if (cell < allCells) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }
                }
            }
        }
    }

    //if he clicks on a cell not on a mine, they uncover a number indicating how many times the cell is adjacent to.
    //clicking on an empty cell, leads to uncovering other adjacent empty cells
    //plus cells with a number that form a border around the space of the empty cells

    //this is a recursive algorithm
    void find_empty_cells(int j) { //change j name
        int current_col = j % N_COLS;
        int cell;

        if (current_col > 0) {
            cell = j - N_COLS - 1;
            
            if (cell >= 0) {
                innerIfs(cell);
            }
            cell = j - 1;
            
            if (cell >= 0) {
                innerIfs(cell);
            }
            cell = j + N_COLS - 1;
            
            if (cell < allCells) {
                innerIfs(cell);
            }
        }
        //for n-j columns
        cell = j - N_COLS;

        if(cell > 0) {
            innerIfs(cell);
        }

        cell = j + N_COLS;

        if (cell < allCells) {

            innerIfs(cell);
        }
        if (current_col < N_COLS - 1) {
            cell = j - N_COLS;
            
            if (cell >= 0) {

                innerIfs(cell);
            } 
            cell = j + N_COLS + 1;

            if(cell < allCells) {

                innerIfs(cell);
            }
            //last condition
            cell = j + 1;

            if (cell < allCells) {
                
                innerIfs(cell);
            }
        }
    }

    //helper method for inner if conditions in find_empty_cells to remove redundancy
    private void innerIfs(int cell) {
        if (field[cell] > MINE_CELL) {
            field[cell] -= COVER_FOR_CELL;

            if(field[cell] == EMPTY_CELL) {
                find_empty_cells(cell);
            }
        }
    }
   
   // Converting the uncovered cell values to their corresponding image
   @Override
    public void paintComponent(Graphics g) {

        int uncover = 0;

        for (int i = 0; i < N_ROWS; i++) {

            for (int j = 0; j < N_COLS; j++) {

                int cell = field[(i * N_COLS) + j];

                if (inGame && cell == MINE_CELL) {

                    inGame = false;
                }

                if (!inGame) {

                    if (cell == COVERED_MINE_CELL) {
                        cell = DRAW_MINE;
                    } else if (cell == MARKED_MINE_CELL) {
                        cell = DRAW_MARK;
                    } else if (cell > COVERED_MINE_CELL) {
                        cell = DRAW_WRONG_MARK;
                    } else if (cell > MINE_CELL) {
                        cell = DRAW_COVER;
                    }

                } else {

                    if (cell > COVERED_MINE_CELL) {
                        cell = DRAW_MARK;
                    } else if (cell > MINE_CELL) {
                        cell = DRAW_COVER;
                        uncover++;
                    }
                }

                //Draws every cell on the window
                g.drawImage(img[cell], (j * CELL_SIZE),
                        (i * CELL_SIZE), this);
            }
        }

        //If there is nothing left to uncover, the game is won
        if (uncover == 0 && inGame) {

            inGame = false;
            statusbar.setText("Game won");

        //Otherwise, the game is lost
        } else if (!inGame) {
            statusbar.setText("Game lost");
        }
    }


    //method reacts to the left and right mouseclicks of the user 
    private class MinesAdapter extends MouseAdapter { //MouseAdapter import not sure

        @Override
        public void mousePressed(MouseEvent e) {
            //x and y coord. of the mouse clicks
            int x = e.getX();
            int y = e.getY(); 

            //compute corresponding column and row of the minefield
            int cCol = x / CELL_SIZE;
            int cRow = y / CELL_SIZE;

            boolean doRepaint = false;

            if(!inGame) {
                newGame();
                repaint();
                
            }

            //check if we are located in the minefield
            if ((x < N_COLS * CELL_SIZE) && (y < N_ROWS * CELL_SIZE)) {
                if (e.getButton() == MouseEvent.BUTTON3) { //uncovering of mines done with right click (BUTTON3)

                    if(field[(cRow * N_COLS) + cCol] > MINE_CELL) {
                        doRepaint = true;

                        if (field[(cRow * N_COLS) + cCol] <= COVERED_MINE_CELL) { //if there is mine, draw mark for the cell
                            if(minesLeft > 0) {
                                field[(cRow * N_COLS) + cCol] += MARK_FOR_CELL;
                                minesLeft--;

                                String msg = Integer.toString(minesLeft);

                                statusbar.setText(msg);
                            } else {
                                statusbar.setText("No marks left");
                            }
                        } else {
                            field[(cRow * N_COLS)+ cCol] -= MARK_FOR_CELL; //if cell already marked, remove mark
                            minesLeft++;

                            String msg = Integer.toString(minesLeft);
                            statusbar.setText(msg);
                        }
                    }
                } else {
                    if (field[(cRow * N_COLS) + cCol] > COVERED_MINE_CELL) { 
                        return;
                    }
                    if ((field[(cRow * N_COLS) + cCol] > MINE_CELL) && (field[(cRow * N_COLS) + cCol] < MARKED_MINE_CELL)) {

                        field[(cRow * N_COLS) + cCol] -= COVER_FOR_CELL; //left click removes a cover from the cell
                        doRepaint = true;

                        if (field[(cRow * N_COLS) + cCol] == MINE_CELL) { //if we left click on a mine the game is over
                            inGame = false; //game over
                        }

                        if (field[(cRow * N_COLS) + cCol] == EMPTY_CELL) { //if we left click on an empty cell we call on find_empty_cells method to uncover other empty cells adjacent to it

                            find_empty_cells((cRow * N_COLS) + cCol);
                        }
                    }
                }
                if (doRepaint) {
                    repaint();
                }
            }
        }
    }
}
