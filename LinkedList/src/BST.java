public class BST<E extends Comparable<E>> {
    Node root;

    public void insert(E element){
        root = insert(root, element);
    }

    public Node insert(Node node, E element) {
        if (node == null) return new Node(element);
        if(element.compareTo(node.element) < 0){
            node.left = insert(node.left, element);
        } else {
            node.right = insert(node.right, element);
        }
        return node;
    }

    public void delete(E element){
        root = delete(root, element);
    }

    public Node delete(Node node, E element){
        if(node == null) return node;
        if(element.compareTo(node.element) > 0){
            node.right = delete(node.right, element);
        } else if(element.compareTo(node.element) < 0){
            node.left = delete(node.left, element);
        } else {//this means that we found the node to delete
            if(node.right == null){return node.left;}
            else if(node.left == null) {return node.right;}
            else {
                if(node.right.left == null){
                    node.right.left = node.left;
                    return node.right;
                }
                node.element = findSmallestRightChild(node.right, node.right.left);
            }
        }
        return node;
    }

    public E findSmallestRightChild(Node parent, Node child){
        if(child.left == null){
            parent.left = child.right;
            return child.element;
        }
        return findSmallestRightChild(child, child.left);
    }


    public void displayTree(){
        displayTree(root, " ");
    }
    public void displayTree(Node node, String indent){
        if(node == null) return;
        displayTree(node.right, indent + "  ");
        System.out.println(indent + node.element);
        displayTree(node.left, indent + "  ");
    }

    class Node {
        E element;
        Node left, right;
        Node(E element){
            this.element = element;
        }
    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);
        tree.insert(2);
        tree.delete(3);
        tree.displayTree();
    }

}
