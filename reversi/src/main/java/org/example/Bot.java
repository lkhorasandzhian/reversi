package org.example;

public final class Bot extends Participant {

    public Bot(GameChips color) {
        super("Bot", color);
    }

    @Override
    public String makeMove(GameChips[][] field) {
        String move = makeEasyMove(field);
        printChoose(move);
        return move;
    }

    private static String makeEasyMove(GameChips[][] field) {
        String move = "";

        int x = 0;
        int y = 0;

        double maxCoefficient = 0;

        double currentCoefficient;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == GameChips.AVAILABLE) {
                    currentCoefficient = calculateEasyCoefficient(field, i, j);
                    if (currentCoefficient > maxCoefficient) {
                        maxCoefficient = currentCoefficient;
                        x = i;
                        y = j;
                        char first = (char)(j + 'a');
                        char second = (char)(8 - i + '0');
                        move = first + "" + second;
                    }
                }
            }
        }

        field[x][y] = GameChips.WHITE;

        return move;
    }

    private static double calculateEasyCoefficient(GameChips[][] field, int x, int y) {
        double ss;
        if (x % 7 == 0 && y % 7 == 0) {
            ss = 0.8;
        } else if (x % 7 == 0 || y % 7 == 0) {
            ss = 0.4;
        } else {
            ss = 0;
        }

        var friendColor = field[x][y];
        var opponentColor = friendColor == GameChips.BLACK ? GameChips.WHITE : GameChips.BLACK;

        double siSum = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (Game.isCorrectRange(x + i) && Game.isCorrectRange(y + j) && field[x + i][y + j] == opponentColor) {
                    for (int k = 2; ; k++) {
                        if (!Game.isCorrectRange(x + i * k) || !Game.isCorrectRange(y + j * k) || field[x + i * k][y + j * k] == GameChips.EMPTY) {
                            break;
                        }
                        if (field[x + i * k][y + j * k] == friendColor) {
                            for (int l = k - 1; l >= 1; l--) {
                                if ((x + i * l) % 7 == 0 || (y + j * l) % 7 == 0) {
                                    siSum += 2;
                                } else {
                                    siSum += 1;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        return siSum + ss;
    }

    private static void printChoose(String move) {
        System.out.println("Move: " + move + System.lineSeparator());
    }
}
