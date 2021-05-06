//this is just a simple Queue that was made for max flow
public class QueueMaxHeap<E> {
    Node root;
    public QueueMaxHeap(){}
    public void add(E item){
        if(root == null){
            root = new Node(item);
        } else{
            Node node = root;
            while(node.next != null){
                node = node.next;
            }
            node.next = new Node(item);
        }
    }
    public E get(){
        Node node = root;
        root = root.next;
        return node.item;
    }
    public boolean isEmpty(){
        return root == null;
    }

    class Node{
        Node next;
        E item;
        Node(E i){
            item = i;
        }
    }
}
