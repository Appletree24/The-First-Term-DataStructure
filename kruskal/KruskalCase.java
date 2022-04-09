package com.appletree24.kruskal;

import java.lang.reflect.Array;
import java.util.Arrays;

public class KruskalCase {
    private int edgeNum;
    private char[] vertexes;
    private int[][] matrix;
    //使用INF，表示两条边不能连通
    private static final int INF=Integer.MAX_VALUE;
    public static void main(String[] args) {
        char[] vertexes={'A','B','C','D','E','G'};
    }


    public KruskalCase(char[] vertexes,int[][] matrix){
        //初始化顶点数和边的个数
        int vNum=vertexes.length;
        //初始化顶点
        this.vertexes=new char[vNum];
        for(int i=0;i<vertexes.length;i++){
            this.vertexes[i]=vertexes[i];
        }
//        this.vertexes=Arrays.copyOf(vertexes,vertexes.length);
        //初始化边
        this.matrix=new int[vNum][vNum];
        for(int i=0;i<vNum;i++){
            for(int j=0;j<vNum;j++){
                this.matrix[i][j]=matrix[i][j];
            }
        }
        //统计边的个数
        for(int i=0;i<vNum;i++){
            for(int j=i+1;j<vNum;j++){
                if(this.matrix[i][j]!=INF){
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal(){
        //表示最后结果数组的索引
        int index=0;
        //保存已有最小生成树中的每个顶点在最小生成树中的终点
        int[] ends=new int[edgeNum];
        //创建结果数组，用于保存最后的最小生成树
        EData[] rets=new EData[edgeNum];
        //获取图中所有边的集合
        EData[] edges=getEdges();
        for(int i=0;i<edgeNum;i++){
            //获取第i条边的起点
            int p1=getPosition(edges[i].start);
            int p2=getPosition(edges[i].end);

            //获取p1这个顶点在已有的生成树中的终点
            int m=getEnd(ends,p1);
            int n=getEnd(ends,p2);
            //判断是否构成回路
            if(m!=n){
                ends[m]=n;
                rets[index++]=edges[i];
            }
        }
        System.out.println(Arrays.toString(rets));
    }

    public void print(){
        System.out.println("邻接矩阵为:");
        for(int i=0;i<vertexes.length;i++){
            for(int j=0;j<vertexes.length;j++){
                System.out.printf("%12d",matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对边进行排序

    /**
     * 对边进行排序
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges){
        for(int i=0;i<edges.length-1;i++){
            for(int j=0;j<edges.length-1-i;j++){
                if(edges[j].weight>edges[j+1].weight){
                    EData tmp=edges[j];
                    edges[j]=edges[j+1];
                    edges[j+1]=tmp;
                }
            }
        }
    }

    /**
     *
     * @param ch 传入顶点的值
     * @return 返回对应顶点的下标
     */
    private int getPosition(char ch){
        for(int i=0;i<vertexes.length;i++){
            if(vertexes[i]==ch){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边，放到EData这个数组中，后面需要遍历该数组
     * 通过matrix邻接矩阵获得
     * @return EData[]的形式是  {['A','B',12],['B','F',7]}……
     */
    private EData[] getEdges(){
        int index=0;
        EData[] edges=new EData[edgeNum];
        for(int i=0;i<vertexes.length;i++){
            for(int j=i+1;j<vertexes.length;j++){
                if(matrix[i][j]!=INF){
                    edges[index++]=new EData(vertexes[i],vertexes[j],matrix[i][j] );
                }
            }
        }
        return edges;
    }

    /**
     * '获取下标为i的顶点的终点，用于后面判断两个顶点的重点是否相同
     * @param ends 记录各个顶点对应的终点是哪个
     * @param i 传入节点对应的下标
     * @return 下标为i的顶点对应的终点的下标
     */
    private int getEnd(int[] ends,int i){
        while (ends[i]!=0){
            i=ends[i];
        }
        return i;
    }

}

//创建一个新的类EData，他的对象实例就表示一条边
class EData{
    //边的一个点
    char start;
    //边的另外一个点
    char end;
    int weight;
    public EData(char start,char end,int weight){
        this.start=start;
        this.end=end;
        this.weight=weight;
    }
    //重写toString方法

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }


}
