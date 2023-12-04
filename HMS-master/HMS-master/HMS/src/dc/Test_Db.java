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

public class Test_Db extends JFrame {
	private JPanel contentPane;
	private JTextField trno;
	private JTextField txray_testid;
	private JTextField test_name;
	private JTextField tavg;
	private JTextField tamnt;
	String regno, arecno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test_Db frame = new Test_Db();
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
	public Test_Db() {
		setTitle("Test_Db");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 456);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel pnl_btn = new JPanel();
		pnl_btn.setBackground(new Color(204, 0, 204));
		pnl_btn.setBounds(503, 106, 181, 313);
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
				int a = Integer.parseInt(trno.getText());
				int a1 = Integer.parseInt(txray_testid.getText());
				String a2 = test_name.getText();
				int a3 = Integer.parseInt(tavg.getText());
				int a4 = Integer.parseInt(tamnt.getText());
				String insert = "INSERT INTO `test_db`(`Reg`,`Test_id`, `Test_name`, `Test_amt`, `Average`) VALUES ("
						+ a + "," + a1 + ",'" + a2 + "'," + a3 + "," + a4 + ")";
				try {
					Gamer.setGame(insert);
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
				arecno = JOptionPane.showInputDialog("Enter the Test_id. to search the record");
				try {
					Gamer.setGame("DELETE FROM `test_db` WHERE Test_id=" + Integer.parseInt(arecno));
					JOptionPane.showMessageDialog(null, "Record Deleted Succesfully!");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Deletion failed!");
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
//update button
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setToolTipText("Update this record");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clean();
//searchRec();
//System.out.println("updated...");
//arecno=JOptionPane.showInputDialog("Enter the Admission Record No. to search the record");
				try {
					Gamer.setGame("");
					JOptionPane.showMessageDialog(null, "Record Updated Succesfully!");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Updation failed!");
					System.out.println(e.getCause());
				}
				clean();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdate.setBounds(49, 217, 95, 33);
		pnl_btn.add(btnUpdate);
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(DISPOSE_ON_CLOSE);
				setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
		});
		btnExit.setToolTipText("Close this window");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(49, 260, 95, 33);
		pnl_btn.add(btnExit);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 0, 153));
		panel.setBounds(0, 0, 844, 96);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("Test ID Form");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 0, 670, 86);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewLabel.setBackground(Color.BLUE);
		JLabel lblNewLabel_1 = new JLabel("Registration No");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(65, 128, 132, 29);
		contentPane.add(lblNewLabel_1);
		JLabel lblChartNo = new JLabel("X Ray Test ID");
		lblChartNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblChartNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChartNo.setBounds(65, 172, 132, 29);
		contentPane.add(lblChartNo);
		JLabel lblRoomNo = new JLabel("Test Name");
		lblRoomNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblRoomNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRoomNo.setBounds(65, 211, 132, 29);
		contentPane.add(lblRoomNo);
		JLabel lblBedNo = new JLabel("Test Amount");
		lblBedNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblBedNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBedNo.setBounds(65, 250, 132, 29);
		contentPane.add(lblBedNo);
		JLabel lblAdmissionDate = new JLabel("Average");
		lblAdmissionDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdmissionDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAdmissionDate.setBounds(65, 289, 132, 29);
		contentPane.add(lblAdmissionDate);
		trno = new JTextField();
		trno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		trno.setBounds(207, 126, 259, 29);
		contentPane.add(trno);
		trno.setColumns(10);
		txray_testid = new JTextField();
		txray_testid.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txray_testid.setColumns(10);
		txray_testid.setBounds(207, 172, 259, 29);
		contentPane.add(txray_testid);
		test_name = new JTextField();
		test_name.setFont(new Font("Tahoma", Font.PLAIN, 18));
		test_name.setColumns(10);
		test_name.setBounds(207, 211, 259, 29);
		contentPane.add(test_name);
		tavg = new JTextField();
		tavg.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tavg.setColumns(10);
		tavg.setBounds(207, 289, 259, 29);
		contentPane.add(tavg);
		tamnt = new JTextField();
		tamnt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tamnt.setColumns(10);
		tamnt.setBounds(207, 250, 259, 29);
		contentPane.add(tamnt);
	}// end of constructor

	private void searchRec() {
		String tid = JOptionPane.showInputDialog("Enter the Test ID to search the record");
		try {
			Gamer g = new Gamer();
			Connection con = g.setGame();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM `test_db` where Test_id=" + Integer.parseInt(tid));
			while (rs.next()) {
				trno.setText(rs.getInt(1) + "");
				txray_testid.setText(rs.getInt(2) + "");
				test_name.setText(rs.getString(3) + "");
				tamnt.setText(rs.getInt(4) + "");
				tavg.setText(rs.getInt(5) + "");
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
	 * } catch(SQLException e) {
	 * JOptionPane.showMessageDialog(null,"Error in Registration No. Transfer"+e); }
	 * return size; }
	 */
	private void clean() {
		trno.setText("");
		txray_testid.setText("");
		test_name.setText("");
		tamnt.setText("");
		tavg.setText("");
	}
}