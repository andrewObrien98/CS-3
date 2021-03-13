// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

class UnderflowException extends RuntimeException {
    /**
     * Construct this exception object.
     *
     * @param message the error message.
     */
    public UnderflowException(String message) {
        super(message);
    }
}

public class Tree<E extends Comparable<? super E>> {
    final String ENDLINE = "\n";
    private String tab = "  ";
    private BinaryNode<E> root;  // Root of tree
    private BinaryNode<E> curr;  // Last node accessed in tree
    private String treeName;     // Name of tree

    /**
     * Create an empty tree
     *
     * @param label Name of tree
     */
    public Tree(String label) {
        treeName = label;
        root = null;
    }

    /**
     * Create non ordered tree from list in preorder
     *
     * @param arr    List of elements
     * @param label  Name of tree
     * @param height Maximum height of tree
     */
    public Tree(ArrayList<E> arr, String label, int height) {
        this.treeName = label;
        root = buildUnordered(arr, height, null);
    }

    /**
     * Create BST
     *
     * @param arr   List of elements to be added
     * @param label Name of tree
     */
    public Tree(ArrayList<E> arr, String label) {
        root = null;
        treeName = label;
        for (int i = 0; i < arr.size(); i++) {
            bstInsert(arr.get(i));
        }
    }

    public Tree(E arr, String label) {
        root = null;
        treeName = label;
        bstInsert(arr);

    }


    /**
     * Create BST from Array
     *
     * @param arr   List of elements to be added
     * @param label Name of  tree
     */
    public Tree(E[] arr, String label) {
        root = null;
        treeName = label;
        for (int i = 0; i < arr.length; i++) {
            bstInsert(arr[i]);
        }
    }

