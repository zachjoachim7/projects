package chess;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class MyChessGame implements ChessGame{

    private MyChessBoard board;
    private TeamColor currentTeamTurn;

    public MyChessGame() {
        board = new MyChessBoard();
        currentTeamTurn = TeamColor.WHITE;
    }

    @Override
    public TeamColor getTeamTurn() {
        return currentTeamTurn;
    }

    @Override
    public void setTeamTurn(TeamColor team) {
        currentTeamTurn = team;
    }

    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {

        ChessPiece piece = board.getPiece(startPosition);
        Collection<ChessMove> pieceMoves = piece.pieceMoves(board, startPosition);
        List<ChessMove> valid = new ArrayList<>();
        for (ChessMove move : pieceMoves) {

            MyChessBoard copy = deepCopyBoard(board);
            board.addPiece(move.getEndPosition(), board.getPiece(move.getStartPosition()));
            board.addPiece(move.getStartPosition(), null);

            if (!isInCheck(board.getPiece(move.getEndPosition()).getTeamColor())) {
                valid.add(move);
            }
            board = copy;
        }
        return valid;
    }
    private MyChessBoard deepCopyBoard(MyChessBoard original) {
        MyChessBoard copy = new MyChessBoard();
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                ChessPosition position = new MyChessPosition(i,j);
                if (original.getPiece(position) != null) {
                    ChessPiece piece = original.getPiece(position);
                    copy.addPiece(position, piece);
                }
            }
        }
        return copy;
    }

    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {
        if (this.validMoves(move.getStartPosition()).contains(move)) {
            if (board.getPiece(move.getStartPosition()).getTeamColor() == this.getTeamTurn()) {
                if (move.getPromotionPiece() != null) {
                    if (move.getPromotionPiece().equals(ChessPiece.PieceType.QUEEN)) {
                        board.addPiece(move.getEndPosition(), new Queen(board.getPiece(move.getStartPosition()).getTeamColor()));
                        board.addPiece(move.getStartPosition(), null);
                    } else if (move.getPromotionPiece().equals(ChessPiece.PieceType.ROOK)) {
                        board.addPiece(move.getEndPosition(), new Rook(board.getPiece(move.getStartPosition()).getTeamColor()));
                        board.addPiece(move.getStartPosition(), null);
                    } else if (move.getPromotionPiece().equals(ChessPiece.PieceType.KNIGHT)) {
                        board.addPiece(move.getEndPosition(), new Knight(board.getPiece(move.getStartPosition()).getTeamColor()));
                        board.addPiece(move.getStartPosition(), null);
                    } else if (move.getPromotionPiece().equals(ChessPiece.PieceType.BISHOP)) {
                        board.addPiece(move.getEndPosition(), new Bishop(board.getPiece(move.getStartPosition()).getTeamColor()));
                        board.addPiece(move.getStartPosition(), null);
                    }
                    if (this.getTeamTurn() == TeamColor.WHITE) {
                        this.setTeamTurn(TeamColor.BLACK);
                    } else if (this.getTeamTurn() == TeamColor.BLACK) {
                        this.setTeamTurn(TeamColor.WHITE);
                    }
                } else {
                    board.addPiece(move.getEndPosition(), board.getPiece(move.getStartPosition()));
                    board.addPiece(move.getStartPosition(), null);
                    if (this.getTeamTurn() == TeamColor.WHITE) {
                        this.setTeamTurn(TeamColor.BLACK);
                    } else if (this.getTeamTurn() == TeamColor.BLACK) {
                        this.setTeamTurn(TeamColor.WHITE);
                    }
                }
            }
            else {
                throw new InvalidMoveException("Invalid move");
            }
        }
        else {
            throw new InvalidMoveException("Invalid move");
        }
    }

    @Override
    public boolean isInCheck(TeamColor teamColor) {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                MyChessPosition currPosition = new MyChessPosition(i,j);
                if (board.getPiece(currPosition) != null) {
                    for (ChessMove move : board.getPiece(currPosition).pieceMoves(board, currPosition)) {
                        if (move.getEndPosition().equals(findKingPosition(teamColor))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    @Override
    public boolean isInCheckmate(TeamColor teamColor) {
        if (!isInCheck(teamColor)) {
            return false;
        }

        if (!isInCheck(teamColor)) {
            return false;
        }

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                MyChessPosition position = new MyChessPosition(i, j);
                ChessPiece piece = board.getPiece(position);

                if (piece != null && piece.getTeamColor() == teamColor) {
                    Collection<ChessMove> validMoves = piece.pieceMoves(board, position);

                    for (ChessMove move : validMoves) {
                        MyChessBoard copy = deepCopyBoard(board);

                        board.addPiece(move.getEndPosition(), board.getPiece(move.getStartPosition()));
                        board.addPiece(move.getStartPosition(), null);

                        if (!isInCheck(teamColor)) {
                            board = copy;
                            return false;
                        }
                        board = copy;
                    }
                }
            }
        }
        return true;
    }
    @Override
    public boolean isInStalemate(TeamColor teamColor) {
        if (isInCheck(teamColor)) {
            return false;
        }
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                MyChessPosition position = new MyChessPosition(i, j);
                ChessPiece piece = board.getPiece(position);
                if (piece != null && piece.getTeamColor() == teamColor) {
                    Collection<ChessMove> validMoves = piece.pieceMoves(board, position);
                    for (ChessMove move : validMoves) {
                        MyChessBoard copy = deepCopyBoard(board);
                        board.addPiece(move.getEndPosition(), board.getPiece(move.getStartPosition()));
                        board.addPiece(move.getStartPosition(), null);
                        if (!isInCheck(teamColor)) {
                            board = copy;
                            return false;
                        }
                        board = copy;
                    }
                }
            }
        }
        return true;
    }
    private MyChessPosition findKingPosition(TeamColor teamColor) {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                MyChessPosition position = new MyChessPosition(i, j);
                ChessPiece piece = board.getPiece(position);
                if (piece != null && piece.getPieceType() == ChessPiece.PieceType.KING && piece.getTeamColor() == teamColor) {
                    return position;
                }
            }
        }
        return null;
    }

    @Override
    public void setBoard(ChessBoard board) {
        this.board = (MyChessBoard) board;
    }
    @Override
    public ChessBoard getBoard() {
        return board;
    }
}
