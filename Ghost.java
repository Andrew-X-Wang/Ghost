import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * Plaaaaaayyyyy GHOST!!
 * 
 * @author Andrew Wang
 * @version aVeryBigNumber
 */
public class Ghost
{
    int numOfPlayers;

    /**
     * Constructor for objects of class Ghost (the Game)
     * Offers choices for the User
     */
    public Ghost() throws Exception
    {
        String[] choices = 
            {
                "Play Computer",
                "Play People",
                "Make a Complaint",
                "Read Rules",
            };
            
            String s1 = (String)JOptionPane.showInputDialog(
        
                null, //could specify location in frame
                "What would you like to do?", //the question
                "GHOST!", //the frame title
                JOptionPane.PLAIN_MESSAGE, //the icon type
                null, //an icon you select
                choices, //array of options
                choices[0]); //the default to be selected
                
             if(s1.equals(choices[0]))
             {
                playComputer();
             }
             
             else if(s1.equals(choices[1]))
             {
                Scanner keyboard = new Scanner(System.in);
                System.out.println("Enter the number of players");
                playPeople(keyboard.nextInt());
                keyboard.close();
             }
             else if(s1.equals(choices[2]))
             {
                 complaint();
                 Ghost ghost = new Ghost();
             }
             else if(s1.equals(choices[3]))
             {
                readRules();
                Ghost ghost = new Ghost();
             }
    }

    /**
     * Choices for playing against Computer: Start Game, Read Rules, or Make a Complaint
     * 
     * @param none
     * @return none
     * Called by Ghost constructor and itself
     */
    public void playComputer() throws Exception
    {
        String[] choices = 
        {
            "Start Game",
            "Read Rules",
            "Make a Complaint",
        };
        
        String s1 = (String)JOptionPane.showInputDialog(
        
                null, //could specify location in frame
                "What would you like to do?", //the question
                "Play Computer!", //the frame title
                JOptionPane.PLAIN_MESSAGE, //the icon type
                null, //an icon you select
                choices, //array of options
                choices[0]); //the default to be selected
                
        if(s1.equals(choices[1]))
        {
            readRules();
            playComputer();
        }
        else if(s1.equals(choices[0]))
        {
            startCGame();
        }
        else if(s1.equals(choices[2]))
        {
            complaint();
            playComputer();
        }
    }
    
    /**
     * Choices for playing against People: Start Game, Read Rules, or Make a Complaint
     * 
     * @param nop - the number of Players
     * @return none
     * Called by Ghost constructor and itself
     */
    public void playPeople(int nop) throws Exception
    {
        numOfPlayers = nop;
        String[] choices = 
        {
            "Start Game",
            "Read Rules",
            "Make a Complaint",
        };
        
        String s1 = (String)JOptionPane.showInputDialog(
        
                null, //could specify location in frame
                "What would you like to do?", //the question
                "Multiplayer Mode: " + nop + "Player Game!", //the frame title
                JOptionPane.PLAIN_MESSAGE, //the icon type
                null, //an icon you select
                choices, //array of options
                choices[0]); //the default to be selected
                
        if(s1.equals(choices[1]))
        {
            readRules();
            playPeople(numOfPlayers);
        }
        else if(s1.equals(choices[0]))
        {
            startPGame();
        }
        else if(s1.equals(choices[2]))
        {
            complaint();
            playPeople(numOfPlayers);
        }
    }
    
    /**
     * Reads from Rules.txt and prints out the Rules for the User to read
     * 
     * @param none
     * @return none
     * Called by Ghost constructor, playComputer(), and playPeople()
     */
    public void readRules() throws IOException
    {
        FileReader fr = new FileReader("Files/Rules.txt");
        Scanner scanner = new Scanner(fr);
        while(scanner.hasNext())
        {
            System.out.print(scanner.nextLine());
            System.out.println("");
        }
    }
    
    /**
     * Starting up a Game against the Computer: prints current Ghost before each Turn
     * 
     * @param none
     * @return none
     * Called by playComputer()
     */
    public void startCGame() throws Exception
    {
        System.out.println("****************");
        System.out.println("GAME START!");
        //Runtime.getRuntime().exec("say -v Susan " + "Game Start!");
        Thread.sleep(1000);
        
        String word = new String();
        boolean cont = true;
        while(cont)
        {
            if(word.equals(""))
                {
                    //if there isn't an input yet don't print out the Ghost
                }
                else
                {
                    System.out.println("The current Ghost is: " + word.toUpperCase());
                }
                
            word = personCTurn(word);   //Player's Turn
            
            System.out.println("The current Ghost is: " + word.toUpperCase());
            word = computerTurn(word);  //Computer's Turn
        }
    }
    
