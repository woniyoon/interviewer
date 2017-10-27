package ca.ciccc.madp202.backend.model;

public class Interview {

    //properties
    String topic;


    //constructor
    public Interview() {}
    
    public Interview(String topic) {
        this.topic = topic;
    }


    //getter & setter
    public String getType() {
        return topic;
    }

    public void setType(String topic) {
        this.topic = topic;
    }

}
