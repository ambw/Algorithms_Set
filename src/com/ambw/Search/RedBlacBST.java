package com.ambw.Search;

import com.sun.org.apache.regexp.internal.RE;

import java.util.NoSuchElementException;

/**
 * Created by bing0ne on 12/09/2016.
 */
public class RedBlacBST<Key extends Comparable<Key>,Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        Key key;
        Value val;
        Node left,right;
        int N;
        boolean color;

        Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        return x != null && x.color;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);

        return x;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    private Node rotateRight(Node h) {
        Node x = h.right;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);

        return x;
    }

    private void flipColor(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    public Node put(Node h, Key key, Value val) {
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
        if (isRed(h.left) && isRed(h.right)) {
            flipColor(h);
        }

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node balance(Node h) {
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }

        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColor(h);
        }
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node moveRedLeft(Node h) {


        // h的兄弟结点也是2-结点
        // 将左结点和父节点的最小键和左子节点最近的兄弟结点组成为一个四结点
        //如果不是 也要先转换 这样有益于下面部分的变化 因为这里的旋转的限制
        flipColor(h);


        //如果h的兄弟结点不是二结点
        //h的兄弟结点的右节点 给到父节点, 父节点的最小健移动到左子结点
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColor(h);
        }
        return h;
    }

    public void deleteMin() {


        //如果 root 的 左子节点是2-结点
        //root to  REF
        // 右子节点如果是3-结点 这个等式无法看出 root TO RED
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = deleteMin(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }

    }

    private Node deleteMin(Node h) {
        if (h.left == null) {
            return null;
        }

        // 判断左子结点 是不是3- 结点
        //如果不是 改变2结点
        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.right);

        return balance(h);
    }


    public boolean isEmpty() {
        if (root.N == 0) {
            return false;
        } else {
            return true;
        }
    }



    private Node moveRedRight(Node h) {

        // 这里是两种情况的合并,一种是左右两个结点都是2-结点,变为4-结点;
        // 另一种是左结点不是从左结点借结点,但是对于这种情况我们也需要这个变化,来帮助我们旋转变化。
        flipColor(h);

        // 当左结点为3-结点
        // 旋转变换
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColor(h);
        }

        return h;
    }

    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = deleteMax(root);

        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node deleteMax(Node h) {


        // 当当前结点是3-结点时候,右旋,来方便删除
        if (isRed(h.left)) {
            h = rotateRight(h);
        }

        // 当当前结点的右节点为 null 说明没有结点比他更大
        // 又因为,我们通过旋转变化使得当前结点的红链接向右,所以直接删除
        if (h.right == null) {
            return null;
        }

        //判断当前结点的➡右结点是不是3-结点,如果不是从左结点借
        if (!isRed(h.right) && !isRed(h.right.left)) {
            h = moveRedRight(h);
        }

        h.right = deleteMax(h.right);

        return balance(h);
    }

    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = delete(root, key);

        if(!isEmpty()) root.color = BLACK;
    }

    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left)) {
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)) {
                h = rotateRight(h);
            }
            if (key.compareTo(h.key) == 0 && h.right == null) {
                return null;
            }
            if (!isRed(h.right) && !isRed(h.right.left)) {
                h = moveRedRight(h);
            }
            if (key.compareTo(h.key) == 0) {
                h.val = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            } else {
                h.right = delete(h.right, key);
            }


        }
        return balance(h);
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    }

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) {
        // assert x != null;
        if (x.left == null) return x;
        else                return min(x.left);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
    }

    // the largest key in the subtree rooted at x; null if no such key
    private Node max(Node x) {
        // assert x != null;
        if (x.right == null) return x;
        else                 return max(x.right);
    }


    public Value get(Key key) {
        if (key == null) throw new NullPointerException("argument to get() is null");
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }
}
