package pumaPass;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import pumaPass.pumaUtils.Cryptor;
import pumaPass.pumaUtils.FileUtil;

public class PumaPass {

	private JFrame frmPumapassA;
	private JPasswordField passwordField;
	private JTextField textField;
	private JPasswordField passwordField_1;
	private JTable table;
	private String[] profileNames;
	private String curKey;
	private String curProfSelected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					PumaPass window = new PumaPass();
					window.frmPumapassA.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PumaPass() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPumapassA = new JFrame();
		frmPumapassA.setResizable(false);
		frmPumapassA.setTitle("Puma-Pass - A Simple Java Password Manager");
		frmPumapassA.setFont(new Font("Arial", Font.PLAIN, 12));
		frmPumapassA.setBounds(100, 100, 440, 500);
		frmPumapassA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// setup variables
		
		
		/// Create Cryptor object with null key
		Cryptor crypt;
		try
		{
			crypt = new Cryptor("default");
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(frmPumapassA, "Cryptor object failed to initialize!", "FATAL ERROR - Cryptor Failed", JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException("Cryptor object failed to initialize!");
		}
		
		JPanel panel = new JPanel();
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Profile Selection");
		panel.setBorder(title);
		
		JPanel panel_1 = new JPanel();
		panel.setLayout(null);
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(16, 38, 90, 20);
		panel.add(comboBox);
		profileNames = FileUtil.readSaveFilenames();
		/// Populate comboBox with the names of the saves in the Saves sub-folder
		if (profileNames != null)
		{
			for (int z = 0; z < profileNames.length; z++)
			{
				comboBox.addItem(profileNames[z].substring(0, profileNames[z].length() - 4));
			}
		}
		
		JLabel lblChooseProfile = new JLabel("Choose Profile");
		lblChooseProfile.setBounds(16, 18, 96, 14);
		panel.add(lblChooseProfile);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(122, 38, 104, 20);
		passwordField.setText("************");
		panel.add(passwordField);
		
		JLabel lblEnterPassKey = new JLabel("Enter Pass Key");
		lblEnterPassKey.setBounds(122, 18, 118, 14);
		panel.add(lblEnterPassKey);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setBounds(250, 38, 90, 23);
		panel.add(btnDecrypt);
		
		JLabel lblDecryptAndDisplay = new JLabel("Decrypt and Display");
		lblDecryptAndDisplay.setBounds(250, 18, 154, 14);
		panel.add(lblDecryptAndDisplay);
		TitledBorder title2;
		title2 = BorderFactory.createTitledBorder("Profile Creation");
		panel_1.setBorder(title2);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(16, 38, 90, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblProfileName = new JLabel("Profile Name");
		lblProfileName.setBounds(16, 18, 96, 14);
		panel_1.add(lblProfileName);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(122, 38, 104, 20);
		passwordField_1.setText("************");
		panel_1.add(passwordField_1);
		
		JLabel lblDesiredPassKey = new JLabel("Desired Pass Key");
		lblDesiredPassKey.setBounds(122, 18, 118, 14);
		panel_1.add(lblDesiredPassKey);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(250, 37, 96, 23);
		panel_1.add(btnCreate);
		
		JLabel lblCreateNewProfile = new JLabel("Create New Profile");
		lblCreateNewProfile.setBounds(250, 18, 154, 14);
		panel_1.add(lblCreateNewProfile);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, frmPumapassA.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -288, SpringLayout.SOUTH, frmPumapassA.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, -10, SpringLayout.EAST, frmPumapassA.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, frmPumapassA.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -369, SpringLayout.SOUTH, frmPumapassA.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, frmPumapassA.getContentPane());
		frmPumapassA.getContentPane().setLayout(springLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 29, SpringLayout.SOUTH, panel_1);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, panel);
		frmPumapassA.getContentPane().add(scrollPane);
		
		// setup columns
		String[] columns = new String[]{"URLs", "Usernames", "Passwords"};
		// setup fake rows
		String[][] data = new String[][]{{"http://www.google.com", "username", "pass"}, 
			{"http://www.google.com", "username", "pass"}, {"http://www.google.com", "username", "pass"}, {"http://www.google.com", "username", "pass"}, {"http://www.google.com", "username", "pass"}, {"http://www.google.com", "username", "pass"},
			{"http://www.google.com", "username", "pass"}, {"http://www.google.com", "username", "pass"}, {"http://www.google.com", "username", "pass"}, {"http://www.google.com", "username", "pass"}, {"http://www.google.com", "username", "pass"},
			{"http://www.google.com", "username", "pass"}, {"http://www.google.com", "username", "pass"}, {"http://www.google.com", "username", "pass"}, {"http://www.google.com", "username", "pass"}, {"http://www.google.com", "username", "pass"}};
		
