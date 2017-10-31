package ca.ciccc.madp202.backend.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import ca.ciccc.madp202.backend.model.Profile;
import ca.ciccc.madp202.backend.model.User;

public class ProfileService {

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	Profile profile = new Profile();
	Calendar calendar = Calendar.getInstance();
	Timestamp timestamp = new Timestamp(calendar.getTime().getTime());

	String url = "jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
	String id = "root";
	String password = "Lime1234";

	public void addUser(User user) {

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {

			con = DriverManager.getConnection(url, id, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * from userList");

			String sql = "insert into userList (firstName, lastName, username, country, joined) values('"
					+ user.getFirstName() + "','" + user.getLastName() + "','" + user.getUsername() + "','"
					+ user.getNationality() + "','"+ timestamp.toString() +"')";

			st.execute(sql);

		} catch (SQLException ex) {
//			Logger lgr = Logger.getLogger(Test.class.getName());
			Logger lgr = Logger.getLogger(ProfileService.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {
			try {
				con.close();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public Profile addExtraInfo(User user, Profile profile) {

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, id, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * from userList");

			while (rs.next()) {
				if (rs.getString(4).equals(user.getUsername())) {
					profile.setUserId(rs.getInt(1));
					profile.setJoined((Date) rs.getTimestamp(6));
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return profile;
	}
}