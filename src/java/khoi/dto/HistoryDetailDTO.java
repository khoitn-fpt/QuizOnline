/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.dto;

import java.io.Serializable;

/**
 *
 * @author Nguyen Khoi
 */
public class HistoryDetailDTO implements Serializable{
     private int historyNum;
    private String questionName;
    private String userChoice;
    private String isRightChoice;

    public HistoryDetailDTO() {
    }

    public HistoryDetailDTO(int historyNum, String questionName, String userChoice, String isRightChoice) {
        this.historyNum = historyNum;
        this.questionName = questionName;
        this.userChoice = userChoice;
        this.isRightChoice = isRightChoice;
    }

    public int getHistoryNum() {
        return historyNum;
    }

    public void setHistoryNum(int historyNum) {
        this.historyNum = historyNum;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(String userChoice) {
        this.userChoice = userChoice;
    }

    public String getIsRightChoice() {
        return isRightChoice;
    }

    public void setIsRightChoice(String isRightChoice) {
        this.isRightChoice = isRightChoice;
    }
    
}
