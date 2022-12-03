package org.example;

public abstract class Participant {
    protected int score = 0;
    protected String name;
    protected String lastMove;
    protected Boolean isWinner = false;

    protected Participant(String name) {
        this.name = name;
    }

    protected abstract void makeMove();
}
