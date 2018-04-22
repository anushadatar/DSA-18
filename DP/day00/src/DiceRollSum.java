import java.util.HashMap;

public class DiceRollSum {

    // Runtime: TODO
    // Space: TODO
    public static int diceRollSum(int N) {
        HashMap<Integer, Integer> memo = new HashMap<>();
        // Calls recursive function.
        return diceRollRecursive(N, memo);
    }
    public static int diceRollRecursive(int N, HashMap<Integer, Integer> memo) {
        // Base cases/Subproblems
        if (N == 0) {
            return 1;
        }
        if (N < 0) {
            return 0;
        }
        // Has this been memoized
        if (memo.containsKey(N)) {
            return memo.get(N);
        }

        int value = 0;
        // Guessing
        for (int i = 1; i <= 6; i++) {
            value += diceRollRecursive(N - i, memo);
        }
        // Memoize
        memo.put(N, value);
        // Solution
        return value;
    }

    }
