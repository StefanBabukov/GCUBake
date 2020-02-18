
/**
 * A class, which defines the course lessons available.
 * 
 * @author Stefan Babukov
 * @version (a version number or a date)
 */
import java.util.*;  

public class Lesson
{
    // instance variables - replace the example below with your own
    private int x;
    private String name;
    public int numberOfLessons;
    
    /**
     * Constructor for objects of class Lesson
     */
    public Lesson(String name)
    {
        this.numberOfLessons = 15;
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public static void main(String args[]){
        
    }

}
