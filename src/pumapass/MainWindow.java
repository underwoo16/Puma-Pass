package pumapass;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pumapass.encryption.Cryptor;

public class MainWindow
{

	/**
	 * JFrame member variables
	 */
	private JFrame frame;
	private JTable table;
	private Profile prof;
	
	/**
	 * Other member variables
	 */
	Cryptor crypt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow()
	{
		this.setProfile();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setTitle("Puma-Pass - A Simple Java Password Manager");
		frame.setBounds(100, 100, 500, 450);
		frame.setMinimumSize(new Dimension(500, 300));
		ImageIcon img = new ImageIcon("res/logo.png");
		frame.setIconImage(img.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		table = new JTable();
		// setup columns
		String[] columns = new String[]{"URLs", "Usernames", "Passwords"};
		// setup fake rows
		String[] data = new String[]{"", "", ""};
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(columns);
		for (int x = 0; x < 25; x++)
		{
			dtm.addRow(data);
		}
		table.setModel(dtm);
		table.setCellSelectionEnabled(true);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnCopyCell = new JButton("Copy Cell");
		panel_1.add(btnCopyCell);
		
		JButton btnSaveChanges = new JButton("Save Changes");
		panel_1.add(btnSaveChanges);
		
		JButton btnDeleteRow = new JButton("Delete Row");
		panel_1.add(btnDeleteRow);
		
		JButton btnAddRow = new JButton("Add Row");
		panel_1.add(btnAddRow);
		
		/// Create Cryptor object with null key
		try
		{
			crypt = new Cryptor("default");
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(frame, "Cryptor object failed to initialize!", "FATAL ERROR - Cryptor Failed", JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException("Cryptor object failed to initialize!");
		}
		
		
	}
	
	public void setVisible(Boolean bc)
	{
		frame.setVisible(bc);
	}
	
	private void setProfile()
	{
		ProfileSelector ps = new ProfileSelector();
		ps.setModal(true);
		Profile prof = ps.showDialog();
		this.prof = prof;
	}

}
