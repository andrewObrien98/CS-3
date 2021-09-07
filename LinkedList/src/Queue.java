public class Queue<E extends Comparable<E>> {
    Node head;
    int size = 0;

    public void insert(E element){
        if(head == null){
            head = new Node(element);
        } else {
            head = insert(head, element);
        }
        size++;
    }

    public Node insert(Node node, E element){
        if(node == null){
            return new Node(element);
        }
        node.next = insert(node.next, element);
        return node;
    }

    public void delete(E element){
        if(head != null){
            head = delete(head, element);
        }
        size--;
    }
    public Node delete(Node node, E element){
        if(node == null){//if not found
            return node;
        }
        if(node.element.compareTo(element) == 0){//if found
            return node.next;
        }
        node.next = delete(node.next, element);
        return node;
    }



    public boolean find(E element){
        Node node = head;
        while(node != null){
            if(node.element.compareTo(element) == 0){
                return true;
            }
            node = node.next;
        }
        return false;
    }


    public void display(){
        Node node = head;
        while(node != null){
            System.out.println(node.element);
            node = node.next;
        }
    }

    public int getSize(){
        return size;
    }

    public class Node{
        E element;
        Node next;
        Node(E element){
            this.element = element;
        }
    }

    public static void main(String[] args) {
        Queue<Integer> stack = new Queue<>();
        stack.insert(1);
        stack.insert(2);
        stack.insert(3);
        stack.insert(4);
        System.out.println(stack.find(5));
        stack.display();
        System.out.printf("The size of the stack is %d\n", stack.getSize());
        stack.delete(2);
        System.out.printf("The size of the stack is %d\n", stack.getSize());
        stack.insert(5);
        System.out.printf("The size of the stack is %d\n", stack.getSize());
        stack.display();
    }
}
