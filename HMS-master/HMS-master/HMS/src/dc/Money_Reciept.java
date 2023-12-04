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

public class Money_Reciept extends JFrame {
	private JPanel contentPane;
	private JTextField tmon_recno;
	private JTextField tregno;
	private JTextField tamsn_date;
	private JTextField tamnt;
	private JTextField tdiscount;
	private JTextField tdischarge;
	String regno, arecno;
	private JTextField tpaidamnt;
	private JTextField tnetamnt;
	Connection conn = null;
	Statement stmt = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Money_Reciept frame = new Money_Reciept();
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
	public Money_Reciept() {
		setTitle("Admission");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 560);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel pnl_btn = new JPanel();
		pnl_btn.setBackground(new Color(204, 0, 204));
		pnl_btn.setBounds(663, 103, 181, 408);
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
				int recno = Integer.parseInt(tmon_recno.getText());
				int regno = Integer.parseInt(tregno.getText());
				String doad = tamsn_date.getText();
				String dis = tdischarge.getText();
				int total = Integer.parseInt(tamnt.getText());
				int discount = Integer.parseInt(tdiscount.getText());
				int net = Integer.parseInt(tnetamnt.getText());
				int paid = Integer.parseInt(tpaidamnt.getText());
				String insert = "INSERT INTO `money_receipt`(`Rec_no`, `Reg_no`, `Date_of_adm`, `Date_of_disc`, `Total_amt`, `Discount`, `Net_amt`, `Paid_amt`) "
						+ "VALUES (" + recno + "," + regno + ",'" + doad + "','" + dis + "'," + total + "," + discount
						+ "," + net + "," + paid + ")";
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
				arecno = JOptionPane.showInputDialog("Enter the Money Recpt No to search the record");
				try {
					Gamer.setGame("DELETE FROM `money_receipt` WHERE Rec_no=" + Integer.parseInt(arecno));
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
					Gamer.setGame("UPDATE `admission` SET `Ad_rec_no`=" + Integer.parseInt(tmon_recno.getText())
							+ ",`Reg_no`=" + Integer.parseInt(tmon_recno.getText()) + ",`Chart_number`="
							+ Integer.parseInt(tregno.getText()) + ",`Room_no`="
							+ Integer.parseInt(tamsn_date.getText()) + ",`Bed_no`="
							+ Integer.parseInt(tdischarge.getText()) + ",`Adm_date`=" + tamnt.getText() + ",`D_name`="
							+ tdiscount.getText() + " ad_rec_no=" + Integer.parseInt(tmon_recno.getText()));
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
		JLabel lblNewLabel = new JLabel("Entry Form of Money Reciept");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 0, 834, 86);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewLabel.setBackground(Color.BLUE);
		JLabel lblNewLabel_1 = new JLabel("Money Record No");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(68, 183, 191, 29);
		contentPane.add(lblNewLabel_1);
		JLabel lblChartNo = new JLabel("Registration No");
		lblChartNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblChartNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChartNo.setBounds(68, 227, 191, 29);
		contentPane.add(lblChartNo);
		JLabel lblRoomNo = new JLabel("Date of Admission");
		lblRoomNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblRoomNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRoomNo.setBounds(68, 266, 191, 29);
		contentPane.add(lblRoomNo);
		JLabel lblBedNo = new JLabel("Date of Disc");
		lblBedNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblBedNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBedNo.setBounds(68, 305, 191, 29);
		contentPane.add(lblBedNo);
		JLabel lblAdmissionDate = new JLabel("Total Amount");
		lblAdmissionDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdmissionDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAdmissionDate.setBounds(68, 344, 191, 29);
		contentPane.add(lblAdmissionDate);
		JLabel lblDoctorName = new JLabel("Discount");
		lblDoctorName.setHorizontalAlignment(SwingConstants.LEFT);
		lblDoctorName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDoctorName.setBounds(68, 383, 191, 29);
		contentPane.add(lblDoctorName);
		tmon_recno = new JTextField();
		tmon_recno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tmon_recno.setBounds(275, 180, 259, 29);
		contentPane.add(tmon_recno);
		tmon_recno.setColumns(10);
		tregno = new JTextField();
		tregno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tregno.setColumns(10);
		tregno.setBounds(275, 226, 259, 29);
		contentPane.add(tregno);
		tamsn_date = new JTextField();
		tamsn_date.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tamsn_date.setColumns(10);
		tamsn_date.setBounds(275, 265, 259, 29);
		contentPane.add(tamsn_date);
		tamnt = new JTextField();
		tamnt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tamnt.setColumns(10);
		tamnt.setBounds(275, 343, 259, 29);
		contentPane.add(tamnt);
		tdiscount = new JTextField();
		tdiscount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdiscount.setColumns(10);
		tdiscount.setBounds(275, 383, 259, 29);
		contentPane.add(tdiscount);
		tdischarge = new JTextField();
		tdischarge.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdischarge.setColumns(10);
		tdischarge.setBounds(275, 304, 259, 29);
		contentPane.add(tdischarge);
		tpaidamnt = new JTextField();
		tpaidamnt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tpaidamnt.setColumns(10);
		tpaidamnt.setBounds(275, 461, 259, 29);
		contentPane.add(tpaidamnt);
		JLabel lblAge = new JLabel("Paid Amount");
		lblAge.setHorizontalAlignment(SwingConstants.LEFT);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAge.setBounds(68, 462, 191, 29);
		contentPane.add(lblAge);
		JLabel lblSex = new JLabel("Net Amount");
		lblSex.setHorizontalAlignment(SwingConstants.LEFT);
		lblSex.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSex.setBounds(68, 423, 191, 29);
		contentPane.add(lblSex);
		tnetamnt = new JTextField();
		tnetamnt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tnetamnt.setColumns(10);
		tnetamnt.setBounds(275, 422, 259, 29);
		contentPane.add(tnetamnt);
	}// end of constructor

	private void searchRec() {
		String recno = JOptionPane.showInputDialog("Enter the Money Receipt No to search the record");
		try {
			Gamer g = new Gamer();
			Connection con = g.setGame();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM `money_receipt` WHERE Rec_no=" + Integer.parseInt(recno));
			while (rs.next()) {
				tmon_recno.setText(rs.getInt(1) + "");
				tregno.setText(rs.getInt(2) + "");
				tamsn_date.setText(rs.getString(3) + "");
				tdischarge.setText(rs.getString(4) + "");
				tamnt.setText(rs.getInt(5) + "");
				tdiscount.setText(rs.getInt(6) + "");
				tnetamnt.setText(rs.getInt(7) + "");
				tpaidamnt.setText(rs.getInt(8) + "");
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
		tmon_recno.setText("");
		tregno.setText("");
		tamsn_date.setText("");
		tdischarge.setText("");
		tamnt.setText("");
		tdiscount.setText("");
		tnetamnt.setText("");
		tpaidamnt.setText("");
	}
}