/**
*  Project 4 Fall2017 "Pitcher Stats Database Using Binary Tree"
*
*  Names: Robert Burch-Lau & Samuel Tregea
*
*  Professor: Dennis Venabule
*
*  Due Date: 29 November 2017
**/


import java.util.*;
import java.io.*;

@SuppressWarnings("LossyEncoding")
public class PitcherStatsTree
{
    private static TreeBag<Pitcher> tree = new TreeBag<Pitcher>();
    private static PrintWriter writer;
    private static final String FILE = "C:/Users/Owner/Desktop/pitcherstats.csv";
    
    /**
    * main method. Sends file name to factory method for tree creation
    * Runs menu
    * @param args
    **/
    public static void main(String [] args)
    {
        tree = Factory(FILE);
        menu();
    }

    /**
    * Menu for program functions. Allows interaction with the created tree (add, remove, display)
    * Also can save updated tree to file
    **/
    public static void menu()
    {
        Scanner sc = new Scanner(System.in);
        int response;



        while(true)
        {
            System.out.println("\n1.\tDisplay listing to screen of all pitcher's stats (ordered by last name) ");
            System.out.println("2.\tDisplay the pitchers in current tree format");
            System.out.println("3.\tFind and display one individual pitcher's stats ");
            System.out.println("4.\tUpdate an individual pitcher's stats (by adding results of a new game)");
            System.out.println("5.\tRemove a pitcher from the Database");
            System.out.println("6.\tAdd a new pitcher to the Database ");
            System.out.println("7.\tQuit and update datafile");
            response = sc.nextInt();
            
            /**
            *  When user enters '1' from the menu, the static TreeBag object "tree" uses
            *  It's display function call. 
            **/
            if(response == 1)
            {
                tree.display();
            }
            /**
            *  When user enters '2' from the menu, the static TreeBag object "tree" uses
            *  It's displayAsTree function call. 
            **/
            else if(response == 2)
            {
                tree.displayAsTree();
            }
            /**
            *  When user enters '3' from the menu, they will be prompted to search for a player
            *  A String array is created, then placed into a Pitcher() class constructor.
            *  When the pitcher is found in the binary tree, the tree object will call
            *  its retrieve() method then display the specified pitcher.
            **/
            else if (response == 3)
            {
                System.out.println("Please enter the name of the pitcher you seek in the form: Last Name,First Name (no spaces)");
                String[] searchName = sc.next().split(",");
                Pitcher temp = new Pitcher(searchName[0], searchName[1], "noteam");
                Pitcher found = tree.retrieve(temp);
                if(found != null)
                    System.out.println(found.toString());
                else
                    System.out.println("The pitcher was not found");
            }
            /**
            *  When user enters '4' from the menu, they will be prompted to remove a specified pitcher
            *  Using a similar syntactical structure as menu option #3.
            *  After a player is selected, the user will be prompted by set questions.
            *  When the user is finished, the selected pitchers stats are then updated.
            **/
            else if(response == 4)
            {

                System.out.println("Please enter the name of the pitcher you want to add a game to in the form: Last Name,First Name (no spaces)");
                String[] searchName = sc.next().split(",");
                Pitcher temp = new Pitcher(searchName[0], searchName[1], "noteam");
                Pitcher found = tree.retrieve(temp);
                if(found != null)
                {
                    boolean result = false;
                    found.setGamesPitched(found.getGamesPitched() + 1);

                    System.out.println("Did they win?: (y/n)");
                    if (sc.next().equals("y"))
                        found.setGamesWon(found.getGamesWon() + 1);

                    System.out.println("How many runs did they earn?");
                    found.setEarnedRuns(found.getEarnedRuns() + sc.nextInt());

                    System.out.println("How many hits did they get?");
                    found.setHits(found.getHits() + sc.nextInt());


                    System.out.println("How many innings did they play?");
                    found.setInningsPitched(found.getInningsPitched() + sc.nextInt());

                    System.out.println("Did they get a shutout?: (y/n)");
                    if (sc.next().equals("y"))
                        found.setShutouts(found.getShutouts() + 1);

                    System.out.println("How many strikeouts did they get?");
                    found.setStrikeouts(found.getStrikeouts() + sc.nextInt());

                    found.setERA((found.getEarnedRuns() / found.getInningsPitched()) * 9);
                }
                else
                    System.out.println("The pitcher was not found");

            }
            /**
            *  When user enters '5' from the menu, they will be prompted to search for a player
            *  A String array is created, then placed into a Pitcher() class constructor.
            *  When the pitcher is found in the binary tree, the tree object will call
            *  Its retrieve() method to find the specified pitcher, and then will
            *  Remove the pitcher by calling the TreeBag funciton remove().
            **/
            else if(response == 5)
            {
                System.out.println("Please enter the name of the pitcher you seek in the form: Last Name,First Name (no spaces)");
                String[] searchName = sc.next().split(",");
                Pitcher temp = new Pitcher(searchName[0], searchName[1], "noteam");
                Pitcher found = tree.retrieve(temp);//Retreiving the selected pitcher
                if(found != null)
                {
                    tree.remove(temp);//Pitcher is then removed from the file
                    System.out.println("The selected pitcher has been removed from the file.");
                }
                else
                    System.out.println("The selected pitcher does not exist in the file.");

            }
            /**
            *  When user enters '6' from the menu, they will be prompted to search for a player
            *  A String array is created, then placed into a Pitcher() class constructor.
            *  the tree object will call its add() function and will then add a Player to the database
            **/
            else if (response == 6)
            {
                System.out.println("Please enter the name of the pitcher you seek in the form: Last Name,First Name,Team (Team ex.: BRS, NYY, etc) (no spaces)");
                String[] searchName = sc.next().split(",");
                Pitcher temp = new Pitcher(searchName[0], searchName[1], searchName[2]);//searchName[0] = Last Name
                tree.add(temp);                                                         //searchName[1] = First Name  
            }                                                                           //searchName[2] = Team Name
            /**
            *  When user enters '7' from the menu, the program will save all changes
            *  And then close. The CSV file will automatically be updated upon completion.
            **/
            else if(response == 7)
            {
                saveStats(FILE);
                System.out.println("Good-bye!");
                break;
            }
            /**
            *  Used incase user inputs a number that is not in range of the menu (0, 8, 25, etc)
            **/
            else
                System.out.println("Please enter a valid number");

        }
    }

