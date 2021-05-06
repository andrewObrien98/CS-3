public class LeftistHeap<E extends Comparable<E>> {
    Node root;
    LeftistHeap(){}

    /**
     * calls its child insert that will return the new root
     * @param element this is the task from test Schedule but in reality can be any element
     */
    public void insert(E element){
        Node node = new Node(element);
        root = insert(node, root);
    }

    /**
     *
     * @param node is the new node that we are looking to place
     * @param alreadyNode starts at root but then flows to the tree until its either null or less than node
     * one should note that as it comes back up the tree after placing the node it will rebalance it to a leftist heap.
     * @return the root
     */
    public Node insert(Node node, Node alreadyNode){
        if(alreadyNode == null){
            node.npl = 0;
            return node;
        }
        if(node.element.compareTo(alreadyNode.element) < 0){
            node.left = alreadyNode;
            return node;
        }
        alreadyNode.right = insert(node, alreadyNode.right);
        alreadyNode = checkNPL(alreadyNode);
        return alreadyNode;

    }

    /**
     *
     * @param node from this node we will look at its children to make sure that they are following the leftist min heap properties
     * @return the node that now fulfills the leftist heap properties
     */
    public Node checkNPL(Node node){
        node.npl = findNPL(node);
        if(node.left != null){node.left.npl = findNPL(node.left);}
        if(node.right != null){node.right.npl = findNPL(node.right);}
        if(node.left == null){
            node.left = node.right;
            node.right = null;
        } else if(node.right != null && node.left.npl < node.right.npl){
            Node temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        return node;
    }

    /**
     *
     * @param node is the node that we want to find its npl
     * @return an int that is the nodes npl
     */
    public int findNPL(Node node){
        if(node.left == null || node.right == null) return 0;
        int leftSide = 1 + findNPL(node.left);
        int rightSide = 1 + findNPL(node.right);
        return Math.min(leftSide, rightSide);
    }

    /**
     * calls its child deleteMin
     * will change its root after deleting it
     * @return the smallest item in the tree which is the root
     */
    public E deleteMin(){
        E minimum = root.element;
        root = deleteMin(root.left, root.right);
        return minimum;
    }

    /**
     * this will go through and reconstruct a new leftist min heap after getting rid of its root and will
     * return the new root
     * @param left starts out as the left side of the root
     * @param right starts out as the right side of the root
     * @return the root
     */
    public Node deleteMin(Node left, Node right){
        if(right == null)return left;
        if(left == null)return right;
        if(left.element.compareTo(right.element) < 0){
            left.right = deleteMin(left.right, right);
            checkNPL(left);
            return left;
        }
        right.right = deleteMin(left, right.right);
        checkNPL(right);
        return right;

    }

    /**
     * calls its recursive helper printTree
     */
    public void printTree(){
        printTree(root, "");
    }

    /**
     *
     * @param node start out as the root
     * @param indentation keeps track of how far down the tree you are
     */
    public void printTree(Node node, String indentation){
        if(node == null)return;
        printTree(node.right, indentation + " ");
        System.out.println(indentation + node.element + " (npl : " + node.npl + ")" + " node.right(" + node.right + ")" + " node.left(" + node.left + ")");
        printTree(node.left, indentation + " ");
    }

    /**
     *
     * @return true if root is null and false otherwise
     */
    public boolean isEmpty(){
        return root == null;
    }

    /**
     * This is just a class for the node that stores its element, left and right node, and its npl
     */
    public class Node {
        Node left;
        Node right;
        E element;
        int npl;
        Node(){}
        Node(E e){
            element = e;
        }
    }

}
