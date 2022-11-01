package main;

import main.enums.Color;
import main.enums.Mode;

public class Ladya extends ChessTool {

    public static final int REPRESENTATION_OF_ROW_INDEX = 0;

    public static final int REPRESENTATION_OF_COLUMN_INDEX = 1;
    private boolean isFirstMove;

    public Ladya(Color color, String name, int row, int column) {
        super(color,name,row,column);
        boolean isFirstMove = true;
    }
    public boolean validateMove(int[] fromIndexes, int[] toIndexes,ChessBoard board){
        return ((Math.abs( fromIndexes[ REPRESENTATION_OF_ROW_INDEX ] - toIndexes[ REPRESENTATION_OF_ROW_INDEX ] ) <= 7 && Math.abs( fromIndexes[ REPRESENTATION_OF_ROW_INDEX ] - toIndexes[ REPRESENTATION_OF_ROW_INDEX ] ) > 0 && fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] == toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] )
                || (Math.abs( fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] - toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] ) <= 7 && Math.abs( fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] - toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] ) > 0 && fromIndexes[ REPRESENTATION_OF_ROW_INDEX ] == toIndexes[ REPRESENTATION_OF_ROW_INDEX ] ))
                && (validateRow( fromIndexes, toIndexes, board ));
    }
    private boolean validateRow(int[] fromIndexes, int[] toIndexes,ChessBoard board) {
        if (fromIndexes[ REPRESENTATION_OF_ROW_INDEX ] == toIndexes[ REPRESENTATION_OF_ROW_INDEX ] && fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] < toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ]) {
            for ( int i = 1; i < toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] - fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ]; i++) {
                if(!board.isEmpty(fromIndexes[ REPRESENTATION_OF_ROW_INDEX ],toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] - i)){
                    System.out.println( "Way is blocked \n" );
                    return false;
                }
            }
        }else if (fromIndexes[ REPRESENTATION_OF_ROW_INDEX ] == toIndexes[ REPRESENTATION_OF_ROW_INDEX ] && fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] > toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ]){
            for ( int i = 1; i < fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] - toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ]; i++) {
                if(!board.isEmpty(fromIndexes[ REPRESENTATION_OF_ROW_INDEX ],fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] - i)){
                    System.out.println( "Way is blocked \n" );
                    return false;
                }
            }
        }else if (fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] == toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] && fromIndexes[ REPRESENTATION_OF_ROW_INDEX ] < toIndexes[ REPRESENTATION_OF_ROW_INDEX ]){
            for ( int i = 1; i < toIndexes[ REPRESENTATION_OF_ROW_INDEX ] - fromIndexes[ REPRESENTATION_OF_ROW_INDEX ]; i++) {
                if(!board.isEmpty(toIndexes[ REPRESENTATION_OF_ROW_INDEX ] - i,fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ])){
                    System.out.println( "Way is blocked \n" );
                    return false;
                }
            }
        }else if (fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] == toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] && fromIndexes[ REPRESENTATION_OF_ROW_INDEX ] > toIndexes[ REPRESENTATION_OF_ROW_INDEX ]){
            for ( int i = 1; i < fromIndexes[ REPRESENTATION_OF_ROW_INDEX ] - toIndexes[ REPRESENTATION_OF_ROW_INDEX ]; i++) {
                if(!board.isEmpty(fromIndexes[ REPRESENTATION_OF_ROW_INDEX ] - i,fromIndexes[ REPRESENTATION_OF_COLUMN_INDEX ])){
                    System.out.println( "Way is blocked \n" );
                    return false;
                }
            }
        }

        return true;

    }
    }

