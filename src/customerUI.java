
import java.util.*; 

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.*;  

 public class customerUI extends JFrame implements ActionListener {
	 Customer student;
	 boolean sign = true;
     DefaultListModel<String> l1 = new DefaultListModel<>();  
	JButton Refresh = new JButton("Refresh");
	JButton signupORcancel = new JButton("Sign for a course");
	JList <String> ChoiseSelection = new JList <>(l1);
	ArrayList<String> chefNames = new ArrayList<String>();
	private JPanel contentPane;
	SQLconnection connection = new SQLconnection();
	JLabel SelectedCourse = new JLabel();
	JLabel lblNewLabel = new JLabel();
	JLabel StudentStatus = new JLabel();
	JLabel lblNewLabel_1 = new JLabel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
	}

	/**
	 * Create the frame.
	 */
	public customerUI(Customer student) {
		this.student = student;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		SelectedCourse.setBounds(18, 59, 185, 16);
		contentPane.add(SelectedCourse);
		
		lblNewLabel.setBounds(18, 83, 273, 16);
		contentPane.add(lblNewLabel);
		
		Refresh.setBounds(247, 129, 117, 29);
		contentPane.add(Refresh);
		
		JLabel username = new JLabel("Hello "+ student.username);
		username.setBounds(192, 19, 172, 16);
		contentPane.add(username);
		
		StudentStatus.setBounds(18, 42, 140, 16);
		contentPane.add(StudentStatus);
		
		
		signupORcancel.setBounds(159, 389, 132, 45);
		contentPane.add(signupORcancel);

		ChoiseSelection.setEnabled(true);
		ChoiseSelection.setBounds(141, 162, 253, 201);
		contentPane.add(ChoiseSelection);
		
		lblNewLabel_1.setBounds(119, 134, 162, 16);
		contentPane.add(lblNewLabel_1);
		

		JButton PreviousBtn = new JButton("Previous courses");
		PreviousBtn.setBounds(385, 389, 117, 45);
		contentPane.add(PreviousBtn);
		
		
		Refresh.addActionListener(this);
		PreviousBtn.addActionListener(this);
		signupORcancel.addActionListener(this);
		refreshInfo();
	}
	
	public void refreshInfo() {
		StudentStatus.setText("Status - "+ this.student.status);

		if(this.student.courseID!=0) {
			SelectedCourse.setText("Current course - "+ connection.get_data("lesson", "name", "lessonID", Integer.toString(this.student.courseID), "String", "int").stringVar);
			lblNewLabel.setText("Lessons attended - " + this.student.lessonsAttended);
			ChoiseSelection.setVisible(false);
			signupORcancel.setText("Cancel course");
			lblNewLabel_1.setVisible(false);
		}
		else {
			this.refreshLessons();
			lblNewLabel.setText("Not assigned to a course");
			lblNewLabel_1.setText("Available courses");

		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		 if (e.getSource() == this.Refresh) {
			 this.refreshInfo();
		 }
		 if (e.getSource()==this.signupORcancel) {
			 if (this.student.courseID == 0) {
				//getting the selected course and signing for it
				String chefName = chefNames.get(ChoiseSelection.getSelectedIndex());
				int chefID = connection.get_data("chefs", "chefID", "username", chefName, "int", "String").integerVar;
				int courseID = connection.get_data("chefs", "courseID", "chefID", Integer.toString(chefID), "int", "int").integerVar;
				
				connection.update_data("students", "courseID", courseID, "studentID", this.student.studentID);
				connection.update_data("students", "chefID", chefID, "studentID", this.student.studentID);
				connection.update_data("chefs", "studentID", this.student.studentID, "chefID", chefID);
				this.student.populateData(this.student.studentID);
				connection.modify_data("delete from available_chefs where name = '"+ chefName + "'");
				
				refreshInfo();
			}
			if(this.student.courseID!=0) {
				//cancel the course, update the chef that student cancelled
			}
		 }
	}
	public void refreshLessons() {
		int i = 1;
		l1.clear();
		chefNames.clear();
		while (true) {
			
			
			String chefName = connection.get_data("available_chefs", "name", "ID", Integer.toString(i), "String", "int").stringVar;
			int lesson_id = connection.get_data("available_chefs", "lessonID", "ID", Integer.toString(i), "int", "int").integerVar;
			
			int lessons = connection.get_data("lesson", "NUMBER_LESSONS", "lessonID", Integer.toString(lesson_id), "int", "int").integerVar;
			String lessonName = connection.get_data("lesson", "NAME", "lessonID", Integer.toString(lesson_id), "String", "int").stringVar;
			if (chefName == null) {
				break;
			}
			 l1.addElement(lessonName + ", chef - "+ chefName + ", lessons - " + lessons);  
			 chefNames.add(chefName);
			 i++;
		}
		System.out.println(chefNames);
	}
	}

