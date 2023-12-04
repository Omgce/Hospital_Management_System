package dc;

import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class MainMenu extends JFrame {
	private JMenuBar mb;
	private JMenu mPatient, mBilling, mEnquiry, mReport, mOrganization, mExit;
	private JMenuItem miRegistration, miAdmission, miTest_Rec, miMoney_Rec, miPatient_Acc;
	private JMenuItem miTest_Db, miTest_G, miTest_F, miRoom_D, miDoctor_D;
	private JMenuItem mrP_R_Rep, mrP_Adm_Rep, mrM_R_Rep, mrP_Acc_Rep;
	private JMenuItem miYes, miNo;
	private JLabel l1, title;
	private JPanel p1, p2;
	private ImageIcon image;
	private JLabel lblImage;
	private JPanel pnl;
	private Container cn;
	String str1;
	int r;
	Dimension screensize;

	public MainMenu() {
		setTitle("HOSPITAL MANAGEMENT SYSTEM ");
		Container cn = new Container();
		cn = getContentPane();
		cn.setLayout(null);
		cn.setBackground(new Color(252, 226, 250));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(mb);
		p1 = new JPanel();
		image = new ImageIcon("E://amit.jpg");
		l1 = new JLabel(image);
		p1.add(l1);
		p1.setBounds(0, 03, 1375, 1000);
		cn.add(p1);
		/*
		 * title=new JLabel("Hospital Automation System"); title.setFont(new
		 * Font("Times New Roman",1,30)); title.setForeground(Color.red); p2=new
		 * JPanel(); p2.add(title); p2.setBackground(Color.pink);
		 * p2.setBounds(0,50,850,100); cn.add(p2);
		 */
		mb = new JMenuBar();
		mPatient = new JMenu("Patient Entry ");
		mPatient.setMnemonic('P');
		mBilling = new JMenu("Billing Detail ");
		mBilling.setMnemonic('B');
		mReport = new JMenu("Report ");
		mReport.setMnemonic('R');
		mEnquiry = new JMenu("Enquiry ");
		mEnquiry.setMnemonic('E');
		mOrganization = new JMenu("Organization ");
		mOrganization.setMnemonic('O');
		mExit = new JMenu("Exit");
		mExit.setMnemonic('X');
		mrP_R_Rep = new JMenuItem("Patient Registration Report ");
		mrP_Adm_Rep = new JMenuItem("Patient Account Report ");
		mrM_R_Rep = new JMenuItem("Money Reciept Report ");
		mrP_Acc_Rep = new JMenuItem("Patient Account Report");
		miRegistration = new JMenuItem("Registration ");
		miAdmission = new JMenuItem("Admission ");
		miTest_Rec = new JMenuItem("Test Reciept ");
		miMoney_Rec = new JMenuItem("Money Reciept ");
		miPatient_Acc = new JMenuItem("Patient Account");
		miTest_Db = new JMenuItem("Test Detail ");
		miTest_G = new JMenuItem("Test Given ");
		miTest_F = new JMenuItem("Test Found ");
		miRoom_D = new JMenuItem("Room Detail");
		miDoctor_D = new JMenuItem("Doctor Detail");
		miYes = new JMenuItem("Yes");
		miNo = new JMenuItem("No");
		miRegistration.addActionListener(new clickListener());
		miAdmission.addActionListener(new clickListener());
		miTest_Rec.addActionListener(new clickListener());
		miMoney_Rec.addActionListener(new clickListener());
		miPatient_Acc.addActionListener(new clickListener());
		miTest_Db.addActionListener(new clickListener());
		miTest_G.addActionListener(new clickListener());
		miTest_F.addActionListener(new clickListener());
		miRoom_D.addActionListener(new clickListener());
		miDoctor_D.addActionListener(new clickListener());
		mrP_R_Rep.addActionListener(new clickListener());
		mrP_Adm_Rep.addActionListener(new clickListener());
		mrM_R_Rep.addActionListener(new clickListener());
		mrP_Acc_Rep.addActionListener(new clickListener());
		miYes.addActionListener(new clickListener());
		mb.add(mPatient);
		mb.add(mBilling);
		mb.add(mEnquiry);
		mb.add(mReport);
		mb.add(mOrganization);
		mb.add(mExit);
		mPatient.add(miRegistration);
		mPatient.add(miAdmission);
		mBilling.add(miTest_Rec);
		mBilling.add(miMoney_Rec);
		mEnquiry.add(miPatient_Acc);
		mEnquiry.add(miDoctor_D);
		mEnquiry.add(miRoom_D);
		mReport.add(mrP_R_Rep);
		mReport.add(mrP_Adm_Rep);
		mReport.add(mrM_R_Rep);
		mReport.add(mrP_Acc_Rep);
		mOrganization.add(miTest_Db);
		mOrganization.add(miTest_G);
		mOrganization.add(miTest_F);
		mExit.add(miYes);
		mExit.add(miNo);
		pack();
		setJMenuBar(mb);
		setSize(520, 229);
		screensize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screensize.width - 800) / 2, (screensize.height - 600) / 2, 900, 600);
		setVisible(true);
		setResizable(true);
	}

	protected void processWindowEvent(WindowEvent we) {
		if (we.getID() == we.WINDOW_CLOSING) {
			r = JOptionPane.showConfirmDialog(null, "Do you want to exit !", "Enter Carefully",
					JOptionPane.WARNING_MESSAGE);
			if (r == JOptionPane.YES_OPTION)
				dispose();
		}
	}

	public class clickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == miRegistration) {
				Registration sm = new Registration();
			}
			if (e.getSource() == miAdmission) {
				Admission dm = new Admission();
			}
			if (e.getSource() == miTest_Rec) {
				Test_Reciept bm = new Test_Reciept();
			}
			if (e.getSource() == miMoney_Rec) {
				Money_Reciept popm = new Money_Reciept();
			}
			if (e.getSource() == miPatient_Acc) {
				Patient_Account lm = new Patient_Account();
			}
			if (e.getSource() == miTest_Db) {
				Test_Db hm = new Test_Db();
			}
			if (e.getSource() == miTest_G) {
				Test_Given wm = new Test_Given();
			}
			if (e.getSource() == miTest_F) {
				Test_Given tm = new Test_Given();
			}
			if (e.getSource() == miRoom_D) {
				Room_Detail sbr = new Room_Detail();
			}
			if (e.getSource() == miDoctor_D) {
				Doctor_Details spr = new Doctor_Details();
			}
			if (e.getSource() == mrP_R_Rep) {
				Registration svr = new Registration();
			}
			if (e.getSource() == mrP_Adm_Rep) {
				Admission spr = new Admission();
			}
			if (e.getSource() == mrM_R_Rep) {
				Money_Reciept slr = new Money_Reciept();
			}
			if (e.getSource() == mrP_Acc_Rep) {
				Patient_Account shr = new Patient_Account();
			}
			if (e.getSource() == miYes) {
				r = JOptionPane.showConfirmDialog(null, "Do you want to exit !", "Enter Carefully",
						JOptionPane.WARNING_MESSAGE);
				if (r == JOptionPane.YES_OPTION)
					dispose();
			}
		}
	}

	public static void main(String args[]) {
		MainMenu mm = new MainMenu();
	}
}