package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bishop implements ChessPiece{

    private final ChessGame.TeamColor color;
    private final PieceType pieceType;

    public Bishop(ChessGame.TeamColor teamColor) {
        color = teamColor;
        pieceType = PieceType.BISHOP;
    }

    @Override
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    @Override
    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        List<ChessMove> validMoves = new ArrayList<>();
        for (int i = myPosition.getRow()+1, j = myPosition.getColumn()+1; i <= 8 && j <= 8; i++, j++) {
            MyChessPosition next = new MyChessPosition(i,j);
            if (board.getPiece(next) != null) {
                ChessGame.TeamColor pieceColor = board.getPiece(next).getTeamColor();
                if (pieceColor != this.getTeamColor()) {
                    validMoves.add(new MyChessMove(myPosition, next, null));
                }
                break;
            }
            else {
                validMoves.add(new MyChessMove(myPosition, next, null));
            }
        }
        for (int i = myPosition.getRow()+1, j = myPosition.getColumn()-1; i <= 8 && j >= 1; i++, j--) {
            MyChessPosition next = new MyChessPosition(i,j);
            if (board.getPiece(next) != null) {
                ChessGame.TeamColor pieceColor = board.getPiece(next).getTeamColor();
                if (pieceColor != this.getTeamColor()) {
                    validMoves.add(new MyChessMove(myPosition, next, null));
                }
                break;
            }
            else {
                validMoves.add(new MyChessMove(myPosition, next, null));
            }
        }
        for (int i = myPosition.getRow()-1, j = myPosition.getColumn()+1; i >= 1 && j <= 8; i--, j++) {
            MyChessPosition next = new MyChessPosition(i,j);
            if (board.getPiece(next) != null) {
                ChessGame.TeamColor pieceColor = board.getPiece(next).getTeamColor();
                if (pieceColor != this.getTeamColor()) {
                    validMoves.add(new MyChessMove(myPosition, next, null));
                }
                break;
            }
            else {
                validMoves.add(new MyChessMove(myPosition, next, null));
            }
        }
        for (int i = myPosition.getRow()-1, j = myPosition.getColumn()-1; i >= 1 && j >= 1; i--, j--) {
            MyChessPosition next = new MyChessPosition(i,j);
            if (board.getPiece(next) != null) {
                ChessGame.TeamColor pieceColor = board.getPiece(next).getTeamColor();
                if (pieceColor != this.getTeamColor()) {
                    validMoves.add(new MyChessMove(myPosition, next, null));
                }
                break;
            }
            else {
                validMoves.add(new MyChessMove(myPosition, next, null));
            }
        }
        return validMoves;
    }
}
