package ca.ciccc.madp202.backend.model;

import java.util.Date;

public class InterviewResult {
	  //properties
    private String interviewID;
    private int userID;
    private int score;
    private int correct;
    private int wrong;
    private int skipped;
    private int total;
    private Date date;


    //constructor
    public InterviewResult() {}
    
    public InterviewResult(String interviewID, int userID, int score, int correct, int wrong, int skipped, int total, Date date) {
        this.interviewID = interviewID;
        this.userID = userID;
        this.score = score;
        this.correct = correct;
        this.wrong = wrong;
        this.skipped = skipped;
        this.total = total;
        this.date = date;
    }


    //getter & setter
    public String getInterviewID() {
        return interviewID;
    }

    public void setInterviewID(String interviewID) {
        this.interviewID = interviewID;
    }

    public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
    
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public int getSkipped() {
        return skipped;
    }

    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
