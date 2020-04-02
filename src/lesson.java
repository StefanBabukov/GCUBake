import java.util.*;  

class Lesson{
    private String name;
    public int numberOfLessons;
    public String available_chefs = new String();
    public Lesson(String name)
    {
        this.numberOfLessons = 15;
        this.name = name;
    }
    public Lesson() {
    	
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
