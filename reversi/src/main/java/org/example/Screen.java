package org.example;

public final class Screen {
    private static String topPlayer;
    private static int topScore;

    public static void printMenu() {
        System.out.println("REVERSI GAME");
        System.out.println("------------");
        System.out.println("1. PLAY");
        System.out.println("2. TOP SCORE");
    }

    public static void printPlaySection() {
        System.out.println("EASY BOT");
        System.out.println("ADVANCED BOT");
        System.out.println("PvP");
    }

    public static void printTopScore() {
        System.out.println("THE BEST PLAYER");
        System.out.println("Name: " + topPlayer);
        System.out.println("Score: " + topScore);
    }

    public static void printField(int[][] field) {
        String figure;

        System.out.println("—————————————————————————————————————————————————————————");
        for (var line : field) {
            for (var cell : line) {
                figure = switch (cell) {
                    case 1 -> "  ⚪  ";
                    case 2 -> "  ⚫  ";
                    case 3 -> "  ◌   ";
                    default  -> "      ";
                };
                System.out.print("|" + figure);
            }
            System.out.println("|");
            System.out.println("—————————————————————————————————————————————————————————");
        }
    }
}
