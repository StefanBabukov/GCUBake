

//import java.awt.BorderLayout;
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
import java.awt.Color;

 public class chefUI extends JFrame implements ActionListener{
	 public Chef chef;
	private JPanel contentPane;
    DefaultListModel<String> l1 = new DefaultListModel<>();  
	JButton teachBtn = new JButton("Teach course");
	SQLconnection connection = new SQLconnection();
	JLabel teachingLabel = new JLabel();
	JList <String> ChoiseSelection = new JList <>(l1);
	JButton cancelBtn = new JButton("cancel course");
	JButton refresh = new JButton("Refresh");
	JLabel SelectedCourse = new JLabel();
	JButton Pass = new JButton("Pass");
	private final JLabel message = new JLabel();

	public boolean sign;

	public chefUI(Chef chef) {
		this.chef = chef;
		JLabel username = new JLabel("Hello "+ chef.username);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 462);
		cancelBtn.setBounds(236, 257, 117, 29);
		username.setBounds(192, 19, 166, 16);
		teachBtn.setBounds(169, 376, 117, 29);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.add(username);
			
		contentPane.add(teachBtn);
		
		ChoiseSelection.setEnabled(true);
		ChoiseSelection.setBounds(105, 160, 253, 201);
		contentPane.add(ChoiseSelection);
		
		SelectedCourse.setText("No current course");
		SelectedCourse.setBounds(18, 59, 185, 16);
		contentPane.add(SelectedCourse);
		teachingLabel.setBounds(159, 192, 162, 16);
		contentPane.add(teachingLabel);

		Pass.setBounds(145, 257, 75, 29);
		contentPane.add(Pass);
		
		contentPane.add(cancelBtn);
		
		refresh.setBounds(370, 14, 117, 29);
		contentPane.add(refresh);
		message.setForeground(Color.RED);
		message.setBounds(18, 87, 303, 16);
		
		contentPane.add(message);
		
		refresh.addActionListener(this);
		teachBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		Pass.addActionListener(this);
		this.refreshInfo();

	}
	public void refreshInfo() {
		this.chef.populateData(this.chef.chefID);
		if (this.chef.courseID == 0) {
			ChoiseSelection.setVisible(true);
			refreshLessons();
		}
		else {
			ChoiseSelection.setVisible(false);
			if(this.chef.studentID != 0) {
				teachingLabel.setText("You are teaching - " + connection.get_data("students", "username", "studentID", Integer.toString(this.chef.studentID), "String", "int").stringVar);
				SelectedCourse.setText("Current course - " + connection.get_data("lesson", "name", "lessonID", Integer.toString(this.chef.courseID), "String", "int").stringVar);
				teachBtn.setVisible(false);
				}
				else {
					teachingLabel.setText("Searching for a student...");
				}
		}
		message.setText(connection.get_data("chefs", "message", "chefid", Integer.toString(this.chef.chefID), "String", "int").stringVar);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		 if (e.getSource() == refresh) {
			 this.refreshInfo();
		 }
		 if(e.getSource() == cancelBtn) {
			 this.chef.cancelCourse();
			 this.refreshInfo();
		 }
		 if (e.getSource() == this.teachBtn) {
			System.out.println("in event handler");
			String courseName = ChoiseSelection.getSelectedValue();
			this.chef.signLesson(courseName);
			ChoiseSelection.setVisible(false);
			this.refreshInfo();
		 }
		 if(e.getSource() == Pass) {
			 this.chef.passStudent();
			 this.refreshInfo();
		 }
	}
	public void refreshLessons() {
		int i = 1;
		l1.clear();
		while (true) {
						
			String name = connection.get_data("lesson", "name", "lessonID", Integer.toString(i), "String", "int").stringVar;
			if (name == null) {
				break;
			}
			 l1.addElement(name);  
			 i++;
		}
	}

}
