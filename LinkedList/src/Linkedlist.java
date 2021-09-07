public class Linkedlist<E extends Comparable<?>> {
    Node head;

    public void insert(E element){
        if (head == null){
            head = new Node(element);
        } else {
            Node node = head;
            while(node.next != null){
                node = node.next;
            }
            node.next = new Node(element);
        }
    }

    public void printList(){
        Node node = head;
        while (node != null){
            System.out.println(node.element);
            node = node.next;
        }
    }

    public class Node {
        E element;
        Node next;

        Node(E e){
           element = e;
        }
    }

}
