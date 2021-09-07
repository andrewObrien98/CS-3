import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Linkedlist<String> list = new Linkedlist<>();
        list.insert("hello");
        list.insert("word");
        list.insert("McCall");
        list.printList();
        System.out.println("\n\n\n\n");
        list.insert("hello");
        list.insert("word");
        list.insert("McCall");
        list.printList();
    }
}
