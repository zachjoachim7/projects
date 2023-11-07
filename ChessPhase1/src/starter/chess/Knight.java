package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight implements ChessPiece{

    private final ChessGame.TeamColor color;
    private final PieceType pieceType;

    public Knight(ChessGame.TeamColor teamColor) {
        color = teamColor;
        pieceType = PieceType.KNIGHT;
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
        int currRow = myPosition.getRow();
        int currColumn = myPosition.getColumn();
        MyChessPosition upLeft1 = new MyChessPosition(currRow+2, currColumn-1);
        if (upLeft1.getRow() <= 8 && upLeft1.getColumn() >= 1) {
            if (board.getPiece(upLeft1) != null) {
                ChessGame.TeamColor pieceColor = board.getPiece(upLeft1).getTeamColor();
                if (pieceColor != this.getTeamColor()) {
                    validMoves.add(new MyChessMove(myPosition, upLeft1, null));
                }
            }
            else {
                validMoves.add(new MyChessMove(myPosition, upLeft1, null));
            }
        }
        currRow = myPosition.getRow();
        currColumn = myPosition.getColumn();
        MyChessPosition upLeft2 = new MyChessPosition(currRow+1, currColumn-2);
        if (upLeft2.getRow() <= 8 && upLeft2.getColumn() >= 1) {
            if (board.getPiece(upLeft2) != null) {
                ChessGame.TeamColor pieceColor = board.getPiece(upLeft2).getTeamColor();
                if (pieceColor != this.getTeamColor()) {
                    validMoves.add(new MyChessMove(myPosition, upLeft2, null));
                }
            }
            else {
                validMoves.add(new MyChessMove(myPosition, upLeft2, null));
            }
        }
        currRow = myPosition.getRow();
        currColumn = myPosition.getColumn();
        MyChessPosition upRight1 = new MyChessPosition(currRow+2, currColumn+1);
        if (upRight1.getRow() <= 8 && upRight1.getColumn() <= 8) {
            if (board.getPiece(upRight1) != null) {
                ChessGame.TeamColor pieceColor = board.getPiece(upRight1).getTeamColor();
                if (pieceColor != this.getTeamColor()) {
                    validMoves.add(new MyChessMove(myPosition, upRight1, null));
                }
            }
            else {
                validMoves.add(new MyChessMove(myPosition, upRight1, null));
            }
        }
        currRow = myPosition.getRow();
        currColumn = myPosition.getColumn();
        MyChessPosition upRight2 = new MyChessPosition(currRow+1, currColumn+2);
        if (upRight2.getRow() <= 8 && upRight2.getColumn() <= 8) {
            if (board.getPiece(upRight2) != null) {
                ChessGame.TeamColor pieceColor = board.getPiece(upRight2).getTeamColor();
                if (pieceColor != this.getTeamColor()) {
                    validMoves.add(new MyChessMove(myPosition, upRight2, null));
                }
            }
            else {
                validMoves.add(new MyChessMove(myPosition, upRight2, null));
            }
        }
        currRow = myPosition.getRow();
        currColumn = myPosition.getColumn();
        MyChessPosition downLeft1 = new MyChessPosition(currRow-2, currColumn-1);
        if (downLeft1.getRow() >= 1 && downLeft1.getColumn() >= 1) {
            if (board.getPiece(downLeft1) != null) {
                ChessGame.TeamColor pieceColor = board.getPiece(downLeft1).getTeamColor();
                if (pieceColor != this.getTeamColor()) {
                    validMoves.add(new MyChessMove(myPosition, downLeft1, null));
                }
            }
            else {
                validMoves.add(new MyChessMove(myPosition, downLeft1, null));
            }
        }
        currRow = myPosition.getRow();
        currColumn = myPosition.getColumn();
        MyChessPosition downLeft2 = new MyChessPosition(currRow-1, currColumn-2);
        if (downLeft2.getRow() >= 1 && downLeft2.getColumn() >= 1) {
            if (board.getPiece(downLeft2) != null) {
                ChessGame.TeamColor pieceColor = board.getPiece(downLeft2).getTeamColor();
                if (pieceColor != this.getTeamColor()) {
                    validMoves.add(new MyChessMove(myPosition, downLeft2, null));
                }
            }
            else {
                validMoves.add(new MyChessMove(myPosition, downLeft2, null));
            }
        }
        currRow = myPosition.getRow();
        currColumn = myPosition.getColumn();
        MyChessPosition downRight1 = new MyChessPosition(currRow-2, currColumn+1);
        if (downRight1.getRow() >= 1 && downRight1.getColumn() <= 8) {
            if (board.getPiece(downRight1) != null) {
                ChessGame.TeamColor pieceColor = board.getPiece(downRight1).getTeamColor();
                if (pieceColor != this.getTeamColor()) {
                    validMoves.add(new MyChessMove(myPosition, downRight1, null));
                }
            }
            else {
                validMoves.add(new MyChessMove(myPosition, downRight1, null));
            }
        }
        currRow = myPosition.getRow();
        currColumn = myPosition.getColumn();
        MyChessPosition downRight2 = new MyChessPosition(currRow-1, currColumn+2);
        if (downRight2.getRow() >= 1 && downRight2.getColumn() <= 8) {
            if (board.getPiece(downRight2) != null) {
                ChessGame.TeamColor pieceColor = board.getPiece(downRight2).getTeamColor();
                if (pieceColor != this.getTeamColor()) {
                    validMoves.add(new MyChessMove(myPosition, downRight2, null));
                }
            }
            else {
                validMoves.add(new MyChessMove(myPosition, downRight2, null));
            }
        }

        return validMoves;
    }
}
