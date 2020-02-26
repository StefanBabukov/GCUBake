
/**
 * Write a description of class Customer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Customer
{
    private int x;
    private Lesson lesson;
    private String name;
    private int age;
    private String status; 
    public int lessonsAttended;
 
    public Customer(String name, int age)
    {
        this.lesson = new Lesson("None");
        this.lessonsAttended = 0;
        this.name = name;
        this.age = age;        
    }


    public void bookLesson(String lessonName){
        if(this.lessonsAttended == 0){
            this.status = "Beginner";
        }
    }
    
    public void completeLesson(String lessonName, String stop = false){
        //Check if theres a booked lesson for today
        this.lessonsAttended++;
        if (stop){
            this.status = "Not-complete";
        }
        if (this.lessonsAttended == this.lesson.numberOfLessons){
            this.status = "Star-Baker";
        }
        else{
            this.status = "On-going";
        }

    }
    
    public void joinLesson(Lesson lesson){
        this.lesson = lesson;
    }
}
