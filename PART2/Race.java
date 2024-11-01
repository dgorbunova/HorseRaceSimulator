import javax.swing.JTextArea;

import java.lang.Math;
import java.util.concurrent.TimeUnit;



/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McFarewell
 * @version 1.0
 */
public class Race
{
    
    private int raceLength;
    private Horse[] horses;
    private int horseCount;
    private int laneCount;


    
    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance, int count, int lanes)
    {
        // initialise instance variables
        raceLength = distance;
        horseCount = count;
        laneCount = lanes;
        horses = new Horse[laneCount];

    }
    
    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber)
    {
        horses[laneNumber-1] = theHorse;
    }

    
    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public boolean startRace(JTextArea textArea)
    {
        //declare a local variable to tell us when the race is finished
        boolean finished = false;
        
        //reset all the lanes (all horses not fallen and back to 0). 
        for (int i = 0; i < laneCount; i++)
        {
            if (horses[i] != null)
            {
                Horse theHorse = horses[i];
                theHorse.goBackToStart();
            }
        }
                  
        while (!finished)
        {
            for (int i = 0; i < laneCount; i++)
            {
                if (horses[i] != null)
                {
                    Horse theHorse = horses[i];
                    moveHorse(theHorse);
                }
            }
            String winner = "";
            
            printRace(textArea);
            
            
            for (int i=0; i<horseCount; i++){
                Horse theHorse = horses[i];
                if ((theHorse!=null)&&(raceWonBy(theHorse))){
                    finished = true;
                    winner=(theHorse.getName()).toUpperCase();
                    String rounded = String.format("%.2f",(theHorse.getConfidence()+0.1) );
                    theHorse.setConfidence(Double.parseDouble(rounded));
                    System.out.println("And the winner of the race is "+winner);
                } 
                
            }
            boolean allFallen = true;
            for (int i = 0; i < laneCount; i++)
            {
                if (horses[i] != null)
                {
                    Horse theHorse = horses[i];
                    if (!theHorse.hasFallen())
                    {
                        allFallen = false;
                    }
                }
            }

            if (allFallen)
            {
                finished = true;
                printRace(textArea);
                System.out.println("There is no winner, as all horses have fallen!");
            }
            
            //wait for 100 milliseconds
            try{ 
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(Exception e){}
            try {
                Thread.sleep(100); // Simulate race progress
                
                
            } catch (InterruptedException e) {}
        }
       return true;
    }
    
    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
        
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
               theHorse.moveForward();
            }
            
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.05*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
                double confidence = theHorse.getConfidence();
                String rounded = String.format("%.2f",confidence-0.1);
                theHorse.setConfidence(Double.parseDouble(rounded));
                
            }
        } 
    }
    // 
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse)
    {
        if (theHorse.getDistanceTravelled() >= raceLength)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /***
     * Print the race on the terminal
     */
    private void printRace(JTextArea textArea)
    {

        //System.out.print("\033[H\033[2J");  //clear the terminal window
        //System.out.flush();
        textArea.setText("");
        multiplePrint('=',raceLength+3); //top edge of track
        System.out.println();

        for (int i = 0; i < laneCount; i++)
        {
                Horse theHorse = horses[i];
                printLane(theHorse);
                if (i != laneCount-1){
                    System.out.println();
                }
                
        }
        
        multiplePrint('=',raceLength+3); //bottom edge of track
        System.out.println();  
    }
    
    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse)
    {
        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = raceLength;
        int spacesAfter = 0;
        if (theHorse != null)
        {
            spacesBefore = theHorse.getDistanceTravelled();
            spacesAfter = raceLength - theHorse.getDistanceTravelled();
        }
        
        //print a | for the beginning of the lane
        System.out.print('|');
        
        //print the spaces before the horse
        multiplePrint(' ',spacesBefore);
        
        //if the horse has fallen then print dead
        //else print the horse's symbol
        if (theHorse != null)
        {
            if(theHorse.hasFallen()){
                System.out.print('X');
            } else {
            System.out.print(theHorse.getSymbol());
            }
        } else {
            System.out.print(' ');
        }
        
        
        
        //print the spaces after the horse
        multiplePrint(' ',spacesAfter);
        
        //print the | for the end of the track followed by the name of the horse and its confidence
        String message;
        if (theHorse == null)
        {
            message = "EMPTY LANE";
        } else if (theHorse.hasFallen())
        {
            message = ((theHorse.getName().toUpperCase())+" (Current Confidence: "+theHorse.getConfidence()+") OUT OF RACE");
        }
        else
        {
            message = ((theHorse.getName().toUpperCase())+" (Current Confidence: "+theHorse.getConfidence()+")");
        }
        
        System.out.println("|   "+message); 
        System.out.flush();
    }
        
    
    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }

    
}
