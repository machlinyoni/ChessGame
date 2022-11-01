package main;

import main.enums.Color;
import main.enums.Mode;

public class Kone extends ChessTool {
    public static final int REPRESENTATION_OF_ROW_INDEX = 0;

    public static final int REPRESENTATION_OF_COLUMN_INDEX = 1;
    public Kone(Color color, String name, int row, int column) {
        super(color,name,row,column);
    }

    public boolean validateMove(int[] fromIndexes, int[] toIndexes,ChessBoard board){
        return (Math.abs( fromIndexes[ REPRESENTATION_OF_ROW_INDEX ] - toIndexes[ REPRESENTATION_OF_ROW_INDEX ] ) == 2
                && Math.abs( fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] - toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] ) == 1)
                || (Math.abs( fromIndexes[ REPRESENTATION_OF_ROW_INDEX ] - toIndexes[ REPRESENTATION_OF_ROW_INDEX ] ) == 1
                && Math.abs( fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] - toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] ) == 2);
    }
}
