package ca.ciccc.madp202.backend.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import ca.ciccc.madp202.backend.model.QuestionEntity;
import ca.ciccc.madp202.backend.model.Quiz;

public class InterviewService {

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	QuestionEntity questionEntity = new QuestionEntity();
	ArrayList<QuestionEntity> quiz2 = new ArrayList<QuestionEntity>();
	Quiz quiz = new Quiz();

	String url = "jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
	String id = "root";
	String password = "gjddj02!";

	public Quiz setDetails(String type, Quiz quiz) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {

			con = DriverManager.getConnection(url, id, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM questions " + 
					"INNER JOIN interviewType ON questions.interviewID = interviewType.interviewID " + 
					"WHERE interviewType.type =\"" + type+ "\";");

			while (rs.next()) {
				quiz.setInterviewID(rs.getString("interviewID"));
				quiz.setDuration(rs.getInt("duration"));
				quiz.setTopic(rs.getString("type"));
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
		return quiz;

	}

	// public Quiz setDetails(String type, Quiz quiz) {
	// try {
	// Class.forName("org.gjt.mm.mysql.Driver");
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	// try {
	//
	// con = DriverManager.getConnection(url, id, password);
	// st = con.createStatement();
	// rs = st.executeQuery("SELECT * from interviewType");
	//
	// if (type.equalsIgnoreCase(rs.getString(2))) {
	// quiz.setDuration(rs.getInt(3));
	// quiz.setTopic(type);
	// }
	//
	// } catch (SQLException ex) {
	// Logger lgr = Logger.getLogger(InterviewService.class.getName());
	// lgr.log(Level.SEVERE, ex.getMessage(), ex);
	//
	// } finally {
	// try {
	// con.close();
	// st.close();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	// return quiz;
	//
	// }

	public Quiz loadQuestions(String type, Quiz quiz) {

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {

			con = DriverManager.getConnection(url, id, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * from questions");

			while (rs.next()) {
				if (rs.getString(9).equals(type)) {
					int difficultyLevel = rs.getInt("difficultyLevel");
					String description = rs.getString("description");
					String option1 = rs.getString("option1");
					String option2 = rs.getString("option2");
					String option3 = rs.getString("option3");
					String option4 = rs.getString("option4");
					int questionID = rs.getInt("questionID");

					QuestionEntity questionEntity = new QuestionEntity(difficultyLevel, description, option1, option2,
							option3, option4, questionID);

					quiz2.add(questionEntity);
				}
			}

			quiz.setQuestionEntities(quiz2);

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
		return quiz;
	}

}
