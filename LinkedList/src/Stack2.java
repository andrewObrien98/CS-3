public class Stack2<E extends Comparable<E>> {
    Node head;

    public void insert(E element){
        if(head == null){
            head = new Node(element);
            return;
        }
        Node node = new Node(element);
        node.next = head;
        head = node;
    }

    public void printStack(){
        Node node = head;
        while(node != null){
            System.out.println(node.element);
            node = node.next;
        }
    }

    public void delete(E element){
        head = delete(head, element);
    }

    public Node delete(Node node, E element){
        if(node == null){
            return null;
        }
        else if(node.element.compareTo(element) == 0){
            return node.next;
        }
        node.next = delete(node.next, element);
        return node;
    }

    class Node {
        Node next;
        E element;

        Node(E element){
            this.element = element;
        }
    }

    public static void main(String[] args) {
        Stack2<Integer> stack = new Stack2<>();
        stack.insert(1);
        stack.insert(2);
        stack.insert(3);
        stack.insert(4);
        stack.printStack();
        stack.delete(2);
        stack.delete(5);
        stack.insert(6);
        System.out.println("\n\n\n");
        stack.printStack();
    }
}
