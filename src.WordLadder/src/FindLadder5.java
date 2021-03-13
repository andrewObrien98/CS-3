import java.util.ArrayList;

public class FindLadder5 {
    int moves;
    String endWord;
    ArrayList<String> dictionary;
    String startWord;
    int count;
    FindLadder5(String startWord, String endWord, ArrayList<String> dictionary){
        this.endWord = endWord;
        this.moves = 0;
        this.dictionary = dictionary;
        this.startWord = startWord;
        this.count = 0;
    }
    //sets up everything to do some word ladder finding
    public void setUp(){
        this.dictionary.remove(this.startWord);
        ArrayList<String> startWords = new ArrayList<>();
        startWords.add(this.startWord);
        LinkedList<String> currentLaddersList = new LinkedList<>();
        currentLaddersList.insert(startWords);
        LinkedList<String> nextLaddersList = new LinkedList<>();

        this.dictionary.remove(this.endWord);
        ArrayList<String> endWords = new ArrayList<>();
        endWords.add(this.endWord);
        LinkedList<String> endCurrentLaddersList = new LinkedList<>();
        endCurrentLaddersList.insert(endWords);
        LinkedList<String> endNextLaddersList = new LinkedList<>();

        FindMatches(currentLaddersList, nextLaddersList, endCurrentLaddersList, endNextLaddersList);
    }

    //this will create a word ladder that starts from the both the start word and the end word
    public  void FindMatches(LinkedList<String> currentLaddersList, LinkedList<String> nextLaddersList, LinkedList<String> endCurrentLaddersList, LinkedList<String> endNextLaddersList){
        boolean done = false;
        ArrayList<String> wordsFound = new ArrayList<>();
        wordsFound.add(this.endWord);
        ArrayList<String> endWordsFound = new ArrayList<>();
        while(!done) {
            this.moves++;
            //for front word ladder
            for (int i = 0; i < currentLaddersList.getSize() && !done; i++) {
                ArrayList<String> name2 = currentLaddersList.pop();
                String name1 = name2.get(name2.size() - 1);
                done = AllPossibleOffByOne(this.dictionary, name1, nextLaddersList, name2, wordsFound, endWordsFound, endCurrentLaddersList);

            }
            //for back word ladder
            for (int i = 0; i < endCurrentLaddersList.getSize() && !done; i++) {
                ArrayList<String> name2 = endCurrentLaddersList.pop();
                String name1 = name2.get(name2.size() - 1);
                done = AllPossibleOffByOne(this.dictionary, name1, endNextLaddersList, name2, endWordsFound, wordsFound, nextLaddersList);

            }
            if (!nextLaddersList.isNotEmpty() && !done) {
                System.out.println("Sorry but could not find a solution that would fit this word ladder");
                done = true;
            }
            if (!endNextLaddersList.isNotEmpty() && !done) {
                System.out.println("Sorry but could not find a solution that would fit this word ladder");
                done = true;
            }
            currentLaddersList = nextLaddersList;
            nextLaddersList = new LinkedList<>();
            endCurrentLaddersList = endNextLaddersList;
            endNextLaddersList = new LinkedList<>();
        }
    }

    //this one will create all the possible off by ones that can come from the word put into it and will then check to see if that word is in the dictionary
    //if that word is in the dictionary it will insert it into a the list and then insert that list into the linkedlist. after that it removes the word from the dictionary
    public boolean AllPossibleOffByOne(ArrayList<String> dictionary, String name1, LinkedList<String> nextLaddersList, ArrayList<String> name2, ArrayList<String> wordsFound, ArrayList<String> EndWordsFound, LinkedList<String> foundList){
        String[] name = name1.split("");
        String[] alphabet = {"a","b","c","d","e","f","g","h","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        for (int i = 0; i < name.length; i++){
            name = name1.split("");
            for(String letter: alphabet) {
                ArrayList<String> baseWord = (ArrayList) name2.clone();
                name[i] = letter;
                String word = "";
                for(String letter1 : name){
                    word += letter1;
                }
                if (wordsFound.contains(word)) {
                    baseWord.add(word);
                    System.out.println("total enqueues: " + count);
                    WordInfo wordInfo = new WordInfo(word, this.moves, baseWord);
                    wordInfo.print();
                    print(baseWord, foundList, word);
                    return true;
                }
                if (dictionary.contains(word)){
                    baseWord.add(word);
                    nextLaddersList.insert(baseWord);
                    dictionary.remove(word);
                    this.count++;
                    EndWordsFound.add(word);
                }
            }
        }
        return false;
    }

    //this just prints the quickest path from the word to the other one.
    public void print(ArrayList<String> answer, LinkedList<String> listAnswer, String word){
        System.out.printf("The quickest way from %s to %s is: ", this.startWord, this.endWord);
        for (int i = 0; i < listAnswer.getSize(); i++) {
            ArrayList<String> possibility = listAnswer.pop();
            if (possibility.contains(word)){
                for (String words : answer){
                    System.out.print(words + " ");
                }
                boolean done = false;
                for (int j = 1; j <= possibility.size(); j++){
                    if (done){
                        System.out.print(possibility.get(possibility.size() - j) + " ");
                    }
                    if (possibility.get(possibility.size() - j).equals(word)){
                        done = true;
                    }
                }
                System.out.println();
                break;
            }
        }

    }
}
