package com.example.gamecenter;


import java.io.Serializable;

public class Board2048 implements Serializable {
    private Tile2048[][] board;
    private int score = 0;
    private Tile2048[][] lastBoard;
    private int lastScore;

    public Board2048() {
        setBoard(new Tile2048[4][4]);
        for (int i = 0; i<getBoard().length;i++) {
            for (int j = 0; j<getBoard()[i].length;j++) {
                getBoard()[i][j] = new Tile2048();
            }
        }
    }

    public Tile2048[][] getBoard() {
        return board;
    }

    public void setBoard(Tile2048[][] board) {
        this.board = board;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String toString(){
        String s = "";
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                s += board[i][j].toString() + " ";
            }
            s += "\n";
        }
        return s;
    }

    public Tile2048[][] getLastBoard() {
        return lastBoard;
    }

    public void setLastBoard(Tile2048[][] lastBoard) {
        this.lastBoard = lastBoard;
    }

    public void goBack() {
        if (lastBoard != null) {
            score = lastScore;
            board = new Tile2048[lastBoard.length][lastBoard[0].length];
            for (int i = 0; i<lastBoard.length; i++) {
                for (int j = 0; j<lastBoard[i].length; j++) {
                    board[i][j] = new Tile2048();
                    board[i][j].setValue(lastBoard[i][j].getValue());
                }
            }
        }
    }

    public int getHighTile() {
        int aux = 0;
        for (int i = 0; i<getBoard().length;i++) {
            for (int j = 0; j<getBoard()[i].length;j++) {
                if (getBoard()[i][j].getValue() > aux) {
                    aux = getBoard()[i][j].getValue();
                }
            }
        }
        return aux;
    }


    public void spawn() {
        int aux = 0;
        for (int i = 0; i<getBoard().length;i++) {
            for (int j = 0; j<getBoard()[i].length;j++) {
                if (getBoard()[i][j].getValue() == 0) {
                    aux++;
                }
            }
        }
        Tile2048[] auxArray = new Tile2048[aux];
        aux = 0;
        for (int i = 0; i<getBoard().length;i++) {
            for (int j = 0; j<getBoard()[i].length;j++) {
                if (getBoard()[i][j].getValue() == 0) {
                    auxArray[aux] = getBoard()[i][j];
                    aux++;
                }
            }
        }
        if (Math.random()*100 <= 90) {
            auxArray[(int)(Math.random()*aux)].setValue(2);
        }
        else {
            auxArray[(int)(Math.random()*aux)].setValue(4);
        }
    }

    public boolean isBlackOut() {
        boolean blackout = true;
        int i = 0;
        while((i < getBoard().length) && blackout){
            int j = 0;
            while((j < getBoard()[i].length) && blackout){
                if (getBoard()[i][j].getValue() == 0) {
                    blackout = false;
                }
                j++;
            }
            i++;
        }
        return blackout;
    }

    public boolean changeLeft() {
        boolean hasChanged = false;
        Board2048 aux = new Board2048();
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                aux.board[i][j].setValue(board[i][j].getValue());
            }
        }
        aux.left();
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                if (board[i][j].getValue() != aux.getBoard()[i][j].getValue()) {
                    hasChanged = true;
                }
            }
        }

        return hasChanged;
    }

    public boolean changeRight() {
        boolean hasChanged = false;
        Board2048 aux = new Board2048();
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                aux.board[i][j].setValue(board[i][j].getValue());
            }
        }
        aux.right();
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                if (board[i][j].getValue() != aux.getBoard()[i][j].getValue()) {
                    hasChanged = true;
                }
            }
        }

        return hasChanged;
    }

    public boolean changeDown() {
        boolean hasChanged = false;
        Board2048 aux = new Board2048();
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                aux.board[i][j].setValue(board[i][j].getValue());
            }
        }
        aux.down();
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                if (board[i][j].getValue() != aux.getBoard()[i][j].getValue()) {
                    hasChanged = true;
                }
            }
        }

        return hasChanged;
    }

    public boolean changeUp() {
        boolean hasChanged = false;
        Board2048 aux = new Board2048();
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                aux.board[i][j].setValue(board[i][j].getValue());
            }
        }
        aux.up();
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                if (board[i][j].getValue() != aux.getBoard()[i][j].getValue()) {
                    hasChanged = true;
                }
            }
        }

        return hasChanged;
    }


    public boolean isGameOver() { //FALTA TESTEAR
        Boolean gameOver = false;
        if (isBlackOut()) {
            gameOver = true;
            Board2048 aux = new Board2048();
            for (int i = 0; i<board.length; i++) {
                for (int j = 0; j<board[i].length; j++) {
                    aux.board[i][j].setValue(board[i][j].getValue());
                }
            }
            aux.left();
            aux.right();
            aux.up();
            aux.down();
            for (int i = 0; i<board.length; i++) {
                for (int j = 0; j<board[i].length; j++) {
                    if (board[i][j].getValue() != aux.getBoard()[i][j].getValue()) {
                        gameOver = false;
                    }
                }
            }
        }
        return gameOver;
    }

    public void updateLastBoard() {
        lastScore = score;
        lastBoard = new Tile2048[board.length][board[0].length];
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                lastBoard[i][j] = new Tile2048();
                lastBoard[i][j].setValue(board[i][j].getValue());
            }
        }
    }

    public void up() {
        updateLastBoard();
        for (int aux = 0; aux < board.length; aux++) {
            for (int i = board.length-1; i > 0; i--) {
                for (int j = 0; j<board[i].length; j++) {
                    if (board[i-1][j].getValue() == 0 && board[i][j].getValue() != 0) {
                        board[i-1][j].setValue(board[i][j].getValue());
                        board[i][j].setValue(0);
                    }
                }
            }
        }

        for (int i = 0; i < board.length-1; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getValue() == board[i+1][j].getValue()) {
                    board[i][j].setValue(board[i][j].getValue() * 2);
                    score += board[i][j].getValue();
                    board[i+1][j].setValue(0);
                }
            }
        }

        for (int aux = 0; aux < board.length; aux++) {
            for (int i = board.length-1; i > 0; i--) {
                for (int j = 0; j<board[i].length; j++) {
                    if (board[i-1][j].getValue() == 0 && board[i][j].getValue() != 0) {
                        board[i-1][j].setValue(board[i][j].getValue());
                        board[i][j].setValue(0);
                    }
                }
            }
        }
    }

    public void down() {
        updateLastBoard();
        for (int aux = 0; aux < board.length-1; aux++) {
            for (int i = 0; i < board.length-1; i++) {
                for (int j = 0; j<board[i].length; j++) {
                    if (board[i+1][j].getValue() == 0 && board[i][j].getValue() != 0) {
                        board[i+1][j].setValue(board[i][j].getValue());
                        board[i][j].setValue(0);
                    }
                }
            }
        }

        for (int i = board.length-1; i > 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getValue() == board[i-1][j].getValue()) {
                    board[i][j].setValue(board[i][j].getValue() * 2);
                    score += board[i][j].getValue();
                    board[i-1][j].setValue(0);
                }
            }
        }

        for (int aux = 0; aux < board.length-1; aux++) {
            for (int i = 0; i < board.length-1; i++) {
                for (int j = 0; j<board[i].length; j++) {
                    if (board[i+1][j].getValue() == 0 && board[i][j].getValue() != 0) {
                        board[i+1][j].setValue(board[i][j].getValue());
                        board[i][j].setValue(0);
                    }
                }
            }
        }
    }

    public void left(){
        updateLastBoard();
        for (int aux = 0; aux < board.length; aux++) {
            for (int i = 0; i< board.length; i++) {
                for (int j = board.length-1; j > 0; j--) {
                    if (board[i][j-1].getValue() == 0 && board[i][j].getValue() != 0) {
                        board[i][j-1].setValue(board[i][j].getValue());
                        board[i][j].setValue(0);
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length-1; j++) {
                if (board[i][j].getValue() == board[i][j+1].getValue()) {
                    board[i][j].setValue(board[i][j].getValue() * 2);
                    score += board[i][j].getValue();
                    board[i][j+1].setValue(0);
                }
            }
        }

        for (int aux = 0; aux < board.length; aux++) {
            for (int i = 0; i< board.length; i++) {
                for (int j = board.length-1; j > 0; j--) {
                    if (board[i][j-1].getValue() == 0 && board[i][j].getValue() != 0) {
                        board[i][j-1].setValue(board[i][j].getValue());
                        board[i][j].setValue(0);
                    }
                }
            }
        }
    }

    public void right() {
        updateLastBoard();
        for (int aux = 0; aux < board.length-1; aux++) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j<board[i].length-1; j++) {
                    if (board[i][j+1].getValue() == 0 && board[i][j].getValue() != 0) {
                        board[i][j+1].setValue(board[i][j].getValue());
                        board[i][j].setValue(0);
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = board[i].length-1; j > 0; j--) {
                if (board[i][j].getValue() == board[i][j-1].getValue()) {
                    board[i][j].setValue(board[i][j].getValue() * 2);
                    score += board[i][j].getValue();
                    board[i][j-1].setValue(0);
                }
            }
        }

        for (int aux = 0; aux < board.length-1; aux++) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j<board[i].length-1; j++) {
                    if (board[i][j+1].getValue() == 0 && board[i][j].getValue() != 0) {
                        board[i][j+1].setValue(board[i][j].getValue());
                        board[i][j].setValue(0);
                    }
                }
            }
        }
    }




}
