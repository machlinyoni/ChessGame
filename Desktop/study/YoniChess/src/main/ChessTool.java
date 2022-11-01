package main;

import main.enums.Color;
import main.enums.Mode;

import java.util.Objects;

public abstract class ChessTool {

    private final Color color;
    private final boolean isAlive;
    private final String name;
    private int row;
    private int column;
    private boolean isFirstMove;

    public ChessTool(Color color, String name, int row, int column) {
        this.color = color;
        this.isAlive = true;
        this.name = name;
        this.row = row;
        this.column = column;
        isFirstMove = true;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }

    public Color getColor() {
        return color;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public String getName() {
        return name;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    public int[] getIndexes(){
        int[] indexes = new int[2];
        indexes[0] = this.row;
        indexes[1] = this.column;
        return indexes;
    }

    private void setRow(int row) {
        this.row = row;
    }

    private void setColumn(int column) {
        this.column = column;
    }

    public void setIndexes(int row,int column){
        setRow( row );
        setColumn( column );
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        ChessTool chessTool = (ChessTool) o;
        return isAlive == chessTool.isAlive && row == chessTool.row && column == chessTool.column && color == chessTool.color && Objects.equals( name, chessTool.name );
    }

    public String printTool(){
        return this.name + this.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash( color, isAlive, name, row, column );
    }

    @Override
    public String toString() {
        return "ChessTool{" + "color=" + color +
                ", isAlive=" + isAlive +
                ", name='" + name + '\'' +
                ", row=" + row +
                ", column=" + column +
                '}';
    }

    public abstract boolean validateMove(int[] fromIndexes, int[] toIndexes, ChessBoard board);


}



