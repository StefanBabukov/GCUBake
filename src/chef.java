//import java.util.*; 

class Chef
{
    // instance variables - replace the example below with your own
    public String username;

    public Customer student;
    public int chefID; 
    public Lesson currentCourse;
    public int courseID;
    public int studentID;
	SQLconnection connection = new SQLconnection();

    public Chef(String name, int chefID){
        this.username = name;
        this.chefID = chefID;
    }
    public Chef() {
    	
    }
    /*public void bookClass(Lesson lesson, String date){
        boolean exists = false;
        for (int i=0; i<this.classes.length; i++){
            if (classes[i] == lesson){
                exists = true;
            }
        }
        if (exists){
            lesson.schedule.put(date, this.name);
            this.assignedClasses.put(date, lesson.getName());
            System.out.println("Class booked.");
        }
        else{
            System.out.println("Chef not assigned to teach this course!");
        }
    }
    */
    /*public void teachLesson(Lesson lesson){
        int i=0;
        while(true){
            if(this.classes[i] == null){
                this.classes[i] = lesson;
                break;
            }
            i++;
        }
    }
	*/
    /*public Dictionary<String, Object> checkTimetable(){
        //System.out.println(this.assignedClassses.toString());
        return this.assignedClasses;
    }
    */
    public void signLesson(String courseName) {
		this.courseID = connection.get_data("lesson", "lessonID", "name", courseName, "int", "String").integerVar;
		connection.update_data("chefs", "courseID", this.courseID, "chefID", this.chefID, null);
		String[] fields = new String[] {"lessonID", "name"};
    	String[] values = new String[] {"'"+this.courseID+"'", "'"+this.username+"'"};
		connection.set_data("available_chefs", fields, values);
		this.populateData(this.chefID);
    }
    public void cancelCourse() {
		connection.update_data("chefs", "courseID", 0, "chefID", this.chefID, null);
		connection.update_data("students", "status", 0, "studentID", this.studentID, "Not-complete");
		connection.update_data("students", "courseID", 0, "studentID", this.studentID, null);
		connection.update_data("students", "chefID", 0, "studentID", this.studentID, null);
		connection.update_data("chefs", "studentID", 0, "chefID", this.chefID, null);
		this.populateData(this.chefID);
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

    	//Lesson chefLesson = new Lesson();
    	//currentCourse = chefLesson.getLessonFromSQL(this.courseID);
    }
    public static void main(String args[]){
        
    }
    
}
