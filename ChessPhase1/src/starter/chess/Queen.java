package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Queen implements ChessPiece{

    private final ChessGame.TeamColor color;
    private final PieceType pieceType;

    public Queen(ChessGame.TeamColor teamColor) {
        color = teamColor;
        pieceType = PieceType.QUEEN;
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
        return "Queen{" +
                "color=" + color +
                ", pieceType=" + pieceType +
                '}';
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        List<ChessMove> validMoves = new ArrayList<>();
        Bishop bishop = new Bishop(color);
        validMoves.addAll(bishop.pieceMoves(board, myPosition));
        Rook rook = new Rook(color);
        validMoves.addAll(rook.pieceMoves(board, myPosition));

        return validMoves;
    }
}
