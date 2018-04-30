public class EditDistance {

    public static int minEditDist(String a, String b) {
        // Subproblem: Number of edits needed to get from a[0] to a[i] to b[0] to b[i]
        // Guess: Whether to insert, replace, or delete
        // RR : The minimum value with each operation
        // Memoize : The memo is the solution to the subproblem for each i and j combination
        // Solve : memo[length][length]
        int m, n;
        int [][] memo = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i < memo.length; i++ ) {
            for (int j = 0; j < memo[0].length; j++) {
                m = i -1; n = j -1;
                if (i == 0) { memo[i][j] = j;}
                else if (j == 0) { memo[i][j] = i; }
                else {
                    int x = memo[i-1][j]  + 1;
                    int y = memo[i][j-1] + 1;
                    int z;
                    if (a.charAt(m) == b.charAt(n)) {  z = memo[i-1][j-1]; }
                    else {  z = memo[i-1][j-1] + 1; }
                memo[i][j] = Math.min(Math.min(x, y), z);
                }
            }
        }
        return memo[a.length()][b.length()];
    }
}