package org.example;

public enum GameChips {
    EMPTY {
        @Override
        public String toString() {
            return "      ";
        }
    },
    WHITE {
        @Override
        public String toString() {
            return "  ⚪  ";
        }
    },
    BLACK {
        @Override
        public String toString() {
            return "  ⚫  ";
        }
    },
    AVAILABLE {
        @Override
        public String toString() {
            return "  ◌   ";
        }
    }
}
