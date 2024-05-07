import java.util.*;

public class Minesweeper {
    public static char[][] generateBoard(int gridSize) {
        char[][] newBoard = new char[gridSize][gridSize];
        for (char[] row : newBoard) {
            Arrays.fill(row, ' ');
        }
        return newBoard;
    }

    public static List<int[]> placeMines (char[][] newBoard, int numberOfMines, int[] firstClick) {
        List<int[]> minePositions = new ArrayList<>();
        int gridSize = newBoard.length;
        int clickRow = firstClick[0];
        int clickCol = firstClick[1];

        Random random = new Random();

        while (numberOfMines > 0) {
            int row = random.nextInt(gridSize);
            int col = random.nextInt(gridSize);

            //Rule: make sure generated pos isn't the first clicked pos
            if (row == clickRow && col == clickCol) {
                continue;
            }

            if (newBoard[row][col] != '*') {
                newBoard[row][col] = '*';
                minePositions.add(new int[]{row, col});
                numberOfMines--;
            }
        }
        
        return minePositions;
    }

    public static void printBoard(char[][] newBoard) {
        for (char[] row : newBoard) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        int gridSize;
        int numMines;
        int firstClickRow;
        int firstClickCol;
    
        // Get valid grid size
        do {
            System.out.print("Enter the size of the grid: ");
            gridSize = scanner.nextInt();
            if (gridSize <= 0) {
                System.out.println("Grid size must be a positive integer. Please try again.");
            }
        } while (gridSize <= 0);
    
        // Get valid number of mines
        do {
            System.out.print("Enter the number of mines: ");
            numMines = scanner.nextInt();
            if (numMines <= 0 || numMines >= gridSize * gridSize) {
                System.out.println("Number of mines must be a positive integer less than the total number of cells. Please try again.");
            }
        } while (numMines <= 0 || numMines >= gridSize * gridSize);
    
        // Get valid row for first click
        do {
            System.out.print("Enter the row of the first clicked cell: ");
            firstClickRow = scanner.nextInt();
            if (firstClickRow < 0 || firstClickRow >= gridSize) {
                System.out.println("Row number must be between 0 and " + (gridSize - 1) + ". Please try again.");
            }
        } while (firstClickRow < 0 || firstClickRow >= gridSize);
    
        // Get valid column for first click
        do {
            System.out.print("Enter the column of the first clicked cell: ");
            firstClickCol = scanner.nextInt();
            if (firstClickCol < 0 || firstClickCol >= gridSize) {
                System.out.println("Column number must be between 0 and " + (gridSize - 1) + ". Please try again.");
            }
        } while (firstClickCol < 0 || firstClickCol >= gridSize);
    
        int[] firstClick = {firstClickRow, firstClickCol};
    
        // Generate an empty board
        char[][] board = generateBoard(gridSize);
    
        // Place mines
        List<int[]> minePositions = placeMines(board, numMines, firstClick);
    
        // Print board with mines hidden
        System.out.println("Minesweeper Board:");
        printBoard(board);
    
        System.out.println("\nMine Positions:");
        for (int[] position : minePositions) {
            System.out.println(Arrays.toString(position));
        }
    }
    
}