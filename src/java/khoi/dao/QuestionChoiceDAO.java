/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khoi.connection.DBContext;
import khoi.dto.QuestionChoiceDTO;

/**
 *
 * @author Nguyen Khoi
 */
public class QuestionChoiceDAO implements Serializable{
    public boolean createQuestionChoice(int questionID, boolean isRight, String answer) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "insert into [dbo].[tblQuestionChoices]([questionID],[isRight],[answer])\n"
                        + "values (?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, questionID);
                pst.setBoolean(2, isRight);
                pst.setString(3, answer);
                int row = pst.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    private List<QuestionChoiceDTO> listChoice;

    public List<QuestionChoiceDTO> getListChoice() {
        return listChoice;
    }

    public void getQuestionChoice(int questionID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select [choiceID],[questionID],[isRight],[answer]\n"
                        + "from [dbo].[tblQuestionChoices]\n"
                        + "where [questionID] = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, questionID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (this.listChoice == null) {
                        this.listChoice = new ArrayList<>();
                    }
                    this.listChoice.add(new QuestionChoiceDTO(rs.getInt("choiceID"), rs.getInt("questionID"), rs.getBoolean("isRight"), rs.getString("answer")));
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean updateQuestionChoice(int choiceID, int questionID, boolean isRight, String answer) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "update [dbo].[tblQuestionChoices]\n"
                        + "set [answer]=?,[isRight]=?\n"
                        + "where [questionID] = ? and [choiceID]=?";
                pst = con.prepareStatement(sql);
                pst.setString(1, answer);
                pst.setBoolean(2, isRight);
                pst.setInt(3, questionID);
                pst.setInt(4, choiceID);
                int row = pst.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    private List<QuestionChoiceDTO> listQuizChoice;

    public List<QuestionChoiceDTO> getListQuizChoice() {
        return listQuizChoice;
    }
    
    public void GetQuizChoice(int questionID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select [choiceID],[questionID],[isRight],[answer]\n"
                        + "from [dbo].[tblQuestionChoices]\n"
                        + "where [questionID]= ? order by NEWID()";
                pst = con.prepareStatement(sql);
                pst.setInt(1, questionID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    QuestionChoiceDTO dto = new QuestionChoiceDTO(rs.getInt("choiceID"), rs.getInt("questionID"), rs.getBoolean("isRight"), rs.getString("answer"));
                    if(this.listQuizChoice == null){
                        this.listQuizChoice = new ArrayList<>();
                    }
                    this.listQuizChoice.add(dto);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
