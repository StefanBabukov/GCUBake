
/**
 * Write a description of class Customer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Customer
{
    // instance variables - replace the example below with your own
    private int x;
    private Lesson lesson;
    private String name;
    private int age;
    private String status; 
    public int lessonsAttended;
    /**
     * Constructor for objects of class Customer
     */
    public Customer(String name, int age)
    {
        // initialise instance variables
        this.lesson = new Lesson("None");
        this.lessonsAttended = 0;
        this.name = name;
        this.age = age;        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void bookLesson(String lessonName){
        if(this.lessonsAttended == 0){
            this.status = "Beginner";
        }
        
        // book a lesson
    }
    
    public void completeLesson(String lessonName){
        //Check if theres a booked lesson for today
        this.lessonsAttended++;
        if (this.lessonsAttended == this.lesson.numberOfLessons){
            this.status = "Star-Baker";
        }
        else{
            this.status = "On-going";
        }
    }
    
    public void finishLesson(String lessonName){
        this.status = "Not-complete";
    }
    
    public void joinLesson(String name){
        if( name.equals("Cakes") || name.equals("Pies") || name.equals("Muffins")){
            lesson.setName(name);
        }
        else{
            System.out.println("No such lesson!");
        }
    }
}
