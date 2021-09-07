public class Practice<E extends Comparable<E>> {
    Node root;

    public void insert(E element){
        root = insert(element, root);
    }

    public Node insert(E element, Node node){
        if(node == null) return new Node(element);
        if(element.compareTo(node.element) > 0){
            node.right = insert(element, node.right);
            return node;
        }
        node.left = insert(element, node.left);
        return node;
    }


    public void display(){
        display(root, "");
    }
    public void display(Node node, String indentation){
        if(node == null) return ;
        display(node.right, indentation + " ");
        System.out.println(indentation + node.element);
        display(node.left, indentation + " ");
    }

    public class Node {
        E element;
        Node right;
        Node left;

        public Node(E element){
            this.element = element;
        }
    }

    public static void main(String[] args) {
        Practice<Integer> BST = new Practice<>();
        BST.insert(10);
        BST.insert(5);
        BST.insert(15);
        BST.insert(7);
        BST.insert(12);
        BST.insert(3);
        BST.insert(17);
        BST.display();
    }

}
