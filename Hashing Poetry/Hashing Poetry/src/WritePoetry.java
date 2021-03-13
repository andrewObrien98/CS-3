import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WritePoetry {
    ArrayList<String> keys = new ArrayList<>();

    WritePoetry(){

    }

    /**
     *
     * @param poem takes the poem file
     * @param startWord takes the start word
     * @param amount how many words in the new poem
     * @param bool if I should print the hash table
     * @functions will create a hashTable, print it out, and then write a new poem
     * @return True or False based off of whether it printed hashTable or not
     * @throws FileNotFoundException
     */
    public boolean WritePoem(String poem, String startWord, int amount, boolean bool) throws FileNotFoundException {
        HashTable<String, WordFreqInfo> hashTable = createHashTable(poem);
        printHashtable(hashTable, bool);
        writeNewPoem(hashTable, startWord, amount);
        this.keys = new ArrayList<>();
        return bool;
    }

    /**
     *
     * @param path is the name of the file we want
     * @functions it will run through everyWord in the poem and keep track of the words that follow it and how many times each one occurs.
     * It will keep this information in the hash Table
     * @return a hash table
     * @throws FileNotFoundException
     */
    public HashTable<String, WordFreqInfo> createHashTable(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner poem = new Scanner(file);
        HashTable<String, WordFreqInfo> hashTable = new HashTable<>();
        String currentWord = poem.next();
        currentWord = currentWord.toLowerCase();
        WordFreqInfo previous = new WordFreqInfo(currentWord, 1);
        hashTable.insert(currentWord, previous);
        keysList(currentWord);
        while(poem.hasNext()){
            currentWord = poem.next();
            currentWord = currentWord.toLowerCase();
            previous.updateFollows(currentWord);
            previous = new WordFreqInfo(currentWord, 1);
            boolean insert = hashTable.insert(currentWord, previous);
            if(!insert){
                WordFreqInfo sameWord = hashTable.find(currentWord);
                sameWord.occurCt += 1;
                keysList(currentWord);
                previous = sameWord;
            }
            keysList(currentWord);
        }
        return hashTable;
    }

    /**
     *
     * @param word new key value
     * @function keeps track off all of the keys
     */
    public void keysList(String word){
        if(!this.keys.contains(word)){
            this.keys.add(word);
        }
    }

    /**
     *
     * @param hashTable is the hash Table
     * @param print whether to print the hashTable or not
     * @functions it will run through all of the keys then print them
     */
    public void printHashtable(HashTable<String, WordFreqInfo> hashTable, Boolean print){
        if(print){
            for(int i = 0; i < this.keys.size(); i++){
                WordFreqInfo word = hashTable.find(this.keys.get(i));
                System.out.println(word);
            }
        }
    }

    /**
     *
     * @param hashTable
     * @param startWord
     * @param endAmount
     * @functions: this prints out the words to the new poem
     */
    public void writeNewPoem(HashTable<String, WordFreqInfo> hashTable, String startWord, int endAmount){
        int currentAmount = 0;
        String currentWord = startWord;
        while(currentAmount < endAmount){
            if(checkChar(currentWord)){
                System.out.print(currentWord + "\n");
            } else if (checkCommaWords(currentWord)){
                System.out.print(" " + currentWord + "\n");
            } else{
                System.out.print(" " + currentWord);
            }
            currentAmount += 1;
            if(currentAmount < endAmount){
                currentWord = findNextWord(hashTable, currentWord);}
        }

        if(!checkChar(currentWord)){
            System.out.print(".\n");}
    }

    /**
     *
     * @param character
     * @return true is character is a ! , ? or . false otherwise
     */
    public boolean checkChar(String character){
        if(character.equals("!")||character.equals("?")||character.equals(",")||character.equals("." )){
            return true;
        }
        return false;
    }

    /**
     *
     * @param character word
     * @return true if the word has a comma attached to it and false otherwise
     */
    public boolean checkCommaWords(String character){
        String[] wordList = character.split("");
        if( wordList[wordList.length - 1].equals(",") ){
            return true;
        }
        return false;
    }

    /**
     *
     * @param table
     * @param currentWord
     * @return the next word in the poem
     */
    public String findNextWord(HashTable<String, WordFreqInfo> table, String currentWord){
        WordFreqInfo value = table.find(currentWord);
        String nextword = nextWord(value);
        return nextword;
    }

    /**
     * @param value is the an element that was attached to the key
     * @function It will go through and based off an algorithm pick which of the next words from the current word should go next
     * @return the next word
     */
    public String nextWord(WordFreqInfo value){
        int total = value.occurCt;
        WordFreqInfo.Freq nextWord = new WordFreqInfo.Freq("bogus", 1);
        Random rand = new Random();
        if(total > 1){
            int max = rand.nextInt(total - 1);
            int sum = 0;
            for(int i = 0; sum <= max; i++){
                nextWord = value.followList.get(i);
                sum += nextWord.followCt;
            }
            return nextWord.follow;
        }
        return value.followList.get(0).follow;

    }


}

