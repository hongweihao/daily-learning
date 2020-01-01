package mkii.datastru;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 最重要的还是图的存储方式，即数据结构
 *
 */
public class Graph {
    private int v; // 顶点的个数
    private LinkedList<Integer>[] adj; // 邻接表，跟二维数组相似

    // 构造的时候需要明确顶点数量，因为是数组所以顶点数量确定就不能改变
    // 相当于先把所有的顶点放入进去，接下来只能添加这些顶点对应的边
    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    // 添加顶点之间的边，存储在邻接表中
    public void addEdge(int s, int t) { // 无向图一条边存两次
        adj[s].add(t);
        adj[t].add(s);
    }

    /** 广度搜索，顶点s到顶点t的路径。一层一层遍历搜索
     *
     * 时间复杂度：
     * 空间复杂度：
     *
     * @param s
     * @param t
     */
    public void bfs(int s, int t) {
        if (s == t) return;
        // 记录节点是否被访问过
        boolean[] visited = new boolean[v];
        visited[s]=true;
        // 广度搜索算法需要层层遍历，用来存储已被访问但是相连节点还没有被访问的节点
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        // 用来记录路径
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        while (queue.size() != 0) {
            // 出队，如果没有元素会返回null
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); ++i) {
                // 遍历顶点w的邻接表
                int q = adj[w].get(i);
                if (!visited[q]) {
                    // prev[q] 存储的是，顶点 q 是从哪个前驱顶点遍历过来的。
                    prev[q] = w;
                    // 判断顶点w的相连节点中有没有顶点t
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    // 相连顶点放入队列
                    queue.add(q);
                }
            }
        }
    }

    private void print(int[] prev, int s, int t) { // 递归打印s->t的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }


    /**
     * 深度优先遍历：
     *
     */
    // 递归终止条件
    boolean found = false; // 全局变量或者类成员变量

    public void dfs(int s, int t) {
        found = false;
        // 存放元素是否访问过
        boolean[] visited = new boolean[v];
        // 存放路径，深度优先搜索不一定得到最短的路径
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }

        // 表示查找s到t
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        // 递归终止条件
        if (found == true) return;
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        // 遍历顶点w的邻接表，每个顶点深入递归（深度）
        for (int i = 0; i < adj[w].size(); ++i) {
            int q = adj[w].get(i);
            if (!visited[q]) {
                // 走过的路径
                prev[q] = w;
                // 从当前顶点的邻接表对应顶点q到t，继续搜索
                recurDfs(q, t, visited, prev);
            }
        }
    }

    public static void main(String[] args) {
        // 创建6个顶点的图，并设定边
        Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        //graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        graph.bfs(0, 4);
        System.out.println();
        graph.dfs(0, 4);
    }
}
