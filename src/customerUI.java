
import java.util.*; 

import java.awt.BorderLayout;
import java.awt.Color;
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
	JLabel message = new JLabel();
	JLabel StudentStatus = new JLabel();
	JLabel lblNewLabel_1 = new JLabel();
	JLabel lessonsAttended = new JLabel();
	JButton previousBtn = new JButton("Previous courses");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
	}

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
		
		message.setBounds(18, 88, 358, 16);
		contentPane.add(message);
		message.setForeground(Color.RED);

		Refresh.setBounds(385, 37, 117, 29);
		contentPane.add(Refresh);
		
		JLabel username = new JLabel("Hello "+ student.username);
		username.setBounds(192, 19, 172, 16);
		contentPane.add(username);
		
		StudentStatus.setBounds(18, 37, 185, 16);
		contentPane.add(StudentStatus);
		
		
		signupORcancel.setBounds(159, 389, 132, 45);
		contentPane.add(signupORcancel);

		ChoiseSelection.setEnabled(true);
		ChoiseSelection.setBounds(58, 134, 336, 243);
		contentPane.add(ChoiseSelection);
		
		lblNewLabel_1.setBounds(129, 116, 162, 16);
		contentPane.add(lblNewLabel_1);
		

		previousBtn.setBounds(385, 389, 117, 45);
		contentPane.add(previousBtn);
		
		lessonsAttended.setBounds(18, 70, 197, 16);
		contentPane.add(lessonsAttended);
		
		
		Refresh.addActionListener(this);
		previousBtn.addActionListener(this);
		signupORcancel.addActionListener(this);
		refreshInfo();
	}
	
	public void refreshInfo() {
		this.student.populateData(this.student.studentID);
		StudentStatus.setText("Status - "+ this.student.status);
		if(this.student.status.equals("Star-Baker")) {
			lessonsAttended.setText("Lessons attended - " + this.student.lessonsAttended);
		}
		if(this.student.courseID!=0 ) {
			SelectedCourse.setText("Current course - "+ connection.get_data("lesson", "name", "lessonID", Integer.toString(this.student.courseID), "String", "int").stringVar);
			lessonsAttended.setText("Lessons attended - " + this.student.lessonsAttended);
			ChoiseSelection.setVisible(false);
			signupORcancel.setText("Cancel course");
			signupORcancel.setVisible(true);
			lblNewLabel_1.setVisible(false);
			previousBtn.setVisible(false);
		}
		
		else {
			lblNewLabel_1.setVisible(true);
			previousBtn.setVisible(true);
			ChoiseSelection.setVisible(true);
			signupORcancel.setText("Sign for a course");
			if(previousBtn.getText() == "Available chefs") {
				this.refreshPreviousCourses();
			}
			if(previousBtn.getText() == "Previous courses") {
				this.refreshLessons();
			}
			SelectedCourse.setText("Not assigned to a course");
		}
		message.setText(connection.get_data("students", "message", "studentid", Integer.toString(this.student.studentID), "String", "int").stringVar);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//populating the completed courses section
		 if (e.getSource() == previousBtn) {
			 if (previousBtn.getText() == "Previous courses") {
				 this.refreshPreviousCourses();
			 }
			 else {
				 this.refreshLessons();
			 }
		 }
		 if (e.getSource() == this.Refresh) {
			 this.refreshInfo();
		 }
		 if (e.getSource()==this.signupORcancel) {
			 if (signupORcancel.getText() != "Cancel course") {
				//getting the selected course and signing for it
				String chefName = chefNames.get(ChoiseSelection.getSelectedIndex());
				this.student.joinLesson(chefName);
				signupORcancel.setVisible(false);
			}
			else {
			 //cancel the course, update the chef that student cancelled
				this.student.leaveLesson();
			}
			this.refreshInfo();

		 }
	}
	public void refreshPreviousCourses() {
		l1.clear();
		int i = 0;
		String [][] table = new String [100][100];
		table = connection.get_table("completed_courses where studentID = "+this.student.studentID);
		while(true) {
			String lessonName = table[i][0];
			if(lessonName == null) {
				break;
			}
			l1.addElement(lessonName + " Status: Star-Baker");
			i++;
		}
		lblNewLabel_1.setText("Completed Lessons:");

		previousBtn.setText("Available chefs:");
	}
	public void refreshLessons() {
		int i = 0;
		l1.clear();
		chefNames.clear();
		String [][] table = new String[100][100];
		table = connection.get_table("available_chefs");
		while (true) {
			
			String chefName = table[i][0];
			String lesson_id = table[i][1];
			if(chefName==null) {
				break;
			}
			int lessons = connection.get_data("lesson", "NUMBER_LESSONS", "lessonID", lesson_id, "int", "int").integerVar;
			String lessonName = connection.get_data("lesson", "NAME", "lessonID", lesson_id, "String", "int").stringVar;
			l1.addElement(lessonName + ", chef - "+ chefName + ", lessons - " + lessons);  
			chefNames.add(chefName);
			 i++;
		}
		System.out.println(chefNames);
		previousBtn.setText("Previous courses");
		lblNewLabel_1.setText("Available courses");

	}
	}

