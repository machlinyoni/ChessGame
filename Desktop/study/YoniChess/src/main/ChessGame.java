package main;
import main.enums.Color;
import main.exceptions.InvalidInputException;
import main.ui.Messages;

import java.util.ArrayList;
import java.util.Scanner;

public class ChessGame {

    private Player player1;
    private Player player2;

    private boolean check = false;

    private Player currentPlayer;

    private final ChessBoard board;
    private final Scanner scan = new Scanner( System.in );
    private ChessTool lastPlayedTool  = null;
    private boolean mate = false;
    private int turnNumber = 1;

    public static final int REPRESENTATION_OF_ROW_INDEX = 0;
    public static final int REPRESENTATION_OF_COLUMN_INDEX = 1;

    public ChessGame() {
        board = new ChessBoard();
        board.init();
        players();
        board.printBoard();

        while ( !mate ) {
            currentPlayer = changeCurrentPlayer( currentPlayer );
            move( currentPlayer );
            board.printBoard();
        }
        scan.close();
        System.out.println( "CheckMate " + currentPlayer.getName() + "wins!" );
    }

    private void players() {
//            System.out.println("Enter the name of player number 1");
//            player1 = new Player(scan.nextLine(), Color.W);
//            System.out.println("Enter the name of player number 2");
//            player2 = new Player(scan.nextLine(), Color.B);
//            while (player1 == null || player1.equals("")) {
//                System.out.println("Enter the name of player number 1");
//                player1 = new Player(scan.nextLine(), Color.W);
//            }
//            while (player2 == null || player2.equals("")) {
//                System.out.println("Enter the name of player number 1");
//                player2 = new Player(scan.nextLine(), Color.B);;
//            }
        player1 = new Player( "yoni", Color.W );
        player2 = new Player( "Yo-av", Color.B );

        System.out.println( player1.printPlayer() + " is White, " + player2.printPlayer() + " is Black" );
    }

    public void move(Player player) {
        checkIfCheck(board);
        if(turnNumber > 2) {
            checkIfMate( lastPlayedTool );
            if(mate){
                return;
            }
        }
        System.out.println( player.getName() + "'s turn" );
        if ( check ) {
            Messages.print( currentPlayer.getColor() + " Is under CHECK " );
        }
        int[] from;
        do {
            from = moveValidation();
        }while (from[ 0 ] == 9 ) ;
        ChessTool tool = board.getTool( from[ 0 ], from[ 1 ] );
        if ( isKingThreatened( board ) ) {
            System.out.println( "Move is not possible as King will be threatened" );
            board.moveTool( from, tool );
            moveValidation();
        }
        turnNumber ++;
    }

    public int[]  moveValidation() {
        int[] from;
        ChessTool tool;
        do {
            from = getSquareFromPlayer( "from" );
            tool = board.getTool( from[ 0 ], from[ 1 ] );
        } while ( !isFromValid( tool, currentPlayer ) );
        lastPlayedTool = tool;

        int[] to;
        do {
            to = getSquareFromPlayer( "to | (to exit enter '9')" );
        } while ( !isToValid( to, currentPlayer, tool ) );
        if ( Karole.castling ) {
            System.out.println( " Castling" );
        } else {
            board.moveTool( to, tool );
        }
        return from;
    }

    public int[] getSquareFromPlayer(String moveType) {
        System.out.println( "Enter the fields you want to move " + moveType );
        int[] indexes = new int[ 2 ];
        System.out.println( "1 . Enter the ROW (1-8)" );
        int row = 0;
        while ( !scan.hasNextInt() ) {
            scan.nextLine();
            System.out.println( "Invalid input  \n1 . Enter the ROW (1-8)" );
        }
        try {
            row = utils.Convertors.convertRow( scan.nextInt() );
        } catch ( InvalidInputException e ) {
            System.out.println( e.getMessage() + "\nno such row" );
            getSquareFromPlayer( moveType );
        }
        scan.nextLine();

        System.out.println( "2 . Enter the COLUMN (a-h)" );

        int column = 0;
        try {
            column = utils.Convertors.convertInputToIndex( scan.nextLine() );
        } catch ( InvalidInputException e ) {
            System.out.println( e.getMessage() + "\nno such column" );
            getSquareFromPlayer( moveType );
        }
        indexes[ REPRESENTATION_OF_ROW_INDEX ] = row;
        indexes[ REPRESENTATION_OF_COLUMN_INDEX ] = column;
        return indexes;
    }

