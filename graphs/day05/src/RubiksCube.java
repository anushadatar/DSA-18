import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

// use this class if you are designing your own Rubik's cube implementation



public class RubiksCube {
    int [] cube = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5};
    // initialize a solved rubiks cube
    public RubiksCube() {
    }


    // creates a copy of the rubics cube
    public RubiksCube(RubiksCube r) {
        cube = r.cube;
    }
    public RubiksCube(int [] r) {
        cube = r;
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
     * <p>
     * Your hashCode must follow this specification:
     * if A.equals(B), then A.hashCode() == B.hashCode()
     * <p>
     * Note that this does NOT mean:
     * if A.hashCode() == B.hashCode(), then A.equals(B)
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(cube);
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

    private static void printArray(int[] anArray) {
        for (int i = 0; i < anArray.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(anArray[i]);
        }
        System.out.println();
    }

    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        //RubiksCube copy = new RubiksCube(this);
        //System.out.print(copy.cube);
        int[] newCube = new int[cube.length];
        int[] copyOfCube = new int[cube.length];

        System.arraycopy(this.cube, 0, copyOfCube, 0, cube.length);

        switch (c) {
            case 'u':
                // upper face clockwise
                newCube[17] = copyOfCube[0];
                newCube[19] = copyOfCube[1];
                newCube[0] = copyOfCube[22];
                newCube[1] = copyOfCube[20];
                newCube[22] = copyOfCube[11];
                newCube[20] = copyOfCube[10];
                newCube[11] = copyOfCube[17];
                newCube[10] =copyOfCube[19];
                newCube[14] = copyOfCube[15];
                newCube[12] = copyOfCube[14];
                newCube[13] = copyOfCube[12];
                newCube[15] = copyOfCube[13];
                break;
            case 'U':
                // upper face counterclockwise
                newCube[22] = copyOfCube[0];
                newCube[20] = copyOfCube[1];
                newCube[11] = copyOfCube[22];
                newCube[10] = copyOfCube[20];
                newCube[17] = copyOfCube[11];
                newCube[19] = copyOfCube[10];
                newCube[0] = copyOfCube[17];
                newCube[1] =copyOfCube[19];
                newCube[14] = copyOfCube[12];
                newCube[12] = copyOfCube[13];
                newCube[13] = copyOfCube[15];
                newCube[15] = copyOfCube[14];
                break;
            case 'r':
                // right face clockwise
                newCube[15] = copyOfCube[3];
                newCube[13] = copyOfCube[1];
                newCube[9] = copyOfCube[13];
                newCube[11] = copyOfCube[15];
                newCube[5] = copyOfCube[9];
                newCube[7] = copyOfCube[11];
                newCube[1] = copyOfCube[5];
                newCube[3] =copyOfCube[7];
                newCube[21] = copyOfCube[20];
                newCube[20] = copyOfCube[22];
                newCube[22] = copyOfCube[23];
                newCube[23] = copyOfCube[21];
                break;
            case 'R':
                // right face counterclockwise
                newCube[1] = copyOfCube[13];
                newCube[3] = copyOfCube[15];
                newCube[5] = copyOfCube[1];
                newCube[7] = copyOfCube[3];
                newCube[9] = copyOfCube[5];
                newCube[11] = copyOfCube[7];
                newCube[13] = copyOfCube[9];
                newCube[15] =copyOfCube[11];
                newCube[22] = copyOfCube[20];
                newCube[20] = copyOfCube[21];
                newCube[23] = copyOfCube[22];
                newCube[21] = copyOfCube[23];
                break;
            case 'f':
                // front face clockwise
                newCube[5] = copyOfCube[22];
                newCube[4] = copyOfCube[23];
                newCube[18] = copyOfCube[5];
                newCube[19] = copyOfCube[4];
                newCube[14] = copyOfCube[18];
                newCube[15] = copyOfCube[19];
                newCube[22] = copyOfCube[14];
                newCube[23] =copyOfCube[15];
                newCube[1] = copyOfCube[0];
                newCube[3] = copyOfCube[1];
                newCube[2] = copyOfCube[3];
                newCube[0] = copyOfCube[2];
                break;
            case 'F':
                // front face counterclockwise
                newCube[22] = copyOfCube[5];
                newCube[23] = copyOfCube[4];
                newCube[5] = copyOfCube[18];
                newCube[4] = copyOfCube[19];
                newCube[18] = copyOfCube[14];
                newCube[19] = copyOfCube[15];
                newCube[14] = copyOfCube[22];
                newCube[15] = copyOfCube[23];
                newCube[0] = copyOfCube[1];
                newCube[1] = copyOfCube[3];
                newCube[3] = copyOfCube[2];
                newCube[2] = copyOfCube[0];
                break;
        }
        return new RubiksCube(newCube);
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

    public static char[] getScramble(int size) {
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

    public List<RubiksCube> neighbors() {
        List<RubiksCube> n = new ArrayList<RubiksCube>();
        char[] potential_moves = {'u', 'U', 'r', 'R', 'f', 'F'};
        for (char a : potential_moves) {
            n.add(this.rotate(a));
        }
        return n;


    }


    // return the list of rotations needed to solve a rubik's cube
    public List<Character> solve() {

        return new ArrayList<>();
    }

public static void main(String[] args) {
    RubiksCube cube1 = new RubiksCube();
  //  cube1.rotate('f').rotate('f').rotate('f');
    RubiksCube cube2 = new RubiksCube();
//    cube2.rotate('F');
   // cube1.rotate('f');
    System.out.println(cube1.rotate('f').rotate('f').rotate('f').equals(cube2.rotate('F')));
    System.out.println(cube1.rotate('u').rotate('u').rotate('u').equals(cube2.rotate('U')));
    System.out.println(cube1.rotate('r').rotate('r').rotate('r').equals(cube2.rotate('R')));


    printArray(cube1.cube);
    printArray(cube2.cube);
}

}
