package ca.ciccc.madp202.backend.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import ca.ciccc.madp202.backend.Test;
import ca.ciccc.madp202.backend.model.Credential;
import ca.ciccc.madp202.backend.model.Profile;

public class CredentialService {

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	String url = "jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
	String id = "root";
	String password = "Lime1234";
	Profile profile = new Profile();
	
	

	public Profile getProfileEntity(Credential credential) {
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {

			con = DriverManager.getConnection(url, id, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * from userList WHERE username = '" + credential.getUsername() + "'");
	
	
			if(!rs.first()) {
				profile.setUserId(-1);
				profile.setToken(null);
			} else if (rs.first()) {
				profile.setCountry(rs.getString("country"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setJoined(rs.getDate("joined"));
				profile.setUserId(rs.getInt("userId"));
				profile.setToken("token");
			}
				

		} catch (SQLException ex) {

			Logger lgr = Logger.getLogger(Test.class.getName());
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
		return profile;
	}

	
//	public Profile giveProfileEntity(Credential credential) {
//		try {
//			Class.forName("org.gjt.mm.mysql.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		try {
//
//			con = DriverManager.getConnection(url, id, password);
//			st = con.createStatement();
//			rs = st.executeQuery("SELECT * from userList");
//
//			while (rs.next()) {			 
//					profile.setCountry(rs.getString("country"));
//					profile.setFirstName(rs.getString("firstName"));
//					profile.setLastName(rs.getString("lastName"));
//					profile.setJoined(rs.getDate("joined"));
//					profile.setUserId(rs.getInt("userId"));
//					profile.setToken("token");
//				} 
//			
//
//		} catch (SQLException ex) {
//
//			Logger lgr = Logger.getLogger(Test.class.getName());
//			lgr.log(Level.SEVERE, ex.getMessage(), ex);
//
//		} finally {
//			try {
//				con.close();
//				st.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//		return profile;
//	}

	
	
}
