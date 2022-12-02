package org.example;

import java.util.NoSuchElementException;
import java.util.Scanner;

public final class Game {
    private static final Scanner input = new Scanner(System.in);
    private static final GameChips[][] field = new GameChips[8][8];
    private static String topPlayer = "---";
    private static int topScore = 0;

    static {
        field[3][3] = GameChips.WHITE;
        field[4][4] = GameChips.WHITE;
        field[4][3] = GameChips.BLACK;
        field[3][4] = GameChips.BLACK;
        field[3][2] = GameChips.AVAILABLE;
        field[2][3] = GameChips.AVAILABLE;
        field[5][4] = GameChips.AVAILABLE;
        field[4][5] = GameChips.AVAILABLE;
    }

    public static void StartProcess() {
        while (true) {
            Screen.printMenu();
            int signal = receiveSignal(4);
            System.out.println();
            if (signal == 1) {
                Screen.printPlaySection();
                switch (receiveSignal(4)) {
                    case 1 -> playEasyMode();
                    case 2 -> playAdvancedMode();
                    case 3 -> playPvP();
                    case 0 -> {}
                }
            } else if (signal == 2) {
                Screen.printTopScore(topPlayer, topScore);
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
                signal = Integer.parseInt(input.nextLine());
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

    private static void playEasyMode() {
        Screen.printField(field);
    }

    private static void playAdvancedMode() {
        Screen.printField(field);
    }

    private static void playPvP() {
        Screen.printField(field);
    }

    private static void removeBestPlayer() {
        topPlayer = "---";
        topScore = 0;
    }
}
