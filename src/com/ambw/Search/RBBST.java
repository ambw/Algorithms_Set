package com.ambw.Search;

/**
 * Created by bing0ne on 14/09/2016.
 */
public class RBBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node{
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        //表示结点颜色
        boolean color;

        Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    private int size(Node h) {
        if (h == null) {
            return 0;
        } else {
            return h.N;
        }
    }

    public int size() {
        return size(root);
    }

    private boolean isRed(Node h) {
        return h != null && h.color;
    }

    //右旋 只有3-结点可以旋转
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;

        x.N = h.N;

        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    //左旋 这又3-结点 可以旋转
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;

        x.N = h.N;

        h.N = 1 + size(h.left) + size(h.right);

        return h;
    }

    private Node flipColor(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
        return h;
    }

    public void put(Key key, Value val) {
        root = put(root,key,val);

    }

    private Node put(Node h, Key key, Value val) {

        if (h == null) {
            return new Node(key, val, 1, RED);
        }

        int cmp = key.compareTo(h.key);

        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }

        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }

        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.right) && isRed(h.left)) {
            h = flipColor(h);
        }

        h.N = size(h.left) + size(h.right) + 1;

        return h;
    }

    private Node balance(Node h) {

        //不同于 put 的方法。 去除判断左结点是否为红
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }

        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.right) && isRed(h.left)) {
            h = flipColor(h);
        }

        return h;
    }

    private boolean isEmpty() {
        return root.N == 0;
    }

    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = deleteMin(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }

    }

    private Node moveRedLeft(Node h) {
        //当左右两个结点都是2-结点的时候
        //将 h和两个子节点组为一个4-结点
        //h 变为黑色的结点
        if(!isRed(h.right) && !isRed(h.right.left))
            flipColor(h);

        //h的右节点是3-结点的情况
        //我们要将右子节点的最小键移动到  h所在的结点
        //根据前面的左右旋转代码 可知 我们无法将两个黑色的结点交换
        //所以我们需要先改变 h 及其子节点的颜色来帮助我们移动右子节点的最小键
        if(isRed(h.right.left)){
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            //当我们将之移动完成后我们需要恢复他的颜色
            flipColor(h);
        }

        return h;
    }

    private Node deleteMin(Node h) {
        if (h.left == null) {
            return null;
        }

        //判断左子节点说不是3-结点 如果不是移动右子节点的最小键
        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }
        return balance(h);
    }
}
