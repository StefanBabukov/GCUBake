import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginFrame extends JFrame implements ActionListener {
 
   	JPanel container = new JPanel();
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
    JLabel welcome=new JLabel("Please login or register");
	SQLconnection connection = new SQLconnection();
	JButton back = new JButton("Back");
	
    LoginFrame()
    {
       //Calling methods inside constructor.
		setContentPane(container);

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
        userTextField.addActionListener(this);
        passwordField.addActionListener(this);
        passwordField.setBounds(150,220,150,30);
        showPassword.setBounds(150,250,150,30);
        loginButton.setBounds(50,300,100,30);
        loginButton.addActionListener(this);
        registerButton.setBounds(200,300,100,30);
   		confirmBtn.setBounds(50,300,100,30);
   		confirmBtn.addActionListener(this);
        registerButton.addActionListener(this);
        chefRadio.setBounds(50,100,75,50);
        studentRadio.setBounds(120,100,120,50);
        back.setBounds(200,300,100,30);
        back.addActionListener(this);
        studentRadio.setVisible(false);
        chefRadio.setVisible(false);
        chefRadio.addActionListener(this);
        back.setVisible(false);
        studentRadio.addActionListener(this);
        ButtonGroup bg=new ButtonGroup();    
        bg.add(chefRadio);bg.add(studentRadio); 
        welcome.setBounds(50, 70, 220, 30);
        showPassword.addActionListener(this);
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
       container.add(welcome);
       container.add(back);
   }
 
    @Override
    public void actionPerformed(ActionEvent e) {
    	System.out.print(e.getSource());
    	String role = null;
    	if (showPassword.isSelected()) {
    		passwordField.setEchoChar((char)0);
    	}
    	else {
    		passwordField.setEchoChar('*');
    	}
    	if (chefRadio.isSelected()) {
    		role = "Chef";
    	}
    	if (studentRadio.isSelected()) {
    		role = "Student";
    	}
    	if (e.getSource() == registerButton) {
        	loginButton.setVisible(false);
        	confirmBtn.setVisible(true);
        	registerButton.setVisible(false);
        	studentRadio.setVisible(true);
        	chefRadio.setVisible(true);
        	welcome.setVisible(true);
        	back.setVisible(true);
        	welcome.setForeground(Color.BLACK);
        	welcome.setText("Choose a username and password");
    	}
    	if(e.getSource()==back) {
        	back.setVisible(false);
          	loginButton.setVisible(true);
        	confirmBtn.setVisible(false);
        	registerButton.setVisible(true);
        	studentRadio.setVisible(false);
        	chefRadio.setVisible(false);
        	welcome.setVisible(true);
        	welcome.setForeground(Color.BLACK);
        	welcome.setText("Please login or register");
    	}
    	if(e.getSource() == confirmBtn) {
    		String username = userTextField.getText();
        	String password = String.valueOf(passwordField.getPassword());

        	String checkUsernameExistance = connection.get_data("users", "username", "username", username, "String", "String").stringVar;
        	if(checkUsernameExistance != null) {
        		//username exists, registration is cancelled
        		welcome.setForeground(Color.RED);
        		welcome.setText("Username already in use!");
        		userTextField.setText("");
        		passwordField.setText("");
        	}
        	else {
	        	String[] fields = new String[] {"username", "password", "role"};
	        	
	            String[] values = new String[] {"'"+username+"'", "'"+password+"'", "'"+role+"'"};
	            connection.set_data("users", fields, values);
	       
	            returnObject id = connection.get_data("users", "id", "username", username, "int", "String");
	            
	            if (role.equals("Chef")) {
	            	Chef newChef = new Chef(username, id.integerVar);
	            	newChef.createRow();
	            	chefUI frame = new chefUI(newChef);
					frame.setVisible(true);
	            }
	            
	            if(role.equals("Student")) {
	            	Customer newStudent = new Customer(username, id.integerVar);
	            	newStudent.createRow();
	            	customerUI frame = new customerUI(newStudent);
					frame.setVisible(true);
	            }
        	}
            
        }
     	
    	
    	if(e.getSource() == loginButton) {
    		String username = userTextField.getText();
    		String password = String.valueOf(passwordField.getPassword());
    		returnObject password_check;
        	String checkUsernameExistance = connection.get_data("users", "username", "username", username, "String", "String").stringVar;
        	//checking if account username exists
        	if (checkUsernameExistance == null) {
        		welcome.setForeground(Color.RED);
        		welcome.setText("Wrong password or username!");
        	}
        	else {
        		//checking if the username and password match
        		password_check = connection.get_data("users", "password", "username", username, "String", "String");
	        	if (password_check.stringVar.equals(password)) {
	        		//logging in
	        		System.out.println("Success");
	        		
	        		welcome.setForeground(Color.BLACK);
	        		welcome.setText("Welcome " + username);
	        		//launch next part of the GUI
	        		
	        		role = connection.get_data("users", "role", "username", username, "String", "String").stringVar;
	        		int id = connection.get_data("users", "id", "username", username, "int", "String").integerVar;
	        		//determining the role - chef/student of the account and launching the appropriate gui
	        		if(role.equals("Student")) {
	        			Customer getCustomer = new Customer();
	        			getCustomer.populateData(id);
	        			customerUI frame = new customerUI(getCustomer);
	    				frame.setVisible(true);
	        		}
	        		if(role.equals("Chef")) {
	        			Chef getChef = new Chef();
	        			getChef.populateData(id);
	        			chefUI frame = new chefUI(getChef);
	    				frame.setVisible(true);
	        		}
	        	}
	        	else {
	        		welcome.setForeground(Color.RED);
	        		welcome.setText("Wrong password or username!");
	        		System.out.println("Wrong password or username");
	        	}
        	}
    	}
    }
    public static void main(String[] args){
    	
        LoginFrame frame=new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10,10,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        frame.setResizable(false);
 
    }
 }