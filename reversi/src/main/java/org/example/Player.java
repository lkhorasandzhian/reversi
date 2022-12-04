package org.example;

import java.util.NoSuchElementException;

public final class Player extends Participant {
    public Player(String name, GameChips color) {
        super(name, color);
    }

    public static Player createPlayer(GameChips color) {
        String name;
        while (true) {
            System.out.print("Player (" + color + ") name: ");
            try {
                name = Main.input.nextLine();
                if (1 <= name.length() && name.length() <= 10) {
                    return new Player(name, color);
                }
                System.out.println(System.lineSeparator() + "Incorrect name. Size must be in range [1;10]...");
            } catch (NoSuchElementException exception) {
                System.out.println(System.lineSeparator() + "Shortcut Ctrl+D or something else was pressed");
                System.out.println("Restart application whether you want play again");
                System.exit(0);
            } catch (Throwable exception) {
                System.out.println(System.lineSeparator() + "Incorrect input. Please, try again...");
            }
        }
    }

    @Override
    public String makeMove(GameChips[][] field) {
        String move;

        while (true) {
            move = chooseMove();

            int x = 8 - (move.charAt(1) - '0');
            int y = move.charAt(0) - 'a';

            if (field[x][y] == GameChips.AVAILABLE) {
                field[x][y] = this.color;
                return move;
            }
            System.out.println("This cell is unavailable. Please, select another one..." + System.lineSeparator());
        }
    }

    private String chooseMove() {
        String move;
        while (true) {
            try {
                System.out.print("Move: ");
                move = Main.input.nextLine();
                if (move.length() == 2 &&
                    'a' <= move.charAt(0) && move.charAt(0) <= 'h' &&
                    '1' <= move.charAt(1) && move.charAt(1) <= '8') {
                    System.out.println();
                    return move;
                }
                System.out.println(System.lineSeparator() + "Move must contains 1 letter and 1 number. Please, try again...");
            } catch (NoSuchElementException exception) {
                System.out.println(System.lineSeparator() + "Shortcut Ctrl+D or something else was pressed");
                System.out.println("Restart application whether you want play again");
                System.exit(0);
            } catch (Throwable exception) {
                System.out.println(System.lineSeparator() + "Incorrect move. Please, try again...");
            }
        }
    }
}
