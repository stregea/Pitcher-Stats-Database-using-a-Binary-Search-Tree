/**
*  Project 4 Fall2017 "Pitcher Stats Database Using Binary Tree"
*
*  Names: Robert Burch-Lau & Samuel Tregea
*
*  Professor: Dennis Venabule
*
*  Due Date: 29 November 2017
**/

public class Pitcher implements Comparable<Pitcher>
{
    private String lastName;
    private String firstName;
    private String team;
    private int gamesPitched;
    private int gamesWon;
    private int shutouts;
    private double inningsPitched;
    private int hits;
    private int earnedRuns;
    private int strikeouts;
    private double winPCT;
    private double ERA;

    /**
    *  Constructor for a pitcher who maybe does not have any games played yet
    *  @param lName - last name
    *  @param fName -first name
    *  @param org - team they play for
    **/
    public Pitcher(String lName, String fName, String org){
        lastName = lName;
        firstName = fName;
        team = org;
    }

    /**
    *  Full pitcher constructor with all data
    *  @param line -takes a line from a csv file
    **/
    public Pitcher(String line)
    {
        String[] pData = line.split(",");
        lastName = pData[0];
        firstName = pData[1];
        team = pData[2];
        gamesPitched = Integer.parseInt(pData[3]);
        gamesWon = Integer.parseInt(pData[4]);
        winPCT = Double.parseDouble("0"+pData[5]);
        shutouts = Integer.parseInt(pData[6]);
        inningsPitched = Double.parseDouble(pData[7]);
        hits = Integer.parseInt(pData[8]);
        earnedRuns = Integer.parseInt(pData[9]);
        strikeouts = Integer.parseInt(pData[10]);
        ERA = earnedRuns / inningsPitched * 9;
    }

    /**
    *  returns pitchers last name
    *  @return lastName
    **/
    public String getLastName()
    {
        return lastName;
    }
   
    /**
    *  returns pitchers first name 
    *  @return firstName
    **/
    public String getFirstName()
    {
        return firstName;
    }
   
    /**
    *  returns pitchers team name 
    *  @return team
    **/
    public String getTeam()
    {
        return team;
    }
   
    /**
    *  returns the number of games pitched 
    *  @return geamsPitched
    **/
    public int getGamesPitched()
    {
        return gamesPitched;
    }
  
    /**
    *  returns the number of games won 
    *  @return geamsWon
    **/
    public int getGamesWon()
    {
        return gamesWon;
    }
    
    /**
    *  returns the number of shutouts 
    *  @return shutouts
    **/
    public int getShutouts()
    {
        return shutouts;
    }
   
    /**
    *  returns the number of innings pitched 
    *  @return inningsPitched
    **/
    public double getInningsPitched()
    {
        return inningsPitched;
    }
   
    /**
    *  returns the number of hits 
    *  @return hits
    **/
    public int getHits()
    {
        return hits;
    }
   
    /**
    *  returns the number of earned runs 
    *  @return earnedRuns
    **/
    public int getEarnedRuns()
    {
        return earnedRuns;
    }
   
    /**
    *  returns the number of strikeouts 
    *  @return strikeouts
    **/
    public int getStrikeouts()
    {
        return strikeouts;
    }
   
    /**
    *  returns the earned-runned-average (ERA)
    *  @return ERA
    **/
    public double getERA()
    {
        return ERA;
    }
   
    /**
    *  returns the win percentage 
    *  @return winPCT
    **/
    public double getWinPCT()
    {
        return winPCT;
    }
    
    /**
    *  Sets the last name
    *  @param lastName - String
    **/
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    /**
    *  Sets the first name
    *  @param firstName - String
    **/
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
   
    /**
    *  Sets the team
    *  @param team - String
    **/
    public void setTeam(String team)
    {
        this.team = team;
    }
   
    /**
    *  Sets the games pitched
    *  @param gamesPitched - int
    **/
    public void setGamesPitched(int gamesPitched)
    {
        this.gamesPitched = gamesPitched;
    }
    
    /**
    *  Sets the games won
    *  @param gamesWon - int
    **/
    public void setGamesWon(int gamesWon)
    {
        this.gamesWon = gamesWon;
    }
    
    /**
    *  Sets the shutouts
    *  @param shutouts - int
    **/
    public void setShutouts(int shutouts)
    {
        this.shutouts = shutouts;
    }
   
    /**
    *  Sets the innings pitched
    *  @param inningsPitched - double
    **/
    public void setInningsPitched(double inningsPitched)
    {
        this.inningsPitched = inningsPitched;
    }
    /**
    *  Sets the hits
    *  @param hits - int
    **/
    public void setHits(int hits)
    {
        this.hits = hits;
    }
    
    /**
    *  Sets the earned runs
    *  @param earnedRuns - int
    **/
    public void setEarnedRuns(int earnedRuns)
    {
        this.earnedRuns = earnedRuns;
    }
   
    /**
    *  Sets the strikeouts
    *  @param strikeouts - int
    **/
    public void setStrikeouts(int strikeouts)
    {
        this.strikeouts = strikeouts;
    }
   
    /**
    *  Sets the earned run average (ERA)
    *  @param ERA - double
    **/
    public void setERA(double ERA)
    {
        this.ERA = ERA;
    }


    /**
    *  Turns pitcher data into a string for output
    *  @return info -  a string of pitcher data
    **/
    @Override
    public String toString()
    {

            String info = "Pitcher: " + lastName + ", " + firstName
                    + ", Team: " + team
                    + ", Games Pitched: " + gamesPitched
                    + ", Games Won: " + gamesWon
                    + ", Shutouts: " + shutouts
                    + ", Innings Pitched: " + inningsPitched
                    + ", Hits: " + hits
                    + ", Earned Runs: " + earnedRuns
                    + ", Strikeouts: " + strikeouts
                    + ", ERA: " + ERA;
            return info;
    }
    /**
    *  Turns pitcher data into a string to save into CSV file
    *  @return info -  a string of pitcher data
    **/
    public static String toCSV(Pitcher pitcher)
    {
        String info = pitcher.getLastName() + "," + pitcher.getFirstName()
                + "," + pitcher.getTeam()
                + "," + pitcher.getGamesPitched()
                + "," + pitcher.getGamesWon()
                + "," + pitcher.getWinPCT()
                + "," + pitcher.getShutouts()
                + "," + pitcher.getInningsPitched()
                + "," + pitcher.getHits()
                + "," + pitcher.getEarnedRuns()
                + "," + pitcher.getStrikeouts();
        return info;
    }

    /**
    *  Finds if pitcher name is greater, less than, or equal to the target pitcher. Returns 1, -1, and 0 respectively.
    *  @param pitcher
    *  @return 1, -1, 0
    **/
    public int compareTo(Pitcher pitcher)
    {
        if (this.lastName.compareToIgnoreCase(pitcher.getLastName()) == 0)
            return this.firstName.compareToIgnoreCase(pitcher.getFirstName());
        else
            return this.lastName.compareToIgnoreCase(pitcher.getLastName());
    }

}
