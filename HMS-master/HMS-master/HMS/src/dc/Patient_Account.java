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

public class Patient_Account extends JFrame {
	private JPanel contentPane;
	private JTextField tpid1;
	private JTextField tpname;
	private JTextField txray_charge;
	private JTextField tultra_charge;;
	private JTextField tpcharge;
	private JTextField tmedcost;
	String regno, arecno;
	private JTextField tbedchrg;
	private JTextField texchrg;
	private JTextField tnoday;
	private JTextField tadmin_date;
	private JTextField tdischrg_date;
	private JTextField ttotal;
	private JTextField tdiscount;
	private JTextField tnet;
	private JTextField tpaid;
	Connection con = null;
	Statement stmnt = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Patient_Account frame = new Patient_Account();
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
	public Patient_Account() {
		setTitle("Admission");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 756);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel pnl_btn = new JPanel();
		pnl_btn.setBackground(new Color(204, 0, 204));
		pnl_btn.setBounds(663, 103, 181, 688);
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
				int p1 = Integer.parseInt(tpid1.getText());
				String p2 = tpname.getText();
				int p3 = Integer.parseInt(txray_charge.getText());
				int p4 = Integer.parseInt(tultra_charge.getText());
				int p5 = Integer.parseInt(tpcharge.getText());
				int p6 = Integer.parseInt(tmedcost.getText());
				int p7 = Integer.parseInt(tbedchrg.getText());
				int p8 = Integer.parseInt(texchrg.getText());
				int p9 = Integer.parseInt(tnoday.getText());
				String p10 = tadmin_date.getText();
				String p11 = tdischrg_date.getText();
				int p12 = Integer.parseInt(ttotal.getText());
				int p13 = Integer.parseInt(tdiscount.getText());
				int p14 = Integer.parseInt(tpaid.getText());
				int p15 = Integer.parseInt(tnet.getText());
				String insert = "INSERT INTO `patient_account`(`P_id`,`P_name`, `x_charge`, `soundch`, `p_charge`, `Medicine_cost`, `Bed_charge`,`Extra_charge`, `No_of_day`, `Date_of_admit`, `Date_of_Discharge`,`Tot_amt`, `discount`, `Paid_amt`, `Net_amt`) VALUES("
						+ p1 + ",'" + p2 + "'," + p3 + "," + p4 + "," + p5 + "," + p6 + "," + p7 + "," + p8 + "," + p9
						+ ",'" + p10 + "','" + p11 + "'," + p12 + "," + p13 + "," + p14 + "," + p15 + ")";
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
				arecno = JOptionPane.showInputDialog("Enter the P_id. to search the record");
				try {
					Gamer.setGame("DELETE FROM `patient_account` WHERE P_id=" + Integer.parseInt(arecno));
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
//arecno=JOptionPane.showInputDialog("Enter the P_id. to search the record");
				try {
					Gamer.setGame("UPDATE `admission` SET `Ad_rec_no`=" + Integer.parseInt(tpid1.getText())
							+ ",`Reg_no`=" + Integer.parseInt(tpid1.getText()) + ",`Chart_number`="
							+ Integer.parseInt(tpname.getText()) + ",`Room_no`="
							+ Integer.parseInt(txray_charge.getText()) + ",`Bed_no`="
							+ Integer.parseInt(tultra_charge.getText()) + ",`Adm_date`=" + tpcharge.getText()
							+ ",`D_name`=" + tmedcost.getText() + " ad_rec_no=" + Integer.parseInt(tpid1.getText()));
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
		JLabel lblNewLabel = new JLabel("Entry Form of Patient Account");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 0, 834, 86);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewLabel.setBackground(Color.BLUE);
		JLabel lblNewLabel_1 = new JLabel("Patient's Id");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(80, 121, 191, 29);
		contentPane.add(lblNewLabel_1);
		JLabel lblChartNo = new JLabel("Paintent Name");
		lblChartNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblChartNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChartNo.setBounds(80, 165, 191, 29);
		contentPane.add(lblChartNo);
		JLabel lblRoomNo = new JLabel("X Ray Charge");
		lblRoomNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblRoomNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRoomNo.setBounds(80, 204, 191, 29);
		contentPane.add(lblRoomNo);
		JLabel lblBedNo = new JLabel("Ultra Sound Charge");
		lblBedNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblBedNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBedNo.setBounds(80, 243, 191, 29);
		contentPane.add(lblBedNo);
		JLabel lblAdmissionDate = new JLabel("Pythology Charge");
		lblAdmissionDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdmissionDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAdmissionDate.setBounds(80, 282, 191, 29);
		contentPane.add(lblAdmissionDate);
		JLabel lblDoctorName = new JLabel("Medcine Cost");
		lblDoctorName.setHorizontalAlignment(SwingConstants.LEFT);
		lblDoctorName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDoctorName.setBounds(80, 321, 191, 29);
		contentPane.add(lblDoctorName);
		tpid1 = new JTextField();
		tpid1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tpid1.setBounds(287, 118, 259, 29);
		contentPane.add(tpid1);
		tpid1.setColumns(10);
		tpname = new JTextField();
		tpname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tpname.setColumns(10);
		tpname.setBounds(287, 164, 259, 29);
		contentPane.add(tpname);
		txray_charge = new JTextField();
		txray_charge.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txray_charge.setColumns(10);
		txray_charge.setBounds(287, 203, 259, 29);
		contentPane.add(txray_charge);
		tpcharge = new JTextField();
		tpcharge.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tpcharge.setColumns(10);
		tpcharge.setBounds(287, 281, 259, 29);
		contentPane.add(tpcharge);
		tmedcost = new JTextField();
		tmedcost.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tmedcost.setColumns(10);
		tmedcost.setBounds(287, 321, 259, 29);
		contentPane.add(tmedcost);
		tultra_charge = new JTextField();
		tultra_charge.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tultra_charge.setColumns(10);
		tultra_charge.setBounds(287, 242, 259, 29);
		contentPane.add(tultra_charge);
		JLabel lblDisease = new JLabel("Date of Admit");
		lblDisease.setHorizontalAlignment(SwingConstants.LEFT);
		lblDisease.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDisease.setBounds(80, 478, 191, 29);
		contentPane.add(lblDisease);
		tadmin_date = new JTextField();
		tadmin_date.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tadmin_date.setColumns(10);
		tadmin_date.setBounds(287, 477, 259, 29);
		contentPane.add(tadmin_date);
		tnoday = new JTextField();
		tnoday.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tnoday.setColumns(10);
		tnoday.setBounds(287, 438, 259, 29);
		contentPane.add(tnoday);
		JLabel lblRegistrationChange = new JLabel("No of Day");
		lblRegistrationChange.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistrationChange.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegistrationChange.setBounds(80, 439, 191, 29);
		contentPane.add(lblRegistrationChange);
		texchrg = new JTextField();
		texchrg.setFont(new Font("Tahoma", Font.PLAIN, 18));
		texchrg.setColumns(10);
		texchrg.setBounds(287, 399, 259, 29);
		contentPane.add(texchrg);
		JLabel lblAge = new JLabel("Extra Charge");
		lblAge.setHorizontalAlignment(SwingConstants.LEFT);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAge.setBounds(80, 400, 191, 29);
		contentPane.add(lblAge);
		JLabel lblSex = new JLabel("Bed Charge");
		lblSex.setHorizontalAlignment(SwingConstants.LEFT);
		lblSex.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSex.setBounds(80, 361, 191, 29);
		contentPane.add(lblSex);
		tbedchrg = new JTextField();
		tbedchrg.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tbedchrg.setColumns(10);
		tbedchrg.setBounds(287, 360, 259, 29);
		contentPane.add(tbedchrg);
		tnet = new JTextField();
		tnet.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tnet.setColumns(10);
		tnet.setBounds(287, 673, 259, 29);
		contentPane.add(tnet);
		JLabel lblNetAmount = new JLabel("Net Amount");
		lblNetAmount.setHorizontalAlignment(SwingConstants.LEFT);
		lblNetAmount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNetAmount.setBounds(80, 674, 191, 29);
		contentPane.add(lblNetAmount);
		JLabel lblPaidAmount = new JLabel("Paid Amount");
		lblPaidAmount.setHorizontalAlignment(SwingConstants.LEFT);
		lblPaidAmount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPaidAmount.setBounds(80, 635, 191, 29);
		contentPane.add(lblPaidAmount);
		tpaid = new JTextField();
		tpaid.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tpaid.setColumns(10);
		tpaid.setBounds(287, 634, 259, 29);
		contentPane.add(tpaid);
		tdiscount = new JTextField();
		tdiscount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdiscount.setColumns(10);
		tdiscount.setBounds(287, 595, 259, 29);
		contentPane.add(tdiscount);
		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiscount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDiscount.setBounds(80, 596, 191, 29);
		contentPane.add(lblDiscount);
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalAmount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalAmount.setBounds(80, 557, 191, 29);
		contentPane.add(lblTotalAmount);
		ttotal = new JTextField();
		ttotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ttotal.setColumns(10);
		ttotal.setBounds(287, 556, 259, 29);
		contentPane.add(ttotal);
		JLabel lblDateOfDischarge = new JLabel("Date of Discharge");
		lblDateOfDischarge.setHorizontalAlignment(SwingConstants.LEFT);
		lblDateOfDischarge.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDateOfDischarge.setBounds(80, 517, 191, 29);
		contentPane.add(lblDateOfDischarge);
		tdischrg_date = new JTextField();
		tdischrg_date.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdischrg_date.setColumns(10);
		tdischrg_date.setBounds(287, 517, 259, 29);
		contentPane.add(tdischrg_date);
	}// end of constructor

