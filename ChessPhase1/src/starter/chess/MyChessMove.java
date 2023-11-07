package chess;

import java.util.Objects;

public class MyChessMove implements ChessMove{

    private final ChessPosition startPosition;
    private final ChessPosition endPosition;
    private final ChessPiece.PieceType promotionPiece;

    public MyChessMove(ChessPosition start, ChessPosition end, ChessPiece.PieceType promotion) {
        startPosition = start;
        endPosition = end;
        promotionPiece = promotion;
    }

    @Override
    public String toString() {
        return "MyChessMove{" +
                "startPosition=" + startPosition.toString() +
                ", endPosition=" + endPosition.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyChessMove that = (MyChessMove) o;
        return Objects.equals(startPosition, that.startPosition) && Objects.equals(endPosition, that.endPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPosition, endPosition);
    }

    @Override
    public ChessPosition getStartPosition() {
        return startPosition;
    }

    @Override
    public ChessPosition getEndPosition() {
        return endPosition;
    }

    @Override
    public ChessPiece.PieceType getPromotionPiece() {
        return promotionPiece;
    }
}
