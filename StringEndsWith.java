import java.util.*;
import java.io.*;

/**
 * Write a description of class StringEndsWith here.
 * 
 * @author Andrew Wang
 * @version 0.0
 */
//@TODO: Take a look at functionality and logic within code
public class StringEndsWith
{
    public ArrayList<String> endsWith(String s) throws IOException
    {
        ProcessWords pw = new ProcessWords();
        
        String ucInput = s.toUpperCase();
        ArrayList<String> listOfAllWords = pw.allWordsToList();
        ArrayList<String> wordList = new ArrayList<String>();
        for(int counter = 0; counter < listOfAllWords.size(); counter ++)
        {
            String thisWord = listOfAllWords.get(counter).toUpperCase();
            boolean hasAll = true;
            if(thisWord.length() < ucInput.length())
            {
                hasAll = false;
            }
            if(hasAll)
            {
                loop1:
                for(int counter2 = thisWord.length() - 1; counter2 >= thisWord.length() - ucInput.length(); counter2 --) //WHY DOES THIS WORK??
                {
                    if(thisWord.charAt(counter2) != ucInput.charAt(ucInput.length() - (thisWord.length() - counter2)))
                    {
                        hasAll = false;
                        break loop1;
                    }
                }
            }
            if(hasAll)
            {
                wordList.add(listOfAllWords.get(counter));
            }
        }
        for(int counter3 = 0; counter3 < wordList.size(); counter3 ++)
        {
            System.out.println(wordList.get(counter3));
        }
        return wordList;
    }
}
