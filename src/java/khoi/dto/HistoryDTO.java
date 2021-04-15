/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Nguyen Khoi
 */
public class HistoryDTO implements Serializable{
    private int num;
    private String email;
    private String subjectID;
    private Date quizDate;
    private float mark;
    private String numOfCorrect;

    public HistoryDTO() {
    }

    public HistoryDTO(int num, String email, String subjectID, Date quizDate, float mark, String numOfCorrect) {
        this.num = num;
        this.email = email;
        this.subjectID = subjectID;
        this.quizDate = quizDate;
        this.mark = mark;
        this.numOfCorrect = numOfCorrect;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public Date getQuizDate() {
        return quizDate;
    }

    public void setQuizDate(Date quizDate) {
        this.quizDate = quizDate;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public String getNumOfCorrect() {
        return numOfCorrect;
    }

    public void setNumOfCorrect(String numOfCorrect) {
        this.numOfCorrect = numOfCorrect;
    }
    
}
