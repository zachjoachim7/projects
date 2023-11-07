package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook implements ChessPiece{

    private final ChessGame.TeamColor color;
    private final PieceType pieceType;

    public Rook(ChessGame.TeamColor teamColor) {
        color = teamColor;
        pieceType = PieceType.ROOK;
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
    public String toString() {
        return "Rook{" +
                "color=" + color +
                ", pieceType=" + pieceType +
                '}';
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        List<ChessMove> validMoves = new ArrayList<>();
        for (int i = myPosition.getRow()+1; i <= 8; i++) {
            MyChessPosition next = new MyChessPosition(i, myPosition.getColumn());
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
        for (int i = myPosition.getRow()-1; i >= 1; i--) {
            MyChessPosition next = new MyChessPosition(i, myPosition.getColumn());
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
        for (int i = myPosition.getColumn()+1; i <= 8; i++) {
            MyChessPosition next = new MyChessPosition(myPosition.getRow(), i);
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
        for (int i = myPosition.getColumn()-1; i >= 1; i--) {
            MyChessPosition next = new MyChessPosition(myPosition.getRow(), i);
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
