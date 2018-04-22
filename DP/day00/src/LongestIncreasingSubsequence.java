public class LongestIncreasingSubsequence {

    // Runtime: O(N^2)
    // Space: O(N)
    public static int LIS(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int max = 1;
        int[] memo = new int[A.length];
        // Guess the base case.
        memo[0] = 1;
        // Recurrence is just going through it?
        for (int i = 1; i <memo.length; i++) {
            int value = 0;
            for (int j = 0; j < i; j ++) {
                // Subproblem
                if (A[i] > A[j]) {
                    value = Math.max(value, memo[j]);
                }
            }
            // Add to the memo
            memo[i] = value + 1;
            max = Math.max(max, memo[i]);
        }
        // Solution
        return max;
    }

}

