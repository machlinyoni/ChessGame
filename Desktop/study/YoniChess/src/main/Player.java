package main;

import main.enums.Color;

import java.util.Objects;

public class Player {
    private Color color;

    private String name;

    public Player(String name,Color color) {
        this.name = name;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String printPlayer(){
        return this.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return color == player.color && Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, name);
    }
}
