package org.example;

import java.util.NoSuchElementException;

public final class Game {
    private static final GameChips[][] field = new GameChips[8][8];
    private static Player topPlayer;

    static {
        topPlayer = new Player("---", GameChips.WHITE);
        field[3][3] = GameChips.WHITE;
        field[4][4] = GameChips.WHITE;
        field[4][3] = GameChips.BLACK;
        field[3][4] = GameChips.BLACK;
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
        System.out.println();
        Participant participant1 = Player.createPlayer(GameChips.BLACK);
        Participant participant2 = switch (mode) {
            case 1 -> new Bot(false, GameChips.WHITE);
            case 2 -> new Bot(true, GameChips.WHITE);
            default -> Player.createPlayer(GameChips.WHITE);
        };

        Participant[] participants = { participant1, participant2 };

        System.out.println();

        int i = 0;
        boolean wasUnavailable = false;
        while (true) {
            Screen.printTurn(participants[i]);
            addAvailableCells(participants[i].color);
            Screen.printAvailableMoves(field);
            addScore(participants);
            Screen.printScoreTable(participant1, participant2);
            Screen.printField(field);
            System.out.println();
            if (hasAvailableCells()) {
                String move = participants[i].makeMove(field);
                clearAvailableCells();
                drawReceivedChips(move);
                wasUnavailable = false;
            } else {
                System.out.println(participants[i].name + " hasn't available moves..." + System.lineSeparator());
                if (wasUnavailable) {
                    break;
                }
                wasUnavailable = true;
            }
            i = (i + 1) % 2;
        }
        Participant winner = determineWinner(participant1, participant2);
        Screen.printGameResult(participant1, participant2, winner);
        if (winner instanceof Player applicant) {
            if (applicant.score > topPlayer.score) {
                topPlayer = applicant;
            }
        }
    }

    private static void addAvailableCells(GameChips playerColor) {
        var opponentColor = playerColor == GameChips.BLACK ? GameChips.WHITE : GameChips.BLACK;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (field[x][y] != GameChips.EMPTY) {
                    continue;
                }
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0) {
                            continue;
                        }
                        if (isCorrectRange(x + i) && isCorrectRange(y + j) && field[x + i][y + j] == opponentColor) {
                            for (int k = 2; ; k++) {
                                if (!isCorrectRange(x + i * k) || !isCorrectRange(y + j * k) || field[x + i * k][y + j * k] == GameChips.EMPTY || field[x + i * k][y + j * k] == GameChips.AVAILABLE) {
                                    break;
                                }
                                if (field[x + i * k][y + j * k] == playerColor) {
                                    field[x][y] = GameChips.AVAILABLE;
                                    break;
                                }
                            }
                            if (field[x][y] == GameChips.AVAILABLE) {
                                break;
                            }
                        }
                    }
                    if (field[x][y] == GameChips.AVAILABLE) {
                        break;
                    }
                }
            }
        }
    }

    private static void clearAvailableCells() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (field[x][y] == GameChips.AVAILABLE) {
                    field[x][y] = GameChips.EMPTY;
                }
            }
        }
    }

    private static boolean hasAvailableCells() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (field[x][y] == GameChips.AVAILABLE) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isCorrectRange(int index) {
        return (0 <= index && index <= 7);
    }

    private static void drawReceivedChips(String move) {
        int x = 8 - (move.charAt(1) - '0');
        int y = move.charAt(0) - 'a';

        var friendColor = field[x][y];
        var opponentColor = friendColor == GameChips.BLACK ? GameChips.WHITE : GameChips.BLACK;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (isCorrectRange(x + i) && isCorrectRange(y + j) && field[x + i][y + j] == opponentColor) {
                    for (int k = 2; ; k++) {
                        if (!isCorrectRange(x + i * k) || !isCorrectRange(y + j * k) || field[x + i * k][y + j * k] == GameChips.EMPTY) {
                            break;
                        }
                        if (field[x + i * k][y + j * k] == friendColor) {
                            for (int l = k - 1; l >= 1; l--) {
                                field[x + i * l][y + j * l] = friendColor;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void addScore(Participant[] participants) {
        int currentScore;
        for (int i = 0; i < 2; i++) {
            currentScore = 0;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    if (field[j][k] == participants[i].color) {
                        ++currentScore;
                    }
                }
            }
            participants[i].score = currentScore;
        }
    }

    private static Participant determineWinner(Participant participant1, Participant participant2) {
        if (participant1.score > participant2.score) {
            return participant1;
        } else if (participant1.score < participant2.score) {
            return participant2;
        }
        return null;
    }

    private static void removeBestPlayer() {
        topPlayer = null;
    }
}
