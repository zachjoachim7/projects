package chess;

import java.util.HashMap;
import java.util.Map;

public class MyChessBoard implements ChessBoard {

    private final Map<ChessPosition, ChessPiece> board;

    public MyChessBoard() {
        board = new HashMap<>();
        //resetBoard();
    }

    @Override
    public void addPiece(ChessPosition position, ChessPiece piece) {
        board.put(position, piece);
    }

    @Override
    public ChessPiece getPiece(ChessPosition position) {
        return board.get(position);
    }

    @Override
    public void resetBoard() {
        board.clear();

        // Add white pieces
        addPiece(new MyChessPosition(1, 1), new Rook(ChessGame.TeamColor.WHITE));
        addPiece(new MyChessPosition(1, 2), new Knight(ChessGame.TeamColor.WHITE));
        addPiece(new MyChessPosition(1, 3), new Bishop(ChessGame.TeamColor.WHITE));
        addPiece(new MyChessPosition(1, 4), new Queen(ChessGame.TeamColor.WHITE));
        addPiece(new MyChessPosition(1, 5), new King(ChessGame.TeamColor.WHITE));
        addPiece(new MyChessPosition(1, 6), new Bishop(ChessGame.TeamColor.WHITE));
        addPiece(new MyChessPosition(1, 7), new Knight(ChessGame.TeamColor.WHITE));
        addPiece(new MyChessPosition(1, 8), new Rook(ChessGame.TeamColor.WHITE));

        for (int col = 1; col <= 8; col++) {
            addPiece(new MyChessPosition(2, col), new Pawn(ChessGame.TeamColor.WHITE));
        }

        // Add black pieces
        addPiece(new MyChessPosition(8, 1), new Rook(ChessGame.TeamColor.BLACK));
        addPiece(new MyChessPosition(8, 2), new Knight(ChessGame.TeamColor.BLACK));
        addPiece(new MyChessPosition(8, 3), new Bishop(ChessGame.TeamColor.BLACK));
        addPiece(new MyChessPosition(8, 4), new Queen(ChessGame.TeamColor.BLACK));
        addPiece(new MyChessPosition(8, 5), new King(ChessGame.TeamColor.BLACK));
        addPiece(new MyChessPosition(8, 6), new Bishop(ChessGame.TeamColor.BLACK));
        addPiece(new MyChessPosition(8, 7), new Knight(ChessGame.TeamColor.BLACK));
        addPiece(new MyChessPosition(8, 8), new Rook(ChessGame.TeamColor.BLACK));

        for (int col = 1; col <= 8; col++) {
            addPiece(new MyChessPosition(7, col), new Pawn(ChessGame.TeamColor.BLACK));
        }
    }
}
