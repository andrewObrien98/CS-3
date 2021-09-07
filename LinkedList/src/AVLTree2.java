public class AVLTree2<E extends Comparable<E>> {
    Node root;


    public void delete(E element){
        root = delete(element, root);
    }
    public Node delete(E element, Node node){
        if(node == null) return null;
        else if(element.compareTo(node.element) < 0){
            node.left = delete(element, node.left);
        } else if(element.compareTo(node.element) > 0){
            node.right = delete(element, node.right);
        } else {
            if(node.right == null){return node.left;}
            else if(node.left == null){return node.right;}
            else{
                Node largestLeft = biggestLeftNode(node);
                largestLeft.right = node.right;
                return largestLeft;
            }
        }
        return check(node);
    }
    public Node biggestLeftNode(Node node){
        if(node.left.right == null){
            return node.left;
        }
        Node root = biggestLeftNode(node.left, node.left.right);
        root.left = node.left;
        return root;
    }

    public Node biggestLeftNode(Node parent, Node child){
        if(child.right == null){
            parent.right = child.left;
            return child;
        }
        Node node =  biggestLeftNode(parent.right, child.right);
        check(parent);
        return node;
    }

    public void add(E element){
        root = add(element, root);
    }

    public Node add(E element, Node node){
        if(node == null){return new Node(element);}
        else if (element.compareTo(node.element) < 0){
            node.left = add(element, node.left);
        } else if (element.compareTo(node.element) > 0){
            node.right = add(element, node.right);
        } else {
            return null;
        }
        return check(node);
    }

    public Node check(Node node){
        node.height = max(height(node.left), height(node.right)) + 1;//get the correct height for the node
        int balance = getBalance(node);

        //here is where the rotations come in
        //left left
        if(balance < -1 && height(node.right) > height(node.left)){
            return leftRotation(node);
        } //right left
        if(balance < -1 && height(node.right) < height(node.left)){
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }// right right
        if(balance > 1 && height(node.left) > height(node.right)){
            return rightRotation(node);
        }// left right
        if(balance > 1 && height(node.left) < height(node.right)){
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }
        return node;
    }
    public int max(int left, int right){
        if(left > right) return left;
        return right;
    }
    public int height(Node node){
        if(node == null) return -1;

        return node.height;
    }

    public int getBalance(Node node){
        if(node == null) return 0;
        return height(node.left) - height(node.right);
    }

    public Node rightRotation(Node node){
        Node root = node.left;
        Node rootsRight = root.right;

        root.right = node;
        node.left = rootsRight;

        node.height = max(height(node.left), height(node.right)) + 1;
        root.height = max(height(root.left), height(root.right)) + 1;
        return root;
    }

    public Node leftRotation(Node node){
        Node root = node.right;
        Node rootsLeft = root.left;

        root.left = node;
        node.right = rootsLeft;

        node.height = max(height(node.left), height(node.right)) + 1;
        root.height = max(height(root.left), height(root.right)) + 1;
        return root;
    }

    public void printTree(){
        printTree(root, " ");
    }
    public void printTree(Node node, String indentation){
        if(node == null) return;
        printTree(node.right, indentation + " ");
        System.out.println(indentation + node.element + " height: " + node.height);
        printTree(node.left, indentation + " ");
    }


    class Node {
        Node left, right;
        E element;
        int height;

        Node(E element){
            this.element = element;
            height = 0;
        }
    }

    public static void main(String[] args) {
        AVLTree2<Integer> tree = new AVLTree2<>();
        tree.add(5);
        tree.add(9);
        tree.add(4);
        tree.add(10);
        tree.add(7);
        tree.add(11);
        tree.add(12);
        tree.delete(4);
        tree.printTree();
        System.out.println("\n\n\n");
        tree.delete(7);
        tree.printTree();
        System.out.println("\n\n\n");
        tree.delete(5);
        tree.printTree();

    }
}
