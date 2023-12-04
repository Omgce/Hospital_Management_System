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

public class Room_Detail extends JFrame {
	private JPanel contentPane;
	private JTextField trno;
	private JTextField tnoofbed;
	private JTextField tvacbed;
	private JTextField tbcharge;
	private JTextField tresbed;
	String regno, arecno;
	Connection conn = null;
	Statement stmt = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Room_Detail frame = new Room_Detail();
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
public Room_Detail() {
setTitle("Admission");
setResizable(false);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(100, 100, 860, 456);
contentPane = new JPanel();
contentPane.setBackground(Color.WHITE);
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);
JPanel pnl_btn = new JPanel();
pnl_btn.setBackground(new Color(204, 0, 204));
pnl_btn.setBounds(663, 103, 181, 313);
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
int rno = Integer.parseInt(trno.getText());
int nbed = Integer.parseInt(tnoofbed.getText());
int vbed = Integer.parseInt(tvacbed.getText());
int rbed = Integer.parseInt(tresbed.getText());
int bchrge = Integer.parseInt(tbcharge.getText());
String insert = "INSERT INTO `room_detail`(`Room_no`, `No_of_bed`,`Vacant_bed`, `Reserved_bed`, `Bed_charge`) VALUES ("+rno+","+nbed+","+vbed+","+rbed+","+bchrge+")";
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
arecno=JOptionPane.showInputDialog("Enter the Room_no to search the record");
try {
String delete = "delete FROM `room_detail`where Room_no="+Integer.parseInt(arecno);
Class.forName("com.mysql.jdbc.Driver");
conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/doctor","root","ROOT");
stmt = conn.createStatement();
stmt.execute(delete);
JOptionPane.showMessageDialog(null, "Record Deleted Succesfully!");
} catch (Exception e) {
JOptionPane.showMessageDialog(null,
"Deletion failed!");
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
JOptionPane.showMessageDialog(null,
"Updation failed!");
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
JLabel lblNewLabel = new JLabel("Entry Form of Room Detail");
lblNewLabel.setForeground(Color.WHITE);
lblNewLabel.setBounds(10, 0, 834, 86);
panel.add(lblNewLabel);
lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 39));
lblNewLabel.setBackground(Color.BLUE);
JLabel lblNewLabel_1 = new JLabel("Room No");
lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
lblNewLabel_1.setBounds(68, 183, 191, 29);
contentPane.add(lblNewLabel_1);
JLabel lblChartNo = new JLabel("No of Bed");
lblChartNo.setHorizontalAlignment(SwingConstants.LEFT);
lblChartNo.setFont(new Font("Tahoma", Font.BOLD, 16));
lblChartNo.setBounds(68, 227, 191, 29);
contentPane.add(lblChartNo);
JLabel lblRoomNo = new JLabel("Vacant Bed");
lblRoomNo.setHorizontalAlignment(SwingConstants.LEFT);
lblRoomNo.setFont(new Font("Tahoma", Font.BOLD, 16));
lblRoomNo.setBounds(68, 266, 191, 29);
contentPane.add(lblRoomNo);
JLabel lblBedNo = new JLabel("Reserved Bed");
lblBedNo.setHorizontalAlignment(SwingConstants.LEFT);
lblBedNo.setFont(new Font("Tahoma", Font.BOLD, 16));
lblBedNo.setBounds(68, 305, 191, 29);
contentPane.add(lblBedNo);
JLabel lblAdmissionDate = new JLabel("Bed Charge");
lblAdmissionDate.setHorizontalAlignment(SwingConstants.LEFT);
lblAdmissionDate.setFont(new Font("Tahoma", Font.BOLD, 16));
lblAdmissionDate.setBounds(68, 344, 191, 29);
contentPane.add(lblAdmissionDate);
trno = new JTextField();
trno.setFont(new Font("Tahoma", Font.PLAIN, 18));
trno.setBounds(275, 180, 259, 29);
contentPane.add(trno);
trno.setColumns(10);
tnoofbed = new JTextField();
tnoofbed.setFont(new Font("Tahoma", Font.PLAIN, 18));
tnoofbed.setColumns(10);
tnoofbed.setBounds(275, 226, 259, 29);
contentPane.add(tnoofbed);
tvacbed = new JTextField();
tvacbed.setFont(new Font("Tahoma", Font.PLAIN, 18));
tvacbed.setColumns(10);
tvacbed.setBounds(275, 265, 259, 29);
contentPane.add(tvacbed);
tbcharge = new JTextField();
tbcharge.setFont(new Font("Tahoma", Font.PLAIN, 18));
tbcharge.setColumns(10);
tbcharge.setBounds(275, 343, 259, 29);
contentPane.add(tbcharge);
tresbed = new JTextField();
tresbed.setFont(new Font("Tahoma", Font.PLAIN, 18));
tresbed.setColumns(10);
tresbed.setBounds(275, 304, 259, 29);
contentPane.add(tresbed);
}// end of constructor

	private void searchRec() {
		String rno = JOptionPane.showInputDialog("Enter the Room No. to search the record");
		try {
			Gamer g = new Gamer();
			Connection con = g.setGame();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM `room_detail` where Room_no=" + Integer.parseInt(rno));
			while (rs.next()) {
				trno.setText(rs.getInt(1) + "");
				tnoofbed.setText(rs.getInt(2) + "");
				tvacbed.setText(rs.getInt(3) + "");
				tresbed.setText(rs.getInt(4) + "");
				tbcharge.setText(rs.getInt(5) + "");
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
		trno.setText("");
		tnoofbed.setText("");
		tvacbed.setText("");
		tresbed.setText("");
		tbcharge.setText("");
	}
}