package main.enums;

public enum Color {
        W("White"),
        B("Black");

        private String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
