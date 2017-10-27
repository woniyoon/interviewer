package ca.ciccc.madp202.backend.model;

import java.util.Date;

public class HistoryEntity {

		String topic;
	    Date date;
	    int score;

	    public HistoryEntity() {}
	    
	    public HistoryEntity(String topic, Date date, int score) {
	        this.topic = topic;
	        this.date = date;
	        this.score = score;
	    }

	    public String getTopic() {
	        return topic;
	    }

	    public void setTopic(String topic) {
	        this.topic = topic;
	    }

	    public Date getDate() {
	        return date;
	    }

	    public void setDate(Date date) {
	        this.date = date;
	    }

	    public int getScore() {
	        return score;
	    }

	    public void setScore(int score) {
	        this.score = score;
	    }
}
