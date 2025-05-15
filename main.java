import java.util.*;

public class Main {

    static class Graph {
        private List<Set<Integer>> adjList; // Para evitar arestas duplicadas
        private int V;

        public Graph(List<List<Integer>> inputAdjList) {
            this.V = inputAdjList.size();
            this.adjList = new ArrayList<>();

            // Copia as listas para sets para facilitar verificação de duplicatas
            for (List<Integer> neighbors : inputAdjList) {
                this.adjList.add(new HashSet<>(neighbors));
            }
        }

        public boolean isValid() {
            for (int i = 0; i < V; i++) {
                for (int neighbor : adjList.get(i)) {
                    // Verifica se o vértice está dentro do intervalo
                    if (neighbor < 0 || neighbor >= V) {
                        return false;
                    }

                    // Verifica auto-loop
                    if (neighbor == i) {
                        return false;
                    }

                    // Verifica se a aresta é bidirecional
                    if (!adjList.get(neighbor).contains(i)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        // Grafo válido: 0-1, 0-2, 1-2
        List<List<Integer>> validGraph = new ArrayList<>();
        validGraph.add(Arrays.asList(1, 2)); // 0
        validGraph.add(Arrays.asList(0, 2)); // 1
        validGraph.add(Arrays.asList(0, 1)); // 2

        Graph g1 = new Graph(validGraph);
        System.out.println("Grafo válido: " + g1.isValid());

        // Grafo inválido: auto-loop em 0
        List<List<Integer>> invalidGraph1 = new ArrayList<>();
        invalidGraph1.add(Arrays.asList(0, 1)); // 0
        invalidGraph1.add(Arrays.asList(0));    // 1

        Graph g2 = new Graph(invalidGraph1);
        System.out.println("Grafo inválido (auto-loop): " + g2.isValid());

        // Grafo inválido: falta (0→1 mas 1↛0)
        List<List<Integer>> invalidGraph2 = new ArrayList<>();
        invalidGraph2.add(Arrays.asList(1)); // 0
        invalidGraph2.add(Arrays.asList());  // 1

        Graph g3 = new Graph(invalidGraph2);
        System.out.println("Grafo inválido (não bidirecional): " + g3.isValid());
    }
}

