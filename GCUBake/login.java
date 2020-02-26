import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;  
class LoginPage implements ActionListener{  
    JTextField loginInput;  
    JButton loginbtn, registerbtn;  
    JPasswordField passwdInput = new JPasswordField();   
    JLabel logintxt = new JLabel();
    JPanel panel = new JPanel(new BorderLayout());
    LoginPage(){  
        final JFrame f = new JFrame("GCU Bake");
        f.setBackground(Color.red);
        panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setBounds(100, 0, 600, 600);
        
        loginInput = new JTextField("Login");
        loginInput.setBounds(200, 50, 150, 20);
        passwdInput.setBounds(200, 100, 150, 20);
        loginbtn=new JButton("login");  
        loginbtn.setBounds(200,150,75,50);  
        registerbtn=new JButton("register");  
        registerbtn.setBounds(200,200,75,50);  

        loginbtn.addActionListener(this);  
        registerbtn.addActionListener(this); 
        
        f.add(loginInput);f.add(passwdInput);f.add(loginbtn);f.add(registerbtn); f.add(panel); 
        f.setSize(800,800);  
        f.setLayout(null);  
        f.setVisible(true);  
    }

    public void actionPerformed(ActionEvent e) {  
        if(e.getSource() == registerbtn){
            this.f.remove(this.loginbtn);
        }
        if(e.getSource() == loginbtn){
            //database lookup, and login
        }
    }
    public static void main(String[] args) {
        new LoginPage();
    }

}