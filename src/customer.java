//import java.util.ArrayList;

class Customer
{
    public String username;
    public String status; 
    public int lessonsAttended;
    public int studentID;
    public int chefID;
    public int courseID;
    SQLconnection connection = new SQLconnection();
    public String message;
    public Customer(String username, int studentID){
        this.lessonsAttended = 0;
        this.username = username;
        this.status = " ";
        this.studentID = studentID;
    }
    public Customer() {
    	
    }
    public void joinLesson(String chefName) {
		
		//checking if the course has already been taken by another student
		if(connection.get_data("chefs", "studentid", "username", chefName, "int", "String").integerVar == 0) {
			int chefID = connection.get_data("chefs", "chefID", "username", chefName, "int", "String").integerVar;
			int courseID = connection.get_data("chefs", "courseID", "chefID", Integer.toString(chefID), "int", "int").integerVar;
			connection.update_data("students", "courseID", courseID, "studentID", this.studentID, null);
			connection.update_data("students", "chefID", chefID, "studentID", this.studentID, null);
			connection.update_data("chefs", "studentID", this.studentID, "chefID", chefID, null);
			connection.update_data("students", "status", 0, "studentID", this.studentID, "Beginner");
			connection.modify_data("delete from available_chefs where name = '"+ chefName + "'");	
			connection.update_data("students", "message", 0, "studentID", this.studentID, " ");
			connection.update_data("students", "lessons_attended", 0, "studentID", this.studentID, null);
			connection.update_data("students", "message", 0, "studentID", this.studentID, " ");
		}
		else {
			//course has been taken
			connection.update_data("students", "message", 0, "studentid", this.studentID, "Course is already taken, choose another one!");
		
		}
		this.populateData(this.studentID);

    }
    
    public void leaveLesson( ) {
  
    	
        connection.update_data("chefs", "message", 0, "chefID", this.chefID, "Student Cancelled the course!");
    	connection.update_data("students", "status", 0, "studentID", this.studentID, "Not-complete");
    	connection.update_data("students", "lessons_attended", 0, "studentID", this.studentID, null);
    	
		connection.update_data("students", "courseID", 0, "studentID", this.studentID, null);
		connection.update_data("chefs", "studentID", 0, "chefID", this.chefID, null);
		connection.update_data("chefs", "courseID", 0, "chefID", this.chefID, null);

		connection.update_data("students", "chefID", 0, "studentID", this.studentID, null);
		this.populateData(this.studentID);
    }
    
    public void addToCompletedCourses() {
    	String [] fields = new String[]{"studentID","name"};
    	String course_name = connection.get_data("lesson", "name", "lessonID", Integer.toString(this.courseID), "String", "int").stringVar;
    	String [] values = new String[] {"'"+this.studentID+"'", "'"+course_name+"'"};
    	connection.set_data("completed_courses", fields, values);
    }
    
    public void populateData(int ID) {
    	//data about the account is fetched from the database and populated into the fields of this object
    	String stringID;
    	this.studentID = ID;
    	stringID = Integer.toString(ID);
    	this.username = connection.get_data("students", "username", "studentID", stringID, "String", "int").stringVar;
    	this.status = connection.get_data("students", "status", "studentID", stringID, "String", "int").stringVar;
    	this.courseID = connection.get_data("students", "courseID", "studentID", stringID, "int", "int").integerVar;
    	this.chefID = connection.get_data("students", "chefID", "studentID", stringID, "int", "int").integerVar;
    	this.lessonsAttended = connection.get_data("students", "lessons_attended", "studentID", stringID, "int", "int").integerVar;
    	this.message = connection.get_data("students", "message", "studentID", stringID, "String", "int").stringVar;
    			
    	if (this.status.equals("Star-Baker") && this.chefID != 0) {
    		//course is completed
    		this.addToCompletedCourses();
    		connection.update_data("students", "courseID", 0, "studentID", this.studentID, null);
    		connection.update_data("chefs", "studentID", 0, "chefID", this.chefID, null);
    		connection.update_data("students", "chefID", 0, "studentID", this.studentID, null);
    		this.courseID = 0;
    		this.chefID = 0;
    	}
    }
    public void createRow(){
    	//this method is called when a new account is created
    	String[] fields = new String[] {"username", "studentID", "lessons_attended", "status" };
    	String[] values = new String[] {"'"+this.username+"'", "'"+this.studentID+"'","'"+this.lessonsAttended+"'","'"+this.status+"'"};
    	connection.set_data("students", fields, values);
    }

}
