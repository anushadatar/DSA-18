import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;
    int size;
    int goal[][];

    /*
     * Create the goal state ;)
     */
    public int[][] makeGoal(int size) {
        int[][] goal = new int[size][size];
        int counter = 1;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                goal[i][j] = counter;
                counter++;
            }
        }
        goal[size-1][size-1] = 0;
        return goal;
    }

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        this.n = b.length;
        tiles = b;
        size = b.length;
        goal = makeGoal(size);
    }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        return size;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != goal[i][j]) {
                    int gr = (tiles[i][j] - 1) / size;
                    int gc = (tiles[i][j] - 1) % size;
                    sum += Math.abs(i - gr) + Math.abs(j - gc);
                }
            }
        }
        return sum;
    }
    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tiles[i][j] != goal[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        // Basically have to check even/odd status of number of inversions.

        return false;
    }

    public static Board swap(int[][] board, int i1, int j1, int i2, int j2) {
        int temp = board[i1][j1];
        board[i1][j1] = board[i2][j2];
        board[i2][j2] = temp;
        Board b = new Board(board);
        System.out.println("swap call:");
        printBoard(b);
        return b;
    }

    public static void printBoard(Board board) {
        for (int i = 0; i < board.size; i++) {
            for (int j = 0; j < board.size; j++) {
                System.out.print(board.tiles[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        ArrayList<Board> boards = new ArrayList<>();
        int[][] copyOfTiles = tiles;
        int i = -1, j = -1;
        for (int x = 0; x < size(); x++) {
            for (int y = 0; y < size(); y++) {
                if (tiles[x][y] == 0) {
                    i = x;
                    j = y;
                    System.out.print("Found 0! It's at " + i + " , " + j);
                    break;
                }
                if (i >= 0) {
                    break;
                }
            }
        }
        if (i >= 0) {
            if (i + 1 < size) {
                boards.add(swap(copyOfTiles, i, j, i + 1, j));
            }
            if (j + 1 < size) {
                boards.add(swap(copyOfTiles, i, j, i, j + 1));
            }
            if (i - 1 >= 0) {
                boards.add(swap(copyOfTiles, i, j, i - 1, j));
            }
            if (j - 1 >= 0) {
                boards.add(swap(copyOfTiles, i, j, i, j - 1));
            }
            return boards;
        }
    return null;
    }
    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
       // Iterable<Board> it = board.neighbors();
        for (Board neighbor:board.neighbors()){
            printBoard(neighbor);
        }
    }
}
