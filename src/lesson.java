import java.util.*;  

class Lesson{
    private String name;
    public int numberOfLessons;
    public String available_chefs = new String();
    private int LessonID;
    public Lesson() {
    	
    }
    public int get_id() {
    	return this.LessonID;
    }
    public String getName(){
        return this.name;
    }
    public Lesson getLessonFromSQL(int id) {
    	Lesson returnLesson = new Lesson();
    	SQLconnection connection = new SQLconnection();
    	
    	returnLesson.name = connection.get_data("lesson", "NAME", "lessonID", Integer.toString(id), "String", "int").stringVar;
    	returnLesson.numberOfLessons = connection.get_data("lesson", "NUMBER_LESSONS", "lessonID", Integer.toString(id), "int", "int").integerVar;
    	returnLesson.LessonID = id;
    	
    	//populate available_chefs variable
    	
    	return returnLesson;
    }
    public void setName(String name){
        this.name = name;
    }
    public static void main(String args[]){
        
    }

}
