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

public class Test_Reciept extends JFrame {
	private JPanel contentPane;
	private JTextField test_rec;
	private JTextField trecdate;
	private JTextField tdelvrec;
	private JTextField tpaid;
	private JTextField tdue;
	private JTextField tamnt;
	JComboBox reg_list;
	String regno, arecno;
	String arr[] = new String[] { "Select Option", "Med1241", "Icu124", "Check12451", "Oth524" };
	Connection conn = null;
	Statement stmt = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test_Reciept frame = new Test_Reciept();
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
	public Test_Reciept() {
		setTitle("Test_Reciept");
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
				int recn = Integer.parseInt(test_rec.getText());
				String n1 = (String) reg_list.getSelectedItem();
				String rdate = trecdate.getText();
				String delv = tdelvrec.getText();
				int total = Integer.parseInt(tamnt.getText());
				int paid = Integer.parseInt(tpaid.getText());
				int due = Integer.parseInt(tdue.getText());
				String insert = "INSERT INTO `test_reciept` (`Rec_no`, `Reg_no`, `Date_of_rec`, `Delivery_date`, `Total_amt`, `Paid_amt`, `Due_amt`) VALUES ("
						+ recn + ",'" + n1 + "','" + rdate + "','" + delv + "'," + total + "," + paid + "," + due + ")";
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
					Gamer.setGame("DELETE FROM `test_reciept` WHERE Rec_no=" + Integer.parseInt(arecno));
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
				int recn = Integer.parseInt(test_rec.getText() + "");
				int regno = recn;
				String rdate = trecdate.getText();
				String delv = tdelvrec.getText();
				int total = Integer.parseInt(tamnt.getText());
				int paid = Integer.parseInt(tpaid.getText());
				int due = Integer.parseInt(tdue.getText());
				try {
					Gamer.setGame("UPDATE `test_reciept` SET `Rec_no`=" + recn + ",`Reg_no`=" + regno
							+ ",`Date_of_rec`='" + rdate + "',`Delivery_date`='" + delv + "',`Total_amt`=" + total
							+ ",`Paid_amt`=" + paid + ",`Due_amt`=" + due + " WHERE Rec_no=" + recn);
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
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 844, 96);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("Entry Form of Test Reciept");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 0, 834, 86);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewLabel.setBackground(Color.BLUE);
		JLabel lblNewLabel_1 = new JLabel("Test Record No");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(68, 144, 191, 29);
		contentPane.add(lblNewLabel_1);
		JLabel lblRegistrationNo = new JLabel("Registration No");
		lblRegistrationNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistrationNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegistrationNo.setBounds(68, 183, 191, 29);
		contentPane.add(lblRegistrationNo);
		JLabel lblChartNo = new JLabel("Date of Record");
		lblChartNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblChartNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChartNo.setBounds(68, 227, 191, 29);
		contentPane.add(lblChartNo);
		JLabel lblRoomNo = new JLabel("Delivery Record");
		lblRoomNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblRoomNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRoomNo.setBounds(68, 266, 191, 29);
		contentPane.add(lblRoomNo);
		JLabel lblBedNo = new JLabel("Total Amount");
		lblBedNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblBedNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBedNo.setBounds(68, 305, 191, 29);
		contentPane.add(lblBedNo);
		JLabel lblAdmissionDate = new JLabel("Paid Amount");
		lblAdmissionDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdmissionDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAdmissionDate.setBounds(68, 344, 191, 29);
		contentPane.add(lblAdmissionDate);
		JLabel lblDoctorName = new JLabel("Due Amount");
		lblDoctorName.setHorizontalAlignment(SwingConstants.LEFT);
		lblDoctorName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDoctorName.setBounds(68, 383, 191, 29);
		contentPane.add(lblDoctorName);
		test_rec = new JTextField();
		test_rec.setFont(new Font("Tahoma", Font.PLAIN, 18));
		test_rec.setBounds(275, 141, 259, 29);
		contentPane.add(test_rec);
		test_rec.setColumns(10);
		trecdate = new JTextField();
		trecdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		trecdate.setColumns(10);
		trecdate.setBounds(275, 226, 259, 29);
		contentPane.add(trecdate);
		tdelvrec = new JTextField();
		tdelvrec.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdelvrec.setColumns(10);
		tdelvrec.setBounds(275, 265, 259, 29);
		contentPane.add(tdelvrec);
		tpaid = new JTextField();
		tpaid.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tpaid.setColumns(10);
		tpaid.setBounds(275, 343, 259, 29);
		contentPane.add(tpaid);
		tdue = new JTextField();
		tdue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tdue.setColumns(10);
		tdue.setBounds(275, 383, 259, 29);
		contentPane.add(tdue);
		reg_list = new JComboBox(arr);
		reg_list.setFont(new Font("Tahoma", Font.BOLD, 14));
		reg_list.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		reg_list.setBackground(Color.WHITE);
		reg_list.setBounds(274, 185, 260, 29);
		contentPane.add(reg_list);
		tamnt = new JTextField();
		tamnt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tamnt.setColumns(10);
		tamnt.setBounds(275, 304, 259, 29);
		contentPane.add(tamnt);
	}// end of constructor

private void searchRec()
{
String recno=JOptionPane.showInputDialog("Enter the Test Receipt No. to search the record");
try {
Gamer g = new Gamer();
Connection con = g.setGame();
Statement s = con.createStatement();
ResultSet rs = s.executeQuery("SELECT * FROM `test_reciept` where Rec_no="+Integer.parseInt(recno));
while(rs.next())
{
test_rec.setText(rs.getInt(1)+"");
trecdate.setText(rs.getString(3)+"");
tdelvrec.setText(rs.getString(4)+"");
tamnt.setText(rs.getInt(5)+"");
tpaid.setText(rs.getInt(6)+"");
tdue.setText(rs.getInt(7)+"");
}
rs.close();
} catch (Exception e2) {
JOptionPane.showMessageDialog(null, "Record does not exist!"+e2);
System.out.println(e2.getStackTrace());
}
}// search()

public void LoadReg_no()
{
try
{
Gamer g = new Gamer();
Connection con = g.setGame();
Statement s = con.createStatement();
ResultSet rs = s.executeQuery("SELECT reg_no FROM `Registration`");
while(rs.next())
{
//reg_list.addItem(rs.getInt("Reg_no"));
}
}
catch(SQLException e)
{
JOptionPane.showMessageDialog(null,"Error in Registration No. Transfer"+e);
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
		trecdate.setText("");
		tdelvrec.setText("");
		tamnt.setText("");
		tpaid.setText("");
		tdue.setText("");
	}
}