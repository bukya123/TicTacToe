package Stratergies.WinningStrategy;

import Models.Board;
import Models.Move;

public interface WinningStrategy {
    public boolean checkWinner(Board board, Move move);
    public void undo(Board board, Move move);
}