    public Player changeCurrentPlayer(Player current) {
        return current == player1 ? player2 : player1;
    }

    public boolean isFromValid(ChessTool tool, Player player) {
        if ( tool == null ) {
            Messages.print( Messages.NO_SUCH_TOOL );
            return false;
        }
        if ( tool.getColor() != player.getColor() ) {
            Messages.print( Messages.WRONG_TOOL_COLOR );
            return false;
        }
        return true;
    }

    public boolean isToValid(int[] toIndexes,Player player,ChessTool tool) {
        if(toIndexes[0] == 9 ){
            moveValidation();
        }
        if ( !board.validateDestination( toIndexes, tool ) ) {
            Messages.print( "Destination is not valid" );
            return false;
        }
        if ( !tool.validateMove( tool.getIndexes(), toIndexes, board ) ) {
                if(Karole.castling){
                    return true;
                }else {
                    Messages.print( "Move is not possible" );
                    return false;
                }
        }
        if ( tool.getName().equals( "K" ) ) {
            if ( board.validateFieldIsThreatened( toIndexes ,player.getColor() ) ) {
                Messages.print( "The King cannot go there, this field is threatened" );
                return false;
            }
        }
        return true;
    }

    private boolean isKingThreatened(ChessBoard board) {
        int[] kingIndexes = board.getToolIndexes( "K", currentPlayer );
        return board.validateFieldIsThreatened( kingIndexes , currentPlayer.getColor() );
    }


    private void checkIfCheck(ChessBoard board) {
        check = isKingThreatened( board );
    }

    private boolean validateKingMoves(ChessTool tool){
        ArrayList<int[]> listOfAvailableKingIndexes = new ArrayList<>();
        int[] indexes = new int[2];
        int[] lowIndexes = new int[2];
        int[] highIndexes = new int[2];
        lowIndexes[0] = tool.getRow() -1 ;
        lowIndexes[1] = tool.getColumn() - 1;
        highIndexes[0] = tool.getRow() + 1 ;
        highIndexes[1] = tool.getColumn() + 1;

        for ( int i = lowIndexes[0]; i < highIndexes[0] ; i++ ) {
            for ( int j = lowIndexes[1]; j < highIndexes[1]; j++ ) {
                if (i < 0 || j < 0 || i > 7  ||j > 7 || (tool.getRow() == i && tool.getColumn() == j)){
                    continue;
                }
                indexes[0] = i;
                indexes[1] = j;
                listOfAvailableKingIndexes.add(indexes);
            }
        }
        for ( int[] available: listOfAvailableKingIndexes ){
            if( isToValid( available,currentPlayer, tool))
                return true;
        }
        return false;
     }

    private void checkIfMate(ChessTool tool) {
        int[] indexes = board.getToolIndexes( "K", currentPlayer);
        if ( check  && !validateKingMoves(board.getTool( indexes[0],indexes[1] )) && wayToMateIsBlockable(tool) ) {
            mate = true;
        }
    }

    public boolean wayToMateIsBlockable(ChessTool tool){

        ArrayList<int[]> list =  board.createListOfFieldsBetweenFigures( board.getToolIndexes( "K", currentPlayer ),tool.getIndexes());
        for ( int[] indexes: list) {
            if(board.validateFieldIsThreatened(indexes,currentPlayer.getColor())){
                return true;
            }
        }
        return false;
    }




}

