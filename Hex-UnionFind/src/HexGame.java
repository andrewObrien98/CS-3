import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HexGame {
    Union union = new Union(126);
    int top = 122;
    int bottom = 123;
    int left = 124;
    int right = 125;
    String[] chosen = new String[122];
    int playersTurn = 0;

    /**
     * calls createHex to start the game
     * @param file the file from which the numbers are taken
     * @throws FileNotFoundException
     */
    public HexGame(String file) throws FileNotFoundException {
        createHex(file);
    }

    /**
     * takes a number one at a time from the file and switches off from blue to red on deciding which side gets the number
     * will check each time a number is chosen to see if it has any neighbors it connect with
     * it will also check everytime to see if it has won!
     * @param file is the file from where the numbers are drawn from
     * @throws FileNotFoundException
     */
    public void createHex(String file) throws FileNotFoundException{
        Scanner input = new Scanner(new File(file));
        while(input.hasNext()){
            int value = input.nextInt();
            if(chosen[value] == null){
                if (playersTurn % 2 == 0) {
                    chosen[value] = "Blue";
                    findNeighbors(value);
                    checkForBlueWalls(value);
                    if (union.find(left) == union.find(right)) {
                        printHex("blue", file);
                        break;
                    }

                } else {
                    chosen[value] = "Red";
                    findNeighbors(value);
                    checkForRedWalls(value);
                    if (union.find(bottom) == union.find(top)) {
                        printHex("red", file);
                        break;
                    }
                }
                playersTurn++;
            } else {
                System.out.println("\nThat value has already been chosen, please choose another\n");
            }

        }
    }

    /**
     * just performs the find functions on all 6 neighbors
     * @param value is an int from the hex
     */
    public void findNeighbors(int value){
        find(value - 1, value);
        find(value + 1, value);
        find(value - 11, value);
        find(value + 11, value);
        find(value - 10, value);
        find(value + 10, value);

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
        if(neighbor < 1 || neighbor > 121){
            return false;
        } else if(value % 11 == 0 && (neighbor == value + 1 || neighbor == value - 10)){
            return false;
        } else if(value % 11 == 1 && (neighbor == value -1 || neighbor == value + 10)){
            return false;
        }
        return true;
    }
    /**
     * checks to see if its on the blue wall, if it is then it adds it to the blue Wall array
     * @param value that is in the hex
     */
    public void checkForBlueWalls(int value){
        if(value % 11 == 1){
            union.union(union.find(left), union.find(value));
        } else if (value % 11 == 0){
            union.union(union.find(right), union.find(value));
        }
    }

    /**
     * checks to see if its on the red wall, if it is then it adds it to the red Wall array
     * @param value that is in the hex
     */
    public void checkForRedWalls(int value){
        if(value > 0 && value < 12){
            union.union(union.find(top), union.find(value));
        } else if(value < 122 && value > 110){
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
     * @param file name of the file
     */
    public void printHex(String color, String file){
        if(color.equals("blue")){ANSI_COLOR = "\u001B[34m";}
        else{ANSI_COLOR ="\u001B[31m";}
        System.out.println(ANSI_COLOR + "File Name: " + file);
        System.out.println("--------------------------" + color + " WINS----------------------------");
        printHex("", 1);
        playersTurn++;
        System.out.printf(ANSI_COLOR + "--------------------It took a total of %d moves!---------------\n\n\n\n", playersTurn);
    }

    /**
     * prints out the hex using recursion
     * @param indentation how far over to print each line
     * @param index what point you are at in the array
     */
    public void printHex(String indentation, int index){
        System.out.print(indentation);
        while((index + 1) % 11 != 1){
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
        System.out.println();
        if(index < 122){
            printHex(indentation + " ", index);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        HexGame hex1 = new HexGame("moves.txt");
        HexGame hex2 = new HexGame("moves2.txt");

    }
}