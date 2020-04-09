
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
		
		Refresh.setBounds(385, 37, 117, 29);
		contentPane.add(Refresh);
		
		JLabel username = new JLabel("Hello "+ student.username);
		username.setBounds(192, 19, 172, 16);
		contentPane.add(username);
		
		StudentStatus.setBounds(18, 42, 185, 16);
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
		this.student.populateData(this.student.studentID);
		StudentStatus.setText("Status - "+ this.student.status);
		
		if(this.student.courseID!=0) {
			SelectedCourse.setText("Current course - "+ connection.get_data("lesson", "name", "lessonID", Integer.toString(this.student.courseID), "String", "int").stringVar);
			lblNewLabel.setText("Lessons attended - " + this.student.lessonsAttended);
			ChoiseSelection.setVisible(false);
			signupORcancel.setText("Cancel course");
			signupORcancel.setVisible(true);

			lblNewLabel_1.setVisible(false);
		}
		else {
			ChoiseSelection.setVisible(true);
			signupORcancel.setText("Sign for a course");
			this.refreshLessons();
			SelectedCourse.setText("Not assigned to a course");
			lblNewLabel_1.setText("Available courses");

		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		 if (e.getSource() == this.Refresh) {
			 this.refreshInfo();
		 }
		 if (e.getSource()==this.signupORcancel) {
			 if (signupORcancel.getText() != "Cancel course") {
				//getting the selected course and signing for it
				String chefName = chefNames.get(ChoiseSelection.getSelectedIndex());
				this.student.joinLesson(chefName);
				signupORcancel.setVisible(false);

				this.refreshInfo();
			}
			//if(this.student.courseID!=0) {
			else {
			 //cancel the course, update the chef that student cancelled
				//status to not-complete
				this.student.leaveLesson();
				this.refreshInfo();
			}
		 }
	}
	//SET @row = 0;

	//SELECT @row := @row + 1 AS Row, ID
	//FROM available_chefs;

	public void refreshLessons() {
		int i = 0;
		l1.clear();
		chefNames.clear();
		String [][] table = new String[100][100];
		table = connection.get_table();
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
	}
	}

