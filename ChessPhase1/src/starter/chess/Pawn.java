package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn implements ChessPiece{

    private final ChessGame.TeamColor color;

    private final PieceType pieceType;

    public Pawn(ChessGame.TeamColor teamColor) {
        color = teamColor;
        pieceType = PieceType.PAWN;
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
        if (board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.WHITE) {
            int nextRow = myPosition.getRow() + 1;
            int column = myPosition.getColumn();
            MyChessPosition nextPosition = new MyChessPosition(nextRow, column);
            if (board.getPiece(nextPosition) == null) {
                validMoves.add(new MyChessMove(myPosition, nextPosition, null));
                if (myPosition.getRow() == 2) {
                    MyChessPosition twoStep = new MyChessPosition(myPosition.getRow() + 2, myPosition.getColumn());
                    if (board.getPiece(twoStep) == null) {
                        validMoves.add(new MyChessMove(myPosition, twoStep, null));
                    }
                }
            }
            column = myPosition.getColumn() - 1;
            MyChessPosition capture = new MyChessPosition(nextRow, column);
            if (board.getPiece(capture) != null) {
                if (board.getPiece(capture).getTeamColor() != this.getTeamColor()) {
                    validMoves.add(new MyChessMove(myPosition, capture, null));
                }
            }
            column = myPosition.getColumn() + 1;
            MyChessPosition capture2 = new MyChessPosition(nextRow, column);
            if (board.getPiece(capture2) != null) {
                if (board.getPiece(capture2).getTeamColor() != this.getTeamColor()) {
                    validMoves.add(new MyChessMove(myPosition, capture2, null));
                }
            }

        }

        if (board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.BLACK) {
            int nextRow = myPosition.getRow() - 1;
            int column = myPosition.getColumn();
            MyChessPosition nextPosition = new MyChessPosition(nextRow, column);
            if (board.getPiece(nextPosition) == null) {
                validMoves.add(new MyChessMove(myPosition, nextPosition, null));
                if (myPosition.getRow() == 7) {
                    MyChessPosition twoStep = new MyChessPosition(myPosition.getRow() - 2, myPosition.getColumn());
                    if (board.getPiece(twoStep) == null) {
                        validMoves.add(new MyChessMove(myPosition, twoStep, null));
                    }
                }
            }
            column = myPosition.getColumn() - 1;
            MyChessPosition capture = new MyChessPosition(nextRow, column);
            if (board.getPiece(capture) != null) {
                if (board.getPiece(capture).getTeamColor() != this.getTeamColor()) {
                    validMoves.add(new MyChessMove(myPosition, capture, null));
                }
            }
            column = myPosition.getColumn() + 1;
            MyChessPosition capture2 = new MyChessPosition(nextRow, column);
            if (board.getPiece(capture2) != null) {
                if (board.getPiece(capture2).getTeamColor() != this.getTeamColor()) {
                    validMoves.add(new MyChessMove(myPosition, capture2, null));
                }
            }
        }
        if (myPosition.getRow() == 2 && color == ChessGame.TeamColor.BLACK) {
            MyChessPosition pos1 = new MyChessPosition(myPosition.getRow()-1, myPosition.getColumn()-1);
            MyChessPosition pos2 = new MyChessPosition(myPosition.getRow()-1, myPosition.getColumn());
            MyChessPosition pos3 = new MyChessPosition(myPosition.getRow()-1, myPosition.getColumn()+1);
            if (myPosition.getColumn() > 1 && myPosition.getColumn() < 8) {
                if (board.getPiece(pos1) != null) {
                    if (board.getPiece(pos1).getTeamColor() != color) {
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.QUEEN));
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.ROOK));
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.BISHOP));
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.KNIGHT));
                    }
                }
                if (board.getPiece(pos2) != null) {
                    if (board.getPiece(pos2).getTeamColor() != color) {
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.QUEEN));
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.ROOK));
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.BISHOP));
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.KNIGHT));
                    }
                }
                if (board.getPiece(pos3) != null) {
                    if (board.getPiece(pos3).getTeamColor() != color) {
                        validMoves.add(new MyChessMove(myPosition, pos3, PieceType.QUEEN));
                        validMoves.add(new MyChessMove(myPosition, pos3, PieceType.ROOK));
                        validMoves.add(new MyChessMove(myPosition, pos3, PieceType.BISHOP));
                        validMoves.add(new MyChessMove(myPosition, pos3, PieceType.KNIGHT));
                    }
                }
            }
            if (myPosition.getColumn() == 1) {
                if (board.getPiece(pos3) != null) {
                    if (board.getPiece(pos3).getTeamColor() != color) {
                        validMoves.add(new MyChessMove(myPosition, pos3, PieceType.QUEEN));
                        validMoves.add(new MyChessMove(myPosition, pos3, PieceType.BISHOP));
                        validMoves.add(new MyChessMove(myPosition, pos3, PieceType.ROOK));
                        validMoves.add(new MyChessMove(myPosition, pos3, PieceType.KNIGHT));
                    }
                }
                if (board.getPiece(pos2) != null) {
                    if (board.getPiece(pos2).getTeamColor() != color) {
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.QUEEN));
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.ROOK));
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.BISHOP));
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.KNIGHT));
                    }
                }
            }
            if (myPosition.getColumn() == 8) {
                if (board.getPiece(pos2) != null) {
                    if (board.getPiece(pos2).getTeamColor() != color) {
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.BISHOP));
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.QUEEN));
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.KNIGHT));
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.ROOK));
                    }
                }
                if (board.getPiece(pos2) != null) {
                    if (board.getPiece(pos1).getTeamColor() != color) {
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.QUEEN));
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.ROOK));
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.KNIGHT));
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.BISHOP));
                    }
                }
            }
        }
        if (myPosition.getRow() == 7 && color == ChessGame.TeamColor.WHITE) {
            MyChessPosition pos1 = new MyChessPosition(myPosition.getRow()+1, myPosition.getColumn()+1);
            MyChessPosition pos2 = new MyChessPosition(myPosition.getRow()+1, myPosition.getColumn());
            MyChessPosition pos3 = new MyChessPosition(myPosition.getRow()+1, myPosition.getColumn()-1);
            if (myPosition.getColumn() > 1 && myPosition.getColumn() < 8) {
                if (board.getPiece(pos1) != null) {
                    if (board.getPiece(pos1).getTeamColor() != color) {
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.QUEEN));
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.ROOK));
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.BISHOP));
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.KNIGHT));
                    }
                }
                if (board.getPiece(pos2) != null) {
                    if (board.getPiece(pos2).getTeamColor() != color) {
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.QUEEN));
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.ROOK));
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.BISHOP));
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.KNIGHT));
                    }
                }
                if (board.getPiece(pos3) != null) {
                    if (board.getPiece(pos3).getTeamColor() != color) {
                        validMoves.add(new MyChessMove(myPosition, pos3, PieceType.QUEEN));
                        validMoves.add(new MyChessMove(myPosition, pos3, PieceType.ROOK));
                        validMoves.add(new MyChessMove(myPosition, pos3, PieceType.BISHOP));
                        validMoves.add(new MyChessMove(myPosition, pos3, PieceType.KNIGHT));
                    }
                }
            }
            if (myPosition.getColumn() == 1) {
                if (board.getPiece(pos1) != null) {
                    if (board.getPiece(pos1).getTeamColor() != color) {
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.QUEEN));
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.ROOK));
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.BISHOP));
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.KNIGHT));
                    }
                }
                if (board.getPiece(pos2) != null) {
                    if (board.getPiece(pos2).getTeamColor() != color) {
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.QUEEN));
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.ROOK));
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.BISHOP));
                        validMoves.add(new MyChessMove(myPosition, pos2, PieceType.KNIGHT));
                    }
                }
            }
            if (myPosition.getColumn() == 8) {
                if (board.getPiece(pos1) != null) {
                    if (board.getPiece(pos1).getTeamColor() != color) {
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.QUEEN));
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.ROOK));
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.BISHOP));
                        validMoves.add(new MyChessMove(myPosition, pos1, PieceType.KNIGHT));
                    }
                }
                if (board.getPiece(pos3) != null) {
                    if (board.getPiece(pos3).getTeamColor() != color) {
                        validMoves.add(new MyChessMove(myPosition, pos3, PieceType.QUEEN));
                        validMoves.add(new MyChessMove(myPosition, pos3, PieceType.ROOK));
                        validMoves.add(new MyChessMove(myPosition, pos3, PieceType.BISHOP));
                        validMoves.add(new MyChessMove(myPosition, pos3, PieceType.KNIGHT));
                    }
                }
            }
        }
        return validMoves;
    }
}
