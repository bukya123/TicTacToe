import Controllers.GameController;
import Exceptions.BotCountException;
import Exceptions.PlayerCountException;
import Exceptions.SymbolCountException;
import Models.*;
import Stratergies.WinningStrategy.ColumnWinningStrategy;
import Stratergies.WinningStrategy.DiagonalWinningStrategy;
import Stratergies.WinningStrategy.RowWinningStrategy;
import Stratergies.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)throws PlayerCountException, BotCountException, SymbolCountException {//TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);
        try {
            int dimensions = 3;
            List<Player> players = new ArrayList<>();
            players.add(new Player(1L, "AB", new Symbol('X'), PlayerType.HUMAN));
            players.add(new Bot(2L, "GPT", new Symbol('O'), PlayerType.BOT, BotDifficultyLevel.EASY));
            List<WinningStrategy> winningStrategies = new ArrayList<>();
            winningStrategies.add(new ColumnWinningStrategy());
            winningStrategies.add(new RowWinningStrategy());
            winningStrategies.add(new DiagonalWinningStrategy());

            Game game = gameController.startGame(dimensions,
                    players, winningStrategies);
            System.out.println("Game has been created");

            while (gameController.checkState(game).equals(GameState.IN_PROGRESS)){
                gameController.displayBoard(game);

                System.out.println("Do you want to do an undo (y/n)");
                String ans = scanner.next();
                if(ans.equalsIgnoreCase("y")){
                    gameController.undo(game);
                    continue;
                }

                gameController.makeMove(game);
            }

            if (gameController.checkState(game).equals(GameState.DRAW))
                System.out.println("Game has been drawn");

            if(gameController.checkState(game).equals(GameState.WIN)){
                System.out.println("Game has been won by " + gameController.getWinner(game).getName());
            }

        } catch (Exception e){
            throw e;
//            System.out.println("Something went wrong in creating the game " +e);
        }
    }
}