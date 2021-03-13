import java.util.ArrayList;
public class WordInfo {
    public String word;
    public int moves;
    public ArrayList<String> history;

    public WordInfo(String word, int moves, ArrayList<String> history){
        this.word = word;
        this.moves = moves;
        this.history = history;
    }

    public void printHistory(){
        for(String word : this.history){
            System.out.print(word + " ");
        }
        System.out.println("]");
    }

    public void print(){
        System.out.print("Word " + word    + " Moves " +moves  + " History [");
        printHistory();
    }

}

