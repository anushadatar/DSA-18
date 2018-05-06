import java.util.Arrays;

// Subproblem : Minimum possible cost
// Guess: The last balloon we pop - that's the variable a
// Recurrence Relation : Figuring out the last one
// Memoize : Keep value for different subarrays
// Solve : Grab the end of the array associated with the full problem.

// O(N^3) where N is the length of the list
// O(N^2) space

public class BalloonPopping {

    public static int maxPoints(int[] B) {
        int[][] memo = new int[B.length + 2][B.length + 2];

        int[] values = new int[B.length + 2];
        values[0] = 1;
        values[B.length + 1] = 1;
        System.arraycopy(B, 0, values, 1, B.length);

        for (int i = 0; i < B.length; i++) {
            for (int j = 1; j < B.length + 1 - i; j++) {
                int k = i + j;
                int max_val = Integer.MIN_VALUE;
                for (int a = j; a < k + 1; a++) {
                    max_val = Math.max(max_val, values[a] * values[j -1] * values[k + 1] + memo[j][a-1] + memo[a+1][k]);
                }
                memo[j][k] = max_val;
            }
        }
    return memo[1][B.length];
    }
}