    // Test program
    public static void main(String[] args) {
        long seed = 436543;
        Random generator = new Random(seed);  // Don't use a seed if you want the numbers to be different each time
        final String ENDLINE = "\n";

        Integer[] list1 = {25, 10, 60, 55, 58, 56, 14, 63, 8, 50, 6, 9, 15, 27, 29, 61};
        Tree<Integer> treeOne = new Tree<Integer>(list1, "TreeOne:");
        System.out.println(treeOne.toString());
        System.out.println(treeOne.toString2());

        Integer[] v2 = {200, 15, 3, 65, 83, 70, 90};
        ArrayList<Integer> v2L = new ArrayList<Integer>(Arrays.asList(v2));
        Tree<Integer> treeTwo = new Tree<Integer>(v2L, "TreeTwo:", 2);
        System.out.println(treeTwo.toString());
        treeTwo.flip();
        System.out.println("Now flipped" + treeTwo.toString());


        final int SIZE = 8;
        ArrayList<Integer> randomList = new ArrayList<Integer>();
        for (int i = 0; i < SIZE * 2; i++) {
            int t = generator.nextInt(200);
            randomList.add(t);
        }
        int val = 60;
        randomList.add(val);
        Tree<Integer> treeThree = new Tree<Integer>(randomList, "TreeThree:");

        System.out.println(treeThree.toString());
        treeThree.contains(val);  //Sets the current node inside the class.
        int succCount = 7;  // how many successors do you want to see?
        System.out.println("In TreeThree, starting at " + val + ENDLINE);
        for (int i = 0; i < succCount; i++) {
            System.out.println("The next successor is " + treeThree.successor());
        }

        System.out.println(treeThree.toString());
        for (int mylevel = 0; mylevel < SIZE; mylevel += 2) {
            System.out.println("Number nodes at level " + mylevel + " is " + treeThree.nodesInLevel(mylevel));
        }

        System.out.println(treeThree.toString());
        System.out.println("All paths from treeThree");
        treeThree.printAllPaths();


        treeTwo.flip();
        System.out.println(treeTwo.toString());
        System.out.println("treeTwo Contains BST: " + treeTwo.countBST());

        Integer[] v = {21, 8, 5, 6, 7, 19, 10, 40, 43, 52, 12, 60};
        ArrayList<Integer> vL = new ArrayList<Integer>(Arrays.asList(v));
        Tree<Integer> treeTen = new Tree<Integer>(vL, "TreeTen", 3);
        System.out.println(treeTen.toString());
        System.out.println("treeTen Contains BST: " + treeTen.countBST());

        treeTen.pruneK(60);
        treeTen.changeName("treeTen after pruning 60");
        System.out.println(treeTen.toString());
        System.out.println(treeTwo.toString());
        treeTwo.pruneK(290);
        treeTwo.changeName("treeTwo after pruning 290");
        System.out.println(treeTwo.toString());


        System.out.println(treeOne.toString());
        System.out.println("treeOne Least Common Ancestor of (56,61) " + treeOne.lca(56, 61) + ENDLINE);

        System.out.println("treeOne Least Common Ancestor of (6,25) " + treeOne.lca(6, 25) + ENDLINE);
        System.out.println("treeOne Least Common Ancestor of (27,59) " + treeOne.lca(27, 59) + ENDLINE);

        Integer[] v8 = {200, 15, 3, 65, 83, 70, 90, 69, 66, 68};
        Tree<Integer> treeEight = new Tree<Integer>(v8, "TreeEight:");
        System.out.println(treeEight.toString());
        treeEight.balanceTree();
        treeEight.changeName("treeEight after balancing");
        System.out.println(treeEight.toString());

        System.out.println(treeOne.toString());
        treeOne.keepRange(14, 50);
        treeOne.changeName("treeOne after keeping only nodes between 14 and 50");
        System.out.println(treeOne.toString());

        treeEight.changeName("treeEight");
        System.out.println(treeEight.toString());
        treeEight.keepRange(3, 69);
        treeEight.changeName("treeEight after keeping only nodes between 3  and 69");
        System.out.println(treeEight.toString());

        //Bonus
        System.out.print("Tree1 byLevelZigZag (5): ");
        treeOne.byLevelZigZag(5);
        System.out.print("TreeThree byLevelZigZag (3): ");
        treeThree.byLevelZigZag(3);

        Integer[] inorder = {4, 2, 1, 7, 5, 8, 3, 6};
        Integer[] preorder = {1, 2, 4, 3, 5, 7, 8, 6};
        Tree<Integer> treeBonus = new Tree<Integer>("TreeBonus");
        treeBonus.buildTreeTraversals(inorder, preorder);
        treeBonus.changeName("TreeBonus built from inorder and preorder traversals");
        System.out.println(treeBonus.toString());

    }

    /**
     * Change name of tree
     *
     * @param name new name of tree
     */
    public void changeName(String name) {
        this.treeName = name;
    }

    /**
     * Return a string displaying the tree contents as a tree with one node per line
     */
    public String toString() {
        if (root == null)
            return (treeName + " Empty tree\n");
        else
            return treeName + "\n" + toString(root, "");
    }

    /**
     * Return a string displaying the tree contents as a single line
     */
    public String toString2() {
        if (root == null)
            return treeName + " Empty tree";
        else
            return treeName + " " + toString2(root);
    }

    /**
     * reverse left and right children recursively
     */
    public void flip() {
        flip(root);
    }

    /**
     * Find successor of "curr" node in tree
     *
     * @return String representation of the successor
     */
    public String successor() {
        if (curr == null) curr = root;
        curr = successor(curr);
        if (curr == null) return "null";
        else return curr.toString();
    }

    /**
     * Counts number of nodes in specifed level
     *
     * @param level Level in tree, root is zero
     * @return count of number of nodes at specified level
     */
    public int nodesInLevel(int level) {
        return nodesInLevel(root, 0, level);
    }

    /**
     * Print all paths from root to leaves
     */
    public void printAllPaths() {
        System.out.println(printAllPaths(root, ""));
    }

    /**
     * Print contents of levels in zig zag order starting at maxLevel
     *
     * @param maxLevel
     */
    public void byLevelZigZag(int maxLevel) {
        System.out.println(" ");
    }

