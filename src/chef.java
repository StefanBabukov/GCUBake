import java.util.*; 

class Chef
{
    // instance variables - replace the example below with your own
    public String username;

    public Customer student;
    public int chefID; 
    public Lesson current_course;
    
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
    public void createRow(){
    	SQLconnection connection = new SQLconnection();
    	String[] fields = new String[] {"username", "chefID", "current_course", "student" };
    	String[] values = new String[] {"'"+this.username+"'", "'"+this.chefID+"'","'"+" "+"'","'"+" "+"'"};
    	connection.set_data("chefs", fields, values);
    }
    public void populateData(int ID) {
    	String stringID;
    	stringID = Integer.toString(ID);
    	SQLconnection connection = new SQLconnection();
    	this.username = connection.get_data("chefs", "username", "chefID", stringID, "String", "int").stringVar;
    	
    }
    public static void main(String args[]){
        /*System.out.println("hello");
        Lesson cakeLesson = new Lesson("Cakes");
        Lesson muffinLesson = new Lesson("Muffins");
        Chef bob = new Chef("Bob");
        Chef john = new Chef("John");
        

        bob.teachLesson(cakeLesson);
        bob.bookClass(cakeLesson, "11.06");
        bob.teachLesson(muffinLesson);
        bob.bookClass(muffinLesson, "13.06");
       // john.bookClass(cakeLesson,"12.06");
        
        //cakeLesson.getSchedule();
        bob.checkTimetable();
        System.out.println(bob.assignedClasses.toString());
        */
    }
    
}
