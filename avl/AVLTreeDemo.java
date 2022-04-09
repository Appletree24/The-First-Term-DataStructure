package com.appletree24.avl;

import java.util.logging.Logger;

public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr={10,8,11,6,9,7};
        AVLTree avlTree=new AVLTree();
        for(int x:arr){
            avlTree.add(new Node(x));
        }
        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println();
        System.out.println("无平衡处理前");
        System.out.println(avlTree.getRoot().height());
        System.out.println("平衡处理后");
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot().height());
    }
}




class AVLTree{
    private Node root;
    private static final Logger logger=Logger.getLogger(String.valueOf(AVLTree.class));

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


    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}






class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public int leftHeight(){
        if(left==null){
            return 0;
        }
        return left.height();
    }

    public int rightHeight(){
        if(right==null){
            return 0;
        }
        return right.height();
    }



    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }


    private void leftRotate(){
        //以当前根节点的值创建节点
        Node newNode = new Node(value);
        //将新节点的左子树设置为当前节点的左子树
        newNode.left=left;
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right=right.left;
        //把当前节点的值替换成右子节点的值
        value=right.value;
        //把当前节点的右子树设置成当前节点右子树的右子树
        right=right.right;
        //把当前节点的左子节点设置成新的节点
        left=newNode;
    }


    /**
     * 右旋转（目的是为了降低左子树的高度）
     * 1.以根节点的值创建一个新的节点
     * 2.把当前节点的右子树设置为新节点的右子树
     * 3.新节点的左子树设置为当前节点的左子树的右子树
     * 4.当前节点的值换为左子节点的值
     * 5.把当前节点的左子树设置成左子树的左子树
     * 6.当前节点的右子树设置为新节点
     */
    private void rightRotate(){
        Node newNode=new Node(value);
        newNode.right=right;
        newNode.left=left.right;
        value=left.value;
        left=left.left;
        right=newNode;
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

        //当添加完一个节点后，如果（右子树高度-左子树高度）》1，就进行左旋转
        if((rightHeight()-leftHeight())>1) {
            //如果节点的右子树的左子树高度大于它的右子树的高度，那么我们先对此节点的右节点进行一个右旋转，再进行左旋转
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
            return;//执行完上述操作后，不要继续往下执行，此二者为二选一的关系，需要return
        }

        if((leftHeight()-rightHeight())>1){
            //如果节点的左子树的右子树高度大于它的左子树的高度，那么我们先对此节点的左节点进行一个左旋转，再进行右旋转
            if(left!=null&&left.rightHeight()>left.leftHeight()){
                left.leftRotate();
                rightRotate();
            }else{
                rightRotate();
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
