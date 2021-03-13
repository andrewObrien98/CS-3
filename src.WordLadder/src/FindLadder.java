import java.util.ArrayList;

public class FindLadder {
    int moves;
    String endWord;
    ArrayList<String> dictionary;
    String startWord;
    int count;

    FindLadder(String startWord, String endWord, ArrayList<String> dictionary){
        this.endWord = endWord;
        this.moves = 0;
        this.dictionary = dictionary;
        this.startWord = startWord;
        this.count = 0;
    }

    //this is where I get the whole thing set up and ready to find the word ladder
    public void setUp(){
        this.dictionary.remove(this.startWord);
        ArrayList<String> words = new ArrayList<>();
        words.add(this.startWord);
        LinkedList<String> currentLaddersList = new LinkedList<>();
        currentLaddersList.insert(words);
        FindMatches(currentLaddersList, this.dictionary);
    }

    //this method will go through each word in my currentLaddersList and will run in across the dictionary to see if it has matches. it will then pass new words into a new linkedlist
    public  String FindMatches(LinkedList<String> currentLaddersList, ArrayList<String> dictionary ) {

        LinkedList<String> nextLaddersList = new LinkedList<>();
        this.moves++;
        for(int i = 0; i < currentLaddersList.getSize(); i++){
            ArrayList<String> oldLadder = currentLaddersList.pop();
            ArrayList newLadder;
            ArrayList<String> wordsToRemove = new ArrayList<>();
            for(String word : dictionary){
                newLadder = (ArrayList) oldLadder.clone();
                //this spot will check to see if words are compatible and if they are will throw them into the nextLaddersList
                if (offByOne(oldLadder.get(oldLadder.size() - 1), word)){
                    newLadder.add(word);
                    count++;
                    nextLaddersList.insert(newLadder);
                    wordsToRemove.add(word);
                    WordInfo wordInfo = new WordInfo(word, this.moves, newLadder);
                    wordInfo.print();

                    if (word.compareTo(this.endWord) == 0){
                        System.out.println("total enqueues: " + count);
                        System.out.println("it took a total " + nextLaddersList.getSize() + " queues");
                        print(nextLaddersList);
                        return "found";
                    }
                }
            }
            remove(wordsToRemove, dictionary);
        }
        if (!nextLaddersList.isNotEmpty()){
            System.out.println("Sorry but could not find a solution that would fit this word ladder");
            return "not found";
        }
        return FindMatches(nextLaddersList, dictionary);
    }

    //this method will check to see if the other word is only off by one letter
    public boolean offByOne(String word, String dictWord){
        int badLetters = 0;
        String[] letters = word.split("");
        String[] dicLetters = dictWord.split("");
        for(int i = 0; i < letters.length; i++) {
            if (!letters[i].equals(dicLetters[i])) {
                badLetters++;
            }
            if (badLetters > 1) {
                return false;
            }
        }
        return true;
    }

    //I made this method just for the purpose of printing out the answer
    public void print(LinkedList<String> answer){
        System.out.printf("The quickest way from %s to %s is: ", this.startWord, this.endWord);
        ArrayList<String> answerWords = answer.pop();
        for (int i = 0; i < answerWords.size(); i++) {
            System.out.print(answerWords.get(i) + " ");
        }
    }

    //this will take out the words that have already been used from the dictionary
    public void remove(ArrayList<String> words, ArrayList<String> dictionary){
        for (String badWords : words){
            dictionary.remove(badWords);
        }
    }


}
