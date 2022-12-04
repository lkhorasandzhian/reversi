package org.example;

public abstract class Participant {
    protected int score = 0;
    protected String name;
    protected GameChips color;

    protected Participant(String name, GameChips color) {
        this.name = name;
        this.color = color;
    }

    protected abstract String makeMove(GameChips[][] field);
}
