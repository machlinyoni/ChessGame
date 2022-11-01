package main;
import main.enums.Color;
import main.enums.Mode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ChessBoard {

    ChessTool[][] board;

    public static final int REPRESENTATION_OF_ROW_INDEX = 0;

    public static final int REPRESENTATION_OF_COLUMN_INDEX = 1;

    private final Scanner scan = new Scanner( System.in );

    public ChessBoard() {
        this.board = new ChessTool[8][8];
    }


    public void init(){
        //black board
        this.board[1][0] = new Peshka(Color.B,"P",1,0);
        this.board[1][1] = new Peshka(Color.B,"P",1,1);
        this.board[1][2] = new Peshka(Color.B,"P",1,2);
        this.board[1][3] = new Peshka(Color.B,"P",1,3);
        this.board[1][4] = new Peshka(Color.B,"P",1,4);
        this.board[1][5] = new Peshka(Color.B,"P",1,5);
        this.board[1][6] = new Peshka(Color.B,"P",1,6);
        this.board[1][7] = new Peshka(Color.B,"P",1,7);
        this.board[0][0] = new Ladya(Color.B,"R",0,0);
        this.board[0][7] = new Ladya(Color.B,"R",0,7);
        this.board[0][1] = new Kone(Color.B,"KN",0,1);
        this.board[0][6] = new Kone(Color.B,"KN",0,6);
        this.board[0][2] = new Slon(Color.B,"B",0,2);
        this.board[0][5] = new Slon(Color.B,"B",0,5);
        this.board[0][3] = new Karaleva(Color.B,"Q",0,3);
        this.board[0][4] = new Karole(Color.B,"K",0,4);

        //white board
        this.board[6][0] = new Peshka(Color.W,"P",6,0);
        this.board[6][1] = new Peshka(Color.W,"P",6,1);
        this.board[6][2] = new Peshka(Color.W,"P",6,2);
        this.board[6][3] = new Peshka(Color.W,"P",6,3);
        this.board[6][4] = new Peshka(Color.W,"P",6,4);
        this.board[6][5] = new Peshka(Color.W,"P",6,5);
        this.board[6][6] = new Peshka(Color.W,"P",6,6);
        this.board[6][7] = new Peshka(Color.W,"P",6,7);
        this.board[7][0] = new Ladya(Color.W,"R",7,0);
        this.board[7][7] = new Ladya(Color.W,"R",7,7);
        this.board[7][1] = new Kone(Color.W,"KN",7,1);
        this.board[7][6] = new Kone(Color.W,"KN",7,6);
        this.board[7][2] = new Slon(Color.W,"B",7,2);
        this.board[7][5] = new Slon(Color.W,"B",7,5);
        this.board[7][3] = new Karaleva(Color.W,"Q",7,3);
        this.board[7][4] = new Karole(Color.W,"K",7,4);

    }
    public ChessTool getTool(int row,int column){
        return this.board[row][column];
    }

    public int[] getToolIndexes(String tool,Player currentPlayer){
        int[] toolIndexes = new int[2];
        for ( int row = 0; row < 8  ; row++ ) {
            for ( int column = 0; column < 8 ; column++ ) {
                if (this.board[row][column] == null){
                    continue;
                }
                if(currentPlayer.getColor() == board[row][column].getColor() && tool.equals( board[row][column].getName() )){
                    toolIndexes = board[row][column].getIndexes();
                }
            }
        }
        return toolIndexes;
    }
    public boolean validateFieldIsThreatened(int[] indexesToCheck,Color color) {
        Color rivalColor = color == Color.W ? Color.B : Color.W;
        ArrayList <int[]> toolIndexes = getListOfIndexesByColor( rivalColor );
        for ( int[] toolIndex : toolIndexes ) {
            if ( getTool( toolIndex[ REPRESENTATION_OF_ROW_INDEX ], toolIndex[ REPRESENTATION_OF_COLUMN_INDEX ] ).validateMove( toolIndex, indexesToCheck, this ) ) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<int[]> getListOfIndexesByColor(Color color){
        ArrayList<int[]> listOfToolIndexes = new ArrayList<>();
        for ( int i = 0; i < 8  ; i++ ) {
            for ( int j = 0; j < 8 ; j++ ) {
                if(this.board[i][j] == null ){
                    continue;
                }
                if(this.board[i][j].getColor() == color){
                    listOfToolIndexes.add(this.board[i][j].getIndexes());
                }
            }
        }
        return listOfToolIndexes;
    }
    public void moveTool(int[] toIndexes,ChessTool tool){
        setFigure( toIndexes, tool );
        tool.setFirstMove( false );
    }
    private void setFigure(int[] toIndexes, ChessTool tool) {
        int endIndex = tool.getColor() == Color.W ? 0 : 7;
        if ( tool.getName().equals( "P" ) && tool.getRow() == endIndex ) {
            System.out.println( "Your Peshka reached the end, Choose a new figure to switch to: \n Karaleva\n Ladya\n Slon\n Kone " );
            String newTool = scan.nextLine();
            switch ( newTool ) {
                case "Karaleva":
                    board[ toIndexes[ 0 ] ][ toIndexes[ 1 ] ] = new Karaleva( tool.getColor(), newTool, toIndexes[ 0 ], toIndexes[ 1 ] );
                    break;
                case "Ladya":
                    board[ toIndexes[ 0 ] ][ toIndexes[ 1 ] ] = new Ladya( tool.getColor(), newTool, toIndexes[ 0 ], toIndexes[ 1 ] );
                    break;
                case "Slon":
                    board[ toIndexes[ 0 ] ][ toIndexes[ 1 ] ] = new Slon( tool.getColor(), newTool, toIndexes[ 0 ], toIndexes[ 1 ] );
                    break;
                case "Kone":
                    board[ toIndexes[ 0 ] ][ toIndexes[ 1 ] ] = new Kone( tool.getColor(), newTool, toIndexes[ 0 ], toIndexes[ 1 ] );
                    break;
            }
            deleteFigure( tool );
        } else {
            board[ toIndexes[ 0 ] ][ toIndexes[ 1 ] ] = tool;
            deleteFigure( tool );
            tool.setIndexes( toIndexes[ 0 ], toIndexes[ 1 ] );
        }
    }
    private void deleteFigure(ChessTool tool){
        board[tool.getRow()][ tool.getColumn()] = null;
    }

    public boolean validateDestination(int[] toIndexes, ChessTool tool) {
        return this.board[ toIndexes[ REPRESENTATION_OF_ROW_INDEX ] ][ toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] ] == null || this.board[ toIndexes[ REPRESENTATION_OF_ROW_INDEX ] ][ toIndexes[ REPRESENTATION_OF_COLUMN_INDEX ] ].getColor() != tool.getColor();
    }

      public ArrayList <int[]> createListOfFieldsBetweenFigures(int[] kingIndex, int[] toolIndex ) {
          ArrayList <int[]> list = new ArrayList <>();
          ChessTool king = getTool( kingIndex[ 0 ], kingIndex[ 1 ] );
          ChessTool tool = getTool( toolIndex[ 0 ], toolIndex[ 1 ] );
          int[] index = new int[ 2 ];
              if ( tool.getColumn() == king.getColumn() ) {
                  if ( king.getRow() > tool.getRow() ) {
                      for ( int i = 1; i <= Math.abs( king.getRow() - tool.getRow() ); i++ ) {
                          index[ 0 ] = king.getRow() - i;
                          index[ 1 ] = king.getColumn();
                          list.add( index );
                      }
                  } else {
                      for ( int i = 1; i <= Math.abs( king.getRow() - tool.getRow() ); i++ ) {
                          index[ 0 ] = king.getRow() + i;
                          index[ 1 ] = king.getColumn();
                          list.add( index );
                      }

                  }
              } else if ( tool.getRow() == king.getRow() ) {
                  if ( king.getColumn() >= tool.getColumn() ) {
                      for ( int i = 1; i < Math.abs( king.getColumn() - tool.getColumn() ); i++ ) {
                          index[ 0 ] = king.getRow();
                          index[ 1 ] = king.getColumn()-i;
                          list.add( index );
                      }
                  } else {
                      for ( int i = 1; i <= Math.abs( king.getColumn() - tool.getColumn() ); i++ ) {
                          index[ 0 ] = king.getRow();
                          index[ 1 ] = king.getColumn() + i;
                          list.add( index );
                      }
                  }
              }else if(tool.getRow() != king.getRow() && tool.getColumn() != king.getColumn()){
                  if(king.getRow() > tool.getRow()){
                      if(king.getColumn() > tool.getColumn()){
                          for ( int i = 1; i <= Math.abs( king.getColumn() - tool.getColumn() ) ; i++ ) {
                          index[ 0 ] = king.getRow() - i;
                          index[ 1 ] = king.getColumn() - i;
                          list.add( index );
                          }
                      }else{
                          for ( int i = 1; i <= Math.abs( king.getColumn() - tool.getColumn() ) ; i++ ) {
                              index[ 0 ] = king.getRow() - i ;
                              index[ 1 ] = king.getColumn() + i;
                              list.add( index );
                          }
                      }
                  }else{
                      if(king.getColumn() > tool.getColumn()){
                      for ( int i = 1; i <= Math.abs( king.getColumn() - tool.getColumn() ) ; i++ ) {
                          index[ 0 ] = king.getRow() + i;
                          index[ 1 ] = king.getColumn() - i;
                          list.add( index );
                      }
                  }
                  else{
                      for ( int i = 1; i <= Math.abs( king.getColumn() - tool.getColumn() ) ; i++ ) {
                          index[ 0 ] = king.getRow() + i ;
                          index[ 1 ] = king.getColumn() + i;
                          list.add( index );
                      }
                  }
                  }
              }

              return list;

          }


    public boolean isEmpty(int row,int column){
        return board[ row ][ column ] == null;
    }




    @Override
    public String toString() {
        return "ChessBoard{" + "board=" + (board == null ? "null" : Arrays.asList( board ).toString()) +
                '}';
    }

    public void printBoard(){
        System.out.println("------------------------------------------------------------------");
        System.out.println("\ta\t\tb\t\tc\t\td\t\te\t\tf\t\tg\t\th ");
        for (int i = 0; i < board.length ; i++) {
            System.out.print(i+1 + "\t");
            for (int j = 0; j < board.length ; j++) {
                if (board[i][j] == null) {
                    System.out.print("\t\t");
                } else {
                    System.out.print(board[i][j].printTool() + "\t\t");
                }
            }
            System.out.println("\n");

        }

        System.out.println("------------------------------------------------------------------");

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that = (ChessBoard) o;
        return Arrays.deepEquals( board, that.board );
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode( board );
    }
}