    /**
    *  Creates tree out of CSV file, exits the program if the file is empty
    *  @param file -String of file location
    *  @return TreeBag - a tree of Pitcher objects
    *  @exception FileNotFoundException caught if file is not in location.
    *  @postcondition -Tree created if file was found
    **/
    public static TreeBag<Pitcher> Factory(String file)
    {
        File f = new File(file);

        // access the file
        Scanner scn = null;
        try
        {
            scn = new Scanner(f);
        }
        catch (FileNotFoundException e)
        {
            System.out.printf("Specified file was not found: %s", file);
            return null;
        }

        // read the file
        if(!scn.hasNext()) {
            System.out.println("The file was empty, please check your file and try again");
            System.exit(0);
        }
        scn.nextLine();
        while (scn.hasNext())
        {
            String line = scn.nextLine();
            tree.add(new Pitcher(line));
        }

        return tree;
    }


    /**
    *  Opens a writer and overwrites the file with the current data in the tree then closes the file
    *  @precondition file exists
    *  @postcondition file is now filled with all the tree's data in alphabetical order
    *  @param fileName
    **/
    private static void saveStats(String fileName)
    {
        try {
            String file = fileName;
            writer = new PrintWriter(file);
            BTNode root = tree.getRoot();
            inorderWriter(root);
            writer.close();
            System.out.println("File Saved");
        }
        catch (FileNotFoundException e) {
            System.out.println("The file was not found");
        }
    }

    /**
    *  Traverses the tree and writes to the file
    *  @param node
    **/
    private static void inorderWriter(BTNode node)
    {
        if (node.getLeft() != null)
            inorderWriter(node.getLeft());
        writer.append(Pitcher.toCSV((Pitcher)node.getData()) + "\n");
        if (node.getRight() != null)
            inorderWriter(node.getRight());
    }


}//PitcherStatsTree