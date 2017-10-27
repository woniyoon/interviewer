package ca.ciccc.madp202.backend.model;

import java.util.ArrayList;

public class Quiz {
	   private int duration;
	    private ArrayList<QuestionEntity> questionEntities;
	    private String topic;
	    private String interviewID;


	    //constructor
	    public Quiz() {}
	    
	    public Quiz(int duration, ArrayList<QuestionEntity> questionEntities, String topic, String interviewID) {
	        this.duration = duration;
	        this.questionEntities = questionEntities;
	        this.topic = topic;
	        this.interviewID = interviewID;
	    }

	    //getter & setter
	    public int getDuration() {
	        return duration;
	    }

	    public void setDuration(int duration) {
	        this.duration = duration;
	    }

	    public ArrayList<QuestionEntity> getQuestionEntities() {
	        return questionEntities;
	    }

	    public void setQuestionEntities(ArrayList<QuestionEntity> questionEntities) {
	        this.questionEntities = questionEntities;
	    }

	    public String getTopic() {
	        return topic;
	    }

	    public void setTopic(String topic) {
	        this.topic = topic;
	    }

	    public String getInterviewID() {
	        return interviewID;
	    }

	    public void setInterviewID(String interviewID) {
	        this.interviewID = interviewID;
	    }

}
