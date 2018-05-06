public class Regex {
    // Subproblem : Does each index match
    // Guess whether or not it is the same value as before
    // Recurrence Relation: Going through each expansion
    // Memoize: Keeps the value true or false for whether or not they are equal for each index pair.
    // Solution : The full length of each string.

    // Time and Space are O(n^3)

    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        // Like the EditDistance, but different.
        boolean[][] memo = new boolean[s.length() + 1][p.length() + 1];
        memo[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if ( p.charAt(i) == '*' && memo[0][i-1]) {
                memo[0][i+1] = true;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {


                if (s.charAt(i) == p.charAt(j)) {
                    memo[i+1][j+1] = memo[i][j];
                }
                if (p.charAt(j) == '.') {
                    memo[i+1][j+1] = memo[i][j];
                }
                if (p.charAt(j) == '*') {
                    if ( p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        memo[i+1][j+1] = memo[i+1][j-1];
                    }
                    else {
                        memo[i+1][j+1] = (memo[i+1][j-1] || memo[i][j+1] || memo[i+1][j] );
                    }
                }
            }
        }
        return memo[s.length()][p.length()];
    }

}
