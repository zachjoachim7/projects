package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King implements ChessPiece{

    private final ChessGame.TeamColor color;
    private final PieceType pieceType;

    public King(ChessGame.TeamColor teamColor) {
        color = teamColor;
        pieceType = PieceType.KING;
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
        int[][] directions = { {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1} };
        for (int[] dir : directions) {
            int newRow = myPosition.getRow() + dir[0];
            int newCol = myPosition.getColumn() + dir[1];
            MyChessPosition newPosition = new MyChessPosition(newRow, newCol);

            if (isValidPosition(newPosition) && (board.getPiece(newPosition) == null || board.getPiece(newPosition).getTeamColor() != color)) {
                validMoves.add(new MyChessMove(myPosition, newPosition, null));
            }
        }
        return validMoves;
    }
    private boolean isValidPosition(MyChessPosition position) {
        int row = position.getRow();
        int col = position.getColumn();
        return row >= 1 && row <= 8 && col >= 1 && col <= 8;
    }
}



