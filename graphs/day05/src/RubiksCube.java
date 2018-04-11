import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

// use this class if you are designing your own Rubik's cube implementation
public class RubiksCube {

    int [] cube;
    // initialize a solved rubiks cube
    public RubiksCube() {
        int [] cube  = {0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5};
    }


    // creates a copy of the rubics cube
    public RubiksCube(RubiksCube r) {
        RubiksCube cubeCopy = new RubiksCube();
        System.arraycopy(r.cube, 0,cubeCopy.cube, 0, r.cube.length);
    }

    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;
        for (int i = 0; i < this.cube.length; i++) {
            if (this.cube[i] != other.cube[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * return a hashCode for this rubik's cube.
     *
     * Your hashCode must follow this specification:
     *   if A.equals(B), then A.hashCode() == B.hashCode()
     *
     * Note that this does NOT mean:
     *   if A.hashCode() == B.hashCode(), then A.equals(B)
     */
    @Override
    public int hashCode() {
        return cube.hashCode();
    }

    public boolean isSolved() {
        if (this.equals(new RubiksCube())) {
            return true;
        }
        return false;
    }


    // given a list of rotations, return a rubik's cube with the rotations applied
    public RubiksCube rotate(List<Character> c) {
        RubiksCube rub = this;
        for (char r : c) {
            rub = rub.rotate(r);
        }
        return rub;
    }

    public int[] swap(int[] cube, int val1, int val2) {
        int temp = cube[val1];
        cube[val1] = cube[val2];
        cube[val2] = temp;
        return cube;
    }

    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        RubiksCube copy = new RubiksCube(this);
        switch (c) {
            case 'u':
                // upper face clockwise

                break;
            case 'U':
                // upper face counterclockwise
                copy.cube = swap(copy.cube, 0, 22);
                copy.cube = swap(copy.cube, 1, 20);
                copy.cube = swap(copy.cube, , 22);
                copy.cube = swap(copy.cube, 0, 22);


                break;
            case 'r':
                // right face clockwise
                break;
            case 'R':
                // right face counterclockwise
                break;
            case 'f':
                // front face clockwise

                break;
            case 'F':
                // front face counterclockwise
                break;
        }
        return this;
    }

    // returns a random scrambled rubik's cube by applying random rotations
    public static RubiksCube scrambledCube(int numTurns) {
        RubiksCube r = new RubiksCube();
        char[] listTurns = getScramble(numTurns);
        for (int i = 0; i < numTurns; i++) {
            r = r.rotate(listTurns[i]);
        }
        return r;
    }

    public static char[] getScramble(int size){
        char[] listTurns = new char[size];
        for (int i = 0; i < size; i++) {
            switch (ThreadLocalRandom.current().nextInt(0, 6)) {
                case 0:
                    listTurns[i] = 'u';
                    break;
                case 1:
                    listTurns[i] = 'U';
                    break;
                case 2:
                    listTurns[i] = 'r';
                    break;
                case 3:
                    listTurns[i] = 'R';
                    break;
                case 4:
                    listTurns[i] = 'f';
                    break;
                case 5:
                    listTurns[i] = 'F';
                    break;
            }
        }
        return listTurns;
    }


    // return the list of rotations needed to solve a rubik's cube
    public List<Character> solve() {
        // TODO
        return new ArrayList<>();
    }

}



