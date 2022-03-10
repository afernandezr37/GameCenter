package com.example.gamecenter;

import java.io.Serializable;

public class BoardPegSolitaire implements Serializable {
    //0 no casilla
    //1 casilla vacia
    //2 casilla con bolita
    //3 casilla con bolita seleccionada
    private Peg[][] board;
    private Peg[][] anterior;

    public BoardPegSolitaire(Peg[][] board) {
        this.board = board;
    }

    public BoardPegSolitaire() {
        board = new Peg[7][7];
    }

    public Peg[][] getBoard() {
        return board;
    }

    public void setBoard(Peg[][] board) {
        this.board = board;
    }

    public void assignDefaultValues() {
        board[0][0] = new Peg(0);
        board[0][1] = new Peg(0);
        board[0][2] = new Peg(2);
        board[0][3] = new Peg(2);
        board[0][4] = new Peg(2);
        board[0][5] = new Peg(0);
        board[0][6] = new Peg(0);
        board[1][0] = new Peg(0);
        board[1][1] = new Peg(0);
        board[1][2] = new Peg(2);
        board[1][3] = new Peg(2);
        board[1][4] = new Peg(2);
        board[1][5] = new Peg(0);
        board[1][6] = new Peg(0);
        board[2][0] = new Peg(2);
        board[2][1] = new Peg(2);
        board[2][2] = new Peg(2);
        board[2][3] = new Peg(2);
        board[2][4] = new Peg(2);
        board[2][5] = new Peg(2);
        board[2][6] = new Peg(2);
        board[3][0] = new Peg(2);
        board[3][1] = new Peg(2);
        board[3][2] = new Peg(2);
        board[3][3] = new Peg(1);
        board[3][4] = new Peg(2);
        board[3][5] = new Peg(2);
        board[3][6] = new Peg(2);
        board[4][0] = new Peg(2);
        board[4][1] = new Peg(2);
        board[4][2] = new Peg(2);
        board[4][3] = new Peg(2);
        board[4][4] = new Peg(2);
        board[4][5] = new Peg(2);
        board[4][6] = new Peg(2);
        board[5][0] = new Peg(0);
        board[5][1] = new Peg(0);
        board[5][2] = new Peg(2);
        board[5][3] = new Peg(2);
        board[5][4] = new Peg(2);
        board[5][5] = new Peg(0);
        board[5][6] = new Peg(0);
        board[6][0] = new Peg(0);
        board[6][1] = new Peg(0);
        board[6][2] = new Peg(2);
        board[6][3] = new Peg(2);
        board[6][4] = new Peg(2);
        board[6][5] = new Peg(0);
        board[6][6] = new Peg(0);
    }

    public void moveRight(int i, int jTo) {
        updateLastBoard();
        board[i][jTo-2].setValue(1);
        board[i][jTo-1].setValue(1);
        board[i][jTo].setValue(2);
    }

    public void moveUp(int iTo, int j) {
        updateLastBoard();
        board[iTo+2][j].setValue(1);
        board[iTo+1][j].setValue(1);
        board[iTo][j].setValue(2);
    }

    public void moveDown(int iTo, int j) {
        updateLastBoard();
        board[iTo-2][j].setValue(1);
        board[iTo-1][j].setValue(1);
        board[iTo][j].setValue(2);
    }

    public void moveLeft(int i, int jTo) {
        updateLastBoard();
        board[i][jTo+2].setValue(1);
        board[i][jTo+1].setValue(1);
        board[i][jTo].setValue(2);
    }

