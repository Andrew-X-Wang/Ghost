import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * See Title
 * 
 * @author Andrew Wang 
 * @version 0.3
 */
public class MainMenu
{
    /**
     * It's the Main Menu
     * 
     * @param none
     * @return none
     */
    public void menuOptions() throws Exception
    {
        System.out.println("");
        ProcessWords pw = new ProcessWords();
        System.out.println("*##############################*");
        System.out.println("What Would You Like To Do?");
        //Runtime.getRuntime().exec("say -v Susan " + "What would you like to do?");
        
        String[] choices = 
        {
            "Keypad Stuff!",
            "Find an Anagram!",
            "Find Words that contain specific Letters!",
            "Find Words that contain a specific Segment!",
            "Find Words that have a certain Prefix!",
            "Find Words that have a certain Suffix!",
            "Check if a certain String makes a Word!",
            "PLAY gHoSt!1!!1!!11!!!1!!!!!!!!!1",
            "No thanks.",
        };
        
        String s1 = (String)JOptionPane.showInputDialog(
        
                null, //could specify location in frame
                "What would you like to do?", //the question
                "Main Menu:", //the frame title
                JOptionPane.PLAIN_MESSAGE, //the icon type
                null, //an icon you select
                choices, //array of options
                choices[7]); //the default to be selected
                
           
        
        if(s1.equals(choices[0]))
        {
            System.out.println("================");
            
            String[] choices2 = 
            {
                "Get Numbers from Letters!",
                "Get Letters from Numbers!",
            };
            
            String s2 = (String)JOptionPane.showInputDialog(
        
                null, //could specify location in frame
                "What would you like to do?", //the question
                "NumberPad!", //the frame title
                JOptionPane.PLAIN_MESSAGE, //the icon type
                null, //an icon you select
                choices2, //array of options
                choices2[0]); //the default to be selected
            
            if(s2.equals(choices2[0]))
            {
                Scanner keyboard = new Scanner(System.in);  //upper case or lower case doesn't matter; getNumberFromLetter deals with it
                System.out.println("Enter what Letters you'd like to convert to Numbers: (Format: abcdefg...)");
                String input = keyboard.next();
                String output = "";
                for(int counter = 0; counter < input.length(); counter ++)
                {
                    output += pw.getNumberFromLetter(input.charAt(counter));
                }
                System.out.println("From '" + input + "' you get: " + output + "!");
            }
            
            else if(s2.equals(choices2[1]))
            {
                Scanner keyboard = new Scanner(System.in);
                System.out.println("Enter what Numbers you'd like to convert to Letters: (Format: 23456789...");
                String input = keyboard.next();
                ArrayList<Integer> inputList = new ArrayList<Integer>();
                for(int counter2 = 0; counter2 < input.length(); counter2 ++)
                {
                    inputList.add(Character.getNumericValue(input.charAt(counter2)));
                }
                
                ArrayList<String> output = new ArrayList<String>();
                for(int counter = 0; counter < inputList.size(); counter ++)
                {
                    output.add(pw.getLCLetterFromNumber(inputList.get(counter)));
                }
                
                System.out.println("From '" + input + "' you get: ");
                System.out.print(output.get(0));
                for(int counter2 = 1; counter2 < output.size(); counter2 ++)
                {
                    System.out.print(", ");
                    System.out.print(output.get(counter2));
                }
                System.out.print("!");
            }
            
            menuOptions();
        }
        
        else if(s1.equals(choices[1]))
        {
            System.out.println("================");
            
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Find an Anagram of ____ (Format: abcdefg...)");
            String input = keyboard.next();
            ArrayList<String> output = pw.findAnagrams(input);
            
            if(output.size() > 0)
            {
                System.out.println("Some Anagrams of '" + input + "' are: ");
                for(int counter = 0; counter < output.size(); counter ++)
                {
                    System.out.println(output.get(counter));
                }
            }
            else
            {
                System.out.println("There are no Anagrams of '" + input + "' ...");
            }
            
            keyboard.close();
            
            menuOptions();
        }
        
        else if(s1.equals(choices[2]))
        {
            System.out.println("================");
            
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the Letters you're looking to make a Word from: (Format: abcdefg...)");
            String input = keyboard.next();
            ArrayList<String> output = pw.allWordsWithLetters(input);
            if(output.size() > 0)
            {
                System.out.println("Some Words that have '" + input + "' in them are: ");
                for(int counter = 0; counter < output.size(); counter ++)
                {
                    System.out.println(output.get(counter));
                }
            }
            else
            {
                System.out.println("There are no Words that have '" + input + "' in them...");
            }
            keyboard.close();
            
            menuOptions();
        }
        
        else if(s1.equals(choices[3]))
        {
            System.out.println("================");
            
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the Segment of Letters you want to make a Word from: (Format: abcdefg...)");
            String input = keyboard.next();
            ArrayList<String> output = pw.wordsWithSection(input);
            System.out.println("Some Words that have '" + input + "' in them are: ");
            if(output.size() > 0)
            {
                System.out.println("Some Words that have '" + input + "' in them are: ");
                for(int counter = 0; counter < output.size(); counter ++)
                {
                    System.out.println(output.get(counter));
                }
            }
            else
            {
                System.out.println("There are no Words that have '" + input + "' in them...");
            }
            keyboard.close();
            
            menuOptions();
        }
        
        else if(s1.equals(choices[4]))
        {
            System.out.println("================");
            
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the prefix you want to start a word with: (Format: abcdefg...)");
            String input = keyboard.next();
            ArrayList<String> output = pw.startsWith(input);
            if(output.size() > 0)
            {
                System.out.println("Some Words that start with '" + input + "' are: ");
                for(int counter = 0; counter < output.size(); counter ++)
                {
                    System.out.println(output.get(counter));
                }
            }
            else
            {
                System.out.println("There are no Words that start with '" + input + "' ...");
            }
            keyboard.close();
            
            menuOptions();
        }
        
        else if(s1.equals(choices[5]))
        {
            System.out.println("================");
            
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the suffix you want to end a word with: (Format: abcdefg...)");
            String input = keyboard.next();
            ArrayList<String> output = pw.endsWith(input);
            if(output.size() > 0)
            {
                System.out.println("Some Words that end with '" + input + "' are: ");
                for(int counter = 0; counter < output.size(); counter ++)
                {
                    System.out.println(output.get(counter));
                }
            }
            else
            {
                System.out.println("There are no Words that end with '" + input + "' ...");
            }
            keyboard.close();
            
            menuOptions();
        }
        
        else if(s1.equals(choices[6]))
        {
            System.out.println("================");
            
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the ArrayList<Character> you want to check is a Word (Format: abcdefg...)");
            boolean output = pw.isAWord(keyboard.next());
            keyboard.close();
            if(output)
            {
                System.out.println("Yes, yes it is!");
            }
            else
            {
                System.out.println("*****");
                System.out.println("Sodium Bromate");
                for(int counter = 0; counter < 3; counter ++)
                {
                    System.out.print(".");
                    Thread.sleep(1000);
                }
                System.out.println("");
                System.out.println("NaBrOOO");
                System.out.println("*****");
            }
            
            menuOptions();
        }
        
        else if(s1.equals(choices[7]))
        {
            System.out.println("================");
            
            Ghost ghost = new Ghost();
        }
        
        else if(s1.equals(choices[8]))
        {
            JOptionPane.showMessageDialog(null,":(", "D:", JOptionPane.PLAIN_MESSAGE, null);    
            System.exit(0);
        }
    }
}
