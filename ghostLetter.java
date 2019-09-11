import java.util.*;
import java.io.*;

/**
 * A ghostLetter for the Computer to make a decision
 * 
 * @author Andrew Wang
 * @version 3 or 4 I think? Maybe 5 or 6... or 7...
 */
public class ghostLetter
{
    char letter;
    int potential;
    
    /**
     * Constructor for objects of class ghostLetter (for the purpose of the Computer choosing Letters)
     */
    public ghostLetter(char c, int p)
    {
        letter = c;
        potential = p;
    }
    
    /**
     * The Computer's turn: checks if the User loses or chooses a Letter
     * 
     * @param word: the Word segment that's currently in play
     * @return char (the Letter) or 0 if the User loses, 1 if the Computer loses (which it shouldn't check itself for)
     * Called by ghost.computerTurn()
     */
    public char chooseLetter(String word) throws Exception
    {
        ProcessWords pw = new ProcessWords();
        
        String ucInput = word.toUpperCase();
        ArrayList<String> listOfAllWords = pw.allWordsToList();
        for(int counter = 0; counter < listOfAllWords.size(); counter ++)   //checks if Word has already been made
        {
            String ucThisWord = listOfAllWords.get(counter).toUpperCase();
            if(ucInput.equals(ucThisWord))
            {
                System.out.println("****************");
                System.out.println("You've made a Word!");
                System.out.println("****************");
                //Runtime.getRuntime().exec("say -v Susan " + "You've made a word!");
                Thread.sleep(2000);
                return 0;
            }
        }
        
        ArrayList<String> possWordList = pw.startsWith(word);
        if(possWordList.size() == 0)    //checks if the segment can't make a Word
        {
            System.out.println("****************");
            System.out.println("That can't make a Word!");
            System.out.println("****************");
            //Runtime.getRuntime().exec("say -v Susan " + "That can't make a word!");
            Thread.sleep(2000);
            return 0;
        }
        /**code reaches here pretty fast*/
        
        ArrayList<ghostLetter> possLetterList = new ArrayList<ghostLetter>();
        for(int counter = 0; counter < possWordList.size(); counter ++)     /**used to check for repeats before adding...*/
        {
            String thisWord = possWordList.get(counter).toUpperCase();
            char possChar = thisWord.charAt(word.length()); //the char immediately following the "prefix" Word
            
            //Used to take out Letters that made Words but I want the Computer to be able to "make mistakes", or rather allow the User the option of Challenging
            ghostLetter possLetter = new ghostLetter(thisWord.charAt(word.length()), 0);    //adds a new ghostLetter with the Letter immediately following the "prefix" word and 0 Potential
            possLetterList.add(possLetter);
        }
        
        for(int counter2 = 0; counter2 < possLetterList.size(); counter2 ++)    //take out all the repeats
        {
            for(int counter6 = counter2 + 1; counter6 < possLetterList.size(); counter6 ++) //so doesn't end up taking out itself? also just more effecient in general
            {
                if(possLetterList.get(counter2).letter == (possLetterList.get(counter6).letter))
                {
                    possLetterList.remove(counter6);
                    counter6 --;        //so it doesn't skip indices
                }
            }
        }   //sometimes returns a "-"? oh yeah makes sense some words have dashes in them...
        
        System.out.println(" No. of Options Found: " + possLetterList.size());/**does it even get here? 2 hrs and not here... 4 hours and not here... UPDATE! takes like 120 seconds for 'Z'... update... takes significantly longer for 'A'*/
        //Runtime.getRuntime().exec("say -v Susan " + "Number of options found..." + possLetterList.size());
        Thread.sleep(2500);
        
        for(int counter3 = 0; counter3 < possLetterList.size(); counter3 ++) //loop through the possible Letters, determine pathPotentials
        {
            ArrayList<String> charPossWords = pw.startsWith(word + possLetterList.get(counter3).letter);    //list of possWords that start with the word + the possLetter
            for(int counter4 = 0; counter4 < charPossWords.size(); counter4 ++) //yeahhh this is gonna take a while... Update: nvm
            {
                if((charPossWords.get(counter4).length() - (word.length() + 1)) % 2 == 1) //if odd number of Letters left before a Word is created
                {
                    possLetterList.get(counter3).potential ++;
                }
                else //if odd number of Letters left before Word is created
                {
                    possLetterList.get(counter3).potential --;
                }
            }
            if(pw.isAWord(word + possLetterList.get(counter3).letter)) //checks if the added char makes a Word
            {
                possLetterList.get(counter3).potential -= 10;    //if it is a Word then potential goes down by a lot, but it doesn't remove it from the list of possibilities completely;
            }
            
            System.out.println(possLetterList.get(counter3).letter + " " + possLetterList.get(counter3).potential);
        }
        /** For some reason takes a while to get here too for the letter A? it shouldn't since possLetterList.size() should be < 26 at this point*/
                //Update: can be 27 because there are words with dashes ('-') in them   //Update: can be 28 cause there are apostrophes... Not that it matters anymore with the repeats actually being taken out
        /**'A' --> 'AZ' after like 20 minutes lol*/
        /**Update: it's much much faster now 0_O guess having the "take out repeats" loop actually work makes it much faster*/
        
        for(int counter5 = 0; counter5 < possLetterList.size() - 1; counter5 ++)    //sorts through possLetters and puts the highest Potential at the end of the ArrayList
        {
            ghostLetter thisLetter = possLetterList.get(counter5);
            ghostLetter nextLetter = possLetterList.get(counter5 + 1);
            if(thisLetter.potential > nextLetter.potential)   //switch if thisPotential is greater
            {
                possLetterList.set(counter5 + 1, possLetterList.get(counter5));
            }
        }
        if(possLetterList.size() < 1)  //if no possibilities it loses (although "zygot-" definitely has possibilities? Update: nvm) 
        {
            return 1;       //Computer loses    //Update: Not sure if this will ever be a thing now that it is allowed to make words
        }
        
        if(possLetterList.get(possLetterList.size() - 1).potential < 0) //when there are no good options
        {
            System.out.println("");
            System.out.println("*T_T*");
            System.out.println("");
            //Runtime.getRuntime().exec("say -v Susan " + "Blame Andrew for not having word lists that differentiate between common and uncommon words.");
            Thread.sleep(5000);
        }
        return possLetterList.get(possLetterList.size() - 1).letter;    //returns last element (highest Potential)
    }
}
