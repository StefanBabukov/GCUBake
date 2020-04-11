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
	      } 
	      
	      rs.close();
	      stmt.close();
	      conn.close();
	
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	   if (var_type.equals("String")) {
		   object.stringVar = string_table_value;
	   }
	   if (var_type.equals("int")) {
		   object.integerVar = int_table_value;
	   }
	   
	return object;
	   
   }
   
public void set_data(String table, String[] fields, String[] values) {
	   Connection conn = null;
	   try{
	      Class.forName("com.mysql.jdbc.Driver");
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      System.out.println("Creating statement...");
	      String query;
	      query = "INSERT INTO " + table + " (" + Arrays.toString(fields).replace("[", "").replace("]", "") + ") " +  "VALUES " + "(" + Arrays.toString(values).replace("[", "").replace("]", "") + ")";
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
   public void update_data(String table, String column, int value, String id_name, int id_value, String stringValue) {
	   Connection conn = null;

	   try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      String query;
		      if(stringValue == null) {
		    	  query = "update " + table + " set " + column + " = " + value + " where " + id_name + " = " + id_value ;
		      }
		      else {
		    	  query = "update " + table + " set " + column + " = '" + stringValue + "' where " + id_name + " = " + id_value ;
		      }
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
   public void modify_data(String query) {
	   Connection conn = null;

	   try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
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
	public String[][] get_table(String table){
		Connection conn = null;
		   Statement stmt = null;
		   String [][] list =new String[100][100];

		   try{
		      Class.forName("com.mysql.jdbc.Driver");
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql = null;
		      sql = "SELECT * from "+ table;
		      System.out.println(sql);

		      ResultSet rs = stmt.executeQuery(sql);
		      int i = 0;
		      while(rs.next()){
		    	  if (table.equals("available_chefs")) {
		    		  list[i][0] = rs.getString("name");
		    		  list[i][1] = Integer.toString(rs.getInt("lessonID"));
		    		  
		    	  }
		    	  else {
		    		 list[i][0] = rs.getString("name");
		    	  }
		    	  System.out.println(list[i][0]);
		    	  i++;
		      } 
		      rs.close();
		      stmt.close();
		      conn.close();
		   }
		   catch (Exception e)
		      {
		        System.err.println("Got an exception!");
		        System.err.println(e.getMessage());
		      }	
		   return list;

	}

   }