    /**
     * Starting up a Multiplayer Game: prints current Ghost before each User's Turn
     * 
     * @param none
     * @return none
     * Called by playPeople()
     */
    public void startPGame() throws Exception
    {
        System.out.println("****************");
        System.out.println("GAME START!");
        //Runtime.getRuntime().exec("say -v Susan " + "Game Start!");
        
        String word = new String();
        boolean cont = true;        //boolean cont isn't actually used / changed anywhere else, it's just to keep the Game going
        while(cont)
        {
            for(int counter = 0; counter < numOfPlayers; counter ++)
            {
                if(word.equals(""))
                {
                    //if there isn't an input yet don't print out the Ghost
                }
                else
                {
                    System.out.println("The current Ghost is: " + word.toUpperCase());
                }
                word = personPTurn(word, counter);
            }
        }
    }
    
    /**
     * User's Turn in a Multiplayer Game: choices to Add a Letter, Challenge, or Make a Complaint
     * 
     * @param word - the Word segment that's currently in play
     * @param playerNumber - the User's Turn index
     * @return word - the new Word segment that's in play
     * Called by startPGame() and itself
     */
    public String personPTurn(String word, int playerNumber) throws Exception
    {
        System.out.println("------------");
        System.out.println("Player " + (playerNumber + 1) + ": Your Turn!"); // index + 1 = real playerNumber
        String[] choices = 
        {
            "Add a Letter",
            "Challenge: That doesn't make a Word!",
            "Challenge: That's already a Word!",
            "Make a Complaint",
        };
        
        String s1 = (String)JOptionPane.showInputDialog(
        
                null, //could specify location in frame
                "What would you like to do?", //the question
                "Player " + (playerNumber + 1) + "'s Turn:", //the frame title
                JOptionPane.PLAIN_MESSAGE, //the icon type
                null, //an icon you select
                choices, //array of options
                choices[0]); //the default to be selected
                
           
        
        if(s1.equals(choices[0]))
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("What letter would you like to add?");
            String input = keyboard.next();
            if(input.length() != 1)
            {
                System.out.println("One Letter at a time please!");
                personPTurn(word, playerNumber);
            }
            word = word + input;
            keyboard.close();
        }
        else if(s1.equals(choices[1]))          //if notWordChallenge is true means it does make a word; challenge is lost
        {
            boolean challengeLost = notWordChallenge(word, playerNumber - 1); //challengeLost is true if exists is true
            if(challengeLost) //!exists
            {
                System.out.println("Challenge Lost!");
                lose(playerNumber);
            }
            else //exists
            {
                System.out.println("Challenge Won!");
                lose(playerNumber + 1);
            }
        }
        else if(s1.equals(choices[2]))
        {
             boolean challengeWon = alreadyWordChallenge(word, playerNumber - 1); //challengeWon is true if isAWord is true
             if(challengeWon) //isAWord
             {
                System.out.println("Challenge Won!");
                lose(playerNumber - 1);
             }
             else //!isAWord
             {
                System.out.println("Challenge Lost!");
                lose(playerNumber);
             }
        }
        else if(s1.equals(choices[3]))
        {
            complaint();
            personPTurn(word, playerNumber);
        }
        
