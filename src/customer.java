class Customer
{
    private Lesson lesson;
    public String username;
    private String status; 
    private int lessonsAttended;
    public int studentID;
    
    public Customer(String username, int studentID){
        this.lesson = new Lesson("None");
        this.lessonsAttended = 0;
        this.username = username;
        this.status = " ";
        this.studentID = studentID;
    }
    public Customer() {
    	
    }
    public void bookLesson(String lessonName){
        if(this.lessonsAttended == 0){
            this.status = "Beginner";
        }
    }
    
    public void completeLesson(String lessonName, boolean stop){
        //Check if theres a booked lesson for today
        this.lessonsAttended++;
        if (stop){
            this.status = "Not-complete";
        }
        else {
	        if (this.lessonsAttended == this.lesson.numberOfLessons){
	            this.status = "Star-Baker";
	        }
	        else{
	            this.status = "On-going";
	        }
        }

    }
    public void populateData(int ID) {
    	String stringID;
    	stringID = Integer.toString(ID);
    	SQLconnection connection = new SQLconnection();
    	this.username = connection.get_data("students", "username", "studentID", stringID, "String", "int").stringVar;
    	this.status = connection.get_data("students", "status", "studentID", stringID, "String", "int").stringVar;
    	
    	//this.
    }
    public void createRow(){
    	SQLconnection connection = new SQLconnection();
    	String[] fields = new String[] {"username", "studentID", "lessons_attended", "status" };
    	String[] values = new String[] {"'"+this.username+"'", "'"+this.studentID+"'","'"+this.lessonsAttended+"'","'"+this.status+"'"};
    	connection.set_data("students", fields, values);
    }
    public void joinLesson(Lesson lesson){
        this.lesson = lesson;
    }
}
