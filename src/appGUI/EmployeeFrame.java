package appGUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Font;
//using rs2xml.jar file to easily load database into jtable. Other methods kept breaking.
import net.proteanit.sql.DbUtils;

public class EmployeeFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
    private JPopupMenu popupMenu;
    private JMenuItem menuItemAdd;
    private JMenuItem menuItemRemove;
    private JMenuItem menuItemRemoveAll;
    private JTable table_1;
    private JTextField jtf_search_2;
	/**
	 * Create the frame.
	 */
	public EmployeeFrame(String userName) {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 456);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 52, 616, 354);
		contentPane.add(tabbedPane);
		
		JPanel pending_tems_panel = new JPanel();
		pending_tems_panel.setBackground(Color.YELLOW);
		tabbedPane.addTab("Pending Orders", null, pending_tems_panel, null);
		pending_tems_panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 11, 557, 266);
		pending_tems_panel.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		try {
        
            ///String url = "jdbc:postgresql://localhost/app_db";
         	String url = "jdbc:postgresql://localhost:5432/app_db";

			
            Connection connection = (Connection)DriverManager.getConnection(url,"postgres","rtx");
            PreparedStatement st = (PreparedStatement)connection.prepareStatement("Select * from \"PendingOrders\"");
            ResultSet res = st.executeQuery();
            table_1.setModel(DbUtils.resultSetToTableModel(res));
            
            JButton Completed = new JButton("Completed");
            Completed.setBounds(111, 292, 89, 23);
            pending_tems_panel.add(Completed);
             connection.close();
             
        } catch (Exception exception) {
            exception.printStackTrace();
        }
		
				
		JPanel Modify_Items_panel = new JPanel();
		Modify_Items_panel.setBackground(new Color(127, 255, 0));
		tabbedPane.addTab("Modify Items", null, Modify_Items_panel, null);
		Modify_Items_panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 69, 541, 246);
		Modify_Items_panel.add(scrollPane);
		
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
	      JTextField jtf_search = new JTextField(15);
	      jtf_search.setBounds(87, 93, 138, 20);
	      JLabel searchLbl = new JLabel("Search");
	      searchLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
	      searchLbl.setBounds(11, 93, 78, 17);
	      
	      
	    tableModel = new DefaultTableModel(rowData, columnNames);
	    TableRowSorter sorter = new TableRowSorter<>(tableModel);
		
	    table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		getContentPane().add(searchLbl);
	    getContentPane().add(jtf_search);
	      
		// constructs the popup menu
        popupMenu = new JPopupMenu();
        menuItemAdd = new JMenuItem("Add New Item");
        menuItemRemove = new JMenuItem("Remove Current item");
        menuItemRemoveAll = new JMenuItem("Remove All Items");
         
       
        menuItemAdd.addActionListener(this);
        menuItemRemove.addActionListener(this);
        menuItemRemoveAll.addActionListener(this);
       
        popupMenu.add(menuItemAdd);
        popupMenu.add(menuItemRemove);
        popupMenu.add(menuItemRemoveAll);
		
     // sets the popup menu for the table
        table.setComponentPopupMenu(popupMenu);
        
        jtf_search_2 = new JTextField();
        jtf_search_2.setBounds(214, 34, 160, 20);
        Modify_Items_panel.add(jtf_search_2);
        jtf_search_2.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Search");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_1.setBounds(108, 37, 65, 14);
        Modify_Items_panel.add(lblNewLabel_1);
       table.addMouseListener(new MouseAdapter() {    	   
       
       });
        
		JButton btnNewButton = new JButton("LogOut");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		btnNewButton.setBounds(498, 14, 128, 30);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("User :"+userName);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(251, 14, 145, 27);
		contentPane.add(lblNewLabel);
	}
	
	
	
	private void addNewRow() {
        tableModel.addRow(new String[0]);
    }
     
    private void removeCurrentRow() {
        int selectedRow = table.getSelectedRow();
        tableModel.removeRow(selectedRow);
    }
     
    private void removeAllRows() {
        int rowCount = tableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            tableModel.removeRow(0);
        }
    }
	
	@Override
	 public void actionPerformed(ActionEvent event) {
        JMenuItem menu = (JMenuItem) event.getSource();
        if (menu == menuItemAdd) {
            addNewRow();
        } else if (menu == menuItemRemove) {
            removeCurrentRow();
        } else if (menu == menuItemRemoveAll) {
            removeAllRows();
        }
    }

	

}
