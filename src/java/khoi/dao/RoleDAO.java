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

/**
 *
 * @author Nguyen Khoi
 */
public class RoleDAO implements Serializable{
     public String getRole(String roleID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select [roleName]\n"
                        + "from [dbo].[tblRoles]\n"
                        + "where [roleID] = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, roleID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return rs.getString("roleName");
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
}
