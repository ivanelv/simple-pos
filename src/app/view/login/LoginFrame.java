package app.view.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.controller.AuthController;
import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.factory.TextFieldFactory;
import app.view.custom_component.MyColor;
import app.view.main.MainDialog;
import util.FileHelper;

public class LoginFrame extends JFrame implements ActionListener, ILoginFrame
{
	private JButton btnLogin;
	private JLabel lblTitle, lblUsername, lblPassword;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private MainDialog mainDialog;

	public LoginFrame()
	{
		setTitle("SIVle POS");
		setSize(500, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(new ImageIcon(FileHelper.getAssetsPath() + "/logo-colored.png").getImage());
		getRootPane().setDefaultButton(getLoginButton());
		initializeComponent();
	}

	public void initializeComponent()
	{
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		JPanel panel = new JPanel(layout);
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(new Insets(16, 16, 16, 16)));

		/**
		 * Title
		 */
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 7;
		c.weightx = 1;
		panel.add(getTitleLabel(), c);

		/**
		 * Input Fields
		 */
		JPanel usernamePanel = new JPanel(new BorderLayout());
		usernamePanel.setBackground(Color.WHITE);
		usernamePanel.add(getUsernameLabel(), BorderLayout.NORTH);
		usernamePanel.add(Box.createVerticalStrut(5));
		usernamePanel.add(getUsernameField(), BorderLayout.SOUTH);

		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 2;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(usernamePanel, c);

		JPanel passwordPanel = new JPanel(new BorderLayout());
		passwordPanel.setBackground(Color.WHITE);
		passwordPanel.add(getPasswordLabel(), BorderLayout.NORTH);
		passwordPanel.add(Box.createVerticalStrut(5));
		passwordPanel.add(getPasswordField(), BorderLayout.SOUTH);

		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 2;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(passwordPanel, c);

		c.gridy = 6;
		c.gridheight = 1;
		panel.add(getLoginButton(), c);

		this.add(panel, BorderLayout.CENTER);

		if (mainDialog == null)
			mainDialog = new MainDialog();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnLogin)
		{
			String errorMessage = AuthController.login(txtUsername.getText(), new String(txtPassword.getPassword()));

			if (errorMessage.isEmpty())
			{
				this.setVisible(false);
				txtUsername.setText("");
				txtUsername.requestFocus();
				txtPassword.setText("");
				
				mainDialog.setVisible(true);
				LoginFrame.this.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(null, errorMessage, "Stop", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public JButton getLoginButton()
	{
		if (btnLogin == null)
		{
			btnLogin = ButtonFactory.getInstance().create("Login");
			btnLogin.setBackground(MyColor.getPrimaryBackground());
			btnLogin.setForeground(Color.WHITE);
			btnLogin.addActionListener(this);
		}
		return btnLogin;
	}

	@Override
	public JLabel getTitleLabel()
	{
		if (lblTitle == null)
		{
			lblTitle = new JLabel();
			lblTitle.setIcon(new ImageIcon(FileHelper.getAssetsPath() + "/logo.png"));
		}

		return lblTitle;
	}

	@Override
	public JLabel getUsernameLabel()
	{
		if (lblUsername == null)
			lblUsername = LabelFactory.getInstance().create("Username");

		return lblUsername;
	}

	@Override
	public JLabel getPasswordLabel()
	{
		if (lblPassword == null)
			lblPassword = LabelFactory.getInstance().create("Password");

		return lblPassword;
	}

	@Override
	public JTextField getUsernameField()
	{
		if (txtUsername == null)
			txtUsername = TextFieldFactory.getInstance().create();

		return txtUsername;
	}

	@Override
	public JPasswordField getPasswordField()
	{
		if (txtPassword == null)
			txtPassword = (JPasswordField) TextFieldFactory.getInstance().create(true);

		return txtPassword;
	}

}
