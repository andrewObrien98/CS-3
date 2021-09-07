public class AVLTree3<E extends Comparable<E>> {
    Node root;

    public void insert(E element){
        root = insert(root, element);
    }
    public Node insert(Node node, E element){
        if(node == null){
            return new Node(element);
        } else if(element.compareTo(node.element) < 0){
            node.left = insert(node.left, element);
        } else if(element.compareTo(node.element) > 0){
            node.right = insert(node.right, element);
        } else {
            return node;
        }
        return checkBalance(node);


    }
    public Node checkBalance(Node node){
        node.height = max(height(node.left), height(node.right)) + 1;
        int balance = getBalance(node);

        //left left
        if(balance < -1 && height(node.right) > height(node.left)){
            return leftRotation(node);
        }//right left
        if(balance < -1 && height(node.right) > height(node.left)){
            node.right = rightRotation(node.right);
            return leftRotation(node);
        } // right right
        if(balance > 1 && height(node.left) > height(node.right)){
            return rightRotation(node);
        }// left right
        if(balance > 1 && height(node.left) < height(node.right)){
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }
        return node;
    }

    public Node leftRotation(Node node){
        Node root = node.right;
        Node rootsLeft = node.left;
        root.left = node;
        node.right = rootsLeft;

        node.height = max(height(node.right), height(node.left)) + 1;
        root.height = max(height(root.right), height(root.left)) + 1;

        return root;
    }

    public Node rightRotation(Node node){
        Node root = node.left;
        Node rootsRight = root.right;
        root.right = node;
        node.left = rootsRight;

        node.height = max(height(node.right), height(node.left)) + 1;
        root.height = max(height(root.right), height(root.left)) + 1;

        return root;
    }

    public int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    public int height(Node node){
        if(node == null){
            return -1;//maybe change this
        }
        return node.height;
    }

    public int max(int left, int right){
        if(left > right){
            return left;
        }
        return right;
    }

    public void displayTree(){
        displayTree(root, "");
    }

    public void displayTree(Node node, String indent){
        if(node == null) return;
        displayTree(node.right, indent + " ");
        System.out.println(indent + node.element + " height: " + node.height);
        displayTree(node.left, indent + " ");
    }

    public void delete(E element){
        root = delete(root, element);
    }

    public Node delete(Node node, E element){
        if(node == null){return null;}
        if(element.compareTo(node.element) < 0){
            node.left = delete(node.left, element);
        } else if(element.compareTo(node.element) > 0){
            node.right = delete(node.right, element);
        } else {// i found it
            if(node.right == null){
                return node.left;
            } else if(node.left == null){
                return node.right;
            }
            
        }
        return checkBalance(node);
    }

    class Node{
        Node right, left;
        E element;
        int height;

        Node(E element){
            this.element = element;
            this.height = 0;
        }
    }

    public static void main(String[] args) {
        AVLTree3<Integer> tree = new AVLTree3<>();
        tree.insert(4);
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(5);
        tree.insert(6);
//        tree.insert(7);
        tree.displayTree();
    }
}
