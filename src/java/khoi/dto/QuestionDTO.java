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
public class QuestionDTO implements Serializable{
     private int questionID;
    private  String questionName;
    private String subjectID;
    private Date createDate;
    private boolean status;

    public QuestionDTO() {
    }

    public QuestionDTO(int questionID, String questionName, String subjectID, Date createDate, boolean status) {
        this.questionID = questionID;
        this.questionName = questionName;
        this.subjectID = subjectID;
        this.createDate = createDate;
        this.status = status;
    }
    public QuestionDTO(int questionID, String questionName, String subjectID, boolean status) {
        this.questionID = questionID;
        this.questionName = questionName;
        this.subjectID = subjectID;
        this.status = status;
    }
    public QuestionDTO(int questionID, String questionName) {
        this.questionID = questionID;
        this.questionName = questionName;
        
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
