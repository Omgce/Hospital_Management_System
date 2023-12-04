package dc;

import java.sql.*;
import javax.swing.JOptionPane;

public class Gamer {
	public static Connection setGame(String action) {
		Connection con = null;
		Statement stmnt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctor", "root", "ROOT");
			System.out.println("ready .....");
			stmnt = con.createStatement();
			stmnt.execute(action);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Your unable to use it???" + e.getStackTrace());
		}
		return con;
	}

	public Connection setGame() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctor", "root", "ROOT");
			System.out.println("ready .....");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Your unable to use it???" + e);
		}
		return con;
	}
}// end