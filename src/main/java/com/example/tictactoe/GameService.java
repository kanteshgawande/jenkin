package com.example.tictactoe;

import org.springframework.stereotype.Service;

@Service
public class GameService {

    private String[] board = new String[9];
    private String player;
    private boolean gameOver;

    public GameService() {
        reset();
    }

    public String[] getBoard() {
        return board;
    }

    public String getPlayer() {
        return player;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String makeMove(int index) {
        if (board[index] != null || gameOver) return "Invalid";

        board[index] = player;

        if (checkWin()) {
            gameOver = true;
            return player + " wins";
        }

        player = player.equals("X") ? "O" : "X";
        return "Next";
    }

    public void reset() {
        for (int i = 0; i < 9; i++) board[i] = null;
        player = Math.random() < 0.5 ? "X" : "O";
        gameOver = false;
    }

    private boolean checkWin() {
        int[][] wins = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };

        for (int[] w : wins) {
            if (board[w[0]] != null &&
                board[w[0]].equals(board[w[1]]) &&
                board[w[1]].equals(board[w[2]])) {
                return true;
            }
        }
        return false;
    }
}