package ca.ciccc.madp202.backend.model;

import java.util.ArrayList;


public class HistoryRecord {

    ArrayList<HistoryEntity> recordOfHistory = new ArrayList<HistoryEntity>();
    
    public HistoryRecord () {}

    public HistoryRecord(ArrayList<HistoryEntity> recordOfHistory) {
        this.recordOfHistory = recordOfHistory;
    }

    public ArrayList<HistoryEntity> getRecordOfHistory() {
        return recordOfHistory;
    }

    public void setRecordOfHistory(ArrayList<HistoryEntity> recordOfHistory) {
        this.recordOfHistory = recordOfHistory;
    }

}
