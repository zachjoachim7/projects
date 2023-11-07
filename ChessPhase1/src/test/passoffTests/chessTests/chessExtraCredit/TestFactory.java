package passoffTests.chessTests.chessExtraCredit;

import chess.*;

/**
 * Used for testing your code
 * Add in code using your classes for each method for each FIXME
 */
public class TestFactory {

    //Chess Functions
    //------------------------------------------------------------------------------------------------------------------
    public static ChessBoard getNewBoard(){
        //
		return new MyChessBoard();
    }

    public static ChessGame getNewGame(){
        //
		return new MyChessGame();
    }

    public static ChessPiece getNewPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type){
        //
        ChessPiece piece = new Pawn(pieceColor);
		if (type == ChessPiece.PieceType.ROOK) {
            piece = new Rook(pieceColor);
        }
        if (type == ChessPiece.PieceType.QUEEN) {
            piece =  new Queen(pieceColor);
        }
        if (type == ChessPiece.PieceType.KING) {
            piece = new King(pieceColor);
        }
        if (type == ChessPiece.PieceType.BISHOP) {
            piece = new Bishop(pieceColor);
        }
        if (type == ChessPiece.PieceType.KNIGHT) {
            piece = new Knight(pieceColor);
        }
        return piece;
    }

    public static ChessPosition getNewPosition(Integer row, Integer col){
        //
		return new MyChessPosition(row, col);
    }

    public static ChessMove getNewMove(ChessPosition startPosition, ChessPosition endPosition, ChessPiece.PieceType promotionPiece){
        //
        MyChessMove move = new MyChessMove(startPosition, endPosition, promotionPiece);
        return move;
    }
    //------------------------------------------------------------------------------------------------------------------


    //Server API's
    //------------------------------------------------------------------------------------------------------------------
    public static String getServerPort(){
        return "8080";
    }
    //------------------------------------------------------------------------------------------------------------------


    //Websocket Tests
    //------------------------------------------------------------------------------------------------------------------
    public static Long getMessageTime(){
        /*
        Changing this will change how long tests will wait for the server to send messages.
        3000 Milliseconds (3 seconds) will be enough for most computers. Feel free to change as you see fit,
        just know increasing it can make tests take longer to run.
        (On the flip side, if you've got a good computer feel free to decrease it)
         */
        return 3000L;
    }
    //------------------------------------------------------------------------------------------------------------------
}
