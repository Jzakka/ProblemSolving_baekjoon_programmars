import java.util.*;

class Main{
    public static final int UNSEEN=0;
    public static final int SEEN = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();
        int a,b;
        Graph undigraph = new Graph(n);
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            undigraph.addEdge(a,b);
        }
        undigraph.listSort();
        //undigraph.printList();
        undigraph.BFS(r,r);
        undigraph.printOrder(r);
    }
}

class ListComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return (Integer) o1 - (Integer) o2;
    }
}

class Graph{
    ArrayList<Integer> [] adjacentList;
    //public int[] state = new int[100001];

    public int[] orderArray = new int[100001];

    public int[] reachable = new int[100001];

    public boolean[] visited = new boolean[100001];

    private int visitOrder = 0;

    private Queue<Integer> queue = new LinkedList<>();

    public Graph(int n) {
        adjacentList = new ArrayList[n + 1];
        for (int i = 1; i < adjacentList.length; i++) {
            adjacentList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w){
        adjacentList[v].add(w);
        adjacentList[w].add(v);
    }

    public void listSort() {
        ListComparator lc = new ListComparator();
        for (int i = 1; i < adjacentList.length; i++) {
            adjacentList[i].sort(lc);
        }
    }

    public void DFS(int r, int startV){
        orderArray[r] = ++visitOrder;
        reachable[r] = startV;
        visited[r] = true;

        for(Integer adjacent: adjacentList[r]){
            if(!visited[adjacent]){
                DFS(adjacent, startV);
            }
        }
    }

    public void BFS(int r, int startV) {
        queue.offer(r);
        orderArray[r] = ++visitOrder;
        visited[r] = true;
        reachable[r] = startV;
        while (!queue.isEmpty()) {
            Integer u = queue.poll();
            for (Integer adjacent : adjacentList[u]) {
                if(!visited[adjacent])
                {
                    visited[adjacent] = true;
                    orderArray[adjacent] = ++visitOrder;
                    reachable[adjacent] = startV;
                    queue.offer(adjacent);
                }
            }
        }
    }

    public void printOrder(int startV) {
        for (int i = 1; i < adjacentList.length; i++) {
            if (reachable[i] != startV) {
                System.out.println(0);
            } else {
                System.out.println(orderArray[i]);
            }
        }
    }
}