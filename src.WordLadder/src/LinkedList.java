import java.util.ArrayList;

public class LinkedList<E extends Comparable<E>> {
    Node<E> head = new Node<>();
    Node<E> tail;
    int size;

    LinkedList(){
        this.size = 0;
    }

    public void insert(ArrayList<E> object) {
        Node<E> node = new Node<>(object);
        Node<E> current = head.next;
        Node<E> previous = head;
        previous.next = node;
        node.next = current;
        this.size++;
    }


    public int getSize(){
        return this.size;
    }

    public boolean isNotEmpty(){
        return this.size > 0;
    }

    public void print(){
        Node<E> current = head;
        while(current.next != null){
            System.out.println(current.next.value);
            current.next = current.next.next;
        }
    }

    public ArrayList<E> pop(){
        if (head.next.next != null) {
            ArrayList<E> value = head.next.value;
            head.next = head.next.next;
            return value;
        }
        return head.next.value;
    }






    class Node<E>{
        Node<E> next;
        public ArrayList<E> value;

        Node(){}
        Node(ArrayList<E> o){
            this.value = o;
            //next = e;
        }
    }
}
