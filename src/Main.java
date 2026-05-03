void main() {

    // PART 1 — MyHashTable
    System.out.println(" PART 1: MyHashTable \n");

    MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();

    Random random = new Random();
    String[] names = {"Aizhan", "Bakberdi", "Amir", "Diana", "Eva",
            "Farida", "Gena", "Hank", "Ivy", "Mark"};

    for (int i = 0; i < 10000; i++) {
        MyTestingClass key = new MyTestingClass(
                random.nextInt(100000),
                names[random.nextInt(names.length)]
        );
        Student value = new Student(
                names[random.nextInt(names.length)],
                random.nextInt(100)
        );
        table.put(key, value);
    }

    System.out.println("Distribution across buckets:");
    for (int i = 0; i < table.getM(); i++) {
        System.out.println("Bucket " + i + ": " + table.getBucketSize(i) + " elements");
    }
    System.out.println("Total elements: " + table.size());
    System.out.println("Ideal count per bucket: " + (10000 / table.getM()));

    // PART 2 — BST
    System.out.println("\n PART 2: BST \n");

    BST<Integer, String> tree = new BST<>();

    tree.put(5, "five");
    tree.put(3, "three");
    tree.put(7, "seven");
    tree.put(1, "one");
    tree.put(4, "four");

    System.out.println("Tree size: " + tree.size());
    System.out.println("get(3): " + tree.get(3));
    System.out.println("get(9): " + tree.get(9));

    System.out.println("\nIn-order traversal (sorted):");
    for (var elem : tree.iterator()) {
        System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
    }

    tree.delete(3);
    System.out.println("\nAfter deleting 3:");
    for (var elem : tree.iterator()) {
        System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
    }
}
