package appGUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import jdbc_sql.JDBCPostgreSQLConnection;

public class ShoppingCart extends JFrame {

	private JTextField jtf_search;
	   private JLabel searchLbl;
	   private TableModel model;
	   private JTable items_table;
	   private TableRowSorter sorter;
	   private JScrollPane items_jsp;
	   private JButton CheckOut_btn;
	   private JLabel items_label;
	   private JTable cart_table;
	   
	   
	   public ShoppingCart(String username) {
		//   public ShoppingCart() {
		  //super();
	      setTitle("Customer Shopping");
	      jtf_search = new JTextField(15);
	      jtf_search.setBounds(87, 93, 138, 20);
	      searchLbl = new JLabel("Search");
	      searchLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
	      searchLbl.setBounds(11, 93, 78, 17);
	      String[] columnNames = {"Item", "Price/Unit"};
	      Object[][] rowData = {{"Milk (regular)(1 gallon)","3.23 $"},
	    		  {"White Bread (1 lb)", "2.48 $"},
	    		  {"Chicken Fillets (1 lb)","	4.13 $"},
	    		  {"Meat (1 lb)","5.69 $"},
	    		  {"Apples (1 lb)","	2.05 $"},
	    		  {"Banana (1 lb)","	0.71 $"},
	    		  {"Oranges (1 lb)","1.81 $"},
	    		  {"Tomato (1 lb)","	1.93 $"},
	    		  {"Potato (1 lb)","	1.17 $"},
	    		  {"Onion (1 lb)","	1.18 $"},
	    		  {"Lettuce (1 head)","1.64 $"},
	    		  {"Water (1.5 liter bottle)","1.81 $"},
	    		  {"Bottle of Wine (Mid-Range)","	12.00 $"},
	    		  {"Domestic Beer (0.5 liter bottle)","	2.42 $"},
	    		  {"Imported Beer (12 oz small bottle)","2.82 $"},
	    		  {"Cigarettes 20 Pack (Marlboro)","	8.00 $"},
	      };
	      model = new DefaultTableModel(rowData, columnNames);
	      sorter = new TableRowSorter<>(model);
	      items_table = new JTable(model);
	      
	      items_table.setFocusable(false);
	      items_table.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	                 // to detect doble click events
	               JTable target = (JTable)me.getSource();
	               int row = target.getSelectedRow(); // select a row
	               int column = target.getSelectedColumn(); // select a column
	               String item = (String) items_table.getValueAt(row,0);
	              int a = JOptionPane.showConfirmDialog(null,"Do you want to add "+item+"to cart?","Add to cart",0);
	         if (a==JOptionPane.YES_OPTION) {
	        	 String quantity  = JOptionPane.showInputDialog("Enter Quantity");
	         //System.out.println(quantity+" of "+item+" added to cart.");
	        	 items_label.setText(quantity+" of "+item+" added to cart.");
	        	 
	         }
	         }
	      });
	      
	      
	      items_table.setRowSorter(sorter);
	      items_jsp = new JScrollPane(items_table);
	      items_jsp.setBounds(11, 121, 285, 158);
	      getContentPane().setLayout(null);
	      getContentPane().add(searchLbl);
	      getContentPane().add(jtf_search);
	      getContentPane().add(items_jsp);
	      
	      JPanel panel = new JPanel();
	      panel.setBackground(Color.YELLOW);
	      panel.setBounds(369, 11, 334, 369);
	      getContentPane().add(panel);
	      panel.setLayout(null);
	    
	      JLabel lblNewLabel = new JLabel("Shopping Cart");
	      lblNewLabel.setBounds(107, 11, 131, 40);
	      lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
	      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	      panel.add(lblNewLabel);
	      
	      CheckOut_btn = new JButton("CheckOut");
	      CheckOut_btn.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      	JOptionPane.showMessageDialog(panel,"Your order for number items ($ price ) was completed successfully.");
	      	dispose();
	      	}
	      });
	      CheckOut_btn.setBounds(107, 318, 98, 40);
	      panel.add(CheckOut_btn);
	      
	      //this is to be loaded from saved database 
	      cart_table = new JTable();
	      cart_table.setBounds(10, 62, 314, 243);
	      panel.add(cart_table);
	      //model = new DefaultTableModel(rowData, columnNames);
	      final String[] columns = new String[]{"Item","Quantity","Price"};
	      String[][] data = new String[][]{new String[]{"","",""}};
	      
	      cart_table.setModel(new DefaultTableModel(data,columns));
	      
	      
	      
	      JButton Logout_btn = new JButton("Logout");
	      Logout_btn.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      	dispose();
	      	}
	      });
	      Logout_btn.setBounds(11, 11, 89, 31);
	      getContentPane().add(Logout_btn);
	      
	      items_label = new JLabel(".");
	      items_label.setBounds(11, 300, 285, 63);
	      getContentPane().add(items_label);
	      
	      JLabel User_info_lbl = new JLabel(".");
	      User_info_lbl.setText("Welcome "+username);
	      
	      User_info_lbl.setBounds(128, 19, 175, 63);
	      getContentPane().add(User_info_lbl);
	      jtf_search.getDocument().addDocumentListener(new DocumentListener() {
	         @Override
	         public void insertUpdate(DocumentEvent e) {
	            search(jtf_search.getText());
	         }
	         @Override
	         public void removeUpdate(DocumentEvent e) {
	            search(jtf_search.getText());
	         }
	         @Override
	         public void changedUpdate(DocumentEvent e) {
	            search(jtf_search.getText());
	         }
	         public void search(String str) {
	            if (str.length() == 0) {
	               sorter.setRowFilter(null);
	            } else {
	               sorter.setRowFilter(RowFilter.regexFilter(str));
	            }
	         }
	      });
	      setSize(734, 429);
	      setDefaultCloseOperation(EXIT_ON_CLOSE);
	      setLocationRelativeTo(null);
	      setResizable(false);
	      setVisible(true);
	   }	
}
