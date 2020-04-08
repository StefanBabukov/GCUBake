class Customer
{
    public String username;
    public String status; 
    public int lessonsAttended;
    public int studentID;
    
    public int courseID;
    
    public Lesson currentCourse;
    
    public Customer(String username, int studentID){
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
        //Check if there's a booked lesson for today
        this.lessonsAttended++;
        if (stop){
            this.status = "Not-complete";
        }
        else {
	        if (this.lessonsAttended == this.currentCourse.numberOfLessons){
	            this.status = "Star-Baker";
	        }
	        else{
	            this.status = "On-going";
	        }
        }

    }
    public void populateData(int ID) {
    	//data about the account is fetched from the database and populated into the fields of this object

    	String stringID;
    	stringID = Integer.toString(ID);
    	SQLconnection connection = new SQLconnection();
    	this.username = connection.get_data("students", "username", "studentID", stringID, "String", "int").stringVar;
    	this.status = connection.get_data("students", "status", "studentID", stringID, "String", "int").stringVar;
    	this.courseID = connection.get_data("students", "courseID", "studentID", stringID, "int", "int").integerVar;
    	
    	Lesson studentLesson = new Lesson();
    	currentCourse = studentLesson.getLessonFromSQL(this.courseID);
    	//this.
    }
    public void createRow(){
    	SQLconnection connection = new SQLconnection();
    	String[] fields = new String[] {"username", "studentID", "lessons_attended", "status" };
    	String[] values = new String[] {"'"+this.username+"'", "'"+this.studentID+"'","'"+this.lessonsAttended+"'","'"+this.status+"'"};
    	connection.set_data("students", fields, values);
    }
    //public void joinLesson(Lesson lesson){
     //   this.currentCourse = lesson;
   // }
}
