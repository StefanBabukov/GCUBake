import java.sql.*;
import java.util.*;
public class SQLconnection {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/GCUBake";

   //  Database credentials
   static final String USER = "foo";
   static final String PASS = "bar";
   
   public String get_data (String table, String field) {
   Connection conn = null;
   Statement stmt = null;
   String table_value = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT " + field + " FROM " + table;
      System.out.println(sql);
      ResultSet rs = stmt.executeQuery(sql);
      //STEP 5: Extract data from result set

      while(rs.next()){
         //Retrieve by column name
          table_value  = rs.getString(field);
        // INSERT INTO `lesson`(NAME) VALUES ("HI");
      } 
      
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();

   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
return table_value;
   
}//end get_func
   
public void set_data(String table, String[] fields, String[] values) {
	   Connection conn = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      String query;
	      query = "INSERT INTO " + table + " (" + Arrays.toString(fields).replace("[", "").replace("]", "") + ") " +  "VALUES " + "(" + Arrays.toString(values).replace("[", "").replace("]", "") + ")";
	     // System.out.println(sql);
	      System.out.println(query);
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.execute();
	      conn.close();
	   }
	      catch (Exception e)
	      {
	        System.err.println("Got an exception!");
	        System.err.println(e.getMessage());
	      }	   
}
   
   public static void main (String[] args) {
	   SQLconnection data = new SQLconnection();
	   String[] fields = new String[] {"NAME", "NUMBER_LESSONS"};
	   String[] values = new String [] {" 'test2' ", "'loll'"};
	   data.set_data("lesson", fields , values);
	   System.out.println(data.get_data("lesson", "name"));

   }
   }
//end FirstExample