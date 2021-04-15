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
public class QuestionChoiceDTO implements Serializable{
    private int choiceID;
    private int questionID;
    private boolean isRight;
    private String answer;

    public QuestionChoiceDTO() {
    }

    public QuestionChoiceDTO(int choiceID, int questionID, boolean isRight, String answer) {
        this.choiceID = choiceID;
        this.questionID = questionID;
        this.isRight = isRight;
        this.answer = answer;
    }

    public int getChoiceID() {
        return choiceID;
    }

    public void setChoiceID(int choiceID) {
        this.choiceID = choiceID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public boolean isIsRight() {
        return isRight;
    }

    public void setIsRight(boolean isRight) {
        this.isRight = isRight;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
}
