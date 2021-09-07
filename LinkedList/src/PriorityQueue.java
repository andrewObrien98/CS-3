public class PriorityQueue<E extends Comparable<E>> {
    Node head;

    public void insert(E element){
        Node node = new Node(element);
        head = insert(head,node);
    }
    public Node insert(Node node,Node newNode){
        if(node == null){
            return newNode;
        }
        if(newNode.element.compareTo(node.element) >= 0){
            newNode.next = node;
            return newNode;
        }
        node.next = insert(node.next, newNode);
        return node;
    }

    public void print(){
        Node node = head;
        while(node != null){
            System.out.println(node.element);
            node = node.next;
        }
    }

    public class Node {
        E element;
        Node next;
        public Node(E element){
            this.element = element;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> PQ = new PriorityQueue<>();
        PQ.insert(5);
        PQ.insert(6);
        PQ.insert(4);
        PQ.insert(10);
        PQ.insert(3);
        PQ.print();
    }
}
