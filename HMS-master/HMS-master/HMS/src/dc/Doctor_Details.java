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

public class Doctor_Details extends JFrame {
	/**
	*
	*/
	private JPanel contentPane;
	private JTextField tdoc_id;
	private JTextField tname;
	private JTextField tdob;
	private JTextField tdoc_add;
	private JTextField tdoc_deg;
	private JTextField tdoc_dep;
	private JTextField troomno;
	String regno, arecno;
	private JTextField tdoj;
	private JTextField tgen;
	private JTextField tsalary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Doctor_Details frame = new Doctor_Details();
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
	public Doctor_Details() {
		setTitle("Doctor_Details");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 577);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel pnl_btn = new JPanel();
		pnl_btn.setBackground(new Color(204, 0, 204));
		pnl_btn.setBounds(565, 106, 181, 421);
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
		JButton btnSaves = new JButton("Save");
		btnSaves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a1 = tdoc_id.getText();
				String a2 = tname.getText();
				String a3 = tdob.getText();
				String a4 = tdoc_add.getText();
				String a5 = tdoc_deg.getText();
				String a6 = tdoc_dep.getText();
				String a7 = troomno.getText();
				String a8 = tdoj.getText();
				String a9 = tgen.getText();
				int a10 = Integer.parseInt(tsalary.getText());
				String insert = "INSERT INTO `doctor_details`(`Doctor_id`, `D_name`, `DOB`, `D_add`, `D_designation`, `D_department`, `Room_no`, `Doc_date_of_joining`, `Gender`, `salary`) VALUES('"
						+ a1 + "','" + a2 + "','" + a3 + "','" + a4 + "','" + a5 + "','" + a6 + "','" + a7 + "','" + a8
						+ "','" + a9 + "'," + a10 + ")";
				try {
					Gamer.setGame(insert);
					JOptionPane.showMessageDialog(null, "Record Saved Succesfully!");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "cannot be Saved!");
				}
			}
		});
		btnSaves.setToolTipText("Save this record");
		btnSaves.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSaves.setBounds(49, 88, 95, 33);
		pnl_btn.add(btnSaves);
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				clean();
				System.out.println("deleted...");
				arecno = JOptionPane.showInputDialog("Enter the Doctor_Id. to search the record");
				try {
					Gamer.setGame("DELETE FROM `doctor_details` WHERE Doctor_id=" + Integer.parseInt(arecno));
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
					Gamer.setGame("UPDATE `admission` SET `Ad_rec_no`=" + Integer.parseInt(tdoc_id.getText())
							+ ",`Reg_no`=" + Integer.parseInt(tdoc_id.getText()) + ",`Chart_number`="
							+ Integer.parseInt(tname.getText()) + ",`Room_no`=" + Integer.parseInt(tdob.getText())
							+ ",`Bed_no`=" + Integer.parseInt(tdoc_add.getText()) + ",`Adm_date`=" + tdoc_deg.getText()
							+ ",`D_name`=" + tdoc_dep.getText() + "ad_rec_no=" + Integer.parseInt(tdoc_id.getText()));
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
		JLabel lblNewLabel = new JLabel("Entry Form of Doctor Details");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 0, 834, 86);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewLabel.setBackground(Color.BLUE);
		JLabel lblNewLabel_1 = new JLabel("Doctor's Id");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(66, 141, 191, 29);
		contentPane.add(lblNewLabel_1);
		JLabel lblChartNo = new JLabel("Doctor Name");
		lblChartNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblChartNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChartNo.setBounds(66, 185, 191, 29);
		contentPane.add(lblChartNo);
		JLabel lblRoomNo = new JLabel("Date Of Birth");
		lblRoomNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblRoomNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRoomNo.setBounds(66, 224, 191, 29);
		contentPane.add(lblRoomNo);
		JLabel lblBedNo = new JLabel("Doctor's Address\r\n");
		lblBedNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblBedNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBedNo.setBounds(66, 263, 191, 29);
		contentPane.add(lblBedNo);
		JLabel lblDoctorName = new JLabel("Doctor's Designation");
		lblDoctorName.setHorizontalAlignment(SwingConstants.LEFT);
		lblDoctorName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDoctorName.setBounds(66, 303, 191, 29);
		contentPane.add(lblDoctorName);
		JLabel lblAdmissionDate = new JLabel("Doctor's Department");
		lblAdmissionDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdmissionDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAdmissionDate.setBounds(66, 342, 191, 29);
		contentPane.add(lblAdmissionDate);
		JLabel lblSex = new JLabel("Room No");
		lblSex.setHorizontalAlignment(SwingConstants.LEFT);
		lblSex.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSex.setBounds(66, 381, 191, 29);
		contentPane.add(lblSex);
		JLabel lblAge = new JLabel("Date of Joining");
		lblAge.setHorizontalAlignment(SwingConstants.LEFT);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAge.setBounds(66, 420, 191, 29);
		contentPane.add(lblAge);
		JLabel lblRegistrationChange = new JLabel("Gender");
		lblRegistrationChange.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistrationChange.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegistrationChange.setBounds(66, 459, 191, 29);
		contentPane.add(lblRegistrationChange);
		JLabel lblDisease = new JLabel("Salary");
		lblDisease.setHorizontalAlignment(SwingConstants.LEFT);
		lblDisease.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDisease.setBounds(66, 498, 191, 29);
		contentPane.add(lblDisease);
		tdoc_id = new JTextField();
		tdoc_id.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdoc_id.setBounds(273, 138, 259, 29);
		contentPane.add(tdoc_id);
		tdoc_id.setColumns(100);
		tname = new JTextField();
		tname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tname.setBounds(273, 184, 259, 29);
		contentPane.add(tname);
		tname.setColumns(100);
		tdob = new JTextField();
		tdob.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdob.setBounds(273, 223, 259, 29);
		contentPane.add(tdob);
		tdob.setColumns(100);
		tdoc_deg = new JTextField();
		tdoc_deg.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdoc_deg.setBounds(273, 301, 259, 29);
		contentPane.add(tdoc_deg);
		tdoc_deg.setColumns(100);
		tdoc_dep = new JTextField();
		tdoc_dep.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdoc_dep.setColumns(100);
		tdoc_dep.setBounds(273, 341, 259, 29);
		contentPane.add(tdoc_dep);
		tdoc_add = new JTextField();
		tdoc_add.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdoc_add.setBounds(273, 262, 259, 29);
		contentPane.add(tdoc_add);
		tdoc_add.setColumns(100);
		tsalary = new JTextField();
		tsalary.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tsalary.setBounds(273, 497, 259, 29);
		contentPane.add(tsalary);
		tdoc_add.setColumns(100);
		tgen = new JTextField();
		tgen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tgen.setColumns(10);
		tgen.setBounds(273, 458, 259, 29);
		contentPane.add(tgen);
		tdoj = new JTextField();
		tdoj.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdoj.setBounds(273, 419, 259, 29);
		contentPane.add(tdoj);
		tdoc_add.setColumns(100);
		troomno = new JTextField();
		troomno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		troomno.setBounds(273, 380, 259, 29);
		contentPane.add(troomno);
		tdoc_add.setColumns(100);
	}// end of constructor

	private void searchRec() {
		String did = JOptionPane.showInputDialog("Enter the Doctor ID. to search the record");
		try {
			Gamer g = new Gamer();
			Connection con = g.setGame();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM `doctor_details` where Doctor_id=" + Integer.parseInt(did));
			while (rs.next()) {
				tdoc_id.setText(rs.getInt(1) + "");
				LoadReg_no();
				tname.setText(rs.getString(2) + "");
				tdob.setText(rs.getString(3) + "");
				tdoc_add.setText(rs.getString(4) + "");
				tdoc_deg.setText(rs.getString(5) + "");
				tdoc_dep.setText(rs.getString(6));
				troomno.setText(rs.getInt(7) + "");
				tdoj.setText(rs.getString(8));
				tgen.setText(rs.getString(9));
				tsalary.setText(rs.getInt(10) + "");
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
			ResultSet rs = s.executeQuery("SELECT Doctor_id FROM `doctor_details`");
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
		tdoc_id.setText("");
		tname.setText("");
		tdob.setText("");
		tdoc_add.setText("");
		tdoc_deg.setText("");
		tdoc_dep.setText("");
		troomno.setText("");
		tdoj.setText("");
		tgen.setText("");
		tsalary.setText("");
	}
}
