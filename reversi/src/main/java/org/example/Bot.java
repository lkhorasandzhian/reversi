package org.example;

public final class Bot extends Participant {
    private static int number = 1;
    private Boolean isAdvanced;

    public Bot(Boolean isAdvanced) {
        super("Bot");
        this.isAdvanced = isAdvanced;
    }

    @Override
    protected void makeMove() {

    }
}
