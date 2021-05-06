public class DynamicSolver {
    int row = 13;
    int col = 25;
    int[][] matrix = new int[row][col];
    int[] set;

    DynamicSolver(int[] s){
        set = s;
    }

    /**
     * this will set it up by putting the row and column header persay
     */
    public void setUp(){
        for(int c = 1; c < col; c++){
            matrix[0][c] = c;//this will put values in for the number of teacups we are going for
            matrix[1][c] = c * set[1]; // this will put in the values for each amount of teacups if we just have a set of 1
        }
        for(int r = 1; r < row; r++){
            matrix[r][0] = r;//this just puts values in the rows
        }
    }

    /**
     * this will go through the rest of the matrix and insert values in every single (row, col)
     */
    public void insertValues(){
        for(int r = 2; r < row; r++){
            for(int c = 1; c < col; c++){
                int size = r; // what row we are on corresponds to the number of teacups in a set we are going with
                int amount = c; // this just corresponds to how many teacups we need
                if(amount < size){matrix[r][c] = matrix[r-1][c];}
                else{
                    int withSize;
                    if(c - r == 0){ withSize = set[r];}//this check how big it would be with the size
                    else{withSize = matrix[r][c - r] + set[r];}
                    int withoutSize = matrix[r - 1][c];//this checks how big it was on the row up above. hence without the size
                    matrix[r][c] = Math.max(withSize, withoutSize);
                }
            }
        }
    }

    /**
     * this will just go through and print the best sum and its max and then call the display path
     */
    public void displayBestOption(){
        System.out.println("OutPut:");
        for(int c = 1; c < 25; c++){
            System.out.printf("Best Sum for (%d teacup): $%d [", c, matrix[12][c]);
            displayPath(12, c);
            System.out.print("]\n");
        }
        System.out.print("\n\n");
    }

    /**
     * this will print the path of a max, meaning what teacup sets we used
     * @param row
     * @param col
     */
    public void displayPath(int row, int col){
        while(row != 1 && matrix[row][col] == matrix[row - 1][col]){
            row = row -1;//keeps going up a row to find where the max first started
        }
        System.out.printf(" %d", row);
        if(col - row > 0) {//if it is equal to zero then we have reached the end of our path
            System.out.print(",");
            displayPath(row, col - row);
        }
    }

    /**
     * this was super useful in building the program as I could see if the matrix I built was correct
     * this will just display the matrix
     */
    public void printMatrix(){
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                System.out.printf("%3d ", matrix[r][c]);
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }

}
