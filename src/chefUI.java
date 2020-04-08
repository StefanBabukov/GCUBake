

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JList;

 public class chefUI extends JFrame implements ActionListener{
	 public Chef chef;
	private JPanel contentPane;
    DefaultListModel<String> l1 = new DefaultListModel<>();  
	JButton signupORpass = new JButton("Teach course");
	SQLconnection connection = new SQLconnection();
	JLabel teachingLabel = new JLabel();
	JList <String> ChoiseSelection = new JList <>(l1);
	JButton cancelBtn = new JButton("cancel course");

	public boolean sign;

	public chefUI(Chef chef) {
		this.chef = chef;
		JLabel username = new JLabel("Hello "+ chef.username);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 462);
		cancelBtn.setBounds(236, 257, 117, 29);
		username.setBounds(192, 19, 166, 16);
		signupORpass.setBounds(169, 376, 117, 29);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.add(username);
			
		contentPane.add(signupORpass);
		
		ChoiseSelection.setEnabled(true);
		ChoiseSelection.setBounds(105, 160, 253, 201);
		contentPane.add(ChoiseSelection);
		
		JLabel SelectedCourse = new JLabel("Current course - " + connection.get_data("lesson", "name", "lessonID", Integer.toString(this.chef.courseID), "String", "int").stringVar);
		SelectedCourse.setBounds(18, 59, 185, 16);
		contentPane.add(SelectedCourse);
		teachingLabel.setBounds(159, 192, 162, 16);
		contentPane.add(teachingLabel);

		//JLabel lblNewLabel = new JLabel("Lessons attended -");
		//lblNewLabel.setBounds(18, 83, 273, 16);
		//contentPane.add(lblNewLabel);
	
		JLabel TeachingStudent = new JLabel("");
		TeachingStudent.setBounds(159, 229, 61, 16);
		contentPane.add(TeachingStudent);
		
		JButton Pass = new JButton("Pass");
		Pass.setBounds(145, 257, 75, 29);
		contentPane.add(Pass);
		
		contentPane.add(cancelBtn);
		
		if (this.chef.courseID == 0) {
			ChoiseSelection.setVisible(true);
			refreshLessons();

		}
		else {
			ChoiseSelection.setVisible(false);
			if(this.chef.studentID != 0) {
				teachingLabel.setText("You are teaching - " + connection.get_data("students", "username", "studentID", Integer.toString(this.chef.studentID), "String", "int").stringVar);
				}
				else {
					teachingLabel.setText("Searching for a student...");
				}
		}
		signupORpass.addActionListener(this);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 System.out.println("in event handler1");

		// if (e.getSource() == this.Refresh) {
		//	 this.refreshLessons();
		 //}
		 System.out.print(e.getSource());
		 if (e.getSource() == this.signupORpass) {
			 System.out.println("in event handler");
			 if (this.chef.courseID == 0) {
				String courseName = new String();
				courseName = ChoiseSelection.getSelectedValue();
				
				this.chef.courseID = connection.get_data("lesson", "lessonID", "name", courseName, "int", "String").integerVar;
				connection.update_data("chefs", "courseID", this.chef.courseID, "chefID", this.chef.chefID);
				String[] fields = new String[] {"lessonID", "name"};
		    	String[] values = new String[] {"'"+this.chef.courseID+"'", "'"+this.chef.username+"'"};
				connection.set_data("available_chefs", fields, values);
				ChoiseSelection.setVisible(false);
			 }
		 }
	}
	public void refreshLessons() {
		int i = 1;
		l1.clear();
		while (true) {
			
			//2.addElement("Turbo C++");  
			SQLconnection connection = new SQLconnection();
			
			String name = connection.get_data("lesson", "name", "lessonID", Integer.toString(i), "String", "int").stringVar;
			if (name == null) {
				break;
			}
			 l1.addElement(name);  
			 i++;
		}
	}
	public static void main(String[] args) {
		
	}
}
