import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * Send you to the Main Menu
 * 
 * @author Andrew Wang
 * @version 2.0 probably
 */
public class Runner
{
    public static void main(String[] args) throws Exception
    {
        /*
        ProcessWords process = new ProcessWords();
        process.removeRepeats();
        */
        
        System.out.println("\f");
        MainMenu menu = new MainMenu();
        menu.menuOptions();
    }
}
