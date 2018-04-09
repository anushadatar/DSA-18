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
     * This is like, O(n^2)
     */
    public boolean solvable() {
        // Basically have to check even/odd status of number of inversions.
        int [] inversions = new int[tiles.length * tiles.length];
        int total = 0;
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n; j++) {
               inversions[(i*tiles.length) + j] = tiles[i][j];
            }
        }
        for (int i = 0; i <inversions.length - 1; i++) {
            for (int j = i + 1; j < inversions.length; j++) {
                if (inversions[i] != 0 && inversions[j] != 0) {
                    if (inversions[j] > inversions[i]) {
                        total++;
                    }
                }
            }
        }
        return (total % 2 == 0);

    }

    public int[][] swap(int[][] board, int i1, int j1, int i2, int j2) {
        int temp = board[i1][j1];
            board[i1][j1] = board[i2][j2];
            board[i2][j2] = temp;
            return board;
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


    private static int[][] copyOf(int[][] A) {
        int[][] B = new int[A.length][A.length];
        for (int i = 0; i < A.length; i++) {
            System.arraycopy(A[i], 0, B[i], 0, A.length);
        }
        return B;
    }

    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        ArrayList<Board> boards = new ArrayList<>();
        int[][] copyOfTiles;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tiles[i][j] == 0) {
                    if (i + 1 < size) {
                        copyOfTiles = copyOf(tiles);
                        copyOfTiles = swap(copyOfTiles, i, j, i + 1, j);
                        boards.add(new Board(copyOfTiles));
                    }
                    if (j + 1 < size) {
                        copyOfTiles = copyOf(tiles);
                        copyOfTiles = swap(copyOfTiles, i, j, i, j + 1);
                        boards.add(new Board(copyOfTiles));
                    }
                    if (i - 1 >= 0) {
                        copyOfTiles = copyOf(tiles);
                        copyOfTiles = swap(copyOfTiles, i, j, i - 1, j);
                        boards.add(new Board(copyOfTiles));
                    }
                    if (j - 1 >= 0) {
                        copyOfTiles = copyOf(tiles);
                        copyOfTiles = swap(copyOfTiles, i, j, i, j - 1);
                        boards.add(new Board(copyOfTiles));
                    }
                }
            }
        }
        return boards;
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
        int[][] initState = {{1, 0, 3}, {2, 4, 5}, {6, 7, 8}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
    }
}
