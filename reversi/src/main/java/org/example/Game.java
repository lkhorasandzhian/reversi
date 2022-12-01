package org.example;

import java.util.Scanner;

public class Game {
    private static final Scanner input = new Scanner(System.in);
    private static int[][] field = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 3, 0, 0, 0, 0},
            {0, 0, 3, 1, 2, 0, 0, 0},
            {0, 0, 0, 2, 1, 3, 0, 0},
            {0, 0, 0, 0, 3, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };

    public static void StartProcess() {
        int code;
        while (true) {
            Screen.printMenu();
            code = input.nextInt();
            System.out.println();
            Screen.printField(field);
        }
    }
}
