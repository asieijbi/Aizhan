import java.util.ArrayList;
import java.util.List;

public class BST<K extends Comparable<K>, V> {

    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left, right;
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }


    public class Entry {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }

        @Override
        public String toString() {
            return "{" + key + " -> " + value + "}";
        }
    }


    public void put(K key, V val) {
        root = put(root, key, val);
    }


    private Node put(Node node, K key, V val) {
        if (node == null) {
            size++;
            return new Node(key, val);
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.val = val;
        }

        return node;
    }


    public V get(K key) {
        Node node = root;

        while (node != null) {
            int cmp = key.compareTo(node.key);

            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.val;
            }
        }

        return null;
    }


    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {

            size--;


            if (node.right == null) return node.left;

            if (node.left == null) return node.right;


            Node successor = findMin(node.right);
            successor.right = deleteMin(node.right);
            successor.left = node.left;
            node = successor;
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }


    public int size() {
        return size;
    }


    public Iterable<Entry> iterator() {
        List<Entry> list = new ArrayList<>();
        inOrder(root, list);
        return list;
    }


    private void inOrder(Node node, List<Entry> list) {
        if (node == null) return;
        inOrder(node.left, list);
        list.add(new Entry(node.key, node.val));
        inOrder(node.right, list);
    }

}
