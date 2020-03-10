class Customer
{
    private Lesson lesson;
    private String username;
    private String password;
    private String status; 
    private int lessonsAttended;
 
    public Customer(String username, String password){
        this.lesson = new Lesson("None");
        this.lessonsAttended = 0;
        this.username = username;
        this.password = password;
        this.status = "Beginner";
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
    public void update_data(){
    	SQLconnection connection = new SQLconnection();
    	String[] fields = new String[] {"USERNAME", "PASSWORD", "STATUS"};
    	String[] values = new String[] {"'"+this.username+"'", "'"+this.password+"'", "'"+this.status+"'"};
    	connection.set_data("Customer", fields, values);
    }
    public void joinLesson(Lesson lesson){
        this.lesson = lesson;
    }
}
