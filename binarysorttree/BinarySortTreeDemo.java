package com.appletree24.binarysorttree;

import java.util.logging.Logger;

/**
 * @author Appletree24
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr={7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree=new BinarySortTree();
        for(int x:arr){
            binarySortTree.add(new Node(x));
        }
        binarySortTree.infixOrder();
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(1);
        binarySortTree.delNode(10);
        System.out.println();
        binarySortTree.infixOrder();

    }
}



class BinarySortTree{
    private Node root;
    private static final Logger logger=Logger.getLogger(String.valueOf(BinarySortTree.class));

    public Node search(int value){
        if(root==null){
            return null;
        }else{
            return root.search(value);
        }
    }


    public Node searchParent(int value){
        if(root==null){
            return null;
        }else{
            return root.searchParent(value);
        }
    }


    /**
     *
     * @param node  传入的节点（当成二叉排序树的根节点）
     * @return  返回以Node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node){
        //此方法同时还要删除这一最小节点
        while(node.left!=null){
            node=node.left;
        }
        //这时target指向了最小节点
        //删除最小节点
        delNode(node.value);
        return node.value;
    }



    public void delNode(int value){
        if(root==null){
            return;
        }else{
            //1.先找到要删除的节点
            Node targetNode=search(value);
            if(targetNode==null){
                return;
            }
            //如果二叉树只有一个根节点
            if(root.left==null&&root.right==null){
                root=null;
                return;
            }

            //寻找父节点
            Node parent=searchParent(value);
            //叶子节点
            if(targetNode.left==null&&targetNode.right==null){
                if(parent.left!=null&&parent.left.value==value){
                    parent.left=null;
                }else if(parent.right!=null&&parent.right.value==value){
                    parent.right=null;
                }
            }else if(targetNode.left!=null&&targetNode.right!=null){
                /*
                  删除有两棵子树的节点
                  1.首先找到要删除的节点targetNode
                  2.找到targetNode的父节点parent
                  3.从targetNode的 右子树找到最小的节点（左子树就是找最大的节点）
                  4.用一个临时变量，将最小节点的值保存
                  5.删除最小节点
                  6.targetNode.value=temp  把targetNode值换为找到的最小节点
                 */
                targetNode.value= delRightTreeMin(targetNode.right);
            }else{  //一棵子树
                if (targetNode.left != null) {
                    if (parent != null) {
                        if (parent.left.value == targetNode.value) {
                            parent.left = targetNode.left;
                        } else {  //右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {  //target节点有右子节点
                    if (parent.left.value == value) {
                        parent.left = targetNode.right;
                    } else {
                        parent.right = targetNode.right;
                    }
                }
            }
        }
    }


    public void add(Node node){
        if(root==null){
            root=node;
        }else{
            root.add(node);
        }
    }

    public void infixOrder(){
        if(root!=null){
            root.infixOrder();
        }else{
            logger.info("此二叉树为空");
        }
    }

}


class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }


    /**
     *
     * @param value  希望删除的节点的值
     * @return  返回节点或NULL
     */
    public Node search(int value){
        if(value==this.value){
            return this;
        }else if(value<this.value){
            if(this.left==null){
                return null;
            }
            return this.left.search(value);
        }else{
            if(this.right==null){
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     *找到要删除节点的父节点
     * @param value  要查找的节点值
     * @return  返回要删除节点的父节点 没有就返回null
     */
    public Node searchParent(int value){
        if((this.left!=null&&this.left.value==value)||(this.right!=null&&this.right.value==value)){
            return this;
        }else{
            //如果要查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if(value<this.value&&this.left!=null){
                return this.left.searchParent(value);
            }else if(value>=this.value&&this.right!=null){
                return this.right.searchParent(value);
            }else{
                return null;
            }
        }
    }




    /**
     * 添加节点
     * @param node 节点
     */
    public void add(Node node){
        if(node==null){
            return;
        }

        if(node.value<this.value){
            if(this.left==null){
                this.left=node;
            }else{
                //递归添加
                this.left.add(node);
            }
        }else{
            if(this.right==null){
                this.right=node;
            }else{
                this.right.add(node);
            }
        }
    }


    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.print(this.value+" ");
        if(this.right!=null){
            this.right.infixOrder();
        }
    }
}
