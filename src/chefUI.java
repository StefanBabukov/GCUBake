

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

 public class chefUI extends JFrame {
	 public Chef chef;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
	}

	/**
	 * Create the frame.
	 */
	public chefUI(Chef chef) {
		this.chef = chef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel username = new JLabel("Hello "+ chef.username);
		username.setBounds(192, 19, 166, 16);
		contentPane.add(username);
		
		JButton signupORpass = new JButton("Teach course");
		signupORpass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		signupORpass.setBounds(169, 376, 117, 29);
		contentPane.add(signupORpass);
		
		JList ChoiseSelection = new JList();
		ChoiseSelection.setEnabled(false);
		ChoiseSelection.setBounds(105, 160, 253, 201);
		contentPane.add(ChoiseSelection);
		
		JLabel SelectedCourse = new JLabel("Current course -");
		SelectedCourse.setBounds(18, 59, 185, 16);
		contentPane.add(SelectedCourse);
		
		JLabel lblNewLabel = new JLabel("Lessons attended -");
		lblNewLabel.setBounds(18, 83, 273, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("You are teaching:");
		lblNewLabel_1.setBounds(159, 192, 162, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel TeachingStudent = new JLabel("");
		TeachingStudent.setBounds(159, 229, 61, 16);
		contentPane.add(TeachingStudent);
		
		JButton Pass = new JButton("Pass");
		Pass.setBounds(145, 257, 75, 29);
		contentPane.add(Pass);
		
		JButton btnNewButton = new JButton("cancel course");
		btnNewButton.setBounds(236, 257, 117, 29);
		contentPane.add(btnNewButton);
	}
}
