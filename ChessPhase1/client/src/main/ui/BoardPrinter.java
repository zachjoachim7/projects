package ui;

public class BoardPrinter {

    private static final String[][] initialBoard = {
            {"R", "N", "B", "Q", "K", "B", "N", "R"},
            {"P", "P", "P", "P", "P", "P", "P", "P"},
            {".", ".", ".", ".", ".", ".", ".", "."},
            {".", ".", ".", ".", ".", ".", ".", "."},
            {".", ".", ".", ".", ".", ".", ".", "."},
            {".", ".", ".", ".", ".", ".", ".", "."},
            {"p", "p", "p", "p", "p", "p", "p", "p"},
            {"r", "n", "b", "q", "k", "b", "n", "r"}
    };

    private static final String[] unicodePieces = {
            "r", "n", "b", "q", "k", "p", "R", "N", "B", "Q", "K", "P", "."
    };

    private static final String[] unicodePiecesCharacters = {
            "\u265C", "\u265E", "\u265D", "\u265B", "\u265A", "\u265F",
            "\u2656", "\u2658", "\u2657", "\u2655", "\u2654", "\u2659", " "
    };

    public static void printBoard() {
        for (int i = 0; i < initialBoard.length; i++) {
            for (int j = 0; j < initialBoard[i].length; j++) {
                String piece = initialBoard[i][j];
                String unicodeChar = mapPieceToUnicode(piece);
                System.out.print(unicodeChar + " ");
            }
            System.out.println();
        }
    }

    private static String mapPieceToUnicode(String piece) {
        for (int i = 0; i < unicodePieces.length; i++) {
            if (unicodePieces[i].equals(piece)) {
                return unicodePiecesCharacters[i];
            }
        }
        return " "; // Fallback to space if piece not found
    }

}
