package com.appletree24.BinaryTree.ThreadedBinaryTree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root=new HeroNode(1,"Zzh1");
        HeroNode node2=new HeroNode(3,"Zzh2");
        HeroNode node3=new HeroNode(6,"zZH3");
        HeroNode node4=new HeroNode(8,"Zzh4");
        HeroNode node5=new HeroNode(10,"Zzh5");
        HeroNode node6=new HeroNode(14,"Zzh6");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        BinaryTree binaryTree=new BinaryTree();
        binaryTree.setRoot(root);
        binaryTree.threadedNodes();
        binaryTree.threadedList();
        HeroNode heroNode=node5.getLeft();
        System.out.println(heroNode);
    }
}

class BinaryTree {
    private HeroNode root;

    //指向要线索化的节点的前一个节点
    private HeroNode pre;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //遍历线索化二叉树
    public void threadedList() {
        HeroNode node = root;
        while (node != null) {
            //循环找leftType==1的节点，找到线索化处理后的有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1) {
                node=node.getRight();
                System.out.println(node);
            }
            node=node.getRight();
        }
    }


    /**
     *线索化二叉树节点
     * @param node 当前需要线索化的节点
     */
    public void threadedNodes(HeroNode node){
        if(node==null){
            return;
        }

        //先线索化左子树
        threadedNodes(node.getLeft());
        //线索化当前节点
        if(node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre!=null&&pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre=node; //勿忘
        //线索化右子树
        threadedNodes(node.getRight());
    }

    public void delNode(int no){
        if(root!=null){
            if(root.getNo()==no&&root.getLeft()==null&&root.getRight()==null){
                root=null;
            }else{
                root.delNode(no);
            }
        }else{
            System.out.println("二叉树为空");
        }
    }
}


class HeroNode {
    private int no;
    private String name;
    private HeroNode Left;
    private HeroNode Right;
    /**
     * 当leftType==0时，表示指向左子树，为1表示指向前驱结点
     * 当rightType==0时，表示指向右子树，为1表示指向后继结点
     */
    private int leftType;
    private int rightType;


    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        super();
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return Left;
    }

    public void setLeft(HeroNode left) {
        Left = left;
    }

    public HeroNode getRight() {
        return Right;
    }

    public void setRight(HeroNode right) {
        Right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public void delNode(int no) {
        if (this.Left != null && this.Left.no == no) {
            this.Left = null;
            return;
        }
        if (this.Right != null && this.Right.no == no) {
            this.Right = null;
            return;
        }
        if (this.Left != null) {
            this.Left.delNode(no);
        }
        if (this.Right != null) {
            this.Right.delNode(no);
        }
    }
}



