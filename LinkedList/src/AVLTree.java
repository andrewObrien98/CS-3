public class AVLTree<E extends Comparable<E>> {
    Node root;

    public void insert(E element){
        root = insert(element, root);
    }

    public Node insert(E element, Node node){
        if(node == null){
            return new Node(element);
        } else if(element.compareTo(node.element) < 0){
            node.left = insert(element, node.left);
        } else if(element.compareTo(node.element) > 0){
            node.right = insert(element, node.right);
        } else {return null;}

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        //now the 4 cases for unbalance
        //right right
        if(balance > 1 && element.compareTo(node.left.element) < 0){
            return rightRotation(node);
        }
        //left left
        if(balance < -1 && element.compareTo(node.right.element) > 0){
            return leftRotation(node);
        }
        //left right
        if(balance > 1 && element.compareTo(node.left.element) > 0){
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }
        //right left
        if(balance < -1 && element.compareTo(node.right.element) < 0){
            node.right = rightRotation(node.right);
            return leftRotation(node.left);
        }
        return node;
    }
    public int height(Node node){
        if(node == null){
            return -1;
        }
        return node.height;
    }

    public int max(int left, int right){
        if(left > right){
            return left;
        }
        return right;
    }

    public int getBalance(Node node){
        if(node == null){
            return -1;
        }
        return height(node.left) - height(node.right);
    }

    public Node rightRotation(Node node){
        Node left = node.left.right;
        Node root = node.left;
        root.right = node;
        node.left = left;

        node.height = max(height(node.left), height(node.right)) + 1;
        root.height = max(height(root.left), height(root.right)) + 1;
        return root;


    }
    public Node leftRotation(Node node){
        Node right = node.right.left;
        Node root = node.right;
        root.left = node;
        node.right = right;

        node.height = max(height(node.left), height(node.right)) + 1;
        root.height = max(height(root.left), height(root.right)) + 1;
        return root;
    }

    public void printTree(){
        printTree(root, " ");
    }
    public void printTree(Node node, String indentation){
        if(node == null ){ return;}
        printTree(node.right, indentation + "  ");
        System.out.println(indentation + node.element + " height: " + node.height);
        printTree(node.left, indentation + "  ");
    }

    public class Node {
        Node left;
        Node right;
        E element;
        int height;
        Node(E element){
            this.element = element;
            height = 0;
        }
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(50);
        tree.insert(47);
        tree.insert(45);
        tree.printTree();
    }
}
