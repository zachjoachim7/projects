package chess;

import java.util.Objects;

public class MyChessPosition implements ChessPosition{

    private final int row;
    private final int column;

    @Override
    public String toString() {
        return "MyChessPosition{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyChessPosition that = (MyChessPosition) o;
        return row == that.row && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    public MyChessPosition(int pieceRow, int pieceColumn) {
        row = pieceRow;
        column = pieceColumn;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }
}
