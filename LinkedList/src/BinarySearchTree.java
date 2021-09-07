public class BinarySearchTree<E extends Comparable<E>> {
    Node head;

    public void insert(E element){
        head = insert(element, head);
    }

    public Node insert(E element, Node node){
        if(node == null){
            return new Node(element);
        } else if (element.compareTo(node.element) >= 0){
            node.right = insert(element, node.right);
        } else{
            node.left = insert(element, node.left);
        }
        return node;
    }

    public Boolean contains(E element){
        Node node = head;
        while(node != null){
            if(element.compareTo(node.element) == 0){
                return true;
            } else if(element.compareTo(node.element) > 0){
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return false;
    }

    public void deleteNode(E element){
        head = deleteNode(element, head);
    }
    public Node deleteNode(E element, Node node){
        if(node == null){return null;}
        else if(element.compareTo(node.element) < 0){
            node.left = deleteNode(element, node.left);
        } else if (element.compareTo(node.element) > 0){
            node.right = deleteNode(element, node.right);
        } else {
            if(node.right == null){
                return node.left;
            } else if(node.left == null){
                return node.right;
            } else {
                Node root = findBiggestLeftChild(node);
                root.right = node.right;
                return root;
            }
        }
        return node;
    }

    public Node findBiggestLeftChild(Node node){
        if(node.left.right == null){
            return node.left;
        }
        Node root = findBiggestLeftChild(node.left, node.left.right);
        root.left = node.left;
        return root;
    }
    public Node findBiggestLeftChild(Node parent, Node child){
        if(child.right == null){
            parent.right = child.left;
            return child;
        }
        return findBiggestLeftChild(parent.right, child.right);
    }

    public void printTree(){
        printTree(head, "");
    }
    public void printTree(Node node, String indentation){
        if(node == null){return;}
        printTree(node.right, indentation + " ");
        System.out.println(indentation + node.element);
        printTree(node.left, indentation + " ");
    }

    public class Node {
        Node left;
        Node right;
        E element;

        Node(E element){
            this.element = element;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> BST = new BinarySearchTree<>();
        BST.insert(5);
        BST.insert(8);
        BST.insert(3);
        BST.insert(6);
        BST.insert(7);
        BST.insert(9);
        BST.insert(4);
        BST.insert(2);
        BST.printTree();
        System.out.println(BST.contains(3));
        System.out.println(BST.contains(10));
        System.out.println("\n\n\n");
        BST.deleteNode(8);
        BST.deleteNode(10);
        BST.deleteNode(3);
        BST.deleteNode(4);
        BST.deleteNode(5);
        BST.deleteNode(7);
        BST.printTree();
    }
}
