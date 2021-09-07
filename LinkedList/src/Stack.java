public class Stack<E extends Comparable<?>> {
    node head;

    public boolean insert(E element){
        if(head == null){
            head = new node(element);
            return true;
        }
        node node = new node(element);
        node.next = head;
        head = node;
        return true;
    }

    public void displayStack(){
        node node = head;
        while(node != null){
            System.out.println(node.element);
            node = node.next;
        }
    }

    public class node{
        E element;
        node next;

        node(E element){
            this.element = element;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.insert(1);
        stack.insert(2);
        stack.insert(3);
        stack.insert(4);
        stack.displayStack();
    }
}

