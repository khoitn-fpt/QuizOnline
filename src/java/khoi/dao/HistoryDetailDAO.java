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
import khoi.dto.HistoryDetailDTO;

/**
 *
 * @author Nguyen Khoi
 */
public class HistoryDetailDAO implements Serializable{
     private List<HistoryDetailDTO> listHistoryDetail;

    public List<HistoryDetailDTO> getListHistoryDetail() {
        return listHistoryDetail;
    }
    
    public void GetHistoryDetail(int num) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select [questionName],[userChoice],[isRightChoice]\n"
                        + "from [dbo].[tblHistoryDetail]\n"
                        + "where [historyNum]= ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, num);
                rs = pst.executeQuery();
                while(rs.next()){
                    HistoryDetailDTO dto = new HistoryDetailDTO(num, rs.getString("questionName"), rs.getString("userChoice"), rs.getString("isRightChoice"));
                    if(this.listHistoryDetail == null){
                        this.listHistoryDetail = new ArrayList<>();
                    }
                    this.listHistoryDetail.add(dto);
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

    public boolean CreateHistoryDetail(int historyNum, String questionName, String userChoice,String isRightChoice) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "insert into [dbo].[tblHistoryDetail]([historyNum],[questionName],[userChoice],[isRightChoice])\n"
                        + "values (?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, historyNum);
                pst.setString(2, questionName);
                pst.setString(3, userChoice);
                pst.setString(4, isRightChoice);
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
}
