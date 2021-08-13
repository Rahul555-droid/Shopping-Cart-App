package appGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.sql.*;
import jdbc_sql.JDBCPostgreSQLConnection;


public class OpenFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTabbedPane tabbedPane;
	private JTextField username_txt_c;
	private JTextField password_txt_c;
	private JTextField first_name_txt_c;
	private JTextField last_name_txt_c;
	private JTextField email_txt_c;
	private JTextField username_txt;
	private JTextField password_txt;
	private JTextField first_name_txt;
	private JTextField last_name_txt;
	private JTextField email_txt;
	private JTextField address_txt_c;
	
	/**
	 * Create the frame.
	 */
	public OpenFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 446);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 173, 412);
		panel.setBackground(Color.YELLOW);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("User Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnNewButton_1.setBounds(0, 41, 173, 45);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Register customer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
				
			}
		});
		btnNewButton.setBounds(0, 116, 173, 64);
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Register Employee/Manager");
		btnNewButton_2.setBackground(new Color(240, 240, 240));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnNewButton_2.setBounds(0, 222, 173, 52);
		panel.add(btnNewButton_2);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnExit.setBounds(0, 312, 173, 45);
		panel.add(btnExit);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(171, -57, 482, 469);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 240, 240));
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(148, 176, 136, 44);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(59, 176, 79, 44);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(59, 233, 79, 44);
		panel_1.add(lblNewLabel_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(148, 231, 136, 46);
		panel_1.add(passwordField);
		
		JButton btnNewButton_3 = new JButton("Log in");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = textField.getText();
                String password = passwordField.getText();
                
                try {
                  
                    ///String url = "jdbc:postgresql://localhost/app_db";
                	String url = "jdbc:postgresql://localhost:5432/app_db";
                    
                    Connection connection = (Connection)DriverManager.getConnection(url,"postgres","rtx");

                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select * from users where username=? and password=?");

                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose();
                        int id  = rs.getInt("userID");
            			String user_type = rs.getString("user_type");
            	        //depending on user type open required frame
            			if (user_type.contentEquals("Customers")){
            				ShoppingCart frame2 = new ShoppingCart(userName);
            				frame2.setVisible(true);
            			}
            			else{
            				EmployeeFrame frame3 = new EmployeeFrame(userName);
                			frame3.setVisible(true);
            			
            			}
            			
                        JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Wrong Username or Password");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            
                
                
            }
		});
		btnNewButton_3.setBounds(195, 311, 89, 23);
		panel_1.add(btnNewButton_3);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		tabbedPane.addTab("New tab", null, panel_3, null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Register as Employee or Manager");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2_1.setBounds(110, 34, 357, 66);
		panel_3.add(lblNewLabel_2_1);
		
		username_txt = new JTextField();
		username_txt.setColumns(10);
		username_txt.setBounds(183, 111, 148, 20);
		panel_3.add(username_txt);
		
		password_txt = new JTextField();
		password_txt.setColumns(10);
		password_txt.setBounds(183, 142, 148, 20);
		panel_3.add(password_txt);
		
		first_name_txt = new JTextField();
		first_name_txt.setColumns(10);
		first_name_txt.setBounds(183, 173, 148, 20);
		panel_3.add(first_name_txt);
		
		last_name_txt = new JTextField();
		last_name_txt.setColumns(10);
		last_name_txt.setBounds(183, 216, 148, 20);
		panel_3.add(last_name_txt);
		
		email_txt = new JTextField();
		email_txt.setColumns(10);
		email_txt.setBounds(183, 259, 148, 20);
		panel_3.add(email_txt);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Manager");
		rdbtnNewRadioButton_1.setBounds(136, 337, 118, 23);
		rdbtnNewRadioButton_1.setActionCommand("Manager");
		panel_3.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Employee");
		rdbtnNewRadioButton_2.setSelected(true);
		rdbtnNewRadioButton_2.setActionCommand("Employee");
		rdbtnNewRadioButton_2.setBounds(267, 337, 129, 23);
		panel_3.add(rdbtnNewRadioButton_2);
		
		ButtonGroup G1 = new ButtonGroup();
		G1.add(rdbtnNewRadioButton_1);
        G1.add(rdbtnNewRadioButton_2);

		
		
		JLabel lblNewLabel_4_6 = new JLabel("Position");
		lblNewLabel_4_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_6.setBounds(37, 338, 65, 20);
		panel_3.add(lblNewLabel_4_6);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Username");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_1_1.setBounds(37, 114, 85, 20);
		panel_3.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_2_1 = new JLabel("Password");
		lblNewLabel_4_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_2_1.setBounds(37, 142, 85, 20);
		panel_3.add(lblNewLabel_4_2_1);
		
		JLabel lblNewLabel_4_3_1 = new JLabel("FirstName");
		lblNewLabel_4_3_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_3_1.setBounds(37, 173, 65, 20);
		panel_3.add(lblNewLabel_4_3_1);
		
		JLabel lblNewLabel_4_4_1 = new JLabel("Lastname");
		lblNewLabel_4_4_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_4_1.setBounds(37, 216, 65, 20);
		panel_3.add(lblNewLabel_4_4_1);
		
		JLabel lblNewLabel_4_5_1 = new JLabel("Email Address");
		lblNewLabel_4_5_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_5_1.setBounds(37, 259, 110, 20);
		panel_3.add(lblNewLabel_4_5_1);
		
		JButton btnNewButton_4 = new JButton("Register");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String first_name = first_name_txt.getText();
                String last_name =  last_name_txt.getText();
                String email = email_txt.getText();
                String username = username_txt.getText();
                String password = password_txt.getText();
                String position = G1.getSelection().getActionCommand();
                String usertype = "Employee";
                try {
                	String url = "jdbc:postgresql://localhost:5432/app_db";
                    Connection connection = (Connection)DriverManager.getConnection(url,"postgres","rtx");
                	
                    //first adding this to users
                    String query1 = "INSERT INTO users(username,password,user_type) values('" + username + "','" + password + "','" + usertype+"')";
                   
                    
                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query1);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(null, "This is alredy exist");
                    } else {
                       
                        PreparedStatement st = (PreparedStatement) connection
                                .prepareStatement("Select * from users where username=? and password=?");

                            st.setString(1, username);
                            st.setString(2, password);
                            ResultSet rs = st.executeQuery();
                           rs.next();
                           int userid  = rs.getInt("userID");   
                            
                            
                                
                    // create account
                    
  String query2 = "INSERT INTO employees(userid,first_name,last_name,username,email,employee_position) values('" + userid + "','" + first_name + "','" + last_name +"','" + username+"','"
		 +email +"','"+position + "')"; 
                        
                        Statement sta1 = connection.createStatement();
                        int x1 = sta1.executeUpdate(query2);
                    if(x!=0) {
                    	JOptionPane.showMessageDialog(null,
                                "Welcome, Your account is sucessfully created. Go to login");
                    }
                        
                    }
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

			}
		});
		btnNewButton_4.setBounds(183, 402, 148, 28);
		panel_3.add(btnNewButton_4);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Are you sure you want to Exit?");
		lblNewLabel.setBounds(39, 77, 413, 114);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		panel_4.add(lblNewLabel);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Yes");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		rdbtnNewRadioButton.setBounds(99, 198, 109, 23);
		panel_4.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(99, 228, 109, 23);
		panel_4.add(rdbtnNo);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Register as Customer");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(110, 34, 221, 66);
		panel_2.add(lblNewLabel_2);
		
		username_txt_c = new JTextField();
		username_txt_c.setBounds(183, 111, 148, 20);
		panel_2.add(username_txt_c);
		username_txt_c.setColumns(10);
		
		password_txt_c = new JTextField();
		password_txt_c.setColumns(10);
		password_txt_c.setBounds(183, 142, 148, 20);
		panel_2.add(password_txt_c);
		
		first_name_txt_c = new JTextField();
		first_name_txt_c.setColumns(10);
		first_name_txt_c.setBounds(183, 173, 148, 20);
		panel_2.add(first_name_txt_c);
		
		last_name_txt_c = new JTextField();
		last_name_txt_c.setColumns(10);
		last_name_txt_c.setBounds(183, 216, 148, 20);
		panel_2.add(last_name_txt_c);
		
		email_txt_c = new JTextField();
		email_txt_c.setColumns(10);
		email_txt_c.setBounds(183, 259, 148, 20);
		panel_2.add(email_txt_c);
		
		JLabel lblNewLabel_4_1 = new JLabel("Username");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_1.setBounds(37, 114, 85, 20);
		panel_2.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Password");
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_2.setBounds(37, 142, 85, 20);
		panel_2.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_3 = new JLabel("FirstName");
		lblNewLabel_4_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_3.setBounds(37, 173, 65, 20);
		panel_2.add(lblNewLabel_4_3);
		
		JLabel lblNewLabel_4_4 = new JLabel("Lastname");
		lblNewLabel_4_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_4.setBounds(37, 216, 65, 20);
		panel_2.add(lblNewLabel_4_4);
		
		JLabel lblNewLabel_4_5 = new JLabel("Email Address");
		lblNewLabel_4_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_5.setBounds(37, 259, 110, 20);
		panel_2.add(lblNewLabel_4_5);
		
		address_txt_c = new JTextField();
		address_txt_c.setColumns(10);
		address_txt_c.setBounds(183, 307, 148, 20);
		panel_2.add(address_txt_c);
		
		JLabel lblNewLabel_4_5_2 = new JLabel("Address");
		lblNewLabel_4_5_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_5_2.setBounds(37, 307, 110, 20);
		panel_2.add(lblNewLabel_4_5_2);
		
		// Similar impleementation  to register for employees 
		JButton btnNewButton_4_1 = new JButton("Register");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				String first_name = first_name_txt_c.getText();
                String last_name =  last_name_txt_c.getText();
                String email = email_txt_c.getText();
                String username = username_txt_c.getText();
                String password = password_txt_c.getText();
                String address = address_txt_c.getText();
                String usertype = "Customers";
                try {
                	String url = "jdbc:postgresql://localhost/app_db";
                    Connection connection = (Connection)DriverManager.getConnection(url,"postgres","root");
                	
                    //first adding this to users
                    String query1 = "INSERT INTO users(username,password,user_type) values('" + username + "','" + password + "','" + usertype+"')";
         
                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query1);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(null, "This alredy exist");
                    } else {
                       
                        PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select * from users where username=? and password=?");

                            st.setString(1, username);
                            st.setString(2, password);
                            ResultSet rs = st.executeQuery();
                           rs.next();
                           int userid  = rs.getInt("userID");   
                    // create customers account
                    
  String query2 = "INSERT INTO customers(userid,first_name,last_name,username,email,address) values('" + userid + "','" + first_name + "','" + last_name +"','" + username+"','"
		 +email +"','"+address + "')"; 
                        
                        Statement sta1 = connection.createStatement();
                        int x1 = sta1.executeUpdate(query2);
                    if(x!=0) {
                    	JOptionPane.showMessageDialog(null,
                                "Welcome, Your account is sucessfully created. Go to login");
                    }
                        
                    }
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
				
			}
		});
		btnNewButton_4_1.setBounds(183, 378, 148, 30);
		panel_2.add(btnNewButton_4_1);
	}
}
