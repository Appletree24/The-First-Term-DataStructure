package com.appletree24.prim;

import java.util.Arrays;

/**
 * @author Appletree24
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data=new char[]{'A','B','C','D','E','F','G'};
        int vertexes=data.length;
        int[][] weight={{10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},};
        MGraph mGraph=new MGraph(vertexes);
        MinTree minTree=new MinTree();
        minTree.createGraph(mGraph,vertexes,data,weight);
        //测试
        minTree.prim(mGraph,0);

    }

}



class MinTree{
    /**
     * 创建图的邻接矩阵
     * @param graph 图对象
     * @param vertexes 顶点个数
     * @param data 图各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph,int vertexes,char data[],int[][] weight){
        int i,j;
        for( i=0;i<vertexes;i++){
            graph.data[i]=data[i];
            for(j=0;j<vertexes;j++){
                graph.weight[i][j]=weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph){
        for(int[] link:graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     *Prim算法 最小生成树
     * @param graph 要生成最小生成树的图
     * @param v 从图中哪个节点创建最小生成树 'A'->0 'B'->1
     */
    public void prim(MGraph graph,int v){
        //标记节点是否被访问过的数组
        int[] visited = new int[graph.vertexes];
        //将当前节点设置为已访问
        visited[v]=1;
        //h1和h2记录两个顶点的下标
        int h1=-1;
        int h2=-1;
        int count=0;
        //类似哨兵，因为我们找到一个小的数字就会替换之前的，所以这里故意设置一个很大的数字，保证肯定会被替换
        int minWeight=10000;
        //根据连通图生成树的定义，可知边的个数为顶点数减一，所以我们循环n-1次即可
        for(int k=1;k< graph.vertexes;k++){
            //确定每一次生成的子图和哪两个结点距离最近 i表示被访问过的节点
            for(int i=0;i< graph.vertexes;i++){
                //j表示还没有访问过的节点
                for(int j=0;j< graph.vertexes;j++){
                    if(visited[i]==1&&visited[j]==0&&graph.weight[i][j]<minWeight){
                        //替换minWeight（寻找已经访问过的系欸但和未访问过的节点间权值最小的边）
                        minWeight=graph.weight[i][j];
                        h1=i;
                        h2=j;
                    }
                }
            }
            count+=minWeight;
            System.out.println("边"+graph.data[h1]+","+graph.data[h2]+"权值:"+minWeight);
            visited[h2]=1;
            minWeight=10000;
        }
        System.out.println(count);
    }

}



/**
 * vertex顶点 data存放节点数据 weight存放边 邻接矩阵
 */
class MGraph{
    int vertexes;
    char[] data;
    int[][] weight;

    public MGraph(int vertexes){
        this.vertexes=vertexes;
        data=new char[vertexes];
        weight=new int[vertexes][vertexes];
    }

}


