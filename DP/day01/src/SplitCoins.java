// Subproblem : Minimum distance in between
// Guess : Whether or not to include the value
// RR : Minimum difference between including and not including some value
// Memo : Minimum difference with i
// Solve : Return minimum memo value
// O (number of coins * largest sum)
public class SplitCoins {

    public static int splitCoins(int[] coins) {
        int total = 0;
        for (int i = 0; i < coins.length; i++) {
            total += coins[i];
        }
        int [][] memo = new int[coins.length][total];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
            }
        }
        return splitRec(coins, memo, 0, coins.length, total);
    }

    private static int splitRec(int[] coins, int[][] memo, int a, int b, int total) {
        int total_sum = total - a;
        if (b == 0) { return Math.abs(a - total_sum); }
        if (memo[b-1][a] != -1) { return memo[b-1][a]; }
        memo[b-1][a] = Math.min(splitRec(coins, memo, a + coins[b-1], b-1, total), splitRec(coins, memo, a, b-1,total));
        return memo[b-1][a];
    }
}
