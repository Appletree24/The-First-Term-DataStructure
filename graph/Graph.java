package com.appletree24.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Appletree24
 */
public class Graph {
    /**
     * 存储顶点集合
     */
    private ArrayList<String> vertexList;
    /**
     * 存储图对应的邻接矩阵
     */
    private int[][] edges;
    //边的数目
    private int numOfEdges;
    private boolean[] isVisted;



    public static void main(String[] args) {
        int n=5;
        String[] VertexValue={"A","B","C","D","E"};
        Graph graph=new Graph(n);
        for(String value:VertexValue){
            graph.insertVertex(value);
        }

        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        graph.showGraph();
        System.out.println();
        System.out.println("bfs:");
        graph.bfs();
    }

    /**
     *
     * @param n 要创建的图有多少个节点
     */
    public Graph(int n){
        //初始化矩阵和集合
        edges=new int[n][n];
        vertexList=new ArrayList<String>(n);
        numOfEdges=0;
        isVisted=new boolean[5];
    }


    //得到第一个邻接节点的下标w
    public int getFirstNeighbor(int index){
        for(int j=0;j<vertexList.size();j++){
            if(edges[index][j]>0){
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1,int v2){
        for(int j=v2+1;j<vertexList.size();j++){
            if(edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }


    private void dfs(boolean[] isVisted,int i){
        //dfs,i第一次是0
        //首先访问该节点，输出
        System.out.print(getValueByIndex(i)+"->");
        //将节点设置为已访问
        isVisted[i]=true;
        //查找i的第一个邻接节点w
        int w=getFirstNeighbor(i);
        while (w!=-1){
            if(!isVisted[w]){
                dfs(isVisted,w);
            }
            w=getNextNeighbor(i,w);
        }
    }

    public void dfs(){
        //遍历所有节点  重载
        for(int i=0;i<getNumOfVertex();i++){
            if(!isVisted[i]){
                dfs(isVisted,i);
            }
        }
    }

    private void bfs(boolean[] isVisited,int i){
        //u表示队列的头节点对应的下标 w表示第一个邻接节点
        int u;
        int w;
        //队列 将节点访问的顺序记录
        LinkedList queue = new LinkedList();
        //访问当前节点，输出信息
        System.out.print(getValueByIndex(i)+"->");
        isVisited[i]=true;
        //队列从尾部加入数据
        queue.addLast(i);
        while (!queue.isEmpty()){
            //取出队列的头节点下标
            u=(Integer) queue.removeFirst();
            //得到第一个邻接点下标w
            w=getFirstNeighbor(u);
            while(w!=-1){
                //是否访问过
                if(!isVisted[w]){
                    System.out.print(getValueByIndex(w)+"->");
                    isVisited[w]=true;
                    queue.addLast(w);
                }
                //在矩阵中，找u这一行对应的w之后的第一个邻接点
                w=getNextNeighbor(u,w);
            }
        }
    }


    public void bfs(){
        //bfs遍历所有节点 上面是单个节点
        for(int i=0;i<getNumOfVertex();i++) {
            if (!isVisted[i]) {
                bfs(isVisted, i);
            }
        }
    }

    /**
     *
     * @return 返回图中顶点个数
     */
    public int getNumOfVertex(){
        return vertexList.size();
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph(){
        for(int[] link:edges){
            System.out.println(Arrays.toString(link));
        }
    }

    public int getNumOfEdges(){
        return numOfEdges;
    }


    /**
     * 返回节点i（下标）对应的数据
     * @param i i代表下标 0->A 1->B 2->C 以此类推
     * @return 返回对应的节点数据
     */
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }


    /**
     *
     * @param v1 节点1
     * @param v2 节点2
     * @return 有无通路
     */
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }


    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }


    /**
     *
     * @param v1 节点1
     * @param v2 节点2
     * @param weight 权值 1代表有边 0代表无边
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        //无向图 对称
        edges[v2][v1]=weight;
        numOfEdges++;
    }

}
