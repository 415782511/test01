package homework14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    public static void main(String[] args) {
        int n = 5;
        String vertexs[] = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        //添加定点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.showGraph();
/*        System.out.println("深度优先");
        graph.dfs();
        System.out.println();*/
        System.out.println("广度优先");
        graph.bfs();
    }

    private ArrayList<String> vertexList;//储存顶点集合
    private int[][] edges;//连接矩阵
    private int numOfEdges;//边的数目
    private boolean[] isVisited;

    //构造函数
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    //得到第一个邻接节点的下标w(存在返回对应下标，没找到返回—1)
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //dfs进行重载
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //深度优先遍历算法
    public void dfs(boolean[] isVisited, int i) {
        System.out.print(getValueByIndex(i) + " -> ");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) {//找到下一个邻接节点
            if (!isVisited[w]) {
                dfs(isVisited, w);
            } else {
                w = getNextNeighbor(i, w);
            }
        }

    }

    //对所有节点进行广度优先搜索
    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    //对一个节点进行广度优先的方法
    public void bfs(boolean[] isVisited, int i) {
        int u;//队列头结点
        int w;//邻接节点w
        LinkedList queue = new LinkedList();
        System.out.print(getValueByIndex(i) + " => ");
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()) {
            u = (Integer) queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {//找到了
                if ( !isVisited[w]) {
                    System.out.print(getValueByIndex(w) + " => ");
                    isVisited[w] = true;
                    queue.addLast(w);
                } else {
                    w = getNextNeighbor(u, w);
                }

            }
        }
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 插入边
     *
     * @param v1     点的下标
     * @param v2     点的下标
     * @param weight 两点间的距离
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //返回边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点i对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1 v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //现实图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

}