    /**
     * Counts all non-null binary search trees embedded in tree
     *
     * @return Count of embedded binary search trees
     */
    public Integer countBST() {
        if (root == null) return 0;
        return countBST(root);
    }

    //O(n^log)
    //find the amount of BST within a BS
    //Will go through each BST and will check all of its right and left childs and make sure they adhere to the rules. if it all checks out it adds a plus one. in the end it returns the final number
    private Integer countBST(BinaryNode<E> node) {
        if (node == null) {
            return 0;
        } else if (node.right == null && node.left == null) {
            return 1;
        } else if (node.left == null && node.right.element.compareTo(node.element) > 0) {
            if (checkRight(node, node.right)) {
                return 1 + countBST(node.right);
            }
        } else if (node.right == null && node.left.element.compareTo(node.element) < 0) {
            if (checkLeft(node, node.left)) {
                return 1 + countBST(node.left);
            }
        } else if (node.right != null && node.left != null && node.right.element.compareTo(node.element) > 0 && node.left.element.compareTo(node.element) < 0) {
            if (checkLeft(node, node.left) && checkRight(node, node.right)) {
                return 1 + countBST(node.right) + countBST(node.left);
            }
        }
        return countBST(node.right) + countBST(node.left);
    }

    //O(n^log)
    private boolean checkRight(BinaryNode<E> node, BinaryNode<E> rightNode) {
        if (rightNode == null) {
            return true;
        }
        if (rightNode.element.compareTo(node.element) > 0) {
            return checkRight(node, rightNode.right) && checkRight(node, rightNode.left);
        }
        return false;
    }

    //O(n^log)
    private boolean checkLeft(BinaryNode<E> node, BinaryNode<E> leftNode) {
        if (leftNode == null) {
            return true;
        }
        if (leftNode.element.compareTo(node.element) < 0) {
            return checkLeft(node, leftNode.left) && checkLeft(node, leftNode.right);
        }
        return false;
    }

    /**
     * Insert into a bst tree; duplicates are allowed
     *
     * @param x the item to insert.
     */
    public void bstInsert(E x) {
        root = bstInsert(x, root, null);
    }

    /**
     * Determines if item is in tree
     *
     * @param item the item to search for.
     * @return true if found.
     */
    public boolean contains(E item) {
        return bstContains(item, root);
    }

    /**
     * Remove all paths from tree that sum to less than given value
     *
     * @param sum: minimum path sum allowed in final tree
     */
    public void pruneK(Integer sum) {
        pruneK(root, sum);
    }

    //O(n^log)
    //it will go through each child. as it goes it subtracts the parents number from the sum. if it finds that you are less than the total sum at the end of the line it cuts you
    private void pruneK(BinaryNode<E> node, int max) {
        if (node.right != null && node.left != null) {
            pruneK(node.right, max - (Integer) node.element);
            pruneK(node.left, max - (Integer) node.element);
        } else if (node.left != null) {
            pruneK(node.left, max - (Integer) node.element);
        } else if (node.right != null) {
            pruneK(node.right, max - (Integer) node.element);
        }
        if (node.right == null && node.left == null) {
            int number = (Integer) node.element;
            if (number < max) {
                BinaryNode<E> parent = node.parent;
                if (parent.right != null && node.element.compareTo(parent.right.element) == 0) {
                    parent.right = null;
                } else if (parent.left != null && node.element.compareTo(parent.left.element) == 0) {
                    parent.left = null;
                }
            }
        }
    }

    /**
     * Build tree given inOrder and preOrder traversals.  Each value is unique
     *
     * @param inOrder  List of tree nodes in inorder
     * @param preOrder List of tree nodes in preorder
     */
    public void buildTreeTraversals(E[] inOrder, E[] preOrder) {
        root = null;
    }

