import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.util.ArrayList;

public class LadderGame {
    static int MaxWordSize = 15;
    ArrayList<String>[] allList;  // Array of ArrayLists of words of each length.
    Random random;

    public LadderGame(String file) {
        random = new Random();
        allList = new ArrayList[MaxWordSize];
        for (int i = 0; i < MaxWordSize; i++)
            allList[i] = new ArrayList<String>();

        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNext()) {
                String word = reader.next();
                if (word.length()< MaxWordSize) allList[word.length()].add(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this method grabs the dictionary list with the correct lengths and then calls FindLadder to find the solution
    public void play(String a, String b) {
        if (a.length() != b.length()){
            System.out.println("Words are not the same length");
            return;
         }
        if (a.length()  >= MaxWordSize) return;
        if (!allList[a.length()].contains(a)||!allList[a.length()].contains(b)){
            System.out.println("These words are not in the dicitonary!");
            return;
        }
        ArrayList<String> list;
        ArrayList<String> l = allList[a.length()];
        list = (ArrayList) l.clone();
        System.out.println("Seeking a solution from " + a + " ->" + b + " Size of List " + list.size());
        long time1 = System.currentTimeMillis();
        FindLadder5 solution = new FindLadder5(a,b,list);
        solution.setUp();
        System.out.printf("time it took to perform the operation: %d milliseconds\n\n", (System.currentTimeMillis() - time1));

    }
    public void play(int len) {
       if (len >= MaxWordSize) return;
        ArrayList<String> list = allList[len];
        String a = list.get(random.nextInt(list.size()));
        String b = list.get(random.nextInt(list.size()));
        play(a, b);
    }

    public void listWords(int amount, int size){
        int count = 0;
        for (String word : allList[size]){
            System.out.println(word);
            count++;
            if (count == amount){
                break;
            }
        }
    }

}
