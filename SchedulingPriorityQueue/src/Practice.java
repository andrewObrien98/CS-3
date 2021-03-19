public class Practice {
    public static void main(String[] args) {
        LeftistHeap<Integer> numbers = new LeftistHeap<>();
        numbers.insert(1);
        numbers.insert(2);
        numbers.insert(3);
        numbers.insert(4);
        numbers.insert(5);
        numbers.insert(6);
        numbers.insert(7);
        numbers.insert(8);
        numbers.printTree();
        System.out.println("\n\n");
        System.out.println("Minimum is: " + numbers.deleteMin());
        System.out.println("\n\n");
        numbers.printTree();
        System.out.println("\n\n");
        System.out.println("Minimum is: " + numbers.deleteMin());
        System.out.println("\n\n");
        numbers.printTree();
        System.out.println("\n\n");
        System.out.println("Minimum is: " + numbers.deleteMin());
        System.out.println("\n\n");
        numbers.printTree();
        System.out.println("\n\n");
        System.out.println("Minimum is: " + numbers.deleteMin());
        System.out.println("\n\n");
        numbers.printTree();

    }
}