    /**
     * Find the least common ancestor of two nodes
     *
     * @param a first node
     * @param b second node
     * @return String representation of ancestor
     */
    public String lca(E a, E b) {
        BinaryNode<E> ancestor;
        ancestor = lca(root, (Integer) a, (Integer) b);
        return ancestor.toString();
    }

    //O(log(n))
    //this just goes through from the root and then follows the path that the integer take until they split off in different directions and then it returns that number
    private BinaryNode<E> lca(BinaryNode<E> node, int a, int b) {
        int nodeValue = (Integer) node.element;
        if (nodeValue < a && nodeValue < b) {
            return lca(node.right, a, b);
        } else if (nodeValue > a && nodeValue > b) {
            return lca(node.left, a, b);
        }
        return node;
    }

    /**
     * Balance the tree
     */
    //I build a balanced tree. I first go through the first tree and and get all of its elements in order and then pass it to the build tree functions
    public void balanceTree() {
        ArrayList<BinaryNode<E>> nodeContainer = new ArrayList<>();
        findElement(nodeContainer, root);
        root = buildTree(nodeContainer, 0, nodeContainer.size() - 1);
    }


    //O(n^log)
    //I split the list in half on each recursion and always add the middle part to the tree
    private BinaryNode<E> buildTree(ArrayList<BinaryNode<E>> list, int beg, int end) {
        if (beg == end) {
            list.get(beg).right = null;
            list.get(beg).left = null;
            return list.get(beg);
        }
        if (beg > end) {
            return null;
        }
        int mid = (beg + end) / 2;
        BinaryNode<E> t = list.get(mid);
        if (beg == 0 && end == list.size() - 1) {
            t.parent = null;
        }
        t.left = buildTree(list, beg, mid - 1);
        t.right = buildTree(list, mid + 1, end);
        return t;
    }

    //O(n^log*b^a)
    //finds each node in a tree and adds it to a list in numerical order
    private void findElement(ArrayList<BinaryNode<E>> list, BinaryNode<E> node) {
        if (node != null) {
            findElement(list, node.left);
            list.add(node);
            findElement(list, node.right);
        }
    }

    /**
     * In a BST, keep only nodes between range
     *
     * @param a lowest value
     * @param b highest value
     */
    public void keepRange(E a, E b) {
        root = findRange(root, a, b);
    }
    //O(n^log*b^a)
    //I first find the node that is the last node that corresponds to the max of the valid range and then I go through that one and find the min from it.
    private BinaryNode<E> findRange(BinaryNode<E> node, E min, E max) {
        if (node == null) {
            return null;
        }
        if (node.element.compareTo(max) <= 0) {
            node.right = findRange(node.right, min, max);
        } else {
            return findRange(node.left, max, min);
        }
        node = findMin(node, min);
        return node;
    }
    //O(log(n))
    //find the minimum node within its range
    private BinaryNode<E> findMin(BinaryNode<E> node, E min) {
        if (node == null) {
            return null;
        }
        if (node.element.compareTo(min) >= 0) {
            node.left = findMin(node.left, min);
        } else {
            return findMin(node.right, min);
        }
        return node;
    }

    /**
     * Build a NON BST tree by preorder
     *
     * @param arr    nodes to be added
     * @param height maximum height of tree
     * @param parent parent of subtree to be created
     * @return new tree
     */
    //O(n^log)
    private BinaryNode<E> buildUnordered(ArrayList<E> arr, int height, BinaryNode<E> parent) {
        if (arr.isEmpty()) return null;
        BinaryNode<E> curr = new BinaryNode<>(arr.remove(0), null, null, parent);
        if (height > 0) {
            curr.left = buildUnordered(arr, height - 1, curr);
            curr.right = buildUnordered(arr, height - 1, curr);
        }
        return curr;
    }

