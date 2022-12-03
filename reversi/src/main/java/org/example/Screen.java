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
        System.out.println("2. ADVANCED BOT");
        System.out.println("3. PvP");
        System.out.println("0. BACK");
    }

    public static void printTopScore(Player topPlayer) {
        System.out.println("THE BEST PLAYER");
        System.out.println("Name: " + topPlayer.name);
        System.out.println("Score: " + topPlayer.score);
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

    public static void printField(GameChips[][] field) {
        System.out.println("—————————————————————————————————————————————————————————");
        for (var line : field) {
            for (var chip : line) {
                System.out.print("|" + chip);
            }
            System.out.println("|");
            System.out.println("—————————————————————————————————————————————————————————");
        }
    }

    public static void printParticipant(Participant participant) {
        System.out.println(participant.name + "'s turn...");
        System.out.print("Move: ");
    }
}
