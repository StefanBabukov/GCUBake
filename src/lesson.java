import java.util.*;  

class Lesson
{
    private String name;
    public int numberOfLessons;
    public Dictionary<String, String> schedule = new Hashtable<String, String>();

    public Lesson(String name)
    {
        this.numberOfLessons = 15;
        this.name = name;
    }

    public Dictionary<String, String> getSchedule(){

        System.out.println(this.schedule.toString());
        return this.schedule;
        // System.out.println(this.schedule);
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