	private void searchRec() {
		String recno = JOptionPane.showInputDialog("Enter the P_id to search the record");
		try {
			Gamer g = new Gamer();
			Connection con = g.setGame();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM `patient_account` where P_id=" + Integer.parseInt(recno));
			while (rs.next()) {
				tpid1.setText(rs.getInt(1) + "");
				LoadReg_no();
				tpname.setText(rs.getString(2) + "");
				txray_charge.setText(rs.getInt(3) + "");
				tultra_charge.setText(rs.getInt(4) + "");
				tpcharge.setText(rs.getInt(5) + "");
				tmedcost.setText(rs.getInt(6) + "");
				tbedchrg.setText(rs.getInt(7) + "");
				texchrg.setText(rs.getInt(8) + "");
				tnoday.setText(rs.getInt(9) + "");
				tadmin_date.setText(rs.getString(10) + "");
				tdischrg_date.setText(rs.getString(11) + "");
				ttotal.setText(rs.getInt(12) + "");
				tdiscount.setText(rs.getInt(13) + "");
				tnet.setText(rs.getInt(14) + "");
				tpaid.setText(rs.getInt(15) + "");
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
			ResultSet rs = s.executeQuery("SELECT P_id FROM `patient_account`");
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
		tpid1.setText("");
		tpname.setText("");
		txray_charge.setText("");
		tultra_charge.setText("");
		tpcharge.setText("");
		tmedcost.setText("");
		tbedchrg.setText("");
		texchrg.setText("");
		tnoday.setText("");
		tadmin_date.setText("");
		tdischrg_date.setText("");
		ttotal.setText("");
		tdiscount.setText("");
		tnet.setText("");
		tpaid.setText("");
	}
}