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
import khoi.dto.HistoryDTO;

/**
 *
 * @author Nguyen Khoi
 */
public class HistoryDAO implements Serializable{
    public boolean CreateHistory(String email, String subjectID, Date quizDate, float mark, String numOfCorrect) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "insert into [dbo].[tblHistory]([email],[subjectID],[quizDate],[mark],[numOfCorrect])\n"
                        + "values (?,?,convert(varchar, GETDATE(), 0),?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, subjectID);
                pst.setFloat(3, mark);
                pst.setString(4, numOfCorrect);
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

    private List<HistoryDTO> listHistory;

    public List<HistoryDTO> getListHistory() {
        return listHistory;
    }

    public void GetHistory(String email, String subject, int index) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                if (subject.equals("all")) {
                    String sql = "with x as (select ROW_NUMBER() over (order by num desc) as r,*\n"
                            + "from [dbo].[tblHistory]\n"
                            + "where  [email] = ? ) \n"
                            + "select num,[email],[subjectID],[quizDate],[mark],[numOfCorrect]\n"
                            + "from x \n"
                            + "where r between ?*10-9 and ?*10";
                    pst = con.prepareStatement(sql);
                    pst.setString(1, email);
                    pst.setInt(2, index);
                    pst.setInt(3, index);
                } else {
                    String sql = "with x as (select ROW_NUMBER() over (order by num desc) as r,*\n"
                            + "from [dbo].[tblHistory]\n"
                            + "where  [email] = ? and [subjectID] = ? ) \n"
                            + "select num,[email],[subjectID],[quizDate],[mark],[numOfCorrect]\n"
                            + "from x \n"
                            + "where r between ?*10-9 and ?*10";
                    pst = con.prepareStatement(sql);
                    pst.setString(1, email);
                    pst.setString(2, subject);
                    pst.setInt(3, index);
                    pst.setInt(4, index);
                }
                rs = pst.executeQuery();
                while (rs.next()) {
                    HistoryDTO dto = new HistoryDTO(rs.getInt("num"),rs.getString("email"), rs.getString("subjectID"), rs.getDate("quizDate"), rs.getFloat("mark"), rs.getString("numOfCorrect"));
                    if (this.listHistory == null) {
                        this.listHistory = new ArrayList<>();
                    }
                    this.listHistory.add(dto);
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

    public int count(String email, String subject) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                if (subject.equals("all")) {
                    String sql = "select COUNT(*) from [dbo].[tblHistory] where [email] = ?";
                    pst = con.prepareStatement(sql);
                    pst.setString(1, email);
                    rs = pst.executeQuery();
                } else {
                    String sql = "select COUNT(*) from [dbo].[tblHistory] where [email] = ? and [subjectID] = ?";
                    pst = con.prepareStatement(sql);
                    pst.setString(1, email);
                    pst.setString(2, subject);
                    rs = pst.executeQuery();

                }
                if (rs.next()) {
                    return rs.getInt(1);
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
        return 0;
    }
    
    public int getHistoryID() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "SELECT TOP 1 [num] FROM [dbo].[tblHistory] ORDER BY [num] desc";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return rs.getInt("num");
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
        return -1;
    }
}
