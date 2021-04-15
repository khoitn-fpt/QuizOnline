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
import khoi.dto.SubjectDTO;

/**
 *
 * @author Nguyen Khoi
 */
public class SubjectDAO implements Serializable{
    private List<SubjectDTO> listSubject;

    public List<SubjectDTO> getListSubject() {
        return listSubject;
    }

    public void getSubject() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select [subjectID],[subjectName]\n"
                        + "from [dbo].[tblSubjects]";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    SubjectDTO dto = new SubjectDTO(rs.getString("subjectID"), rs.getString("subjectName"));
                    if (this.listSubject == null) {
                        this.listSubject = new ArrayList<>();
                    }
                    this.listSubject.add(dto);
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
