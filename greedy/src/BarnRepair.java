import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        Arrays.sort(occupied);
        int total = occupied.length;
        ArrayList<Integer> spaces = new ArrayList<>();
        for (int i = 1; i < total; i++) {
            int space = occupied[i] - occupied[i - 1];
            if (space > 1) {
                spaces.add(space - 1);
            }
        }
        Collections.sort(spaces);
        int boards = spaces.size() + 1;
        while (boards > M) {
            total += spaces.remove(0);
            boards--;
        }


        return total;
    }
}