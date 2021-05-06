public class Main {
    //this is just where we get our sets from and create our dynamic solver
    public static void main(String[] args) {
        int[][] set = new int[2][13];
        int[] set1 = {0, 1, 3, 5, 9, 10, 15, 17, 18, 19, 22, 25, 27};
        int[] set2 = {0, 2, 5, 8, 9, 10, 15, 19, 23, 24, 29, 30, 32};
        set[0] = set1;
        set[1] = set2;
        for(int[] s: set){
            System.out.println("Set " + s[1] + "\n");
            DynamicSolver solver = new DynamicSolver(s);
            solver.setUp();
            solver.insertValues();
            //solver.printMatrix(); if youd like to see the matrix print here
            solver.displayBestOption();
        }

    }
}
