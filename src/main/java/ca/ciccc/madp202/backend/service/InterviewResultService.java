package ca.ciccc.madp202.backend.service;

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

import ca.ciccc.madp202.backend.model.CollectedAnswer;
import ca.ciccc.madp202.backend.model.HistoryEntity;
import ca.ciccc.madp202.backend.model.HistoryRecord;
import ca.ciccc.madp202.backend.model.InterviewResult;

public class InterviewResultService {

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	
	ArrayList<String> rightAnswers = new ArrayList<String>();
	Calendar calendar = Calendar.getInstance();
	Timestamp timestamp = new Timestamp(calendar.getTime().getTime());

	int correctAnswer = 0;
	int wrongAnswer = 0;
	int skippedAnswer = 0;
	HistoryRecord historyRecord = new HistoryRecord();

	String url = "jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
	String id = "root";
	String password = "gjddj02!";

	public InterviewResult marking(InterviewResult interviewResult, CollectedAnswer collectedAnswer) {

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {

			con = DriverManager.getConnection(url, id, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * from answers WHERE interviewID='" + collectedAnswer.getInterviewID() + "';");

			while (rs.next()) {
				rightAnswers.add(rs.getString(3));
			}

			
			for (int i = 0; i < collectedAnswer.getGivenAnswers().size(); i++) {
				
				if(collectedAnswer.getGivenAnswers().get(i).getAnswer().equalsIgnoreCase("S")){
					skippedAnswer++;
					wrongAnswer++;
				} else if(!collectedAnswer.getGivenAnswers().get(i).getAnswer().equalsIgnoreCase(rightAnswers.get(i))) {
					wrongAnswer++;					
				} else if(collectedAnswer.getGivenAnswers().get(i).getAnswer().equalsIgnoreCase(rightAnswers.get(i))){
					correctAnswer++;
				}
			}

			interviewResult.setCorrect(correctAnswer);
			interviewResult.setWrong(wrongAnswer);
			interviewResult.setSkipped(skippedAnswer);

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
		return interviewResult;
	}

	
	public void saveHistory(InterviewResult interviewResult) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {

			con = DriverManager.getConnection(url, id, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * from history;");
			
			String type = null;
			
			if(interviewResult.getInterviewID().equals("201")) {
				type = "java";
			} else if (interviewResult.getInterviewID().equals("202")) {
				type = "SQL";
			}
			
			String sql = "insert into history (userID, type, date, score) values('"
					+  interviewResult.getUserID() + "','" + type + "','" + timestamp.toString() + "','"
					+ interviewResult.getScore() +"')";

			st.execute(sql);

			
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

	public HistoryRecord getHistory(int userID) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {

			con = DriverManager.getConnection(url, id, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * from history WHERE userID ='"+ userID+"';");
			
			
			while(rs.next()) {
				historyRecord.getRecordOfHistory().add(new HistoryEntity(rs.getString(3), rs.getDate(4), rs.getInt(5)));
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
		
		return historyRecord;
	}

}
