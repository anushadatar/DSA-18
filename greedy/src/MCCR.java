public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            int[] distances = new int[G.numberOfV()];
            boolean[] set = new boolean[G.numberOfV()];
            IndexPQ<Integer> Q = new IndexPQ<>(G.numberOfE());
            Edge[] path = new Edge[G.numberOfE()];
            int total_cost = 0;

            for (int i = 0; i < distances.length; i++) {
                distances[i] = Integer.MAX_VALUE;
            }

            for (int i = 0; i < G.numberOfV(); i++) {
                if (!set[i]) {
                    distances[i] = 0;
                    Q.insert(i, distances[i]);
                    while (!Q.isEmpty()) {
                        int j = Q.delMin();
                        set[j] = true;

                        for (Edge e : G.edges(j)) {
                            int k = e.other(j);
                            if (e.weight() < distances[k] && !set[k]) {
                                distances[k] = e.weight();
                                path[k] = e;
                                if (Q.contains(k)) {
                                    Q.decreaseKey(k, distances[k]);
                                }
                                else {
                                    Q.insert(k, distances[k]);
                                }
                            }
                        }
                    }
                }
            }

            for (Edge e : path) {
                if (e != null) {
                    total_cost += e.weight();
                }
            }

            return total_cost;
        }

    }

