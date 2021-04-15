/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoi.dao.SubjectDAO;
import khoi.dao.UserDAO;
import khoi.dto.SubjectDTO;
import khoi.dto.UserDTO;
import khoi.encryp.SHA_256;
import org.apache.log4j.Logger;

/**
 *
 * @author Nguyen Khoi
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "loginPage";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            UserDAO dao = new UserDAO();
            SHA_256 encry = new SHA_256();
            String passwordEncry = encry.toHexString(encry.getSHA(password));
            UserDTO dto = dao.checkLogin(email, passwordEncry);
            SubjectDAO sdao = new SubjectDAO();
            sdao.getSubject();
            List<SubjectDTO> listSubject = sdao.getListSubject();
            if (dto != null) {
                HttpSession session = request.getSession();
                session.setAttribute("EMAIL", dto.getEmail());
                session.setAttribute("FULLNAME", dto.getFullName());
                session.setAttribute("ROLE", dto.getRoleID());
                session.setAttribute("ListSubject", listSubject);
                url = "adminPage";
            }else{
                request.setAttribute("LoginFail", "Wrong email or password");
            }

        } catch (SQLException | NamingException | NoSuchAlgorithmException e) {
            Logger.getLogger(LoginServlet.class).error("Error at LoginServlet " + e.toString());
        } finally {
          request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
