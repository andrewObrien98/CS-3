public class LinkedList2<E extends Comparable<E>> {
    Node head;

    public void insert(E element){
        if(head == null){
            head = new Node(element);
        } else {
            Node node = head;
            while(node.next != null){
                node = node.next;
            }
            node.next = new Node(element);
        }
    }

    public void printLinkedList(){
        Node node = head;
        while(node != null){
            System.out.println(node.element);
            node = node.next;
        }
    }

    public class Node {
        E element;
        Node next;

        Node (E element){
            this.element = element;
        }
    }

    public static void main(String[] args) {
        LinkedList2<String> list = new LinkedList2<>();
        list.insert("Andrew");
        list.insert("McCall");
        list.insert("Milo");
        list.insert("Nate");
        list.printLinkedList();
    }

}
