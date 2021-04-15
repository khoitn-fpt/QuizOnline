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
import javax.naming.NamingException;
import khoi.connection.DBContext;
import khoi.dto.UserDTO;

/**
 *
 * @author Nguyen Khoi
 */
public class UserDAO implements Serializable{
    public UserDTO checkLogin(String email, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select [email],[password],[fullName],[roleID],[status]\n"
                        + "from [dbo].[tblUsers]\n"
                        + "where email = ? and password = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return new UserDTO(rs.getString("email"), rs.getString("password"), rs.getString("fullName"), rs.getString("roleID"), rs.getBoolean("status"));
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
        return null;
    }

    public boolean createAccount(String email, String password, String fullname, String role, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "insert into [dbo].[tblUsers]([fullName],[email],[password],[roleID],[status])\n"
                        + "values (?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, fullname);
                pst.setString(2, email);
                pst.setString(3, password);
                pst.setString(4, role);
                pst.setBoolean(5, status);
                int row = pst.executeUpdate();
                if (row > 0 ) {
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
