package com.appletree24.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




//赫夫曼树因编码形式的不同，可能导致最后形成的赫夫曼树与编码均不同，但WPL一定是唯一的。
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr={13,7,8,3,29,6,1};
        Node root=createHuffmanTree(arr);
        preOrder(root);
    }

    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }
    }


    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            System.out.println(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;
            //删除用过的两个节点
            nodes.remove(left);
            nodes.remove(right);
            //加入新生成的一个节点
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    public Node(int value){
        this.value=value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value-o.value;
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

}
