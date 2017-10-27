package ca.ciccc.madp202.backend.model;

import java.util.ArrayList;

public class CollectedAnswer {
    //properties
//    HashMap<Integer, AnswerEntity> collectedAnswers;
	ArrayList<AnswerEntity> givenAnswers = new ArrayList<>();
	private int userID;
    private String interviewID;


    //constructor
    public CollectedAnswer() {}
    
    public CollectedAnswer(ArrayList<AnswerEntity> givenAnswers, int userID, String interviewID) {
        this.givenAnswers = givenAnswers;
        this.userID = userID;
        this.interviewID = interviewID;
    }


    //getter & setter
//    public HashMap<Integer, AnswerEntity> getCollectedAnswers() {
//        return collectedAnswers;
//    }
//
//    public void setCollectedAnswers(HashMap<Integer, AnswerEntity> collectedAnswers) {
//        this.collectedAnswers = collectedAnswers;
//    }
    
    
    public ArrayList<AnswerEntity> getGivenAnswers() {
		return givenAnswers;
	}

	public void setGivenAnswers(ArrayList<AnswerEntity> givenAnswers) {
		this.givenAnswers = givenAnswers;
	}

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getInterviewID() {
        return interviewID;
    }

    public void setInterviewID(String interviewID) {
        this.interviewID = interviewID;
    }
}