		table = new JTable();
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(columns);
		table.setModel(dtm);
		table.setCellSelectionEnabled(true);
		for (int x = 0; x < 15; x++)
		{
			dtm.addRow(data[x]);
		}
		scrollPane.setViewportView(table);
		frmPumapassA.getContentPane().add(panel);
		frmPumapassA.getContentPane().add(panel_1);
		
		JMenuBar menuBar = new JMenuBar();
		springLayout.putConstraint(SpringLayout.WEST, menuBar, 0, SpringLayout.WEST, frmPumapassA.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, menuBar, 0, SpringLayout.EAST, frmPumapassA.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, menuBar);
		springLayout.putConstraint(SpringLayout.NORTH, menuBar, 0, SpringLayout.NORTH, frmPumapassA.getContentPane());
		frmPumapassA.getContentPane().add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		springLayout.putConstraint(SpringLayout.NORTH, mnFile, 0, SpringLayout.NORTH, frmPumapassA.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, mnFile, 179, SpringLayout.EAST, menuBar);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmExportProfile = new JMenuItem("Export Profile");
		mnFile.add(mntmExportProfile);
		
		JMenuItem mntmDeleteProfile = new JMenuItem("Delete Profile");
		mnFile.add(mntmDeleteProfile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnPasswordGenerator = new JMenu("Password Generator");
		menuBar.add(mnPasswordGenerator);
		
		JMenuItem mntmRandomPassGen = new JMenuItem("Random Pass Gen");
		mnPasswordGenerator.add(mntmRandomPassGen);
		
		JMenuItem mntmDicewarePassGen = new JMenuItem("Diceware Pass Gen");
		mnPasswordGenerator.add(mntmDicewarePassGen);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		
		JButton btnCopyToClipboard = new JButton("Copy To Clipboard");
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -6, SpringLayout.NORTH, btnCopyToClipboard);
		springLayout.putConstraint(SpringLayout.WEST, btnCopyToClipboard, 10, SpringLayout.WEST, frmPumapassA.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnCopyToClipboard, -10, SpringLayout.SOUTH, frmPumapassA.getContentPane());
		frmPumapassA.getContentPane().add(btnCopyToClipboard);
		
		JButton btnAddRow = new JButton("Add Row");
		springLayout.putConstraint(SpringLayout.NORTH, btnAddRow, 0, SpringLayout.NORTH, btnCopyToClipboard);
		springLayout.putConstraint(SpringLayout.EAST, btnAddRow, -10, SpringLayout.EAST, frmPumapassA.getContentPane());
		frmPumapassA.getContentPane().add(btnAddRow);
		
		JButton btnRemoveRow = new JButton("Remove Row");
		springLayout.putConstraint(SpringLayout.NORTH, btnRemoveRow, 0, SpringLayout.NORTH, btnCopyToClipboard);
		springLayout.putConstraint(SpringLayout.EAST, btnRemoveRow, -6, SpringLayout.WEST, btnAddRow);
		frmPumapassA.getContentPane().add(btnRemoveRow);
		
		JLabel lblCurrentProfileDisplayed = new JLabel("Current Profile Displayed  >  NONE");
		curProfSelected = null;
		springLayout.putConstraint(SpringLayout.NORTH, lblCurrentProfileDisplayed, 6, SpringLayout.SOUTH, panel_1);
		springLayout.putConstraint(SpringLayout.WEST, lblCurrentProfileDisplayed, 0, SpringLayout.WEST, scrollPane);
		frmPumapassA.getContentPane().add(lblCurrentProfileDisplayed);
		
