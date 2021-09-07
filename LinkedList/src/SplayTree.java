public class SplayTree<E extends Comparable<E>> {
    Node root;

    public void insert(E element){
        root = insert(element, root);
    }

    public Node insert(E element, Node node){
        if(node == null){
            return new Node(null, element);
        } else if(element.compareTo(node.element) > 0){
            return splay(insert(element, node, node.right));
        }
        return splay(insert(element, node, node.left));
    }

    public Node insert(E element, Node parent, Node child){
        while(child != null){
            parent = child;
            if(element.compareTo(child.element) > 0){
                child = child.right;
            } else {
                child = child.left;
            }
        }
        return new Node(parent, element);
    }

    public Node splay(Node node){
        while(node.parent != null){
            //zig, single right rotation
            if(node.parent.parent == null && node.element.compareTo(node.parent.element) < 0){
                node = rightRotation(node);
            } //zag, single left rotation
            else if(node.parent.parent == null && node.element.compareTo(node.parent.element) > 0){
                node = leftRotation(node);
            } //zig zig double right rotation
            else if(node.parent.element.compareTo(node.parent.parent.element) < 0 && node.element.compareTo(node.parent.element) < 0){
                node.parent = rightRotation(node.parent);
                node = rightRotation(node);
            }// zag zag double left rotation
            else if(node.parent.element.compareTo(node.parent.parent.element) > 0 && node.element.compareTo(node.parent.element) > 0){
                node.parent = leftRotation(node.parent);
                node = leftRotation(node);
            } //Zig Zag right rotation followed by left rotation
            else if(node.parent.element.compareTo(node.parent.parent.element) > 0 && node.element.compareTo(node.parent.element) < 0){
                node = rightRotation(node);
                node = leftRotation(node);
            } //Zag Zig rotation. left rotation followed by a right rotation
            else {
                node = leftRotation(node);
                node = rightRotation(node);
            }
        }
        return node;
    }

    public Node rightRotation(Node node){
        Node parent = node.parent;
        Node parentParent = node.parent.parent;

        parent.left = node.right;
        node.right = parent;
        parent.parent = node;
        node.parent = parentParent;
        
        return node;
    }

    public Node leftRotation(Node node){
        Node parent = node.parent;
        Node parentParent = node.parent.parent;

        parent.right = node.left;
        node.left = parent;
        parent.parent = node;
        node.parent = parentParent;

        return node;
    }

    public void delete(E element){
        root = delete(root, element);
        display();
        System.out.println("\n\n\n");
        Node rightTree = root.right;
        Node leftTree = root.left;
        root = findMax(leftTree);
        root.right = rightTree;
    }

    public Node findMax(Node node){
        while(node.right != null){
            node = node.right;
        }
        return splay(node);
    }

    public Node delete(Node node, E element){
        while(node.element.compareTo(element) != 0){
            if(element.compareTo(node.element) > 0){
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return splay(node);
    }

    public void display(){
        display(root, "");
    }
    public void display(Node node, String indent){
        if(node == null) return;
        display(node.right, indent + "  ");
        System.out.println(indent + node.element);
        display(node.left, indent + "  ");
    }

    class Node {
        Node left;
        Node right;
        Node parent;
        E element;

        Node(Node parent, E element){
            this.parent = parent;
            this.element = element;
        }
    }

    public static void main(String[] args) {
        SplayTree<Integer> tree = new SplayTree<>();
        tree.insert(5);
        tree.insert(6);
        tree.insert(3);
        tree.insert(4);
        tree.insert(9);
        tree.insert(1);
        tree.insert(15);
        tree.insert(7);
        tree.delete(9);
        tree.display();
    }
}
