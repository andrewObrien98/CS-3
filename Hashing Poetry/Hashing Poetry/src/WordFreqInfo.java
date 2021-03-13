import java.util.*;

public class WordFreqInfo {
    public String word;
    public int occurCt;
    ArrayList<Freq> followList;

    public WordFreqInfo(String word, int count) {
        this.word = word;
        this.occurCt = count;
        this.followList = new ArrayList<Freq>();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Word :" + word+":");
        sb.append(" (" + occurCt + ") : ");
        for (Freq f : followList)
            sb.append(f.toString());

        return sb.toString();
    }

    public void updateFollows(String follow) {
        //System.out.println("updateFollows " + word + ": " + follow);
        for (Freq f : followList) {
            if (follow.compareTo(f.follow) == 0) {
                f.followCt++;
                return;
            }
        }
        followList.add(new Freq(follow, 1));
    }

    public static class Freq {
        String follow;
        int followCt;

        public Freq(String follow, int ct) {
            this.follow = follow;
            this.followCt = ct;
        }

        public String toString() {
            return follow + " [" + followCt + "] ";
        }

        public boolean equals(Freq f2) {
            return this.follow.equals(f2.follow);
        }
    }

//    public static void main(String[] args) {
//        HashTable<String> list = new HashTable<>();
//        boolean insert1 = list.insert("hello", new WordFreqInfo("hello", 1));
//        boolean insert2 = list.insert("bye", new WordFreqInfo("hello", 1));
//        System.out.println(list.toString(2));
//        System.out.println(insert1);
//        System.out.println(insert2);
//    }


}

