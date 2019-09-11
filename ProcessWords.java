import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * All the basic methods that make up the Program
 * 
 * @author Andrew Wang
 * @version anotherVeryBigNumber
 */
public class ProcessWords
{
    public ArrayList<String> allWordsToList() throws IOException
    {
        ArrayList<String> listOfAllWords = new ArrayList<String>();
        Scanner scanner = new Scanner(new File("Files/TextFiles/AllWordsNoRepeats.txt"));
        while(scanner.hasNext())
        {
            listOfAllWords.add(scanner.nextLine());
        }
        return listOfAllWords;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int getNumberFromLetter(char letter)
    {
        int numberPad = 0;
        if(letter > 64 && letter < 80 ) //A --> O 
        {
            numberPad = (letter + 1) / 3 - 20;
        }
        else if(letter > 96 && letter < 112) //a --> o
        {
            numberPad = (letter - 1) /  3 - 30;
        }
        else if(letter > 79 && letter < 84 || letter > 111 && letter < 116) //PQRS || pqrs
        {
            numberPad = 7;
        }
        else if(letter > 83 && letter < 87 || letter > 115 && letter < 119) //TUV || tuv
        {
            numberPad = 8;
        }
        else if(letter > 86 && letter < 91 || letter > 118 && letter < 123) //WXYZ || wxyz
        {
            numberPad = 9;
        }
        return numberPad;
    }

    public String getLCLetterFromNumber(int num) //only converts to lower case Letters
    {
        String listOfLetters = null;
        if(num == 2)
        {
            listOfLetters = "abc";
        }
        else if(num == 3)
        {
            listOfLetters = "def";
        }
        else if(num == 4)
        {
            listOfLetters = "ghi";
        }
        else if(num == 5)
        {
            listOfLetters = "jkl";
        }
        else if(num == 6)
        {
            listOfLetters = "mno";
        }
        else if(num == 7)
        {
            listOfLetters = "pqrs";
        }
        else if(num == 8)
        {
            listOfLetters = "tuv";
        }
        else if(num == 9)
        {
            listOfLetters = "wxyz";
        }
        return listOfLetters;
    }

    public String getUCLetterFromNumber(int num) //only converts to upper case letters
    {
        String listOfLetters = null;
        if(num == 2)
        {
            listOfLetters = "ABC";
        }
        else if(num == 3)
        {
            listOfLetters = "DEF";
        }
        else if(num == 4)
        {
            listOfLetters = "GHI";
        }
        else if(num == 5)
        {
            listOfLetters = "JKL";
        }
        else if(num == 6)
        {
            listOfLetters = "MNO";
        }
        else if(num == 7)
        {
            listOfLetters = "PQRS";
        }
        else if(num == 8)
        {
            listOfLetters = "TUV";
        }
        else if(num == 9)
        {
            listOfLetters = "WXYZ";
        }
        return listOfLetters;
    }

    public char[] alphabetizeLetters(String s)
    {
        String uc = s.toUpperCase();
        char[] list = new char[s.length()];
        for(int counter = 0; counter < uc.length(); counter ++)
        {
            list[counter] = uc.charAt(counter);
        }

        for(int counter = 0; counter < uc.length(); counter ++)
        {
            for(int counter2 = counter + 1; counter2 < uc.length(); counter2 ++)
            {
                if(list[counter] > list[counter2])
                {
                    char temp = list[counter];
                    list[counter] = list[counter2];
                    list[counter2] = temp;
                }
            }
        }
        return list;
    }
    
    public boolean isAWord(String s) throws IOException
    {
        ArrayList<String> listOfAllWords = allWordsToList();
        String ucInput = s.toUpperCase();
        boolean exists = false;
        loop1:
        for(int counter = 0; counter < listOfAllWords.size(); counter ++)
        {
            String thisWord = listOfAllWords.get(counter).toUpperCase();
            if(thisWord.equals(ucInput))
            {
                exists = true;
                break loop1;
            }
        }
        return exists;
    }

    /**
     * NOT DONE YET
     */
    public ArrayList<String> findAnagrams(String s) throws Exception
    {
        char[] alphInput = alphabetizeLetters(s);
        ArrayList<String> listOfAllWords = allWordsToList();
        ArrayList<String> anagramList = new ArrayList<String>();
        for(int counter = 0; counter < listOfAllWords.size(); counter ++)
        {
            String thisWord = listOfAllWords.get(counter);
            char[] alphThisWord = alphabetizeLetters(thisWord);
            if(thisWord.length() == s.length())
            {
                boolean equals = true;
                loop1:
                for(int counter2 = 0; counter2 < s.length(); counter2 ++)
                {
                    if(alphThisWord[counter2] != alphInput[counter2])
                    {
                        equals = false;
                        break loop1;
                    }
                }
                if(equals)
                {
                    anagramList.add(thisWord);
                }
            }
        }
        
        /*
        for(int counter = 0; counter < anagramList.size(); counter ++)
        {
            System.out.println(anagramList.get(counter));
        }
        */
        /*
        for(int counter = 0; counter < anagramList.size(); counter ++)
        {
            Runtime.getRuntime().exec("say -v Good News " + anagramList.get(counter));
            Thread.sleep(3000);
        }
        */
        return anagramList;
    }

    /*public ArrayList<ArrayList<String>> getWordCreatorFromLetters()
    {
        Scanner keyboard = new Scanner(System.in);
        String fromKeyboard = keyboard.next();
        String[] stringList = fromKeyboard.split("");

        ArrayList<ArrayList<String>> wordCreator = new ArrayList<ArrayList<String>>();
        for(int counter = 0; counter < stringList.length; counter ++)
        {
            ArrayList<String> letters = alphabetizeToLetters(Integer.parseInt(stringList[counter]));
            wordCreator.add(letters);
        }

        //some way to parse the wordCreator

        return wordCreator;

    }

    public ArrayList<String> getWordsFromWordCreator()  //fake news
    {
        ArrayList<ArrayList<String>> wordCreator = getWordCreatorFromLetters();

        for(int counter = 0; counter < wordCreator.get(0).size(); counter ++)
        {
            String possibleWord = wordCreator.get(0).get(counter);

            for(int counter2 = 1; counter2 < wordCreator.size(); counter2 ++)
            {
                //for(int counter4 = 0; counter4 < wordCreator.get(counter2).size(); counter4 ++)
                possibleWord = possibleWord + wordCreator.get(counter2).get(0);

                for(int counter3 = wordCreator.get(wordCreator.size()).length(); counter3 > 0; counter3 --)
                {
                    possibleWord = possibleWord + wordCreator.ge(counter2).get(counter3);
                }
            }
        }

    }
    */

    public ArrayList<String> allWordsWithLetters(String s) throws IOException
    {
        String ucInput = s.toUpperCase();
        ArrayList<String> listOfAllWords = allWordsToList();
        ArrayList<String> wordList = new ArrayList<String>();
        for(int counter = 0; counter < listOfAllWords.size(); counter ++)
        {
            boolean allLetters = true;
            String thisWord = listOfAllWords.get(counter);
            String moddedThisWord = thisWord.toUpperCase();
            for(int counter2 = 0; counter2 < ucInput.length(); counter2 ++)
            {
                boolean hasLetter = false;
                loop1:
                for(int counter3 = 0; counter3 < moddedThisWord.length(); counter3 ++)
                {
                    if(moddedThisWord.charAt(counter3) == ucInput.charAt(counter2))
                    {
                        hasLetter = true;
                        moddedThisWord = moddedThisWord.substring(0, counter3) + moddedThisWord.substring(counter3 + 1, moddedThisWord.length());
                        break loop1;
                    }
                }
                if(!hasLetter)
                {
                    allLetters = false;
                }
            }
            if(allLetters)
            {
                wordList.add(thisWord);
            }
        }
        
        /*
        for(int counter4 = 0; counter4 < wordList.size(); counter4 ++)
        {
            System.out.println(wordList.get(counter4));
        }
        */
        return wordList;
    }
    
    public ArrayList<String> wordsWithSection(String s) throws IOException
    {
        String ucInput = s.toUpperCase();
        ArrayList<String> listOfAllWords = allWordsToList();
        ArrayList<String> wordList = new ArrayList<String>();
        for(int counter = 0; counter < listOfAllWords.size(); counter ++)
        {
            String thisWord = listOfAllWords.get(counter).toUpperCase();
            boolean hasAll = true;
            loop1:
            for(int counter2 = 0; counter2 < thisWord.length(); counter2 ++)
            {
                if(thisWord.charAt(counter2) == ucInput.charAt(0))
                {
                    for(int counter3 = 1; counter3 < ucInput.length(); counter3 ++)
                    {
                        try
                        {
                            if(thisWord.charAt(counter2 + counter3) != ucInput.charAt(counter3))
                            {
                                hasAll = false;
                                break loop1;
                            }
                        }
                        catch(StringIndexOutOfBoundsException e)
                        {
                            hasAll = false;
                            break loop1;
                        }
                    }
                    break loop1;
                }
                if(counter2 == thisWord.length() - 1)  //wait wut
                {
                    hasAll = false;
                    break loop1;
                }
            }
            if(hasAll)
            {
                wordList.add(listOfAllWords.get(counter));
            }
        }
        
        /*
        for(int counter4 = 0; counter4 < wordList.size(); counter4 ++)
        {
            System.out.println(wordList.get(counter4));
        }
        */
        return wordList;
    }
    
    public ArrayList<String> startsWith(String s) throws IOException
    {
        String ucInput = s.toUpperCase();
        ArrayList<String> listOfAllWords = allWordsToList();
        ArrayList<String> wordList = new ArrayList<String>();
        for(int counter = 0; counter < listOfAllWords.size(); counter ++)
        {
            String thisWord = listOfAllWords.get(counter).toUpperCase();
            boolean hasAll = true;
            loop1:
            for(int counter2 = 0; counter2 < ucInput.length(); counter2 ++)
            {
                try
                {
                    if(thisWord.charAt(counter2) != ucInput.charAt(counter2))
                    {
                        hasAll = false;
                        break loop1;
                    }
                }
                catch(StringIndexOutOfBoundsException e)
                {
                    hasAll = false;
                    break loop1;
                }
            }
            if(hasAll)
            {
                wordList.add(listOfAllWords.get(counter));
            }
        }
        
        /*
        for(int counter3 = 0; counter3 < wordList.size(); counter3 ++)
        {
            System.out.println(wordList.get(counter3));
        }
        */
        return wordList;
    }
    
    public ArrayList<String> FakeendsWith(String s) throws IOException
    {
        String ucInput = s.toUpperCase();
        ArrayList<String> listOfAllWords = allWordsToList();
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
        
        /*
        for(int counter3 = 0; counter3 < wordList.size(); counter3 ++)
        {
            System.out.println(wordList.get(counter3));
        }
        */
        return wordList;
    }
    
    public ArrayList<String> endsWith(String s) throws IOException
    {
        String ucInput = s.toUpperCase();
        ArrayList<String> listOfAllWords = allWordsToList();
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
                for(int counter2 = 1; counter2 <= ucInput.length(); counter2 ++) //WHY DOES THIS WORK??
                {
                    if(thisWord.charAt(thisWord.length() - counter2) != ucInput.charAt(ucInput.length() - counter2))
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
        
        /*
        for(int counter3 = 0; counter3 < wordList.size(); counter3 ++)
        {
            System.out.println(wordList.get(counter3));
        }
        */
        return wordList;
    }
    
    public boolean checkPrefix(String pf, String possWord) throws IOException
    {
        ArrayList<String> wordList = startsWith(pf);
        boolean exists = false;
        loop1:
        for(int counter = 0; counter < wordList.size(); counter ++)
        {
            if(wordList.get(counter).equals(possWord))
            {
                exists = true;
                break loop1;
            }
        }
        return exists;
    }
    
    
    
    public void removeRepeats() throws Exception
    {
        ArrayList<String> listOfAllWords = allWordsToList();
        for(int counter = 0; counter < listOfAllWords.size(); counter ++)    //take out all the repeats
        {
            for(int counter2 = counter + 1; counter2 < listOfAllWords.size(); counter2 ++) //so doesn't end up taking out itself? also just more effecient in general
            {
                if(listOfAllWords.get(counter).equals(listOfAllWords.get(counter2)))
                {
                    listOfAllWords.remove(counter2);
                    counter2 --;        //so it doesn't skip indices
                }
            }
        }   //sometimes returns a "-"?
        
        PrintWriter printer = new PrintWriter("Files/TextFiles/AllWordsNoRepeats.txt");
        for(int counter3 = 0; counter3 < listOfAllWords.size(); counter3 ++)
        {
            printer.println(listOfAllWords.get(counter3));
        }
        printer.close();
    }
}