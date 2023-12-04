package dc;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import javax.swing.border.EmptyBorder;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class User_Login extends JFrame {
	private JPanel contentPane;
	private JTextField tid;
	private JPasswordField tpwd;
	String id, pwd;
	JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_Login frame = new User_Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public User_Login() {
		setTitle("User Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 329);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel pnl_btn = new JPanel();
		pnl_btn.setBackground(new Color(0, 0, 255));
		pnl_btn.setBounds(0, 22, 540, 201);
		contentPane.add(pnl_btn);
		pnl_btn.setLayout(null);
		tid = new JTextField();
		tid.setBounds(146, 103, 259, 29);
		pnl_btn.add(tid);
		tid.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tid.setColumns(10);
		tpwd = new JPasswordField();
		tpwd.setBounds(146, 64, 259, 29);
		pnl_btn.add(tpwd);
		tpwd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tpwd.setColumns(10);
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(28, 64, 104, 29);
		pnl_btn.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		JLabel lblChartNo = new JLabel("User");
		lblChartNo.setForeground(new Color(255, 255, 255));
		lblChartNo.setBounds(28, 108, 104, 29);
		pnl_btn.add(lblChartNo);
		lblChartNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblChartNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogin = new JButton("login ");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogin.setBounds(182, 145, 191, 29);
		pnl_btn.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tid.getText().equals("admin") && tpwd.getText().equals("admin")) {
					MainMenu a = new MainMenu();
					a.setVisible(true);
				}
			}
		});
	}// end of constructor
}