        return word;
    }
    
    /**
     * User's Turn in a Game against the Computer: choices to Add a Letter, Challenge, or Submit a Complaint
     * 
     * @param word - the Word segment that's currently in play
     * @return word - the new Word segment that's in play
     * Called by startCGame() and itself
     */
    public String personCTurn(String word) throws Exception
    {
        System.out.println("------------");
        System.out.println("Player: Your Turn!"); 
        //Runtime.getRuntime().exec("say -v Susan " + "Your Turn!");
        Thread.sleep(1000);
        
        String[] choices = 
        {
            "Add a Letter",
            "Challenge! That's already a Word!",
            "Submit a Complaint",
        };
        
        String s1 = (String)JOptionPane.showInputDialog(
        
                null, //could specify location in frame
                "What would you like to do?", //the question
                "Player's Turn:", //the frame title
                JOptionPane.PLAIN_MESSAGE, //the icon type
                null, //an icon you select
                choices, //array of options
                choices[0]); //the default to be selected
                
           
        
        if(s1.equals(choices[0]))
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("What letter would you like to add?");
            //Runtime.getRuntime().exec("say -v Susan " + "What letter would you like to add?");
            String input = keyboard.next();
            if(input.length() != 1)
            {
                System.out.println("One Letter at a time please!");
                //Runtime.getRuntime().exec("say -v Susan " + "One letter at a time please!");
                personCTurn(word);
            }
            word = word + input;
            keyboard.close();
        }
        else if(s1.equals(choices[1]))
        {
            ProcessWords pw = new ProcessWords();
            boolean challengeWon = pw.isAWord(word);    //if it is a Word, challengeWon and Computer loses, otherwise !challengeWon and User loses
            if(challengeWon)
            {
                System.out.println("****************");
                System.out.println("The Program calls me Player 2, but ignore that. Andrew's just being too lazy to create another method.");
                //Runtime.getRuntime().exec("say -v Susan " + "The Program calls me Player 2, but ignore that. Andrew's just being too lazy to create another method.");
                Thread.sleep(7000);
                
                System.out.println("Player: ... but that's not really the Computer talking is it... it's just Andrew telling the Program to tell the User he's lazy...");
                //Runtime.getRuntime().exec("say -v Daniel " + "but that's not really the Computer talking is it... it's just Andrew telling the Program to tell the User he's lazy");
                Thread.sleep(7500);
                
                System.out.println("Warning: Please Do Not Touch The Fourth Wall. It Is Fragile And Is Easily Broken.");
                //Runtime.getRuntime().exec("say -v Yuri " + "Warning: Please Do Not Touch The Fourth Wall. It Is Fragile And Is Easily Broken.");
                Thread.sleep(6500);
                
                for(int counter = 0; counter < 3; counter ++)
                {
                    System.out.print(".");
                    Thread.sleep(500);
                }
                System.out.println("Almost as Fragile as the Program.");
                //Runtime.getRuntime().exec("say -v Yuri " + "Almost as Fragile as the Program.");
                Thread.sleep(4000);
                
                System.out.println("Thank you.");
                //Runtime.getRuntime().exec("say -v Yuri " + "Thank you.");
                Thread.sleep(1500);
                
                lose(1);    //Player 2 (index 1 but not really) is the Computer
            }
            else
            {
                lose(0);    //Player 1 (index 0 but not reall) is the User
            }
        }
        else if(s1.equals(choices[2]))
        {
            complaint();
            personCTurn(word);
        }
        
        return word;
    }
    
    /**
     * Computer's Turn in a Game against the Computer: check's if the User has lost, then chooses what Letter to add to the segment currently in play
     * 
     * @param word - the Word segment that's currently in play
     * @return word + letter - the new Word segment that's in play
     * Called by startCGame() and itself
     */
    public String computerTurn(String word) throws Exception
    {
        System.out.println("------------");
        System.out.println("Player 2's (Computer's) Turn!");
        //Runtime.getRuntime().exec("say -v Susan " + "My turn!");
        Thread.sleep(1000);
        String thinking = "Thinking";
        //Runtime.getRuntime().exec("say -v Susan " + thinking);
        for(int counter = 0; counter < thinking.length(); counter ++)
        {
            System.out.print(thinking.charAt(counter));
            Thread.sleep(50);
        }
        for(int counter2 = 0; counter2 < 3; counter2 ++)
        {
            System.out.print(".");
            Thread.sleep(1000);
        }
        System.out.println("");
        
        ghostLetter methodCaller = new ghostLetter('a', 0);
        
        char letter = methodCaller.chooseLetter(word);      //chooses the Letter
        if(letter == 0)
        {
            lose(0);    //User (index 0 kinda not really) loses if the segment can't make a Word
        }
        if(letter == 1)
        {
            lose(1);    //Computer (index 1 kinda not really) loses if it can't find viable Letters to use  //I don't think this is applicable anymore because the Computer is allowed to add Letters that make Words if there are no other options
        }
        return word + letter;
    }
    
    /**
     * Challenge by User in a Multiplayer Game: bounces back to the previous User and checks if the segment currently in play can make a Word
     * 
     * @param prefix - the Word segment that's currently in play, the one that's being Challenged on whether or not it can make a Word
     * @param playerNumber - the Turn index of the User that is being Challenged
     * @return boolean exists - whether or not the Challenge has been won (if exists then Challenge lost)
     * Called by personPTurn()
     */
    public boolean notWordChallenge(String prefix, int playerNumber) throws IOException
    {
        System.out.println("------------");
        if(playerNumber > 0)
        {
            System.out.println("Player " + (playerNumber + 1) + ": You have been Challenged by Player " + (playerNumber + 2)); //index + 1 = real playerNumber
        }
        else
        {
            System.out.println("Player " + numOfPlayers + ": You have been Challenged by Player " + (playerNumber + 2)); //if index == 0 then the last Player is being Challenged
        }
        
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What is the Word you were trying to make?");
        String possWord = keyboard.next();
        ProcessWords pw = new ProcessWords();
        return pw.checkPrefix(prefix, possWord);
    }
    
    /**
     * Challenge by User in a Multiplayer Game: bounces back to the previous User and checks if the segment currently in play is already a Word
     * 
     * @param word - the Word segment that's currently in play, the one that's being Challenged on whether or not it is already a Word
     * @param playerNumber - the Turn index of the User that is being Challenged
     * @return boolean exists - whether or not the Challenge has been won (if exists then Challenge won)
     * Called by personPTurn()
     */
    public boolean alreadyWordChallenge(String word, int playerNumber) throws IOException
    {
        System.out.println("------------");
        if(playerNumber >= 0)
        {
            System.out.println("Player " + (playerNumber + 2) + ": You have Challenged Player " + playerNumber + 1); //index + 1 = real playerNumber
        }
        else
        {
            System.out.println("Player " + (playerNumber + 2) + ": You have Challenged Player " + numOfPlayers); //if index == 0 then the last Player is being Challenged
        }
        ProcessWords pw = new ProcessWords();
        return pw.isAWord(word);
    }
    
    /**
     * Allows User to file a Complaint about if there's a Word that's not listed in AllWords.txt; adds to a text file labeled Complaint.txt
     * 
     * @param none
     * @return none
     * Called by Ghost constructor, playComputer(), playPeople(), personPTurn(), personCTurn(), and lose()
     */
    public void complaint() throws IOException
    {
        FileReader fr = new FileReader("Files/Complaints.txt");
        Scanner fileScanner = new Scanner(fr);
        ArrayList<String> complaintList = new ArrayList<String>();
        while(fileScanner.hasNext())
        {
            complaintList.add(fileScanner.nextLine());
        }
        fileScanner.close();
        fr.close();
        
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What Word isn't listed?");
        //Runtime.getRuntime().exec("say -v Susan " + "What word isn't listed?");
        String complaint = keyboard.nextLine();
        complaintList.add(complaint);
        keyboard.close();
        
        PrintWriter pw = new PrintWriter("Files/Complaints.txt");
        for(int counter = 0; counter < complaintList.size(); counter ++)
        {
            pw.println(complaintList.get(counter));
        }    
        pw.close();
        
        System.out.println("Complaint Made: Added '" + complaint + "' to Complaints.txt");
        //Runtime.getRuntime().exec("say -v Susan " + "Complaint Made: Added '" + complaint + "' to Complaints.txt");
    }
    
    /**
     * Endgame for when either a User or the Computer loses (doesn't have a specific one for Computer; just labels it as Player 2)
     * 
     * @param playerNumber - the Turn index of the User that has Lost
     * @return none
     * Called by personPTurn(), personCTurn(), and computerTurn();
     */
    public void lose(int playerNumber) throws Exception
    {
        System.out.println("--------------");
        System.out.println("--------------");
        if(playerNumber >= 0)
        {
            System.out.println("Player " + (playerNumber + 1) + ": You Have Lost!"); //index + 1 = real playerNumber
            //Runtime.getRuntime().exec("say -v Susan " + "Player " + (playerNumber + 1) + ": You Have Lost!");
            
        }
        else
        {
            System.out.println("Player " + numOfPlayers + ": You Have Lost!"); //if -1 then last Player loses
            //Runtime.getRuntime().exec("say -v Susan " + "Player " + numOfPlayers + ": You Have Lost!");
        }
        Thread.sleep(2000);
        //Runtime.getRuntime().exec("say -v Susan " + "Good game!");
        
        String[] choices = 
        {
            "Start Over",
            "Quit Game",
            "Submit a Complaint",
        };
        
        String s1 = (String)JOptionPane.showInputDialog(
        
                null, //could specify location in frame
                "What would you like to do?", //the question
                "GAME OVER", //the frame title
                JOptionPane.PLAIN_MESSAGE, //the icon type
                null, //an icon you select
                choices, //array of options
                choices[0]); //the default to be selected
        
        if(s1.equals(choices[0]))
        {
            Ghost ghost = new Ghost();
        }
        else if(s1.equals(choices[1]))
        {
            JOptionPane.showMessageDialog(null,"Have a nice Trip! See you next Fall!", "END", JOptionPane.PLAIN_MESSAGE, null);    
            System.exit(0);
        }
        else if(s1.equals(choices[2]))        
        {
            complaint();
            JOptionPane.showMessageDialog(null,"Have a nice Trip! See you next Fall!", "END", JOptionPane.PLAIN_MESSAGE, null);    
            System.exit(0);
        }
    }
}
