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

public class Registration extends JFrame {
	private JPanel contentPane;
	private JTextField treg_no;
	private JTextField tpname;
	private JTextField tpadd;
	private JTextField tdeprt;
	private JTextField tdname;
	private JTextField tdor;
	String regno, arecno;
	private JTextField tdisease;
	private JTextField treg_charge;
	private JTextField tage;
	private JTextField tsex;
	Connection conn = null;
	Statement stmt = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
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
	public Registration() {
		setTitle("Registration");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 655);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel pnl_btn = new JPanel();
		pnl_btn.setBackground(new Color(204, 0, 204));
		pnl_btn.setBounds(663, 103, 181, 504);
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
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				clean();
				System.out.println("deleted...");
				arecno = JOptionPane.showInputDialog("Enter the Registration No. to search the record");
				int x = Integer.parseInt(arecno);
				try {
					String delete = "DELETE FROM `registration` WHERE Reg_no=" + x;
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
					Gamer.setGame("UPDATE `admission` SET `Ad_rec_no`=" + Integer.parseInt(treg_no.getText())
							+ ",`Reg_no`=" + Integer.parseInt(treg_no.getText()) + ",`Chart_number`="
							+ Integer.parseInt(tpname.getText()) + ",`Room_no`=" + Integer.parseInt(tpadd.getText())
							+ ",`Bed_no`=" + Integer.parseInt(tdor.getText()) + ",`Adm_date`=" + tdeprt.getText()
							+ ",`D_name`=" + tdname.getText() + " ad_rec_no=" + Integer.parseInt(treg_no.getText()));
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
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int regno = Integer.parseInt(treg_no.getText());
				String pname = tpname.getText();
				String add = tpadd.getText();
				String regdate = tdor.getText();
				String dep = tdeprt.getText();
				String dname = tdname.getText();
				String sex = tsex.getText();
				int reg_chrg = Integer.parseInt(treg_charge.getText());
				String disease = tdisease.getText();
				String age = tage.getText();
				String insert = "INSERT INTO `registration`(`Reg_no`, `P_name`, `P_address`, `Date_of_reg`, `Department`, `D_name`,`Sex`, `Age`, `Reg_charge`, `Disease`) VALUES "
						+ "(" + regno + ",'" + pname + "','" + add + "','" + regdate + "','" + dep + "','" + dname
						+ "','" + sex + "','" + age + "'," + reg_chrg + ",'" + disease + "')";
				try {
					Gamer.setGame(insert);
					JOptionPane.showMessageDialog(null, "Record Saved Succesfully!");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "cannot be Saved!" + e);
				}
			}
		});
		btnSave.setToolTipText("Save this record");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSave.setBounds(49, 88, 95, 33);
		pnl_btn.add(btnSave);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 0, 153));
		panel.setBounds(0, 0, 844, 96);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("Entry Form of Registration");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 0, 834, 86);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewLabel.setBackground(Color.BLUE);
		JLabel lblNewLabel_1 = new JLabel("Registration No");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(68, 183, 191, 29);
		contentPane.add(lblNewLabel_1);
		JLabel lblChartNo = new JLabel("Paintent Name");
		lblChartNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblChartNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChartNo.setBounds(68, 227, 191, 29);
		contentPane.add(lblChartNo);
		JLabel lblRoomNo = new JLabel("Paintent Address");
		lblRoomNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblRoomNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRoomNo.setBounds(68, 266, 191, 29);
		contentPane.add(lblRoomNo);
		JLabel lblBedNo = new JLabel("Date Of Registration");
		lblBedNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblBedNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBedNo.setBounds(68, 305, 191, 29);
		contentPane.add(lblBedNo);
		JLabel lblAdmissionDate = new JLabel("Department");
		lblAdmissionDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdmissionDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAdmissionDate.setBounds(68, 344, 191, 29);
		contentPane.add(lblAdmissionDate);
		JLabel lblDoctorName = new JLabel("Doctor Name");
		lblDoctorName.setHorizontalAlignment(SwingConstants.LEFT);
		lblDoctorName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDoctorName.setBounds(68, 383, 191, 29);
		contentPane.add(lblDoctorName);
		treg_no = new JTextField();
		treg_no.setFont(new Font("Tahoma", Font.PLAIN, 18));
		treg_no.setBounds(275, 180, 259, 29);
		contentPane.add(treg_no);
		treg_no.setColumns(10);
		tpname = new JTextField();
		tpname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tpname.setColumns(10);
		tpname.setBounds(275, 226, 259, 29);
		contentPane.add(tpname);
		tpadd = new JTextField();
		tpadd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tpadd.setColumns(10);
		tpadd.setBounds(275, 265, 259, 29);
		contentPane.add(tpadd);
		tdeprt = new JTextField();
		tdeprt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdeprt.setColumns(10);
		tdeprt.setBounds(275, 343, 259, 29);
		contentPane.add(tdeprt);
		tdname = new JTextField();
		tdname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdname.setColumns(10);
		tdname.setBounds(275, 383, 259, 29);
		contentPane.add(tdname);
		tdor = new JTextField();
		tdor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdor.setColumns(10);
		tdor.setBounds(275, 304, 259, 29);
		contentPane.add(tdor);
		JLabel lblDisease = new JLabel("Disease");
		lblDisease.setHorizontalAlignment(SwingConstants.LEFT);
		lblDisease.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDisease.setBounds(68, 540, 191, 29);
		contentPane.add(lblDisease);
		tdisease = new JTextField();
		tdisease.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdisease.setColumns(10);
		tdisease.setBounds(275, 539, 259, 29);
		contentPane.add(tdisease);
		treg_charge = new JTextField();
		treg_charge.setFont(new Font("Tahoma", Font.PLAIN, 18));
		treg_charge.setColumns(10);
		treg_charge.setBounds(275, 500, 259, 29);
		contentPane.add(treg_charge);
		JLabel lblRegistrationChange = new JLabel("Registration Charge");
		lblRegistrationChange.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistrationChange.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegistrationChange.setBounds(68, 501, 191, 29);
		contentPane.add(lblRegistrationChange);
		tage = new JTextField();
		tage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tage.setColumns(10);
		tage.setBounds(275, 461, 259, 29);
		contentPane.add(tage);
		JLabel lblAge = new JLabel("Age");
		lblAge.setHorizontalAlignment(SwingConstants.LEFT);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAge.setBounds(68, 462, 191, 29);
		contentPane.add(lblAge);
		JLabel lblSex = new JLabel("Sex");
		lblSex.setHorizontalAlignment(SwingConstants.LEFT);
		lblSex.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSex.setBounds(68, 423, 191, 29);
		contentPane.add(lblSex);
		tsex = new JTextField();
		tsex.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tsex.setColumns(10);
		tsex.setBounds(275, 422, 259, 29);
		contentPane.add(tsex);
	}// end of constructor

	private void searchRec() {
		String reg = JOptionPane.showInputDialog("Enter the Registration no to search the record");
		try {
			String search = "SELECT * FROM `registration` where Reg_no=" + Integer.parseInt(reg);
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctor", "root", "ROOT");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(search);
			while (rs.next()) {
				treg_no.setText(rs.getInt(1) + "");
				tpname.setText(rs.getString(2) + "");
				tpadd.setText(rs.getString(3) + "");
				tdor.setText(rs.getString(4) + "");
				tdeprt.setText(rs.getString(5) + "");
				tdname.setText(rs.getString(6));
				tsex.setText(rs.getString(7));
				tage.setText(rs.getInt(8) + "");
				treg_charge.setText(rs.getInt(9) + "");
				tdisease.setText(rs.getString(10));
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
		treg_no.setText("");
		tpname.setText("");
		tpadd.setText("");
		tdor.setText("");
		tdeprt.setText("");
		tdname.setText("");
		tdor.setText("");
		tdisease.setText("");
		treg_charge.setText("");
		tage.setText("");
		tsex.setText("");
	}
}