//import java.util.*; 

class Chef
{
    // instance variables - replace the example below with your own
    public String username;
    public Customer student;
    public int chefID; 
    public int courseID;
    public int studentID;
	SQLconnection connection = new SQLconnection();

    public Chef(String name, int chefID){
        this.username = name;
        this.chefID = chefID;
    }
    public Chef() {
    	
    }

    public void signLesson(String courseName) {
		this.courseID = connection.get_data("lesson", "lessonID", "name", courseName, "int", "String").integerVar;
		connection.update_data("chefs", "courseID", this.courseID, "chefID", this.chefID, null);
		connection.update_data("chefs", "message", 0, "chefID", this.chefID, " ");

		String[] fields = new String[] {"lessonID", "name"};
    	String[] values = new String[] {"'"+this.courseID+"'", "'"+this.username+"'"};
		connection.set_data("available_chefs", fields, values);
		this.populateData(this.chefID);
    }
    public void cancelCourse() {
    	connection.update_data("students", "message", 0, "studentID", this.studentID, "Your chef cancelled the course!");
		connection.update_data("chefs", "courseID", 0, "chefID", this.chefID, null);
		connection.update_data("students", "status", 0, "studentID", this.studentID, "Not-complete");
		connection.update_data("students", "courseID", 0, "studentID", this.studentID, null);
		connection.update_data("students", "chefID", 0, "studentID", this.studentID, null);
		connection.update_data("chefs", "studentID", 0, "chefID", this.chefID, null);
		this.populateData(this.chefID);
    }
    
    public void passStudent() {
    	int lessonsAttended = connection.get_data("students", "lessons_attended", "studentID", Integer.toString(this.studentID), "int", "int").integerVar+1;
    	int requiredLessons = connection.get_data("lesson","number_lessons","lessonID", Integer.toString(this.courseID),"int","int").integerVar;
    	String status ="On-going";
    	String courseName = connection.get_data("lesson", "name", "lessonID", Integer.toString(this.courseID), "String", "int").stringVar;
    	connection.update_data("students", "lessons_attended", lessonsAttended, "studentID", this.studentID, null);
    	System.out.println("lessons attended -" + lessonsAttended + "Lessons required - " + requiredLessons);
    	if (lessonsAttended == requiredLessons) {
    		status = "Star-Baker";
    		connection.update_data("chefs", "message", 0, "chefID", this.chefID, "Course finished, sign up for a new one!");

    		connection.update_data("students", "message", 0, "studentID", this.studentID, "You have completed the course " + courseName + "!");
    		connection.update_data("chefs", "studentID", 0, "chefID", this.chefID, null);
    		connection.update_data("chefs", "courseID", 0, "chefID", this.chefID, null);
    	}
    	connection.update_data("students", "status", 0, "studentID", this.studentID, status);
    }
    
    public void createRow(){
    	String[] fields = new String[] {"username", "chefID"};
    	String[] values = new String[] {"'"+this.username+"'", "'"+this.chefID+"'"};
    	connection.set_data("chefs", fields, values);
    }
    public void populateData(int ID) {
    	//data about the account is fetched from the database and populated into the fields of this object
    	String stringID;
    	this.chefID = ID;
    	stringID = Integer.toString(ID);
    	this.username = connection.get_data("chefs", "username", "chefID", stringID, "String", "int").stringVar;
    	this.courseID = connection.get_data("chefs", "courseID", "chefID", stringID, "int", "int").integerVar;
    	this.studentID = connection.get_data("chefs", "studentID", "chefID", stringID, "int", "int").integerVar;

    }
    public static void main(String args[]){
        
    }
    
}
