package pumapass;

import java.awt.BorderLayout;
import pumapass.Profile;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JList;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPasswordField;
import java.awt.Choice;
import java.awt.Dimension;
import pumapass.datastorage.Vault;

import javax.swing.JLabel;

public class ProfileSelector extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private ArrayList<String> profileNames;
	
	public Profile showDialog()
	{
		this.setVisible(true);
		return null;
	}

	/**
	 * Create the dialog.
	 */
	public ProfileSelector() {
		setTitle("Puma-Pass - Profile Select");
		setBounds(100, 100, 250, 135);
		setMinimumSize(new Dimension(250, 135));
		ImageIcon img = new ImageIcon("res/logo.png");
		setIconImage(img.getImage());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//
		Choice choice = new Choice();
		choice.setBounds(91, 10, 100, 20);
		contentPanel.add(choice);
		Vault.checkManifest();
		this.profileNames = Vault.getProfileList();
		if (profileNames != null)
		{
			for (int z = 0; z < profileNames.size(); z++)
			{
				choice.addItem(profileNames.get(z));
			}
		}
		
		passwordField = new JPasswordField();
		passwordField.setBounds(91, 36, 100, 20);
		contentPanel.add(passwordField);
		
		JLabel lblProfile = new JLabel("Profile:");
		lblProfile.setBounds(10, 10, 75, 14);
		contentPanel.add(lblProfile);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 36, 75, 14);
		contentPanel.add(lblPassword);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnCancel = new JButton("Cancel");
			buttonPane.add(btnCancel);
			
			JButton btnCreateNew = new JButton("Create New");
			buttonPane.add(btnCreateNew);
			
			JButton btnLogin = new JButton("Login");
			buttonPane.add(btnLogin);
		}
	}
}
