import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
// Time : O(n!)
// Space : O(n)
public class Permutations {

    private static void backtrack(LinkedList<Integer> curr, Set<Integer> unused, List<List<Integer>> subsets) {
        if (unused.isEmpty())
            subsets.add(new LinkedList<>(curr));
        for (int u : new LinkedList<>(unused)) {
            curr.addLast(u);
            unused.remove(u);
            backtrack(curr, unused, subsets);
            unused.add(u);
            curr.removeLast();
        }
    }

    public static List<List<Integer>> permutations(List<Integer> A) {
        List<List<Integer>> perms = new LinkedList<>();
        LinkedList<Integer> current = new LinkedList<>();
        Set<Integer> unused = new HashSet<>(A);
        backtrack(current, unused, perms);
        return perms;
    }
}