		JButton btnSaveChanges = new JButton("Save Changes");
		springLayout.putConstraint(SpringLayout.NORTH, btnSaveChanges, 6, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, btnSaveChanges, 6, SpringLayout.EAST, btnCopyToClipboard);
		springLayout.putConstraint(SpringLayout.EAST, btnSaveChanges, -10, SpringLayout.WEST, btnRemoveRow);
		frmPumapassA.getContentPane().add(btnSaveChanges);
		
		
		/// Action Listeners
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getItemCount() == 0)
				{
					JOptionPane.showMessageDialog(frmPumapassA, "There are no profiles to decrypt.", "Warning - No Profiles", JOptionPane.WARNING_MESSAGE);
				}
				else if(passwordField.getPassword().length == 0)
				{
					JOptionPane.showMessageDialog(frmPumapassA, "There is no pass key entered.", "Warning - No Pass Key", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					String[] names;
					String profName = (String)comboBox.getSelectedItem();
					// get the encrypted names/passes
					try
					{
						String[] namesAndPasses = FileUtil.readRecords(profName);
						names = namesAndPasses;
					}
					catch (Exception e2)
					{
						JOptionPane.showMessageDialog(frmPumapassA, "FileUtil failed to load profile!", "FATAL ERROR - FileUtil Failed", JOptionPane.ERROR_MESSAGE);
						throw new RuntimeException("FileUtil failed to load profile!");
					}
					// set the Cryptor's key
					try
					{
						curKey = new String(passwordField.getPassword());
						crypt.setKey(curKey);
					}
					catch (Exception e3)
					{
						JOptionPane.showMessageDialog(frmPumapassA, "Cryptor failed to set new key!", "FATAL ERROR - Cryptor Failed", JOptionPane.ERROR_MESSAGE);
						throw new RuntimeException("Cryptor failed to set new key!");
					}
					// decrypt data
					try
					{
						names = crypt.decryptArray(names);
					}
					catch (Exception e4)
					{
						JOptionPane.showMessageDialog(frmPumapassA, "Cryptor failed to decrypt data!", "FATAL ERROR - Cryptor Failed", JOptionPane.ERROR_MESSAGE);
						throw new RuntimeException("Cryptor failed to decrypt data!");
					}
					if (names[0].equals("INCORRECT KEY"))
					{
						JOptionPane.showMessageDialog(frmPumapassA, "The pass key you entered was incorrect or invalid!", "WARNING - Incorrect Password", JOptionPane.WARNING_MESSAGE);
					}
					else // - good pass key
					{
						// add data to the table
						// but first clear the table
						int rows = dtm.getRowCount();
						for (int g = 0; g < rows; g++)
						{
							dtm.removeRow(0);
						}
						// now fill the table
						for (int s = 0; s < (names.length / 3); s++)
						{
							String[] rowData = {names[s * 3], names[(s * 3) + 1], names[(s * 3) + 2]};
							dtm.addRow(rowData);
						}
						// set current profile displayed
						lblCurrentProfileDisplayed.setText("Current Profile Displayed  >  " + profName);
						curProfSelected = profName;
					} // end else - good pass key
					
				}
			}
		}); // end btnDecrypt action listener
		
		
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] blankRow = {"", "", ""};
				dtm.addRow(blankRow);
			}
		}); // end btnAddRow action listener
		
		
		btnRemoveRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dtm.getRowCount() > 0)
					dtm.removeRow(dtm.getRowCount() - 1);
			}
		}); // end btnRemoveRow action listener
		
		
		btnCopyToClipboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isselected = false;
				for (int k = 0; k < dtm.getRowCount(); k++)
				{
					for (int j = 0; j < dtm.getColumnCount(); j++)
					{
						if (table.isCellSelected(k, j))
							isselected = true;
					}
				}
				
				if (isselected == true)
				{
					String selectedStr = (String)dtm.getValueAt(table.getSelectedRow(), table.getSelectedColumn());  
					StringSelection stringSelection = new StringSelection(selectedStr);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
				}
			}
		}); // end btnCopyToClipboard action listener
		
		
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newProf = textField.getText();
				if (newProf == null || newProf.length() < 1)
				{
					JOptionPane.showMessageDialog(frmPumapassA, "You need to enter a profile name!", "WARNING - No Profile Entered", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					boolean isValid = FileUtil.checkValidFN(newProf);
					boolean nameTaken = false;
					for (int y = 0; y < profileNames.length; y++)
					{
						if (newProf.equalsIgnoreCase(profileNames[y]))
							nameTaken = true;
					}
					if (isValid && !nameTaken)
					{
						// clear the table and setup some blank rows
						int rows = dtm.getRowCount();
						for (int g = 0; g < rows; g++)
						{
							dtm.removeRow(0);
						}
						String[] blankRow = {"", "", ""};
						for (int k = 0; k < 12; k++)
						{
							dtm.addRow(blankRow);
						}
						// set the Cryptor key
						try
						{
							curKey = new String(passwordField_1.getPassword());
							crypt.setKey(curKey);
						}
						catch (Exception e3)
						{
							JOptionPane.showMessageDialog(frmPumapassA, "Cryptor failed to set new key!", "FATAL ERROR - Cryptor Failed", JOptionPane.ERROR_MESSAGE);
							throw new RuntimeException("Cryptor failed to set new key!");
						}
						// set current profile displayed
						lblCurrentProfileDisplayed.setText("Current Profile Displayed  >  " + newProf);
						curProfSelected = newProf;
					}
					else if (!isValid)
					{
						JOptionPane.showMessageDialog(frmPumapassA, "You need to enter and alphanumeric profile name!", "WARNING - Invalid Profile Name", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(frmPumapassA, "That profile name already exist!", "WARNING - Invalid Profile Name", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		}); // end btnCreate action listener
		
		
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (curProfSelected == null || curKey == null)
				{
					JOptionPane.showMessageDialog(frmPumapassA, "No profile selected! Please load or create profile first.", "WARNING - No Profile Selected", JOptionPane.WARNING_MESSAGE);
				}
				else if (table.getRowCount() == 0)
				{
					JOptionPane.showMessageDialog(frmPumapassA, "Table is empty! Table must have contents to save.", "WARNING - Empty Table", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					boolean isEmpty = true;
					// check if the rows and cols are empty
					for (int p = 0; p < table.getRowCount(); p++)
					{
						for (int q = 0; q < table.getColumnCount(); q++)
						{
							if (((String)table.getValueAt(p, q)).length() > 0)
								isEmpty = false;
						}
					}
					if (isEmpty)
					{
						JOptionPane.showMessageDialog(frmPumapassA, "Table is empty! Table must have contents to save.", "WARNING - Empty Table", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						// check for rows that are partially completed
						boolean incompleteRows = false;
						int validRows = 0;
						for (int k = 0; k < table.getRowCount(); k++)
						{
							if ((((String)table.getValueAt(k, 0)).length() == 0) || (((String)table.getValueAt(k, 1)).length() == 0) || (((String)table.getValueAt(k, 2)).length() == 0))
							{
								incompleteRows = true;
								if ((((String)table.getValueAt(k, 0)).length() == 0) && (((String)table.getValueAt(k, 1)).length() == 0) && (((String)table.getValueAt(k, 2)).length() == 0))
								{
									incompleteRows = false;
								}
							}
							else
							{
								validRows++;
							}
						}
						if (incompleteRows)
						{
							JOptionPane.showMessageDialog(frmPumapassA, "Table has incomplete rows! Table must have complete rows or empty rows.", "WARNING - Incomplete Rows", JOptionPane.WARNING_MESSAGE);
						}
						else
						{
							// set Cryptor key again for good measure - is it redundant? yeah probably, but I'm okay with that.
							try
							{
								crypt.setKey(curKey);
							}
							catch (Exception e3)
							{
								JOptionPane.showMessageDialog(frmPumapassA, "Cryptor failed to set new key!", "FATAL ERROR - Cryptor Failed", JOptionPane.ERROR_MESSAGE);
								throw new RuntimeException("Cryptor failed to set new key!");
							}
							// get strings from table
							String[] dataToEncrypt = new String[validRows * 3];
							int curCell = 0;
							for (int y = 0; y < validRows; y++)
							{
								if ((((String)table.getValueAt(y, 0)).length() > 0) && (((String)table.getValueAt(y, 1)).length() > 0) && (((String)table.getValueAt(y, 2)).length() > 0))
								{
									for (int f = 0; f < 3; f++)
									{
										dataToEncrypt[curCell] = (String)table.getValueAt(y, f);
										curCell++;
									}
								}
							}
							// encrypt table contents
							String[] dataPostEncrypt;
							try
							{
								dataPostEncrypt = crypt.encryptArray(dataToEncrypt);
							}
							catch (Exception e4)
							{
								JOptionPane.showMessageDialog(frmPumapassA, "Cryptor failed to encrypt data!", "FATAL ERROR - Cryptor Failed", JOptionPane.ERROR_MESSAGE);
								throw new RuntimeException("Cryptor failed to encrypt data!");
							}
							// write encrypted String[] to file specified by curProfSelected
							try
							{
								FileUtil.writeRecords(curProfSelected, dataPostEncrypt);
							}
							catch (Exception e5)
							{
								JOptionPane.showMessageDialog(frmPumapassA, "FileUtil failed to save data!", "FATAL ERROR - FileUtil Failed", JOptionPane.ERROR_MESSAGE);
								throw new RuntimeException("FileUtil failed to save data!");
							}
							// add profile to profileNames
							boolean alreadyAdded = false;
							for (int x = 0; x < profileNames.length; x++)
							{
								if (curProfSelected.equalsIgnoreCase(profileNames[x]))
									alreadyAdded = true;
							}
							if (!alreadyAdded)
							{
								String[] temp = new String[profileNames.length + 1];
								for (int p = 0; p < profileNames.length; p++)
								{
									temp[p] = profileNames[p];
								}
								temp[temp.length - 1] = curProfSelected;
								profileNames = temp;
								// add profile to dropdown list
								comboBox.addItem(curProfSelected);
							}
						}
					}
				}
				
			}
		}); // end btnSaveChanges action listener
		
		
		/// Menu Bar Action Listeners
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		}); // end mntmExit action listener
		
		
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmPumapassA, "                  Puma-Pass V0.00\n\u00a9 Chandler Underwood and Cody Machine\n          GNU GENERAL PUBLIC LICENSE", "About Puma-Pass", JOptionPane.INFORMATION_MESSAGE);
			}
		}); // end mntmAbout action listener
		
		
	}
}
