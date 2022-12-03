package org.example;

import java.util.NoSuchElementException;

public final class Game {
    private static final GameChips[][] field = new GameChips[8][8];
    private static final Player topPlayer;

    static {
        topPlayer = new Player("---");
        field[3][3] = GameChips.WHITE;
        field[4][4] = GameChips.WHITE;
        field[4][3] = GameChips.BLACK;
        field[3][4] = GameChips.BLACK;
        field[3][2] = GameChips.AVAILABLE;
        field[2][3] = GameChips.AVAILABLE;
        field[5][4] = GameChips.AVAILABLE;
        field[4][5] = GameChips.AVAILABLE;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == null) {
                    field[i][j] = GameChips.EMPTY;
                }
            }
        }
    }

    public static void StartProcess() {
        while (true) {
            Screen.printMenu();
            int signal = receiveSignal(4);
            System.out.println();
            if (signal == 1) {
                Screen.printPlaySection();
                int mode = receiveSignal(4);
                if (mode != 0) {
                    startGame(mode);
                }
            } else if (signal == 2) {
                Screen.printTopScore(topPlayer);
            } else if (signal == 3) {
                Screen.printSettings();
                switch (receiveSignal(2)) {
                    case 1 -> removeBestPlayer();
                    case 0 -> {}
                }
            } else if (signal == 0) {
                Screen.printEndTitle();
                return;
            }
            System.out.println();
        }
    }

    private static int receiveSignal(int countOfItems) {
        int signal;
        while (true) {
            try {
                signal = Integer.parseInt(Main.input.nextLine());
                if (0 <= signal && signal <= countOfItems - 1) {
                    return signal;
                }
                System.out.println(System.lineSeparator() + "Incorrect number. Please, select one from the list...");
            } catch (NoSuchElementException exception) {
                System.out.println(System.lineSeparator() + "Shortcut Ctrl+D or something else was pressed");
                System.out.println("Restart application whether you want play again");
                System.exit(0);
            } catch (Throwable exception) {
                System.out.println(System.lineSeparator() + "Incorrect input. Please, try again...");
            }
        }
    }

    private static void startGame(int mode) {
        Screen.printField(field);

        Participant participant1 = Player.createPlayer();
        Participant participant2 = switch (mode) {
            case 1 -> new Bot(false);
            case 2 -> new Bot(true);
            default -> Player.createPlayer();
        };

        do {
            participant1.makeMove();
            Screen.printField(field);
            System.out.println();
            participant2.makeMove();
            Screen.printField(field);
            System.out.println();
        } while (!participant1.isWinner && !participant2.isWinner);
    }

    private static void removeBestPlayer() {
        topPlayer.name = "---";
        topPlayer.score = 0;
    }
}
