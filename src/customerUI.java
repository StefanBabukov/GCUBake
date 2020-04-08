

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

	private JPanel contentPane;

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
		
		JLabel username = new JLabel("Hello "+ student.username);
		username.setBounds(192, 19, 172, 16);
		contentPane.add(username);
		
		JLabel StudentStatus = new JLabel("Status - "+ student.status);
		StudentStatus.setBounds(18, 42, 140, 16);
		contentPane.add(StudentStatus);
		
		
		signupORcancel.setBounds(159, 389, 132, 45);
		contentPane.add(signupORcancel);

		JList <String> ChoiseSelection = new JList <>(l1);
		ChoiseSelection.setEnabled(true);
		ChoiseSelection.setBounds(141, 162, 253, 201);
		contentPane.add(ChoiseSelection);
		this.refreshLessons();
		
		JLabel SelectedCourse = new JLabel("Current course - "+ student.currentCourse.getName());
		SelectedCourse.setBounds(18, 59, 185, 16);
		contentPane.add(SelectedCourse);
		
		JLabel lblNewLabel = new JLabel("Lessons attended - " + student.lessonsAttended);
		lblNewLabel.setBounds(18, 83, 273, 16);
		contentPane.add(lblNewLabel);
		
		JButton PreviousBtn = new JButton("Previous courses");
		PreviousBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		PreviousBtn.setBounds(385, 389, 117, 45);
		contentPane.add(PreviousBtn);
		
		Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get the courses and populate table
			}
		});
		Refresh.setBounds(247, 129, 117, 29);
		contentPane.add(Refresh);
		
		JLabel lblNewLabel_1 = new JLabel("Available courses");
		lblNewLabel_1.setBounds(119, 134, 162, 16);
		contentPane.add(lblNewLabel_1);
		
		signupORcancel.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		
		 if (e.getSource() == this.Refresh) {
			 this.refreshLessons();
		 }
		 if (e.getSource()==this.signupORcancel) {
			 if (sign) {
				 String a = new String();
				a = l1.getSelectedValue().toString();
			}
		 }
	}
	public void refreshLessons() {
		int i = 1;
		while (true) {
			
			//2.addElement("Turbo C++");  
			SQLconnection connection = new SQLconnection();
			
			String name = connection.get_data("lesson", "name", "lessonID", Integer.toString(i), "String", "int").stringVar;
			int lessons = connection.get_data("lesson", "NUMBER_LESSONS", "lessonID", Integer.toString(i), "int", "int").integerVar;
			if (name == null) {
				break;
			}
			 l1.addElement(name + " - number of lessons is "+ lessons);  
			 i++;
		}
	}
	}

