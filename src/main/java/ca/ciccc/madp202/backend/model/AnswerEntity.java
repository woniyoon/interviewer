package ca.ciccc.madp202.backend.model;

public class AnswerEntity {

    //properties
    private int questionID;
    private String answer;

    //constructor
    public AnswerEntity() {}
   
    public AnswerEntity(int questionID, String answer) {
        this.questionID = questionID;
        this.answer = answer;
    }

    //getter & setter
    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
	
}
