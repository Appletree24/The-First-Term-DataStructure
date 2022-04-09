package com.appletree24.BinaryTree;

import java.util.Scanner;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "Zzh");
        HeroNode heroNode2 = new HeroNode(2, "Zzh2");
        HeroNode heroNode3 = new HeroNode(3, "Zzh3");
        HeroNode heroNode4 = new HeroNode(4, "Zzh4");
        HeroNode heroNode5 = new HeroNode(5, "Zzh5");
        binaryTree.setRoot(root);
        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode3.setLeft(heroNode5);
        heroNode3.setRight(heroNode4);
        System.out.println("后序遍历方式:");
        Scanner scanner = new Scanner(System.in);
        int no = scanner.nextInt();
        HeroNode resNode = binaryTree.postOrderSearch(no);
        if (resNode != null) {
            System.out.printf("找到此节点，no=%d,name=%s\n", resNode.getNo(), resNode.getName());
        } else {
            System.out.println("没有找到");
        }
        System.out.println("删除前，前序遍历");
        binaryTree.preOrder();
        binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();
    }
}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("当前二叉树为空");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("当前二叉树为空");
        }
    }

    public HeroNode preOderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            System.out.println("二叉树为空");
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            System.out.println("二叉树为空");
            return null;
        }
    }

    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            System.out.println("二叉树为空");
            return null;
        }
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
            return;
        }
    }

}


class HeroNode {
    private int no;
    private String name;
    private HeroNode Left;
    private HeroNode Right;

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


    public void preOrder() {
        System.out.println(this);
        if (this.Left != null) {
            this.Left.preOrder();
        }
        if (this.Right != null) {
            this.Right.preOrder();
        }
    }

    public void infixOrder() {
        if (this.Left != null) {
            this.Left.infixOrder();
        }
        System.out.println(this);
        if (this.Right != null) {
            this.Right.infixOrder();
        }
    }


    public void postOrder() {
        if (this.Left != null) {
            this.Left.preOrder();
        }
        if (this.Right != null) {
            this.Right.preOrder();
        }
        System.out.println(this);
    }


    /**
     * 前序查找
     *
     * @param no 要查找的节点编号
     * @return 返回节点 没找到就返回null
     */
    public HeroNode preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.Left != null) {
            resNode = this.Left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.Right != null) {
            resNode = this.Right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     *
     * @param no 同上
     */
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.Left != null) {
            resNode = this.Left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        if (this.Right != null) {
            resNode = this.Right.infixOrderSearch(no);
        }
        return resNode;
    }


    /**
     * 后续遍历查找
     *
     * @param no 编号
     * @return 同上
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.Left != null) {
            resNode = this.Left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.Right != null) {
            resNode = this.Right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

}
