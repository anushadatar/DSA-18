// Subproblem = Minimal initial health, i to j
// Guess = Down or right??
// RR : Min of going down or going right, or 1 if no solution
// Memo : Next optimal move subproblem solution
// SOlution = initial value
// Everything is O(height x width)
public class DungeonGame {
    public static int minInitialHealth(int[][] map) {
        int[][] memo = new int[map.length][map[0].length];
        if (map[map.length-1][map[0].length-1] <= 0) {
            memo[map.length - 1][map[0].length - 1] = 1 + Math.abs(map[map.length - 1][map[0].length - 1]);
        }
        else {
                memo[map.length-1][map[0].length - 1] = 1;
        }
        for (int i = map.length - 2; i >= 0; i--) {
            memo[i][map[0].length - 1] = Math.max(memo[i + 1][map[0].length - 1] - map[i][map[0].length - 1], 1);
        }
        for (int j = map[0].length - 2; j >= 0; j--) {
            memo[map.length - 1][j] = Math.max(memo[map.length - 1][j + 1] - map[map.length - 1][j], 1);
        }
        for (int i = map.length - 2; i >= 0; i--) {
            for (int j = map[0].length - 2; j >= 0; j--) {
                int minimum_value = Math.min(memo[i+1][j], memo[i][j+1]);
                memo[i][j] = Math.max(minimum_value - map[i][j], 1);
            }
        }
        return memo[0][0];
    }
}
