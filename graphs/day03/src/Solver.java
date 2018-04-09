/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State {
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.cost = board.manhattan() + this.moves;
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }

        public int compareTo(State s) {
            return this.cost - s.cost;
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        while (state.prev != null) {
            return root(state.prev);
        }
        return state;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        this.solutionState = new State(initial, 0, null);
        PriorityQueue<State> open = new PriorityQueue<>();
        HashSet<State> closed = new HashSet<>();
        open.add(solutionState);
        if (!this.isSolvable()) {
            this.minMoves = solutionState.moves;
            this.solved = false;
            return;
        }
        while (!open.isEmpty()) {
            closed.add(solutionState);
            solutionState = open.poll();
            if (solutionState.board.isGoal()) {
                solved = true;
                break;
            }
            for (Board current : solutionState.board.neighbors()) {
                boolean ignored = false;
                State current_state = new State(current, solutionState.moves + 1, solutionState);
                for (State n:open) {
                    if (n.equals(current_state) && n.cost<current_state.cost) {
                        ignored = true;
                        break;
                    }
                }
                for (State n:closed) {
                    if (n.equals(current_state) && n.cost<current_state.cost) {
                        ignored = true;
                        break;
                    }
                }
                if (ignored == false) {
                    open.add(current_state);

                }
            }
            this.minMoves = solutionState.moves;
        } 
    }


    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        // TODO
        return this.solutionState.board.solvable();
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        State solution = this.solutionState;
        List <Board> solution_list = new LinkedList<>();
        while (solution != null) {
            solution_list.add(solution.board);
            solution = solution.prev;
        }
        return solution_list;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */
    public static void main(String[] args) {
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board initial = new Board(initState);

        Solver solver = new Solver(initial);
    }


}
