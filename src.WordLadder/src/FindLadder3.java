import java.util.ArrayList;

public class FindLadder3 {
    int moves;
    String endWord;
    ArrayList<String> dictionary;
    String startWord;
    int count;
    FindLadder3(String startWord, String endWord, ArrayList<String> dictionary){
        this.endWord = endWord;
        this.moves = 0;
        this.dictionary = dictionary;
        this.startWord = startWord;
        this.count = 0;
    }
    //sets up everything to do some word ladder finding
    public void setUp(){
        this.dictionary.remove(this.startWord);
        ArrayList<String> words = new ArrayList<>();
        words.add(this.startWord);
        LinkedList<String> currentLaddersList = new LinkedList<>();
        currentLaddersList.insert(words);
        LinkedList<String> nextLaddersList = new LinkedList<>();
        FindMatches(currentLaddersList, this.dictionary, nextLaddersList);
    }

    //this one will run through the linked list and add options that work for the word ladder. once the correct word ladder is found
    //or it has run out of options it will stop the while loop and print the corresponding answer
    public  void FindMatches(LinkedList<String> currentLaddersList, ArrayList<String> dictionary, LinkedList<String> nextLaddersList){
        boolean done = false;
        while(!done) {
            this.moves++;
            for (int i = 0; i < currentLaddersList.getSize(); i++) {
                ArrayList<String> name2 = currentLaddersList.pop();
                String name1 = name2.get(name2.size() - 1);
                boolean found = AllPossibleOffByOne(dictionary, name1, nextLaddersList, name2);
                if (found){
                    done = true;
                    break;
                }
            }
            if (!nextLaddersList.isNotEmpty()) {
                System.out.println("Sorry but could not find a solution that would fit this word ladder");
                done = true;
            }
            currentLaddersList = nextLaddersList;
            nextLaddersList = new LinkedList<>();
        }
    }

    //this one will create all the possible off by ones that can come from the word put into it and will then check to see if that word is in the dictionary
    //if that word is in the dictionary it will insert it into a the list and then insert that list into the linkedlist. after that it removes the word from the dictionary
    public boolean AllPossibleOffByOne(ArrayList<String> dictionary, String name1, LinkedList<String> nextLaddersList, ArrayList<String> name2){
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
                if (dictionary.contains(word)){
                    baseWord.add(word);
                    nextLaddersList.insert(baseWord);
                    dictionary.remove(word);
                    this.count++;
                    if (word.equals(this.endWord)){
                        WordInfo wordInfo = new WordInfo(word, this.moves, baseWord);
                        wordInfo.print();
                        System.out.println("total enqueues: " + count);
                        print(nextLaddersList);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //this just prints the quickest path from the word to the other one.
    public void print(LinkedList<String> answer){
        System.out.printf("The quickest way from %s to %s is: ", this.startWord, this.endWord);
        ArrayList<String> answerWords = answer.pop();
        for (int i = 0; i < answerWords.size(); i++) {
            System.out.print(answerWords.get(i) + " ");
        }
    }
}
