import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import java.sql.*;  

class MysqlCon{  
public static void main(String args[]){  
try{  
    Class.forName("com.mysql.jdbc.Driver");  
    Connection con=DriverManager.getConnection(  
    "jdbc:mysql://localhost:3306/GCUBake","foo","bar");  
    //here sonoo is database name, root is username and password  
    Statement stmt=con.createStatement();  
    ResultSet rs=stmt.executeQuery("select * from emp");  
    while(rs.next())  
    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
    con.close();  
}catch(Exception e){ System.out.println(e);}  
}  
}  
class LoginFrame extends JFrame implements ActionListener {
 
    Container container=getContentPane();
    JLabel userLabel=new JLabel("USERNAME");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JTextField userTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JButton loginButton=new JButton("LOGIN");
    JButton registerButton=new JButton("REGISTER");
    JCheckBox showPassword=new JCheckBox("Show Password");
    JButton confirmBtn = new JButton("Confirm");
    JRadioButton chefRadio=new JRadioButton("Chef");    
    JRadioButton studentRadio=new JRadioButton("Student");   
 
    LoginFrame()
    {
       //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
 
    }
   public void setLayoutManager()
   {
       container.setLayout(null);
   }
   public void setLocationAndSize()
   {
       //Setting location and Size of each components using setBounds() method.
       
	   userLabel.setBounds(50,150,100,30);
       passwordLabel.setBounds(50,220,100,30);
       userTextField.setBounds(150,150,150,30);
       passwordField.setBounds(150,220,150,30);
       showPassword.setBounds(150,250,150,30);
       loginButton.setBounds(50,300,100,30);
       registerButton.setBounds(200,300,100,30);
   		confirmBtn.setBounds(50,300,100,30);
        registerButton.addActionListener(this);
        chefRadio.setBounds(50,100,75,50);
        studentRadio.setBounds(120,100,120,50);
        studentRadio.setVisible(false);
        chefRadio.setVisible(false);
        chefRadio.addActionListener(this);
        studentRadio.addActionListener(this);
   }
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
       container.add(userLabel);
       container.add(passwordLabel);
       container.add(userTextField);
       container.add(passwordField);
       container.add(showPassword);
       container.add(loginButton);
       container.add(registerButton);
       container.add(confirmBtn);
       container.add(chefRadio);
       container.add(studentRadio);
   }
 
    @Override
    public void actionPerformed(ActionEvent e) {
    	if (chefRadio.isSelected()) {
    		studentRadio.setSelected(false);
    	}
    	if (studentRadio.isSelected()) {
    		chefRadio.setSelected(false);
    	}
    	loginButton.setVisible(false);
    	confirmBtn.setVisible(true);
    	registerButton.setVisible(false);
    	studentRadio.setVisible(true);
    	chefRadio.setVisible(true);
    }
}
 
class Login {
    public static void main(String[] args){
        LoginFrame frame=new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10,10,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
 
    }}