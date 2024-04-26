/**
 * Write a description of class Horse here.
 * This class represents a horse in the race. It contains fields for the name of the horse, the symbol that represents the horse.
 * 
 * @author  Daria Gorbunova
 * @version 20-04-2024
 */
public class Horse
{
    //Fields of class Horse, set to private, so they can only be accessed by methods of the class
    private String name;
    private char symbol;
    private int distanceTravelled;
    private boolean hasFallen;
    private double confidence;
    private String colour;
    private String accesory;
    
    
    
      
    //Constructor of class Horse
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        this.symbol = horseSymbol;
        this.name = horseName;
        this.confidence = horseConfidence;
        this.distanceTravelled = 0;
        this.hasFallen = false;
        this.colour=null;
        this.accesory=null;
       
    }
    
    
    
    //Other methods of class Horse
    public void fall()
    {
        this.hasFallen = true;
    }
    
    public double getConfidence()
    {
        return this.confidence;
    }
    
    public int getDistanceTravelled()
    {
        return this.distanceTravelled;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public char getSymbol()
    {
        return this.symbol;
    }
    
    public void goBackToStart()
    {
        this.distanceTravelled = 0;
    }
    
    public boolean hasFallen()
    {
        return this.hasFallen;
    }

    public void moveForward()
    {
        this.distanceTravelled= this.distanceTravelled + 1;
    }

    public void setConfidence(double newConfidence)
    {
        this.confidence = newConfidence;
    }
    
    public void setSymbol(char newSymbol)
    {
        this.symbol = newSymbol;
    }

    public void setColour(String newColour)
    {
        this.colour = newColour;
    }

    public void setAccesory(String newAccesory)
    {
        this.accesory = newAccesory;
    }

    public String getColour()
    {
        return this.colour;
    }

    public String getAccesory()
    {
        return this.accesory;
    }

    
    
}