    public void selectPeg(int selectedI, int selectedJ) {
        if (board[selectedI][selectedJ].getValue() != 0) {
            boolean selectedPeg = false;
            int previousI = 0;
            int previousJ = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j].getValue() == 3) {
                        selectedPeg = true;
                        previousI = i;
                        previousJ = j;
                    }
                }
            }
            if (!selectedPeg) {
                if (board[selectedI][selectedJ].getValue() == 2) {
                    board[selectedI][selectedJ].setValue(3);
                }
            }
            else {
                if (board[selectedI] [selectedJ].getValue() == 2) {
                    board[previousI][previousJ].setValue(2);
                    board[selectedI][selectedJ].setValue(3);
                }
                else {
                    if (board[selectedI][selectedJ].getValue() == 1) {
                        move(previousI, previousJ, selectedI, selectedJ);
                    }
                }

            }
        }
    }

    public void move(int iFrom, int jFrom, int iTo, int jTo) {
        if (jFrom == jTo) {
            if (((iTo - iFrom) == 2) && (board[iTo-1][jTo].getValue() == 2)) {
                moveDown(iTo, jTo);
            }
            else {
                if (((iTo - iFrom) == -2) && (board[iTo+1][jTo].getValue() == 2)) {
                    moveUp(iTo, jTo);
                }
            }


        }
        else {
            if (iFrom == iTo) {
                if (((jTo - jFrom) == 2) && (board[iTo][jTo-1].getValue() == 2)) {
                    moveRight(iTo, jTo);

                }
                else {
                    if (((jTo -jFrom == -2) && (board[iTo][jTo+1].getValue() == 2))) {
                        moveLeft(iTo, jTo);
                    }
                }
            }
        }
    }

    // NEEDS TESTING
    public boolean isWin() {
        boolean win = false;
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                if ((board[i][j].getValue() == 2) || (board[i][j].getValue() == 3))  {
                    if ((i == 3) && (j == 3)) {
                        win = true;
                    }
                    else {
                        return false;
                    }
                }

            }
        }
        return win;
    }

    //TO DO TEST
    public boolean isGameOver() {
        Peg[][] aux = new Peg[board.length][board[0].length];
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                aux[i][j] = new Peg();
                aux[i][j].setValue(board[i][j].getValue());
            }
        }
        int auxI;
        int auxJ;
        BoardPegSolitaire auxBoardPegSolitaire = new BoardPegSolitaire(aux);
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                if ((auxBoardPegSolitaire.board[i][j].getValue() == 2) || (auxBoardPegSolitaire.board[i][j].getValue() == 3)) {
                    auxI = i-2;
                    auxJ = j;
                    if (auxI >= 0) {
                        if (auxBoardPegSolitaire.getBoard()[auxI][auxJ].getValue() == 1) {
                            auxBoardPegSolitaire.move(i, j, auxI, auxJ);
                        }

                    }
                    auxI = i+2;
                    if (auxI < auxBoardPegSolitaire.getBoard().length) {
                        if (auxBoardPegSolitaire.getBoard()[auxI][auxJ].getValue() == 1) {
                            auxBoardPegSolitaire.move(i, j, auxI, auxJ);
                        }

                    }
                    auxI = i;
                    auxJ = j-2;
                    if (auxJ >= 0) {
                        if (auxBoardPegSolitaire.getBoard()[auxI][auxJ].getValue() == 1) {
                            auxBoardPegSolitaire.move(i, j, auxI, auxJ);
                        }

                    }
                    auxJ = j+2;
                    if (auxJ < auxBoardPegSolitaire.getBoard()[i].length) {
                        if (auxBoardPegSolitaire.getBoard()[auxI][auxJ].getValue() == 1) {
                            auxBoardPegSolitaire.move(i, j, auxI, auxJ);
                        }

                    }
                }
            }
        }
        boolean gameOver = true;
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                if (board[i][j].getValue() != auxBoardPegSolitaire.getBoard()[i][j].getValue()) {
                    gameOver = false;
                }
            }
        }
        return gameOver;
    }

    public int pegNumber() {
        int pegNumber = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((board[i][j].getValue() == 2) || (board[i][j].getValue() == 3)) {
                    pegNumber++;
                }
            }
        }
        return pegNumber;
    }

    public void goBack() {
        if (anterior != null) {
            for (int iLoop = 0; iLoop < board.length; iLoop++) {
                for (int jLoop = 0; jLoop < board[iLoop].length; jLoop++) {

                    board[iLoop][jLoop].setValue(anterior[iLoop][jLoop].getValue());
                }
            }
        }
    }

    public void updateLastBoard() {
        anterior = new Peg[board.length][board[0].length];
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                anterior[i][j] = new Peg();
                anterior[i][j].setValue(board[i][j].getValue());
            }
        }
    }
}
