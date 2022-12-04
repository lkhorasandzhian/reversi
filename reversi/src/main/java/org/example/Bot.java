package org.example;

public final class Bot extends Participant {
    private static int number = 1;
    private boolean isAdvanced;

    public Bot(boolean isAdvanced, GameChips color) {
        super("Bot", color);
        this.isAdvanced = isAdvanced;
    }

    @Override
    public String makeMove(GameChips[][] field) {
        return isAdvanced ? makeAdvancedMove(field) : makeEasyMove(field);
    }

    private String makeEasyMove(GameChips[][] field) {
        return "";
    }

    private String makeAdvancedMove(GameChips[][] field) {
        return "";
    }
}
