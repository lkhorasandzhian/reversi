package org.example;

public final class Screen {
    public static void printMenu() {
        System.out.println("REVERSI GAME");
        System.out.println("------------");
        System.out.println("1. PLAY");
        System.out.println("2. TOP SCORE");
        System.out.println("3. SETTINGS");
        System.out.println("0. EXIT");
    }

    public static void printPlaySection() {
        System.out.println("1. EASY BOT");
        System.out.println("2. PvP");
        System.out.println("0. BACK");
    }

    public static void printTopScore(Player topPlayer) {
        System.out.println("THE BEST PLAYER");
        if (topPlayer != null) {
            System.out.println("Name: " + topPlayer.name);
            System.out.println("Score: " + topPlayer.score);
        } else {
            System.out.println("No information");
        }
    }

    public static void printSettings() {
        System.out.println("1. REMOVE BEST PLAYER");
        System.out.println("0. BACK");
    }

    public static void printEndTitle() {
        System.out.println("""
                ░██████╗░░█████╗░░█████╗░██████╗░██████╗░██╗░░░██╗███████╗
                ██╔════╝░██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗░██╔╝██╔════╝
                ██║░░██╗░██║░░██║██║░░██║██║░░██║██████╦╝░╚████╔╝░█████╗░░
                ██║░░╚██╗██║░░██║██║░░██║██║░░██║██╔══██╗░░╚██╔╝░░██╔══╝░░
                ╚██████╔╝╚█████╔╝╚█████╔╝██████╔╝██████╦╝░░░██║░░░███████╗
                ░╚═════╝░░╚════╝░░╚════╝░╚═════╝░╚═════╝░░░░╚═╝░░░╚══════╝""");
    }

    public static void printScoreTable(Participant participant1, Participant participant2) {
        System.out.print(System.lineSeparator() + "                      ");
        System.out.println(participant1.color + " " + participant1.score + "      " + participant2.color + " " + participant2.score);
    }

    public static void printField(GameChips[][] field) {
        char number = '8';
        System.out.println("  —————————————————————————————————————————————————————————————————");
        for (var line : field) {
            System.out.print(number--);
            for (var chip : line) {
                System.out.print(" |" + chip);
            }
            System.out.println(" |");
            System.out.println("  —————————————————————————————————————————————————————————————————");
        }
        System.out.print("      ");
        for (char letter = 'a'; letter <= 'h'; letter++) {
            System.out.print(letter + "       ");
        }
        System.out.println();
    }

    public static void printTurn(Participant participant) {
        System.out.println(participant.name + "'s (" + participant.color + ") turn.");
    }

    public static void printAvailableMoves(GameChips[][] field) {
        System.out.print("Available moves: ");

        char x;
        char y;

        boolean hasMoves = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == GameChips.AVAILABLE) {
                    x = (char)(j + 'a');
                    y = (char)(8 - i + '0');
                    if (!hasMoves) {
                        System.out.print(x + "" + y);
                        hasMoves = true;
                    } else {
                        System.out.print(", " + x + "" + y);
                    }
                }
            }
        }

        if (!hasMoves) {
            System.out.print("empty");
        }
        System.out.println(".");
    }

    public static void printGameResult(Participant participant1, Participant participant2, Participant winner) {
        System.out.println("GAME INFO:");
        System.out.println(participant1.name + " (" + participant1.color + ") has " + participant1.score + " score");
        System.out.println(participant2.name + " (" + participant2.color + ") has " + participant2.score + " score");
        System.out.println();
        if (winner != null) {
            System.out.println(winner.name + " WIN!");
        }else {
            System.out.println("DRAW!");
        }
    }
}
