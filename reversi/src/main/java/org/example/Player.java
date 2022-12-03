package org.example;

import java.util.NoSuchElementException;

public final class Player extends Participant {
    public Player(String name) {
        super(name);
    }

    public static Player createPlayer() {
        String name;
        while (true) {
            System.out.print("Player name: ");
            try {
                name = Main.input.nextLine();
                if (1 <= name.length() && name.length() <= 25) {
                    return new Player(name);
                }
                System.out.println(System.lineSeparator() + "Incorrect name. Size must be in range [1;25]...");
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
    protected void makeMove() {

    }
}
