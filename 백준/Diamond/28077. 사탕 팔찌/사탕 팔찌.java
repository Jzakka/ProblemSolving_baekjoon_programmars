import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static int K;

    static class Edge {
        String src;
        String dest;

        public Edge(String src, String dest) {
            this.src = src;
            this.dest = dest;
        }
    }


    private static Map<String, List<Edge>> graph = new HashMap<>();


    public static void main(String[] args) {
        String[] inputs = sc.nextLine().split("\\s+");
        int n = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);

        solution(n, k);
    }

    private static void solution(int n, int k) {
        K = k;
        makeBundles(n, k);
        List<String> bracelet = new ArrayList<>();
        if (hasEulerCirculation()) {
            eulerPath(bracelet);
            Collections.reverse(bracelet);
            bracelet = edgeList(bracelet);
            System.out.println("YES");
            StringBuilder res = new StringBuilder();
            bracelet.forEach(bundle -> res.append(bundle).append(" "));
            System.out.println(res);
            return;
        }
        System.out.println("NO");
    }

    private static List<String> edgeList(List<String> bracelet) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < bracelet.size() - 1;i++) {
            String prefix = bracelet.get(i);
            char suffix = bracelet.get(i + 1).charAt(K - 2);
            res.add(prefix+suffix);
        }

        return res;
    }

    private static void eulerPath(List<String> bracelet) {
        Stack<String> stk = new Stack<>();
        String current = graph.keySet().iterator().next(); // 시작점
        stk.push(current);

        while (!stk.isEmpty()) {
            current = stk.peek();
            if (graph.get(current) != null && !graph.get(current).isEmpty()) {
                Edge edge = graph.get(current).remove(graph.get(current).size()-1); // 사용한 간선은 제거
                stk.push(edge.dest);
            } else {
                bracelet.add(stk.pop());
            }
        }
    }


    private static boolean hasEulerCirculation() {
        Map<String, Integer> inDegree = new HashMap<>();
        for (String prefix : graph.keySet()) {
            List<Edge> edges = graph.get(prefix);
            for (Edge edge : edges) {
                mapIncrease(inDegree, edge.dest);
            }
        }

        for (String prefix : graph.keySet()) {
            if (graph.get(prefix).size() != inDegree.get(prefix)) {
                return false;
            }
        }

        Set<String> visited = new HashSet<>();
        dfs(visited, graph.keySet().iterator().next());
        return visited.size() == graph.keySet().size();
    }

    private static void dfs(Set<String> visited, String current) {
        Stack<String> stk = new Stack<>();
        stk.add(current);

        while (!stk.isEmpty()) {
            String node = stk.pop();
            visited.add(node);
            for (Edge edge : graph.get(node)) {
                if (!visited.contains(edge.dest)) {
                    stk.add(edge.dest);
                }
            }
        }
    }

    private static void mapIncrease(Map<String, Integer> inDegree, String suffix) {
        if (inDegree.containsKey(suffix)) {
            inDegree.put(suffix, inDegree.get(suffix) + 1);
        } else {
            inDegree.put(suffix, 1);
        }
    }

    private static void makeBundles(int n, int k) {
        Character[] characters = IntStream.range(0, n).mapToObj(i -> (char) ('A' + i)).toArray(Character[]::new);
        List<Character[]> startBudles = new ArrayList<>();
        recursiveBundleMake(startBudles, k, characters, new ArrayList<>(), 0);

        for (Character[] bundle : startBudles) {
            do {
                String part = Arrays.stream(bundle).map(String::valueOf).collect(Collectors.joining());
                mapAdd(part);
            } while (nextPermutation(bundle));
        }
    }

    private static void mapAdd(String part) {
        String prefix = part.substring(0, part.length() - 1);
        String suffix = part.substring(1);

        if (!graph.containsKey(prefix)) {
            graph.put(prefix, new ArrayList<>());
        }
        graph.get(prefix).add(new Edge(prefix,suffix));
    }

    private static void recursiveBundleMake(List<Character[]> bundles, int k, Character[] characters, List<Character> current, int start) {
        if (current.size() == k) {
            bundles.add(current.stream().toArray(Character[]::new));
            return;
        }

        for (int i = start; i < characters.length; i++) {
            current.add(characters[i]);
            recursiveBundleMake(bundles, k, characters, current, i + 1);
            current.remove(current.size() - 1);
        }
    }

    private static boolean nextPermutation(Character[] arr) {
        int len = arr.length;

        for (int i = len - 1; i > 0; i--) {
            if (arr[i - 1] < arr[i]) {
                Character smallest = Character.MAX_VALUE;
                int swapIdx = i;
                for (int j = i; j < len; j++) {
                    if (arr[i - 1] < arr[j] && smallest > arr[j]) {
                        smallest = arr[j];
                        swapIdx = j;
                    }
                }

                Character temp = arr[i - 1];
                arr[i - 1] = arr[swapIdx];
                arr[swapIdx] = temp;

                Arrays.sort(arr, i, len);
                return true;
            }
        }

        return false;
    }
}