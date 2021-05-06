public class Union {
    int[] list;
    int listSize = 0;
    public Union (int size){
        list = new int[size];
        for(int i = 0; i < size; i++){
            list[i] = -1;
        }
        listSize = size;
    }

    /**
     * this will go through and find which one has the greater size(size if measured in negative so technically which one has the lesser size)
     * Whichever one has the greater size will become the new root and you will set the other root equal to that root
     * @param root1 contains the root that was found in find
     * @param root contains a root that was found in find
     */
    public void union(int root1, int root){
        if(root1 == root){return;}
        int sizeRoot1 = getSize(root1);
        int sizeRoot = getSize(root);
        if(sizeRoot1 < sizeRoot) {
            list[root1] += list[root];
            list[root] = root1;
        } else{
            list[root] += list[root1];
            list[root1] = root;
        }
    }

    /**
     *
     * @param item this is the item where we are looking for its parent
     * @return itself if it has no parent and if it does then it returns its parent
     */
    public int find( int item){
        if (list[item] < 0){
            return item;
        }
        return list[item] = find(list[item]);
    }

    /**
     *
     * @param root
     * @return the root of every root
     */
    public int getSize(int root){
        return list[root];
    }

    public void printUnion(){
        System.out.println("UNION:");
        for(int root: list){
            System.out.printf("%4d", root);
        }
        System.out.println();
        for(int i = 0; i < listSize; i++){
            System.out.printf("%4d", i);
        }
        System.out.println("\n\n\n");
    }
}
