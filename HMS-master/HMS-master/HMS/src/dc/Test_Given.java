package dc;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
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

public class Test_Given extends JFrame {
	private JPanel contentPane;
	private JTextField test_rec;
	private JTextField test_reg;
	private JTextField test_name;
	JComboBox testname;
	int testno;
	int testna;
	Connection conn = null;
	Statement stmt = null;
	String arr[] = new String[] { "Select Option", "fever", "Tv", "cancer", "other" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test_Given frame = new Test_Given();
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
	public Test_Given() {
		setTitle("Test_Given");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 424);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel pnl_btn = new JPanel();
		pnl_btn.setBackground(SystemColor.activeCaption);
		pnl_btn.setBounds(551, 106, 181, 271);
		contentPane.add(pnl_btn);
		pnl_btn.setLayout(null);
		JButton btn_new = new JButton("New");
		btn_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				clean();
			}
		});
		btn_new.setToolTipText("Add New Record");
		btn_new.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_new.setBounds(49, 45, 95, 33);
		pnl_btn.add(btn_new);
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testno = Integer.parseInt(test_rec.getText());
				int testna = Integer.parseInt(test_reg.getText());
				String n1 = (String) testname.getSelectedItem();
				String insert = "INSERT INTO `test_given`(`Rec_no`,`Reg_no`,`Test_name`) VALUES (" + testno + ","
						+ testna + ",'" + n1 + "')";
				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctor", "root", "ROOT");
					stmt = conn.createStatement();
					stmt.execute(insert);
					JOptionPane.showMessageDialog(null, "Record Saved Succesfully!");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "cannot be Saved!");
				}
			}
		});
		btnSave.setToolTipText("Save this record");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSave.setBounds(49, 88, 95, 33);
		pnl_btn.add(btnSave);
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				clean();
				System.out.println("deleted...");
				String test = JOptionPane.showInputDialog("Enter the Test No to search the record");
				testno = Integer.parseInt(test);
				try {
					String delete = "DELETE FROM `test_given` WHERE Rec_no=" + testno;
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctor", "root", "ROOT");
					stmt = conn.createStatement();
					stmt.execute(delete);
					JOptionPane.showMessageDialog(null, "Record Deleted Succesfully!");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Deletion failed!" + e);
				}
				clean();
			}
		});
		btnDelete.setToolTipText("Delete selected record");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(49, 131, 95, 33);
		pnl_btn.add(btnDelete);
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchRec();
			}
		});
		btnSearch.setToolTipText("Search a record");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSearch.setBounds(49, 174, 95, 33);
		pnl_btn.add(btnSearch);
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(DISPOSE_ON_CLOSE);
			}
		});
		btnExit.setToolTipText("Close this window");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(49, 217, 95, 33);
		pnl_btn.add(btnExit);
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 732, 96);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("Entery of Test Given");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 0, 724, 86);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewLabel.setBackground(Color.BLUE);
		JLabel lblNewLabel_1 = new JLabel("Test Record No");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(34, 134, 191, 29);
		contentPane.add(lblNewLabel_1);
		JLabel lblRegistrationNo = new JLabel("Registation no.");
		lblRegistrationNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistrationNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegistrationNo.setBounds(34, 173, 191, 29);
		contentPane.add(lblRegistrationNo);
		JLabel lblChartNo = new JLabel("Test Name");
		lblChartNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblChartNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChartNo.setBounds(34, 217, 191, 29);
		contentPane.add(lblChartNo);
		test_rec = new JTextField();
		test_rec.setFont(new Font("Tahoma", Font.PLAIN, 18));
		test_rec.setBounds(241, 131, 259, 29);
		contentPane.add(test_rec);
		test_rec.setColumns(10);
		test_reg = new JTextField();
		test_reg.setFont(new Font("Tahoma", Font.PLAIN, 18));
		test_reg.setColumns(10);
		test_reg.setBounds(241, 172, 259, 29);
		contentPane.add(test_reg);
		testname = new JComboBox(arr);
		testname.setFont(new Font("Tahoma", Font.BOLD, 14));
		testname.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		testname.setBackground(Color.WHITE);
		testname.setBounds(240, 217, 260, 29);
		contentPane.add(testname);
	}// end of constructor

	private void searchRec() {
		String testno = JOptionPane.showInputDialog("Enter the Test No to search the record");
		try {
			Gamer g = new Gamer();
			Connection con = g.setGame();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM `test_found` where Rec_no=" + Integer.parseInt(testno));
			while (rs.next()) {
				test_rec.setText(rs.getInt(1) + "");
				LoadReg_no();
				test_reg.setText(rs.getString(2) + "");
				testname.setSelectedItem(rs.getString(3).toString());
			}
			rs.close();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Record does not exist!" + e2);
			System.out.println(e2.getStackTrace());
		}
	}// search()

	public void LoadReg_no() {
		try {
			Gamer g = new Gamer();
			Connection con = g.setGame();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT reg_no FROM `Registration`");
			while (rs.next()) {
//reg_list.addItem(rs.getInt("Reg_no"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error in Registration No. Transfer" + e);
		}
	}

	/*
	 * public int LoadReg_noAll() { int size=0; try { Gamer g = new Gamer();
	 * Connection con = g.setGame(); Statement s = con.createStatement(); ResultSet
	 * rs = s.executeQuery("SELECT reg_no FROM `Registration`"); while(rs.next()) {
	 * size++; reg_list.addItem(rs.getInt("Reg_no")); }
	 *
	 * } catch(SQLException e) { JOptionPane.showMessageDialog(null,"Error in
	 * Registration No. Transfer"+e); } return size; }
	 */
	private void clean() {
		test_rec.setText("");
		test_reg.setText("");
		testname.setSelectedIndex(0);
	}
}