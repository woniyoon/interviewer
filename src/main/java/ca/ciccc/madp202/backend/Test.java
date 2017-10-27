package ca.ciccc.madp202.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import ca.ciccc.madp202.backend.model.HistoryEntity;
import ca.ciccc.madp202.backend.model.HistoryRecord;
import ca.ciccc.madp202.backend.service.InterviewService;

public class Test {
	public static void main(String[] args) {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ArrayList<String> rightAnswers = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		Timestamp timestamp = new Timestamp(calendar.getTime().getTime());

		HistoryRecord historyRecord = new HistoryRecord();

		String url = "jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
		String id = "root";
		String password = "gjddj02!";

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {

			int userID = 4;
			con = DriverManager.getConnection(url, id, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * from history WHERE userID ='" + userID + "';");

			while (rs.next()) {
				historyRecord.getRecordOfHistory().add(new HistoryEntity(rs.getString(3), rs.getDate(4), rs.getInt(5)));
			}

			 for (int i = 0; i < historyRecord.getRecordOfHistory().size(); i++) {
		            System.out.println("History\n" + "Topic : " + historyRecord.getRecordOfHistory().get(i).getTopic().toString()+"\n"+ "Date : "+ historyRecord.getRecordOfHistory().get(i).getDate().toString()+"\n" + "Score : "+historyRecord.getRecordOfHistory().get(i).getScore());
		        }
			
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(InterviewService.class.getName());
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

}
