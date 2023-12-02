package ui;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import static ui.EscapeSequences.*;

public class ChessBoardPrinter {

    private final ServerFacade serverFacade;
    private static final int BOARD_SIZE = 8;
    private static final String EMPTY = " ";
    private static final String[] PIECES_TOP = {"R", "N", "B", "Q", "K", "B", "N", "R"};
    private static final String[] PIECES_BOTTOM = {"r", "n", "b", "k", "q", "b", "n", "r"};
    private static final String PAWN_WHITE = "P";
    private static final String PAWN_BLACK = "p";
    private final PrintStream out;

    public ChessBoardPrinter(ServerFacade serverFacade) {

        this.serverFacade = serverFacade;
        this.out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out.print(ERASE_SCREEN);
        printBoardFromBlackPerspective();
        out.println();
        printBoardFromWhitePerspective();
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);

    }

    public void printBoardFromWhitePerspective() {
        drawChessBoard("white");
    }

    public void printBoardFromBlackPerspective() {
        drawChessBoard("black");
    }



    private void drawChessBoard(String color) {
        if (color.equals("black")) {
            for (int row = 0; row < BOARD_SIZE + 2; row++) {
                for (int col = 0; col < BOARD_SIZE + 2; col++) {
                    printSquare(row, col, color);
                }
                out.println();
            }
        }
        else if (color.equals("white")) {
            for (int row = BOARD_SIZE + 1; row >= 0; row--) {
                for (int col = BOARD_SIZE + 1; col >= 0; col--) {
                    printSquare(row, col, color);
                }
                out.println();
            }
        }
    }
    private void printSquare(int row, int col, String color) {

        if (row == 0 || row == BOARD_SIZE + 1) {
            printHeaderFooter(col, row);
        }
        else {
            if (col == 0 || col == BOARD_SIZE + 1) {
                printHeaderFooter(col, row);
            }
            else {
                printBoardSquare(row, col, color);
            }
        }
    }
    private void printBoardSquare(int row, int col, String color) {
        boolean isWhite = (row + col) % 2 == 0;
        setSquareColor(isWhite);
        if (color.equals("black")) {
            printPiece(row, col - 1, color);
        }
        else if (color.equals("white")) {
            printPiece(BOARD_SIZE - row, BOARD_SIZE - col, color);
        }

        resetColor();
    }

    private void printPiece(int row, int col, String color) {
        if (color.equals("black")) {
            if (row == 1) {
                out.print(SET_TEXT_COLOR_RED);
                out.print(PIECES_TOP[col]);
            } else if (row == 2 || row == 7) {
                if (row == 2) {
                    out.print(SET_TEXT_COLOR_RED);
                    out.print(PAWN_WHITE);
                } else {
                    out.print(SET_TEXT_COLOR_GREEN);
                    out.print(PAWN_BLACK);
                }
            } else if (row == 8) {
                out.print(SET_TEXT_COLOR_GREEN);
                out.print(PIECES_BOTTOM[col]);
            } else {
                out.print(EMPTY);
            }
            out.print(EMPTY);
        }

        else if (color.equals("white")) {
            if (row == 0) {
                out.print(SET_TEXT_COLOR_GREEN);
                out.print(PIECES_BOTTOM[col]);
            } else if (row == 1 || row == 6) {
                if (row == 1) {
                    out.print(SET_TEXT_COLOR_GREEN);
                    out.print(PAWN_BLACK);
                } else {
                    out.print(SET_TEXT_COLOR_RED);
                    out.print(PAWN_WHITE);
                }
            } else if (row == 7) {
                out.print(SET_TEXT_COLOR_RED);
                out.print(PIECES_TOP[col]);
            } else {
                out.print(EMPTY);
            }
            out.print(EMPTY);
        }

    }

    private void printHeaderFooter(int col, int row) {
        if (row == 0 || row == BOARD_SIZE + 1) {
            setGrey();
            if (col >= 1 && col <= BOARD_SIZE) {
                out.print((char) ('a' + col - 1) + " ");
            } else {
                out.print("   ");
            }
        }
        else {
            setGrey();
            if (col == 0 || col == BOARD_SIZE + 1) {
                out.print(" " + (BOARD_SIZE + 1 - row) + " ");
            }
        }
        resetColor();
    }


    private void setSquareColor(boolean isWhite) {
        if (isWhite) {
            out.print(SET_BG_COLOR_WHITE);
        } else {
            out.print(SET_BG_COLOR_BLACK);
        }
    }

    private void setGrey() {
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_TEXT_COLOR_WHITE);
    }

    private void resetColor() {
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);
    }

}
