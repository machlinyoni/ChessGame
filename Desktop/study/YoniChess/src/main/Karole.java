package main;

import main.enums.Color;
import main.enums.Mode;

public class Karole extends ChessTool {

    public static final int REPRESENTATION_OF_ROW_INDEX = 0;
    public static final int REPRESENTATION_OF_COLUMN_INDEX = 1;
    private boolean isFirstMove;
    public static boolean castling;

    public Karole(Color color, String name, int row, int column) {
        super(color,name,row,column);
        this.isFirstMove = true;
        castling = false;
    }


    public static void setCastling(boolean castling) {
        Karole.castling = castling;
    }

    public boolean validateMove(int[] fromIndexes, int[] toIndexes, ChessBoard board){
        if (checkForCastling(toIndexes,board)){
            castling = true;
            return false;
        }else{
        return (Math.abs( fromIndexes[ REPRESENTATION_OF_ROW_INDEX ] - toIndexes[ REPRESENTATION_OF_ROW_INDEX ] ) == 1 && (fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] - toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] == 0))
                || (Math.abs( fromIndexes[ REPRESENTATION_OF_ROW_INDEX ] - toIndexes[ REPRESENTATION_OF_ROW_INDEX ] ) == 0 && Math.abs( fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] - toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] ) == 1)
                || ((Math.abs( fromIndexes[ REPRESENTATION_OF_ROW_INDEX ] - toIndexes[ REPRESENTATION_OF_ROW_INDEX ] ) ==  Math.abs( fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] - toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] ) ));
    }
    }

    public int[] getIndexes(){
        int[] indexes = new int[2];
        indexes[0] = this.getRow();
        indexes[1] = this.getColumn();
        return indexes;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }


    public boolean checkForCastling(int[] indexes,ChessBoard board) {
        int rowToCheck = this.getColor() == Color.W ? 7:0;
        if ( isFirstMove() && board.getTool( rowToCheck,7 ).isFirstMove() && indexes[1] == 6   && indexes[0] == rowToCheck  && validateCastlingIsPossible( "Right Castling" ,board) ) {
                int[] rightCastlingKingIndexes = new int[ 2 ];
                int[] rightCastlingRookIndexes = new int[ 2 ];
                rightCastlingKingIndexes[ 0 ] = rowToCheck;
                rightCastlingKingIndexes[ 1 ] = 6;
                rightCastlingRookIndexes[ 0 ] = rowToCheck;
                rightCastlingRookIndexes[ 1 ] = 5;
                board.moveTool( rightCastlingKingIndexes, this );
                board.moveTool( rightCastlingRookIndexes, board.getTool( rowToCheck, 7 ) );
                return true;

        } else if ( isFirstMove() && board.getTool( rowToCheck,0 ).isFirstMove() && indexes[1] == 2  && indexes[0] == rowToCheck && validateCastlingIsPossible( "Left Castling", board ) ) {
                int[] leftCastlingKingIndexes = new int[ 2 ];
                int[] leftCastlingRookIndexes = new int[ 2 ];
                leftCastlingKingIndexes[ 0 ] = rowToCheck;
                leftCastlingKingIndexes[ 1 ] = 2;
                leftCastlingRookIndexes[ 0 ] = rowToCheck;
                leftCastlingRookIndexes[ 1 ] = 3;
                board.moveTool( leftCastlingKingIndexes, this );
                board.moveTool( leftCastlingRookIndexes, board.getTool( rowToCheck, 0 ) );
                return true;
        }else{
                return false;
            }
        }


    public boolean validateCastlingIsPossible(String type,ChessBoard board) {
        int rowIndexByColor = getColor() == Color.W ? 7 : 0;
        if ( type.equals( "Right Castling" ) ) {
            int[] indexesToCheckFirst = new int[ 2 ];
            int[] indexesToCheckSecond = new int[ 2 ];
            indexesToCheckFirst[ 0 ] = rowIndexByColor;
            indexesToCheckFirst[ 1 ] = 5;
            indexesToCheckSecond[ 0 ] = rowIndexByColor;
            indexesToCheckSecond[ 1 ] = 6;
            return board.isEmpty( rowIndexByColor, 5 ) && board.isEmpty( rowIndexByColor, 6 ) && !board.validateFieldIsThreatened( indexesToCheckFirst, this.getColor() ) && !board.validateFieldIsThreatened( indexesToCheckSecond, this.getColor() );

        } else if ( type.equals( "Left Castling" ) ) {
            int[] indexesToCheckFirst = new int[ 2 ];
            int[] indexesToCheckSecond = new int[ 2 ];
            int[] indexesToCheckThird = new int[ 2 ];
            indexesToCheckFirst[ 0 ] = rowIndexByColor;
            indexesToCheckFirst[ 1 ] = 1;
            indexesToCheckSecond[ 0 ] = rowIndexByColor;
            indexesToCheckSecond[ 1 ] = 2;
            indexesToCheckThird[ 0 ] = rowIndexByColor;
            indexesToCheckThird[ 1 ] = 3;
            return board.isEmpty( rowIndexByColor, 1 ) && board.isEmpty( rowIndexByColor, 2 ) && board.isEmpty( rowIndexByColor, 3 ) && !board.validateFieldIsThreatened( indexesToCheckFirst, this.getColor() ) && !board.validateFieldIsThreatened( indexesToCheckSecond, this.getColor() ) && !board.validateFieldIsThreatened( indexesToCheckThird, this.getColor() );
        }
        return false;
    }
}
