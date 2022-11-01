package main;

import main.enums.Color;
import main.enums.Mode;

public class Peshka extends ChessTool {

    public static final int REPRESENTAION_OF_ROW_INDEX = 0;
    public static final int REPRESENTAION_OF_COLUMN_INDEX = 1;

    private boolean isFirstMove;

    public Peshka(Color color, String name, int row, int column) {
        super(color,name,row,column);
        this.isFirstMove = true;
    }
    public boolean validateMove(int[] fromIndexes, int[] toIndexes,ChessBoard board) {
        int firstMove =1;
        int diff;
        int diagonal;
        if ( isFirstMove ) {
            firstMove = 2;
        }
        if(fromIndexes[REPRESENTAION_OF_ROW_INDEX] != toIndexes[REPRESENTAION_OF_ROW_INDEX] && fromIndexes[REPRESENTAION_OF_COLUMN_INDEX] != toIndexes[REPRESENTAION_OF_COLUMN_INDEX] && board.getTool( toIndexes[REPRESENTAION_OF_ROW_INDEX],toIndexes[REPRESENTAION_OF_COLUMN_INDEX]).getColor()!= this.getColor() && board.getTool( toIndexes[REPRESENTAION_OF_ROW_INDEX],toIndexes[REPRESENTAION_OF_COLUMN_INDEX]) != null){
            if(Color.W == this.getColor()) {
                diagonal = fromIndexes[REPRESENTAION_OF_ROW_INDEX] - toIndexes[REPRESENTAION_OF_ROW_INDEX];
            }else {
                diagonal = toIndexes[REPRESENTAION_OF_ROW_INDEX] - fromIndexes[REPRESENTAION_OF_ROW_INDEX];
            }
            return diagonal == 1 && Math.abs( fromIndexes[ REPRESENTAION_OF_COLUMN_INDEX ] - toIndexes[ REPRESENTAION_OF_COLUMN_INDEX ] ) == 1;
        }else{
            if (Color.W == this.getColor()) {
                diff = fromIndexes[REPRESENTAION_OF_ROW_INDEX] - toIndexes[REPRESENTAION_OF_ROW_INDEX];
            } else {
                diff = toIndexes[REPRESENTAION_OF_ROW_INDEX] - fromIndexes[REPRESENTAION_OF_ROW_INDEX];
            }
            return diff <= firstMove && diff > 0 && fromIndexes[ REPRESENTAION_OF_COLUMN_INDEX ] == toIndexes[ REPRESENTAION_OF_COLUMN_INDEX ];
        }
        }


    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }
}