    /**
     * Internal method to insert into a subtree.
     * In tree is balanced, this routine runs in O(log n)
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    //O(log(n))
    private BinaryNode<E> bstInsert(E x, BinaryNode<E> t, BinaryNode<E> parent) {
        if (t == null)
            return new BinaryNode<>(x, null, null, parent);
        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = bstInsert(x, t.left, t);
        } else {
            t.right = bstInsert(x, t.right, t);
        }

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree: a=1, b=2, k=0
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     *          SIDE EFFECT: Sets local variable curr to be the node that is found
     * @return node containing the matched item.
     */
    //O(log(n))
    private boolean bstContains(E x, BinaryNode<E> t) {
        curr = null;
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return bstContains(x, t.left);
        else if (compareResult > 0)
            return bstContains(x, t.right);
        else {
            curr = t;
            return true;    // Match
        }
    }

    /**
     * Internal method to return a string of items in the tree in order
     * This routine runs in O(??)
     *
     * @param t the node that roots the subtree.
     */
    private String toString2(BinaryNode<E> t) {
        if (t == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(toString2(t.left));
        sb.append(t.element.toString() + " ");
        sb.append(toString2(t.right));
        return sb.toString();
    }

    //O(n^log)
    //goes through and builds a string that represents a tree. with its depth it add a space to the depth. it returns a string to be printed
    private String toString(BinaryNode<E> node, String depth) {
        if (node == null) {
            return "";
        }
        depth += "  ";
        if (node.parent == null) {
            return toString(node.right, depth) + depth + node.element + "[no Parent]\n" + toString(node.left, depth);
        }
        return toString(node.right, depth) + depth + node.element + "[" + node.parent.element + "]\n" + toString(node.left, depth);

    }
    //O(n^log)
    //goes through and swaps left and right children recursively
    private void flip(BinaryNode<E> node) {
        if (node == null) {
            return;
        }
        BinaryNode<E> right = node.right;
        node.right = node.left;
        node.left = right;
        flip(node.right);
        flip(node.left);
    }

    //O(log(n))
    //goes through and finds each successor
    private BinaryNode<E> successor(BinaryNode<E> node) {
        if (node.right != null) {
            return successorLeft(node.right);
        } else if (node.element.compareTo(root.element) >= 0) {
            return null;
        }
        return successorParent(node.parent);
    }
    //O(log(n))
    private BinaryNode<E> successorLeft(BinaryNode<E> node) {
        if (node.left == null) {
            return node;
        }
        return successorLeft(node.left);
    }
    //O(log(n))
    private BinaryNode<E> successorParent(BinaryNode<E> node) {
        if (node.parent.left == null) {
            return successorParent(node.parent);
        } else if (node.element.compareTo(node.parent.left.element) == 0) {
            return node.parent;
        }
        return successorParent(node.parent);
    }
    //O(n^log)
    //this one goes through and finds the number of nodes at a certain level
    private int nodesInLevel(BinaryNode<E> node, int currLevel, int level) {
        if (node == null) {
            return 0;
        } else if (currLevel == level) {
            return 1;
        }
        return nodesInLevel(node.left, currLevel + 1, level) + nodesInLevel(node.right, currLevel + 1, level);
    }
    //O(n^log)
    //this one goes through and prints all the possible paths from the root
    private String printAllPaths(BinaryNode<E> node, String word) {
        if (node == null) return "";
        if (node.right == null && node.left == null) {
            return word + node.element + "\n";
        }
        word += node.element + " ";
        return printAllPaths(node.right, word) + printAllPaths(node.left, word);

    }


    // Basic node stored in unbalanced binary  trees
    private static class BinaryNode<AnyType> {
        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        BinaryNode<AnyType> parent; //  Parent node

        // Constructors
        BinaryNode(AnyType theElement) {
            this(theElement, null, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt, BinaryNode<AnyType> pt) {
            element = theElement;
            left = lt;
            right = rt;
            parent = pt;
        }

        // toString for BinaryNode
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Node:");
            sb.append(element);
            if (parent == null) {
                sb.append("<>");
            } else {
                sb.append("<");
                sb.append(parent.element);
                sb.append(">");
            }

            return sb.toString();
        }


    }

}