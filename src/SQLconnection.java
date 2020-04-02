import java.sql.*;
import java.util.*;


class returnObject{
	public int integerVar;
	public String stringVar;
}

public class SQLconnection {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/GCUBake";

   //  Database credentials
   static final String USER = "foo";
   static final String PASS = "bar";
   
   public returnObject get_data (String table, String get_field, String id_field, String name, String var_type, String name_type) {
	   //table to get data from
	   // get_field to get data from
	   // id_field to identify the correct field
	   // name to identify the correct value in id_field
	   // var_type - what type to return in the object
	   // name_type - what type the comparing variable "name" is in the database
	   Connection conn = null;
	   Statement stmt = null;
	   String string_table_value = null;
	   int int_table_value = 0;
	   returnObject object = new returnObject();
	   //SELECT * FROM [table name] WHERE name = "Bob"
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");
	
	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	
	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql = null;
	      if(name_type.equals("String")) {
	    	  sql = "SELECT " + get_field + " FROM " + table + " WHERE " + id_field +" = " + "'"+ name +"'";
	      }
	      if(name_type.equals("int")) {
	    	  sql = "SELECT " + get_field + " FROM " + table + " WHERE " + id_field +" = " + name;
	      }
	      System.out.println(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      //STEP 5: Extract data from result set
	
	      while(rs.next()){
	         //Retrieve by column name
	    	  if (var_type.equals("String")) {
	    		  string_table_value  = rs.getString(get_field);
	    	  }
	    	  if (var_type.equals("int")) {
	    		  int_table_value = rs.getInt(get_field);
	    	  }
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
	   if (var_type.equals("String")) {
		   object.stringVar = string_table_value;
	   }
	   if (var_type.equals("int")) {
		   object.integerVar = int_table_value;
	   }
	   
	return object;
	   
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
	   System.out.println("HI");
   }
   }
//end FirstExample