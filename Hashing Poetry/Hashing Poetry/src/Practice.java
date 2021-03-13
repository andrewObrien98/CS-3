import java.util.ArrayList;
import java.util.Collections;

public class Practice {
    public static void main(String[] args) {
        int mineCount = 10;
        ArrayList<Boolean> cellsWithMines = new ArrayList<>();
        int currentMineCount = 0;
        for(int i = 0; i < 10; i++){
            if(currentMineCount < mineCount){ cellsWithMines.add(true); }//false if they wont have mines
            else{
                cellsWithMines.add(false);//true if they will have mines
            }
            currentMineCount++;
        }
        Collections.shuffle(cellsWithMines);
        for(Boolean bool: cellsWithMines){
            System.out.println(bool);
        }
    }
}
