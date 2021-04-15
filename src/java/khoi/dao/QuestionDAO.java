/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khoi.connection.DBContext;
import khoi.dto.QuestionDTO;

/**
 *
 * @author Nguyen Khoi
 */
public class QuestionDAO implements Serializable{
    public boolean CreateQuestion(String questionName, String subjectID, Date createDate, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "insert into [dbo].[tblQuestions]([questionName],[subjectID],[createDate],[status])\n"
                        + "values (?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, questionName);
                ps.setString(2, subjectID);
                ps.setDate(3, createDate);
                ps.setBoolean(4, status);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public int getQuestionID() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "SELECT TOP 1 [questionID] FROM [dbo].[tblQuestions] ORDER BY [questionID] desc";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("questionID");
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    private List<QuestionDTO> listQuestionn;

    public List<QuestionDTO> getListQuestionn() {
        return listQuestionn;
    }

    public void searchQuestion(String searchValue, String subjectID, boolean status, int index) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                if (!subjectID.equals("all")) {
                    String sql = "with x as (select ROW_NUMBER() over (order by [questionName] asc) as r,*\n"
                            + "from [dbo].[tblQuestions]\n"
                            + "where [questionName] like ? and status = ? and [subjectID] = ? ) \n"
                            + "select  [questionID],[questionName],[subjectID],[status]\n"
                            + "from x \n"
                            + "where r between ?*20-19 and ?*20";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + searchValue + "%");
                    ps.setBoolean(2, status);
                    ps.setString(3, subjectID);
                    ps.setInt(4, index);
                    ps.setInt(5, index);
                } else {
                    String sql = "with x as (select ROW_NUMBER() over (order by [questionName] asc) as r,*\n"
                            + "from [dbo].[tblQuestions]\n"
                            + "where [questionName] like ? and status = ? ) \n"
                            + "select  [questionID],[questionName],[subjectID],[status]\n"
                            + "from x \n"
                            + "where r between ?*20-19 and ?*20";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + searchValue + "%");
                    ps.setBoolean(2, status);
                    ps.setInt(3, index);
                    ps.setInt(4, index);
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    QuestionDTO dto = new QuestionDTO(rs.getInt("questionID"), rs.getString("questionName"), rs.getString("subjectID"), rs.getBoolean("status"));
                    if (this.listQuestionn == null) {
                        this.listQuestionn = new ArrayList<>();
                    }
                    this.listQuestionn.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public int count(String searchValue, String subjectID, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                if (subjectID.equals("all")) {
                    String sql = "select COUNT(*) from [dbo].[tblQuestions] where [questionName] like ? and status = ? ";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + searchValue + "%");
                    ps.setBoolean(2, status);
                    rs = ps.executeQuery();
                } else {
                    String sql = "select COUNT(*) from [dbo].[tblQuestions] where [questionName] like ? and status = ? and [subjectID] = ? ";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + searchValue + "%");
                    ps.setBoolean(2, status);
                    ps.setString(3, subjectID);
                    rs = ps.executeQuery();

                }
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public boolean DeleteQuestion(int questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[tblQuestions]\n"
                        + "SET [status] = 'false'\n"
                        + "WHERE [questionID] = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, questionID);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public QuestionDTO GetQuestion(int questionID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select [questionID],[questionName],[subjectID],[status]\n"
                        + "from [dbo].[tblQuestions]\n"
                        + "where [questionID] = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, questionID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return new QuestionDTO(rs.getInt("questionID"), rs.getString("questionName"), rs.getString("subjectID"), rs.getBoolean("status"));
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    private List<QuestionDTO> listQuiz;

    public List<QuestionDTO> getListQuiz() {
        return listQuiz;
    }

    public void GetQuiz(int numOfQuestion, String subjectID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select top (?) [questionID],[questionName]\n"
                        + "from [dbo].[tblQuestions] \n"
                        + "where [subjectID] = ? and status ='true'\n"
                        + "order by NEWID()";
                ps = con.prepareStatement(sql);
                ps.setInt(1, numOfQuestion);
                ps.setString(2, subjectID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    QuestionDTO dto = new QuestionDTO(rs.getInt("questionID"), rs.getString("questionName"));
                    if (this.listQuiz == null) {
                        this.listQuiz = new ArrayList<>();
                    }
                    this.listQuiz.add(dto);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
    }

    public boolean UpdateQuestion(String questionName, String subjectID, boolean status, int questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "update [dbo].[tblQuestions]\n"
                        + "set [questionName]= ?, [subjectID] = ?, [status] = ?\n"
                        + "where [questionID] = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, questionName);
                ps.setString(2, subjectID);
                ps.setBoolean(3, status);
                ps.setInt(4, questionID);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public int GetNumberOfQuestion(String subject) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select COUNT(*) from [dbo].[tblQuestions] where [subjectID]= ? and [status] = 'true'";
                ps = con.prepareStatement(sql);
                ps.setString(1, subject);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }
}
