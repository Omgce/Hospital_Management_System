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

public class Admission extends JFrame {
	private JPanel contentPane;
	private JTextField tad_rec;
	private JTextField tchar_no;
	private JTextField troom_no;
	private JTextField tadmsn_date;
	private JTextField tdname;
	private JTextField tbed_no;
	JComboBox reg_list;
	String regno, arecno;
	private JTextField treg_no;
	Connection conn = null;
	Statement stmt = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admission frame = new Admission();
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
	public Admission() {
		setTitle("Admission");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 493);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel pnl_btn = new JPanel();
		pnl_btn.setBackground(SystemColor.activeCaption);
		pnl_btn.setBounds(663, 103, 181, 344);
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
				int recno = Integer.parseInt(tad_rec.getText());
				int reg = Integer.parseInt(treg_no.getText());
				int chart = Integer.parseInt(tchar_no.getText());
				int room = Integer.parseInt(troom_no.getText());
				String adate = tadmsn_date.getText();
				String dname = tdname.getText();
				int bno = Integer.parseInt(tbed_no.getText());
				String insert = "INSERT INTO `admission`(`Ad_rec_no`, `Reg_no`, `Chart_number`, `Room_no`, `Bed_no`, `Adm_date`, `D_name`) VALUES ("
						+ recno + "," + reg + "," + chart + "," + room + "," + bno + ",'" + adate + "','" + dname
						+ "')";
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
				arecno = JOptionPane.showInputDialog("Enter the Admission Record No. to search the record");
				try {
					String delete = "DELETE FROM `admission` WHERE ad_rec_no=" + Integer.parseInt(arecno);
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctor", "root", "ROOT");
					stmt = conn.createStatement();
					stmt.execute(delete);
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
			public void actionPerformed(ActionEvent eb) {
//clean();
//searchRec();
//System.out.println("updated...");
				try {
					int rec = Integer.parseInt(tad_rec.getText());
					int regno = Integer.parseInt(treg_no.getText());
					int chart = Integer.parseInt(tchar_no.getText());
					int roomno = Integer.parseInt(troom_no.getText());
					int bno = Integer.parseInt(tbed_no.getText());
					String date = tadmsn_date.getText();
					String name = tdname.getText();
					String update = "UPDATE `admission` SET `Ad_rec_no`=" + rec + ",`Reg_no`=" + regno
							+ ",`Chart_number`=" + chart + ",`Room_no`=" + roomno + ",`Bed_no`=" + bno + ",`Adm_date`='"
							+ date + "',`D_name`='" + name + "' WHERE `Ad_rec_no`=" + rec;
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctor", "root", "");
					stmt = conn.createStatement();
					stmt.execute(update);
					JOptionPane.showMessageDialog(null, "Record Updated Succesfully!");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Updation failed!" + e);
					e.printStackTrace();
					System.out.println(e.getMessage());
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
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 844, 96);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("Entry Form of Admission");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 0, 834, 86);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewLabel.setBackground(Color.BLUE);
		JLabel lblNewLabel_1 = new JLabel("Admission Record No");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(68, 144, 191, 29);
		contentPane.add(lblNewLabel_1);
		JLabel lblRegistrationNo = new JLabel("Registration No");
		lblRegistrationNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistrationNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegistrationNo.setBounds(68, 183, 191, 29);
		contentPane.add(lblRegistrationNo);
		JLabel lblChartNo = new JLabel("Chart No");
		lblChartNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblChartNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChartNo.setBounds(68, 227, 191, 29);
		contentPane.add(lblChartNo);
		JLabel lblRoomNo = new JLabel("Room No");
		lblRoomNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblRoomNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRoomNo.setBounds(68, 266, 191, 29);
		contentPane.add(lblRoomNo);
		JLabel lblBedNo = new JLabel("Bed No");
		lblBedNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblBedNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBedNo.setBounds(68, 305, 191, 29);
		contentPane.add(lblBedNo);
		JLabel lblAdmissionDate = new JLabel("Admission Date");
		lblAdmissionDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdmissionDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAdmissionDate.setBounds(68, 344, 191, 29);
		contentPane.add(lblAdmissionDate);
		JLabel lblDoctorName = new JLabel("Doctor Name");
		lblDoctorName.setHorizontalAlignment(SwingConstants.LEFT);
		lblDoctorName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDoctorName.setBounds(68, 383, 191, 29);
		contentPane.add(lblDoctorName);
		tad_rec = new JTextField();
		tad_rec.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tad_rec.setBounds(275, 141, 259, 29);
		contentPane.add(tad_rec);
		tad_rec.setColumns(10);
		treg_no = new JTextField();
		treg_no.setFont(new Font("Tahoma", Font.BOLD, 14));
		treg_no.setBackground(Color.WHITE);
		treg_no.setBounds(274, 185, 260, 29);
		contentPane.add(treg_no);
		tchar_no = new JTextField();
		tchar_no.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tchar_no.setColumns(10);
		tchar_no.setBounds(275, 226, 259, 29);
		contentPane.add(tchar_no);
		troom_no = new JTextField();
		troom_no.setFont(new Font("Tahoma", Font.PLAIN, 18));
		troom_no.setColumns(10);
		troom_no.setBounds(275, 265, 259, 29);
		contentPane.add(troom_no);
		tadmsn_date = new JTextField();
		tadmsn_date.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tadmsn_date.setColumns(10);
		tadmsn_date.setBounds(275, 343, 259, 29);
		contentPane.add(tadmsn_date);
		tdname = new JTextField();
		tdname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdname.setColumns(10);
		tdname.setBounds(275, 383, 259, 29);
		contentPane.add(tdname);
		tbed_no = new JTextField();
		tbed_no.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tbed_no.setColumns(10);
		tbed_no.setBounds(275, 304, 259, 29);
		contentPane.add(tbed_no);
	}// end of constructor

	private void searchRec() {
		String recno = JOptionPane.showInputDialog("Enter the Admission Record No. to search the record");
		try {
			Gamer g = new Gamer();
			Connection con = g.setGame();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM `admission` where Ad_rec_no=" + Integer.parseInt(recno));
			while (rs.next()) {
				tad_rec.setText(rs.getInt(1) + "");
				LoadReg_no();
				treg_no.setText(rs.getInt(2) + "");
				tchar_no.setText(rs.getInt(3) + "");
				troom_no.setText(rs.getInt(4) + "");
				tbed_no.setText(rs.getInt(5) + "");
				tadmsn_date.setText(rs.getString(6) + "");
				tdname.setText(rs.getString(7));
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
			ResultSet rs = s.executeQuery("SELECT Ad_rec_no FROM `admission`");
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
		tad_rec.setText("");
		treg_no.setText("");
		tchar_no.setText("");
		troom_no.setText("");
		tbed_no.setText("");
		tadmsn_date.setText("");
		tdname.setText("");
	}
}