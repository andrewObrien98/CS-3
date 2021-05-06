import java.io.FileNotFoundException;
import java.util.Scanner;

public class HexUserVersion {
    Union union;
    String[] chosen;
    int top ;
    int bottom ;
    int left ;
    int right ;
    int playersTurn = 0;
    int col = 0;
    int numberOfHexs = 0;
    int row;

    /**
     * calls createHex to start the game
     * @throws FileNotFoundException
     */
    public HexUserVersion( int row, int col) throws FileNotFoundException {
        createHex(row, col);
    }

    /**
     * takes a number one at a time from the file and switches off from blue to red on deciding which side gets the number
     * will check each time a number is chosen to see if it has any neighbors it connect with
     * it will also check everytime to see if it has won!
     * @throws FileNotFoundException
     */
    public void createHex( int row, int col) throws FileNotFoundException{
        this.col = col;
        this.row = row;
        numberOfHexs = row * col;
        union = new Union(numberOfHexs + 5);
        chosen = new String[numberOfHexs + 1];
        top = numberOfHexs - 4;
        bottom = numberOfHexs - 3;
        left = numberOfHexs - 2;
        right = numberOfHexs - 1;
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Enter you value: ");
            int value = input.nextInt();
            if(chosen[value] == null) {
                if (playersTurn % 2 == 0) {
                    chosen[value] = "Blue";
                    findNeighbors(value);
                    checkForBlueWalls(value);
                    if (union.find(left) == union.find(right)) {
                        printHex("blue");
                        break;
                    }

                } else {
                    chosen[value] = "Red";
                    findNeighbors(value);
                    checkForRedWalls(value);
                    if (union.find(bottom) == union.find(top)) {
                        printHex("red");
                        break;
                    }

                }
                playersTurn++;
                printHex("", 1);
            } else {
                System.out.println("Sorry but that value was already taken! Try again");
            }
        }
        printHex("", 1);

    }

    /**
     * just performs the find functions on all 6 neighbors
     * @param value is an int from the hex
     */
    public void findNeighbors(int value){
        find(value - 1, value);
        find(value + 1, value);
        find(value - col, value);
        find(value + col, value);
        find(value - col + 1, value);
        find(value + col - 1, value);

    }

    /**
     * checks to see that its neighbor is of the same team and a valid neighbor, if it is then it unionizes them
     * @param neighbor of the value
     * @param value number from the hex
     */
    public void find(int neighbor, int value){
        if(checkNeighbor(neighbor, value)){
            if (chosen[neighbor] != null && chosen[neighbor].equals(chosen[value])) {
                if(union.find(neighbor) != union.find(value)) {
                    union.union(union.find(neighbor), union.find(value));
                }
            }
        }
    }

    /**
     *
     * @param neighbor the neighbor of the value
     * @param value value in the hex
     * @return true if the neighbor is a real neighbor and false otherwise
     */
    public boolean checkNeighbor(int neighbor, int value){
        if(neighbor < 1 || neighbor > numberOfHexs){
            return false;
        } else if(value % col == 0 && (neighbor == value + 1 || neighbor == value - col - 1)){
            return false;
        } else if(value % col == 1 && (neighbor == value -1 || neighbor == value + col - 1)){
            return false;
        }
        return true;
    }
    /**
     * checks to see if its on the blue wall, if it is then it adds it to the blue Wall array
     * @param value that is in the hex
     */
    public void checkForBlueWalls(int value){
        if(value % col == 1){
            union.union(union.find(left), union.find(value));
        } else if (value % col == 0){
            union.union(union.find(right), union.find(value));
        }
    }

    /**
     * checks to see if its on the red wall, if it is then it adds it to the red Wall array
     * @param value that is in the hex
     */
    public void checkForRedWalls(int value){
        if(value > 0 && value < col + 1){
            union.union(union.find(top), union.find(value));
        } else if(value < numberOfHexs + 1 && value > numberOfHexs - col){
            union.union(union.find(bottom), union.find(value));
        }
    }



    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[30m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static String ANSI_COLOR = "\u001B[34m";

    /**
     * prints out information about the hex
     * @param color what color has won
     */
    public void printHex(String color){
        if(color.equals("blue")){ANSI_COLOR = "\u001B[34m";}
        else{ANSI_COLOR ="\u001B[31m";}
        System.out.println(ANSI_COLOR + "--------------------------" + color + " WINS----------------------------");
        playersTurn++;
        System.out.printf(ANSI_COLOR + "--------------------It took a total of %d moves!---------------\n\n\n\n", playersTurn);
    }

    /**
     * prints out the hex using recursion
     * @param indentation how far over to print each line
     * @param index what point you are at in the array
     */
    public void printHex(String indentation, int index){
        if(index == 1){
            System.out.print(indentation);
            for(int i = 0; i < col; i++){
                System.out.print(ANSI_RED + "----" + ANSI_WHITE);
            }
            System.out.println();
        }
        System.out.print(indentation);
        System.out.print(ANSI_BLUE + "\\");
        while((index + 1) % col != 1){
            if(chosen[index] == null){
                System.out.printf(ANSI_WHITE +"%4d", index);
            } else if(chosen[index].equals("Red")){
                System.out.printf(ANSI_RED + "%4d" + ANSI_WHITE, index);
            } else if(chosen[index].equals("Blue")){
                System.out.printf(ANSI_BLUE + "%4d" + ANSI_WHITE, index);
            }
            index ++;
        }
        if(chosen[index] == null){
            System.out.printf("%4d", index);
        } else if(chosen[index].equals("Red")){
            System.out.printf(ANSI_RED + "%4d" + ANSI_WHITE, index);
        } else if(chosen[index].equals("Blue")){
            System.out.printf(ANSI_BLUE + "%4d" + ANSI_WHITE, index);
        }
        index++;
        System.out.print(ANSI_BLUE + "\\");
        System.out.println();
        if(index < numberOfHexs + 1){
            printHex(indentation + " ", index);
        }
        if(index == numberOfHexs + 1){
            System.out.print(indentation);
            for(int i = 0; i < col; i++){
                System.out.print(ANSI_RED + "----" + ANSI_WHITE);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Enter your dimensions: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the amount of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Enter the amount of columns: ");
        int col = scanner.nextInt();
        HexUserVersion hex = new HexUserVersion( rows, col);


    }
}