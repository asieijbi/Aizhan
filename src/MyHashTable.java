public class MyHashTable<K, V> {



    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }


    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;


    public MyHashTable() {
        chainArray = new HashNode[M];
    }


    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
    }




    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }



    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];


        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }


        size++;
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
    }


    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value; // нашли!
            }
            head = head.next;
        }
        return null; // не нашли
    }


    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        HashNode<K, V> prev = null; // предыдущий узел

        while (head != null) {
            if (head.key.equals(key)) {
                size--;
                if (prev != null) {
                    prev.next = head.next; // пропускаем удаляемый узел
                } else {
                    chainArray[index] = head.next; // удаляем первый узел
                }
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null; // элемент не найден
    }


    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return true;
                }
                head = head.next;
            }
        }
        return false;
    }


    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return head.key;
                }
                head = head.next;
            }
        }
        return null;
    }


    public int getBucketSize(int index) {
        int count = 0;
        HashNode<K, V> head = chainArray[index];
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    public int getM() { return M; }
    public int size() { return size; }
}
