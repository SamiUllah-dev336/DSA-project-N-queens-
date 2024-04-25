import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EightQueens {
    public static void main(String[] args) {
        int[] board = generateRandomBoard();
        while (true) {
            List<int[]> successors = getSuccessors(board);
            int[] bestSuccessor = null;
            int bestFitness = getFitness(board);
            for (int[] successor : successors) {
                int fitness = getFitness(successor);
                if (fitness > bestFitness) {
                    bestSuccessor = successor;
                    bestFitness = fitness;
                }
            }
            if (bestSuccessor == null) {
                // Local maximum
                printBoard(board);
                return;
            }
            board = bestSuccessor;
        }
    }

    private static int[] generateRandomBoard() {
        // Generate a random initial board state
        Random random = new Random();
        int[] board = new int[8];
        for (int col = 0; col < 8; col++) {
            while (true) {
                int row = random.nextInt(8);
                if (board[row] == 0) {
                    board[row] = col + 1;
                    break;
                }
            }
        }
        return board;
    }

    private static List<int[]> getSuccessors(int[] board) {
        // Generate all possible successor boards
        List<int[]> successors = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row] == col + 1) {
                    continue;
                }
                int[] newBoard = board.clone();
                newBoard[row] = col + 1;
                successors.add(newBoard);
            }
        }
        return successors;
    }

    private static int getFitness(int[] board) {
        // Calculate the number of non-attacking pairs of queens
        int fitness = 28;
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 8; j++) {
                if (board[i] == board[j]) {
                    // Same column
                    fitness--;
                } else if (Math.abs(board[i] - board[j]) == Math.abs(i - j)) {
                    // Same diagonal
                    fitness--;
                }
            }
        }
        return fitness;
    }

    private static void printBoard(int[] board) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row] == col + 1) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
